package util;

public class SequenceGenerator {

    private int sequence = 0;

    public synchronized int generate()
    {
        return ++sequence;
    }
}
