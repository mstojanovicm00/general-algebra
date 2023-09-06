package rs.raf.mstojanovic6119rn.algebra.general.special;

import rs.raf.mstojanovic6119rn.algebra.general.Algebra;
import rs.raf.mstojanovic6119rn.algebra.general.operable.Operable;
import rs.raf.mstojanovic6119rn.algebra.general.operation.Constant;
import rs.raf.mstojanovic6119rn.algebra.general.operation.InnerOperation;
import rs.raf.mstojanovic6119rn.algebra.general.operation.OuterOperation;
import rs.raf.mstojanovic6119rn.algebra.general.special.additional.Associable;
import rs.raf.mstojanovic6119rn.algebra.general.special.additional.Commutable;
import rs.raf.mstojanovic6119rn.algebra.general.special.additional.Distributable;

import java.util.Set;

public class VectorSpaces {

    public static Algebra createModule(Set<Operable> operables,
                                       InnerOperation addition,
                                       OuterOperation scalarMultiplication,
                                       Set<Operable> scalars,
                                       InnerOperation additionOfScalars,
                                       InnerOperation multiplicationOfScalars) {
        Groups.Group group = (Groups.Group) Groups.createGroup(operables, addition);
        if (!group.isCommutative())
            throw new RuntimeException();
        Fields.createField(scalars, additionOfScalars, multiplicationOfScalars);
        return new VectorSpace(operables, addition, group.getInverse(), group.getNeutralElement(), scalarMultiplication);
    }

    private VectorSpaces() {

    }

    static class VectorSpace extends Algebra implements Commutable, Associable, Distributable {
        VectorSpace(Set<Operable> operables,
                    InnerOperation addition, InnerOperation minus, Constant zero,
                    OuterOperation scalarMultiplication) {
            super(operables, addition, minus, scalarMultiplication, zero);
        }
        @Override
        public boolean isAssociative() {
            return true;
        }
        @Override
        public boolean isCommutative() {
            return true;
        }
        @Override
        public boolean isDistributive() {
            return true;
        }
    }
}
