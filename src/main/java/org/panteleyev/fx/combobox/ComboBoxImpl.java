/*
 Copyright © 2020 Petr Panteleyev <petr@panteleyev.org>
 SPDX-License-Identifier: BSD-2-Clause
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

