/*
 Copyright (c) Petr Panteleyev. All rights reserved.
 Licensed under the BSD license. See LICENSE file in the project root for full license information.
 */
package org.panteleyev.fx;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableCell;
import javafx.scene.control.TreeTableColumn;
import javafx.util.Callback;

/**
 * This class provides convenience method to create objects of the following classes:
 * <ul>
 *     <li>{@link TreeTableColumn}</li>
 *     <li>{@link TreeItem}</li>
 * </ul>
 */
public final class TreeTableFactory {
    private TreeTableFactory() {
    }

    /**
     * Creates new tree table column.
     *
     * @param text             column text
     * @param cellFactory      cell factory
     * @param propertyCallback object property retriever
     * @param widthBinding     width property binding
     * @param <S>              the type of the TreeTableView generic type
     * @param <T>              the type of the item contained within the Cell.
     * @return table column
     */
    public static <S, T> TreeTableColumn<S, T> treeTableColumn(
        String text,
        Callback<TreeTableColumn<S, T>, TreeTableCell<S, T>> cellFactory,
        Callback<S, T> propertyCallback,
        ObservableValue<? extends Number> widthBinding)
    {
        var column = new TreeTableColumn<S, T>(text);
        column.setSortable(false);

        if (cellFactory != null) {
            column.setCellFactory(cellFactory);
        }

        if (propertyCallback != null) {
            column.setCellValueFactory(p -> new ReadOnlyObjectWrapper<>(
                propertyCallback.call(p.getValue().getValue())));
        }

        if (widthBinding != null) {
            column.prefWidthProperty().bind(widthBinding);
        }
        return column;
    }

    /**
     * Creates new tree table column.
     *
     * @param text         column text
     * @param cellFactory  cell factory
     * @param widthBinding width property binding
     * @param <S>          the type of the TreeTableView generic type
     * @return table column
     */
    public static <S> TreeTableColumn<S, S> treeTableColumn(
        String text,
        Callback<TreeTableColumn<S, S>, TreeTableCell<S, S>> cellFactory,
        ObservableValue<? extends Number> widthBinding)
    {
        var column = treeTableColumn(text, cellFactory, p -> p, widthBinding);
        column.setSortable(true);
        return column;
    }

    /**
     * Creates new tree item.
     *
     * @param expanded if tree item should be expanded
     * @param <T>      value type
     * @return tree item
     */
    public static <T> TreeItem<T> treeItem(boolean expanded) {
        var item = new TreeItem<T>();
        item.setExpanded(expanded);
        return item;
    }

    /**
     * Creates new tree item.
     *
     * @param value    item value
     * @param expanded if tree item should be expanded
     * @param <T>      value type
     * @return tree item
     */
    public static <T> TreeItem<T> treeItem(T value, boolean expanded) {
        var item = new TreeItem<T>(value);
        item.setExpanded(expanded);
        return item;
    }

    /**
     * Creates new tree item.
     *
     * @param value    item value
     * @param graphic  item graphic node
     * @param expanded if tree item should be expanded
     * @param <T>      value type
     * @return tree item
     */
    public static <T> TreeItem<T> treeItem(T value, Node graphic, boolean expanded) {
        var item = new TreeItem<T>(value, graphic);
        item.setExpanded(expanded);
        return item;
    }
}
