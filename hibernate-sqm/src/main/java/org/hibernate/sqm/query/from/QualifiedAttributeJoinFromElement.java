/*
 * Hibernate, Relational Persistence for Idiomatic Java
 *
 * License: Apache License, Version 2.0
 * See the LICENSE file in the root directory or visit http://www.apache.org/licenses/LICENSE-2.0
 */
package org.hibernate.sqm.query.from;

import org.hibernate.sqm.SemanticQueryWalker;
import org.hibernate.sqm.query.JoinType;
import org.hibernate.sqm.domain.AttributeDescriptor;
import org.hibernate.sqm.query.predicate.Predicate;

/**
 * @author Steve Ebersole
 */
public class QualifiedAttributeJoinFromElement
		extends AbstractJoinedFromElement
		implements QualifiedJoinedFromElement {
	private final AttributeDescriptor joinedAttributeDescriptor;
	private final boolean fetched;

	private Predicate onClausePredicate;

	public QualifiedAttributeJoinFromElement(
			FromElementSpace fromElementSpace,
			String alias,
			AttributeDescriptor joinedAttributeDescriptor,
			JoinType joinType,
			boolean fetched) {
		super( fromElementSpace, alias, joinedAttributeDescriptor.getType(), joinType );
		this.joinedAttributeDescriptor = joinedAttributeDescriptor;
		this.fetched = fetched;
	}

	public AttributeDescriptor getJoinedAttributeDescriptor() {
		return joinedAttributeDescriptor;
	}

	public boolean isFetched() {
		return fetched;
	}

	@Override
	public Predicate getOnClausePredicate() {
		return onClausePredicate;
	}

	public void setOnClausePredicate(Predicate predicate) {
		this.onClausePredicate = predicate;
	}

	@Override
	public <T> T accept(SemanticQueryWalker<T> walker) {
		return walker.visitQualifiedAttributeJoinFromElement( this );
	}
}
