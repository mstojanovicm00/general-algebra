package primeri;

import rs.raf.mstojanovic6119rn.algebra.general.Algebra;
import rs.raf.mstojanovic6119rn.algebra.general.congruence.Congruence;
import rs.raf.mstojanovic6119rn.algebra.general.homomorphism.Homomorphism;
import rs.raf.mstojanovic6119rn.algebra.general.homomorphism.HomomorphismBuilder;
import rs.raf.mstojanovic6119rn.algebra.general.operable.Operable;
import rs.raf.mstojanovic6119rn.algebra.general.operable.OperablePool;
import rs.raf.mstojanovic6119rn.algebra.general.operation.Constant;
import rs.raf.mstojanovic6119rn.algebra.general.operation.InnerOperation;
import rs.raf.mstojanovic6119rn.algebra.general.operation.Operation;
import rs.raf.mstojanovic6119rn.algebra.general.operation.OuterOperation;
import rs.raf.mstojanovic6119rn.algebra.general.utility.relations.Relation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Primer1 {
    public static void main(String[] args) {
        List<Operable> operables1 = new ArrayList<>(), operables2 = new ArrayList<>(),
                operables3 = Arrays.stream(new Integer[] {20}).map(OperablePool::getOperable)
                        .collect(Collectors.toList());
        Relation.RelationBuilder<Operable, Operable> builder = Relation.RelationBuilder.newRelationBuilder();
        for (int i = 0; i < 10; ++i) {
            operables1.add(OperablePool.getOperable(i));
            operables2.add(OperablePool.getOperable(i + 10));
            builder.addPairInRelation(OperablePool.getOperable(i), OperablePool.getOperable(i));
        }
        Relation<Operable, Operable> relation = builder.toRelation();
        Operation addition1 = new InnerOperation(2, (operables -> {
            int a = (int) operables[0].getContent(), b = (int) operables[1].getContent();
            return OperablePool.getOperable((a + b) % 10);
        }));
        Operation addition2 = new InnerOperation(2, (operables -> {
            int a = (int) operables[0].getContent(), b = (int) operables[1].getContent();
            return OperablePool.getOperable((a + b) % 10 + 10);
        }));
        Operation sampleOuterOperation = new OuterOperation(scalarVectorPair -> scalarVectorPair.second,
                (Operable[]) operables3.toArray());
        Constant constant1 = new Constant(operables1.get(0)), constant2 = new Constant(operables2.get(10));
        Algebra algebra1 = new Algebra((Operable[]) operables1.toArray());
        algebra1.getOperations().addAll(List.of(addition1, sampleOuterOperation, constant1));
        Algebra algebra2 = new Algebra((Operable[]) operables2.toArray());
        algebra2.getOperations().addAll(List.of(addition2, sampleOuterOperation, constant2));
        Homomorphism homomorphism = (Homomorphism) HomomorphismBuilder.createHomomorphism(algebra1, algebra2,
                o -> OperablePool.getOperable((int) o.getContent() + 10));
        homomorphism.isIsomorphism();
        Congruence congruence = Congruence.createConguence(algebra1, relation);
        congruence.areRelated(OperablePool.getOperable(0), OperablePool.getOperable(0));
    }
}
