// Copyright © 2020-2025 Petr Panteleyev
// SPDX-License-Identifier: BSD-2-Clause
package org.panteleyev.fx.factories;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import java.util.Objects;

/**
 * Provides factory methods to create buttons.
 */
public final class ButtonFactory {
    /**
     * Creates new {@link ButtonType button type}.
     *
     * @param text       button type text
     * @param buttonData button type position
     * @return {@link ButtonType} instance
     */
    public static ButtonType buttonType(String text, ButtonBar.ButtonData buttonData) {
        return new ButtonType(text, buttonData);
    }

    /**
     * Creates new {@link Button}.
     *
     * @param text button text
     * @return {@link Button} instance
     */
    public static Button button(String text) {
        return new Button(text);
    }

    /**
     * Creates new {@link Button}.
     *
     * @param text    button text
     * @param graphic button icon
     * @return {@link Button} instance
     */
    public static Button button(String text, Node graphic) {
        return new Button(text, graphic);
    }

    /**
     * Creates new {@link Button}.
     *
     * @param text   button text
     * @param action button action
     * @return {@link Button} instance
     * @throws NullPointerException if {@code action} is {@code null}
     */
    public static Button button(String text, EventHandler<ActionEvent> action) {
        var button = new Button(text);
        button.setOnAction(Objects.requireNonNull(action, "Action cannot be null"));
        return button;
    }

    /**
     * Creates new {@link Button}.
     *
     * @param text    button text
     * @param graphic button icon
     * @param action  button action
     * @return {@link Button} instance
     * @throws NullPointerException if {@code action} is {@code null}
     */
    public static Button button(String text, Node graphic, EventHandler<ActionEvent> action) {
        var button = new Button(text, graphic);
        button.setOnAction(Objects.requireNonNull(action, "Action cannot be null"));
        return button;
    }

    /**
     * Creates new {@link RadioButton}.
     *
     * @param text  button text
     * @param group toggle group
     * @return {@link RadioButton} instance
     */
    public static RadioButton radioButton(String text, ToggleGroup group) {
        var button = new RadioButton(text);
        button.setToggleGroup(group);
        return button;
    }

    /**
     * Creates new {@link RadioButton}.
     *
     * @param text     button text
     * @param group    toggle group
     * @param selected initial selected state
     * @return {@link RadioButton} instance
     */
    public static RadioButton radioButton(String text, ToggleGroup group, boolean selected) {
        var button = new RadioButton(text);
        button.setToggleGroup(group);
        button.setSelected(selected);
        return button;
    }
}
