package rs.raf.mstojanovic6119rn.algebra.general.operation.comparator;

import rs.raf.mstojanovic6119rn.algebra.general.operation.Constant;
import rs.raf.mstojanovic6119rn.algebra.general.operation.InnerOperation;
import rs.raf.mstojanovic6119rn.algebra.general.operation.Operation;
import rs.raf.mstojanovic6119rn.algebra.general.operation.OuterOperation;

interface SameClassComparator<T extends Operation.Type> {
    Integer compare(T a, T b);

    class SameClassComparatorServer {
        public static final SameClassComparatorServer SAME_CLASS_COMPARATOR_SERVER
                = new SameClassComparatorServer();
        private final InnerTypeComparator innerTypeComparator = new InnerTypeComparator();
        private final OuterTypeComparator outerTypeComparator = new OuterTypeComparator();
        private final ConstantTypeComparator constantTypeComparator = new ConstantTypeComparator();
        private SameClassComparatorServer() {

        }
        public SameClassComparator get(Operation.Type operationType) {
            if (operationType instanceof InnerOperation.IntegerType)
                return this.innerTypeComparator;
            if (operationType instanceof OuterOperation.SetType)
                return this.outerTypeComparator;
            if (operationType instanceof Constant.ZeroType)
                return this.constantTypeComparator;
            throw new ClassCastException();
        }
    }
}
