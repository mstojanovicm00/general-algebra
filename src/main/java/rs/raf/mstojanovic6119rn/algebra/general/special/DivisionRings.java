package rs.raf.mstojanovic6119rn.algebra.general.special;

import rs.raf.mstojanovic6119rn.algebra.general.Algebra;
import rs.raf.mstojanovic6119rn.algebra.general.operable.Operable;
import rs.raf.mstojanovic6119rn.algebra.general.operation.Constant;
import rs.raf.mstojanovic6119rn.algebra.general.operation.InnerOperation;
import rs.raf.mstojanovic6119rn.algebra.general.operation.utility.BinaryOperationUtils;
import rs.raf.mstojanovic6119rn.algebra.general.special.additional.Associable;
import rs.raf.mstojanovic6119rn.algebra.general.special.additional.Commutable;
import rs.raf.mstojanovic6119rn.algebra.general.special.additional.Distributable;

import java.util.Set;

public class DivisionRings {

    public static Algebra createBody(Set<Operable> operables,
                                     InnerOperation addition, InnerOperation multiplication) {
        Rings.Ring ring = (Rings.Ring) Rings.createRing(operables, addition, multiplication);
        return ring.convertToDivisionRing();
    }

    private DivisionRings() {

    }

    static class DivisionRing extends Algebra implements Commutable, Associable, Distributable {
        DivisionRing(Set<Operable> operables,
                     InnerOperation addition, InnerOperation minus, Constant zero,
                     InnerOperation multiplication, InnerOperation multiplicativeInverse, Constant one) {
            super(operables, addition, multiplication, minus, multiplicativeInverse, zero, one);
        }
        InnerOperation getAddition() {
            return (InnerOperation) this.getOperations().get(0);
        }
        InnerOperation getMultiplication() {
            return (InnerOperation) this.getOperations().get(1);
        }
        InnerOperation getNegation() {
            return (InnerOperation) this.getOperations().get(2);
        }
        InnerOperation getMultiplicativeInverse() {
            return (InnerOperation) this.getOperations().get(3);
        }
        Constant getNeutralElementForAddition() {
            return (Constant) this.getOperations().get(4);
        }
        Constant getNeutralElementForMultiplication() {
            return (Constant) this.getOperations().get(5);
        }
        Fields.Field convertToField() {
            if (this.isCommutative())
                return new Fields.Field(super.getOperables(),
                        this.getAddition(), this.getNegation(), this.getNeutralElementForAddition(),
                        this.getMultiplication(), this.getMultiplicativeInverse(),
                        this.getNeutralElementForMultiplication());
            throw new RuntimeException();
        }
        @Override
        public boolean isAssociative() {
            return true;
        }
        @Override
        public boolean isCommutative() {
            return BinaryOperationUtils.isCommutative(this.getMultiplication(), super.getOperables());
        }
        @Override
        public boolean isDistributive() {
            return true;
        }
    }
}
