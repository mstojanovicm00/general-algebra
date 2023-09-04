package rs.raf.mstojanovic6119rn.algebra.general.special;

import rs.raf.mstojanovic6119rn.algebra.general.Algebra;
import rs.raf.mstojanovic6119rn.algebra.general.operable.Operable;
import rs.raf.mstojanovic6119rn.algebra.general.operation.Constant;
import rs.raf.mstojanovic6119rn.algebra.general.operation.InnerOperation;

import java.util.Set;

public class Monoids {

    public static Algebra createMonoid(Set<Operable> operables, InnerOperation operation) {
        Semigroups.Semigroup semigroup = (Semigroups.Semigroup) Semigroups.createSemigroup(operables, operation);
        return semigroup.convertToMonoid();
    }

    private Monoids() {

    }

    static class Monoid extends Algebra {
        Monoid(Set<Operable> operables, InnerOperation operation, Constant constant) {
            super(operables, operation, constant);
        }
    }
}
