/*
 Copyright (c) Petr Panteleyev. All rights reserved.
 Licensed under the BSD license. See LICENSE file in the project root for full license information.
 */
package org.panteleyev.fx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.util.Callback;
import org.panteleyev.fx.combobox.ComboBoxImpl;
import org.panteleyev.fx.combobox.ComboBoxListCellImpl;
import java.util.function.Function;

/**
 * This class implements builder that creates instances of {@link ComboBox}.
 *
 * @param <T> type of the {@link ComboBox} items
 */
public class ComboBoxBuilder<T> {
    private String defaultString = "";
    private Function<T, String> stringConverter = Object::toString;
    private Function<T, Image> imageConverter = x -> null;
    private EventHandler<ActionEvent> handler = event -> {};
    private ObservableList<T> items;
    private Callback<ListView<T>, ListCell<T>> cellFactory;

    public ComboBoxBuilder() {
    }

    /**
     * Sets {@link ComboBox} items from an array. Primary use case is <code>Enum</code> values.
     *
     * @param items {@link ComboBox} items
     */
    public ComboBoxBuilder(T[] items) {
        this.items = FXCollections.observableArrayList(items);
    }

    /**
     * Sets {@link ComboBox} items.
     *
     * @param items {@link ComboBox} items
     */
    public ComboBoxBuilder(ObservableList<T> items) {
        this.items = items;
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
     * Sets default string. This string will be shown for <code>null</code> items.
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
     * Sets converter that represents item as {@link Image}. Default value is <code>x -&gt; null</code>.
     *
     * @param imageConverter converter to image
     * @return <code>this</code>
     */
    public ComboBoxBuilder<T> withImageConverter(Function<T, Image> imageConverter) {
        this.imageConverter = imageConverter;
        return this;
    }

    /**
     * Builds {@link ComboBox} instance.
     *
     * @return {@link ComboBox} instance
     */
    public ComboBox<T> build() {
        var cb = new ComboBoxImpl<T>(items, defaultString);
        cb.setOnAction(handler);

        if (cellFactory == null) {
            cellFactory = x -> new ComboBoxListCellImpl<>(defaultString, stringConverter, imageConverter);
        }
        cb.setCellFactory(cellFactory);
        cb.setButtonCell(cellFactory.call(null));
        return cb;
    }
}
