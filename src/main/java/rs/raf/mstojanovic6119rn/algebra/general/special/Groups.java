package rs.raf.mstojanovic6119rn.algebra.general.special;

import rs.raf.mstojanovic6119rn.algebra.general.Algebra;
import rs.raf.mstojanovic6119rn.algebra.general.operable.Operable;
import rs.raf.mstojanovic6119rn.algebra.general.operation.Constant;
import rs.raf.mstojanovic6119rn.algebra.general.operation.InnerOperation;
import rs.raf.mstojanovic6119rn.algebra.general.operation.utility.BinaryOperationUtils;
import rs.raf.mstojanovic6119rn.algebra.general.special.additional.Associable;
import rs.raf.mstojanovic6119rn.algebra.general.special.additional.Commutable;

import java.util.Set;

public class Groups {

    public static Algebra createGroup(Set<Operable> operables, InnerOperation operation) {
        Monoids.Monoid monoid = (Monoids.Monoid) Monoids.createMonoid(operables, operation);
        return monoid.convertToGroup();
    }

    private Groups() {

    }

    static class Group extends Algebra implements Commutable, Associable {
        Group(Set<Operable> operables, InnerOperation operation, InnerOperation inverse, Constant constant) {
            super(operables, operation, inverse, constant);
        }
        InnerOperation getBinaryOperation() {
            return (InnerOperation) super.getOperations().get(0);
        }
        InnerOperation getInverse() {
            return (InnerOperation) super.getOperations().get(1);
        }
        Constant getNeutralElement() {
            return (Constant) super.getOperations().get(2);
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
