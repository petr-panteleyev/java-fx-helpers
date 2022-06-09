/*
 Copyright © 2020-2021 Petr Panteleyev <petr@panteleyev.org>
 SPDX-License-Identifier: BSD-2-Clause
 */
package org.panteleyev.fx;

import org.testng.annotations.Test;

@Test
public class TestReadOnlyStringConverter {

    @Test(expectedExceptions = UnsupportedOperationException.class)
    public void testException() {
        new ReadOnlyStringConverter<>() {
            @Override
            public String toString(Object o) {
                return null;
            }
        }.fromString("abc");
    }
}
