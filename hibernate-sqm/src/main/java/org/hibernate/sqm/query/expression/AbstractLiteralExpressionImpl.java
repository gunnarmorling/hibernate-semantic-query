/*
 * Hibernate, Relational Persistence for Idiomatic Java
 *
 * License: Apache License, Version 2.0
 * See the LICENSE file in the root directory or visit http://www.apache.org/licenses/LICENSE-2.0
 */
package org.hibernate.sqm.query.expression;

/**
 * @author Steve Ebersole
 */
public abstract class AbstractLiteralExpressionImpl<T> implements LiteralExpression<T> {
	private final T value;

	public AbstractLiteralExpressionImpl(T value) {
		this.value = value;
	}

	@Override
	public T getLiteralValue() {
		return value;
	}
}
