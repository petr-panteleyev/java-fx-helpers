/*
 Copyright (c) Petr Panteleyev. All rights reserved.
 Licensed under the BSD license. See LICENSE file in the project root for full license information.
 */
package org.panteleyev.fx;

import javafx.util.StringConverter;

public abstract class ToStringConverter<T> extends StringConverter<T> {
    @Override
    public T fromString(String string) {
        return null;
    }
}
