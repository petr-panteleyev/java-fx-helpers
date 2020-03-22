package org.panteleyev.fx;

/*
 Copyright (c) Petr Panteleyev. All rights reserved.
 Licensed under the BSD license. See LICENSE file in the project root for full license information.
 */

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
