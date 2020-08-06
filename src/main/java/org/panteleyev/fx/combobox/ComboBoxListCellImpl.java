/*
 Copyright (c) Petr Panteleyev. All rights reserved.
 Licensed under the BSD license. See LICENSE file in the project root for full license information.
 */
package org.panteleyev.fx.combobox;

import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.Objects;
import java.util.function.Function;

public class ComboBoxListCellImpl<T> extends ComboBoxListCell<T> {
    private final String defaultValue;
    private final Function<T, String> converter;
    private final Function<T, Image> toImageConverter;

    public ComboBoxListCellImpl(String defaultValue,
                                Function<T, String> converter,
                                Function<T, Image> toImageConverter)
    {
        Objects.requireNonNull(converter);
        Objects.requireNonNull(toImageConverter);

        this.defaultValue = defaultValue;
        this.converter = converter;
        this.toImageConverter = toImageConverter;
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
            setGraphic(image == null ? null : new ImageView(image));
        }
    }
}
