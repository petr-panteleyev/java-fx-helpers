package org.panteleyev.fx;

/*
 Copyright (c) Petr Panteleyev. All rights reserved.
 Licensed under the BSD license. See LICENSE file in the project root for full license information.
 */

import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import org.controlsfx.validation.ValidationSupport;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Base class for dialogs.
 *
 * @param <R> The return type of the dialog, via the result property.
 */
public class BaseDialog<R> extends Dialog<R> {
    protected final ValidationSupport validation = new ValidationSupport();

    private static final URL CSS = BaseDialog.class.getResource("/org/panteleyev/fx/fx.css");

    /**
     * Creates dialog instance with no owner and default style sheet.
     */
    public BaseDialog() {
        getDialogPane().getStylesheets().add(CSS.toString());
    }

    /**
     * Creates dialog instance with specified owner.
     *
     * @param owner owner
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
     * @param cssUrl style sheet url
     */
    public BaseDialog(URL cssUrl) {
        this(null, cssUrl);
    }

    /**
     * Creates dialog instance with specified owner and style sheet.
     *
     * @param owner  owner
     * @param cssUrl stye sheet url
     */
    public BaseDialog(Controller owner, URL cssUrl) {
        this(owner);
        if (cssUrl != null) {
            getDialogPane().getStylesheets().add(cssUrl.toString());
        }
    }

    /**
     * Creates OK and Cancel buttons. Supplied resource bundle must define "button.Cancel"
     * resource for Cancel translation.
     *
     * @param rb resource bundle
     */
    protected void createDefaultButtons(ResourceBundle rb) {
        getDialogPane().getButtonTypes().addAll(
            ButtonType.OK,
            ButtonType.CANCEL);

        Button btOk = (Button) getDialogPane().lookupButton(ButtonType.OK);
        btOk.disableProperty().bind(validation.invalidProperty());

        Button btCancel = (Button) getDialogPane().lookupButton(ButtonType.CANCEL);
        btCancel.setText(rb == null ? "Cancel" : rb.getString("button.Cancel"));
    }

    /**
     * Returns button of the specified type.
     *
     * @param type button type
     * @return button if exists
     */
    protected Optional<Button> getButton(ButtonType type) {
        var node = getDialogPane().lookupButton(type);
        return node == null ? Optional.empty() : Optional.of((Button) node);
    }
}
