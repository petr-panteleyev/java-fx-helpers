// Copyright © 2020-2025 Petr Panteleyev
// SPDX-License-Identifier: BSD-2-Clause
package org.panteleyev.fx.factories;

import javafx.embed.swing.JFXPanel;
import javafx.scene.control.ButtonBar;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.panteleyev.fx.Constants.RB;
import static org.panteleyev.fx.Constants.TEST_LABEL;
import static org.panteleyev.fx.Constants.TEST_STRING;
import static org.panteleyev.fx.factories.ButtonFactory.buttonType;
import static org.panteleyev.fx.factories.StringFactory.string;

public class ButtonFactoryTest {
    @BeforeAll
    public static void setup() {
        new JFXPanel();
    }

    @Test
    public void testButtonType() {
        var bt = buttonType("123", ButtonBar.ButtonData.HELP);
        assertEquals("123", bt.getText());
        assertEquals(ButtonBar.ButtonData.HELP, bt.getButtonData());

        var btWithResource = buttonType(string(RB, TEST_LABEL), ButtonBar.ButtonData.APPLY);
        assertEquals(TEST_STRING, btWithResource.getText());
        assertEquals(ButtonBar.ButtonData.APPLY, btWithResource.getButtonData());
    }
}
