package rs.raf.mstojanovic6119rn.algebra.general.special;

import rs.raf.mstojanovic6119rn.algebra.general.Algebra;
import rs.raf.mstojanovic6119rn.algebra.general.operable.Operable;
import rs.raf.mstojanovic6119rn.algebra.general.operation.Constant;
import rs.raf.mstojanovic6119rn.algebra.general.operation.InnerOperation;
import rs.raf.mstojanovic6119rn.algebra.general.operation.utility.BinaryOperationUtils;
import rs.raf.mstojanovic6119rn.algebra.general.special.additional.Associable;
import rs.raf.mstojanovic6119rn.algebra.general.special.additional.Commutable;
import rs.raf.mstojanovic6119rn.algebra.general.special.additional.Distributable;

import java.util.HashSet;
import java.util.Set;

public class Rings {

    public static Algebra createRing(Set<Operable> operables,
                                     InnerOperation addition, InnerOperation multiplication) {
        Groups.Group group = (Groups.Group) Groups.createGroup(operables, addition);
        if (!group.isCommutative())
            throw new RuntimeException();
        Monoids.Monoid monoid = (Monoids.Monoid) Monoids.createMonoid(operables, multiplication);
        return new Ring(operables,
                group.getBinaryOperation(), group.getInverse(), group.getNeutralElement(),
                monoid.getBinaryOperation(), monoid.getNeutralElement());
    }

    private Rings() {

    }

    static class Ring extends Algebra implements Commutable, Associable, Distributable {
        Ring(Set<Operable> operables,
             InnerOperation addition, InnerOperation minus, Constant zero,
             InnerOperation multiplication, Constant one) {
            super(operables, addition, multiplication, minus, zero, one);
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
        Constant getNeutralElementForAddition() {
            return (Constant) this.getOperations().get(3);
        }
        Constant getNeutralElementForMultiplication() {
            return (Constant) this.getOperations().get(4);
        }
        Bodies.Body convertToBody() {
            if (!BinaryOperationUtils.isItsOwnInverse(this.getMultiplication(),
                    super.getOperables(),
                    this.getNeutralElementForMultiplication().calculate(),
                    this.getNeutralElementForAddition().calculate()))
                throw new RuntimeException();
            for (Operable o : super.getOperables()) {
                if (!BinaryOperationUtils.hasInverse(this.getMultiplication(),
                        super.getOperables(), this.getNeutralElementForMultiplication().calculate(), o))
                    throw new RuntimeException();
            }
            Set<Operable> operables = new HashSet<>(super.getOperables());
            operables.removeIf(o -> getNeutralElementForAddition().calculate().equals(o));
            Groups.Group group = (Groups.Group) Groups.createGroup(operables, this.getMultiplication());
            return new Bodies.Body(super.getOperables(),
                    this.getAddition(), this.getNegation(), this.getNeutralElementForAddition(),
                    group.getBinaryOperation(), group.getInverse(), group.getNeutralElement());
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
