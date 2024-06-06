/*
 Copyright © 2020-2024 Petr Panteleyev <petr@panteleyev.org>
 SPDX-License-Identifier: BSD-2-Clause
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

import java.util.Arrays;
import java.util.Objects;

/**
 * This class provides convenience methods to create menus, menu items, etc.
 */
public final class MenuFactory {
    private MenuFactory() {
    }

    /**
     * Creates new menu item.
     *
     * @param text   menu item text
     * @param action menu item action
     * @return menu item
     */
    public static MenuItem menuItem(String text, EventHandler<ActionEvent> action) {
        var menuItem = new MenuItem(text);
        menuItem.setOnAction(action);
        return menuItem;
    }

    /**
     * Creates new menu item.
     *
     * @param text   menu item text
     * @param node   menu item node
     * @param action menu item action
     * @return menu item
     */
    public static MenuItem menuItem(String text, Node node, EventHandler<ActionEvent> action) {
        var menuItem = new MenuItem(text, node);
        menuItem.setOnAction(action);
        return menuItem;
    }

    /**
     * Creates new menu item.
     *
     * @param text           menu item text
     * @param keyCombination menu item accelerator
     * @param action         menu item action
     * @return menu item
     */
    public static MenuItem menuItem(String text, KeyCombination keyCombination, EventHandler<ActionEvent> action) {
        var menuItem = menuItem(text, action);
        menuItem.setAccelerator(keyCombination);
        return menuItem;
    }

    /**
     * Creates new menu item.
     *
     * @param text           menu item text
     * @param node           menu item node
     * @param keyCombination menu item accelerator
     * @param action         menu item action
     * @return menu item
     */
    public static MenuItem menuItem(String text, Node node, KeyCombination keyCombination,
                                    EventHandler<ActionEvent> action) {
        var menuItem = menuItem(text, node, action);
        menuItem.setAccelerator(keyCombination);
        return menuItem;
    }

    /**
     * Creates new menu item.
     *
     * @param text           menu item text
     * @param action         menu item action
     * @param disableBinding boolean binding to disable menu item
     * @return menu item
     */
    public static MenuItem menuItem(String text, EventHandler<ActionEvent> action, BooleanBinding disableBinding) {
        var menuItem = menuItem(text, action);
        menuItem.disableProperty().bind(disableBinding);
        return menuItem;
    }

    /**
     * Creates new menu item.
     *
     * @param text           menu item text
     * @param node           menu item node
     * @param action         menu item action
     * @param disableBinding boolean binding to disable menu item
     * @return menu item
     */
    public static MenuItem menuItem(String text, Node node, EventHandler<ActionEvent> action,
                                    BooleanBinding disableBinding) {
        var menuItem = menuItem(text, node, action);
        menuItem.disableProperty().bind(disableBinding);
        return menuItem;
    }

    /**
     * Creates new menu item.
     *
     * @param text           menu item text
     * @param keyCombination menu item accelerator
     * @param action         menu item action
     * @param disableBinding boolean binding to disable menu item
     * @return menu item
     */
    public static MenuItem menuItem(String text,
                                    KeyCombination keyCombination,
                                    EventHandler<ActionEvent> action,
                                    BooleanBinding disableBinding) {
        var menuItem = menuItem(text, keyCombination, action);
        menuItem.disableProperty().bind(disableBinding);
        return menuItem;
    }

    /**
     * Creates new menu item.
     *
     * @param text           menu item text
     * @param node           menu item node
     * @param keyCombination menu item accelerator
     * @param action         menu item action
     * @param disableBinding boolean binding to disable menu item
     * @return menu item
     */
    public static MenuItem menuItem(String text,
                                    Node node,
                                    KeyCombination keyCombination,
                                    EventHandler<ActionEvent> action,
                                    BooleanBinding disableBinding) {
        var menuItem = menuItem(text, node, keyCombination, action);
        menuItem.disableProperty().bind(disableBinding);
        return menuItem;
    }

    /**
     * Creates new check menu item.
     *
     * @param text     menu item text
     * @param selected initial selected state
     * @return menu item
     */
    public static CheckMenuItem checkMenuItem(String text, boolean selected) {
        var menuItem = new CheckMenuItem(text);
        menuItem.setSelected(selected);
        return menuItem;
    }

    /**
     * Creates new check menu item.
     *
     * @param text     menu item text
     * @param selected initial selected state
     * @param action   menu item action
     * @return menu item
     */
    public static CheckMenuItem checkMenuItem(String text, boolean selected, EventHandler<ActionEvent> action) {
        var menuItem = new CheckMenuItem(text);
        menuItem.setSelected(selected);
        menuItem.setOnAction(action);
        return menuItem;
    }

    /**
     * Creates new check menu item.
     *
     * @param text           menu item text
     * @param selected       initial selected state
     * @param keyCombination menu item accelerator
     * @return menu item
     */
    public static CheckMenuItem checkMenuItem(String text, boolean selected, KeyCombination keyCombination) {
        var menuItem = new CheckMenuItem(text);
        menuItem.setSelected(selected);
        menuItem.setAccelerator(keyCombination);
        return menuItem;
    }

    /**
     * Creates new check menu item.
     *
     * @param text           menu item text
     * @param selected       initial selected state
     * @param keyCombination menu item accelerator
     * @param action         menu item action
     * @return menu item
     */
    public static CheckMenuItem checkMenuItem(String text,
                                              boolean selected,
                                              KeyCombination keyCombination,
                                              EventHandler<ActionEvent> action) {
        var menuItem = new CheckMenuItem(text);
        menuItem.setSelected(selected);
        menuItem.setAccelerator(keyCombination);
        menuItem.setOnAction(action);
        return menuItem;
    }

    /**
     * Creates new menu.
     *
     * @param text  menu text
     * @param items menu items
     * @return menu
     * @deprecated use {@link #menu(String, MenuItem...)} instead
     */
    @Deprecated(forRemoval = true)
    public static Menu newMenu(String text, MenuItem... items) {
        return new Menu(text, null, items);
    }

    /**
     * Creates new menu. Null items are ignored which allows to create menu items optionally.
     *
     * @param text  menu text
     * @param items menu items
     * @return menu
     */
    public static Menu menu(String text, MenuItem... items) {
        return menu(text, null, items);
    }

    /**
     * Creates new menu. Null items are ignored which allows to create menu items optionally.
     *
     * @param text  menu text
     * @param node  menu image node
     * @param items menu items
     * @return menu
     */
    public static Menu menu(String text, Node node, MenuItem... items) {
        var filtered = Arrays.stream(items)
                .filter(Objects::nonNull)
                .toArray(MenuItem[]::new);
        return new Menu(text, node, filtered);
    }

    /**
     * Creates new menu bar. useSystemMenuBar property is set to <code>true</code>.
     *
     * @param menus menus
     * @return menu bar
     */
    public static MenuBar menuBar(Menu... menus) {
        var menuBar = new MenuBar(menus);
        menuBar.setUseSystemMenuBar(true);
        return menuBar;
    }
}
