// Copyright © 2025 Petr Panteleyev
// SPDX-License-Identifier: BSD-2-Clause
package org.panteleyev.fx.factories;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.argumentSet;
import static org.panteleyev.fx.Constants.RB;
import static org.panteleyev.fx.Constants.TEST_LABEL;
import static org.panteleyev.fx.Constants.TEST_STRING;
import static org.panteleyev.fx.factories.StringFactory.string;

public class StringFactoryTest {

    @Test
    public void testStringBundleAndKey() {
        assertEquals(TEST_STRING, string(RB, TEST_LABEL));
    }

    private static List<Arguments> testStringBundleKeyAndSuffixArguments() {
        return List.of(
                argumentSet("Suffix: null", TEST_LABEL, "", TEST_STRING),
                argumentSet("Suffix: empty", TEST_LABEL, "", TEST_STRING),
                argumentSet("Suffix: not empty", TEST_LABEL, "suffix", TEST_STRING + "suffix")
        );
    }

    @ParameterizedTest
    @MethodSource("testStringBundleKeyAndSuffixArguments")
    public void testStringBundleKeyAndSuffix(String key, String suffix, String expected) {
        assertEquals(expected, string(RB, key, suffix));
    }
}
