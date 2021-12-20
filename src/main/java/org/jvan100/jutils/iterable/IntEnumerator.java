package org.jvan100.jutils.iterable;

import java.util.PrimitiveIterator;
import java.util.stream.IntStream;

public class IntEnumerator extends AbstractEnumerator<Integer> {

    private IntEnumerator(PrimitiveIterator.OfInt iterator, int start, int inc) {
        super(iterator, start, inc);
    }

    public static IntEnumerator enumerate(int[] arr, int start, int inc) {
        return new IntEnumerator(IntStream.of(arr).iterator(), start, inc);
    }

    public static IntEnumerator enumerate(int[] arr, int start) {
        return enumerate(arr, start, 1);
    }

    public static IntEnumerator enumerate(int[] arr) {
        return enumerate(arr, 0, 1);
    }

}
