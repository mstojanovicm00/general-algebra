package rs.raf.mstojanovic6119rn.algebra.general.homomorphism;

import rs.raf.mstojanovic6119rn.algebra.general.Algebra;
import rs.raf.mstojanovic6119rn.algebra.general.NotInSetException;
import rs.raf.mstojanovic6119rn.algebra.general.operable.Operable;
import rs.raf.mstojanovic6119rn.algebra.general.utility.FunctionUtils;

import java.util.function.Function;

public class Homomorphism implements IHomomorphism {

    private final Algebra a, b;

    private final Function<Operable, Operable> function;

    Homomorphism(Algebra a, Algebra b, Function<Operable, Operable> function) {
        this.a = a;
        this.b = b;
        this.function = function;
    }

    public final boolean isMonomorphism() {
        return FunctionUtils.isInjection(this.function, this.a.getOperables());
    }

    public final boolean isEpimorphism() {
        return FunctionUtils.isSurjection(this.function, this.a.getOperables(), this.b.getOperables());
    }

    public final boolean isIsomorphism() {
        return FunctionUtils.isBijection(this.function, this.a.getOperables(), this.b.getOperables());
    }

    @Override
    public Operable apply(Operable operable) {
        if (this.a.getOperables().contains(operable))
            return this.function.apply(operable);
        throw new NotInSetException(operable, this.a.getOperables());
    }

    @Override
    public final boolean finale() {
        return true;
    }
}
