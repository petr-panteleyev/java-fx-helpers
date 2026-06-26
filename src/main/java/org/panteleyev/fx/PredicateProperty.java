// Copyright © 2020-2026 Petr Panteleyev
// SPDX-License-Identifier: BSD-2-Clause
package org.panteleyev.fx;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.WeakChangeListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Predicate;

import static java.util.Objects.requireNonNull;

/**
 * This class implements property that wraps {@link Predicate} instance.
 * <p>
 * Default value is {@code true} i.e. {@link PredicateProperty#test(Object)} will return {@code true} until the
 * predicate is altered via {@link PredicateProperty#set(Predicate)}.
 *
 * @param <T> the type of the input to the predicate
 */
public class PredicateProperty<T> extends SimpleObjectProperty<Predicate<T>> implements Predicate<T> {
    private enum Op {
        AND,
        OR,
        NOOP
    }

    private final Collection<PredicateProperty<T>> inputs = new ArrayList<>();
    private final Op op;

    @SuppressWarnings("FieldCanBeLocal")
    private final ChangeListener<Predicate<T>> LISTENER = (x, y, newValue) -> onInputUpdate();

    /**
     * Creates an instance with default predicate.
     */
    public PredicateProperty() {
        super(x -> true);
        this.op = Op.NOOP;
    }

    /**
     * Creates an instance with default predicate.
     *
     * @param bean bean of this property
     * @param name name of this property
     */
    public PredicateProperty(Object bean, String name) {
        super(bean, name, x -> true);
        this.op = Op.NOOP;
    }

    /**
     * Creates an instance with the specified predicate value.
     *
     * @param initialValue initial predicate value
     * @throws NullPointerException if {@code initialValue} is {@code null}
     */
    public PredicateProperty(Predicate<T> initialValue) {
        super(requireNonNull(initialValue, "Predicate value must not be null"));
        this.op = Op.NOOP;
    }

    /**
     * Creates an instance with the specified predicate value.
     *
     * @param bean         bean of this property
     * @param name         name of this property
     * @param initialValue initial predicate value
     * @throws NullPointerException if {@code initialValue} is {@code null}
     */
    public PredicateProperty(Object bean, String name, Predicate<T> initialValue) {
        super(bean, name, requireNonNull(initialValue, "Predicate value cannot be null"));
        this.op = Op.NOOP;
    }

    private PredicateProperty(Op op, Collection<PredicateProperty<T>> inputs) {
        this.op = op;
        this.inputs.addAll(inputs);

        super.set(buildPredicate(op, inputs));

        for (var p : inputs) {
            p.addListener(new WeakChangeListener<>(LISTENER));
        }
    }

    private void onInputUpdate() {
        super.set(buildPredicate(op, inputs));
    }

    private static <T> Predicate<T> buildPredicate(Op op, Collection<PredicateProperty<T>> inputs) {
        Predicate<T> result = op == Op.AND ? x -> true : x -> false;
        for (var p : inputs) {
            result = switch (op) {
                case AND -> result.and(p.get());
                case OR -> result.or(p.get());
                case NOOP -> throw new IllegalArgumentException("Can't build a predicate with NOOP");
            };
        }
        return result;
    }

    /**
     * Creates calculated predicate property that produces logical AND from its arguments.
     *
     * @param args arguments
     * @param <T>  type of the input to the predicate
     * @return predicate property
     * @throws NullPointerException if {@code args} is {@code null}
     */
    public static <T> PredicateProperty<T> and(Collection<PredicateProperty<T>> args) {
        return new PredicateProperty<>(Op.AND, requireNonNull(args, "Predicate arguments must not be null"));
    }

    /**
     * Creates calculated predicate property that produces logical OR from its arguments.
     *
     * @param args arguments
     * @param <T>  type of the input to the predicate
     * @return predicate property
     * @throws NullPointerException if {@code args} is {@code null}
     */
    public static <T> PredicateProperty<T> or(Collection<PredicateProperty<T>> args) {
        return new PredicateProperty<>(Op.OR, requireNonNull(args, "Predicate arguments must not be null"));
    }

    /**
     * Sets the wrapped predicate value.
     *
     * @param predicate predicate value.
     * @throws IllegalStateException if property is calculated
     * @throws NullPointerException  if {@code predicate} is {@code null}
     */
    @Override
    public void set(Predicate<T> predicate) {
        if (op != Op.NOOP) {
            throw new IllegalStateException("Calculated property cannot be set");
        }
        super.set(requireNonNull(predicate, "Predicate must not be null"));
    }

    /**
     * Create a unidirectional binding for this Property.
     *
     * @param observableValue observable this {@link javafx.beans.Observable} should be bound to
     * @throws NullPointerException if {@code observableValue} is {@code null}
     */
    @Override
    public void bind(ObservableValue<? extends Predicate<T>> observableValue) {
        if (op != Op.NOOP) {
            throw new IllegalStateException("Calculated property cannot be bound");
        }
        super.bind(requireNonNull(observableValue, "Observable value must not be null"));
    }

    /**
     * Sets the wrapped predicate value.
     *
     * @param predicate predicate value.
     * @throws IllegalStateException if property is calculated
     * @throws NullPointerException  if {@code predicate} is {@code null}
     */
    @Override
    public void setValue(Predicate<T> predicate) {
        set(predicate);
    }

    /**
     * Changes predicate to its initial value, i.e. x -&gt; true.
     *
     * @throws IllegalStateException if property is calculated
     */
    public void reset() {
        set(_ -> true);
    }

    // Predicate methods

    @Override
    public boolean test(T t) {
        return get().test(t);
    }

    @Override
    public Predicate<T> and(Predicate<? super T> other) {
        return get().and(other);
    }

    @Override
    public Predicate<T> negate() {
        return get().negate();
    }

    @Override
    public Predicate<T> or(Predicate<? super T> other) {
        return get().or(other);
    }
}
