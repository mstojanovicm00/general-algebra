package rs.raf.mstojanovic6119rn.algebra.general.special;

import rs.raf.mstojanovic6119rn.algebra.general.Algebra;
import rs.raf.mstojanovic6119rn.algebra.general.operable.Operable;
import rs.raf.mstojanovic6119rn.algebra.general.operation.Constant;
import rs.raf.mstojanovic6119rn.algebra.general.operation.InnerOperation;
import rs.raf.mstojanovic6119rn.algebra.general.operation.utility.BinaryOperationUtils;
import rs.raf.mstojanovic6119rn.algebra.general.special.additional.Associable;
import rs.raf.mstojanovic6119rn.algebra.general.special.additional.Commutable;

import java.util.Set;

public class Semigroups {

    public static Algebra createSemigroup(Set<Operable> operables, InnerOperation operation) {
        if (!BinaryOperationUtils.isAssociative(operation, operables))
            throw new RuntimeException();
        Magmas.Magma groupoid = (Magmas.Magma) Magmas.createMagma(operables, operation);
        return groupoid.convertToSemigroup();
    }

    private Semigroups() {

    }

    static class Semigroup extends Algebra implements Commutable, Associable {
        Semigroup(Set<Operable> operables, InnerOperation operation) {
            super(operables, operation);
        }
        InnerOperation getBinaryOperation() {
            return (InnerOperation) super.getOperations().get(0);
        }
        Monoids.Monoid convertToMonoid() {
            Set<Operable> operables = super.getOperables();
            InnerOperation operation = this.getBinaryOperation();
            Operable neutralElement = BinaryOperationUtils.findNeutralElement(operation, operables);
            if (neutralElement == null)
                throw new RuntimeException();
            return new Monoids.Monoid(operables, operation, new Constant(neutralElement.getContent()));
        }
        @Override
        public boolean isAssociative() {
            return true;
        }
        @Override
        public boolean isCommutative() {
            return BinaryOperationUtils.isCommutative(this.getBinaryOperation(), super.getOperables());
        }
    }
}
