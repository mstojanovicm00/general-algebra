import rs.raf.mstojanovic6119rn.algebra.general.operable.Operable;
import rs.raf.mstojanovic6119rn.algebra.general.operable.OperablePool;
import rs.raf.mstojanovic6119rn.algebra.general.operation.Constant;

public class Main {
    public static void main(String[] args) {
        Operable operable1 = OperablePool.getOperable(2);
        Operable operable2 = OperablePool.getOperable(3);
        Constant constant = new Constant(2);

    }
}
