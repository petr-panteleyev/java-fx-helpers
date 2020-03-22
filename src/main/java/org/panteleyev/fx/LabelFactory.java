package org.panteleyev.fx;

/*
 Copyright (c) Petr Panteleyev. All rights reserved.
 Licensed under the BSD license. See LICENSE file in the project root for full license information.
 */

import javafx.scene.Node;
import javafx.scene.control.Label;
import java.util.ResourceBundle;

/**
 * This interface provides convenience methods to create objects of {@link javafx.scene.control.Label}.
 */
public interface LabelFactory {
    /**
     * Creates new label.
     *
     * @param text label text
     * @return label
     */
    static Label newLabel(String text) {
        return new Label(text);
    }

    /**
     * Creates new label.
     *
     * @param text    label text
     * @param graphic label graphic node
     * @return label
     */
    static Label newLabel(String text, Node graphic) {
        return new Label(text, graphic);
    }

    /**
     * Creates new label. Label text is loaded from the specified resource bundle.
     *
     * @param rb  resource bundle
     * @param key text key
     * @return label
     */
    static Label newLabel(ResourceBundle rb, String key) {
        return newLabel(rb.getString(key));
    }

    /**
     * Creates new label. This method adds suffix to string loaded from resource bundle.
     *
     * @param rb     resource bundle
     * @param key    text key
     * @param suffix label text suffix
     * @return label
     */
    static Label newLabel(ResourceBundle rb, String key, String suffix) {
        return new Label(rb.getString(key) + suffix);
    }

    /**
     * Creates new label. Label text is loaded from the specified resource bundle.
     *
     * @param rb   resource bundle
     * @param key  text key
     * @param node node for the label
     * @return label
     */
    static Label newLabel(ResourceBundle rb, String key, Node node) {
        return new Label(rb.getString(key), node);
    }

    /**
     * Creates new label. This method adds suffix to string loaded from resource bundle.
     *
     * @param rb     resource bundle
     * @param key    text key
     * @param suffix label text suffix
     * @param node   node for the label
     * @return label
     */
    static Label newLabel(ResourceBundle rb, String key, String suffix, Node node) {
        return new Label(rb.getString(key) + suffix, node);
    }
}
