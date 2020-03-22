package org.panteleyev.fx;

/*
 Copyright (c) Petr Panteleyev. All rights reserved.
 Licensed under the BSD license. See LICENSE file in the project root for full license information.
 */

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * Base class for UI controller.
 */
public class Controller {
    private Stage stage;

    private final String css;

    /**
     * Creates controller based on existing stage.
     *
     * @param stage stage, cannot be null
     * @param css   optional css resource path
     * @throws NullPointerException if stage is null
     */
    public Controller(Stage stage, String css) {
        Objects.requireNonNull(stage);

        this.stage = stage;
        this.css = css;
    }

    /**
     * Creates controller for which stage will be created later.
     *
     * @param css optional css resource path
     */
    public Controller(String css) {
        this.stage = null;
        this.css = css;
    }

    /**
     * Returns visibility status of the controller.
     *
     * @return visibility
     */
    public boolean isVisible() {
        return false;
    }

    public String getTitle() {
        return "";
    }

    /**
     * Returns stage associated with this controller.
     *
     * @return stage
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * Creates stage for given root node.
     * <p>
     * Controller reference is stored in the {@link Scene scene} object using
     * {@link Scene#setUserData(Object)} method.
     *
     * @param root root node
     */
    protected final void setupWindow(Parent root) {
        Scene scene = new Scene(root);
        scene.setUserData(this);

        if (stage == null) {
            stage = new Stage();
        }

        if (css != null) {
            scene.getStylesheets().add(css);
        }
        stage.setTitle(getTitle());
        stage.setScene(scene);
    }
}
