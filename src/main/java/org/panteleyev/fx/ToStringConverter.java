/*
 Copyright © 2020 Petr Panteleyev <petr@panteleyev.org>
 SPDX-License-Identifier: BSD-2-Clause
 */
package org.panteleyev.fx;

import javafx.util.StringConverter;

public abstract class ToStringConverter<T> extends StringConverter<T> {
    @Override
    public T fromString(String string) {
        return null;
    }
}
