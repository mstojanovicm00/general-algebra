package rs.raf.mstojanovic6119rn.algebra.general.special;

import rs.raf.mstojanovic6119rn.algebra.general.Algebra;
import rs.raf.mstojanovic6119rn.algebra.general.operable.Operable;
import rs.raf.mstojanovic6119rn.algebra.general.operation.Constant;
import rs.raf.mstojanovic6119rn.algebra.general.operation.InnerOperation;
import rs.raf.mstojanovic6119rn.algebra.general.operation.utility.BinaryOperationUtils;
import rs.raf.mstojanovic6119rn.algebra.general.special.additional.Associable;
import rs.raf.mstojanovic6119rn.algebra.general.special.additional.Commutable;

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

    static class Monoid extends Algebra implements Commutable, Associable {
        Monoid(Set<Operable> operables, InnerOperation operation, Constant constant) {
            super(operables, operation, constant);
        }
        InnerOperation getBinaryOperation() {
            return (InnerOperation) super.getOperations().get(0);
        }
        Constant getNeutralElement() {
            return (Constant) super.getOperations().get(1);
        }
        Groups.Group convertToGroup() {
            Set<Operable> operables = super.getOperables();
            InnerOperation operation = this.getBinaryOperation();
            Constant constant = this.getNeutralElement();
            Map<Operable, Operable> inverses = BinaryOperationUtils
                    .mapElementsToTheirInverses(operation, operables, constant.calculate());
            Function<Operable[], Operable> inverse = ops -> inverses.get(ops[0]);
            return new Groups.Group(operables, operation, new InnerOperation(1, inverse), constant);
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
