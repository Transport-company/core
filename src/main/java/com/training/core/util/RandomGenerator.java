package com.training.core.util;

import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * Generates random strings
 */
@Component
public class RandomGenerator {
    private final Random random = new Random();
    private final String capitalLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final String digits = "0123456789";

    /**
     * Generates a random string of capital letters.
     *
     * @param length of a generating string
     * @return a generated string
     */
    public String generateCapitalLetterLine(int length) {
        return generateRandomString(length, capitalLetters);
    }

    /**
     * Generates a random straing of digits.
     *
     * @param length of a generating string
     * @return a generated string
     */
    public String generateDigitLine(int length) {
        return generateRandomString(length, digits);
    }

    private String generateRandomString(int length, String template) {
        StringBuilder builder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            builder.append(template.charAt(random.nextInt(template.length())));
        }
        return builder.toString();
    }
}
