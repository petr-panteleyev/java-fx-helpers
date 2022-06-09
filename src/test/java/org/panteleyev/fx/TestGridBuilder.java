/*
 Copyright © 2020 Petr Panteleyev <petr@panteleyev.org>
 SPDX-License-Identifier: BSD-2-Clause
 */
package org.panteleyev.fx;

import org.testng.annotations.Test;

import static org.panteleyev.fx.FxUtils.SKIP;
import static org.panteleyev.fx.grid.GridBuilder.gridCell;

public class TestGridBuilder {

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testGridCellException() {
        gridCell(SKIP, 1, 1);
    }
}
