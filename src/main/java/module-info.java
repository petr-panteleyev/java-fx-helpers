// Copyright © 2020-2025 Petr Panteleyev
// SPDX-License-Identifier: BSD-2-Clause

/**
 * Defines helper and factory classes for JavaFX.
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