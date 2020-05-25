/*
 Copyright (c) Petr Panteleyev. All rights reserved.
 Licensed under the BSD license. See LICENSE file in the project root for full license information.
 */
package org.panteleyev.fx;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public interface BoxFactory {
    /**
     * Creates new HBox.
     *
     * @param alignment the overall alignment of children within the hbox's width and height
     * @param children  the initial set of children for this pane
     * @return HBox instance
     */
    static HBox newHBox(Pos alignment, Node... children) {
        var hBox = new HBox(children);
        hBox.setAlignment(alignment);
        return hBox;
    }

    /**
     * Creates new HBox.
     *
     * @param spacing   the amount of horizontal space between each child
     * @param alignment the overall alignment of children within the hbox's width and height
     * @param children  the initial set of children for this pane
     * @return HBox instance
     */
    static HBox newHBox(double spacing, Pos alignment, Node... children) {
        var hBox = new HBox(spacing, children);
        hBox.setAlignment(alignment);
        return hBox;
    }


    /**
     * Sets the horizontal grow priority for the specified children.
     *
     * @param priority horizontal grow priority
     * @param children children
     */
    static void setHGrow(Priority priority, Node... children) {
        for (var node : children) {
            HBox.setHgrow(node, priority);
        }
    }
}
