// Copyright © 2025 Petr Panteleyev
// SPDX-License-Identifier: BSD-2-Clause
package org.panteleyev.fx.factories;

import java.util.Objects;
import java.util.ResourceBundle;

/**
 * Provides factory methods to load {@link String strings} from {@link java.util.ResourceBundle resource bundles}.
 * <p>
 * Suffixes can be useful to reuse same string from resource bundle in different context. For example, word
 * &quot;Password&quot; can be used as is in dialog title and with &quot;:&quot; in label.
 * <p>
 * <strong>Example:</strong>
 * {@snippet lang = java:
 * dialog.setTitle(string(bundle, "password"));
 * // ...
 * var label = LabelFactory.label(string(bundle, "password", COLON));
 *}
 */
public final class StringFactory {
    /**
     * Suffix commonly used in menu item labels.
     */
    public static final String ELLIPSIS = "...";
    /**
     * Suffix commonly used in dialog labels attached to another control.
     */
    public static final String COLON = ":";

    /**
     * Loads string from resource bundle using provided key.
     *
     * @param bundle resource bundle
     * @param key    key in resource bundle
     * @return string
     * @throws NullPointerException               if {@code bundle} or {@code key} is {@code null}
     * @throws java.util.MissingResourceException if no string for the given key can be found
     * @throws ClassCastException                 if the object found for the given key is not a string
     */
    public static String string(ResourceBundle bundle, String key) {
        return Objects.requireNonNull(bundle, "Resource bundle cannot be null")
                .getString(Objects.requireNonNull(key, "Resource key cannot be null"));
    }

    /**
     * Loads string from resource bundle using provided key. Suffix is appended to the string retrieved from the
     * resource bundle.
     *
     * @param bundle resource bundle
     * @param key    key in resource bundle
     * @param suffix suffix that is appended to the string, ignored if {@code null}
     * @return string
     * @throws NullPointerException               if {@code bundle} or {@code key} is {@code null}
     * @throws java.util.MissingResourceException if no string for the given key can be found
     * @throws ClassCastException                 if the object found for the given key is not a string
     */
    public static String string(ResourceBundle bundle, String key, String suffix) {
        return string(bundle, key) + (suffix == null ? "" : suffix);
    }


    /**
     * Creates a new string by appending suffix to the given string. If {@code str} is {@code null} this method returns
     * {@code null} as well.
     *
     * @param str    string
     * @param suffix suffix that is appended to the string, ignored if {@code null}
     * @return string
     */
    public static String string(String str, String suffix) {
        if (str == null) return null;
        return str + (suffix == null ? "" : suffix);
    }

    private StringFactory() {
    }
}
