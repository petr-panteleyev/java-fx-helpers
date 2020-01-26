/*
 * Copyright (c) 2020, Petr Panteleyev <petr@panteleyev.org>
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
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;
import java.util.ResourceBundle;

/**
 * This interface provides convenience methods to create menus, menu items, etc.
 */
public interface MenuFactory {

    /**
     * Creates new menu item.
     *
     * @param text   menu item text
     * @param action menu item action
     * @return menu item
     */
    static MenuItem newMenuItem(String text, EventHandler<ActionEvent> action) {
        var menuItem = new MenuItem(text);
        menuItem.setOnAction(action);
        return menuItem;
    }

    /**
     * Creates new menu item.
     *
     * @param rb     resource bundle
     * @param key    menu item text key
     * @param action menu item action
     * @return menu item
     */
    static MenuItem newMenuItem(ResourceBundle rb, String key, EventHandler<ActionEvent> action) {
        return newMenuItem(rb.getString(key), action);
    }

    /**
     * Creates new menu item.
     *
     * @param rb     resource bundle
     * @param key    menu item text key
     * @param suffix menu item text suffix
     * @param action menu item action
     * @return menu item
     */
    static MenuItem newMenuItem(ResourceBundle rb, String key, String suffix, EventHandler<ActionEvent> action) {
        return newMenuItem(rb.getString(key) + suffix, action);
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
    static MenuItem newMenuItem(ResourceBundle rb,
                                String key,
                                KeyCombination keyCombination,
                                EventHandler<ActionEvent> action)
    {
        var menuItem = newMenuItem(rb.getString(key), action);
        menuItem.setAccelerator(keyCombination);
        return menuItem;
    }

    /**
     * Creates new menu item.
     *
     * @param rb             resource bundle
     * @param key            menu item text key
     * @param suffix         menu item text suffix
     * @param keyCombination menu item accelerator
     * @param action         menu item action
     * @return menu item
     */
    static MenuItem newMenuItem(ResourceBundle rb,
                                String key,
                                String suffix,
                                KeyCombination keyCombination,
                                EventHandler<ActionEvent> action)
    {
        var menuItem = newMenuItem(rb, key, suffix, action);
        menuItem.setAccelerator(keyCombination);
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
    static MenuItem newMenuItem(ResourceBundle rb,
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
     * @param suffix         menu item text suffix
     * @param action         menu item action
     * @param disableBinding boolean binding to disable menu item
     * @return menu item
     */
    static MenuItem newMenuItem(ResourceBundle rb,
                                String key,
                                String suffix,
                                EventHandler<ActionEvent> action,
                                BooleanBinding disableBinding)
    {
        var menuItem = newMenuItem(rb, key, suffix, action);
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
    static MenuItem newMenuItem(ResourceBundle rb,
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
    static CheckMenuItem newCheckMenuItem(ResourceBundle rb, String key, boolean selected) {
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
    static CheckMenuItem newCheckMenuItem(ResourceBundle rb,
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
    static CheckMenuItem newCheckMenuItem(ResourceBundle rb,
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
    static CheckMenuItem newCheckMenuItem(ResourceBundle rb,
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
    static Menu newMenu(ResourceBundle rb, String key, MenuItem... items) {
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
    static Menu newMenu(ResourceBundle rb, String key, Node node, MenuItem... items) {
        return new Menu(rb.getString(key), node, items);
    }

    /**
     * Creates new menu bar. useSystemMenuBar property is set to <code>true</code>.
     *
     * @param menus menus
     * @return menu bar
     */
    static MenuBar newMenuBar(Menu... menus) {
        var menuBar = new MenuBar(menus);
        menuBar.setUseSystemMenuBar(true);
        return menuBar;
    }
}
