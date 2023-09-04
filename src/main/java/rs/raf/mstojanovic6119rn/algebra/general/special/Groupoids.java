package rs.raf.mstojanovic6119rn.algebra.general.special;

import rs.raf.mstojanovic6119rn.algebra.general.Algebra;
import rs.raf.mstojanovic6119rn.algebra.general.operable.Operable;
import rs.raf.mstojanovic6119rn.algebra.general.operation.DisrespectOfArityException;
import rs.raf.mstojanovic6119rn.algebra.general.operation.InnerOperation;

import java.util.Set;

public class Groupoids {

    public static Algebra createGroupoid(Set<Operable> operables, InnerOperation operation) {
        if (operation.type().getValue() == 2)
            return new Groupoid(operables, operation);
        throw new DisrespectOfArityException(
                new InnerOperation.IntegerType(2),
                operation.type().getValue());
    }

    private Groupoids() {

    }

    static class Groupoid extends Algebra {
        Groupoid(Set<Operable> operables, InnerOperation operation) {
            super(operables, operation);
        }
        Semigroups.Semigroup convertToSemigroup() {
            Set<Operable> operables = super.getOperables();
            InnerOperation operation = (InnerOperation) super.getOperations().get(0);
            return new Semigroups.Semigroup(operables, operation);
        }
    }
}
