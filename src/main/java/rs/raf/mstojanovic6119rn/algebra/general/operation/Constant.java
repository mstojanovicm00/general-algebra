package rs.raf.mstojanovic6119rn.algebra.general.operation;

import rs.raf.mstojanovic6119rn.algebra.general.operable.Operable;
import rs.raf.mstojanovic6119rn.algebra.general.operable.OperablePool;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Represents operations which are considered nullary or constant. They do not take parameters
 * and their result is explicitly given.
 *
 * @author mstojanovic6119rn
 */
public class Constant implements Operation {

    private final Operable operable;

    /**
     * Constructs a <code>Constant</code> object with the given result.
     *
     * @param obj content of the operable which represents the result of this operation
     */
    public Constant(Object obj) {
        this.operable = OperablePool.getOperable(obj);
    }

    @Override
    public Operable calculate(Operable... operables) {
        if (operables.length != 0)
            throw new DisrespectOfArityException(ZeroType.ZERO_TYPE, operables.length);
        return this.operable;
    }

    @Override
    public ZeroType type() {
        return ZeroType.ZERO_TYPE;
    }

    @Override
    public Map<Operable[], Operable> calculations(Set<Operable> operableSet) {
        Map<Operable[], Operable> map = new HashMap<>();
        map.put(new Operable[0], this.calculate());
        return map;
    }

    /**
     * Null object which represents a type of the <code>Constant</code>.
     */
    public static class ZeroType implements Type {
        private static final ZeroType ZERO_TYPE = new ZeroType();
        private ZeroType() {

        }
        @Override
        public String toString() {
            return "0";
        }
        @Override
        public int classId() {
            return 2;
        }
    }
}
