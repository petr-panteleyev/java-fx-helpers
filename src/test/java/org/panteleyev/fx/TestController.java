package org.panteleyev.fx;

/*
 Copyright (c) Petr Panteleyev. All rights reserved.
 Licensed under the BSD license. See LICENSE file in the project root for full license information.
 */

import org.testng.annotations.Test;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

public class TestController {

    @Test(expectedExceptions = NullPointerException.class)
    public void testConstructorException() {
        new Controller(null, null);
    }

    @Test
    public void testDefaultState() {
        var c = new Controller(null);
        assertFalse(c.isVisible());
        assertTrue(c.getTitle().isEmpty());
        assertNull(c.getStage());
    }
}
