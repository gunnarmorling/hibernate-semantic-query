/*
 * Hibernate OGM, Domain model persistence for NoSQL datastores
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later
 * See the lgpl.txt file in the root directory or <http://www.gnu.org/licenses/lgpl-2.1.html>.
 */
package org.hibernate.test.query.parser.hql;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.hibernate.query.parser.SemanticQueryInterpreter;
import org.hibernate.sqm.query.SelectStatement;
import org.hibernate.sqm.query.expression.CollectionSizeFunction;
import org.hibernate.sqm.query.expression.LiteralIntegerExpression;
import org.hibernate.sqm.query.predicate.Predicate;
import org.hibernate.sqm.query.predicate.RelationalPredicate;
import org.hibernate.test.query.parser.ConsumerContextImpl;
import org.junit.Test;

/**
 * @author Gunnar Morling
 *
 */
public class WhereClauseTests {

	private final ConsumerContextImpl consumerContext = new ConsumerContextImpl();

	@Test
	public void testCollectionSizeFunction() {
		SelectStatement statement = interpret( "SELECT t FROM Trip t WHERE SIZE( t.basicCollection ) = 311" );

		Predicate predicate = statement.getQuerySpec().getWhereClause().getPredicate();
		assertThat( predicate, instanceOf( RelationalPredicate.class ) );
		RelationalPredicate relationalPredicate = ( (RelationalPredicate) predicate );

		assertThat( relationalPredicate.getType(), is( RelationalPredicate.Type.EQUAL ) );

		assertThat( relationalPredicate.getRightHandExpression(), instanceOf( LiteralIntegerExpression.class ) );
		assertThat( ( (LiteralIntegerExpression) relationalPredicate.getRightHandExpression() ).getLiteralValue(), is( 311 ) );

		assertThat( relationalPredicate.getLeftHandExpression(), instanceOf( CollectionSizeFunction.class ) );
		assertThat( ( (CollectionSizeFunction) relationalPredicate.getLeftHandExpression() ).getCollectionAlias(), is( "t" ) );
	}

	private SelectStatement interpret(String query) {
		return (SelectStatement) SemanticQueryInterpreter.interpret( query, consumerContext );
	}
}
