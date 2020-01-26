/*
 * Copyright (c) 2020, Petr Panteleyev <petr@panteleyev.org>
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

import javafx.scene.Node;
import javafx.scene.control.Label;
import java.util.ResourceBundle;

/**
 * This interface provides convenience methods to create objects of {@link javafx.scene.control.Label}.
 */
public interface LabelFactory {
    /**
     * Creates new label.
     *
     * @param text label text
     * @return label
     */
    static Label newLabel(String text) {
        return new Label(text);
    }

    /**
     * Creates new label.
     *
     * @param text    label text
     * @param graphic label graphic node
     * @return label
     */
    static Label newLabel(String text, Node graphic) {
        return new Label(text, graphic);
    }

    /**
     * Creates new label. Label text is loaded from the specified resource bundle.
     *
     * @param rb  resource bundle
     * @param key text key
     * @return label
     */
    static Label newLabel(ResourceBundle rb, String key) {
        return newLabel(rb.getString(key));
    }

    /**
     * Creates new label. This method adds suffix to string loaded from resource bundle.
     *
     * @param rb     resource bundle
     * @param key    text key
     * @param suffix label text suffix
     * @return label
     */
    static Label newLabel(ResourceBundle rb, String key, String suffix) {
        return new Label(rb.getString(key) + suffix);
    }

    /**
     * Creates new label. Label text is loaded from the specified resource bundle.
     *
     * @param rb   resource bundle
     * @param key  text key
     * @param node node for the label
     * @return label
     */
    static Label newLabel(ResourceBundle rb, String key, Node node) {
        return new Label(rb.getString(key), node);
    }

    /**
     * Creates new label. This method adds suffix to string loaded from resource bundle.
     *
     * @param rb     resource bundle
     * @param key    text key
     * @param suffix label text suffix
     * @param node   node for the label
     * @return label
     */
    static Label newLabel(ResourceBundle rb, String key, String suffix, Node node) {
        return new Label(rb.getString(key) + suffix, node);
    }
}
