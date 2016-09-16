package com.xebia.jbbouille;

import java.io.InputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.Scanner;

public class ScanUtils {
    private Scanner sc;
    private PrintStream out;

    public ScanUtils(PrintStream out, InputStream in) {
        this.sc = new Scanner(in);
        this.out = out;
    }

    public BigDecimal wantBigDecimal(String text) {
        out.println(text);
        boolean hasNextBigDecimal = sc.hasNextBigDecimal();
        while (!hasNextBigDecimal) {
            sc.next();
            out.println(text);
            hasNextBigDecimal = sc.hasNextBigDecimal();
        }
        return sc.nextBigDecimal();
    }

    public int wantInteger(String text) {
        out.println(text);
        boolean hasNextInteger = sc.hasNextInt();
        while (!hasNextInteger) {
            sc.next();
            out.println(text);
            hasNextInteger = sc.hasNextInt();
        }
        return sc.nextInt();
    }
}
