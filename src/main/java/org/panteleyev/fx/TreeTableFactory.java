/*
 * Copyright (c) 2020, Petr Panteleyev <petr@panteleyev.org>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
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
 * This interface provides convenience method to create objects of the following classes:
 * <ul>
 *     <li>{@link TreeTableColumn}</li>
 *     <li>{@link TreeItem}</li>
 * </ul>
 */
public interface TreeTableFactory {
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
    static <S, T> TreeTableColumn<S, T> newTreeTableColumn(
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
    static <S> TreeTableColumn<S, S> newTreeTableColumn(
        String text,
        Callback<TreeTableColumn<S, S>, TreeTableCell<S, S>> cellFactory,
        ObservableValue<? extends Number> widthBinding)
    {
        var column = newTreeTableColumn(text, cellFactory, p -> p, widthBinding);
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
    static <T> TreeItem<T> newTreeItem(boolean expanded) {
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
    static <T> TreeItem<T> newTreeItem(T value, boolean expanded) {
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
    static <T> TreeItem<T> newTreeItem(T value, Node graphic, boolean expanded) {
        var item = new TreeItem<T>(value, graphic);
        item.setExpanded(expanded);
        return item;
    }
}
