/*
 * Hibernate, Relational Persistence for Idiomatic Java
 *
 * License: Apache License, Version 2.0
 * See the LICENSE file in the root directory or visit http://www.apache.org/licenses/LICENSE-2.0
 */
package org.hibernate.sqm.query.from;

import org.hibernate.sqm.query.predicate.Predicate;

/**
 * @author Steve Ebersole
 */
public interface QualifiedJoinedFromElement extends JoinedFromElement {
	Predicate getOnClausePredicate();
	void setOnClausePredicate(Predicate predicate);
}
