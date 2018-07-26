package com.company;

import com.sun.tools.javac.util.ArrayUtils;

import java.util.Arrays;
import java.util.Collections;

/**
 * Created by BilalZahid on 29/03/2018.
 */
public class Sorter {

    private static int[] numbers = new int[Main.listOfStock.size()];

    public static int[] sort(int numbersToSort[]) {
        numbers = numbersToSort;
        number = numbers.length;
        helper = new int[number];
        mergesort(0, number - 1);
        return numbers;

    }

    private static int[] helper;

    private static int number;


    private static void mergesort(int low, int high) {
        if (low < high) {
            int middle = low + (high - low) / 2;
            mergesort(low, middle);
            mergesort(middle + 1, high);
            merge(low, middle, high);
        }
    }

    private static void merge(int low, int middle, int high) {

        for (int i = low; i <= high; i++) {
            helper[i] = numbers[i];
        }

        int i = low;
        int j = middle + 1;
        int k = low;

        while (i <= middle && j <= high) {
            if (helper[i] <= helper[j]) {
                numbers[k] = helper[i];
                i++;
            } else {
                numbers[k] = helper[j];
                j++;
            }
            k++;
        }
        while (i <= middle) {
            numbers[k] = helper[i];
            k++;
            i++;
        }

    }
}
