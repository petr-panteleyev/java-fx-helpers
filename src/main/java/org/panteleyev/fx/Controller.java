/*
 Copyright (c) Petr Panteleyev. All rights reserved.
 Licensed under the BSD license. See LICENSE file in the project root for full license information.
 */
package org.panteleyev.fx;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Objects;

/**
 * Base class for UI controller.
 */
public class Controller {
    private final Stage stage;
    private final String css;

    /**
     * Creates controller based on existing stage.
     *
     * @param stage stage, cannot be null
     * @param css   optional css resource path
     * @throws NullPointerException if stage is null
     */
    public Controller(Stage stage, String css) {
        this.stage = Objects.requireNonNull(stage);
        this.css = css;
    }

    /**
     * Creates controller with empty stage that has to be configured later.
     *
     * @param css optional css resource path
     */
    public Controller(String css) {
        this.stage = new Stage();
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
     * Configures stage for given root node.
     * <p>
     * Controller reference is stored in the {@link Scene scene} object using
     * {@link Scene#setUserData(Object)} method.
     *
     * @param root root node
     */
    protected final void setupWindow(Parent root) {
        var scene = new Scene(root);
        scene.setUserData(this);

        if (css != null) {
            scene.getStylesheets().add(css);
        }
        stage.setTitle(getTitle());
        stage.setScene(scene);

        stage.setOnHiding(event -> onWindowHiding());
    }

    /**
     * Actions performed on Hiding event.
     */
    protected void onWindowHiding() {
    }

    /**
     * Returns position and size of the controller stage.
     *
     * @return stage position and size
     */
    public StagePositionAndSize getStagePositionAndSize() {
        return new StagePositionAndSize(
            stage.getX(), stage.getY(),
            stage.getWidth(), stage.getHeight(),
            stage.isMaximized()
        );
    }

    /**
     * Sets position and size of the controller stage.
     *
     * @param positionAndSize stage position and size. Ignored if {@code null}.
     */
    public void setStagePositionAndSize(StagePositionAndSize positionAndSize) {
        if (positionAndSize == null) {
            return;
        }

        stage.setX(positionAndSize.x());
        stage.setY(positionAndSize.y());
        stage.setWidth(positionAndSize.width());
        stage.setHeight(positionAndSize.height());
        if (positionAndSize.maximized()) {
            stage.setMaximized(true);
        }
    }
}
