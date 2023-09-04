package rs.raf.mstojanovic6119rn.algebra.general.operation;

import rs.raf.mstojanovic6119rn.algebra.general.operable.Operable;
import rs.raf.mstojanovic6119rn.algebra.general.utility.pairs.Pair;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Represents an outer operation. Outer algebraic operation is a subtype of operations that has exactly
 * two operands. One of those operands is called a 'scalar', the other one is called a 'vector'. Scalar
 * is an operable from the set that defines a type of this operation and a vector is contained in the
 * same set that the result is contained.
 *
 * @author mstojanovic6119rn
 */
public class OuterOperation implements Operation {

    private final SetType scalarSet;

    private final Function<ScalarVectorPair, Operable> function;

    /**
     * Creates an outer algebraic operation object.
     *
     * @param function function of an operation
     * @param operables set of <code>Operable</code> objects
     */
    public OuterOperation(Function<ScalarVectorPair, Operable> function, Operable... operables) {
        this.scalarSet = new SetType(new HashSet<>(Arrays.stream(operables).collect(Collectors.toList())));
        this.function = function;
    }

    @Override
    public Operable calculate(Operable... operables) {
        if (operables.length != 2)
            throw new DisrespectOfArityException(this.scalarSet, operables.length);
        ScalarVectorPair scalarVectorPair = new ScalarVectorPair(operables[0], operables[1]);
        if (this.scalarSet.scalarSet.contains(scalarVectorPair.first))
            return this.function.apply(scalarVectorPair);
        throw new DisrespectOfArityException(this.scalarSet, scalarVectorPair.first);
    }

    @Override
    public SetType type() {
        return this.scalarSet;
    }

    @Override
    public Map<Operable[], Operable> calculations(Set<Operable> operableSet) {
        Map<Operable[], Operable> map = new HashMap<>();
        for (Operable scalar: this.scalarSet.scalarSet) {
            for (Operable vector: operableSet) {
                map.put(new Operable[]{scalar, vector}, this.calculate(scalar, vector));
            }
        }
        return map;
    }

    /**
     * Input type for any outer operation. Contains a scalar and a vector.
     *
     * @author mstojanovic6119rn
     */
    public static class ScalarVectorPair extends Pair<Operable, Operable> {
        /**
         * Creates a <code>ScalarVectorPair</code> object.
         *
         * @param scalar scalar part of a pair
         * @param vector vector part of a pair
         */
        public ScalarVectorPair(Operable scalar, Operable vector) {
            super(scalar, vector);
        }
    }

    /**
     * Outer operation type. Defined by a set of possible values for the scalar operand.
     *
     * @author mstojanovic6119rn
     */
    public static class SetType implements Type {
        private final Set<Operable> scalarSet;
        /**
         * Creates an outer algebraic operation type.
         *
         * @param scalarSet set of possible values for the scalar operand in the operation
         *                  of this type
         */
        public SetType(Set<Operable> scalarSet) {
            this.scalarSet = scalarSet;
        }
        @Override
        public String toString() {
            return this.scalarSet.toString();
        }
        @Override
        public int classId() {
            return 1;
        }
        public Set<Operable> getScalarSet() {
            return scalarSet;
        }
    }
}
