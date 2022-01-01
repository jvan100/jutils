package org.jvan100.jutils.array;

import java.util.Comparator;

public class ArrayUtils {

    public static <T extends Comparable<? super T>> void mergeSort(T[] array) {
        mergeSort(array, 0, array.length, false);
    }

    public static <T extends Comparable<? super T>> void mergeSort(T[] array, int start, int end) {
        mergeSort(array, start, end, false);
    }

    public static <T extends Comparable<? super T>> void mergeSort(T[] array, boolean desc) {
        mergeSort(array, 0, array.length, desc);
    }

    public static <T extends Comparable<? super T>> void mergeSort(T[] array, int start, int end, boolean desc) {
        mergeSort(array, start, end, desc, Comparator.naturalOrder());
    }

    public static <T> void mergeSort(T[] array, Comparator<? super T> comparator) {
        mergeSort(array, 0, array.length, false, comparator);
    }

    public static <T> void mergeSort(T[] array, int start, int end, Comparator<? super T> comparator) {
        mergeSort(array, start, end, false, comparator);
    }

    public static <T> void mergeSort(T[] array, boolean desc, Comparator<? super T> comparator) {
        mergeSort(array, 0, array.length, desc, comparator);
    }

    @SuppressWarnings("unchecked")
    public static <T> void mergeSort(T[] array, int start, int end, boolean desc, Comparator<? super T> comparator) {
        T[] read = array;
        T[] write = (T[]) new Object[array.length];

        final int len = end - start;

        int writeIndex;
        int leftIndex;
        int rightIndex;
        int leftEnd;
        int rightEnd;

        boolean copy = false;

        T[] oldRead = read;

        // Double the size of the sub-arrays each time
        for (int size = 1; size < len; size *= 2) {
            writeIndex = start;

            do {
                // Set utility variables
                leftIndex = writeIndex;
                rightIndex = leftIndex + size;

                leftEnd = Math.min(rightIndex, end);
                rightEnd = Math.min(leftEnd + size, end);

                // Merge elements from 2 sub-arrays into one twice
                // the size
                while (leftIndex < leftEnd && rightIndex < rightEnd)
                    write[writeIndex++] = (comparator.compare(read[leftIndex], read[rightIndex]) > 0) ^ desc ? read[rightIndex++] : read[leftIndex++];

                // Write any remaining elements from the left sub-array
                while (leftIndex < leftEnd)
                    write[writeIndex++] = read[leftIndex++];

                // Write any remaining elements from the right sub-array
                while (rightIndex < rightEnd)
                    write[writeIndex++] = read[rightIndex++];

            } while (writeIndex < end);

            // Swap the read and write arrays
            read = write;
            write = oldRead;
            oldRead = read;

            copy = !copy;
        }

        // If the read array is not the original array,
        // copy the contents of the read array to the original array
        if (copy)
            System.arraycopy(read, start, array, start, len);
    }

}
