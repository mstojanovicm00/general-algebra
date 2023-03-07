package rs.raf.mstojanovic6119rn.algebra.general.operable;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;
import static rs.raf.mstojanovic6119rn.algebra.general.operable.OperablePool.clearPool;
import static rs.raf.mstojanovic6119rn.algebra.general.operable.OperablePool.poolSize;
import static org.junit.jupiter.api.Assumptions.*;

class OperablePoolTest {

    @BeforeEach
    void setUp() {
        clearPool();
    }

    @Test
    void getOperableNull() {
        assertThrows(NullPointerException.class, () -> OperablePool.getOperable(null));
    }

    @Test
    void getOperableEmpty() {
        assumeTrue(poolSize() == 0);
        Operable operable = OperablePool.getOperable(1);
        assertThat(operable.getContent(), is(equalTo(1)));
        assertThat(poolSize(), is(equalTo(1)));
    }

    @Test
    void getOperableNotExists() {
        assumeTrue(poolSize() == 0);
        OperablePool.getOperable(1);
        assumeTrue(poolSize() == 1);
        Operable operable = OperablePool.getOperable(2);
        assertThat(operable.getContent(), is(equalTo(2)));
        assertThat(poolSize(), is(equalTo(2)));
    }

    @Test
    void getOperableExists() {
        assumeTrue(poolSize() == 0);
        OperablePool.getOperable(1);
        assumeTrue(poolSize() == 1);
        Operable operable = OperablePool.getOperable(1);
        assertThat(operable.getContent(), is(equalTo(1)));
        assertThat(poolSize(), is(equalTo(1)));
    }
}