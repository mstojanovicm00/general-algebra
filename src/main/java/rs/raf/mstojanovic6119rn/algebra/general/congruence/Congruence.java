package rs.raf.mstojanovic6119rn.algebra.general.congruence;

import rs.raf.mstojanovic6119rn.algebra.general.Algebra;
import rs.raf.mstojanovic6119rn.algebra.general.NotInSetException;
import rs.raf.mstojanovic6119rn.algebra.general.operable.Operable;
import rs.raf.mstojanovic6119rn.algebra.general.utility.RelationUtils;
import rs.raf.mstojanovic6119rn.algebra.general.utility.relations.Relation;

public class Congruence {

    public static Congruence createConguence(Algebra algebra, Relation<Operable, Operable> relation) {
        if (RelationUtils.isEquivalence(relation, algebra.getOperables()))
            return new Congruence(algebra, relation);
        throw new RelationNotEquivalenceException(relation);
    }

    private final Algebra algebra;

    private final Relation<Operable, Operable> relation;

    private Congruence(Algebra algebra, Relation<Operable, Operable> relation) {
        this.algebra = algebra;
        this.relation = relation;
    }

    public boolean areRelated(Operable a, Operable b) {
        if (this.algebra.getOperables().contains(a)) {
            if (this.algebra.getOperables().contains(b))
                return this.relation.isPairInRelation(a, b);
            throw new NotInSetException(b, this.algebra.getOperables());
        }
        throw new NotInSetException(a, this.algebra.getOperables());
    }
}
