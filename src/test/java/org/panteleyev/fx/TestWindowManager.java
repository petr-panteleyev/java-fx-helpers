/*
 Copyright © 2020-2022 Petr Panteleyev <petr@panteleyev.org>
 SPDX-License-Identifier: BSD-2-Clause
 */
package org.panteleyev.fx;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.embed.swing.JFXPanel;
import javafx.scene.layout.BorderPane;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestWindowManager {
    static class BaseTestController extends Controller {
        public BaseTestController() {
            super(null);
            setupWindow(new BorderPane());
        }

        public boolean getFlag() {
            return false;
        }
    }

    static class ControllerWithPredicate extends BaseTestController {
        private final boolean flag;

        ControllerWithPredicate(boolean flag) {
            this.flag = flag;
        }

        @Override
        public boolean getFlag() {
            return flag;
        }
    }

    private static WindowManager windowManager;

    private static BaseTestController c1;
    private static ControllerWithPredicate c2;
    private static ControllerWithPredicate c3;

    @BeforeAll
    public static void setup() throws Exception {
        new JFXPanel();

        @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
        var queue = new ArrayBlockingQueue<Integer>(1);

        Platform.runLater(() -> {
            c1 = new BaseTestController();
            c2 = new ControllerWithPredicate(false);
            c3 = new ControllerWithPredicate(true);

            windowManager = WindowManager.newInstance(() ->
                    FXCollections.observableArrayList(
                            c1.getStage(),
                            c2.getStage(),
                            c3.getStage()
                    ));

            queue.add(1);
        });

        queue.take();
    }

    @Test
    public void testControllerList() {
        assertEquals(List.of(c1, c2, c3), windowManager.getControllers());
    }

    @Test
    public void testControllerStream() {
        assertEquals(List.of(c1, c2, c3), windowManager.getControllerStream().toList());
    }

    public static List<Arguments> findDataProvider() {
        return List.of(
                Arguments.of(BaseTestController.class, c1),
                Arguments.of(ControllerWithPredicate.class, c2)
        );
    }

    @ParameterizedTest
    @MethodSource("findDataProvider")
    public void testFind(Class<? extends Controller> ctrlClass, Controller expected) {
        var found = windowManager.find(ctrlClass);
        assertEquals(expected, found.orElse(null));
    }


    public static List<Arguments> findWithPredicateDataProvider() {
        return List.of(
                Arguments.of(BaseTestController.class, true, null),
                Arguments.of(BaseTestController.class, false, c1),
                Arguments.of(ControllerWithPredicate.class, false, c2),
                Arguments.of(ControllerWithPredicate.class, true, c3)
        );
    }

    @ParameterizedTest
    @MethodSource("findWithPredicateDataProvider")
    public void testFindWithPredicate(Class<? extends BaseTestController> ctrlClass,
                                      boolean predicate,
                                      Controller expected) {
        var found = windowManager.find(ctrlClass, c -> ((BaseTestController) c).getFlag() == predicate);
        assertEquals(expected, found.orElse(null));
    }
}
