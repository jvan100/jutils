package org.jvan100.jutils.iterable;

import java.util.Arrays;
import java.util.Iterator;

public class Enumerator<T> extends AbstractEnumerator<T> {

    private Enumerator(Iterator<T> iterator, int start, int inc) {
        super(iterator, start, inc);
    }

    public static <T> Enumerator<T> enumerate(Iterable<T> iterable, int start, int inc) {
        return new Enumerator<>(iterable.iterator(), start, inc);
    }

    public static <T> Enumerator<T> enumerate(Iterable<T> iterable, int start) {
        return enumerate(iterable, start, 1);
    }

    public static <T> Enumerator<T> enumerate(Iterable<T> iterable) {
        return enumerate(iterable, 0, 1);
    }

    public static <T> Enumerator<T> enumerate(T[] arr, int start, int inc) {
        return new Enumerator<>(Arrays.stream(arr).iterator(), start, inc);
    }

    public static <T> Enumerator<T> enumerate(T[] arr, int start) {
        return enumerate(arr, start, 1);
    }

    public static <T> Enumerator<T> enumerate(T[] arr) {
        return enumerate(arr, 0, 1);
    }

}
