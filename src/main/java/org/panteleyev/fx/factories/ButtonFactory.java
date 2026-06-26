// Copyright © 2020-2026 Petr Panteleyev
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

import static java.util.Objects.requireNonNull;

/**
 * Provides factory methods to create buttons.
 */
public final class ButtonFactory {
    private static final String BUTTON_TEXT_NPE = "Button text must not be null";
    private static final String ACTION_NPE = "Action must not be null";

    /**
     * Creates new {@link ButtonType button type}.
     *
     * @param text       button type text
     * @param buttonData button type position
     * @return {@link ButtonType} instance
     * @throws NullPointerException if {@code text} or {@code buttonData} is {@code null}
     */
    public static ButtonType buttonType(String text, ButtonBar.ButtonData buttonData) {
        return new ButtonType(
                requireNonNull(text, BUTTON_TEXT_NPE),
                requireNonNull(buttonData, "Button data must not be null")
        );
    }

    /**
     * Creates new {@link Button}.
     *
     * @param text button text
     * @return {@link Button} instance
     * @throws NullPointerException if {@code text} is {@code null}
     */
    public static Button button(String text) {
        return new Button(requireNonNull(text, BUTTON_TEXT_NPE));
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
     * @throws NullPointerException if {@code text} or {@code action} is {@code null}
     */
    public static Button button(String text, EventHandler<ActionEvent> action) {
        var button = new Button(requireNonNull(text, BUTTON_TEXT_NPE));
        button.setOnAction(requireNonNull(action, ACTION_NPE));
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
        button.setOnAction(requireNonNull(action, ACTION_NPE));
        return button;
    }

    /**
     * Creates new {@link RadioButton}.
     *
     * @param text  button text
     * @param group toggle group
     * @return {@link RadioButton} instance
     * @throws NullPointerException if {@code text} is {@code null}
     */
    public static RadioButton radioButton(String text, ToggleGroup group) {
        var button = new RadioButton(requireNonNull(text, BUTTON_TEXT_NPE));
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
     * @throws NullPointerException if {@code text} is {@code null}
     */
    public static RadioButton radioButton(String text, ToggleGroup group, boolean selected) {
        var button = new RadioButton(requireNonNull(text, BUTTON_TEXT_NPE));
        button.setToggleGroup(group);
        button.setSelected(selected);
        return button;
    }
}
