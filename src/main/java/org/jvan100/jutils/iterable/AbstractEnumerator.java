package org.jvan100.jutils.iterable;

import java.util.Iterator;
import java.util.function.Consumer;

public class AbstractEnumerator<T> implements Iterable<AbstractEnumerator.EnumeratedItem<T>> {

    private final Iterator<T> iterator;
    private final int start;
    private final int inc;

    protected AbstractEnumerator(Iterator<T> iterator, int start, int inc) {
        this.iterator = iterator;
        this.start = start;
        this.inc = inc;
    }

    @Override
    public Iterator<EnumeratedItem<T>> iterator() {
        return new Iterator<>() {
            int index = start;

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

}
