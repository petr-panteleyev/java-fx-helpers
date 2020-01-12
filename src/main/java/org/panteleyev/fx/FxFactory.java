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
 * This class provides convenience methods to create and configure various JavaFX controls in one method call.
 * Most of the methods take resource bundle and text key as parameters for I18N purposes.
 */
public final class FxFactory {

    private FxFactory() {
    }

    /**
     * Creates new menu item.
     *
     * @param rb     resource bundle
     * @param key    menu item text key
     * @param action menu item action
     * @return menu item
     */
    public static MenuItem newMenuItem(ResourceBundle rb,
                                       String key,
                                       EventHandler<ActionEvent> action)
    {
        var menuItem = new MenuItem(rb.getString(key));
        menuItem.setOnAction(action);
        return menuItem;
    }

    /**
     * Creates new menu item.
     *
     * @param text   menu item text
     * @param action menu item action
     * @return menu item
     */
    public static MenuItem newMenuItem(String text,
                                       EventHandler<ActionEvent> action)
    {
        var menuItem = new MenuItem(text);
        menuItem.setOnAction(action);
        return menuItem;
    }

    /**
     * Creates new menu item.
     *
     * @param rb             resource bundle
     * @param key            menu item text key
     * @param keyCombination menu item accelerator
     * @param action         menu item action
     * @return menu item
     */
    public static MenuItem newMenuItem(ResourceBundle rb,
                                       String key,
                                       KeyCombination keyCombination,
                                       EventHandler<ActionEvent> action)
    {
        var menuItem = new MenuItem(rb.getString(key));
        menuItem.setAccelerator(keyCombination);
        menuItem.setOnAction(action);
        return menuItem;
    }

    /**
     * Creates new menu item.
     *
     * @param rb             resource bundle
     * @param key            menu item text key
     * @param action         menu item action
     * @param disableBinding boolean binding to disable menu item
     * @return menu item
     */
    public static MenuItem newMenuItem(ResourceBundle rb,
                                       String key,
                                       EventHandler<ActionEvent> action,
                                       BooleanBinding disableBinding)
    {
        var menuItem = newMenuItem(rb, key, action);
        menuItem.disableProperty().bind(disableBinding);
        return menuItem;
    }

    /**
     * Creates new menu item.
     *
     * @param rb             resource bundle
     * @param key            menu item text key
     * @param keyCombination menu item accelerator
     * @param action         menu item action
     * @param disableBinding boolean binding to disable menu item
     * @return menu item
     */
    public static MenuItem newMenuItem(ResourceBundle rb,
                                       String key,
                                       KeyCombination keyCombination,
                                       EventHandler<ActionEvent> action,
                                       BooleanBinding disableBinding)
    {
        var menuItem = newMenuItem(rb, key, keyCombination, action);
        menuItem.disableProperty().bind(disableBinding);
        return menuItem;
    }

    /**
     * Creates new check menu item.
     *
     * @param rb       resource bundle
     * @param key      menu item text key
     * @param selected initial selected state
     * @return menu item
     */
    public static CheckMenuItem newCheckMenuItem(ResourceBundle rb,
                                                 String key,
                                                 boolean selected)
    {
        var menuItem = new CheckMenuItem(rb.getString(key));
        menuItem.setSelected(selected);
        return menuItem;
    }

    /**
     * Creates new check menu item.
     *
     * @param rb       resource bundle
     * @param key      menu item text key
     * @param selected initial selected state
     * @param action   menu item action
     * @return menu item
     */
    public static CheckMenuItem newCheckMenuItem(ResourceBundle rb,
                                                 String key,
                                                 boolean selected,
                                                 EventHandler<ActionEvent> action)
    {
        var menuItem = newCheckMenuItem(rb, key, selected);
        menuItem.setOnAction(action);
        return menuItem;
    }

    /**
     * Creates new check menu item.
     *
     * @param rb             resource bundle
     * @param key            menu item text key
     * @param selected       initial selected state
     * @param keyCombination menu item accelerator
     * @return menu item
     */
    public static CheckMenuItem newCheckMenuItem(ResourceBundle rb,
                                                 String key,
                                                 boolean selected,
                                                 KeyCombination keyCombination)
    {
        var menuItem = newCheckMenuItem(rb, key, selected);
        menuItem.setAccelerator(keyCombination);
        return menuItem;
    }

