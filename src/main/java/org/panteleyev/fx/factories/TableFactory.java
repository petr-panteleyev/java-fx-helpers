// Copyright © 2025 Petr Panteleyev
// SPDX-License-Identifier: BSD-2-Clause
package org.panteleyev.fx.factories;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;

import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Provides factory methods to create instances {@link TableColumn} and related classes.
 * <p>
 * Two types of columns are supported:
 * <ul>
 *     <li>value column: cell type {@code T} is different from {@link javafx.scene.control.TableView} type {@code S};</li>
 *     <li>string column: cell type is {@link String}</li>
 *     <li>object column:  cell type is the same as table type.</li>
 * </ul>
 * <p>
 * Value columns should be configured with converter function that performs {@code S -> T} conversion and optionally
 * cell factory in case default {@link Object#toString()} is not enough.
 * <p>
 * String columns require converter and usually don't need custom cell factory.
 * <p>
 * Object columns do not require converter function however they almost always require cell factory otherwise
 * {@link Object#toString()} will be used.
 * <p>
 * Column parameters not covered by this factory methods can be configured via {@link org.panteleyev.functional.Scope#apply(Object, Consumer)}.
 */
public final class TableFactory {

    /**
     * Implements {@link TableColumn} where cell type {@code T} differs from table type {@code S}.
     *
     * @param <S> The type of the {@link javafx.scene.control.TableView} generic type
     * @param <T> The type of the content in all cells in this {@link TableColumn}.
     */
    public static class TableValueColumn<S, T> extends TableColumn<S, T> {
        TableValueColumn() {
        }

        TableValueColumn(String text) {
            super(text);
        }

        /**
         * Sets conversion from table type {@code S} to cell type {@code T}.
         *
         * @param converter conversion function, ignored if {@code null}
         */
        public void valueConverter(Function<S, T> converter) {
            if (converter == null) return;
            setCellValueFactory(p -> new ReadOnlyObjectWrapper<>(converter.apply(p.getValue())));
        }

        public void widthBinding(ObservableValue<? extends Number> widthBinding) {
            TableFactory.setWidthBinding(this, widthBinding);
        }

        public void comparator(Comparator<T> comparator) {
            TableFactory.setComparator(this, comparator);
        }
    }

    /**
     * Implements {@link TableColumn} where cell type is {@link String}.
     *
     * @param <S> The type of the {@link javafx.scene.control.TableView} generic type
     */
    public static class TableStringColumn<S> extends TableColumn<S, String> {
        TableStringColumn() {
        }

        TableStringColumn(String text) {
            super(text);
        }

        /**
         * Sets conversion from table type {@code S} to {@link String}.
         *
         * @param converter conversion function, ignored if {@code null}
         */
        public void valueConverter(Function<S, String> converter) {
            if (converter == null) return;
            setCellValueFactory(p -> new ReadOnlyObjectWrapper<>(converter.apply(p.getValue())));
        }

        public void widthBinding(ObservableValue<? extends Number> widthBinding) {
            TableFactory.setWidthBinding(this, widthBinding);
        }

        public void comparator(Comparator<String> comparator) {
            TableFactory.setComparator(this, comparator);
        }
    }

    /**
     * Implements {@link TableColumn} where cell type is the same as table type.
     *
     * @param <S> The type of the {@link javafx.scene.control.TableView} generic type
     */
    public static class TableObjectColumn<S> extends TableColumn<S, S> {
        TableObjectColumn() {
            setCellValueFactory(p -> new ReadOnlyObjectWrapper<>(p.getValue()));
        }

        TableObjectColumn(String text) {
            super(text);
            setCellValueFactory(p -> new ReadOnlyObjectWrapper<>(p.getValue()));
        }

        public void widthBinding(ObservableValue<? extends Number> widthBinding) {
            TableFactory.setWidthBinding(this, widthBinding);
        }

        public void comparator(Comparator<S> comparator) {
            TableFactory.setComparator(this, comparator);
        }
    }

    //
    // TableValueColumn
    //

    /**
     * Creates {@link TableValueColumn}.
     *
     * @param <S> the type of the TableView generic type
     * @param <T> the type of the item contained within the Cell.
     * @return table column
     */
    public static <S, T> TableValueColumn<S, T> tableValueColumn() {
        return new TableValueColumn<>();
    }

    /**
     * Creates {@link TableValueColumn}.
     *
     * @param text column text
     * @param <S>  the type of the TableView generic type
     * @param <T>  the type of the item contained within the Cell.
     * @return table column
     */
    public static <S, T> TableValueColumn<S, T> tableValueColumn(String text) {
        return new TableValueColumn<>(text);
    }

    //
    // TableStringColumn
    //

    /**
     * Creates {@link TableStringColumn}.
     *
     * @param <S> the type of the TableView generic type
     * @return table column
     */
    public static <S> TableStringColumn<S> tableStringColumn() {
        return new TableStringColumn<>();
    }

    /**
     * Creates {@link TableStringColumn}.
     *
     * @param text column text
     * @param <S>  the type of the TableView generic type
     * @return table column
     */
    public static <S> TableStringColumn<S> tableStringColumn(String text) {
        return new TableStringColumn<>(text);
    }

    //
    // TableObjectColumn
    //

    /**
     * Creates {@link TableObjectColumn}.
     *
     * @param <S> the type of the TableView generic type, cell contains the same type
     * @return table column
     */
    public static <S> TableObjectColumn<S> tableObjectColumn() {
        return new TableObjectColumn<>();
    }

    /**
     * Creates table column whose cell contains same type as of TableView.
     *
     * @param text column text
     * @param <S>  the type of the TableView generic type, cell contains the same type
     * @return table column
     */
    public static <S> TableObjectColumn<S> tableObjectColumn(String text) {
        return new TableObjectColumn<>(text);
    }

    private static <S, T> void setWidthBinding(TableColumn<S, T> column,
            ObservableValue<? extends Number> widthBinding)
    {
        if (widthBinding != null) {
            column.prefWidthProperty().bind(widthBinding);
        }
    }

    private static <S, T> void setComparator(TableColumn<S, T> column, Comparator<T> comparator) {
        if (comparator == null) {
            column.setSortable(false);
        } else {
            column.setComparator(comparator);
            column.setSortable(true);
        }
    }

    private TableFactory() {
    }
}
