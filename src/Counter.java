//208943555

/**
 * Counter is a simple class that is used for counting things.
 */
public class Counter {
    private int count;

    /**
     * Constructor that creates a Counter.
     */
    public Counter() {
        this.count = 0;
    }

    /**
     * Add number to current count.
     *
     * @param number the number we add to the current count.
     */
    public void increase(int number) {
        this.count = this.count + number;

    }

    /**
     * Subtract number from current count.
     *
     * @param number the number we subtract from the current count.
     */
    public void decrease(int number) {
        this.count = this.count - number;
    }

    /**
     * Get current count.
     *
     * @return the value of the current count.
     */
    public int getValue() {
        return this.count;
    }
}