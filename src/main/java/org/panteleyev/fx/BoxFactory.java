/*
 Copyright (c) Petr Panteleyev. All rights reserved.
 Licensed under the BSD license. See LICENSE file in the project root for full license information.
 */
package org.panteleyev.fx;

import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import java.util.List;
import java.util.function.Consumer;
import static org.panteleyev.fx.FxUtils.SKIP;

/**
 * This interface provides methods to create instances of {@link HBox} and {@link VBox}.
 * This API respects special node value {@link FxUtils#SKIP}.
 */
public interface BoxFactory {
    static Consumer<Node> hBoxHGrow(Priority priority) {
        return node -> HBox.setHgrow(node, priority);
    }

    /**
     * Creates new {@link HBox}.
     *
     * @param spacing the amount of horizontal space between each node
     * @param nodes   the initial set of child nodes for this pane
     * @return HBox instance
     */
    static HBox hBox(double spacing, Node... nodes) {
        return new HBox(spacing, nodes);
    }

    /**
     * Creates new {@link VBox}.
     *
     * @param nodes the initial set of child nodes for this pane
     * @param setup setup function
     * @return HBox instance
     */
    static HBox hBox(List<Node> nodes, Consumer<HBox> setup) {
        var box = new HBox();
        nodes.stream()
            .filter(n -> n != SKIP)
            .forEach(n -> box.getChildren().add(n));
        setup.accept(box);
        return box;
    }

    /**
     * Creates new {@link VBox}.
     *
     * @param spacing the amount of vertical space between each child node
     * @param nodes   the initial set of children for this pane
     * @return VBox instance
     */
    static VBox vBox(double spacing, Node... nodes) {
        return new VBox(spacing, nodes);
    }

    /**
     * Creates new {@link VBox}.
     *
     * @param nodes the initial set of child nodes for this pane
     * @param setup setup function
     * @return HBox instance
     */
    static VBox vBox(List<Node> nodes, Consumer<VBox> setup) {
        var box = new VBox();
        nodes.stream()
            .filter(n -> n != SKIP)
            .forEach(n -> box.getChildren().add(n));
        setup.accept(box);
        return box;
    }
}
