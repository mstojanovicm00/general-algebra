package rs.raf.mstojanovic6119rn.algebra.general.utility;

import rs.raf.mstojanovic6119rn.algebra.general.utility.relations.Relation;

import java.util.Set;

/**
 * Class that contains basic check functions for mathematical relations.
 *
 * @author mstojanovic6119rn
 */
public class RelationUtils {

    /**
     * Checks if the given relation is reflexive on a given set. A relation is reflexive on a set if and only if
     * every element of a set is related to itself.
     *
     * @param relation relation to be checked
     * @param set set on which the relation is to be checked
     * @return if the relation is reflexive
     * @param <A> class of relation elements
     */
    public static<A> boolean isReflexive(Relation<A, A> relation, Set<A> set) {
        for (A a: set) {
            if (!relation.isPairInRelation(a, a))
                return false;
        }
        return true;
    }

    /**
     * Checks if the given relation is symmetric on a given set. A relation is symmetric on a set if and only if
     * for every two elements <code>a</code> and <code>b</code> it's true that if <code>a</code> is related to
     * <code>b</code> then <code>b</code> is related to <code>a</code>.
     *
     * @param relation relation to be checked
     * @param set set on which the relation is to be checked
     * @return if the relation is symmetric
     * @param <A> class of relation elements
     */
    public static<A> boolean isSymmetric(Relation<A, A> relation, Set<A> set) {
        for (A a1: set) {
            for (A a2: set) {
                if (relation.isPairInRelation(a1, a2) && !relation.isPairInRelation(a2, a1))
                    return false;
            }
        }
        return true;
    }

    /**
     * Checks if the given relation is antisymmetric on a given set. A relation is antisymmetric on a set if and
     * only if for every two elements <code>a</code> and <code>b</code> it's true that if <code>a</code> is related
     * to <code>b</code> and <code>b</code> is related to <code>a</code> then <code>a==b</code>.
     *
     * @param relation relation to be checked
     * @param set set on which the relation is to be checked
     * @return if the relation is antisymmetric
     * @param <A> class of relation elements
     */
    public static<A> boolean isAntisymmetric(Relation<A, A> relation, Set<A> set) {
        for (A a1: set) {
            for (A a2: set) {
                if (relation.isPairInRelation(a1, a2) && relation.isPairInRelation(a2, a1)
                        && !a1.equals(a2))
                    return false;
            }
        }
        return true;
    }

    /**
     * Checks if the given relation is transitive on a given set. A relation is transitive on a set if and only if
     * for every three elements <code>a</code>, <code>b</code> and <code>c</code> it's true that if <code>a</code>
     * is related to <code>b</code> and <code>b</code> is related to <code>c</code>, then <code>a</code> is related
     * to <code>c</code>.
     *
     * @param relation relation to be checked
     * @param set set on which the relation is to be checked
     * @return if the relation is transitive
     * @param <A> class of relation elements
     */
    public static<A> boolean isTransitive(Relation<A, A> relation, Set<A> set) {
        for (A a1: set) {
            for (A a2: set) {
                for (A a3: set) {
                    if (relation.isPairInRelation(a1, a2) && relation.isPairInRelation(a2, a3)
                            && !relation.isPairInRelation(a1, a3))
                        return false;
                }
            }
        }
        return true;
    }

    /**
     * Checks if the given relation is an order on a given set. A relation is an order on a given set if and only
     * if it's reflexive, antisymmetric and transitive on that set.
     *
     * @param relation relation to be checked
     * @param set set on which the relation is to be checked
     * @return if the relation is an order
     * @param <A> class of relation elements
     */
    public static<A> boolean isOrder(Relation<A, A> relation, Set<A> set) {
        return isReflexive(relation, set) && isAntisymmetric(relation, set) && isTransitive(relation, set);
    }

    /**
     * Checks if the given relation is an equivalence on a given set. A relation is an equivalence on a given set if
     * and only if it's reflexive, symmetric and transitive on that set.
     *
     * @param relation relation to be checked
     * @param set set on which the relation is to be checked
     * @return if the relation is an equivalence
     * @param <A> class of relation elements
     */
    public static<A> boolean isEquivalence(Relation<A, A> relation, Set<A> set) {
        return isReflexive(relation, set) && isSymmetric(relation, set) && isTransitive(relation, set);
    }

    private RelationUtils() {

    }
}
