package com.yapicimurat.util;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public final class ConverterUtil {
    private ConverterUtil() {
        throw new AssertionError();
    }

    public static List<UUID> stringListToUUIDList(List<String> stringIdList) {
        return stringIdList.stream().map(UUID::fromString).collect(Collectors.toList());
    }

    public static List<String> uuidListToStringList(List<UUID> uuidList) {
        return uuidList.stream().map(UUID::toString).collect(Collectors.toList());
    }
}
