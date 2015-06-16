package com.weibohot.server.util;

import java.util.concurrent.ThreadLocalRandom;

public final class ColorGenerator {

    public static String generatorHtmlColor() {
        return "#" + ColorGenerator.randomHexWithZeroFilled()
                + ColorGenerator.randomHexWithZeroFilled()
                + ColorGenerator.randomHexWithZeroFilled();
    }

    private static String randomHexWithZeroFilled() {
        final ThreadLocalRandom random = ThreadLocalRandom.current();
        final String v = Integer.toHexString(random.nextInt(256));
        return v.length() < 2 ? "0" + v : v;
    }

    public static void main(final String[] args) {
        System.out.println(ColorGenerator.generatorHtmlColor());
    }
}
