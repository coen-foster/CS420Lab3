import java.util.Arrays;

public class VectorClock implements VectorClock_Interface {
    private static final long serialVersionUID = 1L;
	private int[] clock;
    private int processId;

    public VectorClock(int processId) {
        this.processId = processId;
        this.clock = new int[3];
    }

    @Override
    public void increment() {
        clock[processId]++;
    }

    @Override
    public int[] getClock() {
        return clock.clone();
    }

    @Override
    public void update(int[] otherClock) {
        for (int i = 0; i < clock.length; i++) {
            clock[i] = Math.max(clock[i], otherClock[i]);
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(clock);
    }
}
