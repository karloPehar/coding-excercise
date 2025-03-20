package util;

public class SequenceGenerator {

    private int sequence = 0;

    public int generate()
    {
        return ++sequence;
    }
}
