package org.jvan100.jutils.iterable;

import java.util.PrimitiveIterator;
import java.util.stream.DoubleStream;

public class DoubleEnumerator extends AbstractEnumerator<Double> {

    private DoubleEnumerator(PrimitiveIterator.OfDouble iterator, int start, int inc) {
        super(iterator, start, inc);
    }

    public static DoubleEnumerator enumerate(double[] arr, int start, int inc) {
        return new DoubleEnumerator(DoubleStream.of(arr).iterator(), start, inc);
    }

    public static DoubleEnumerator enumerate(double[] arr, int start) {
        return enumerate(arr, start, 1);
    }

    public static DoubleEnumerator enumerate(double[] arr) {
        return enumerate(arr, 0, 1);
    }

}