    /**
     * Creates new check menu item.
     *
     * @param rb             resource bundle
     * @param key            menu item text key
     * @param selected       initial selected state
     * @param keyCombination menu item accelerator
     * @param action         menu item action
     * @return menu item
     */
    public static CheckMenuItem newCheckMenuItem(ResourceBundle rb,
                                                 String key,
                                                 boolean selected,
                                                 KeyCombination keyCombination,
                                                 EventHandler<ActionEvent> action)
    {
        var menuItem = newCheckMenuItem(rb, key, selected);
        menuItem.setAccelerator(keyCombination);
        menuItem.setOnAction(action);
        return menuItem;
    }

    /**
     * Creates new menu.
     *
     * @param rb    resource bundle
     * @param key   menu text key
     * @param items menu items
     * @return menu
     */
    public static Menu newMenu(ResourceBundle rb,
                               String key,
                               MenuItem... items)
    {
        return newMenu(rb, key, null, items);
    }

    /**
     * Creates new menu.
     *
     * @param rb    resource bundle
     * @param key   menu text key
     * @param node  menu image node
     * @param items menu items
     * @return menu
     */
    public static Menu newMenu(ResourceBundle rb,
                               String key,
                               Node node,
                               MenuItem... items)
    {
        return new Menu(rb.getString(key), node, items);
    }

    /**
     * Creates new menu bar. useSystemMenuBar property is set to <code>true</code>.
     *
     * @param menus menus
     * @return menu bar
     */
    public static MenuBar newMenuBar(Menu... menus) {
        var menuBar = new MenuBar(menus);
        menuBar.setUseSystemMenuBar(true);
        return menuBar;
    }

    /**
     * Creates new search text field.
     *
     * @param valueCallback value callback
     * @return text field
     */
    public static TextField newSearchField(Consumer<String> valueCallback) {
        return newSearchField(null, valueCallback);
    }

    /**
     * Creates new search text field.
     *
     * @param leftImage     image to put on the left side of the text field
     * @param valueCallback value callback
     * @return text field
     */
    public static TextField newSearchField(Image leftImage,
                                           Consumer<String> valueCallback)
    {
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
     * Creates new push button.
     *
     * @param text   button text
     * @param action button action
     * @return button
     */
    public static Button newButton(String text,
                                   EventHandler<ActionEvent> action)
    {
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
    public static Button newButton(ResourceBundle rb,
                                   String key,
                                   EventHandler<ActionEvent> action)
    {
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
    public static RadioButton newRadioButton(ResourceBundle rb,
                                             String key,
                                             ToggleGroup group)
    {
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
    public static RadioButton newRadioButton(ResourceBundle rb,
                                             String key,
                                             ToggleGroup group,
                                             boolean selected)
    {
        var button = newRadioButton(rb, key, group);
        button.setSelected(selected);
        return button;
    }

    /**
     * Creates new label.
     *
     * @param rb  resource bundle
     * @param key text key
     * @return label
     */
    public static Label newLabel(ResourceBundle rb, String key) {
        return new Label(rb.getString(key));
    }

    /**
     * Creates new label.
     *
     * @param rb   resource bundle
     * @param key  text key
     * @param node node for the label
     * @return label
     */
    public static Label newLabel(ResourceBundle rb, String key, Node node) {
        return new Label(rb.getString(key), node);
    }

    /**
     * Creates new check box.
     *
     * @param rb  resource bundle
     * @param key text key
     * @return check box
     */
    public static CheckBox newCheckBox(ResourceBundle rb, String key) {
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
    public static Tab newTab(ResourceBundle rb, String key, boolean closeable) {
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
    public static Tab newTab(ResourceBundle rb, String key, boolean closeable, Node node) {
        var tab = new Tab(rb.getString(key), node);
        tab.setClosable(closeable);
        return tab;
    }
}
