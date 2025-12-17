// Copyright © 2020-2025 Petr Panteleyev
// SPDX-License-Identifier: BSD-2-Clause
package org.panteleyev.fx;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PredicatePropertyTest {

    @Test
    public void testAnd() {
        var p1 = new PredicateProperty<Integer>(x -> x < 20);
        var p2 = new PredicateProperty<Integer>(x -> x > 10);

        var prop = PredicateProperty.and(List.of(p1, p2));

        assertTrue(prop.get().test(15));

        p2.set(x -> x > 30);
        assertFalse(prop.get().test(15));
    }

    public static List<Arguments> orDataProvider() {
        return List.of(
                Arguments.of(20, 30, 15, true),
                Arguments.of(20, 30, 40, true),
                Arguments.of(20, 30, 25, false)
        );
    }

    @ParameterizedTest
    @MethodSource("orDataProvider")
    public void testOr(int lower, int upper, int value, boolean expected) {
        var p1 = new PredicateProperty<Integer>(x -> x < lower);
        var p2 = new PredicateProperty<Integer>(x -> x > upper);

        var prop = PredicateProperty.or(List.of(p1, p2));

        assertEquals(expected, prop.test(value));
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
