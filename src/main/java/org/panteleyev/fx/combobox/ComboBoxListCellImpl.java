/*
 Copyright © 2020-2022 Petr Panteleyev <petr@panteleyev.org>
 SPDX-License-Identifier: BSD-2-Clause
 */
package org.panteleyev.fx.combobox;

import javafx.geometry.Dimension2D;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;
import java.util.function.Function;

class ComboBoxListCellImpl<T> extends ComboBoxListCell<T> {
    private final String defaultValue;
    private final Function<T, String> converter;
    private final Function<T, Image> toImageConverter;
    private final Dimension2D imageDimension;

    public ComboBoxListCellImpl(String defaultValue,
                                Function<T, String> converter,
                                Function<T, Image> toImageConverter,
                                Dimension2D imageDimension) {
        Objects.requireNonNull(converter);
        Objects.requireNonNull(toImageConverter);

        this.defaultValue = defaultValue;
        this.converter = converter;
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
            var image = toImageConverter.apply(value);
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
