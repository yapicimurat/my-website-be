package com.yapicimurat.util;

import java.util.*;
import java.util.stream.Collectors;

public final class ConverterUtil {
    private ConverterUtil() {
        throw new AssertionError();
    }

    public static List<UUID> stringListToUUIDList(final List<String> stringIdList) {
        if(Objects.isNull(stringIdList)) return Collections.emptyList();
        return stringIdList.stream().map(UUID::fromString).collect(Collectors.toList());
    }

    public static Set<UUID> stringSetToUUIDSet(final Set<String> stringIdList) {
        if(Objects.isNull(stringIdList)) return Collections.emptySet();
        return stringIdList.stream().map(UUID::fromString).collect(Collectors.toSet());
    }

    public static List<String> uuidListToStringList(final List<UUID> uuidList) {
        if(Objects.isNull(uuidList)) return Collections.emptyList();
        return uuidList.stream().map(UUID::toString).collect(Collectors.toList());
    }
}
