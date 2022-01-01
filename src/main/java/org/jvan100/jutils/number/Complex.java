package org.jvan100.jutils.number;

import java.util.Objects;

public class Complex {

    private double re;
    private double im;

    public Complex(double re, double im) {
        this.re = re;
        this.im = im;
    }

    public Complex() {
        this(0, 0);
    }

    public double getRe() {
        return re;
    }

    public void setRe(double re) {
        this.re = re;
    }

    public double getIm() {
        return im;
    }

    public void setIm(double im) {
        this.im = im;
    }

    public Complex copy() {
        return new Complex(re, im);
    }

    public Complex conjugate() {
        return conjugate(this);
    }

    public double mod() {
        return mod(this);
    }

    public Complex plus(double s) {
        return plus(this, s);
    }

    public Complex plusEquals(double s) {
        return plusEquals(this, s);
    }

    public Complex plus(Complex b) {
        return plus(this, b);
    }

    public Complex plusEquals(Complex b) {
        return plusEquals(this, b);
    }

    public Complex minus(double s) {
        return minus(this, s);
    }

    public Complex minusEquals(double s) {
        return minusEquals(this, s);
    }

    public Complex minus(Complex b) {
        return minus(this, b);
    }

    public Complex minusEquals(Complex b) {
        return minusEquals(this, b);
    }

    public Complex mult(double s) {
        return mult(this, s);
    }

    public Complex multEquals(double s) {
        return multEquals(this, s);
    }

    public Complex mult(Complex b) {
        return mult(this, b);
    }

    public Complex multEquals(Complex b) {
        return multEquals(this, b);
    }

    public Complex divide(double s) {
        return divide(this, s);
    }

    public Complex divideEquals(double s) {
        return divideEquals(this, s);
    }

    public Complex divide(Complex b) {
        return divide(this, b);
    }

    public Complex divideEquals(Complex b) {
        return divideEquals(this, b);
    }

    public static Complex conjugate(Complex a) {
        return new Complex(a.getRe(), -a.getIm());
    }

    public static double mod(Complex a) {
        return Math.sqrt(Math.pow(a.re, 2) + Math.pow(a.im, 2));
    }

    public static Complex pow(Complex a, int pow) {
        final Complex b = a.copy();

        for (int i = 1; i < pow; i++)
            b.multEquals(a);

        return b;
    }

    public static Complex exp(Complex a) {
        final double im = a.getIm();
        final double r = Math.exp(a.getRe());
        return new Complex(r * Math.cos(im), r * Math.sin(im));
    }

    public static Complex exp(Complex a, int base) {
        final double re = a.getRe();
        final double im = a.getIm();
        final double r = Math.pow(base, re);
        final double t = im * Math.log(base);
        return new Complex(r * Math.cos(t), r * Math.sin(t));
    }

    public static Complex plus(Complex a, double s) {
        return new Complex(a.getRe() + s, a.getIm());
    }

    public static Complex plusEquals(Complex a, double s) {
        a.setRe(a.getRe() + s);
        return a;
    }

    public static Complex plus(Complex a, Complex b) {
        return new Complex(a.getRe() + b.getRe(), a.getIm() + b.getIm());
    }

    public static Complex plusEquals(Complex a, Complex b) {
        a.setRe(a.getRe() + b.getRe());
        a.setIm(a.getIm() + b.getIm());
        return a;
    }

    public static Complex minus(Complex a, double s) {
        return new Complex(a.getRe() - s, a.getIm());
    }

    public static Complex minusEquals(Complex a, double s) {
        a.setRe(a.getRe() - s);
        return a;
    }

    public static Complex minus(Complex a, Complex b) {
        return new Complex(a.getRe() - b.getRe(), a.getIm() - b.getIm());
    }

    public static Complex minusEquals(Complex a, Complex b) {
        a.setRe(a.getRe() - b.getRe());
        a.setIm(a.getIm() - b.getIm());
        return a;
    }

    public static Complex mult(Complex a, double s) {
        return new Complex(a.getRe() * s, a.getIm() * s);
    }

    public static Complex multEquals(Complex a, double s) {
        a.setRe(a.getRe() * s);
        a.setIm(a.getIm() * s);
        return a;
    }

    public static Complex mult(Complex a, Complex b) {
        return new Complex(a.getRe() * b.getRe() - a.getIm() * b.getIm(), a.getRe() * b.getIm() + a.getIm() * b.getRe());
    }

    public static Complex multEquals(Complex a, Complex b) {
        a.setRe(a.getRe() * b.getRe() - a.getIm() * b.getIm());
        a.setIm(a.getRe() * b.getIm() + a.getIm() * b.getRe());
        return a;
    }

    public static Complex divide(Complex a, double s) {
        return new Complex(a.getRe() / s, a.getIm() / s);
    }

    public static Complex divideEquals(Complex a, double s) {
        a.setRe(a.getRe() / s);
        a.setIm(a.getIm() / s);
        return a;
    }

    public static Complex divide(Complex a, Complex b) {
        final double d = Math.pow(b.getRe(), 2) + Math.pow(b.getIm(), 2);
        return new Complex((a.getRe() * b.getRe() + a.getIm() * b.getIm()) / d, (a.getRe() * b.getIm() - a.getIm() * b.getRe()) / d);
    }

    public static Complex divideEquals(Complex a, Complex b) {
        final double d = Math.pow(b.getRe(), 2) + Math.pow(b.getIm(), 2);
        a.setRe((a.getRe() * b.getRe() + a.getIm() * b.getIm()) / d);
        a.setIm((a.getRe() * b.getIm() - a.getIm() * b.getRe()) / d);
        return a;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (!(o instanceof Complex))
            return false;

        final Complex b = (Complex) o;
        return re == b.getRe() && im == b.getIm();
    }

    @Override
    public int hashCode() {
        return Objects.hash(re, im);
    }

    @Override
    public String toString() {
        final char o = (im < 0) ? '-' : '+';
        return String.format("(%f %c %fi)", re, o, im);
    }

}
