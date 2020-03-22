package org.panteleyev.fx;

/*
 Copyright (c) Petr Panteleyev. All rights reserved.
 Licensed under the BSD license. See LICENSE file in the project root for full license information.
 */

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
