package rs.raf.mstojanovic6119rn.algebra.general.operation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rs.raf.mstojanovic6119rn.algebra.general.operable.Operable;
import rs.raf.mstojanovic6119rn.algebra.general.operable.OperablePool;

import java.util.Map;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class InnerOperationTest {

    private InnerOperation innerOperation;

    @BeforeEach
    void setUp() {
        this.innerOperation = new InnerOperation(5, operables -> {
            int a = (int) operables[0].getContent();
            int b = (int) operables[1].getContent();
            int c = (int) operables[2].getContent();
            int d = (int) operables[3].getContent();
            int e = (int) operables[4].getContent();
            int res = a + b + c + d + e;
            return OperablePool.getOperable(res);
        });
    }

    @Test
    void calculatePass() {
        Operable res = this.innerOperation.calculate(OperablePool.getOperable(2),
                OperablePool.getOperable(3), OperablePool.getOperable(4),
                OperablePool.getOperable(5), OperablePool.getOperable(1));
        assertThat(res.getContent(), instanceOf(int.class));
        assertThat(res.getContent(), equalTo(15));
    }

    @Test
    void calculateDisrespectOfArity() {
        Exception exception = assertThrows(DisrespectOfArityException.class, () -> this.innerOperation
                .calculate(OperablePool.getOperable(2), OperablePool.getOperable(3),
                        OperablePool.getOperable(4)));
        assertThat(exception.getMessage().trim(),
                equalTo("The given 3 operable array is not compatible to 2"));
    }

    @Test
    void calculationsTest() {
        Map<Operable[], Operable> map = this.innerOperation.calculations(Set.of(OperablePool.getOperable(1)));
        assertThat(map.values(), hasSize(1));
        assertThat(map.values(), containsInAnyOrder(OperablePool.getOperable(5)));
    }
}