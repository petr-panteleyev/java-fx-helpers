// Copyright © 2025 Petr Panteleyev
// SPDX-License-Identifier: BSD-2-Clause
package org.panteleyev.fx.factories.grid;

import javafx.scene.Node;
import javafx.scene.layout.RowConstraints;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Holds grid row attributes.
 */
public class GridRow {
    private final List<Node> nodes;
    private final RowConstraints rowConstraints;

    GridRow(List<Node> nodes, RowConstraints rowConstraints) {
        this.nodes = nodes;
        this.rowConstraints = rowConstraints;
    }

    List<Node> nodes() {
        return nodes;
    }

    RowConstraints rowConstraints() {
        return rowConstraints;
    }

    boolean empty() {
        return nodes.isEmpty();
    }

    /**
     * Creates {@link GridRow} instance with the list of nodes.
     *
     * @param nodes list of nodes to form the grid row
     * @return grid row
     * @throws NullPointerException if {@code nodes} is {@code null}
     */
    public static GridRow gridRow(List<Node> nodes) {
        return new GridRow(
                Objects.requireNonNull(nodes, "Nodes cannot be null"),
                new RowConstraints());
    }


    /**
     * Creates {@link GridRow} instance with the list of nodes.
     *
     * @param nodes list of nodes to form the grid row
     * @return grid row
     * @throws NullPointerException if {@code nodes} is {@code null}
     */
    public static GridRow gridRow(Node... nodes) {
        return new GridRow(Arrays.asList(nodes), new RowConstraints());
    }

    /**
     * Creates {@link GridRow} instance with the list of nodes and row constraints.
     *
     * @param nodes       list of nodes to form the grid row
     * @param constraints row constraints
     * @return grid row
     * @throws NullPointerException if {@code nodes} or `constraints` is {@code null}
     */
    public static GridRow gridRow(List<Node> nodes, RowConstraints constraints) {
        return new GridRow(
                Objects.requireNonNull(nodes, "Nodes cannot be null"),
                Objects.requireNonNull(constraints, "Row constraints cannot be null"));
    }
}
