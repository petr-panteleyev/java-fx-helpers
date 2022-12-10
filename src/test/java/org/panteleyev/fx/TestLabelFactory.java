/*
 Copyright © 2020-2022 Petr Panteleyev <petr@panteleyev.org>
 SPDX-License-Identifier: BSD-2-Clause
 */
package org.panteleyev.fx;

import javafx.embed.swing.JFXPanel;
import javafx.scene.control.Button;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.panteleyev.fx.Constants.RB;
import static org.panteleyev.fx.Constants.TEST_LABEL;
import static org.panteleyev.fx.Constants.TEST_STRING;
import static org.panteleyev.fx.FxUtils.fxString;
import static org.panteleyev.fx.LabelFactory.label;

public class TestLabelFactory {
    @BeforeAll
    public static void setup() {
        new JFXPanel();
    }

    @Test
    public void testNewLabel() {
        var label = label(fxString(RB, TEST_LABEL));
        assertEquals(TEST_STRING, label.getText());

        var node = new Button("123");
        var labelWithNode = label(fxString(RB, TEST_LABEL), node);
        assertEquals(TEST_STRING, labelWithNode.getText());
        assertEquals(node, labelWithNode.getGraphic());
    }
}
