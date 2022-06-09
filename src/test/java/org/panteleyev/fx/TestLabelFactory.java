/*
 Copyright © 2020 Petr Panteleyev <petr@panteleyev.org>
 SPDX-License-Identifier: BSD-2-Clause
 */
package org.panteleyev.fx;

import javafx.embed.swing.JFXPanel;
import javafx.scene.control.Button;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.panteleyev.fx.Constants.RB;
import static org.panteleyev.fx.Constants.TEST_LABEL;
import static org.panteleyev.fx.Constants.TEST_STRING;
import static org.panteleyev.fx.FxUtils.fxString;
import static org.panteleyev.fx.LabelFactory.label;
import static org.testng.Assert.assertEquals;

public class TestLabelFactory {
    @BeforeClass
    public static void setup() {
        new JFXPanel();
    }

    @Test
    public void testNewLabel() {
        var label = label(fxString(RB, TEST_LABEL));
        assertEquals(label.getText(), TEST_STRING);

        var node = new Button("123");
        var labelWithNode = label(fxString(RB, TEST_LABEL), node);
        assertEquals(labelWithNode.getText(), TEST_STRING);
        assertEquals(labelWithNode.getGraphic(), node);
    }
}
