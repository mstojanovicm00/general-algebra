package rs.raf.mstojanovic6119rn.algebra.general.operation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rs.raf.mstojanovic6119rn.algebra.general.operable.Operable;
import rs.raf.mstojanovic6119rn.algebra.general.operable.OperablePool;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class OuterOperationTest {

    private OuterOperation outerOperation;

    @BeforeEach
    void setUp() {
        this.outerOperation = new OuterOperation(scalarVectorPair -> {
            int scalar = (int) scalarVectorPair.first.getContent();
            int vector = (int) scalarVectorPair.second.getContent();
            int res = scalar * vector;
            return OperablePool.getOperable(res);
        }, OperablePool.getOperable(1), OperablePool.getOperable(2),
                OperablePool.getOperable(3), OperablePool.getOperable(4),
                OperablePool.getOperable(5));
    }

    @Test
    void calculateTestPass() {
        Operable res = this.outerOperation.calculate(OperablePool.getOperable(3), OperablePool.getOperable(4));
        assertThat(res.getContent(), instanceOf(int.class));
        assertThat(res, equalTo(OperablePool.getOperable(12)));
    }

    @Test
    void calculateTestDisrespectOfArityByOperableArrayLength() {
        Exception ex = assertThrows(DisrespectOfArityException.class,
                () -> this.outerOperation.calculate(OperablePool.getOperable(1),
                        OperablePool.getOperable(2), OperablePool.getOperable(3)));
        assertThat(ex.getMessage(), startsWith("The given 3 operable array is not compatible to "));
    }

    @Test
    void calculateTestDisrespectOfArityScalarNotInASet() {
        Exception ex = assertThrows(DisrespectOfArityException.class,
                () -> this.outerOperation.calculate(OperablePool.getOperable(10),
                        OperablePool.getOperable(1)));
        assertThat(ex.getMessage(), startsWith("The given 10 operable array is not compatible to "));
    }
}