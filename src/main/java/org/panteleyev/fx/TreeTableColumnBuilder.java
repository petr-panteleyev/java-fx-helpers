/*
 Copyright © 2024 Petr Panteleyev <petr@panteleyev.org>
 SPDX-License-Identifier: BSD-2-Clause
 */
package org.panteleyev.fx;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TreeTableCell;
import javafx.scene.control.TreeTableColumn;
import javafx.util.Callback;

import java.util.Comparator;
import java.util.function.Consumer;

/**
 * This class implements builder that creates instances of {@link TreeTableColumn}.
 *
 * @param <S> The type of the {@link TreeTableColumn} generic type (i.e. S == TreeTableView&lt;S>)
 * @param <T> The type of the content in all cells in this {@link TreeTableColumn}.
 */
public class TreeTableColumnBuilder<S, T> {
    private final String text;
    private Callback<TreeTableColumn<S, T>, TreeTableCell<S, T>> cellFactory;
    private Callback<S, T> propertyCallback;
    private ObservableValue<? extends Number> widthBinding;
    private Comparator<T> comparator;

    /**
     * Creates tree table column.
     *
     * @param text    column text
     * @param builder tree table column builder
     * @param <S>     the type of the {@link TreeTableColumn} generic type
     * @param <T>     the type of the item contained within the cell.
     * @return tree table column
     */
    public static <S, T> TreeTableColumn<S, T> treeTableColumn(String text, Consumer<TreeTableColumnBuilder<S, T>> builder) {
        var columnBuilder = new TreeTableColumnBuilder<S, T>(text);
        builder.accept(columnBuilder);
        return columnBuilder.build();
    }

    /**
     * Creates tree table column.
     *
     * @param text    column text
     * @param builder tree table column builder
     * @param <S>     the type of the {@link TreeTableColumn} generic type, cell contains the same type
     * @return tree table column
     */
    public static <S> TreeTableColumn<S, S> treeTableObjectColumn(String text, Consumer<TreeTableColumnBuilder<S, S>> builder) {
        return treeTableColumn(text, builder.andThen(b -> b.withPropertyCallback(p -> p)));
    }

    private TreeTableColumnBuilder(String text) {
        this.text = text;
    }

    /**
     * Sets comparator for the tree table column. Such column is also set as sortable.
     *
     * @param comparator column comparator
     * @return this
     */
    public TreeTableColumnBuilder<S, T> withComparator(Comparator<T> comparator) {
        this.comparator = comparator;
        return this;
    }

    /**
     * Sets cell factory for the tree table column. Refer to the {@link TreeTableColumn#setCellFactory(Callback)} for details.
     *
     * @param cellFactory cell factory
     * @return this
     */
    public TreeTableColumnBuilder<S, T> withCellFactory(Callback<TreeTableColumn<S, T>, TreeTableCell<S, T>> cellFactory) {
        this.cellFactory = cellFactory;
        return this;
    }

    /**
     * Sets property callback for the tree table column. This callback converts object of type {@code <S>} to the value
     * of type {@code <T>} that is shown in the tree table cell.
     *
     * @param propertyCallback property callback
     * @return this
     */
    public TreeTableColumnBuilder<S, T> withPropertyCallback(Callback<S, T> propertyCallback) {
        this.propertyCallback = propertyCallback;
        return this;
    }

    /**
     * <p>
     * Sets binding for {@link TreeTableColumn#widthProperty()}. This can be used to set column width as percentage of
     * tree table view width.
     * </p>
     * <p><b>Example:</b></p>
     * {@snippet :
     * var w = tableView.widthProperty();
     * var column = tableColumn("Text", b -> b.withWidthBinding(w.multiply(0.15)));
     *}
     *
     * @param widthBinding binding for widthProperty()
     * @return this
     */
    public TreeTableColumnBuilder<S, T> withWidthBinding(ObservableValue<? extends Number> widthBinding) {
        this.widthBinding = widthBinding;
        return this;
    }

    private TreeTableColumn<S, T> build() {
        var column = new TreeTableColumn<S, T>(text);

        if (comparator != null) {
            column.setComparator(comparator);
            column.setSortable(true);
        } else {
            column.setSortable(false);
        }

        if (cellFactory != null) {
            column.setCellFactory(cellFactory);
        }

        if (propertyCallback != null) {
            column.setCellValueFactory(p -> new ReadOnlyObjectWrapper<>(propertyCallback.call(p.getValue().getValue())));
        }

        if (widthBinding != null) {
            column.prefWidthProperty().bind(widthBinding);
        }
        return column;
    }
}
