/*
 Copyright © 2020-2021 Petr Panteleyev <petr@panteleyev.org>
 SPDX-License-Identifier: BSD-2-Clause
 */
package org.panteleyev.fx;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.ArrayBlockingQueue;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

public class TestController {
    @BeforeClass
    public static void setup() {
        new JFXPanel();
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testConstructorException() {
        new Controller(null, null);
    }

    @Test
    public void testDefaultState() throws Exception {
        var queue = new ArrayBlockingQueue<Controller>(1);

        Platform.runLater(() -> {
            var c = new Controller(null);
            queue.add(c);
        });

        var c = queue.take();
        assertFalse(c.isVisible());
        assertTrue(c.getTitle().isEmpty());
        assertNotNull(c.getStage());
    }
}
