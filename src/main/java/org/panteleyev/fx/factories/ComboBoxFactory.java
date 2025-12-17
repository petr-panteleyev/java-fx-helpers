// Copyright © 2025 Petr Panteleyev
// SPDX-License-Identifier: BSD-2-Clause
package org.panteleyev.fx.factories;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Dimension2D;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;

import static org.panteleyev.functional.Scope.apply;

/**
 * Provides factory methods to create instances of {@link ComboBox}.
 */
public final class ComboBoxFactory {
    static private class ComboBoxListCellImpl<T> extends ComboBoxListCell<T> {
        private final String defaultValue;
        private final Function<T, String> converter;
        private final Function<T, Image> toImageConverter;
        private final Dimension2D imageDimension;

        ComboBoxListCellImpl(String defaultValue,
                Function<T, String> converter,
                Function<T, Image> toImageConverter,
                Dimension2D imageDimension)
        {
            this.defaultValue = defaultValue;
            this.converter = converter != null ? converter : Object::toString;
            this.toImageConverter = toImageConverter;
            this.imageDimension = imageDimension;
        }

        public String getDefaultValue() {
            return defaultValue;
        }

        @Override
        public void updateItem(T value, boolean empty) {
            super.updateItem(value, empty);
            setText(empty || value == null ? getDefaultValue() : converter.apply(value));

            if (empty || value == null) {
                setGraphic(null);
            } else {
                var image = toImageConverter == null ? null : toImageConverter.apply(value);
                if (image != null) {
                    var view = new ImageView(image);
                    if (imageDimension != null) {
                        view.setFitWidth(imageDimension.getWidth());
                        view.setFitHeight(imageDimension.getHeight());
                    }
                    setGraphic(view);
                } else {
                    setGraphic(null);
                }
            }
        }
    }

    /**
     * Creates an instance of {code ComboBoxListCell}.
     *
     * @param defaultValue default value show when no item is selected
     * @param converter    function that converts T values to {@link String}
     * @param <T>          item type
     * @return {code ComboBoxListCell}
     */
    public static <T> ComboBoxListCell<T> comboBoxListCell(String defaultValue, Function<T, String> converter) {
        return new ComboBoxListCellImpl<>(defaultValue, converter, null, null);
    }

    /**
     * Creates an instance of {code ComboBoxListCell}.
     *
     * @param defaultValue     default string if {@code T} value is {@code null}
     * @param converter        function that converts {@code T} value to {@link String}
     * @param toImageConverter function that provides {@link Image} for {code T} value
     * @param imageDimension   optional dimension for {@link ImageView} that contains an image
     * @param <T>              item type
     * @return {code ComboBoxListCell}
     */
    public static <T> ComboBoxListCell<T> comboBoxListCell(String defaultValue,
            Function<T, String> converter, Function<T, Image> toImageConverter,
            Dimension2D imageDimension)
    {
        Objects.requireNonNull(toImageConverter, "To image converter cannot be null");
        return new ComboBoxListCellImpl<>(defaultValue, converter, toImageConverter, imageDimension);
    }

    /**
     * Creates {@code ComboBox} instance.
     *
     * @param items {@link ComboBox} items
     * @param <T>   type of items
     * @return {@link ComboBox} instance
     * @throws NullPointerException if {@code items} is {@code null}
     */
    public static <T> ComboBox<T> comboBox(List<T> items) {
        return new ComboBox<>(
                items(Objects.requireNonNull(items, "Items cannot be null"))
        );
    }

    /**
     * Creates {@code ComboBox} instance.
     *
     * @param items       {@link ComboBox} items
     * @param cellFactory cell factory
     * @param <T>         type of items
     * @return {@link ComboBox} instance
     * @throws NullPointerException if {@code items} or {@code cellFactory} is {@code null}
     */
    public static <T> ComboBox<T> comboBox(List<T> items, Callback<ListView<T>, ListCell<T>> cellFactory) {
        Objects.requireNonNull(items, "Items cannot be null");
        Objects.requireNonNull(cellFactory, "Cell factory cannot be null");

        return apply(new ComboBox<>(items(items)), cb -> {
            cb.setCellFactory(cellFactory);
            cb.setButtonCell(cellFactory.call(null));
        });
    }

    /**
     * Creates {@code ComboBox} instance.
     *
     * @param items {@code ComboBox} items
     * @param <T>   type of items
     * @return {@code ComboBox} instance
     * @throws NullPointerException if {@code items} is {@code null}
     */
    public static <T> ComboBox<T> comboBox(T[] items) {
        return new ComboBox<>(
                FXCollections.observableArrayList((Objects.requireNonNull(items, "Items cannot be null")))
        );
    }

    /**
     * Clears selection and sets list value to {@code null}. For {@link ComboBox} instances created with methods from
     * this factory default value is rendered in the button cell, see {@link ComboBox#getButtonCell()}.
     *
     * @param comboBoxes {@code ComboBox} instances
     * @throws NullPointerException if {@code comboBoxes} is {@code null}
     */
    public static void clearValueAndSelection(ComboBox<?>... comboBoxes) {
        Objects.requireNonNull(comboBoxes, "List of ComboBox instances cannot be null");
        for (var cb : comboBoxes) {
            cb.setValue(null);
            cb.getSelectionModel().select(null);

            if (cb.getButtonCell() instanceof ComboBoxListCellImpl<?> cell) {
                cell.setText(cell.getDefaultValue());
            }
        }
    }

    private static <T> ObservableList<T> items(List<T> items) {
        if (items instanceof ObservableList<T> observableList) {
            return observableList;
        } else {
            return FXCollections.observableArrayList(items);
        }
    }

    private ComboBoxFactory() {
    }
}
