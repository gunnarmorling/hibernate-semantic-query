/*
 * Hibernate, Relational Persistence for Idiomatic Java
 *
 * License: Apache License, Version 2.0
 * See the LICENSE file in the root directory or visit http://www.apache.org/licenses/LICENSE-2.0
 */
package org.hibernate.sqm.query.expression;

import org.hibernate.sqm.SemanticQueryWalker;
import org.hibernate.sqm.domain.TypeDescriptor;

/**
 * @author Steve Ebersole
 */
public class ConcatExpression implements Expression {
	private final Expression lhsOperand;
	private final Expression rhsOperand;

	public ConcatExpression(Expression lhsOperand, Expression rhsOperand) {
		this.lhsOperand = lhsOperand;
		this.rhsOperand = rhsOperand;
	}

	public Expression getLeftHandOperand() {
		return lhsOperand;
	}

	public Expression getRightHandOperand() {
		return rhsOperand;
	}

	@Override
	public TypeDescriptor getTypeDescriptor() {
		// for now
		return lhsOperand.getTypeDescriptor();
	}

	@Override
	public <T> T accept(SemanticQueryWalker<T> walker) {
		return walker.visitConcatExpression( this );
	}
}
