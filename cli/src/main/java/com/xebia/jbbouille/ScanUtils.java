package com.xebia.jbbouille;

import java.math.BigDecimal;
import java.util.Scanner;

public class ScanUtils {
    public static Scanner sc = new Scanner(System.in);

    public static BigDecimal wantBigDecimal(String text) {
        System.out.println(text);
        boolean hasNextBigDecimal = sc.hasNextBigDecimal();
        while (!hasNextBigDecimal) {
            sc.next();
            System.out.println(text);
            hasNextBigDecimal = sc.hasNextBigDecimal();
        }
        return sc.nextBigDecimal();
    }

    public static int wantInteger(String text) {
        System.out.println(text);
        boolean hasNextInteger = sc.hasNextInt();
        while (!hasNextInteger) {
            sc.next();
            System.out.println(text);
            hasNextInteger = sc.hasNextInt();
        }
        return sc.nextInt();
    }
}
