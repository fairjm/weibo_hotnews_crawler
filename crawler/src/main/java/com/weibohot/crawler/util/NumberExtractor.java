package com.weibohot.crawler.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class NumberExtractor {

    private static final Pattern pattern = Pattern.compile("\\d+(\\.\\d+)?");

    public static long extract(final String snum) {
        final String trimdNum = snum.trim();
        final Matcher m = NumberExtractor.pattern.matcher(trimdNum);
        double num = 0;
        if (m.find()) {
            num = Double.parseDouble(m.group(0));
        }
        if (snum.contains("万")) {
            num *= 10000;
        } else if (snum.contains("亿")) {
            num *= 100000000;
        }
        return (long) num;
    }

    public static void main(final String[] args) {
        System.out.println(NumberExtractor.extract("1.1万"));
        System.out.println(NumberExtractor.extract("10000.1万"));
        System.out.println(NumberExtractor.extract("10.1亿"));
        System.out.println((long)Double.POSITIVE_INFINITY);
    }
}
