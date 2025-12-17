// Copyright © 2025 Petr Panteleyev
// SPDX-License-Identifier: BSD-2-Clause
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

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import static org.panteleyev.functional.Scope.apply;

/**
 * This class implements the concept of UI action. The purpose is to uniformly create multiple UI controls for the same
 * event handler, text, accelerator, etc.
 * <p>
 * <strong>Example:</strong>
 * {@snippet lang = java:
 * var action = fxAction("Open...")
 *     .onAction(this::onOpen)
 *     .accelerator(new KeyCodeCombination(KeyCode.O, SHORTCUT_DOWN));
 *}
 */
public final class FxAction {
    private final StringProperty textProperty = new SimpleStringProperty("");
    private final ObjectProperty<KeyCombination> acceleratorProperty = new SimpleObjectProperty<>(null);
    private final BooleanProperty disableProperty = new SimpleBooleanProperty(false);
    private final ObjectProperty<EventHandler<ActionEvent>> onActionProperty = new SimpleObjectProperty<>(_ -> {});
    private final BooleanProperty selectedProperty = new SimpleBooleanProperty(false);
    private final BooleanProperty visibleProperty = new SimpleBooleanProperty(true);

    private final List<WeakReference<CheckMenuItem>> checkMenuItemList = new ArrayList<>();

    private FxAction() {
        selectedProperty.addListener((_, oldValue, newValue) -> {
            oldValue = oldValue != null && oldValue;
            newValue = newValue != null && newValue;
            if (newValue != oldValue) {
                onSelectedPropertyChanged(oldValue, newValue);
            }
        });
    }

    private FxAction(String text) {
        this();
        textProperty.set(text);
    }

    /**
     * Returns action text property. Updating this property will update same property of all related controls. Default
     * value is an empty string.
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
    public FxAction text(String text) {
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
    public FxAction accelerator(KeyCombination accelerator) {
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
    public FxAction disable(boolean disable) {
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
    public FxAction onAction(EventHandler<ActionEvent> onAction) {
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
    public FxAction selected(boolean selected) {
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
    public FxAction visible(boolean visible) {
        visibleProperty.set(visible);
        return this;
    }

    /**
     * Creates new instance of {@link FxAction}.
     *
     * @return action
     */
    public static FxAction fxAction() {
        return new FxAction();
    }

    /**
     * Creates new instance of {@link FxAction}.
     *
     * @param text text
     * @return action
     */
    public static FxAction fxAction(String text) {
        return new FxAction(text);
    }

    /**
     * Creates menu item bound to this action.
     *
     * @return menu item
     */
    public MenuItem createMenuItem() {
        return apply(new MenuItem(), menuItem -> {
            menuItem.textProperty().bind(textProperty);
            menuItem.acceleratorProperty().bind(acceleratorProperty);
            menuItem.disableProperty().bind(disableProperty);
            menuItem.onActionProperty().bind(onActionProperty);
            menuItem.visibleProperty().bind(visibleProperty);
        });
    }

    /**
     * Creates check menu item bound to this action.
     *
     * @return check menu item
     */
    public CheckMenuItem createCheckMenuItem() {
        var checkMenuItem = apply(new CheckMenuItem(), item -> {
            item.textProperty().bind(textProperty);
            item.acceleratorProperty().bind(acceleratorProperty);
            item.disableProperty().bind(disableProperty);
            item.onActionProperty().bind(onActionProperty);
            item.visibleProperty().bind(visibleProperty);
        });

        synchronized (checkMenuItemList) {
            checkMenuItemList.add(new WeakReference<>(checkMenuItem));
        }
        return checkMenuItem;
    }

    /**
     * Creates button bound to this action.
     *
     * @return button
     */
    public Button createButton() {
        return apply(new Button(), button -> {
            button.textProperty().bind(textProperty);
            button.disableProperty().bind(disableProperty);
            button.onActionProperty().bind(onActionProperty);
            button.visibleProperty().bind(visibleProperty);
        });
    }

    private void onSelectedPropertyChanged(boolean oldValue, boolean newValue) {
        synchronized (checkMenuItemList) {
            for (var iter = checkMenuItemList.iterator(); iter.hasNext(); ) {
                var checkMenuItem = iter.next().get();
                if (checkMenuItem == null) {
                    iter.remove();
                } else {
                    checkMenuItem.setSelected(newValue);
                }
            }
        }
    }
}
