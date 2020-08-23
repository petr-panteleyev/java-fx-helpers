/*
 Copyright (c) Petr Panteleyev. All rights reserved.
 Licensed under the BSD license. See LICENSE file in the project root for full license information.
 */
package org.panteleyev.fx.grid;

import javafx.scene.Node;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

/**
 * <p>
 * This class implements builder that creates instances of {@link GridPane}.
 * </p>
 * <p>
 * Configuration is done by rows. If some node requires column or/and row span configured then
 * {@link GridBuilder#gridCell(Node, int, int)} must be used instead of node itself.
 * </p>
 * <p>
 * Additional configuration for the grid pane can be done by passing {@code Consumer<GridBuilder>} to
 * {@link GridBuilder#gridPane(List, Consumer)}.
 * </p>
 * <p>
 * <b>Example:</b>
 * <p>
 * the following layout
 *     <table border=1>
 *         <tr><td>n_1_1</td><td>n_2_1</td><td>n_3_1</td></tr>
 *         <tr><td colspan='2'>n_1_2</td><td rowspan='2'>n_3_2</td></tr>
 *         <tr><td>n_1_3</td><td>n_2_3</td></tr>
 *     </table>
 * <p>
 * is configured by the code like this:
 * <p>
 * <pre>
 * {@code
 *  var gridPane = gridPane(
 *      List.of(
 *          gridRow(n_1_1, n_2_1, n_3_1),
 *          gridRow(gridCell(n_1_2, 2, 1), gridCell(n_3_2, 1, 2)),
 *          gridRow(n_1_3, n_2_3, SKIP)
 *      )
 *  );
 * }
 * </pre>
 */
public class GridBuilder {
    /**
     * Special node that represents empty grid cell.
     */
    public static final Node SKIP = new Node() {
    };

    private final List<GridRowBuilder> rows;
    private final Set<String> styles = new HashSet<>();
    private final List<ColumnConstraints> columnConstraints = new ArrayList<>();

    /**
     * Creates instance of {@link GridPane} with the specified rows.
     *
     * @param rows    list of rows
     * @param builder configuration function
     * @return {@link GridPane} instance
     */
    public static GridPane gridPane(List<GridRowBuilder> rows, Consumer<GridBuilder> builder) {
        var gridBuilder = new GridBuilder(rows);
        builder.accept(gridBuilder);
        return gridBuilder.build();
    }

    /**
     * Creates instance of {@link GridPane} with the specified rows.
     *
     * @param rows list of rows
     * @return {@link GridPane} instance
     */
    public static GridPane gridPane(List<GridRowBuilder> rows) {
        return new GridBuilder(rows).build();
    }

    private GridBuilder(List<GridRowBuilder> rows) {
        this.rows = rows;
    }

    /**
     * Adds CSS style to grid pane.
     *
     * @param style CSS style
     * @return this
     */
    public GridBuilder withStyle(String style) {
        this.styles.add(style);
        return this;
    }

    /**
     * Adds CSS styles to grid pane.
     *
     * @param styles CSS styles
     * @return this
     */
    public GridBuilder withStyles(String... styles) {
        this.styles.addAll(Arrays.asList(styles));
        return this;
    }

    /**
     * Adds CSS styles to grid pane.
     *
     * @param styles CSS styles
     * @return this
     */
    public GridBuilder withStyles(Collection<String> styles) {
        this.styles.addAll(styles);
        return this;
    }

    /**
     * Adds column constraints to grid pane.
     *
     * @param columnConstraints column constraints
     * @return this
     */
    public GridBuilder withConstraints(List<ColumnConstraints> columnConstraints) {
        this.columnConstraints.addAll(columnConstraints);
        return this;
    }

    /**
     * Adds column constraints to grid pane.
     *
     * @param columnConstraints column constraints
     * @return this
     */
    public GridBuilder withConstraints(ColumnConstraints... columnConstraints) {
        return withConstraints(Arrays.asList(columnConstraints));
    }

    private GridPane build() {
        var pane = new GridPane();
        pane.getStyleClass().addAll(styles);

        int rowIndex = 0;

        for (var row : rows) {
            if (row.isEmpty()) {
                continue;
            }

            int columnIndex = 0;

            for (var node : row.getNodes()) {
                if (node instanceof GridCell cell) {
                    pane.add(cell.getNode(), columnIndex, rowIndex, cell.getColumnSpan(), cell.getRowSpan());
                    columnIndex += cell.getColumnSpan();
                } else {
                    if (node != SKIP) {
                        pane.add(node, columnIndex, rowIndex);
                    }
                    columnIndex++;
                }
            }

            pane.getRowConstraints().add(row.getRowConstraints());
            rowIndex++;
        }

        if (!columnConstraints.isEmpty()) {
            pane.getColumnConstraints().addAll(columnConstraints);
        }

        return pane;
    }

    /**
     * Creates column constraints with the specified horizontal grow priority.
     *
     * @param hGrow horizontal grow priority
     * @return column constraints
     */
    public static ColumnConstraints columnConstraints(Priority hGrow) {
        var constraints = new ColumnConstraints();
        constraints.setHgrow(hGrow);
        return constraints;
    }

    /**
     * Creates special node that configures column and row span for a grid cell containing provided node.
     *
     * @param node       node that is placed into a grid cell
     * @param columnSpan column span
     * @param rowSpan    row span
     * @return grid cell node
     * @throws IllegalArgumentException if SKIP is used as a node
     */
    public static Node gridCell(Node node, int columnSpan, int rowSpan) {
        if (node == SKIP) {
            throw new IllegalArgumentException("SKIP special node cannot be used in gridCell()");
        }
        return new GridCell(node, columnSpan, rowSpan);
    }
}
