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

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.WeakChangeListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * This class implements property that wraps {@link Predicate} instance.
 * <p>
 * Default value is <code>x -&gt; true</code> i.e. {@link PredicateProperty#test(Object)} will return
 * true until the predicate is altered via {@link PredicateProperty#set(Predicate)}.
 *
 * @param <T> the type of the input to the predicate
 */
public class PredicateProperty<T> extends SimpleObjectProperty<Predicate<T>> implements Predicate<T> {
    private enum Op {
        AND,
        OR
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
        this.op = null;
    }

    /**
     * Creates an instance with default predicate.
     *
     * @param bean bean of this property
     * @param name name of this property
     */
    public PredicateProperty(Object bean, String name) {
        super(bean, name, x -> true);
        this.op = null;
    }

    /**
     * Creates an instance with the specified predicate value.
     *
     * @param initialValue initial predicate value
     */
    public PredicateProperty(Predicate<T> initialValue) {
        super(initialValue);
        this.op = null;
    }

    /**
     * Creates an instance with the specified predicate value.
     *
     * @param bean         bean of this property
     * @param name         name of this property
     * @param initialValue initial predicate value
     */
    public PredicateProperty(Object bean, String name, Predicate<T> initialValue) {
        super(bean, name, initialValue);
        Objects.requireNonNull(initialValue, "Predicate value cannot be null");
        this.op = null;
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
            switch (op) {
                case AND:
                    result = result.and(p.get());
                    break;
                case OR:
                    result = result.or(p.get());
                    break;
            }
        }
        return result;
    }

    /**
     * Creates calculated predicate property that produces logical AND from its arguments.
     *
     * @param args arguments
     * @param <T>  type of the input to the predicate
     * @return predicate property
     */
    public static <T> PredicateProperty<T> and(Collection<PredicateProperty<T>> args) {
        return new PredicateProperty<>(Op.AND, args);
    }

    /**
     * Creates calculated predicate property that produces logical OR from its arguments.
     *
     * @param args arguments
     * @param <T>  type of the input to the predicate
     * @return predicate property
     */
    public static <T> PredicateProperty<T> or(Collection<PredicateProperty<T>> args) {
        return new PredicateProperty<>(Op.OR, args);
    }

    /**
     * Sets the wrapped predicate value.
     *
     * @param predicate predicate value.
     * @throws IllegalStateException if property is calculated
     */
    @Override
    public void set(Predicate<T> predicate) {
        if (op != null) {
            throw new IllegalStateException("Calculated property cannot be set");
        }
        super.set(predicate);
    }

    /**
     * Create a unidirectional binding for this Property.
     *
     * @param observableValue observable this {@link javafx.beans.Observable} should be bound to
     */
    @Override
    public void bind(ObservableValue<? extends Predicate<T>> observableValue) {
        if (op != null) {
            throw new IllegalStateException("Calculated property cannot be bound");
        }
        super.bind(observableValue);
    }

    /**
     * Sets the wrapped predicate value.
     *
     * @param predicate predicate value.
     * @throws IllegalStateException if property is calculated
     */
    @Override
    public void setValue(Predicate<T> predicate) {
        if (op != null) {
            throw new IllegalStateException("Calculated property cannot be set");
        }
        super.setValue(predicate);
    }

    /**
     * Changes predicate to its initial value, i.e. x -&gt; true.
     *
     * @throws IllegalStateException if property is calculated
     */
    public void reset() {
        if (op != null) {
            throw new IllegalStateException("Calculated property cannot be reset");
        }
        super.setValue(x -> true);
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
