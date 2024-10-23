import java.io.Serializable;

public interface VectorClock_Interface extends Serializable {
    void increment();
    int[] getClock();
    void update(int[] otherClock);
}
