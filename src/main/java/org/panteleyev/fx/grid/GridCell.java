/*
 Copyright © 2020 Petr Panteleyev <petr@panteleyev.org>
 SPDX-License-Identifier: BSD-2-Clause
 */
package org.panteleyev.fx.grid;

import javafx.scene.Node;

class GridCell extends Node {
    private final Node node;
    private final int columnSpan;
    private final int rowSpan;

    public GridCell(Node node, int columnSpan, int rowSpan) {
        this.node = node;
        this.columnSpan = columnSpan;
        this.rowSpan = rowSpan;
    }

    public Node getNode() {
        return node;
    }

    public int getColumnSpan() {
        return columnSpan;
    }

    public int getRowSpan() {
        return rowSpan;
    }
}
