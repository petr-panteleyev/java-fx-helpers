/*
 Copyright © 2020-2022 Petr Panteleyev <petr@panteleyev.org>
 SPDX-License-Identifier: BSD-2-Clause
 */
package org.panteleyev.fx;

import javafx.embed.swing.JFXPanel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.panteleyev.fx.FxFactory.textField;

public class TestFxFactory {
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
