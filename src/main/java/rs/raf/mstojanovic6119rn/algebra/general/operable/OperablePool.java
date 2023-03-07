package rs.raf.mstojanovic6119rn.algebra.general.operable;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This class represents an object pool for <code>Operable</code> objects.
 *
 * @author mstojanovic6119rn
 */
public class OperablePool {

    private static final Set<Operable> OPERABLES = new HashSet<>();

    /**
     * Creates an operable with the given <code>content</code>. If the content is null, then it just
     * throws a <code>NullPointerException</code>. If the operable already exists in a set of objects
     * of type <code>Operable</code>, then it returns an existent value, else it returns a newly
     * created operable and that operable is added to a set.
     *
     * @param content value that is to be wrapped with the newly created <code>Operable</code> object
     * @return operable object that wraps the given <code>content</code>
     * @see Operable
     */
    public static Operable getOperable(Object content) {
        if (content == null)
            throw new NullPointerException("Operable content can't be null");
        List<Operable> operables = OPERABLES.stream()
                .filter(o -> o.getContent().hashCode() == content.hashCode()).collect(Collectors.toList());
        if (operables.size() == 1)
            return operables.get(0);
        Operable operable = new Operable(content);
        OPERABLES.add(operable);
        return operable;
    }

    static void clearPool() {
        OPERABLES.clear();
    }

    static int poolSize() {
        return OPERABLES.size();
    }

    private OperablePool() {

    }

}
