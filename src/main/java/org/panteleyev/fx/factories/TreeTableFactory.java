// Copyright © 2020-2025 Petr Panteleyev
// SPDX-License-Identifier: BSD-2-Clause
package org.panteleyev.fx.factories;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;

import java.util.Comparator;
import java.util.function.Function;

/**
 * Provides factory methods to create instances of {@link TreeTableColumn} and related classes.
 */
public final class TreeTableFactory {
    /**
     * Implements {@link TreeTableColumn} where cell type {@code T} differs from table type {@code S}.
     *
     * @param <S> The type of the {@link javafx.scene.control.TreeTableView} generic type
     * @param <T> The type of the content in all cells in this {@link TableColumn}.
     */
    public static class TreeTableValueColumn<S, T> extends TreeTableColumn<S, T> {
        TreeTableValueColumn() {
        }

        TreeTableValueColumn(String text) {
            super(text);
        }

        /**
         * Sets conversion from table type {@code S} to cell type {@code T}.
         *
         * @param converter conversion function, ignored if {@code null}
         */
        public void valueConverter(Function<S, T> converter) {
            if (converter == null) return;
            setCellValueFactory(p -> new ReadOnlyObjectWrapper<>(converter.apply(p.getValue().getValue())));
        }

        public void widthBinding(ObservableValue<? extends Number> widthBinding) {
            TreeTableFactory.setWidthBinding(this, widthBinding);
        }

        public void comparator(Comparator<T> comparator) {
            TreeTableFactory.setComparator(this, comparator);
        }
    }

    /**
     * Implements {@link TreeTableColumn} where cell type is {@link String}.
     *
     * @param <S> The type of the {@link javafx.scene.control.TreeTableView} generic type
     */
    public static class TreeTableStringColumn<S> extends TreeTableColumn<S, String> {
        TreeTableStringColumn() {
        }

        TreeTableStringColumn(String text) {
            super(text);
        }

        /**
         * Sets conversion from table type {@code S} to {@link String}.
         *
         * @param converter conversion function, ignored if {@code null}
         */
        public void valueConverter(Function<S, String> converter) {
            if (converter == null) return;
            setCellValueFactory(p -> new ReadOnlyObjectWrapper<>(converter.apply(p.getValue().getValue())));
        }

        public void widthBinding(ObservableValue<? extends Number> widthBinding) {
            TreeTableFactory.setWidthBinding(this, widthBinding);
        }

        public void comparator(Comparator<String> comparator) {
            TreeTableFactory.setComparator(this, comparator);
        }
    }

    /**
     * Implements {@link TreeTableColumn} where cell type is the same as table type.
     *
     * @param <S> The type of the {@link javafx.scene.control.TreeTableView} generic type
     */
    public static class TreeTableObjectColumn<S> extends TreeTableColumn<S, S> {
        TreeTableObjectColumn() {
            setCellValueFactory(p -> new ReadOnlyObjectWrapper<>(p.getValue().getValue()));
        }

        TreeTableObjectColumn(String text) {
            super(text);
            setCellValueFactory(p -> new ReadOnlyObjectWrapper<>(p.getValue().getValue()));
        }

        public void widthBinding(ObservableValue<? extends Number> widthBinding) {
            TreeTableFactory.setWidthBinding(this, widthBinding);
        }

        public void comparator(Comparator<S> comparator) {
            TreeTableFactory.setComparator(this, comparator);
        }
    }

    //
    // TreeTableValueColumn
    //

    /**
     * Creates {@link TreeTableValueColumn}.
     *
     * @param <S> the type of the TableView generic type
     * @param <T> the type of the item contained within the Cell.
     * @return table column
     */
    public static <S, T> TreeTableValueColumn<S, T> treeTableValueColumn() {
        return new TreeTableValueColumn<>();
    }

    /**
     * Creates {@link TreeTableValueColumn}.
     *
     * @param text column text
     * @param <S>  the type of the TableView generic type
     * @param <T>  the type of the item contained within the Cell.
     * @return table column
     */
    public static <S, T> TreeTableValueColumn<S, T> treeTableValueColumn(String text) {
        return new TreeTableValueColumn<>(text);
    }

    //
    // TreeTableStringColumn
    //

    /**
     * Creates {@link TreeTableStringColumn}.
     *
     * @param <S> the type of the TableView generic type
     * @return table column
     */
    public static <S> TreeTableStringColumn<S> treeTableStringColumn() {
        return new TreeTableStringColumn<>();
    }

    /**
     * Creates {@link TreeTableStringColumn}.
     *
     * @param text column text
     * @param <S>  the type of the TableView generic type
     * @return table column
     */
    public static <S> TreeTableStringColumn<S> treeTableStringColumn(String text) {
        return new TreeTableStringColumn<>(text);
    }

    //
    // TreeTableObjectColumn
    //

    /**
     * Creates {@link TreeTableObjectColumn}.
     *
     * @param <S> the type of the TableView generic type, cell contains the same type
     * @return table column
     */
    public static <S> TreeTableObjectColumn<S> treeTableObjectColumn() {
        return new TreeTableObjectColumn<>();
    }

    /**
     * Creates table column whose cell contains same type as of TableView.
     *
     * @param text column text
     * @param <S>  the type of the TableView generic type, cell contains the same type
     * @return table column
     */
    public static <S> TreeTableObjectColumn<S> treeTableObjectColumn(String text) {
        return new TreeTableObjectColumn<>(text);
    }

    //
    // TreeItem
    //

    /**
     * Creates an empty TreeItem.
     *
     * @param <T> The type of the value contained within the TreeItem.
     * @return tree item
     */
    public static <T> TreeItem<T> treeItem() {
        return new TreeItem<>();
    }

    /**
     * Creates a TreeItem with the value property set to the provided object.
     *
     * @param value The object to be stored as the value of this TreeItem.
     * @param <T>   The type of the value contained within the TreeItem.
     * @return tree item
     */
    public static <T> TreeItem<T> treeItem(T value) {
        return new TreeItem<>(value);
    }

    /**
     * Creates a TreeItem with the value property set to the provided object, and the graphic set to the provided Node.
     *
     * @param value   The object to be stored as the value of this TreeItem.
     * @param graphic The Node to show in the TreeView next to this TreeItem.
     * @param <T>     The type of the value contained within the TreeItem.
     * @return tree item
     */
    public static <T> TreeItem<T> treeItem(T value, Node graphic) {
        return new TreeItem<>(value, graphic);
    }

    private static <S, T> void setWidthBinding(TreeTableColumn<S, T> column,
            ObservableValue<? extends Number> widthBinding)
    {
        if (widthBinding != null) {
            column.prefWidthProperty().bind(widthBinding);
        }
    }

    private static <S, T> void setComparator(TreeTableColumn<S, T> column, Comparator<T> comparator) {
        if (comparator == null) {
            column.setSortable(false);
        } else {
            column.setComparator(comparator);
            column.setSortable(true);
        }
    }

    private TreeTableFactory() {
    }
}
