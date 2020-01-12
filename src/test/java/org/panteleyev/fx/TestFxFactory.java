/*
 * Copyright (c) 2020, Petr Panteleyev <petr@panteleyev.org>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */

package org.panteleyev.fx;

import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.ResourceBundle;
import static org.panteleyev.fx.FxFactory.newCheckMenuItem;
import static org.panteleyev.fx.FxFactory.newLabel;
import static org.panteleyev.fx.FxFactory.newMenuItem;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class TestFxFactory {
    private static final ResourceBundle RB = ResourceBundle.getBundle("test");

    private static final String TEST_LABEL = "test.label";
    private static final String TEST_STRING = "Test string";
    private static final EventHandler<ActionEvent> ACTION = x -> {};


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
