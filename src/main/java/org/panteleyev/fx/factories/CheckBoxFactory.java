// Copyright © 2025 Petr Panteleyev
// SPDX-License-Identifier: BSD-2-Clause
package org.panteleyev.fx.factories;

import javafx.scene.control.CheckBox;

import static org.panteleyev.functional.Scope.apply;

/**
 * Provides factory methods to create instances of {@link javafx.scene.control.CheckBox}.
 */
public final class CheckBoxFactory {

    /**
     * Creates a checkbox with an empty string for its label.
     *
     * @return checkbox
     */
    public static CheckBox checkBox() {
        return new CheckBox();
    }

    /**
     * Creates a checkbox with the specified text as its label.
     *
     * @param text a text string for checkbox label.
     * @return checkbox
     */
    public static CheckBox checkBox(String text) {
        return new CheckBox(text);
    }

    /**
     * Creates a checkbox with the specified text as its label and initial selected property.
     *
     * @param text     a text string for checkbox label.
     * @param selected an initial state of {@link CheckBox#selectedProperty()}
     * @return checkbox
     */
    public static CheckBox checkBox(String text, boolean selected) {
        return apply(checkBox(text), checkBox -> checkBox.setSelected(selected));
    }

    private CheckBoxFactory() {
    }
}
