/*
 Copyright (c) Petr Panteleyev. All rights reserved.
 Licensed under the BSD license. See LICENSE file in the project root for full license information.
 */
package org.panteleyev.fx;

import javafx.scene.Node;
import javafx.scene.control.Label;

/**
 * This class provides convenience methods to create objects of {@link javafx.scene.control.Label}.
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
