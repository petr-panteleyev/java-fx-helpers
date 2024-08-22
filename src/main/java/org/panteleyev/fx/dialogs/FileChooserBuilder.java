/*
 Copyright © 2024 Petr Panteleyev <petr@panteleyev.org>
 SPDX-License-Identifier: BSD-2-Clause
 */
package org.panteleyev.fx.dialogs;

import javafx.stage.FileChooser;

import java.io.File;
import java.util.List;
import java.util.function.Consumer;

/**
 * This class implements builder that creates instances of {@link FileChooser}.
 * <br>Builder configuration is done using user defined consumer.
 * <br>Example:
 * {@snippet :
 * var fileChooser = fileChooser("Open File", List.of(), b ->
 *     b.withInitialDirectory(new File("/home/user"))
 *         .withInitialFileName("file.txt"));
 *}
 */
public final class FileChooserBuilder {
    private final FileChooser fileChooser;

    private File initialDirectory = null;
    private String initialFileName = null;

    /**
     * Creates an instance of {@link FileChooser}.
     *
     * @param title            title
     * @param extensionFilters list of extension filters
     * @param builder          builder to set additional parameters
     * @return new instance of {@link FileChooser}
     */
    public static FileChooser fileChooser(
            String title,
            List<FileChooser.ExtensionFilter> extensionFilters,
            Consumer<FileChooserBuilder> builder
    ) {
        var chooser = new FileChooser();
        chooser.setTitle(title);
        chooser.getExtensionFilters().addAll(extensionFilters);

        var b = new FileChooserBuilder(chooser);
        builder.accept(b);
        return b.build();
    }

    /**
     * Creates an instance of {@link FileChooser}.
     *
     * @param title            title
     * @param extensionFilters list of extension filters
     * @return new instance of {@link FileChooser}
     */
    public static FileChooser fileChooser(String title, List<FileChooser.ExtensionFilter> extensionFilters) {
        return fileChooser(title, extensionFilters, b -> {});
    }

    private FileChooserBuilder(FileChooser fileChooser) {
        this.fileChooser = fileChooser;
    }

    private FileChooser build() {
        if (initialDirectory != null) {
            fileChooser.setInitialDirectory(initialDirectory);
        }

        if (initialFileName != null) {
            fileChooser.setInitialFileName(initialFileName);
        }

        return fileChooser;
    }

    /**
     * Sets the initial directory for the displayed file dialog.
     *
     * @param initialDirectory initial directory
     * @return <code>this</code>
     */
    public FileChooserBuilder withInitialDirectory(File initialDirectory) {
        this.initialDirectory = initialDirectory;
        return this;
    }

    /**
     * Sets the initial file name for the displayed dialog.
     *
     * @param initialFileName initial file name
     * @return <code>this</code>
     */
    public FileChooserBuilder withInitialFileName(String initialFileName) {
        this.initialFileName = initialFileName;
        return this;
    }
}
