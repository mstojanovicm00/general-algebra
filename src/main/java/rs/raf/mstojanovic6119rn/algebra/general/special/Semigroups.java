package rs.raf.mstojanovic6119rn.algebra.general.special;

import rs.raf.mstojanovic6119rn.algebra.general.Algebra;
import rs.raf.mstojanovic6119rn.algebra.general.operable.Operable;
import rs.raf.mstojanovic6119rn.algebra.general.operation.Constant;
import rs.raf.mstojanovic6119rn.algebra.general.operation.InnerOperation;
import rs.raf.mstojanovic6119rn.algebra.general.operation.utility.BinaryOperationUtils;

import java.util.Set;

public class Semigroups {

    public static Algebra createSemigroup(Set<Operable> operables, InnerOperation operation) {
        if (!BinaryOperationUtils.isAssociative(operation, operables))
            throw new RuntimeException();
        Groupoids.Groupoid groupoid = (Groupoids.Groupoid) Groupoids.createGroupoid(operables, operation);
        return groupoid.convertToSemigroup();
    }

    private Semigroups() {

    }

    static class Semigroup extends Algebra {
        Semigroup(Set<Operable> operables, InnerOperation operation) {
            super(operables, operation);
        }
        Monoids.Monoid convertToMonoid() {
            Set<Operable> operables = super.getOperables();
            InnerOperation operation = (InnerOperation) super.getOperations().get(0);
            Operable neutralElement = BinaryOperationUtils.findNeutralElement(operation, operables);
            if (neutralElement == null)
                throw new RuntimeException();
            return new Monoids.Monoid(operables, operation, new Constant(neutralElement.getContent()));
        }
    }
}
