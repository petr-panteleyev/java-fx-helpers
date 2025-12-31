// Copyright © 2020-2025 Petr Panteleyev
// SPDX-License-Identifier: BSD-2-Clause
package org.panteleyev.fx.factories;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

import java.util.Arrays;
import java.util.Objects;

import static org.panteleyev.functional.Scope.apply;

/**
 * Provides factory methods to create menus, menu items, etc.
 * <p>
 * <strong>Example:</strong>
 * {@snippet lang = java:
 * var menu = menu("File",
 *     menuItem("Open"),
 *     menuItem("Save"),
 *     new SeparatorMenuItem(),
 *     apply(menuItem("Exit"), item -> {
 *         item.setOnAction(_ -> System.exit(0));
 *     })
 * )
 *}
 */
public final class MenuFactory {
    //
    // MenuItem
    //

    /**
     * Creates menu item.
     *
     * @param text the display text
     * @return menu item
     */
    public static MenuItem menuItem(String text) {
        return new MenuItem(text);
    }

    /**
     * Creates menu item.
     *
     * @param text    the display text
     * @param graphic the graphic node
     * @return menu item
     */
    public static MenuItem menuItem(String text, Node graphic) {
        return new MenuItem(text, graphic);
    }

    /**
     * Creates menu item.
     *
     * @param text         the display text
     * @param eventHandler the value for the {@code onAction} property, ignored if {@code null}
     * @return menu item
     */
    public static MenuItem menuItem(String text, EventHandler<ActionEvent> eventHandler) {
        var item = menuItem(text);
        if (eventHandler != null) {
            item.setOnAction(eventHandler);
        }
        return item;
    }

    /**
     * Creates menu item.
     *
     * @param text         the display text
     * @param graphic      the graphic node
     * @param eventHandler the value for the {@code onAction} property, ignored if {@code null}
     * @return menu item
     */
    public static MenuItem menuItem(String text, Node graphic, EventHandler<ActionEvent> eventHandler) {
        var item = menuItem(text, graphic);
        if (eventHandler != null) {
            item.setOnAction(eventHandler);
        }
        return item;
    }

    //
    // CheckMenuItem
    //

    /**
     * Creates an empty {@link CheckMenuItem}.
     *
     * @return an instance of {@link CheckMenuItem}
     */
    public static CheckMenuItem checkMenuItem() {
        return new CheckMenuItem();
    }

    /**
     * Creates an instance of {@link CheckMenuItem} with the specified display text.
     *
     * @param text the display text
     * @return an instance of {@link CheckMenuItem}
     */
    public static CheckMenuItem checkMenuItem(String text) {
        return new CheckMenuItem(text);
    }

    /**
     * Creates an instance of {@link CheckMenuItem} with the specified display text and graphic node.
     *
     * @param text    the display text
     * @param graphic the graphic node
     * @return an instance of {@link CheckMenuItem}
     */
    public static CheckMenuItem checkMenuItem(String text, Node graphic) {
        return new CheckMenuItem(text);
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
     * Creates new menu bar. useSystemMenuBar property is set to {@code true}.
     *
     * @param menus menus
     * @return menu bar
     */
    public static MenuBar menuBar(Menu... menus) {
        return apply(new MenuBar(menus), menuBar -> menuBar.setUseSystemMenuBar(true));
    }

    private MenuFactory() {
    }
}
