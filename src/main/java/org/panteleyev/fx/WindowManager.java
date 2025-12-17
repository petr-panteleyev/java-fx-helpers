// Copyright © 2020-2025 Petr Panteleyev
// SPDX-License-Identifier: BSD-2-Clause
package org.panteleyev.fx;

import javafx.collections.ObservableList;
import javafx.stage.Window;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * This class implements window manager to enumerate active {@link Controller controllers} opened by the application.
 */
public final class WindowManager {
    private final Supplier<ObservableList<Window>> windowListSupplier;

    private WindowManager(Supplier<ObservableList<Window>> windowListSupplier) {
        this.windowListSupplier = windowListSupplier;
    }

    public static WindowManager windowManager() {
        return new WindowManager(Window::getWindows);
    }

    static WindowManager windowManager(Supplier<ObservableList<Window>> windowListSupplier) {
        return new WindowManager(windowListSupplier);
    }

    /**
     * Finds first active controller of the specified class.
     *
     * @param ctrlClass controller class
     * @return controller
     * @throws NullPointerException if {@code ctrlClass} is {@code null}
     */
    public Optional<Controller> find(Class<? extends Controller> ctrlClass) {
        Objects.requireNonNull(ctrlClass, "Controller class cannot be null");
        return getControllerStream(ctrlClass)
                .findFirst();
    }

    /**
     * Returns all active controllers.
     *
     * @return list of controllers
     */
    public List<? extends Controller> getControllers() {
        return getControllerStream().toList();
    }

    /**
     * Returns first active controller of the specified class that meets the criteria checked by enumerator.
     *
     * @param ctrlClass  controller class
     * @param enumerator enumerator
     * @return controller
     * @throws NullPointerException if {@code ctrlClass} or {@code enumerator} is {@code null}
     */
    public Optional<Controller> find(Class<? extends Controller> ctrlClass, Predicate<Controller> enumerator) {
        Objects.requireNonNull(ctrlClass, "Controller class cannot be null");
        Objects.requireNonNull(enumerator, "Controller enumerator cannot be null");
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
     * @throws NullPointerException if {@code ctrlClass} is {@code null}
     */
    public Stream<Controller> getControllerStream(Class<? extends Controller> ctrlClass) {
        Objects.requireNonNull(ctrlClass, "Controller class cannot be null");
        return windowListSupplier.get().stream()
                .map(Window::getScene)
                .filter(w -> w.getUserData() != null && ctrlClass.equals(w.getUserData().getClass()))
                .map(window -> (Controller) window.getUserData());
    }
}
