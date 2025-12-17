/*
 Copyright © 2020-2021 Petr Panteleyev <petr@panteleyev.org>
 SPDX-License-Identifier: BSD-2-Clause
 */
package org.panteleyev.fx;

import javafx.util.StringConverter;

import java.util.Objects;
import java.util.function.Function;

/**
 * This class implements base for read-only {@link StringConverter}.
 * <p>
 * Implementation of {@link StringConverter#fromString(String)} throws {@link UnsupportedOperationException}.
 *
 * @param <T> source type
 */
public abstract class ReadOnlyStringConverter<T> extends StringConverter<T> {
    @Override
    public T fromString(String s) {
        throw new UnsupportedOperationException("Read-only");
    }

    /**
     * Creates {@link StringConverter} from converter function.
     *
     * @return converter instance
     * @throws NullPointerException if {@code converter} is {@code null}
     */
    public static <T> StringConverter<T> from(Function<T, String> converter) {
        Objects.requireNonNull(converter, "Converter function cannot be null");
        return new ReadOnlyStringConverter<>() {
            @Override
            public String toString(T object) {
                return converter.apply(object);
            }
        };
    }
}
