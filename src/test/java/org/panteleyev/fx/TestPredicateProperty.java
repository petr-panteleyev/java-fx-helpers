/*
 * Copyright (c) 2020, Petr Panteleyev <petr@panteleyev.org>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */

package org.panteleyev.fx;

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
