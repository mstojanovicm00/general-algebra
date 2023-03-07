package rs.raf.mstojanovic6119rn.algebra.general.operation.comparator;

import rs.raf.mstojanovic6119rn.algebra.general.operation.OuterOperation;

class OuterTypeComparator implements SameClassComparator<OuterOperation.SetType> {
    @Override
    public Integer compare(OuterOperation.SetType a, OuterOperation.SetType b) {
        return Integer.compare(a.getScalarSet().hashCode(), b.getScalarSet().hashCode());
    }
}
