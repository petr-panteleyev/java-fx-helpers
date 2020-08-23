/*
 Copyright (c) Petr Panteleyev. All rights reserved.
 Licensed under the BSD license. See LICENSE file in the project root for full license information.
 */
package org.panteleyev.fx;

import org.testng.annotations.Test;
import static org.panteleyev.fx.grid.GridBuilder.SKIP;
import static org.panteleyev.fx.grid.GridBuilder.gridCell;

public class TestGridBuilder {

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testGridCellException() {
        gridCell(SKIP, 1, 1);
    }
}
