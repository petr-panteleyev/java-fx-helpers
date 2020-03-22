package org.panteleyev.fx;

/*
 Copyright (c) Petr Panteleyev. All rights reserved.
 Licensed under the BSD license. See LICENSE file in the project root for full license information.
 */

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import java.util.ResourceBundle;

/**
 * This interface provides convenience methods to create buttons.
 */
public interface ButtonFactory {
    static ButtonType newButtonType(String text, ButtonBar.ButtonData position) {
        return new ButtonType(text, position);
    }

    static ButtonType newButtonType(ResourceBundle rb, String key, ButtonBar.ButtonData position) {
        return newButtonType(rb.getString(key), position);
    }

    static Button newButton(ResourceBundle rb, String key) {
        return new Button(rb.getString(key));
    }

    /**
     * Creates new push button.
     *
     * @param text   button text
     * @param action button action
     * @return button
     */
    static Button newButton(String text, EventHandler<ActionEvent> action) {
        var button = new Button(text);
        button.setOnAction(action);
        return button;
    }

    /**
     * Creates new push button.
     *
     * @param rb     resource bundle
     * @param key    button text key
     * @param action button action
     * @return button
     */
    static Button newButton(ResourceBundle rb, String key, EventHandler<ActionEvent> action) {
        var button = new Button(rb.getString(key));
        button.setOnAction(action);
        return button;
    }

    /**
     * Creates new radio button.
     *
     * @param rb    resource bundle
     * @param key   button text key
     * @param group toggle group
     * @return button
     */
    static RadioButton newRadioButton(ResourceBundle rb, String key, ToggleGroup group) {
        var button = new RadioButton(rb.getString(key));
        button.setToggleGroup(group);
        return button;
    }

    /**
     * Creates new radio button.
     *
     * @param rb       resource bundle
     * @param key      button text key
     * @param group    toggle group
     * @param selected initial selected state
     * @return button
     */
    static RadioButton newRadioButton(ResourceBundle rb, String key, ToggleGroup group, boolean selected) {
        var button = newRadioButton(rb, key, group);
        button.setSelected(selected);
        return button;
    }
}
