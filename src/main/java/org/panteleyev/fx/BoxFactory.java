/*
 Copyright (c) Petr Panteleyev. All rights reserved.
 Licensed under the BSD license. See LICENSE file in the project root for full license information.
 */
package org.panteleyev.fx;

import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import java.util.Arrays;
import java.util.Collection;
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
        var box = new HBox(spacing);
        addNodes(box, Arrays.asList(nodes));
        return box;
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
        addNodes(box, nodes);
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
        var box = new VBox(spacing);
        addNodes(box, Arrays.asList(nodes));
        return box;
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
        addNodes(box, nodes);
        setup.accept(box);
        return box;
    }

    private static void addNodes(Pane box, Collection<Node> nodes) {
        for (var node : nodes) {
            if (node != SKIP) {
                box.getChildren().add(node);
            }
        }
    }
}
