package com.github.vladislavgoltjajev.personalcode.utility;

import java.util.Arrays;

public final class ThreadUtils {

    public static boolean stackContainsClass(Class<?> cls) {
        return Arrays.stream(Thread.currentThread().getStackTrace())
                .anyMatch(s -> s.getClassName().equals(cls.getName()));
    }

    private ThreadUtils() {
    }
}
