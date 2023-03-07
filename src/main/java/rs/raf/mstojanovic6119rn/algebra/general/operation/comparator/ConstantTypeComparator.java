package rs.raf.mstojanovic6119rn.algebra.general.operation.comparator;

import rs.raf.mstojanovic6119rn.algebra.general.operation.Constant;

public class ConstantTypeComparator implements SameClassComparator<Constant.ZeroType> {
    @Override
    public Integer compare(Constant.ZeroType a, Constant.ZeroType b) {
        return 0;
    }
}
