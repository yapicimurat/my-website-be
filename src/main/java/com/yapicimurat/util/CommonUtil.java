package com.yapicimurat.util;

public final class CommonUtil {
    private CommonUtil() {
        throw new AssertionError();
    }
    public static int clampDataPageNumber(Integer pageNumber) {
        if(pageNumber == null) return 0;
        return Math.max(pageNumber, 0);
    }
}
