/*
 Copyright (c) Petr Panteleyev. All rights reserved.
 Licensed under the BSD license. See LICENSE file in the project root for full license information.
 */
package org.panteleyev.fx;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

/**
 * This interface provides convenience methods to create buttons.
 */
public interface ButtonFactory {
    static ButtonType buttonType(String text, ButtonBar.ButtonData position) {
        return new ButtonType(text, position);
    }

    /**
     * Creates new push button.
     *
     * @param text button text
     * @return button
     */
    static Button button(String text) {
        return new Button(text);
    }

    /**
     * Creates new push button.
     *
     * @param text   button text
     * @param action button action
     * @return button
     */
    static Button button(String text, EventHandler<ActionEvent> action) {
        var button = new Button(text);
        button.setOnAction(action);
        return button;
    }

    /**
     * Creates new radio button.
     *
     * @param text  button text
     * @param group toggle group
     * @return button
     */
    static RadioButton radioButton(String text, ToggleGroup group) {
        var button = new RadioButton(text);
        button.setToggleGroup(group);
        return button;
    }

    /**
     * Creates new radio button.
     *
     * @param text     button text
     * @param group    toggle group
     * @param selected initial selected state
     * @return button
     */
    static RadioButton radioButton(String text, ToggleGroup group, boolean selected) {
        var button = new RadioButton(text);
        button.setToggleGroup(group);
        button.setSelected(selected);
        return button;
    }
}
