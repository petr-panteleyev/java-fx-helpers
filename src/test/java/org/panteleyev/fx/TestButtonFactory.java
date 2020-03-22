package org.panteleyev.fx;

/*
 Copyright (c) Petr Panteleyev. All rights reserved.
 Licensed under the BSD license. See LICENSE file in the project root for full license information.
 */

import javafx.embed.swing.JFXPanel;
import javafx.scene.control.ButtonBar;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.panteleyev.fx.ButtonFactory.newButtonType;
import static org.panteleyev.fx.Constants.RB;
import static org.panteleyev.fx.Constants.TEST_LABEL;
import static org.panteleyev.fx.Constants.TEST_STRING;
import static org.testng.Assert.assertEquals;

public class TestButtonFactory {
    @BeforeClass
    public static void setup() {
        new JFXPanel();
    }

    @Test
    public void testButtonType() {
        var bt = newButtonType("123", ButtonBar.ButtonData.HELP);
        assertEquals(bt.getText(), "123");
        assertEquals(bt.getButtonData(), ButtonBar.ButtonData.HELP);

        var btWithResource = newButtonType(RB, TEST_LABEL, ButtonBar.ButtonData.APPLY);
        assertEquals(btWithResource.getText(), TEST_STRING);
        assertEquals(btWithResource.getButtonData(), ButtonBar.ButtonData.APPLY);
    }
}
