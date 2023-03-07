package rs.raf.mstojanovic6119rn.algebra.general.utility;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

/**
 * Utility class that has some functions for checking some properties of a function
 *
 * @author mstojanovic6119rn
 */
public class FunctionUtils {

    /**
     * Checks if the given function is an injection on a given set. A function is an injection if
     * all the elements of a given set give different results when the function is applied on them.
     *
     * @param function function whose injection property is being checked
     * @param originals set on which a property of a function is being checked
     * @return <code>true</code> if the function is an injection, otherwise false
     * @param <A> type of originals
     * @param <B> type of images
     */
    public static<A, B> boolean isInjection(Function<A, B> function, Set<A> originals) {
        Set<B> subsetOfImages = new HashSet<>();
        for (A original: originals) {
            B image = function.apply(original);
            if (subsetOfImages.contains(image))
                return false;
            subsetOfImages.add(image);
        }
        return true;
    }

    /**
     * Checks if the given function is a surjection on a given set. A function is a surjection if
     * the result set of a function applied on all the originals spans an entire set of images.
     *
     * @param function function whose surjection property is being checked
     * @param originals set on which a property of a function is being checked
     * @param images set that is checked to be an images set for a given function
     * @return <code>true</code> if the function is a surjection, otherwise false
     * @param <A> type of originals
     * @param <B> type of images
     */
    public static<A, B> boolean isSurjection(Function<A, B> function,
                                             Set<A> originals, Set<B> images) {
        Set<B> subsetOfImages = new HashSet<>();
        for (A original: originals)
            subsetOfImages.add(function.apply(original));
        return subsetOfImages.containsAll(images)
                && images.containsAll(subsetOfImages);
    }

    /**
     * Checks if the given function is a bijection on a given set. A function is a bijection if
     * it's an injection and a surjection.
     *
     * @param function function whose bijection property is being checked
     * @param originals set on which a property of a function is being checked
     * @param images set that is checked to be an images set for a given function
     * @return <code>true</code> if the function is a bijection, otherwise false
     * @param <A> type of originals
     * @param <B> type of images
     */
    public static<A, B> boolean isBijection(Function<A, B> function,
                                            Set<A> originals, Set<B> images) {
        return isInjection(function, originals) && isSurjection(function, originals, images);
    }

    private FunctionUtils() {

    }
}
