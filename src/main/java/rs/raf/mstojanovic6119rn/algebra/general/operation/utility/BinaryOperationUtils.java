package rs.raf.mstojanovic6119rn.algebra.general.operation.utility;

import rs.raf.mstojanovic6119rn.algebra.general.operable.Operable;
import rs.raf.mstojanovic6119rn.algebra.general.operation.DisrespectOfArityException;
import rs.raf.mstojanovic6119rn.algebra.general.operation.InnerOperation;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
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

    private static Operable findInverseElement(InnerOperation operation, Set<Operable> operables,
                                               Operable neutralElement, Operable startingElement) {
        for (Operable o : operables) {
            if (!operation.calculate(startingElement, o).equals(neutralElement))
                continue;
            if (!operation.calculate(o, startingElement).equals(neutralElement))
                continue;
            return o;
        }
        return null;
    }

    public static boolean hasInverse(InnerOperation operation, Set<Operable> operables,
                                     Operable neutralElement, Operable startingElement) {
        return findInverseElement(operation, operables, neutralElement, startingElement) != null;
    }

    public static boolean isItsOwnInverse(InnerOperation operation, Set<Operable> operables,
                                          Operable neutralElement, Operable startingElement) {
        return startingElement.equals(findInverseElement(operation, operables, neutralElement, startingElement));
    }

    public static Map<Operable, Operable> mapElementsToTheirInverses(InnerOperation operation,
                                                                      Set<Operable> operables,
                                                                      Operable neutralElement) {
        if (!Objects.equals(
                findInverseElement(operation, operables, neutralElement, neutralElement), neutralElement))
            throw new RuntimeException();
        Map<Operable, Operable> map = new HashMap<>();
        map.put(neutralElement, neutralElement);
        for (Operable o : operables) {
            if (map.containsKey(o))
                continue;
            Operable inverse = findInverseElement(operation, operables, neutralElement, o);
            if (inverse == null)
                throw new RuntimeException();
            map.put(o, inverse);
            map.put(inverse, o);
        }
        return map;
    }

    public static boolean isDistributive(InnerOperation multiplication, InnerOperation addition,
                                         Set<Operable> operables) {
        if (multiplication.type().getValue() != 2)
            throw new DisrespectOfArityException(
                    new InnerOperation.IntegerType(2),
                    multiplication.type().getValue());
        if (addition.type().getValue() != 2)
            throw new DisrespectOfArityException(
                    new InnerOperation.IntegerType(2),
                    addition.type().getValue());
        for (Operable a : operables) {
            for (Operable b : operables) {
                for (Operable c : operables) {
                    Operable leftA = multiplication.calculate(addition.calculate(a, b), c);
                    Operable leftB = addition.calculate(
                            multiplication.calculate(a, c), multiplication.calculate(b, c));
                    if (!leftA.equals(leftB))
                        return false;
                    Operable rightA = multiplication.calculate(c, addition.calculate(a, b));
                    Operable rightB = addition.calculate(
                            multiplication.calculate(c, a), multiplication.calculate(c, b));
                    if (!rightA.equals(rightB))
                        return false;
                }
            }
        }
        return true;
    }

    private BinaryOperationUtils() {

    }
}
