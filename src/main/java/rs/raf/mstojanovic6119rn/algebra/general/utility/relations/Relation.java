package rs.raf.mstojanovic6119rn.algebra.general.utility.relations;

import rs.raf.mstojanovic6119rn.algebra.general.utility.pairs.Pair;

import java.util.HashMap;
import java.util.Map;

/**
 * Class that represents a mathematical relation. A relation is a subset of a Decarte's product
 * of two given sets.
 *
 * @param <A> first set elements' type
 * @param <B> second set elements' type
 *
 * @author mstojanovic6119rn
 */
public class Relation<A, B> {

    private final Map<Pair<A, B>, Boolean> pairBooleanMap;

    private Relation(Map<Pair<A, B>, Boolean> pairBooleanMap) {
        this.pairBooleanMap = pairBooleanMap;
    }

    /**
     * Checks if the two elements are in a relation.
     *
     * @param a element of a first set
     * @param b element of a second set
     * @return if the given elements are related based on this <code>Relation</code>.
     */
    public boolean isPairInRelation(A a, B b) {
        return this.pairBooleanMap.get(new Pair<>(a, b));
    }

    /**
     * A builder for a <code>Relation</code> class.
     *
     * @param <A> first set elements' type
     * @param <B> second set elements' type
     */
    public static class RelationBuilder<A, B> {

        /**
         * Creates a <code>RelationBuilder</code> object.
         *
         * @return created <code>RelationBuilder</code> object
         * @param <A> first set elements' type
         * @param <B> second set elements' type
         */
        public static<A, B> RelationBuilder<A, B> newRelationBuilder() {
            return new RelationBuilder<>();
        }

        private Map<Pair<A, B>, Boolean> pairBooleanMap = new HashMap<>();

        private RelationBuilder() {

        }

        /**
         * Adds a pair to a relation
         *
         * @param pair pair to be added
         * @return <code>RelationBuilder</code> object
         */
        public final RelationBuilder<A, B> addPairInRelation(Pair<A, B> pair) {
            this.pairBooleanMap.put(pair, true);
            return this;
        }

        /**
         * Adds two elements as a related pair to a relation
         *
         * @param a one element of a pair
         * @param b another element of a pair
         * @return <code>RelationBuilder</code> object
         */
        public final RelationBuilder<A, B> addPairInRelation(A a, B b) {
            return this.addPairInRelation(new Pair<>(a, b));
        }

        /**
         * Adds a pair to a relation as an unrelated pair
         *
         * @param pair pair to be added
         * @return <code>RelationBuilder</code> object
         */
        public final RelationBuilder<A, B> addUnrelatedPair(Pair<A, B> pair) {
            this.pairBooleanMap.put(pair, false);
            return this;
        }

        /**
         * Adds two elements as an unrelated pair to a relation
         *
         * @param a one element of a pair
         * @param b another element of a pair
         * @return <code>RelationBuilder</code> object
         */
        public final RelationBuilder<A, B> addUnrelatedPair(A a, B b) {
            return this.addUnrelatedPair(new Pair<>(a, b));
        }

        /**
         * Converts this <code>RelationBuilder</code> object to a <code>Relation</code> object. The map
         * of related objects will be passed to a <code>Relation</code> class's constructor.
         *
         * @return constructed <code>Relation</code> object
         */
        public final Relation<A, B> toRelation() {
            return new Relation<>(this.pairBooleanMap);
        }

    }

}
