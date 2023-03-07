package rs.raf.mstojanovic6119rn.algebra.general.homomorphism;

import rs.raf.mstojanovic6119rn.algebra.general.Algebra;
import rs.raf.mstojanovic6119rn.algebra.general.operable.Operable;

import java.util.function.Function;

public class Endomorphism extends Homomorphism {
    Endomorphism(Algebra a, Function<Operable, Operable> function) {
        super(a, a, function);
    }

    public final boolean isAutomorphism() {
        return super.isIsomorphism();
    }
}
