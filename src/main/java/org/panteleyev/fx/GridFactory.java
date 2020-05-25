/*
 Copyright (c) Petr Panteleyev. All rights reserved.
 Licensed under the BSD license. See LICENSE file in the project root for full license information.
 */
package org.panteleyev.fx;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import org.panteleyev.fx.impl.ColumnSpan;
import java.util.List;

public interface GridFactory {
    /**
     * Special node that represents empty grid cell.
     */
    Node EMPTY_NODE = new Node() {
    };

    /**
     * Creates new GridPane
     *
     * @param styles CSS styles
     * @return GridPane instance
     */
    static GridPane newGridPane(String... styles) {
        var gridPane = new GridPane();
        for (var style : styles) {
            gridPane.getStyleClass().add(style);
        }
        return gridPane;
    }

    /**
     * Sets specified column span for GridPane nodes.
     *
     * @param span  column span
     * @param nodes nodes
     */
    static void setColumnSpan(int span, Node... nodes) {
        if (span < 1) {
            throw new IllegalArgumentException("Column span must be > 0");
        }

        for (var node : nodes) {
            GridPane.setColumnSpan(node, span);
        }
    }

    /**
     * Sets column span for the GridPane child node.
     *
     * @param node       child node
     * @param columnSpan column span
     * @return special node to represent column span
     */
    static Node colSpan(Node node, int columnSpan) {
        return new ColumnSpan(node, columnSpan);
    }

    /**
     * Add rows to the GridPane. Each row is represented by the list of child nodes. Row and column indexes are
     * calculated automatically. To skip column a special node {@link #EMPTY_NODE} should be used. To set column span
     * for a node use {@link #colSpan(Node, int)} method.
     *
     * @param pane GridPane instance
     * @param rows list of rows
     */
    @SafeVarargs
    static void addRows(GridPane pane, List<Node>... rows) {
        int rowIndex = 0;

        for (var row : rows) {
            int columnIndex = 0;

            for (var node : row) {
                if (node instanceof ColumnSpan columnSpan) {
                    pane.add(columnSpan.getNode(), columnIndex, rowIndex, columnSpan.getSpan(), 1);
                    columnIndex += columnSpan.getSpan();
                } else {
                    if (node != EMPTY_NODE) {
                        pane.add(node, columnIndex, rowIndex);
                    }
                    columnIndex++;
                }
            }

            rowIndex++;
        }
    }
}
