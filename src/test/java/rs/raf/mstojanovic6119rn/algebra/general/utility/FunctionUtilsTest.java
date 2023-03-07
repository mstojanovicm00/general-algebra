package rs.raf.mstojanovic6119rn.algebra.general.utility;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;
import static org.mockito.Mockito.*;
import static rs.raf.mstojanovic6119rn.algebra.general.utility.FunctionUtils.*;

class FunctionUtilsTest {

    private Set<Integer> originals = Set.of(1, 2, 3, 4, 5);

    private Set<Double> images;

    private Function<Integer, Double> function;

    @BeforeEach
    void setUp() {
        this.function = mock(Function.class);
    }

    @Test
    void isInjectionTrue() {
        when(this.function.apply(1)).thenReturn(0.1);
        when(this.function.apply(2)).thenReturn(0.2);
        when(this.function.apply(3)).thenReturn(0.3);
        when(this.function.apply(4)).thenReturn(0.4);
        when(this.function.apply(5)).thenReturn(0.6);
        boolean res = isInjection(this.function, this.originals);
        assertTrue(res);
        verify(this.function, times(5)).apply(anyInt());
    }

    @Test
    void isInjectionFalse() {
        when(this.function.apply(1)).thenReturn(0.1);
        when(this.function.apply(2)).thenReturn(0.2);
        when(this.function.apply(3)).thenReturn(0.6);
        when(this.function.apply(4)).thenReturn(0.4);
        when(this.function.apply(5)).thenReturn(0.6);
        boolean res = isInjection(this.function, this.originals);
        assertFalse(res);
        verify(this.function).apply(3);
        verify(this.function).apply(5);
    }

    @Test
    void isSurjectionTrue() {
        this.images = Set.of(0.1, 0.2, 0.3);
        when(this.function.apply(1)).thenReturn(0.1);
        when(this.function.apply(2)).thenReturn(0.2);
        when(this.function.apply(3)).thenReturn(0.1);
        when(this.function.apply(4)).thenReturn(0.3);
        when(this.function.apply(5)).thenReturn(0.2);
        boolean res = isSurjection(this.function, this.originals, this.images);
        assertTrue(res);
        verify(this.function, times(5)).apply(anyInt());
    }

    @Test
    void isSurjectionFalse() {
        this.images = Set.of(0.1, 0.2, 0.3, 0.4);
        when(this.function.apply(1)).thenReturn(0.1);
        when(this.function.apply(2)).thenReturn(0.2);
        when(this.function.apply(3)).thenReturn(0.1);
        when(this.function.apply(4)).thenReturn(0.3);
        when(this.function.apply(5)).thenReturn(0.2);
        boolean res = isSurjection(this.function, this.originals, this.images);
        assertFalse(res);
        verify(this.function, times(5)).apply(anyInt());
    }

    @Test
    void isBijectionTrue() {
        this.images = Set.of(0.1, 0.2, 0.3, 0.4, 0.5);
        when(this.function.apply(1)).thenReturn(0.1);
        when(this.function.apply(2)).thenReturn(0.3);
        when(this.function.apply(3)).thenReturn(0.4);
        when(this.function.apply(4)).thenReturn(0.5);
        when(this.function.apply(5)).thenReturn(0.2);
        assumeTrue(isInjection(this.function, this.originals));
        assumeTrue(isSurjection(this.function, this.originals, this.images));
        boolean res = isBijection(this.function, this.originals, this.images);
        assertTrue(res);
    }

    @Test
    void isBijectionFalseNotAnInjection() {
        this.images = Set.of(0.1, 0.2, 0.3, 0.4);
        when(this.function.apply(1)).thenReturn(0.1);
        when(this.function.apply(2)).thenReturn(0.3);
        when(this.function.apply(3)).thenReturn(0.4);
        when(this.function.apply(4)).thenReturn(0.2);
        when(this.function.apply(5)).thenReturn(0.2);
        assumeFalse(isInjection(this.function, this.originals));
        assumeTrue(isSurjection(this.function, this.originals, this.images));
        boolean res = isBijection(this.function, this.originals, this.images);
        assertFalse(res);
    }

    @Test
    void isBijectionFalseNotASurjection() {
        this.images = Set.of(0.1, 0.2, 0.3, 0.4, 0.5, 0.6);
        when(this.function.apply(1)).thenReturn(0.1);
        when(this.function.apply(2)).thenReturn(0.3);
        when(this.function.apply(3)).thenReturn(0.4);
        when(this.function.apply(4)).thenReturn(0.5);
        when(this.function.apply(5)).thenReturn(0.2);
        assumeTrue(isInjection(this.function, this.originals));
        assumeFalse(isSurjection(this.function, this.originals, this.images));
        boolean res = isBijection(this.function, this.originals, this.images);
        assertFalse(res);
    }
}