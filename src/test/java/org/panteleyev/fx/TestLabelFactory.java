package org.panteleyev.fx;

/*
 Copyright (c) Petr Panteleyev. All rights reserved.
 Licensed under the BSD license. See LICENSE file in the project root for full license information.
 */

import javafx.embed.swing.JFXPanel;
import javafx.scene.control.Button;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.panteleyev.fx.Constants.RB;
import static org.panteleyev.fx.Constants.TEST_LABEL;
import static org.panteleyev.fx.Constants.TEST_STRING;
import static org.panteleyev.fx.LabelFactory.newLabel;
import static org.testng.Assert.assertEquals;

public class TestLabelFactory {
    @BeforeClass
    public static void setup() {
        new JFXPanel();
    }

    @Test
    public void testNewLabel() {
        var label = newLabel(RB, TEST_LABEL);
        assertEquals(label.getText(), TEST_STRING);

        var node = new Button("123");
        var labelWithNode = newLabel(RB, TEST_LABEL, node);
        assertEquals(labelWithNode.getText(), TEST_STRING);
        assertEquals(labelWithNode.getGraphic(), node);
    }
}
