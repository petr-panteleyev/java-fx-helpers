// Copyright © 2025 Petr Panteleyev
// SPDX-License-Identifier: BSD-2-Clause
package org.panteleyev.fx.factories.grid;

import javafx.scene.layout.ColumnConstraints;

/**
 * Provides factory methods to create instances of {@link ColumnConstraints}.
 * <p>
 * <strong>Example:</strong>
 * {@snippet :
 * var constraints = apply(columnConstraints(100.0), c -> {
 *     c.setHalignment(HPos.CENTER);
 *     c.setFillWidth(true);
 *     c.setHgrow(Priority.ALWAYS);
 * });
 *}
 */
public final class ColumnConstraintsFactory {

    /**
     * Creates instance of {@link ColumnConstraints} with no properties set.
     *
     * @return {@link ColumnConstraints} instance
     */
    public static ColumnConstraints columnConstraints() {
        return new ColumnConstraints();
    }

    /**
     * Creates instance of {@link ColumnConstraints} with a fixed width.
     *
     * @return {@link ColumnConstraints} instance
     */
    public static ColumnConstraints columnConstraints(double width) {
        return new ColumnConstraints(width);
    }

    /**
     * Creates instance of {@link ColumnConstraints} with a fixed size range.
     *
     * @param minWidth  the minimum width
     * @param prefWidth the preferred width
     * @param maxWidth  the maximum width
     * @return {@link ColumnConstraints} instance
     */
    public static ColumnConstraints columnConstraints(double minWidth, double prefWidth, double maxWidth) {
        return new ColumnConstraints(minWidth, prefWidth, maxWidth);
    }

    private ColumnConstraintsFactory() {
    }
}
