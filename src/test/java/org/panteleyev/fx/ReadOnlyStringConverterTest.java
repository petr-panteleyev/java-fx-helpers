// Copyright © 2020-2025 Petr Panteleyev
// SPDX-License-Identifier: BSD-2-Clause
package org.panteleyev.fx;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ReadOnlyStringConverterTest {

    @Test
    public void testException() {
        assertThrows(UnsupportedOperationException.class,
                () -> new ReadOnlyStringConverter<>() {
                    @Override
                    public String toString(Object o) {
                        return null;
                    }
                }.fromString("abc")
        );
    }
}
