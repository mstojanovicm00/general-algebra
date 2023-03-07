package rs.raf.mstojanovic6119rn.algebra.general.operation.comparator;

import rs.raf.mstojanovic6119rn.algebra.general.operation.InnerOperation;

class InnerTypeComparator implements SameClassComparator<InnerOperation.IntegerType> {
    @Override
    public Integer compare(InnerOperation.IntegerType a, InnerOperation.IntegerType b) {
        return Integer.compare(a.getValue(), b.getValue());
    }
}
