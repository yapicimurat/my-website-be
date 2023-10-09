package com.yapicimurat.util;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

public final class ConverterUtil {
    private ConverterUtil() {
        throw new AssertionError();
    }

    public static List<UUID> stringListToUUIDList(List<String> stringIdList) {
        if(Objects.isNull(stringIdList)) return Collections.emptyList();
        return stringIdList.stream().map(UUID::fromString).collect(Collectors.toList());
    }

    public static List<String> uuidListToStringList(List<UUID> uuidList) {
        if(Objects.isNull(uuidList)) return Collections.emptyList();
        return uuidList.stream().map(UUID::toString).collect(Collectors.toList());
    }
}
