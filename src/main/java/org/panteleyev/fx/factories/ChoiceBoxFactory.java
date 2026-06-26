// Copyright © 2025-2026 Petr Panteleyev
// SPDX-License-Identifier: BSD-2-Clause
package org.panteleyev.fx.factories;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;

import java.util.Collection;
import java.util.Objects;

import static org.panteleyev.fx.hidden.Strings.ITEMS_NPE;

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
        Objects.requireNonNull(items, ITEMS_NPE);
        return new ChoiceBox<>(observableList(items));
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
        Objects.requireNonNull(items, ITEMS_NPE);
        return new ChoiceBox<>(FXCollections.observableArrayList(items));
    }

    private static <T> ObservableList<T> observableList(Collection<T> items) {
        return items instanceof ObservableList<T> observableList ?
                observableList : FXCollections.observableArrayList(items);
    }

    private ChoiceBoxFactory() {
    }
}
