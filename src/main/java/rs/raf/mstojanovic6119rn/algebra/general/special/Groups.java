package rs.raf.mstojanovic6119rn.algebra.general.special;

import rs.raf.mstojanovic6119rn.algebra.general.Algebra;
import rs.raf.mstojanovic6119rn.algebra.general.operable.Operable;
import rs.raf.mstojanovic6119rn.algebra.general.operation.Constant;
import rs.raf.mstojanovic6119rn.algebra.general.operation.InnerOperation;

import java.util.Set;

public class Groups {

    public static Algebra createGroup(Set<Operable> operables, InnerOperation operation) {
        Monoids.Monoid monoid = (Monoids.Monoid) Monoids.createMonoid(operables, operation);
        return monoid.convertToGroup();
    }

    private Groups() {

    }

    static class Group extends Algebra {
        Group(Set<Operable> operables, InnerOperation operation, InnerOperation inverse, Constant constant) {
            super(operables, operation, inverse, constant);
        }
    }
}
