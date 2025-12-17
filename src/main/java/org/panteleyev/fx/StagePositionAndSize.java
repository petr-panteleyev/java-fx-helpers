// Copyright © 2021-2025 Petr Panteleyev
// SPDX-License-Identifier: BSD-2-Clause
package org.panteleyev.fx;

/**
 * This class represents position and size of the stage including maximized flag.
 *
 * @param x x coordinate
 * @param y y coordinate
 * @param width width
 * @param height height
 * @param maximized {@code true} if stage is maximised, width and height are usually ignored in this case
 */
public record StagePositionAndSize(
        double x, double y,
        double width, double height, boolean maximized
) {
}
