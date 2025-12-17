// Copyright © 2025 Petr Panteleyev
// SPDX-License-Identifier: BSD-2-Clause
package org.panteleyev.fx.factories.grid;

import javafx.scene.layout.RowConstraints;

/**
 * This class implements factory methods for {@link RowConstraints}.
 * <p>
 * <strong>Example:</strong>
 * {@snippet :
 * var constraints = apply(rowConstraints(100.0), c -> {
 *     c.setValignment(VPos.CENTER);
 *     c.setFillHeight(true);
 *     c.setVgrow(Priority.ALWAYS);
 * });
 *}
 */
public class RowConstraintsFactory {
    /**
     * Creates instance of {@link RowConstraints} with no properties set.
     *
     * @return {@link RowConstraints} instance
     */
    public static RowConstraints rowConstraints() {
        return new RowConstraints();
    }

    /**
     * Creates instance of {@link RowConstraints} with a fixed height.
     *
     * @param height row height
     * @return {@link RowConstraints} instance
     */
    public static RowConstraints rowConstraints(double height) {
        return new RowConstraints(height);
    }

    /**
     * Creates instance of {@link RowConstraints} with a fixed size range.
     *
     * @param minHeight  the minimum height
     * @param prefHeight the preferred height
     * @param maxHeight  the maximum height
     * @return {@link RowConstraints} instance
     */
    public static RowConstraints rowConstraints(double minHeight, double prefHeight, double maxHeight) {
        return new RowConstraints(minHeight, prefHeight, maxHeight);
    }

    private RowConstraintsFactory() {
    }
}
