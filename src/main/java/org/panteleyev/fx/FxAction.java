/*
 Copyright © 2025 Petr Panteleyev <petr@panteleyev.org>
 SPDX-License-Identifier: BSD-2-Clause
 */
package org.panteleyev.fx;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;

/**
 * This class implements the concept of UI action. The purpose is to uniformly create multiple UI controls for the same
 * event handler, text, accelerator, etc.
 */
public final class FxAction {
    private final StringProperty textProperty = new SimpleStringProperty("");
    private final ObjectProperty<KeyCombination> acceleratorProperty = new SimpleObjectProperty<>(null);
    private final BooleanProperty disableProperty = new SimpleBooleanProperty(false);
    private final ObjectProperty<EventHandler<ActionEvent>> onActionProperty = new SimpleObjectProperty<>(
            actionEvent -> {});
    private final BooleanProperty selectedProperty = new SimpleBooleanProperty(false);
    private final BooleanProperty visibleProperty = new SimpleBooleanProperty(true);

    private FxAction() {
    }

    /**
     * Returns action text property. Updating this property will update same property of all related controls.
     * Default value is an empty string.
     *
     * @return action text property
     */
    public StringProperty textProperty() {
        return textProperty;
    }

    /**
     * Changes text property.
     *
     * @param text new text value
     * @return this action
     */
    public FxAction setText(String text) {
        textProperty.set(text);
        return this;
    }

    /**
     * Returns action accelerator property. Updating this property will update same property of all related controls.
     * Default value is {@code null}.
     *
     * @return action accelerator property
     */
    public ObjectProperty<KeyCombination> acceleratorProperty() {
        return acceleratorProperty;
    }

    /**
     * Changes accelerator property.
     *
     * @param accelerator new accelerator value
     * @return this action
     */
    public FxAction setAccelerator(KeyCombination accelerator) {
        acceleratorProperty.set(accelerator);
        return this;
    }

    /**
     * Returns action disable property. Updating this property will update same property of all related controls.
     * Default value is {@code false}.
     *
     * @return action disable property
     */
    public BooleanProperty disableProperty() {
        return disableProperty;
    }

    /**
     * Changes disable property.
     *
     * @param disable new disable value
     * @return this action
     */
    public FxAction setDisable(boolean disable) {
        disableProperty.set(disable);
        return this;
    }

    /**
     * Returns action event handler property. Updating this property will update same property of all related controls.
     * Default value is an empty lambda.
     *
     * @return action event handler property
     */
    public ObjectProperty<EventHandler<ActionEvent>> onActionProperty() {
        return onActionProperty;
    }

    /**
     * Changes onAction property.
     *
     * @param onAction new onAction value
     * @return this action
     */
    public FxAction setOnAction(EventHandler<ActionEvent> onAction) {
        onActionProperty.set(onAction);
        return this;
    }

    /**
     * Returns action selected property. Updating this property will update same property of all related controls.
     * Default value is {@code false}.
     *
     * @return action selected property
     */
    public BooleanProperty selectedProperty() {
        return selectedProperty;
    }

    /**
     * Changes selected property.
     *
     * @param selected new selected value
     * @return this action
     */
    public FxAction setSelected(boolean selected) {
        selectedProperty.set(selected);
        return this;
    }

    /**
     * Returns action visible property. Updating this property will update same property of all related controls.
     * Default value is {@code true}.
     *
     * @return action visible property
     */
    public BooleanProperty visibleProperty() {
        return visibleProperty;
    }

    /**
     * Changes visible property.
     *
     * @param visible new visible value
     * @return this action
     */
    public FxAction setVisible(boolean visible) {
        visibleProperty.set(visible);
        return this;
    }

    /**
     * Creates new instance of {@link FxAction}.
     *
     * @param text         text
     * @param eventHandler event handler
     * @return action
     */
    public static FxAction create(String text, EventHandler<ActionEvent> eventHandler) {
        var action = new FxAction();
        action.textProperty().set(text);
        action.onActionProperty().set(eventHandler);
        return action;
    }

    /**
     * Creates new instance of {@link FxAction}.
     *
     * @param text         text
     * @param eventHandler event handler
     * @param accelerator  accelerator
     * @return action
     */
    public static FxAction create(String text, EventHandler<ActionEvent> eventHandler, KeyCombination accelerator) {
        var action = new FxAction();
        action.textProperty().set(text);
        action.acceleratorProperty().set(accelerator);
        action.onActionProperty().set(eventHandler);
        return action;
    }

    /**
     * Creates new instance of {@link FxAction}.
     *
     * @param text         text
     * @param eventHandler event handler
     * @param disable      disable
     * @return action
     */
    public static FxAction create(String text, EventHandler<ActionEvent> eventHandler, boolean disable) {
        var action = new FxAction();
        action.textProperty().set(text);
        action.onActionProperty().set(eventHandler);
        action.disableProperty().set(disable);
        return action;
    }

    /**
     * Creates new instance of {@link FxAction}.
     *
     * @param text            text
     * @param eventHandler    event handler
     * @param accelerator     accelerator
     * @param disableProperty disable property binding
     * @return action
     */
    public static FxAction create(String text, EventHandler<ActionEvent> eventHandler, KeyCombination accelerator,
            BooleanProperty disableProperty) {
        var action = new FxAction();
        action.textProperty().set(text);
        action.acceleratorProperty().set(accelerator);
        action.onActionProperty().set(eventHandler);
        action.disableProperty().bind(disableProperty);
        return action;
    }

    /**
     * Creates new instance of {@link FxAction}.
     *
     * @param text         text
     * @param eventHandler event handler
     * @param accelerator  accelerator
     * @param disable      disable
     * @return action
     */
    public static FxAction create(String text, EventHandler<ActionEvent> eventHandler, KeyCombination accelerator,
            boolean disable) {
        var action = new FxAction();
        action.textProperty().set(text);
        action.acceleratorProperty().set(accelerator);
        action.onActionProperty().set(eventHandler);
        action.disableProperty().set(disable);
        return action;
    }

    /**
     * Creates menu item bound to this action.
     *
     * @return menu item
     */
    public MenuItem createMenuItem() {
        var menuItem = new MenuItem();
        menuItem.textProperty().bind(textProperty);
        menuItem.acceleratorProperty().bind(acceleratorProperty);
        menuItem.disableProperty().bind(disableProperty);
        menuItem.onActionProperty().bind(onActionProperty);
        menuItem.visibleProperty().bind(visibleProperty);
        return menuItem;
    }

    /**
     * Creates check menu item bound to this action.
     *
     * @return check menu item
     */
    public CheckMenuItem createCheckMenuItem() {
        var checkMenuItem = new CheckMenuItem();
        checkMenuItem.textProperty().bind(textProperty);
        checkMenuItem.acceleratorProperty().bind(acceleratorProperty);
        checkMenuItem.disableProperty().bind(disableProperty);
        checkMenuItem.onActionProperty().bind(onActionProperty);
        checkMenuItem.selectedProperty().bind(selectedProperty);
        checkMenuItem.visibleProperty().bind(visibleProperty);
        return checkMenuItem;
    }

    /**
     * Creates button bound to this action.
     *
     * @return button
     */
    public Button createButton() {
        var button = new Button();
        button.textProperty().bind(textProperty);
        button.disableProperty().bind(disableProperty);
        button.onActionProperty().bind(onActionProperty);
        button.visibleProperty().bind(visibleProperty);
        return button;
    }
}
