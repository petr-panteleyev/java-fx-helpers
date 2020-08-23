/*
 Copyright (c) Petr Panteleyev. All rights reserved.
 Licensed under the BSD license. See LICENSE file in the project root for full license information.
 */
package org.panteleyev.fx;

import javafx.scene.Node;
import java.util.ResourceBundle;
import java.util.function.Consumer;

/**
 * This interface provides common utility methods.
 */
public interface FxUtils {

    /**
     * Convenience method to setup JavaFX node.
     *
     * @param node  node
     * @param setup setup consumer
     * @param <T>   type of the node
     * @return node
     */
    static <T extends Node> T fxNode(T node, Consumer<T> setup) {
        setup.accept(node);
        return node;
    }

    /**
     * Creates a new string using resource bundle and key.
     *
     * @param bundle resource bundle
     * @param key    key in resource bundle
     * @return string
     */
    static String fxString(ResourceBundle bundle, String key) {
        return bundle.getString(key);
    }

    /**
     * Creates a new string using resource bundle and key. Suffix is appended to the string retrieved from the
     * resource bundle.
     *
     * @param bundle resource bundle
     * @param key    key in resource bundle
     * @param suffix suffix that is appended to the string
     * @return string
     */
    static String fxString(ResourceBundle bundle, String key, String suffix) {
        return bundle.getString(key) + suffix;
    }

    /**
     * Creates a new string by appending suffix to the given string.
     *
     * @param str    string
     * @param suffix suffix that is appended to the string
     * @return string
     */
    static String fxString(String str, String suffix) {
        return str + suffix;
    }
}
