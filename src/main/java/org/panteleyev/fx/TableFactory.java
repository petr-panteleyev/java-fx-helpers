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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import java.util.Comparator;
import java.util.ResourceBundle;

/**
 * This interface provides convenience method to create objects of {@link TableColumn}.
 */
public interface TableFactory {
    /**
     * Creates new table column.
     *
     * @param text             column text
     * @param cellFactory      cell factory
     * @param propertyCallback object property retriever
     * @param widthBinding     width property binding
     * @param <S>              the type of the TableView generic type
     * @param <T>              the type of the item contained within the Cell.
     * @return table column
     */
    static <S, T> TableColumn<S, T> newTableColumn(
        String text,
        Callback<TableColumn<S, T>, TableCell<S, T>> cellFactory,
        Callback<S, T> propertyCallback,
        ObservableValue<? extends Number> widthBinding)
    {
        var column = new TableColumn<S, T>(text);
        column.setSortable(false);

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

    /**
     * Creates new table column.
     *
     * @param text             column text
     * @param cellFactory      cell factory
     * @param propertyCallback object property retriever
     * @param comparator       column comparator
     * @param widthBinding     width property binding
     * @param <S>              the type of the TableView generic type
     * @param <T>              the type of the item contained within the Cell.
     * @return table column
     */
    static <S, T> TableColumn<S, T> newTableColumn(
        String text,
        Callback<TableColumn<S, T>, TableCell<S, T>> cellFactory,
        Callback<S, T> propertyCallback,
        Comparator<T> comparator,
        ObservableValue<? extends Number> widthBinding)
    {
        var column = newTableColumn(text, cellFactory, propertyCallback, widthBinding);
        column.setComparator(comparator);
        column.setSortable(true);
        return column;
    }

    /**
     * Creates new table column.
     *
     * @param rb               resource bundle
     * @param key              column text key
     * @param cellFactory      cell factory
     * @param propertyCallback object property retriever
     * @param widthBinding     width property binding
     * @param <S>              the type of the TableView generic type
     * @param <T>              the type of the item contained within the Cell.
     * @return table column
     */
    static <S, T> TableColumn<S, T> newTableColumn(
        ResourceBundle rb,
        String key,
        Callback<TableColumn<S, T>, TableCell<S, T>> cellFactory,
        Callback<S, T> propertyCallback,
        ObservableValue<? extends Number> widthBinding)
    {
        return newTableColumn(rb.getString(key), cellFactory, propertyCallback, widthBinding);
    }

    /**
     * Creates new table column.
     *
     * @param rb               resource bundle
     * @param key              column text key
     * @param cellFactory      cell factory
     * @param propertyCallback object property retriever
     * @param comparator       column comparator
     * @param widthBinding     width property binding
     * @param <S>              the type of the TableView generic type
     * @param <T>              the type of the item contained within the Cell.
     * @return table column
     */
    static <S, T> TableColumn<S, T> newTableColumn(
        ResourceBundle rb,
        String key,
        Callback<TableColumn<S, T>, TableCell<S, T>> cellFactory,
        Callback<S, T> propertyCallback,
        Comparator<T> comparator,
        ObservableValue<? extends Number> widthBinding)
    {
        return newTableColumn(rb.getString(key), cellFactory, propertyCallback, comparator, widthBinding);
    }

    /**
     * Creates new table column.
     *
     * @param text         column text
     * @param cellFactory  cell factory
     * @param widthBinding width property binding
     * @param <S>          the type of the TableView generic type
     * @return table column
     */
    static <S> TableColumn<S, S> newTableColumn(
        String text,
        Callback<TableColumn<S, S>, TableCell<S, S>> cellFactory,
        ObservableValue<? extends Number> widthBinding)
    {
        return newTableColumn(text, cellFactory, p -> p, widthBinding);
    }

    /**
     * Creates new table column.
     *
     * @param rb           resource bundle
     * @param key          column text key
     * @param cellFactory  cell factory
     * @param widthBinding width property binding
     * @param <S>          the type of the TableView generic type
     * @return table column
     */
    static <S> TableColumn<S, S> newTableColumn(
        ResourceBundle rb,
        String key,
        Callback<TableColumn<S, S>, TableCell<S, S>> cellFactory,
        ObservableValue<? extends Number> widthBinding)
    {
        return newTableColumn(rb.getString(key), cellFactory, widthBinding);
    }

    /**
     * Creates new table column.
     *
     * @param text         column text
     * @param cellFactory  cell factory
     * @param comparator   column comparator
     * @param widthBinding width property binding
     * @param <S>          the type of the TableView generic type
     * @return table column
     */
    static <S> TableColumn<S, S> newTableColumn(
        String text,
        Callback<TableColumn<S, S>, TableCell<S, S>> cellFactory,
        Comparator<S> comparator,
        ObservableValue<? extends Number> widthBinding)
    {
        var column = newTableColumn(text, cellFactory, widthBinding);
        column.setComparator(comparator);
        column.setSortable(true);
        return column;
    }

    /**
     * Creates new table column.
     *
     * @param rb           resource bundle
     * @param key          column text key
     * @param cellFactory  cell factory
     * @param comparator   column comparator
     * @param widthBinding width property binding
     * @param <S>          the type of the TableView generic type
     * @return table column
     */
    static <S> TableColumn<S, S> newTableColumn(
        ResourceBundle rb,
        String key,
        Callback<TableColumn<S, S>, TableCell<S, S>> cellFactory,
        Comparator<S> comparator,
        ObservableValue<? extends Number> widthBinding)
    {
        return newTableColumn(rb.getString(key), cellFactory, comparator, widthBinding);
    }
}
