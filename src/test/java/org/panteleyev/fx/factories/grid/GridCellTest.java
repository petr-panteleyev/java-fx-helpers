// Copyright © 2020-2025 Petr Panteleyev
// SPDX-License-Identifier: BSD-2-Clause
package org.panteleyev.fx.factories.grid;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.panteleyev.fx.Controller.SKIP;
import static org.panteleyev.fx.factories.grid.GridCell.gridCell;

public class GridCellTest {

    @Test
    public void testGridCellException() {
        assertThrows(IllegalArgumentException.class, () -> gridCell(SKIP, 1, 1));
    }
}
