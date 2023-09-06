package rs.raf.mstojanovic6119rn.algebra.general.operation.utility;

import rs.raf.mstojanovic6119rn.algebra.general.operable.Operable;
import rs.raf.mstojanovic6119rn.algebra.general.operation.InnerOperation;
import rs.raf.mstojanovic6119rn.algebra.general.operation.OuterOperation;
import rs.raf.mstojanovic6119rn.algebra.general.utility.pairs.Pair;

import java.util.Set;
import java.util.function.Function;

public class OuterOperationUtils {

    public static boolean isDistributiveTo(OuterOperation outerOperation, InnerOperation innerOperation,
                                           Set<Operable> operables, Set<Operable> k) {
        for (Operable alpha : k) {
            for (Operable u : operables) {
                for (Operable v : operables) {
                    Operable left = outerOperation.calculate(alpha, innerOperation.calculate(u, v));
                    Operable right = innerOperation.calculate(
                            outerOperation.calculate(alpha, u), outerOperation.calculate(alpha, v));
                    if (!left.equals(right))
                        return false;
                }
            }
        }
        return true;
    }

    public static boolean isDistributiveTo(OuterOperation outerOperation,
                                           Function<Pair<Operable, Operable>, Operable> scalarOperation,
                                           Set<Operable> operables,
                                           Set<Operable> k) {
        for (Operable alpha : k) {
            for (Operable beta : k) {
                for (Operable u : operables) {
                    Operable left = outerOperation.calculate(
                            scalarOperation.apply(new Pair<>(alpha, beta)), u);
                    Operable right = scalarOperation.apply(new Pair<>(
                            outerOperation.calculate(alpha, u), outerOperation.calculate(beta, u)));
                    if (!left.equals(right))
                        return false;
                }
            }
        }
        return true;
    }

    private OuterOperationUtils() {

    }
}
