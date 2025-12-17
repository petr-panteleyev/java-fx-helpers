// Copyright © 2020-2025 Petr Panteleyev
// SPDX-License-Identifier: BSD-2-Clause
package org.panteleyev.fx.factories;

import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import static org.panteleyev.fx.Controller.SKIP;

/**
 * Provides methods to create instances of {@link HBox} and {@link VBox}. This API respects special node value
 * {@link org.panteleyev.fx.Controller#SKIP}.
 * <p>
 * <strong>Example:</strong>
 * {@snippet lang = java:
 * var hBox = BoxFactory.hBox(10.0, List.of(
 *     button("Button 1"),
 *     button("Button 2")
 * ));
 *}
 */
public final class BoxFactory {
    private BoxFactory() {
    }

    /**
     * Creates new {@link HBox}.
     *
     * @param spacing the amount of horizontal space between each node
     * @param nodes   the initial set of child nodes for this pane
     * @return {@link HBox} instance
     * @throws NullPointerException if {@code nodes} is {@code null}
     */
    public static HBox hBox(double spacing, Node... nodes) {
        Objects.requireNonNull(nodes, "Nodes cannot be null");
        var box = new HBox(spacing);
        addNodes(box, Arrays.asList(nodes));
        return box;
    }

    /**
     * Creates new {@link HBox}.
     *
     * @param spacing the amount of horizontal space between each node
     * @param nodes   the initial set of child nodes for this pane
     * @return {@link HBox} instance
     * @throws NullPointerException if {@code nodes} is {@code null}
     */
    public static HBox hBox(double spacing, List<Node> nodes) {
        Objects.requireNonNull(nodes, "Nodes cannot be null");
        var box = new HBox(spacing);
        addNodes(box, nodes);
        return box;
    }

    /**
     * Creates new {@link VBox}.
     *
     * @param spacing the amount of vertical space between each child node
     * @param nodes   the initial set of children for this pane
     * @return {@link VBox} instance
     * @throws NullPointerException if {@code nodes} is {@code null}
     */
    public static VBox vBox(double spacing, Node... nodes) {
        Objects.requireNonNull(nodes, "Nodes cannot be null");
        var box = new VBox(spacing);
        addNodes(box, Arrays.asList(nodes));
        return box;
    }

    /**
     * Creates new {@link VBox}.
     *
     * @param spacing the amount of vertical space between each child node
     * @param nodes   the initial set of children for this pane
     * @return {@link VBox} instance
     * @throws NullPointerException if {@code nodes} is {@code null}
     */
    public static VBox vBox(double spacing, List<Node> nodes) {
        Objects.requireNonNull(nodes, "Nodes cannot be null");
        var box = new VBox(spacing);
        addNodes(box, nodes);
        return box;
    }

    private static void addNodes(Pane box, Collection<Node> nodes) {
        for (var node : nodes) {
            if (node == SKIP) continue;
            box.getChildren().add(node);
        }
    }
}
