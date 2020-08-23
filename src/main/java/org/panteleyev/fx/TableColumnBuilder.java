/*
 Copyright (c) Petr Panteleyev. All rights reserved.
 Licensed under the BSD license. See LICENSE file in the project root for full license information.
 */
package org.panteleyev.fx;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import java.util.Comparator;
import java.util.function.Consumer;

/**
 * This class implements builder that creates instances of {@link TableColumn}.
 *
 * @param <S> The type of the TableView generic type (i.e. S == TableView&lt;S>)
 * @param <T> The type of the content in all cells in this TableColumn.
 */
public class TableColumnBuilder<S, T> {
    private final String text;
    private Callback<TableColumn<S, T>, TableCell<S, T>> cellFactory;
    private Callback<S, T> propertyCallback;
    private ObservableValue<? extends Number> widthBinding;
    private Comparator<T> comparator;

    /**
     * Creates table column.
     *
     * @param text    column text
     * @param builder table column builder
     * @param <S>     the type of the TableView generic type
     * @param <T>     the type of the item contained within the Cell.
     * @return table column
     */
    public static <S, T> TableColumn<S, T> tableColumn(String text, Consumer<TableColumnBuilder<S, T>> builder) {
        var columnBuilder = new TableColumnBuilder<S, T>(text);
        builder.accept(columnBuilder);
        return columnBuilder.build();
    }

    /**
     * Creates table column.
     *
     * @param text    column text
     * @param builder table column builder
     * @param <S>     the type of the TableView generic type, cell contains the same type
     * @return table column
     */
    public static <S> TableColumn<S, S> tableObjectColumn(String text, Consumer<TableColumnBuilder<S, S>> builder) {
        return tableColumn(text, builder.andThen(b -> b.withPropertyCallback(p -> p)));
    }

    private TableColumnBuilder(String text) {
        this.text = text;
    }

    /**
     * Sets comparator for the table column. Such column is also set as sortable.
     *
     * @param comparator column comparator
     * @return this
     */
    public TableColumnBuilder<S, T> withComparator(Comparator<T> comparator) {
        this.comparator = comparator;
        return this;
    }

    /**
     * Sets cell factory for the table column. Refer to the {@link TableColumn#setCellFactory(Callback)} for details.
     *
     * @param cellFactory cell factory
     * @return this
     */
    public TableColumnBuilder<S, T> withCellFactory(Callback<TableColumn<S, T>, TableCell<S, T>> cellFactory) {
        this.cellFactory = cellFactory;
        return this;
    }

    /**
     * Sets property callback for the table column. This callback converts object of type {@code <S>} to the value
     * of type {@code <T>} that is shown in the table cell.
     *
     * @param propertyCallback property callback
     * @return this
     */
    public TableColumnBuilder<S, T> withPropertyCallback(Callback<S, T> propertyCallback) {
        this.propertyCallback = propertyCallback;
        return this;
    }

    /**
     * <p>
     * Sets binding for {@link TableColumn#widthProperty()}. This can be used to set column width as percentage of
     * table view width.
     * </p>
     * <p><b>Example:</b></p>
     * <p>
     * <pre>
     * {@code
     * var w = tableView.widthProperty();
     * var column = tableColumn("Text", b -> b.withWidthBinding(w.multiply(0.15)));
     * }
     * </pre>
     * </p>
     *
     * @param widthBinding binding for widthProperty()
     * @return this
     */
    public TableColumnBuilder<S, T> withWidthBinding(ObservableValue<? extends Number> widthBinding) {
        this.widthBinding = widthBinding;
        return this;
    }

    private TableColumn<S, T> build() {
        var column = new TableColumn<S, T>(text);

        if (comparator != null) {
            column.setComparator(comparator);
            column.setSortable(true);
        }

        if (cellFactory != null) {
            column.setCellFactory(cellFactory);
        }

        if (propertyCallback != null) {
            column.setCellValueFactory(p -> new ReadOnlyObjectWrapper<>(propertyCallback.call(p.getValue())));
        }

        if (widthBinding != null) {
            column.prefWidthProperty().bind(widthBinding);
        }
        return column;
    }
}
