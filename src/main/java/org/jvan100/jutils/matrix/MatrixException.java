package org.jvan100.jutils.matrix;

public class MatrixException extends RuntimeException {

    public MatrixException(String errorMessage) {
        super(errorMessage);
    }

    public MatrixException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }

}
