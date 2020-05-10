package org.panteleyev.fx;

/*
 Copyright (c) Petr Panteleyev. All rights reserved.
 Licensed under the BSD license. See LICENSE file in the project root for full license information.
 */

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
     * @param suffix         menu item text suffix
     * @param keyCombination menu item accelerator
     * @param action         menu item action
     * @param disableBinding boolean binding to disable menu item
     * @return menu item
     */
    static MenuItem newMenuItem(ResourceBundle rb,
                                String key,
                                String suffix,
                                KeyCombination keyCombination,
                                EventHandler<ActionEvent> action,
                                BooleanBinding disableBinding)
    {
        var menuItem = newMenuItem(rb, key, suffix, keyCombination, action);
        menuItem.disableProperty().bind(disableBinding);
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
