/*
 Copyright © 2020-2022 Petr Panteleyev <petr@panteleyev.org>
 SPDX-License-Identifier: BSD-2-Clause
 */
package org.panteleyev.fx;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.ResourceBundle;

final class Constants {
    static final ResourceBundle RB = ResourceBundle.getBundle("test");

    static final String TEST_LABEL = "test.label";
    static final String TEST_STRING = "Test string";
    static final EventHandler<ActionEvent> ACTION = x -> {};

    private Constants() {
    }
}
