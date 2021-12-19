package org.jvan100.jutils.structures;

public class MutablePair<U, V> extends Pair<U, V> {

    public MutablePair(U first, V second) {
        super(first, second);
    }

    public void setFirst(U first) {
        this.first = first;
    }

    public void setSecond(V second) {
        this.second = second;
    }

}
