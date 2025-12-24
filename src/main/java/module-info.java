// Copyright © 2020-2025 Petr Panteleyev
// SPDX-License-Identifier: BSD-2-Clause

import java.util.function.Consumer;

/**
 * Defines helper and factory classes for JavaFX.
 * <p>
 * The goal of this API is to provide more convenient way to create and configure JavaFX controls.
 * <p>
 * Most factory methods use the same combination of parameters as corresponding JavaFX constructors. Additional
 * configuration can be done fluently using {@link org.panteleyev.functional.Scope#apply(Object, Consumer)}.
 * <p>
 * For simple classes using this API does not necessarily reduce the code size however provides more fluent way of
 * constructing scene graph.
 * <p>
 * For more complex classes such as {@link javafx.scene.layout.GridPane} using corresponding
 * {@link org.panteleyev.fx.factories.grid.GridPaneFactory factory} can reduce code size by significant amount.
 * <p>
 * <strong>Example:</strong>
 * {@snippet lang = java:
 * // Plain JavaFX
 *  var button1 = new Button("Button 1");
 *  button1.setOnAction(_ -> handleEvent1());
 *  button1.setDefaultButton(true);
 *  var button2 = new Button("Button 2");
 *  button2.setOnAction(_ -> handleEvent2());
 *  node.getChildren().addAll(button1, button2);
 *
 *  // This API
 *  node.getChildren().addAll(
 *     apply(button("Button 1"), b -> {
 *         b.setOnAction(_ -> handleEvent1());
 *         b.setDefaultButton(true);
 *     }),
 *     apply(button("Button 2"), b -> b.setOnAction(_ -> handleEvent2()))
 *  );
 *}
 *
 * @see <a href="https://www.panteleyev.org/javadoc/java-functional/org.panteleyev.functional/module-summary.html">Java
 * Functional Extensions</a>
 */
module org.panteleyev.fx {
    requires transitive javafx.base;
    requires transitive javafx.graphics;
    requires transitive javafx.controls;

    requires org.panteleyev.functional;

    exports org.panteleyev.fx;
    exports org.panteleyev.fx.factories.grid;
    exports org.panteleyev.fx.factories;
}