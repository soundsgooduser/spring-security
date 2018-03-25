package com.example.spring.security.util;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

import static com.example.spring.security.common.CommonConstants.*;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Optional.ofNullable;

public final class CredentialsUtil {

    private CredentialsUtil() {
    }

    public static String[] extract(final String header) {
        return ofNullable(header)
                .filter(e -> e.startsWith(BASIC))
                .map(e -> e.substring(BASIC.length() + 1))
                .map(e -> {
                    try {
                        return e.getBytes(UTF_8.name());
                    } catch (final UnsupportedEncodingException ex) {
                        return new byte[0];
                    }
                })
                .filter(e -> e.length > 0)
                .map(Base64.getDecoder()::decode)
                .map(e -> {
                    try {
                        return new String(e, UTF_8.name());
                    } catch (final UnsupportedEncodingException e1) {
                        return EMPTY;
                    }
                })
                .filter(e -> e.contains(DELIMITER))
                .map(e -> e.split(DELIMITER))
                .orElse(new String[]{EMPTY, EMPTY});
    }
}
