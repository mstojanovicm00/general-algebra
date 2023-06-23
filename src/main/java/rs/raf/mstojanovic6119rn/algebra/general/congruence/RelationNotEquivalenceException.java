package rs.raf.mstojanovic6119rn.algebra.general.congruence;

import rs.raf.mstojanovic6119rn.algebra.general.operable.Operable;
import rs.raf.mstojanovic6119rn.algebra.general.utility.relations.Relation;

public class RelationNotEquivalenceException extends CantCreateCongruenceException {
    public RelationNotEquivalenceException(Relation<Operable, Operable> relation) {
        this(relation + " is not an equivalence");
    }
    private RelationNotEquivalenceException(String message) {
        super(message);
    }
}
