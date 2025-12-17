// Copyright © 2020-2025 Petr Panteleyev
// SPDX-License-Identifier: BSD-2-Clause
package org.panteleyev.fx.factories;

import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static org.panteleyev.functional.Scope.apply;

/**
 * Provides factory methods to create instances of {@link TextField}.
 */
public final class TextFieldFactory {
    /**
     * Creates new search text field. {@link TextField} implementation is obtained via supplier to allow for custom
     * implementations like clearable text field from ControlsFX library.
     * <p>
     * Two properties are set for text field:
     * <ul>
     * <li>{@code setPrefColumnCount(20)}</li>
     * <li>change listener for {@link TextField#textProperty()} that calls provided {@code valueCallback}</li>
     * </ul>
     * <p>
     * Also {@link TextField#onKeyPressedProperty()} is set to a handler that clears text field on {@link KeyCode#ESCAPE}.
     *
     * @param fieldSupplier text field supplier
     * @param valueCallback value callback, ignored if {@code null}
     * @return text field
     * @throws NullPointerException if {@code fieldSupplier} is {@code null}
     */
    public static TextField searchField(Supplier<TextField> fieldSupplier, Consumer<String> valueCallback) {
        Objects.requireNonNull(fieldSupplier, "Field supplier cannot be null");

        var searchField = fieldSupplier.get();
        searchField.setPrefColumnCount(20);
        if (valueCallback != null) {
            searchField.textProperty().addListener((_, _, newValue) -> valueCallback.accept(newValue));
        }
        searchField.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ESCAPE) {
                searchField.clear();
            }
        });
        return searchField;
    }


    /**
     * Creates new text field.
     *
     * @return text field
     */
    public static TextField textField() {
        return new TextField();
    }

    /**
     * Creates new text field.
     *
     * @param prefColumnCount the preferred number of text columns
     * @return text field
     */
    public static TextField textField(int prefColumnCount) {
        return textField("", prefColumnCount);
    }

    /**
     * Creates new text field.
     *
     * @param initialValue    initial value
     * @param prefColumnCount the preferred number of text columns
     * @return text field
     */
    public static TextField textField(String initialValue, int prefColumnCount) {
        return apply(new TextField(initialValue), textField -> textField.setPrefColumnCount(prefColumnCount));
    }
}
