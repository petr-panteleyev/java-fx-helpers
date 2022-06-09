/*
 Copyright © 2020-2021 Petr Panteleyev <petr@panteleyev.org>
 SPDX-License-Identifier: BSD-2-Clause
 */
package org.panteleyev.fx;

import javafx.embed.swing.JFXPanel;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.UUID;

import static org.panteleyev.fx.FxFactory.textField;
import static org.testng.Assert.assertEquals;

public class TestFxFactory {
    @BeforeClass
    public static void setup() {
        new JFXPanel();
    }

    @Test
    public void testTextField() {
        var initialValue = UUID.randomUUID().toString();
        var prefColumnCount = 100;

        var textField = textField(initialValue, prefColumnCount);
        assertEquals(textField.getText(), initialValue);
        assertEquals(textField.getPrefColumnCount(), prefColumnCount);
    }
}
