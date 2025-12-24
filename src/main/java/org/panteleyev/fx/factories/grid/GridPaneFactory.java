// Copyright © 2025 Petr Panteleyev
// SPDX-License-Identifier: BSD-2-Clause
package org.panteleyev.fx.factories.grid;

import javafx.scene.Node;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

import java.util.List;
import java.util.function.Consumer;

import static org.panteleyev.fx.Controller.SKIP;

/**
 * Provides factory methods to create instances of {@link GridPane}.
 * <p>
 * Configuration is done by rows. If some node requires column or/and row span configured then
 * {@link GridCell#gridCell(Node, int, int)} must be used instead of node itself.
 * <p>
 * Additional configuration for the grid pane can be done using
 * {@link org.panteleyev.functional.Scope#apply(Object, Consumer)}.
 * <p>
 * <strong>Example:</strong>
 * <p>
 * the following layout
 * <table border='1'>
 *     <tr><td>Label 1</td><td>Label 2</td><td>Label 3</td></tr>
 *     <tr><td colspan='2'>Label 4</td><td rowspan='2'>Label 5</td></tr>
 *     <tr><td>Label 6</td><td>Label 7</td></tr>
 * </table>
 * <p>
 * is configured by the code like this:
 * {@snippet :
 * var gridPane = gridPane(
 *     List.of(
 *         gridRow(label("Label 1"), label("Label 2"), label("Label 3")),
 *         gridRow(gridCell(label("Label 4"), 2, 1), gridCell(label("Label 5"), 1, 2)),
 *         gridRow(label("Label 6"), label("Label 7"), SKIP)
 *     )
 * );
 *}
 */
public final class GridPaneFactory {

    /**
     * Creates instance of {@link GridPane} with the specified rows.
     *
     * @param rows grid rows, ignored if {@code null}
     * @return {@link GridPane} instance
     */
    public static GridPane gridPane(List<GridRow> rows) {
        return build(rows, List.of(), List.of());
    }

    /**
     * Creates instance of {@link GridPane} with the specified rows and column constraints.
     *
     * @param rows              grid rows, ignored if {@code null}
     * @param columnConstraints column constraints, ignored if {@code null}
     * @return {@link GridPane} instance
     */
    public static GridPane gridPane(List<GridRow> rows, List<ColumnConstraints> columnConstraints) {
        return build(rows, columnConstraints, List.of());
    }

    /**
     * Creates instance of {@link GridPane} with the specified rows, column constraints and styles.
     *
     * @param rows              grid rows, ignored if {@code null}
     * @param columnConstraints column constraints, ignored if {@code null}
     * @param styles            CSS style classes, ignored if {@code null}
     * @return {@link GridPane} instance
     */
    public static GridPane gridPane(List<GridRow> rows, List<ColumnConstraints> columnConstraints,
            List<String> styles)
    {
        return build(rows, columnConstraints, styles);
    }

    private static GridPane build(List<GridRow> rows, List<ColumnConstraints> columnConstraints, List<String> styles) {
        var pane = new GridPane();
        if (styles != null && !styles.isEmpty()) {
            pane.getStyleClass().addAll(styles);
        }
        if (columnConstraints != null && !columnConstraints.isEmpty()) {
            pane.getColumnConstraints().addAll(columnConstraints);
        }
        if (rows == null || rows.isEmpty()) return pane;

        int rowIndex = 0;

        for (var row : rows) {
            if (row.empty()) {
                continue;
            }

            int columnIndex = 0;

            for (var node : row.nodes()) {
                if (node instanceof GridCell cell) {
                    pane.add(cell.node(), columnIndex, rowIndex, cell.columnSpan(), cell.rowSpan());
                    columnIndex += cell.columnSpan();
                } else {
                    if (node != SKIP) {
                        pane.add(node, columnIndex, rowIndex);
                    }
                    columnIndex++;
                }
            }

            pane.getRowConstraints().add(row.rowConstraints());
            rowIndex++;
        }

        return pane;
    }

    private GridPaneFactory() {
    }
}
