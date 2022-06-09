/*
 Copyright © 2020-2021 Petr Panteleyev <petr@panteleyev.org>
 SPDX-License-Identifier: BSD-2-Clause
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
