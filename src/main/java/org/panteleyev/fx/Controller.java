/*
 * Copyright (c) 2015, 2020, Petr Panteleyev <petr@panteleyev.org>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
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
