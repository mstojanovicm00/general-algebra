package rs.raf.mstojanovic6119rn.algebra.general.homomorphism;

import rs.raf.mstojanovic6119rn.algebra.general.Algebra;
import rs.raf.mstojanovic6119rn.algebra.general.operable.Operable;
import rs.raf.mstojanovic6119rn.algebra.general.operation.Operation;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class HomomorphismBuilder implements IHomomorphism {

    public static HomomorphismBuilder homomorphismBuilder() {
        return new HomomorphismBuilder();
    }

    public static IHomomorphism createHomomorphism(
            Algebra a, Algebra b, Function<Operable, Operable> function) {
        return homomorphismBuilder().setAlgebras(a, b).setFunction(function);
    }

    private Algebra a, b;

    private String message;

    private HomomorphismBuilder() {

    }

    public HomomorphismBuilder setAlgebras(Algebra a, Algebra b) {
        if (a.isSameType(b)) {
            this.a = a;
            this.b = b;
            this.message = "Everything is OK";
        } else
            this.message = "Couldn't set algebras because they are not of the same type";
        return this;
    }

    public IHomomorphism setFunction(Function<Operable, Operable> function) {
        if (this.a == null) {
            this.message = "Couldn't set function because the algebras are not set yet";
            return this;
        }
        for (int i = 0; i < this.a.getOperations().size(); ++i) {
            Operation oa = this.a.getOperations().get(i);
            Operation ob = this.b.getOperations().get(i);
            Map<Operable[], Operable> operableMap = oa.calculations(this.a.getOperables());
            List<Operable[]> leftUp = operableMap.keySet().stream().collect(Collectors.toList());
            List<Operable[]> leftDown = leftUp.stream().map(
                    operables -> {
                        Operable[] ld = new Operable[operables.length];
                        for (int j = 0; j < ld.length; ++j)
                            ld[j] = function.apply(operables[j]);
                        return ld;
                    }
            ).collect(Collectors.toList());
            List<Operable> rightUp = leftUp.stream()
                    .map(oa::calculate).collect(Collectors.toList());
            List<Operable> rightDown1 = rightUp.stream()
                    .map(function).collect(Collectors.toList());
            List<Operable> rightDown2 = leftDown.stream()
                    .map(ob::calculate).collect(Collectors.toList());
            if (!rightDown1.equals(rightDown2)) {
                this.message = "Couldn't set function because one or more operations are not preserved";
                return this;
            }
        }
        if (this.a == this.b)
            return new Endomorphism(this.a, function);
        return new Homomorphism(this.a, this.b, function);
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return this.message;
    }

    @Override
    public Operable apply(Operable operable) {
        throw new NotAHomomorphismException(this);
    }

    @Override
    public final boolean finale() {
        return false;
    }
}
