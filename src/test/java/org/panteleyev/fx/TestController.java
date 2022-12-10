/*
 Copyright © 2020-2022 Petr Panteleyev <petr@panteleyev.org>
 SPDX-License-Identifier: BSD-2-Clause
 */
package org.panteleyev.fx;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ArrayBlockingQueue;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestController {
    @BeforeAll
    public static void setup() {
        new JFXPanel();
    }

    @Test
    public void testConstructorException() {
        assertThrows(NullPointerException.class, () -> new Controller(null, null));
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
