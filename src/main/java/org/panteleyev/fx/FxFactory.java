/*
 * Copyright (c) 2019, 2020, Petr Panteleyev <petr@panteleyev.org>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */

package org.panteleyev.fx;

import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import org.controlsfx.control.textfield.CustomTextField;
import org.controlsfx.control.textfield.TextFields;
import java.util.ResourceBundle;
import java.util.function.Consumer;

/**
 * This interface provides convenience methods to create and configure various JavaFX controls in one method call.
 * Most of the methods take resource bundle and text key as parameters for I18N purposes.
 */
public interface FxFactory {
    /**
     * @deprecated use {@link MenuFactory#newMenuItem(ResourceBundle, String, EventHandler)}
     */
    @Deprecated(since = "1.1.0", forRemoval = true)
    static MenuItem newMenuItem(ResourceBundle rb, String key, EventHandler<ActionEvent> action) {
        return MenuFactory.newMenuItem(rb, key, action);
    }

    /**
     * @deprecated use {@link MenuFactory#newMenuItem(ResourceBundle, String, String, EventHandler)}
     */
    @Deprecated(since = "1.1.0", forRemoval = true)
    static MenuItem newMenuItem(ResourceBundle rb, String key, String suffix, EventHandler<ActionEvent> action) {
        return MenuFactory.newMenuItem(rb, key, suffix, action);
    }

    /**
     * @deprecated use {@link MenuFactory#newMenuItem(String, EventHandler)}
     */
    @Deprecated(since = "1.1.0", forRemoval = true)
    static MenuItem newMenuItem(String text, EventHandler<ActionEvent> action) {
        return MenuFactory.newMenuItem(text, action);
    }

    /**
     * @deprecated use {@link MenuFactory#newMenuItem(ResourceBundle, String, KeyCombination, EventHandler)}
     */
    @Deprecated(since = "1.1.0", forRemoval = true)
    static MenuItem newMenuItem(ResourceBundle rb, String key, KeyCombination keyCombination,
                                EventHandler<ActionEvent> action)
    {
        return MenuFactory.newMenuItem(rb, key, keyCombination, action);
    }

    /**
     * @deprecated use {@link MenuFactory#newMenuItem(ResourceBundle, String, String, KeyCombination, EventHandler)}
     */
    @Deprecated(since = "1.1.0", forRemoval = true)
    static MenuItem newMenuItem(ResourceBundle rb,
                                String key,
                                String suffix,
                                KeyCombination keyCombination,
                                EventHandler<ActionEvent> action)
    {
        return MenuFactory.newMenuItem(rb, key, suffix, keyCombination, action);
    }

    /**
     * @deprecated use {@link MenuFactory#newMenuItem(ResourceBundle, String, EventHandler, BooleanBinding)}
     */
    @Deprecated(since = "1.1.0", forRemoval = true)
    static MenuItem newMenuItem(ResourceBundle rb, String key, EventHandler<ActionEvent> action,
                                BooleanBinding disableBinding)
    {
        return MenuFactory.newMenuItem(rb, key, action, disableBinding);
    }

    /**
     * @deprecated use {@link MenuFactory#newMenuItem(ResourceBundle, String, String, EventHandler, BooleanBinding)}
     */
    @Deprecated(since = "1.1.0", forRemoval = true)
    static MenuItem newMenuItem(ResourceBundle rb,
                                String key,
                                String suffix,
                                EventHandler<ActionEvent> action,
                                BooleanBinding disableBinding)
    {
        return MenuFactory.newMenuItem(rb, key, suffix, action, disableBinding);
    }

    /**
     * @deprecated use {@link MenuFactory#newMenuItem(ResourceBundle, String, KeyCombination, EventHandler, BooleanBinding)}
     */
    @Deprecated(since = "1.1.0", forRemoval = true)
    static MenuItem newMenuItem(ResourceBundle rb,
                                String key,
                                KeyCombination keyCombination,
                                EventHandler<ActionEvent> action,
                                BooleanBinding disableBinding)
    {
        return MenuFactory.newMenuItem(rb, key, keyCombination, action, disableBinding);
    }

    /**
     * @deprecated use {@link MenuFactory#newCheckMenuItem(ResourceBundle, String, boolean)}
     */
    @Deprecated(since = "1.1.0", forRemoval = true)
    static CheckMenuItem newCheckMenuItem(ResourceBundle rb, String key, boolean selected) {
        return MenuFactory.newCheckMenuItem(rb, key, selected);
    }

    /**
     * @deprecated use {@link MenuFactory#newCheckMenuItem(ResourceBundle, String, boolean, EventHandler)}
     */
    @Deprecated(since = "1.1.0", forRemoval = true)
    static CheckMenuItem newCheckMenuItem(ResourceBundle rb,
                                          String key,
                                          boolean selected,
                                          EventHandler<ActionEvent> action)
    {
        return MenuFactory.newCheckMenuItem(rb, key, selected, action);
    }

    /**
     * @deprecated use {@link MenuFactory#newCheckMenuItem(ResourceBundle, String, boolean, KeyCombination)}
     */
    @Deprecated(since = "1.1.0", forRemoval = true)
    static CheckMenuItem newCheckMenuItem(ResourceBundle rb,
                                          String key,
                                          boolean selected,
                                          KeyCombination keyCombination)
    {
        return MenuFactory.newCheckMenuItem(rb, key, selected, keyCombination);
    }

