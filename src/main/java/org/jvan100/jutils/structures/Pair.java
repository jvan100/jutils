package org.jvan100.jutils.structures;

public abstract class Pair<U, V> {

    protected U first;
    protected V second;

    protected Pair(U first, V second) {
        this.first = first;
        this.second = second;
    }

    public U getFirst() {
        return first;
    }

    public V getSecond() {
        return second;
    }

    @Override
    public String toString() {
        return String.format("{%s, %s}", first.toString(), second.toString());
    }
}
