package rs.raf.mstojanovic6119rn.algebra.general;

import rs.raf.mstojanovic6119rn.algebra.general.operable.Operable;

import java.util.Set;

/**
 * An exception that represents a case when some <code>Operable</code> element is not present
 * in a set and is being used as if it was.
 *
 * @author mstojanovic6119rn
 */
public class NotInSetException extends RuntimeException {
    /**
     * Creates an instance of <code>NotInSetException</code> based on an <code>Operable</code>
     * whose presence in a given set is considered false.
     *
     * @param operable operable element not present in a set
     * @param operables set in which an operable is not present
     */
    public NotInSetException(Operable operable, Set<Operable> operables) {
        super("There is no " + operable + " in " + operables);
    }
}
