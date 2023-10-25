package org.example.commons.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class CsvUtils {

    private CsvUtils() {
        throw new IllegalStateException("It is a util class");
    }

    public static String convertToCSV(final Map<String, Map<String, String>> data) {
        if (data == null) {
            return "key"; // or any other default header you'd prefer
        }

        Set<String> subKeys = data.values().stream()
                .filter(Objects::nonNull)
                .flatMap(m -> m.keySet().stream())
                .collect(Collectors.toSet());

        String header = "key," + String.join(",", subKeys);
        String body = data.entrySet().stream()
                .filter(entry -> entry.getValue() != null)
                .map(entry -> {
                    String primary = entry.getKey();
                    Map<String, String> innerMap = entry.getValue();
                    return primary + "," + subKeys.stream()
                            .map(subKey -> innerMap.getOrDefault(subKey, ""))
                            .collect(Collectors.joining(","));
                })
                .collect(Collectors.joining("\n"));

        return header + "\n" + body;
    }

    public static Map<String, Map<String, String>> parseCSV(final String csv) {
        if (csv == null || csv.trim().isEmpty()) {
            return Collections.emptyMap();
        }

        String[] lines = csv.split("\n");
        if (lines.length < 2) {  // At least one header and one data row
            return Collections.emptyMap();
        }

        String[] headers = lines[0].split(",");
        if (headers.length < 2) {
            return Collections.emptyMap();
        }

        List<String> subKeys = Arrays.asList(headers).subList(1, headers.length);

        return IntStream.range(1, lines.length)
                .mapToObj(i -> lines[i].split(","))
                .filter(values -> values.length == headers.length)
                .collect(Collectors.toMap(
                        values -> values[0],
                        values -> IntStream.range(1, values.length)
                                .boxed()
                                .collect(Collectors.toMap(subKeys::get, idx -> values[idx]))
                ));
    }
}
