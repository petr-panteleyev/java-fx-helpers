/*
 * Copyright (c) 2020, Petr Panteleyev <petr@panteleyev.org>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */

package org.panteleyev.fx;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import java.util.ResourceBundle;

/**
 * This interface provides convenience methods to create buttons.
 */
public interface ButtonFactory {
    static ButtonType newButtonType(String text, ButtonBar.ButtonData position) {
        return new ButtonType(text, position);
    }

    static ButtonType newButtonType(ResourceBundle rb, String key, ButtonBar.ButtonData position) {
        return newButtonType(rb.getString(key), position);
    }

    static Button newButton(ResourceBundle rb, String key) {
        return new Button(rb.getString(key));
    }

    /**
     * Creates new push button.
     *
     * @param text   button text
     * @param action button action
     * @return button
     */
    static Button newButton(String text, EventHandler<ActionEvent> action) {
        var button = new Button(text);
        button.setOnAction(action);
        return button;
    }

    /**
     * Creates new push button.
     *
     * @param rb     resource bundle
     * @param key    button text key
     * @param action button action
     * @return button
     */
    static Button newButton(ResourceBundle rb, String key, EventHandler<ActionEvent> action) {
        var button = new Button(rb.getString(key));
        button.setOnAction(action);
        return button;
    }

    /**
     * Creates new radio button.
     *
     * @param rb    resource bundle
     * @param key   button text key
     * @param group toggle group
     * @return button
     */
    static RadioButton newRadioButton(ResourceBundle rb, String key, ToggleGroup group) {
        var button = new RadioButton(rb.getString(key));
        button.setToggleGroup(group);
        return button;
    }

    /**
     * Creates new radio button.
     *
     * @param rb       resource bundle
     * @param key      button text key
     * @param group    toggle group
     * @param selected initial selected state
     * @return button
     */
    static RadioButton newRadioButton(ResourceBundle rb, String key, ToggleGroup group, boolean selected) {
        var button = newRadioButton(rb, key, group);
        button.setSelected(selected);
        return button;
    }
}
