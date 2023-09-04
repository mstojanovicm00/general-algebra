package rs.raf.mstojanovic6119rn.algebra.general.operation.utility;

import rs.raf.mstojanovic6119rn.algebra.general.operable.Operable;
import rs.raf.mstojanovic6119rn.algebra.general.operation.DisrespectOfArityException;
import rs.raf.mstojanovic6119rn.algebra.general.operation.InnerOperation;

import java.util.Set;

public class BinaryOperationUtils {

    public static boolean isCommutative(InnerOperation operation, Set<Operable> operables) {
        if (operation.type().getValue() != 2)
            throw new DisrespectOfArityException(
                    new InnerOperation.IntegerType(2),
                    operation.type().getValue());
        for (Operable o1 : operables) {
            for (Operable o2 : operables) {
                Operable resLeft = operation.calculate(o1, o2);
                Operable resRight = operation.calculate(o2, o1);
                if (!resLeft.equals(resRight))
                    return false;
            }
        }
        return true;
    }

    public static boolean isAssociative(InnerOperation operation, Set<Operable> operables) {
        if (operation.type().getValue() != 2)
            throw new DisrespectOfArityException(
                    new InnerOperation.IntegerType(2),
                    operation.type().getValue());
        for (Operable o1 : operables) {
            for (Operable o2 : operables) {
                for (Operable o3 : operables) {
                    Operable resLeft = operation.calculate(operation.calculate(o1, o2), o3);
                    Operable resRight = operation.calculate(o1, operation.calculate(o2, o3));
                    if (!resLeft.equals(resRight))
                        return false;
                }
            }
        }
        return true;
    }

    private static boolean isNeutralElement(InnerOperation operation, Set<Operable> operables,
                                            Operable neutralElement) {
        if (operation.type().getValue() != 2)
            throw new DisrespectOfArityException(
                    new InnerOperation.IntegerType(2),
                    operation.type().getValue());
        for (Operable o : operables) {
            Operable resLeft = operation.calculate(neutralElement, o);
            if (!resLeft.equals(o))
                return false;
            Operable resRight = operation.calculate(o, neutralElement);
            if (!resRight.equals(o))
                return false;
        }
        return true;
    }

    public static Operable findNeutralElement(InnerOperation operation, Set<Operable> operables) {
        for (Operable o : operables) {
            if (isNeutralElement(operation, operables, o))
                return o;
        }
        return null;
    }

    private BinaryOperationUtils() {

    }
}
