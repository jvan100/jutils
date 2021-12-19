package org.jvan100.jutils;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Consumer;

public class IterUtils {

    public static class EnumeratedItem<T> {

        private final int index;
        private final T item;

        private EnumeratedItem(int index, T item) {
            this.index = index;
            this.item = item;
        }

        public int index() {
            return index;
        }

        public T item() {
            return item;
        }

    }

    public static class Enumerator<T> implements Iterable<EnumeratedItem<T>> {

        private final Iterator<T> iterator;
        private final int start;
        private final int inc;

        public Enumerator(Iterator<T> iterator, int start, int inc) {
            this.iterator = iterator;
            this.start = start;
            this.inc = inc;
        }

        @Override
        public Iterator<EnumeratedItem<T>> iterator() {
            return new Iterator<>() {

                private int index = start;

                @Override
                public boolean hasNext() {
                    return iterator.hasNext();
                }

                @Override
                public EnumeratedItem<T> next() {
                    final EnumeratedItem<T> enumItem = new EnumeratedItem<>(index, iterator.next());

                    index += inc;

                    return enumItem;
                }

            };
        }

        @Override
        public void forEach(Consumer<? super EnumeratedItem<T>> action) {
            int index = start;

            while (iterator.hasNext()) {
                final EnumeratedItem<T> enumItem = new EnumeratedItem<>(index, iterator.next());

                index += inc;

                action.accept(enumItem);
            }
        }

    }

    public static class IntEnumerator {

        public IntEnumerator(int[] arr, int start, int inc) {

        }

    }

    public static <T> Iterable<EnumeratedItem<T>> enumerate(Iterable<T> iterable, int start, int inc) {
        return new Enumerator<>(iterable.iterator(), start, inc);
    }

    public static <T> Iterable<EnumeratedItem<T>> enumerate(Iterable<T> iterable, int start) {
        return enumerate(iterable, start, 1);
    }

    public static <T> Iterable<EnumeratedItem<T>> enumerate(Iterable<T> iterable) {
        return enumerate(iterable, 0, 1);
    }

    public static <T> Iterable<EnumeratedItem<T>> enumerate(T[] arr, int start, int inc) {
        return new Enumerator<>(Arrays.stream(arr).iterator(), start, inc);
    }

    public static <T> Iterable<EnumeratedItem<T>> enumerate(T[] arr, int start) {
        return enumerate(arr, start, 1);
    }

    public static <T> Iterable<EnumeratedItem<T>> enumerate(T[] arr) {
        return enumerate(arr, 0, 1);
    }

}
