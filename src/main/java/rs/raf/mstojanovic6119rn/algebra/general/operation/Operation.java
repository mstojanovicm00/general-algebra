package rs.raf.mstojanovic6119rn.algebra.general.operation;

import rs.raf.mstojanovic6119rn.algebra.general.operable.Operable;

import java.util.Map;
import java.util.Set;

/**
 * Represents the algebraic operation. Algebraic operation is a function that for a given set of operands
 * produces a result of the type of the second operand. If the second operand doesn't exist, then the result
 * is a type of the first operand. If there are no operands, then the result must be in a given set.
 * Sets aren't worked on in the classes that implement this interface. Instead, they are worked on in an
 * <code>Algebra</code> class.
 *
 * @author mstojanovic6119rn
 */
public interface Operation {
    /**
     * For a given set of operands produces a result.
     *
     * @param operables set of operands
     * @return result of an operation
     */
    Operable calculate(Operable... operables);

    /**
     * Returns a type of this algebraic operation.
     *
     * @return operation type
     */
    Type type();

    /**
     * Returns a map in which the keys are inputs of the operation and the values are its outputs.
     *
     * @param operableSet set from which the inputs are got
     * @return map with the properties explained above
     */
    Map<Operable[], Operable> calculations(Set<Operable> operableSet);

    /**
     * Represents an abstraction of all possible operation types.
     *
     * @author mstojanovic6119rn
     */
    interface Type {
        /**
         * Returns a unique value for this implementation class of this interface.
         *
         * @return unique value which is used to sort types of many implementation classes
         */
        int classId();
    }
}
