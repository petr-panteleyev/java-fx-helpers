/*
 Copyright © 2024 Petr Panteleyev <petr@panteleyev.org>
 SPDX-License-Identifier: BSD-2-Clause
 */
package org.panteleyev.fx.grid;

import javafx.geometry.HPos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.Priority;

import java.util.function.Consumer;

/**
 * This class implements builder that creates instances of {@link ColumnConstraints}.
 */
public class ColumnConstraintsBuilder {
    private final ColumnConstraints columnConstraints;

    private double percentWidth = -1;
    private HPos halignment = null;
    private Priority hGrow = null;
    private boolean fillWidth = true;

    /**
     * Creates instance of {@link ColumnConstraints} with no properties set.
     *
     * @param consumer configuration function
     * @return {@link ColumnConstraints} instance
     */
    public static ColumnConstraints columnConstraints(Consumer<ColumnConstraintsBuilder> consumer) {
        var builder = new ColumnConstraintsBuilder();
        consumer.accept(builder);
        return builder.build();
    }

    /**
     * Creates instance of {@link ColumnConstraints} with a fixed width.
     *
     * @param consumer configuration function
     * @return {@link ColumnConstraints} instance
     */
    public static ColumnConstraints columnConstraints(double width, Consumer<ColumnConstraintsBuilder> consumer) {
        var builder = new ColumnConstraintsBuilder(width);
        consumer.accept(builder);
        return builder.build();
    }

    /**
     * Creates instance of {@link ColumnConstraints} with a fixed size range.
     *
     * @param consumer configuration function
     * @return {@link ColumnConstraints} instance
     */
    public static ColumnConstraints columnConstraints(
            double minWidth,
            double prefWidth,
            double maxWidth,
            Consumer<ColumnConstraintsBuilder> consumer
    ) {
        var builder = new ColumnConstraintsBuilder(minWidth, prefWidth, maxWidth);
        consumer.accept(builder);
        return builder.build();
    }

    private ColumnConstraintsBuilder() {
        columnConstraints = new ColumnConstraints();
    }

    private ColumnConstraintsBuilder(double width) {
        columnConstraints = new ColumnConstraints(width);
    }

    private ColumnConstraintsBuilder(double minWidth, double prefWidth, double maxWidth) {
        columnConstraints = new ColumnConstraints(minWidth, prefWidth, maxWidth);
    }

    /**
     * Sets the width percentage of the column.
     *
     * @param percentWidth width percentage
     * @return this
     */
    public ColumnConstraintsBuilder withPercentWidth(double percentWidth) {
        this.percentWidth = percentWidth;
        return this;
    }

    /**
     * Sets the horizontal alignment for the column.
     *
     * @param halignment alignment
     * @return this
     */
    public ColumnConstraintsBuilder withHalignment(HPos halignment) {
        this.halignment = halignment;
        return this;
    }

    /**
     * Sets the horizontal grow priority for the column.
     *
     * @param hGrow priority
     * @return this
     */
    public ColumnConstraintsBuilder withHgrow(Priority hGrow) {
        this.hGrow = hGrow;
        return this;
    }

    /**
     * Sets the horizontal fill policy for the column.
     *
     * @param fillWidth fill policy
     * @return this
     */
    public ColumnConstraintsBuilder withFillWidth(boolean fillWidth) {
        this.fillWidth = fillWidth;
        return this;
    }

    private ColumnConstraints build() {
        columnConstraints.setPercentWidth(percentWidth);
        columnConstraints.setHalignment(halignment);
        columnConstraints.setHgrow(hGrow);
        columnConstraints.setFillWidth(fillWidth);
        return columnConstraints;
    }
}
