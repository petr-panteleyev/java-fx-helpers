// Copyright © 2020-2026 Petr Panteleyev
// SPDX-License-Identifier: BSD-2-Clause
package org.panteleyev.fx;

import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.input.KeyCodeCombination;
import javafx.stage.Window;

import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;

import static java.util.Objects.requireNonNull;

/**
 * Implements base class for dialogs.
 *
 * @param <R> The return type of the dialog, via the result property.
 */
public class BaseDialog<R> extends Dialog<R> {
    /**
     * Creates dialog instance with no owner and no style sheet.
     */
    public BaseDialog() {
    }

    /**
     * Creates dialog instance with specified owner.
     *
     * @param owner owner, ignored if {@code null}
     */
    public BaseDialog(Controller owner) {
        this();
        if (owner != null) {
            initOwner(owner.getStage());
        }
    }

    /**
     * Creates dialog instance with no owner and specified style sheet.
     *
     * @param cssUrl style sheet url, ignored if {@code null}
     */
    public BaseDialog(String cssUrl) {
        this(null, cssUrl);
    }

    /**
     * Creates dialog instance with specified owner and style sheet.
     *
     * @param owner  owner
     * @param cssUrl style sheet url, ignored if {@code null}
     */
    public BaseDialog(Controller owner, String cssUrl) {
        this(owner);
        if (cssUrl != null) {
            getDialogPane().getStylesheets().add(cssUrl);
        }
    }

    /**
     * Centers dialog on screen via {@link Window#centerOnScreen()}. May be useful on Linux.
     */
    public void centerOnScreen() {
        getDialogPane().getScene().getWindow().centerOnScreen();
    }

    /**
     * Creates OK and Cancel buttons. Supplied resource bundle must define "button.Cancel" resource for Cancel
     * translation.
     *
     * @param rb resource bundle, ignored if {@code null}
     */
    protected void createDefaultButtons(ResourceBundle rb) {
        createDefaultButtons(rb, null);
    }

    /**
     * Creates OK and Cancel buttons. Supplied resource bundle must define "button.Cancel" resource for Cancel
     * translation.
     *
     * @param rb                        resource bundle, ignored if {@code null}
     * @param validationInvalidProperty invalid property for validation support, ignored if {@code null}
     */
    protected void createDefaultButtons(ResourceBundle rb, ReadOnlyBooleanProperty validationInvalidProperty) {
        getDialogPane().getButtonTypes().addAll(
                ButtonType.OK,
                ButtonType.CANCEL);

        if (validationInvalidProperty != null) {
            var btOk = (Button) getDialogPane().lookupButton(ButtonType.OK);
            btOk.disableProperty().bind(validationInvalidProperty);
        }

        var btCancel = (Button) getDialogPane().lookupButton(ButtonType.CANCEL);
        btCancel.setText(rb == null ? "Cancel" : rb.getString("button.Cancel"));
    }

    /**
     * Returns button of the specified type.
     *
     * @param type button type
     * @return button if exists
     * @throws NullPointerException if {@code type} is {@code null}
     */
    protected Optional<Button> getButton(ButtonType type) {
        var node = getDialogPane().lookupButton(requireNonNull(type, "Button type must not be null"));
        return node == null ? Optional.empty() : Optional.of((Button) node);
    }


    /**
     * Adds accelerator for this dialog scene.
     *
     * @param accelerator key code combination
     * @param action      action
     * @throws NullPointerException if {@code accelerator} or {@code action} is {@code null}
     */
    public void addAccelerator(KeyCodeCombination accelerator, Runnable action) {
        getDialogPane().getScene().getAccelerators().put(
                requireNonNull(accelerator, "Accelerator must not be null"),
                requireNonNull(action, "Action must not be null")
        );
    }

    /**
     * Adds accelerators for this dialog scene.
     *
     * @param accelerators key code combinations with actions
     * @throws NullPointerException if {@code accelerators} is {@code null}
     */
    public void addAccelerators(Map<KeyCodeCombination, Runnable> accelerators) {
        getDialogPane().getScene().getAccelerators().putAll(
                requireNonNull(accelerators, "Accelerators must not be null")
        );
    }
}
