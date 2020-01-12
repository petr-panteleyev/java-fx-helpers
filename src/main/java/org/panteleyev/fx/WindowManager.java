/*
 * Copyright (c) 2018, 2020, Petr Panteleyev <petr@panteleyev.org>
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

import javafx.collections.ObservableList;
import javafx.stage.Window;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This class implements window manager to manipulate active {@link Controller controllers} opened by the application.
 */
public final class WindowManager {
    private final Supplier<ObservableList<Window>> windowListSupplier;

    private WindowManager(Supplier<ObservableList<Window>> windowListSupplier) {
        this.windowListSupplier = windowListSupplier;
    }

    public static WindowManager newInstance() {
        return new WindowManager(Window::getWindows);
    }

    static WindowManager newInstance(Supplier<ObservableList<Window>> windowListSupplier) {
        return new WindowManager(windowListSupplier);
    }

    /**
     * Finds first active controller of the specified class.
     *
     * @param ctrlClass controller class
     * @return controller
     */
    public Optional<Controller> find(Class<? extends Controller> ctrlClass) {
        return getControllerStream(ctrlClass)
            .findFirst();
    }

    /**
     * Returns all active controllers.
     *
     * @return list of controllers
     */
    public List<? extends Controller> getControllers() {
        return getControllerStream().collect(Collectors.toList());
    }

    /**
     * Returns first active controller of the specified class that meets the criteria checked by enumerator.
     *
     * @param ctrlClass  controller class
     * @param enumerator enumerator
     * @return controller
     */
    public Optional<Controller> find(Class<? extends Controller> ctrlClass, Predicate<Controller> enumerator) {
        return getControllerStream(ctrlClass)
            .filter(enumerator)
            .findFirst();
    }

    /**
     * Returns all active controllers as stream.
     *
     * @return stream of controllers
     */
    public Stream<Controller> getControllerStream() {
        return windowListSupplier.get().stream()
            .map(Window::getScene)
            .filter(w -> w.getUserData() != null && Controller.class.isAssignableFrom(w.getUserData().getClass()))
            .map(window -> (Controller) window.getUserData());
    }

    /**
     * Returns all active controllers of the specified class as stream.
     *
     * @param ctrlClass controller class
     * @return stream of controllers
     */
    public Stream<Controller> getControllerStream(Class<? extends Controller> ctrlClass) {
        return windowListSupplier.get().stream()
            .map(Window::getScene)
            .filter(w -> w.getUserData() != null && ctrlClass.equals(w.getUserData().getClass()))
            .map(window -> (Controller) window.getUserData());
    }
}
