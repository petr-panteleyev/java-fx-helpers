/*
 Copyright © 2020 Petr Panteleyev <petr@panteleyev.org>
 SPDX-License-Identifier: BSD-2-Clause
 */
package org.panteleyev.fx;

import javafx.embed.swing.JFXPanel;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.panteleyev.fx.Constants.ACTION;
import static org.panteleyev.fx.Constants.RB;
import static org.panteleyev.fx.Constants.TEST_LABEL;
import static org.panteleyev.fx.Constants.TEST_STRING;
import static org.panteleyev.fx.FxUtils.fxString;
import static org.panteleyev.fx.MenuFactory.checkMenuItem;
import static org.panteleyev.fx.MenuFactory.menuItem;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class TestMenuFactory {
    @BeforeClass
    public static void setup() {
        new JFXPanel();
    }

    @Test
    public void testNewMenuItem() {
        var menuItem = menuItem(fxString(RB, TEST_LABEL), ACTION);
        assertEquals(menuItem.getText(), TEST_STRING);
        assertEquals(menuItem.getOnAction(), ACTION);

        var plainMenuItem = menuItem(TEST_LABEL, ACTION);
        assertEquals(plainMenuItem.getText(), TEST_LABEL);
        assertEquals(plainMenuItem.getOnAction(), ACTION);
    }

    @Test
    public void testNewCheckMenuItem() {
        var selected = checkMenuItem(fxString(RB, TEST_LABEL), true);
        assertEquals(selected.getText(), TEST_STRING);
        assertTrue(selected.isSelected());

        var notSelected = checkMenuItem(fxString(RB, TEST_LABEL), false);
        assertEquals(notSelected.getText(), TEST_STRING);
        assertFalse(notSelected.isSelected());
    }
}
