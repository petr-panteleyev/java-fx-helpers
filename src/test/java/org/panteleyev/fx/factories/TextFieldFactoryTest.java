// Copyright © 2020-2025 Petr Panteleyev
// SPDX-License-Identifier: BSD-2-Clause
package org.panteleyev.fx.factories;

import javafx.embed.swing.JFXPanel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.panteleyev.fx.factories.TextFieldFactory.textField;

public class TextFieldFactoryTest {
    @BeforeAll
    public static void setup() {
        new JFXPanel();
    }

    @Test
    public void testTextField() {
        var initialValue = UUID.randomUUID().toString();
        var prefColumnCount = 100;

        var textField = textField(initialValue, prefColumnCount);
        assertEquals(initialValue, textField.getText());
        assertEquals(prefColumnCount, textField.getPrefColumnCount());
    }
}
