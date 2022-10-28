/*
 Copyright © 2022 Petr Panteleyev <petr@panteleyev.org>
 SPDX-License-Identifier: BSD-2-Clause
 */
package org.panteleyev.fx.choicebox;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import org.panteleyev.fx.ReadOnlyStringConverter;

import java.util.Collection;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * This class implements builder that creates instances of {@link ChoiceBox}. Builder configuration is done using
 * user defined consumer. Example:
 * {@snippet :
 * var choiceBox = choiceBox(listOf("1", "2", "3"), b -> {
 *      b.withHandler(event -> {});
 * });
 *}
 *
 * @param <T> type of the {@link ComboBox} items
 */
public class ChoiceBoxBuilder<T> {
    private final ObservableList<T> items;
    private ReadOnlyStringConverter<T> stringConverter;

    private EventHandler<ActionEvent> handler = event -> {};

    private ChoiceBoxBuilder(Collection<T> items) {
        this.items = items instanceof ObservableList<T> observableList ?
                observableList : FXCollections.observableArrayList(items);
    }

    /**
     * Sets converter that represents item as string. Default value is {@link Object#toString()}.
     *
     * @param stringConverter converter to string
     * @return <code>this</code>
     */
    public ChoiceBoxBuilder<T> withStringConverter(ReadOnlyStringConverter<T> stringConverter) {
        this.stringConverter = Objects.requireNonNull(stringConverter, "String converter cannot be null");
        return this;
    }

    /**
     * Sets {@link ComboBox#onActionProperty()}.
     *
     * @param handler event handler
     * @return <code>this</code>
     */
    public ChoiceBoxBuilder<T> withHandler(EventHandler<ActionEvent> handler) {
        this.handler = Objects.requireNonNull(handler, "Event handler cannot be null");
        return this;
    }

    public ChoiceBox<T> build() {
        var choiceBox = new ChoiceBox<>(items);
        choiceBox.setOnAction(handler);
        if (stringConverter != null) {
            choiceBox.setConverter(stringConverter);
        }
        return choiceBox;
    }

    /**
     * Creates {@link ChoiceBox} instance.
     *
     * @param items   {@link ChoiceBox} items
     * @param builder builder function
     * @param <T>     type of items
     * @return {@link ChoiceBox} instance
     */
    public static <T> ChoiceBox<T> choiceBox(Collection<T> items, Consumer<ChoiceBoxBuilder<T>> builder) {
        Objects.requireNonNull(items, "Items cannot be null");
        Objects.requireNonNull(builder, "builder cannot be null");

        var b = new ChoiceBoxBuilder<>(items);
        builder.accept(b);
        return b.build();
    }

    /**
     * Creates {@link ChoiceBox} instance.
     *
     * @param items   {@link ChoiceBox} items
     * @param builder builder function
     * @param <T>     type of items
     * @return {@link ChoiceBox} instance
     */
    public static <T> ChoiceBox<T> choiceBox(T[] items, Consumer<ChoiceBoxBuilder<T>> builder) {
        return choiceBox(FXCollections.observableArrayList(items), builder);
    }
}
