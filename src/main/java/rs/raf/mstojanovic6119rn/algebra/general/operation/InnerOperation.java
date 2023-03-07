package rs.raf.mstojanovic6119rn.algebra.general.operation;

import rs.raf.mstojanovic6119rn.algebra.general.operable.Operable;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

/**
 * Represents an inner operation. Inner algebraic operation is an operation which takes arguments
 * which are all contained in the same set and returns a result which is also in that set.
 *
 * @author mstojanovic6119rn
 */
public class InnerOperation implements Operation {

    private final IntegerType arity;

    private final Function<Operable[], Operable> function;

    /**
     * Creates an inner operation object.
     *
     * @param arity number of arguments that an operation takes
     * @param function function which is called in order to retrieve a result of the operation
     *                 for the given arguments
     */
    public InnerOperation(int arity, Function<Operable[], Operable> function) {
        this.arity = new IntegerType(arity);
        this.function = function;
    }

    @Override
    public Operable calculate(Operable... operables) {
        if (this.arity.value == operables.length)
            return this.function.apply(operables);
        throw new DisrespectOfArityException(this.arity, operables.length);
    }

    @Override
    public Type type() {
        return this.arity;
    }

    @Override
    public Map<Operable[], Operable> calculations(Set<Operable> operableSet) {
       return this.calculations(operableSet, this.arity.value, new HashMap<>());
    }

    private Map<Operable[], Operable> calculations(Set<Operable> operableSet, int arity,
                                                   Map<Operable[], Operable> map) {
        if (arity == 1) {
            Map<Operable[], Operable> operableMap = new HashMap<>();
            for (Operable o: operableSet)
                operableMap.put(new Operable[]{o}, o);
            return operableMap;
        }
        for (Operable o: operableSet) {
            Map<Operable[], Operable> recursiveMap = this.calculations(
                    operableSet, arity - 1, new HashMap<>());
            for (Map.Entry<Operable[], Operable> entry: recursiveMap.entrySet()) {
                Operable[] operablesHelp = entry.getKey();
                Operable[] operables = new Operable[operablesHelp.length + 1];
                operables[0] = o;
                for (int i = 1; i < operables.length; ++i)
                    operables[i] = operablesHelp[i - 1];
                if (operables.length == this.arity.value)
                    map.put(operables, this.calculate(operables));
                else
                    map.put(operables, operables[0]);
            }
        }
        return map;
    }

    /**
     * Represents an inner operation type.
     *
     * @author mstojanovic6119rn
     */
    public static class IntegerType implements Type {
        private final int value;
        /**
         * Creates an instance of the inner operation type.
         *
         * @param value arity of the operation
         */
        public IntegerType(int value) {
            this.value = value;
        }
        @Override
        public String toString() {
            return String.valueOf(this.value);
        }
        @Override
        public int classId() {
            return 0;
        }
        public int getValue() {
            return value;
        }
    }
}
