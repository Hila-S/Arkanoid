//208943555

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.List;

/**
 * GameLevel class holds the sprites and the collidables list. This class is in charge of the animation.
 */
public class GameLevel implements Animation {
    static final double WIDTH = 800;
    static final double HEIGHT = 600;
    static final double SIZE_BORDER = 25;
    static final int RADIUS = 5;
    static final double HEIGHT_PADDLE = 15;
    static final int SCORE_OF_WINNING = 100;
    static final double SIZE_SCORE_INDICATOR = 17;

    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Counter blocksCounter;
    private Counter ballsCounter;
    private Counter score;
    private AnimationRunner runner;
    private boolean running;
    private biuoop.KeyboardSensor keyboard;
    private LevelInformation level;

    /**
     * Constructor that creates a new Game.
     *
     * @param level a LevelInformation.
     * @param ks    a KeyboardSensor.
     * @param ar    a AnimationRunner.
     * @param gui   a GUI.
     * @param score a Counter of score.
     */
    public GameLevel(LevelInformation level, KeyboardSensor ks, AnimationRunner ar, GUI gui, Counter score) {
        //initialize the class of SpriteCollection
        this.sprites = new SpriteCollection();
        //initialize the class of GameEnvironment
        this.environment = new GameEnvironment();
        //create new gui
        this.gui = gui;
        //counter the number of the block in the game
        this.blocksCounter = new Counter();
        //counter the number of the balls in the game
        this.ballsCounter = new Counter();
        //counter the score in the game
        this.score = score;
        this.runner = ar;
        this.keyboard = ks;
        this.level = level;
    }

    /**
     * Add the given collidable to the environment.
     *
     * @param c a collidable objects.
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * Add the given sprite to the sprites.
     *
     * @param s sprite.
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * Initialize a new game: create the Blocks, Balls and Paddle and add them to the game.
     */
    public void initialize() {
        BallRemover ballRemover = new BallRemover(this, this.ballsCounter);
        BlockRemover blockRemover = new BlockRemover(this, this.blocksCounter);
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(this.score);
        //create Background
        this.addSprite(level.getBackground());
        //create balls
        List<Velocity> velocityList = level.initialBallVelocities();
        for (int i = 0; i < level.numberOfBalls(); i++) {
            Point centerPoint = new Point(WIDTH / 2, 550);
            Ball ball = new Ball(centerPoint, RADIUS, Color.white, this.environment);
            //update the velocity
            ball.setVelocity(velocityList.get(i));
            //add the balls to the game
            ball.addToGame(this);
            this.ballsCounter.increase(1);
        }
        //create paddle
        double xPaddle = WIDTH / 2 - level.paddleWidth() / 2;
        double yPaddle = HEIGHT - SIZE_BORDER - HEIGHT_PADDLE;
        Rectangle rectangle = new Rectangle(xPaddle, yPaddle, level.paddleWidth(), HEIGHT_PADDLE);
        Paddle paddle = new Paddle(this.keyboard, rectangle, Color.YELLOW, level.paddleSpeed(), SIZE_BORDER,
                WIDTH - SIZE_BORDER);
        //add paddle to the game
        paddle.addToGame(this);
        //create border block
        Block leftBlock = new Block(new Rectangle(new Point(0, 0), SIZE_BORDER, HEIGHT), Color.GRAY);
        Block rightBlock = new Block(new Rectangle(new Point(WIDTH - SIZE_BORDER, 0), SIZE_BORDER, HEIGHT),
                Color.GRAY);
        Block upperBlock = new Block(new Rectangle(new Point(0, 0), WIDTH, SIZE_SCORE_INDICATOR
                + SIZE_BORDER), Color.GRAY);
        Block deathRegion = new Block(new Rectangle(new Point(0, HEIGHT), WIDTH, SIZE_BORDER),
                Color.GRAY);
        //create array of border block
        Block[] limitBlocks = new Block[]{leftBlock, rightBlock, upperBlock, deathRegion};
        //add border block to the game
        for (Block block : limitBlocks) {
            block.addToGame(this);
        }
        //ballRemover will be notified whenever a ball hits the death-region
        deathRegion.addHitListener(ballRemover);
        //create blocks
        for (Block block : level.blocks()) {
            //add the ball to the game
            block.addToGame(this);
            block.addHitListener(blockRemover);
            //increase the blocksCounter
            this.blocksCounter.increase(1);
            block.addHitListener(scoreTrackingListener);
        }
        //create score Indicator
        Rectangle scoreRect = new Rectangle(new Point(0, 0), WIDTH, SIZE_SCORE_INDICATOR);
        ScoreIndicator scoreIndicator = new ScoreIndicator(this.score, scoreRect, this.level.levelName());
        scoreIndicator.addToGame(this);
    }

    /**
     * The method remove collidable from the game.
     *
     * @param c a collidable.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * The method remove sprite from the game.
     *
     * @param s a sprite.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * Return if the animation should stop.
     *
     * @return the boolean member running.
     */
    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * Do one frame of the animation.
     *
     * @param d DrawSurface.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        //if we don't have blocks on the screen
        if (this.blocksCounter.getValue() == 0) {
            //increase the score
            this.score.increase(SCORE_OF_WINNING);
            //the game stop and close the gui
            this.running = false;
        }
        //if we don't have balls on the screen
        if (this.ballsCounter.getValue() == 0) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY,
                    new GameOverScreen(this.score)));
            gui.close();
            this.running = false;
        }
        //option to pause the game when pressing the p key.
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY, new PauseScreen()));
        }
    }

    /**
     * Run the game, start the animation loop.
     */
    public void run() {
        // countdown before turn starts. use our runner to run the current animation -- which is one turn of the game.
        this.runner.run(new CountdownAnimation(2, 3, this.sprites));
        this.running = true;
        // use our runner to run the current animation -- which is one turn of the game.
        this.runner.run(this);
    }

    /**
     * Return the BallsCounter.
     *
     * @return the BallsCounter.
     */
    public Counter getBallsCounter() {
        return this.ballsCounter;
    }

    /**
     * Return the BlocksCounter.
     *
     * @return the BlocksCounter.
     */
    public Counter getBlocksCounter() {
        return this.blocksCounter;
    }
}