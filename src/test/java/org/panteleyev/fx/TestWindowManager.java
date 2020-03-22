package org.panteleyev.fx;

/*
 Copyright (c) Petr Panteleyev. All rights reserved.
 Licensed under the BSD license. See LICENSE file in the project root for full license information.
 */

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.embed.swing.JFXPanel;
import javafx.scene.layout.BorderPane;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.stream.Collectors;
import static org.testng.Assert.assertEquals;

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

    @BeforeClass
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
        assertEquals(windowManager.getControllers(), List.of(c1, c2, c3));
    }

    @Test
    public void testControllerStream() {
        assertEquals(windowManager.getControllerStream().collect(Collectors.toList()), List.of(c1, c2, c3));
    }

    @DataProvider
    public Object[][] findDataProvider() {
        return new Object[][]{
            {BaseTestController.class, c1},
            {ControllerWithPredicate.class, c2}
        };
    }

    @Test(dataProvider = "findDataProvider")
    public void testFind(Class<? extends Controller> ctrlClass, Controller expected) {
        var found = windowManager.find(ctrlClass);
        assertEquals(found.orElse(null), expected);
    }


    @DataProvider
    public Object[][] findWithPredicateDataProvider() {
        return new Object[][]{
            {BaseTestController.class, true, null},
            {BaseTestController.class, false, c1},
            {ControllerWithPredicate.class, false, c2},
            {ControllerWithPredicate.class, true, c3}
        };
    }

    @Test(dataProvider = "findWithPredicateDataProvider")
    public void testFindWithPredicate(Class<? extends BaseTestController> ctrlClass,
                                      boolean predicate,
                                      Controller expected)
    {
        var found = windowManager.find(ctrlClass, c -> ((BaseTestController) c).getFlag() == predicate);
        assertEquals(found.orElse(null), expected);
    }
}
