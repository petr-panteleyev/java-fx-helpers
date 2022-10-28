/*
 Copyright © 2020-2022 Petr Panteleyev <petr@panteleyev.org>
 SPDX-License-Identifier: BSD-2-Clause
 */
package org.panteleyev.fx.combobox;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Dimension2D;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.util.Callback;

import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * This class implements builder that creates instances of {@link ComboBox}. Builder configuration is done using
 * user defined consumer. Example:
 * {@snippet :
 * var comboBox = comboBox(listOf("1", "2", "3"), b -> {
 *      b.withDefaultValue("10");
 *      b.withHandler(event -> {});
 * });
 *}
 *
 * @param <T> type of the {@link ComboBox} items
 */
public class ComboBoxBuilder<T> {
    private final ObservableList<T> items;
    private String defaultString = "";
    private Function<T, String> stringConverter = Object::toString;
    private Function<T, Image> imageConverter = x -> null;
    private Dimension2D imageDimension = null;
    private EventHandler<ActionEvent> handler = event -> {};
    private Callback<ListView<T>, ListCell<T>> cellFactory;

    /**
     * Creates {@link ComboBox} instance.
     *
     * @param items   {@link ComboBox} items
     * @param builder builder function
     * @param <T>     type of items
     * @return {@link ComboBox} instance
     */
    public static <T> ComboBox<T> comboBox(List<T> items, Consumer<ComboBoxBuilder<T>> builder) {
        Objects.requireNonNull(builder, "builder cannot be null");

        var b = new ComboBoxBuilder<T>(items);
        builder.accept(b);
        return b.build();
    }

    /**
     * Creates {@link ComboBox} instance.
     *
     * @param items   {@link ComboBox} items
     * @param builder builder function
     * @param <T>     type of items
     * @return {@link ComboBox} instance
     */
    public static <T> ComboBox<T> comboBox(T[] items, Consumer<ComboBoxBuilder<T>> builder) {
        return comboBox(FXCollections.observableArrayList(items), builder);
    }

    /**
     * Creates {@link ComboBox} instance.
     *
     * @param items {@link ComboBox} items
     * @param <T>   type of items
     * @return {@link ComboBox} instance
     */
    public static <T> ComboBox<T> comboBox(List<T> items) {
        return comboBox(items, b -> {});
    }

    /**
     * Creates {@link ComboBox} instance.
     *
     * @param items {@link ComboBox} items
     * @param <T>   type of items
     * @return {@link ComboBox} instance
     */
    public static <T> ComboBox<T> comboBox(T[] items) {
        return comboBox(items, b -> {});
    }

    private ComboBoxBuilder(List<T> items) {
        this.items = items instanceof ObservableList<T> observableList ?
                observableList : FXCollections.observableArrayList(items);
    }

    /**
     * Sets {@link ComboBox#onActionProperty()}.
     *
     * @param handler event handler
     * @return <code>this</code>
     */
    public ComboBoxBuilder<T> withHandler(EventHandler<ActionEvent> handler) {
        this.handler = handler;
        return this;
    }

    /**
     * Sets default string. This string will be shown for {@code null} items. If not set then an empty string will
     * be used.
     *
     * @param defaultString default string
     * @return <code>this</code>
     */
    public ComboBoxBuilder<T> withDefaultString(String defaultString) {
        this.defaultString = defaultString;
        return this;
    }

    /**
     * Sets converter that represents item as string. Default value is {@link Object#toString()}.
     *
     * @param stringConverter converter to string
     * @return <code>this</code>
     */
    public ComboBoxBuilder<T> withStringConverter(Function<T, String> stringConverter) {
        this.stringConverter = stringConverter;
        return this;
    }

    /**
     * Sets converter that represents item as {@link Image}. Default value is {@code x -> null}.
     *
     * @param imageConverter converter to image
     * @return <code>this</code>
     */
    public ComboBoxBuilder<T> withImageConverter(Function<T, Image> imageConverter) {
        this.imageConverter = imageConverter;
        return this;
    }

    /**
     * Sets image dimension for {@link ComboBoxBuilder#withImageConverter(Function)}.
     *
     * @param dimension image dimension
     * @return <code>this</code>
     */
    public ComboBoxBuilder<T> withImageDimension(Dimension2D dimension) {
        this.imageDimension = dimension;
        return this;
    }

    private ComboBox<T> build() {
        var cb = new ComboBoxImpl<T>(items, defaultString);
        cb.setOnAction(handler);

        if (cellFactory == null) {
            cellFactory = x -> new ComboBoxListCellImpl<>(defaultString, stringConverter, imageConverter,
                    imageDimension);
        }
        cb.setCellFactory(cellFactory);
        cb.setButtonCell(cellFactory.call(null));
        return cb;
    }

    /**
     * Clears selection and sets list value to <code>null</code>. For {@link ComboBox} instances created
     * with methods from this interface default value is rendered in the button cell, see
     * {@link ComboBox#getButtonCell()}.
     *
     * @param comboBoxes {@link ComboBox} instances
     */
    public static void clearValueAndSelection(ComboBox<?>... comboBoxes) {
        for (var cb : comboBoxes) {
            cb.setValue(null);
            cb.getSelectionModel().select(null);

            if (cb instanceof ComboBoxImpl<?> comboBox) {
                comboBox.renderDefaultValue();
            }
        }
    }
}
