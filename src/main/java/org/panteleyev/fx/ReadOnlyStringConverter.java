package org.panteleyev.fx;

/*
 Copyright (c) Petr Panteleyev. All rights reserved.
 Licensed under the BSD license. See LICENSE file in the project root for full license information.
 */

import javafx.util.StringConverter;

public abstract class ReadOnlyStringConverter<T> extends StringConverter<T> {
    @Override
    public T fromString(String s) {
        throw new UnsupportedOperationException("Read-only");
    }
}
