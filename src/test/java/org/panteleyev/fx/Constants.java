/*
 Copyright (c) Petr Panteleyev. All rights reserved.
 Licensed under the BSD license. See LICENSE file in the project root for full license information.
 */
package org.panteleyev.fx;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.util.ResourceBundle;

interface Constants {
    ResourceBundle RB = ResourceBundle.getBundle("test");

    String TEST_LABEL = "test.label";
    String TEST_STRING = "Test string";
    EventHandler<ActionEvent> ACTION = x -> {};
}
