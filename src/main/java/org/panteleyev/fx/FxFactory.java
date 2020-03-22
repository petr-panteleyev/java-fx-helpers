package org.panteleyev.fx;

/*
 Copyright (c) Petr Panteleyev. All rights reserved.
 Licensed under the BSD license. See LICENSE file in the project root for full license information.
 */

import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import org.controlsfx.control.textfield.CustomTextField;
import org.controlsfx.control.textfield.TextFields;
import java.util.ResourceBundle;
import java.util.function.Consumer;

/**
 * This interface provides convenience methods to create and configure various JavaFX controls in one method call.
 * Most of the methods take resource bundle and text key as parameters for I18N purposes.
 */
public interface FxFactory {
    /**
     * Creates new search text field.
     *
     * @param valueCallback value callback
     * @return text field
     */
    static TextField newSearchField(Consumer<String> valueCallback) {
        return newSearchField(null, valueCallback);
    }

    /**
     * Creates new search text field.
     *
     * @param leftImage     image to put on the left side of the text field
     * @param valueCallback value callback
     * @return text field
     */
    static TextField newSearchField(Image leftImage, Consumer<String> valueCallback) {
        var searchField = TextFields.createClearableTextField();
        searchField.setPrefColumnCount(20);
        if (leftImage != null) {
            ((CustomTextField) searchField).setLeft(new ImageView(leftImage));
        }
        searchField.textProperty().addListener((x, y, newValue) -> valueCallback.accept(newValue));
        searchField.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ESCAPE) {
                searchField.clear();
            }
        });
        return searchField;
    }

    /**
     * Creates new check box.
     *
     * @param rb  resource bundle
     * @param key text key
     * @return check box
     */
    static CheckBox newCheckBox(ResourceBundle rb, String key) {
        return new CheckBox(rb.getString(key));
    }

    /**
     * Creates new tab.
     *
     * @param rb        resource bundle
     * @param key       text key
     * @param closeable closeable flag
     * @return tab
     */
    static Tab newTab(ResourceBundle rb, String key, boolean closeable) {
        var tab = new Tab(rb.getString(key));
        tab.setClosable(closeable);
        return tab;
    }

    /**
     * Creates new tab.
     *
     * @param rb        resource bundle
     * @param key       text key
     * @param closeable closeable flag
     * @param node      tab content
     * @return tab
     */
    static Tab newTab(ResourceBundle rb, String key, boolean closeable, Node node) {
        var tab = new Tab(rb.getString(key), node);
        tab.setClosable(closeable);
        return tab;
    }
}
