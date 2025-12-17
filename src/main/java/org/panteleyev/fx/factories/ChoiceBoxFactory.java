// Copyright © 2025 Petr Panteleyev
// SPDX-License-Identifier: BSD-2-Clause
package org.panteleyev.fx.factories;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;

import java.util.Collection;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * Provides factory methods to create instances of {@link ChoiceBox}.
 * <p>
 * <strong>Example:</strong>
 * {@snippet :
 * var choiceBox = ChoiceBoxFactory.choiceBox(List.of(1, 2, 3), b -> {
 *     b.setVisible(false);
 *     b.setConverter(ReadOnlyStringConverter.from(x -> Integer.toString(x)));
 * });
 *}
 */
public final class ChoiceBoxFactory {

    /**
     * Creates {@link ChoiceBox} instance.
     *
     * @param items {@link ChoiceBox} items
     * @param <T>   type of items
     * @return {@link ChoiceBox} instance
     * @throws NullPointerException if {@code items} is {@code null}
     */
    public static <T> ChoiceBox<T> choiceBox(Collection<T> items) {
        Objects.requireNonNull(items, "Items cannot be null");
        return new ChoiceBox<>(observableList(items));
    }

    /**
     * Creates {@link ChoiceBox} instance.
     *
     * @param items      {@link ChoiceBox} items
     * @param configurer configuration function, ignored if {@code null}
     * @param <T>        type of items
     * @return {@link ChoiceBox}instance
     * @throws NullPointerException if {@code items} is {@code null}
     */
    public static <T> ChoiceBox<T> choiceBox(Collection<T> items, Consumer<ChoiceBox<T>> configurer) {
        Objects.requireNonNull(items, "Items cannot be null");

        var choiceBox = new ChoiceBox<>(observableList(items));
        if (configurer != null) {
            configurer.accept(choiceBox);
        }
        return choiceBox;
    }

    /**
     * Creates {@link ChoiceBox} instance.
     *
     * @param items {@link ChoiceBox} items
     * @param <T>   type of items
     * @return {@link ChoiceBox} instance
     * @throws NullPointerException if {@code items} is {@code null}
     */
    public static <T> ChoiceBox<T> choiceBox(T[] items) {
        Objects.requireNonNull(items, "Items cannot be null");
        return new ChoiceBox<>(FXCollections.observableArrayList(items));
    }

    /**
     * Creates {@link ChoiceBox} instance.
     *
     * @param items      {@link ChoiceBox} items
     * @param configurer configuration function, ignored if {@code null}
     * @param <T>        type of items
     * @return {@link ChoiceBox} instance
     * @throws NullPointerException if {@code items} is {@code null}
     */
    public static <T> ChoiceBox<T> choiceBox(T[] items, Consumer<ChoiceBox<T>> configurer) {
        Objects.requireNonNull(items, "Items cannot be null");
        return choiceBox(FXCollections.observableArrayList(items), configurer);
    }

    private static <T> ObservableList<T> observableList(Collection<T> items) {
        return items instanceof ObservableList<T> observableList ?
                observableList : FXCollections.observableArrayList(items);
    }

    private ChoiceBoxFactory() {
    }
}
