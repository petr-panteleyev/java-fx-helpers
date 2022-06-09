/*
 Copyright © 2020 Petr Panteleyev <petr@panteleyev.org>
 SPDX-License-Identifier: BSD-2-Clause
 */
package org.panteleyev.fx;

import javafx.embed.swing.JFXPanel;
import javafx.scene.control.ButtonBar;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.panteleyev.fx.ButtonFactory.buttonType;
import static org.panteleyev.fx.Constants.RB;
import static org.panteleyev.fx.Constants.TEST_LABEL;
import static org.panteleyev.fx.Constants.TEST_STRING;
import static org.panteleyev.fx.FxUtils.fxString;
import static org.testng.Assert.assertEquals;

public class TestButtonFactory {
    @BeforeClass
    public static void setup() {
        new JFXPanel();
    }

    @Test
    public void testButtonType() {
        var bt = buttonType("123", ButtonBar.ButtonData.HELP);
        assertEquals(bt.getText(), "123");
        assertEquals(bt.getButtonData(), ButtonBar.ButtonData.HELP);

        var btWithResource = buttonType(fxString(RB, TEST_LABEL), ButtonBar.ButtonData.APPLY);
        assertEquals(btWithResource.getText(), TEST_STRING);
        assertEquals(btWithResource.getButtonData(), ButtonBar.ButtonData.APPLY);
    }
}
