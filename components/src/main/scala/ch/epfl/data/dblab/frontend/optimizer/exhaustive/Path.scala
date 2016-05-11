package ch.epfl.data
package dblab
package frontend
package optimizer
package exhaustive

/**
 * Compact representation of query plans that is used during join
 * order optimization. A path is characterized only by a join tree
 * while selections, projections, aggregates etc. are not explicitly
 * represented. It is assumed that selections and projections are
 * applied as soon as possible. Aggregates and other operations are
 * not required since they are handled separately during join order
 * optimization. Paths contain additional fields that are used by
 * the optimizer. Remark: the term "Path" is used in the Postgres
 * optimizer with a similar semantic as opposed to full-fledged
 * query plans, we use consistent terminology.
 *
 * @author Immanuel Trummer
 */
class Path(resultParam: RelationInfo) {
  val result: RelationInfo = resultParam
}
/**
 * Represents the scan of a base table in the database.
 */
case class BaseTablePath(override val result: RelationInfo, val tableName: String) extends Path(result)
/**
 * Represents a sub-query in the query tree.
 */
case class SubqueryPath(override val result: RelationInfo, val subqueryID: Int) extends Path(result)
/**
 * Represents the join of two paths.
 */
case class JoinPath(override val result: RelationInfo, val leftPath: Path, val rightPath: Path) extends Path(result)