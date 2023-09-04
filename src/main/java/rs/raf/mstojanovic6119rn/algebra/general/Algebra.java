package rs.raf.mstojanovic6119rn.algebra.general;

import rs.raf.mstojanovic6119rn.algebra.general.operable.Operable;
import rs.raf.mstojanovic6119rn.algebra.general.operation.Operation;
import rs.raf.mstojanovic6119rn.algebra.general.operation.comparator.TypeComparator;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Represents an algebraic structure, or an algebra.
 *
 * @author mstojanovic6119rn
 */
public class Algebra {

    private final Set<Operable> operables = new HashSet<>();

    private final List<Operation> operations = new ArrayList<>();

    /**
     * Creates an <code>Algebra</code> object.
     *
     * @param operables set of <code>Operable</code> objects in a set of the algebra.
     */
    public Algebra(Operable... operables) {
        this.operables.addAll(Arrays.stream(operables).collect(Collectors.toList()));
    }

    private Algebra(Collection<Operable> operables) {
        this.operables.addAll(operables);
    }

    protected Algebra(Collection<Operable> operables, Operation... operations) {
        this(operables);
        for (Operation o : operations) {
            if (!this.addOperation(o))
                throw new RuntimeException();
        }
    }

    private boolean operationPreserved(Operation operation) {
        return this.operables.containsAll(operation.calculations(this.operables).values());
    }

    /**
     * Adding an operation to a list of operations. If the operation is not preserved, then it doesn't
     * do anything.
     *
     * @param operation operation to be added to a list
     * @return <code>true</code> if the operation is preserved, or <code>false</code> in other cases
     */
    public final boolean addOperation(Operation operation) {
        if (!this.operationPreserved(operation))
            return false;
        this.operations.add(operation);
        return true;
    }

    /**
     * Type of this algebra.
     *
     * @return array of types of operations of this algebra.
     */
    public final Operation.Type[] type() {
        Operation.Type[] type = new Operation.Type[this.operations.size()];
        for (int i = 0; i < type.length; ++i)
            type[i] = this.operations.get(i).type();
        return type;
    }

    /**
     * Checks if this and some other algebra are of the same type.
     *
     * @param other the other algebra
     * @return if the algebras are of the same type
     */
    public final boolean isSameType(Algebra other) {
        Operation.Type[] thisType = this.type();
        Operation.Type[] otherType = other.type();
        Arrays.sort(thisType, new TypeComparator());
        Arrays.sort(otherType, new TypeComparator());
        if (thisType.length != otherType.length)
            return false;
        for (int i = 0; i < thisType.length; ++i) {
            if (!thisType.toString().equals(otherType.toString()))
                return false;
        }
        return true;
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public Set<Operable> getOperables() {
        return operables;
    }
}
