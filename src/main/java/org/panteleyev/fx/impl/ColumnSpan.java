/*
 Copyright (c) Petr Panteleyev. All rights reserved.
 Licensed under the BSD license. See LICENSE file in the project root for full license information.
 */
package org.panteleyev.fx.impl;

import javafx.scene.Node;

public class ColumnSpan extends Node {
    private final Node node;
    private final int span;

    public ColumnSpan(Node node, int span) {
        if (span < 1) {
            throw new IllegalArgumentException("Column span must be > 0");
        }

        this.span = span;
        this.node = node;
    }

    public Node getNode() {
        return node;
    }

    public int getSpan() {
        return span;
    }
}
