// Copyright © 2025 Petr Panteleyev
// SPDX-License-Identifier: BSD-2-Clause
package org.panteleyev.fx.factories;

import javafx.stage.FileChooser;

import java.util.List;
import java.util.Objects;

import static org.panteleyev.functional.Scope.apply;

/**
 * Provides factory methods to create instances of {@link FileChooser}.
 * <p>
 * <strong>Example:</strong>
 * {@snippet :
 * var fileChooser = apply(fileChooser("Open File", List.of()), chooser -> {
 *     chooser.setInitialDirectory(new File("/home/user"));
 *     chooser.setInitialFileName("file.txt");
 * });
 *}
 */
public final class FileChooserFactory {
    /**
     * Creates an instance of {@link FileChooser}.
     *
     * @param title            title
     * @param extensionFilters list of extension filters
     * @return new instance of {@link FileChooser}
     * @throws NullPointerException if {@code extensionFilters} is {@code null}
     */
    public static FileChooser fileChooser(String title, List<FileChooser.ExtensionFilter> extensionFilters) {
        Objects.requireNonNull(extensionFilters, "Extension filters cannot be null");

        return apply(new FileChooser(), chooser -> {
            chooser.setTitle(title);
            chooser.getExtensionFilters().addAll(extensionFilters);
        });
    }

    private FileChooserFactory() {
    }
}
