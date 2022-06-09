/*
 Copyright © 2020-2021 Petr Panteleyev <petr@panteleyev.org>
 SPDX-License-Identifier: BSD-2-Clause
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
 * This class provides convenience methods to create buttons.
 */
public final class ButtonFactory {
    public static ButtonType buttonType(String text, ButtonBar.ButtonData position) {
        return new ButtonType(text, position);
    }

    /**
     * Creates new push button.
     *
     * @param text button text
     * @return button
     */
    public static Button button(String text) {
        return new Button(text);
    }

    /**
     * Creates new push button.
     *
     * @param text   button text
     * @param action button action
     * @return button
     */
    public static Button button(String text, EventHandler<ActionEvent> action) {
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
    public static RadioButton radioButton(String text, ToggleGroup group) {
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
    public static RadioButton radioButton(String text, ToggleGroup group, boolean selected) {
        var button = new RadioButton(text);
        button.setToggleGroup(group);
        button.setSelected(selected);
        return button;
    }
}
