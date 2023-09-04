package rs.raf.mstojanovic6119rn.algebra.general.special;

import rs.raf.mstojanovic6119rn.algebra.general.Algebra;
import rs.raf.mstojanovic6119rn.algebra.general.operable.Operable;
import rs.raf.mstojanovic6119rn.algebra.general.operation.Constant;
import rs.raf.mstojanovic6119rn.algebra.general.operation.InnerOperation;
import rs.raf.mstojanovic6119rn.algebra.general.operation.utility.BinaryOperationUtils;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;

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
        Groups.Group convertToGroup() {
            Set<Operable> operables = super.getOperables();
            InnerOperation operation = (InnerOperation) super.getOperations().get(0);
            Constant constant = (Constant) super.getOperations().get(1);
            Map<Operable, Operable> inverses = BinaryOperationUtils
                    .mapElementsToTheirInverses(operation, operables, constant.calculate());
            Function<Operable[], Operable> inverse = ops -> inverses.get(ops[0]);
            return new Groups.Group(operables, operation, new InnerOperation(1, inverse), constant);
        }
    }
}
