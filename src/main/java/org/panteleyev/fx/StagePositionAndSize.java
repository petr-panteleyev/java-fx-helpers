/*
 Copyright © 2021 Petr Panteleyev <petr@panteleyev.org>
 SPDX-License-Identifier: BSD-2-Clause
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
