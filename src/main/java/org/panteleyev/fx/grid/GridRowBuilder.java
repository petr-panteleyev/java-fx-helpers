/*
 Copyright (c) Petr Panteleyev. All rights reserved.
 Licensed under the BSD license. See LICENSE file in the project root for full license information.
 */
package org.panteleyev.fx.grid;

import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.layout.RowConstraints;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * This class implements grid row builder that is used by {@link GridBuilder#gridPane(List, Consumer)}.
 */
public class GridRowBuilder {
    private final List<Node> nodes;
    private RowConstraints rowConstraints = new RowConstraints();

    /**
     * Creates {@link GridRowBuilder} instance with the list of nodes.
     *
     * @param nodes list of nodes to form the grid row
     * @return row builder
     */
    public static GridRowBuilder gridRow(List<Node> nodes) {
        return new GridRowBuilder(nodes);
    }

    /**
     * Creates {@link GridRowBuilder} instance with nodes.
     *
     * @param nodes nodes to form the grid row
     * @return row builder
     */
    public static GridRowBuilder gridRow(Node... nodes) {
        return gridRow(Arrays.asList(nodes));
    }

    private GridRowBuilder(List<Node> nodes) {
        this.nodes = nodes;
    }

    boolean isEmpty() {
        return nodes == null || nodes.isEmpty();
    }

    /**
     * Adds row constraints to this grid row.
     *
     * @param rowConstraints row constraints
     * @return this
     */
    public GridRowBuilder withConstraints(RowConstraints rowConstraints) {
        this.rowConstraints = rowConstraints;
        return this;
    }

    /**
     * Adds vertical alignment to this row constraints.
     *
     * @param vPos vertical alignment
     * @return this
     */
    public GridRowBuilder withValignment(VPos vPos) {
        rowConstraints.setValignment(vPos);
        return this;
    }

    List<Node> getNodes() {
        return nodes;
    }

    RowConstraints getRowConstraints() {
        return rowConstraints;
    }
}
