// Copyright © 2020-2025 Petr Panteleyev
// SPDX-License-Identifier: BSD-2-Clause
package org.panteleyev.fx.factories;

import javafx.scene.Node;
import javafx.scene.control.Label;

/**
 * Provides factory methods to create instances of {@link Label}.
 * <p>
 * <strong>Example:</strong>
 * {@snippet :
 * var label = apply(label("Text"), lbl -> {
 *     lbl.setEllipsisString("...");
 *     lbl.setLineSpacing(1.2);
 * });
 *}
 */
public final class LabelFactory {
    private LabelFactory() {
    }

    /**
     * Creates new label.
     *
     * @param text label text
     * @return label
     */
    public static Label label(String text) {
        return new Label(text);
    }

    /**
     * Creates new label with graphic node.
     *
     * @param text    label text
     * @param graphic label graphic node
     * @return label
     */
    public static Label label(String text, Node graphic) {
        return new Label(text, graphic);
    }
}
