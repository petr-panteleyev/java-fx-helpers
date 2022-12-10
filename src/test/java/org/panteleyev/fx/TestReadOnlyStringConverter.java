/*
 Copyright © 2020-2022 Petr Panteleyev <petr@panteleyev.org>
 SPDX-License-Identifier: BSD-2-Clause
 */
package org.panteleyev.fx;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestReadOnlyStringConverter {

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
