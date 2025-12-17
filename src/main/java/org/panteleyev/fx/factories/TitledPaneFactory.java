// Copyright © 2025 Petr Panteleyev
// SPDX-License-Identifier: BSD-2-Clause
package org.panteleyev.fx.factories;

import javafx.scene.Node;
import javafx.scene.control.TitledPane;

/**
 * Provides factory methods to create instances of {@link TitledPane}.
 */
public class TitledPaneFactory {
    /**
     * Creates a new TitledPane with no title or content.
     *
     * @return TitledPane
     */
    public static TitledPane titledPane() {
        return new TitledPane();
    }

    /**
     * Creates a new TitledPane with a title and content.
     *
     * @param title the title of the TitledPane
     * @param node  the content of the TitledPane
     * @return titled pane
     */
    public static TitledPane titledPane(String title, Node node) {
        return new TitledPane(title, node);
    }
}
