package org.panteleyev.fx;

/*
 Copyright (c) Petr Panteleyev. All rights reserved.
 Licensed under the BSD license. See LICENSE file in the project root for full license information.
 */

import javafx.embed.swing.JFXPanel;
import org.testng.annotations.BeforeClass;

public class TestFxFactory {
    @BeforeClass
    public static void setup() {
        new JFXPanel();
    }

}
