/*
 Copyright (c) Petr Panteleyev. All rights reserved.
 Licensed under the BSD license. See LICENSE file in the project root for full license information.
 */
package org.panteleyev.fx;

/**
 * This class represents position and size of the stage including maximized flag.
 */
public record StagePositionAndSize(
    double x, double y,
    double width, double height, boolean maximized
) {
}
