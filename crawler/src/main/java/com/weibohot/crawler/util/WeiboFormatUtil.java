package com.weibohot.crawler.util;

public final class WeiboFormatUtil {

    public static String cleanFormat(final String in) {
        if (in == null) {
            return "";
        }
        return in.replaceAll("\\\\t", "\t").replaceAll("\\\\n", "\n").replaceAll("\\\\r", "\r")
                .replaceAll("\\\\/", "/").replaceAll("\\\\\"", "\"");
    }
}
