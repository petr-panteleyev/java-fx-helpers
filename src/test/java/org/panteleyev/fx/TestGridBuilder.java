/*
 Copyright © 2020-2022 Petr Panteleyev <petr@panteleyev.org>
 SPDX-License-Identifier: BSD-2-Clause
 */
package org.panteleyev.fx;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.panteleyev.fx.FxUtils.SKIP;
import static org.panteleyev.fx.grid.GridBuilder.gridCell;

public class TestGridBuilder {

    @Test
    public void testGridCellException() {
        assertThrows(IllegalArgumentException.class, () -> gridCell(SKIP, 1, 1));
    }
}
