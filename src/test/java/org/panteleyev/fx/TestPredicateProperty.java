package org.panteleyev.fx;

/*
 Copyright (c) Petr Panteleyev. All rights reserved.
 Licensed under the BSD license. See LICENSE file in the project root for full license information.
 */

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.List;
import java.util.UUID;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class TestPredicateProperty {

    @Test
    public void testAnd() {
        var p1 = new PredicateProperty<Integer>(x -> x < 20);
        var p2 = new PredicateProperty<Integer>(x -> x > 10);

        var prop = PredicateProperty.and(List.of(p1, p2));

        assertTrue(prop.get().test(15));

        p2.set(x -> x > 30);
        assertFalse(prop.get().test(15));
    }

    @DataProvider
    public Object[][] orDataProvider() {
        return new Object[][]{
            {20, 30, 15, true},
            {20, 30, 40, true},
            {20, 30, 25, false},
        };
    }

    @Test(dataProvider = "orDataProvider")
    public void testOr(int lower, int upper, int value, boolean result) {
        var p1 = new PredicateProperty<Integer>(x -> x < lower);
        var p2 = new PredicateProperty<Integer>(x -> x > upper);

        var prop = PredicateProperty.or(List.of(p1, p2));

        assertEquals(prop.test(value), result);
    }

    @Test
    public void testDefaultValue() {
        assertTrue(new PredicateProperty<UUID>().test(UUID.randomUUID()));
        assertTrue(new PredicateProperty<UUID>(this, "default").test(UUID.randomUUID()));
    }

    @Test
    public void testReset() {
        var p = new PredicateProperty<Integer>(x -> x < 10);
        assertFalse(p.test(20));

        p.reset();
        assertTrue(p.test(20));
    }
}
