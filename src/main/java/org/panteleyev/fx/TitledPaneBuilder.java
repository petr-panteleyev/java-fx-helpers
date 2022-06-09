/*
 Copyright © 2021 Petr Panteleyev <petr@panteleyev.org>
 SPDX-License-Identifier: BSD-2-Clause
 */
package org.panteleyev.fx;

import javafx.scene.Node;
import javafx.scene.control.TitledPane;

import java.util.function.Consumer;

/**
 * This class implements builder that creates instances of {@link TitledPane}.
 */
public class TitledPaneBuilder {
    private final String title;
    private final Node node;
    private boolean collapsible = false;
    private boolean animated = false;
    private boolean expanded = true;

    private TitledPaneBuilder(String title, Node node) {
        this.title = title;
        this.node = node;
    }

    /**
     * Specifies if the TitledPane can be collapsed. Default value is {@code false}.
     *
     * @param collapsible a flag indicating the collapsible state
     * @return this
     */
    public TitledPaneBuilder collapsible(boolean collapsible) {
        this.collapsible = collapsible;
        return this;
    }

    /**
     * Specifies if the TitledPane is animated. Default value is {@code true}.
     *
     * @param animated a flag indicating the animated state
     * @return this
     */
    public TitledPaneBuilder animated(boolean animated) {
        this.animated = animated;
        return this;
    }

    /**
     * Specified is the TitledPane is expanded. Default value is {@code true}.
     *
     * @param expanded a flag indicating the expanded state
     * @return this
     */
    public TitledPaneBuilder expanded(boolean expanded) {
        this.expanded = expanded;
        return this;
    }

    public TitledPane build() {
        var pane = new TitledPane(title, node);
        pane.setCollapsible(collapsible);
        pane.setAnimated(animated);
        pane.setExpanded(expanded);
        return pane;
    }

    /**
     * Creates instance of {@link TitledPane} with specified title and content.
     *
     * @param title pane title
     * @param node  content
     * @return titled pane
     */
    public static TitledPane titledPane(String title, Node node) {
        return new TitledPaneBuilder(title, node).build();
    }

    /**
     * Creates instance of {@link TitledPane} with specified title and content.
     *
     * @param title   pane title
     * @param node    content
     * @param builder configuration function
     * @return titled pane
     */
    public static TitledPane titledPane(String title, Node node, Consumer<TitledPaneBuilder> builder) {
        var paneBuilder = new TitledPaneBuilder(title, node);
        builder.accept(paneBuilder);
        return paneBuilder.build();
    }
}
