/*
 Copyright (c) Petr Panteleyev. All rights reserved.
 Licensed under the BSD license. See LICENSE file in the project root for full license information.
 */
package org.panteleyev.fx;

import javafx.scene.control.ComboBox;
import org.panteleyev.fx.combobox.ComboBoxImpl;

public interface FxUtils {
    /**
     * Clears selection and sets list value to <code>null</code>. For {@link ComboBox} instances created
     * with methods from this interface default value is rendered in the button cell, see
     * {@link ComboBox#getButtonCell()}.
     *
     * @param comboBoxes {@link ComboBox} instances
     */
    static void clearValueAndSelection(ComboBox<?>... comboBoxes) {
        for (var cb : comboBoxes) {
            cb.setValue(null);
            cb.getSelectionModel().select(null);

            if (cb instanceof ComboBoxImpl<?> comboBox) {
                comboBox.renderDefaultValue();
            }
        }
    }
}
