package rs.raf.mstojanovic6119rn.algebra.general.utility.pairs;

/**
 * A utility class that wraps two objects in an ordered pair.
 *
 * @param <A> type of the first object in an ordered pair
 * @param <B> type of the second object in an ordered pair
 *
 * @author mstojanovic6119rn
 */
public class Pair<A, B> {

    /**
     * First object in an ordered pair
     */
    public final A first;

    /**
     * Second object in an ordered pair
     */
    public final B second;

    /**
     * Constructs a pair, gives each of the field values that are given through parameters
     *
     * @param first first object in an ordered pair
     * @param second second object in an ordered pair
     */
    public Pair(A first, B second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Pair))
            return false;
        Pair pair = (Pair) obj;
        return pair.first.equals(pair.first) && pair.second.equals(pair.second);
    }
}
