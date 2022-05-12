package org.panteleyev.fx;

import javafx.scene.Node;
import javafx.scene.control.Tab;

public abstract class TabFactory {
    private TabFactory() {
    }

    /**
     * Creates new tab.
     *
     * @param title     tab title
     * @param closeable closeable flag
     * @param node      child node
     * @return tab
     */
    public static Tab tab(String title, boolean closeable, Node node) {
        var tab = new Tab(title, node);
        tab.setClosable(closeable);
        return tab;
    }

    /**
     * Creates new non-closeable tab.
     *
     * @param title     tab title
     * @param node      child node
     * @return tab
     */
    public static Tab tab(String title, Node node) {
        return tab(title, false, node);
    }
}
