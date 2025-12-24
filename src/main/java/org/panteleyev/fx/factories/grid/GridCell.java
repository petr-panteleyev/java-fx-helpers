// Copyright © 2020-2025 Petr Panteleyev
// SPDX-License-Identifier: BSD-2-Clause
package org.panteleyev.fx.factories.grid;

import javafx.scene.Node;
import javafx.scene.Parent;

import java.util.Objects;

import static org.panteleyev.fx.Controller.SKIP;

/**
 * Represents grid cell with column and row spans.
 */
public class GridCell extends Parent {
    private final Node node;
    private final int columnSpan;
    private final int rowSpan;

    GridCell(Node node, int columnSpan, int rowSpan) {
        this.node = node;
        this.columnSpan = columnSpan;
        this.rowSpan = rowSpan;
    }

    Node node() {
        return node;
    }

    int columnSpan() {
        return columnSpan;
    }

    int rowSpan() {
        return rowSpan;
    }

    /**
     * Creates special node that configures column and row span for a grid cell containing provided node.
     *
     * @param node       node that is placed into a grid cell
     * @param columnSpan the number of columns the node's layout area should span
     * @param rowSpan    the number of rows the node's layout area should span
     * @return grid cell node
     * @throws IllegalArgumentException if {@link org.panteleyev.fx.Controller#SKIP} is used as a node
     * @throws NullPointerException     if {@code node} is {@code null}
     */
    public static Node gridCell(Node node, int columnSpan, int rowSpan) {
        Objects.requireNonNull(node, "Node cannot be null");
        if (node == SKIP) {
            throw new IllegalArgumentException("SKIP special node cannot be used in gridCell()");
        }
        return new GridCell(node, columnSpan, rowSpan);
    }
}
