package rs.raf.mstojanovic6119rn.algebra.general.operation.comparator;

import rs.raf.mstojanovic6119rn.algebra.general.operation.Operation;

import java.util.Comparator;

public class TypeComparator implements Comparator<Operation.Type> {

    private final Comparator<Operation.Type> typeComparator
            = Comparator.comparing(Operation.Type::classId);

    @Override
    public int compare(Operation.Type a, Operation.Type b) {
        int idCompare = typeComparator.compare(a, b);
        if (idCompare != 0)
            return idCompare;
        return SameClassComparator.SameClassComparatorServer
                .SAME_CLASS_COMPARATOR_SERVER
                .get(a).compare(a, b);
    }
}
