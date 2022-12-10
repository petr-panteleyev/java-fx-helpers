/*
 Copyright © 2020-2022 Petr Panteleyev <petr@panteleyev.org>
 SPDX-License-Identifier: BSD-2-Clause
 */
package org.panteleyev.fx;

import javafx.embed.swing.JFXPanel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.panteleyev.fx.Constants.ACTION;
import static org.panteleyev.fx.Constants.RB;
import static org.panteleyev.fx.Constants.TEST_LABEL;
import static org.panteleyev.fx.Constants.TEST_STRING;
import static org.panteleyev.fx.FxUtils.fxString;
import static org.panteleyev.fx.MenuFactory.checkMenuItem;
import static org.panteleyev.fx.MenuFactory.menuItem;

public class TestMenuFactory {
    @BeforeAll
    public static void setup() {
        new JFXPanel();
    }

    @Test
    public void testNewMenuItem() {
        var menuItem = menuItem(fxString(RB, TEST_LABEL), ACTION);
        assertEquals(TEST_STRING, menuItem.getText());
        assertEquals(ACTION, menuItem.getOnAction());

        var plainMenuItem = menuItem(TEST_LABEL, ACTION);
        assertEquals(TEST_LABEL, plainMenuItem.getText());
        assertEquals(ACTION, plainMenuItem.getOnAction());
    }

    @Test
    public void testNewCheckMenuItem() {
        var selected = checkMenuItem(fxString(RB, TEST_LABEL), true);
        assertEquals(TEST_STRING, selected.getText());
        assertTrue(selected.isSelected());

        var notSelected = checkMenuItem(fxString(RB, TEST_LABEL), false);
        assertEquals(TEST_STRING, notSelected.getText());
        assertFalse(notSelected.isSelected());
    }
}
