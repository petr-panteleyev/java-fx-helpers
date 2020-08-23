/*
 Copyright (c) Petr Panteleyev. All rights reserved.
 Licensed under the BSD license. See LICENSE file in the project root for full license information.
 */
package org.panteleyev.fx.combobox;

import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

/**
 * This class implements ComboBox that renders default string for null value.
 *
 * @param <T> type of the element
 */
class ComboBoxImpl<T> extends ComboBox<T> {
    private final String defaultString;

    public ComboBoxImpl(ObservableList<T> list, String defaultString) {
        super(list);
        this.defaultString = defaultString;
    }

    public String getDefaultString() {
        return defaultString;
    }

    public void renderDefaultValue() {
        getButtonCell().setText(getDefaultString());
    }
}

