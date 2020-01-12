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
