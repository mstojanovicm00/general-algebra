package rs.raf.mstojanovic6119rn.algebra.general.operation;

/**
 * Unchecked exception which is thrown when the operation type is not being respected by the operand array.
 *
 * @author mstojanovic6119rn
 */
public class DisrespectOfArityException extends RuntimeException {

    /**
     * Creates an object of this type.
     *
     * @param expected expected operation type which wasn't respected by the operand array
     * @param given object which represents a type of the operand array
     */
    public DisrespectOfArityException(Operation.Type expected, Object given) {
        super("The given " + given + " operable array is not compatible to " + expected);
    }
}