    /**
     * @deprecated use {@link MenuFactory#newCheckMenuItem(ResourceBundle, String, boolean, KeyCombination, EventHandler)}
     */
    @Deprecated(since = "1.1.0", forRemoval = true)
    static CheckMenuItem newCheckMenuItem(ResourceBundle rb,
                                          String key,
                                          boolean selected,
                                          KeyCombination keyCombination,
                                          EventHandler<ActionEvent> action)
    {
        return MenuFactory.newCheckMenuItem(rb, key, selected, keyCombination, action);
    }

    /**
     * @deprecated use {@link MenuFactory#newMenu(ResourceBundle, String, MenuItem...)}
     */
    @Deprecated(since = "1.1.0", forRemoval = true)
    static Menu newMenu(ResourceBundle rb, String key, MenuItem... items) {
        return MenuFactory.newMenu(rb, key, items);
    }

    /**
     * @deprecated use {@link MenuFactory#newMenu(ResourceBundle, String, Node, MenuItem...)}
     */
    @Deprecated(since = "1.1.0", forRemoval = true)
    static Menu newMenu(ResourceBundle rb, String key, Node node, MenuItem... items) {
        return MenuFactory.newMenu(rb, key, node, items);
    }

    /**
     * @deprecated use {@link MenuFactory#newMenuBar(Menu...)}
     */
    @Deprecated(since = "1.1.0", forRemoval = true)
    static MenuBar newMenuBar(Menu... menus) {
        return MenuFactory.newMenuBar(menus);
    }

    /**
     * Creates new search text field.
     *
     * @param valueCallback value callback
     * @return text field
     */
    static TextField newSearchField(Consumer<String> valueCallback) {
        return newSearchField(null, valueCallback);
    }

    /**
     * Creates new search text field.
     *
     * @param leftImage     image to put on the left side of the text field
     * @param valueCallback value callback
     * @return text field
     */
    static TextField newSearchField(Image leftImage, Consumer<String> valueCallback) {
        var searchField = TextFields.createClearableTextField();
        searchField.setPrefColumnCount(20);
        if (leftImage != null) {
            ((CustomTextField) searchField).setLeft(new ImageView(leftImage));
        }
        searchField.textProperty().addListener((x, y, newValue) -> valueCallback.accept(newValue));
        searchField.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ESCAPE) {
                searchField.clear();
            }
        });
        return searchField;
    }

    /**
     * @deprecated use {@link ButtonFactory#newButton(String, EventHandler)}
     */
    @Deprecated(since = "1.1.0", forRemoval = true)
    static Button newButton(String text, EventHandler<ActionEvent> action) {
        return ButtonFactory.newButton(text, action);
    }

    /**
     * @deprecated use {@link ButtonFactory#newButton(ResourceBundle, String, EventHandler)}
     */
    @Deprecated(since = "1.1.0", forRemoval = true)
    static Button newButton(ResourceBundle rb, String key, EventHandler<ActionEvent> action) {
        return ButtonFactory.newButton(rb, key, action);
    }

    /**
     * @deprecated use {@link ButtonFactory#newRadioButton(ResourceBundle, String, ToggleGroup)}
     */
    @Deprecated(since = "1.1.0", forRemoval = true)
    static RadioButton newRadioButton(ResourceBundle rb, String key, ToggleGroup group) {
        return ButtonFactory.newRadioButton(rb, key, group);
    }

    /**
     * @deprecated use {@link ButtonFactory#newRadioButton(ResourceBundle, String, ToggleGroup, boolean)}
     */
    @Deprecated(since = "1.1.0", forRemoval = true)
    static RadioButton newRadioButton(ResourceBundle rb, String key, ToggleGroup group, boolean selected) {
        return ButtonFactory.newRadioButton(rb, key, group, selected);
    }

    /**
     * @deprecated use {@link LabelFactory#newLabel(ResourceBundle, String)}
     */
    @Deprecated(since = "1.1.0", forRemoval = true)
    static Label newLabel(ResourceBundle rb, String key) {
        return new Label(rb.getString(key));
    }

    /**
     * @deprecated use {@link LabelFactory#newLabel(ResourceBundle, String, Node)}
     */
    @Deprecated(since = "1.1.0", forRemoval = true)
    static Label newLabel(ResourceBundle rb, String key, Node node) {
        return new Label(rb.getString(key), node);
    }

    /**
     * Creates new check box.
     *
     * @param rb  resource bundle
     * @param key text key
     * @return check box
     */
    static CheckBox newCheckBox(ResourceBundle rb, String key) {
        return new CheckBox(rb.getString(key));
    }

    /**
     * Creates new tab.
     *
     * @param rb        resource bundle
     * @param key       text key
     * @param closeable closeable flag
     * @return tab
     */
    static Tab newTab(ResourceBundle rb, String key, boolean closeable) {
        var tab = new Tab(rb.getString(key));
        tab.setClosable(closeable);
        return tab;
    }

    /**
     * Creates new tab.
     *
     * @param rb        resource bundle
     * @param key       text key
     * @param closeable closeable flag
     * @param node      tab content
     * @return tab
     */
    static Tab newTab(ResourceBundle rb, String key, boolean closeable, Node node) {
        var tab = new Tab(rb.getString(key), node);
        tab.setClosable(closeable);
        return tab;
    }
}
