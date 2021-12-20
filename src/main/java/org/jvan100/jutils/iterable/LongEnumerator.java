package org.jvan100.jutils.iterable;

import java.util.PrimitiveIterator;
import java.util.stream.LongStream;

public class LongEnumerator extends AbstractEnumerator<Long> {

    private LongEnumerator(PrimitiveIterator.OfLong iterator, int start, int inc) {
        super(iterator, start, inc);
    }

    public static LongEnumerator enumerate(long[] arr, int start, int inc) {
        return new LongEnumerator(LongStream.of(arr).iterator(), start, inc);
    }

    public static LongEnumerator enumerate(long[] arr, int start) {
        return enumerate(arr, start, 1);
    }

    public static LongEnumerator enumerate(long[] arr) {
        return enumerate(arr, 0, 1);
    }

}
