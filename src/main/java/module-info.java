/**
 * This module implements helper classes for JavaFX.
 */
module org.panteleyev.fx {
    requires transitive javafx.base;
    requires transitive javafx.graphics;
    requires transitive javafx.controls;

    requires javafx.swing;

    exports org.panteleyev.fx;
    exports org.panteleyev.fx.combobox;
    exports org.panteleyev.fx.grid;
}