package org.panteleyev.fx;

/*
 Copyright (c) Petr Panteleyev. All rights reserved.
 Licensed under the BSD license. See LICENSE file in the project root for full license information.
 */

import javafx.embed.swing.JFXPanel;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.panteleyev.fx.Constants.ACTION;
import static org.panteleyev.fx.Constants.RB;
import static org.panteleyev.fx.Constants.TEST_LABEL;
import static org.panteleyev.fx.Constants.TEST_STRING;
import static org.panteleyev.fx.MenuFactory.newCheckMenuItem;
import static org.panteleyev.fx.MenuFactory.newMenuItem;
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
        var menuItem = newMenuItem(RB, TEST_LABEL, ACTION);
        assertEquals(menuItem.getText(), TEST_STRING);
        assertEquals(menuItem.getOnAction(), ACTION);

        var plainMenuItem = newMenuItem(TEST_LABEL, ACTION);
        assertEquals(plainMenuItem.getText(), TEST_LABEL);
        assertEquals(plainMenuItem.getOnAction(), ACTION);
    }

    @Test
    public void testNewCheckMenuItem() {
        var selected = newCheckMenuItem(RB, TEST_LABEL, true);
        assertEquals(selected.getText(), TEST_STRING);
        assertTrue(selected.isSelected());

        var notSelected = newCheckMenuItem(RB, TEST_LABEL, false);
        assertEquals(notSelected.getText(), TEST_STRING);
        assertFalse(notSelected.isSelected());
    }
}
