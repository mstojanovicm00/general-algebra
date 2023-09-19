package rs.raf.mstojanovic6119rn.algebra.general.special;

import rs.raf.mstojanovic6119rn.algebra.general.Algebra;
import rs.raf.mstojanovic6119rn.algebra.general.operable.Operable;
import rs.raf.mstojanovic6119rn.algebra.general.operation.DisrespectOfArityException;
import rs.raf.mstojanovic6119rn.algebra.general.operation.InnerOperation;
import rs.raf.mstojanovic6119rn.algebra.general.operation.utility.BinaryOperationUtils;
import rs.raf.mstojanovic6119rn.algebra.general.special.additional.Associable;
import rs.raf.mstojanovic6119rn.algebra.general.special.additional.Commutable;

import java.util.Set;

public class Magmas {

    public static Algebra createGroupoid(Set<Operable> operables, InnerOperation operation) {
        if (operation.type().getValue() == 2)
            return new Magma(operables, operation);
        throw new DisrespectOfArityException(
                new InnerOperation.IntegerType(2),
                operation.type().getValue());
    }

    private Magmas() {

    }

    static class Magma extends Algebra implements Commutable, Associable {
        Magma(Set<Operable> operables, InnerOperation operation) {
            super(operables, operation);
        }
        InnerOperation getBinaryOperation() {
            return (InnerOperation) super.getOperations().get(0);
        }
        Semigroups.Semigroup convertToSemigroup() {
            Set<Operable> operables = super.getOperables();
            InnerOperation operation = this.getBinaryOperation();
            return new Semigroups.Semigroup(operables, operation);
        }
        @Override
        public boolean isAssociative() {
            return BinaryOperationUtils.isAssociative(this.getBinaryOperation(), super.getOperables());
        }
        @Override
        public boolean isCommutative() {
            return BinaryOperationUtils.isCommutative(this.getBinaryOperation(), super.getOperables());
        }
    }
}
