// Copyright © 2022-2026 Petr Panteleyev
// SPDX-License-Identifier: BSD-2-Clause
package org.panteleyev.fx.factories;

import javafx.scene.Node;
import javafx.scene.control.Tab;

/**
 * Provides factory methods to create instances of {@link Tab}.
 * <p>
 * All methods set {@link Tab#closableProperty()} to {@code false} to override default behavior.
 */
public final class TabFactory {
    /**
     * Creates a tab with no title.
     *
     * @return tab
     */
    public static Tab tab() {
        var tab = new Tab();
        tab.setClosable(false);
        return tab;
    }

    /**
     * Creates a tab with a text title.
     *
     * @param text the title of the tab
     * @return tab
     */
    public static Tab tab(String text) {
        var tab = new Tab(text);
        tab.setClosable(false);
        return tab;
    }

    /**
     * Creates a tab with a text title and the specified content node.
     *
     * @param text the title of the tab
     * @param node the content of the tab
     * @return tab
     */
    public static Tab tab(String text, Node node) {
        var tab = new Tab(text, node);
        tab.setClosable(false);
        return tab;
    }

    private TabFactory() {
    }
}
