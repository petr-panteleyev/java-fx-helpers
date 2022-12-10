/*
 Copyright © 2020-2022 Petr Panteleyev <petr@panteleyev.org>
 SPDX-License-Identifier: BSD-2-Clause
 */
package org.panteleyev.fx;

import javafx.embed.swing.JFXPanel;
import javafx.scene.control.ButtonBar;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.panteleyev.fx.ButtonFactory.buttonType;
import static org.panteleyev.fx.Constants.RB;
import static org.panteleyev.fx.Constants.TEST_LABEL;
import static org.panteleyev.fx.Constants.TEST_STRING;
import static org.panteleyev.fx.FxUtils.fxString;

public class TestButtonFactory {
    @BeforeAll
    public static void setup() {
        new JFXPanel();
    }

    @Test
    public void testButtonType() {
        var bt = buttonType("123", ButtonBar.ButtonData.HELP);
        assertEquals("123", bt.getText());
        assertEquals(ButtonBar.ButtonData.HELP, bt.getButtonData());

        var btWithResource = buttonType(fxString(RB, TEST_LABEL), ButtonBar.ButtonData.APPLY);
        assertEquals(TEST_STRING, btWithResource.getText());
        assertEquals(ButtonBar.ButtonData.APPLY, btWithResource.getButtonData());
    }
}
