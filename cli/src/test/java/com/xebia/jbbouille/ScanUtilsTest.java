package com.xebia.jbbouille;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class ScanUtilsTest {
    private final ByteArrayOutputStream outTest = new ByteArrayOutputStream();
    private final PrintStream out = new PrintStream(outTest);

    @Test
    public void should_loop_until_big_decimal() throws Exception {
        // Given
        InputStream in = new ByteArrayInputStream("bob\nbob\n1,58".getBytes());
        ScanUtils scanUtils = new ScanUtils(out, in);

        // When
        BigDecimal wantBigDecimal = scanUtils.wantBigDecimal("Want BIG DECIMAL !");

        // Then
        assertThat(wantBigDecimal).isEqualTo(new BigDecimal("1.58"));
    }

    @Test
    public void should_loop_until_integer() throws Exception {
        // Given
        InputStream in = new ByteArrayInputStream("bob\nbob\n2".getBytes());
        ScanUtils scanUtils = new ScanUtils(out, in);

        // When
        BigDecimal wantBigDecimal = scanUtils.wantBigDecimal("Want BIG DECIMAL !");

        // Then
        assertThat(wantBigDecimal).isEqualTo(new BigDecimal("2"));
    }
}