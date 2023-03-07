package rs.raf.mstojanovic6119rn.algebra.general.utility;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rs.raf.mstojanovic6119rn.algebra.general.utility.relations.Relation;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;
import static org.mockito.Mockito.*;

class RelationUtilsTest {

    private Set<Integer> set = Set.of(1, 2, 3, 4, 5);

    private Relation<Integer, Integer> relation;

    @BeforeEach
    void setUp() {
        this.relation = mock(Relation.class);
    }

    @Test
    void isReflexiveAllTrue() {
        when(this.relation.isPairInRelation(anyInt(), anyInt())).thenReturn(false);
        when(this.relation.isPairInRelation(1, 1)).thenReturn(true);
        when(this.relation.isPairInRelation(2, 2)).thenReturn(true);
        when(this.relation.isPairInRelation(3, 3)).thenReturn(true);
        when(this.relation.isPairInRelation(4, 4)).thenReturn(true);
        when(this.relation.isPairInRelation(5, 5)).thenReturn(true);
        boolean res = RelationUtils.isReflexive(this.relation, this.set);
        assertTrue(res);
        verify(this.relation, times(5)).isPairInRelation(anyInt(), anyInt());
    }

    @Test
    void isReflexiveOneFalse() {
        when(this.relation.isPairInRelation(anyInt(), anyInt())).thenReturn(false);
        when(this.relation.isPairInRelation(1, 1)).thenReturn(true);
        when(this.relation.isPairInRelation(3, 3)).thenReturn(true);
        when(this.relation.isPairInRelation(4, 4)).thenReturn(true);
        when(this.relation.isPairInRelation(5, 5)).thenReturn(true);
        assumeFalse(this.relation.isPairInRelation(2, 2));
        boolean res = RelationUtils.isReflexive(this.relation, this.set);
        assertFalse(res);
        verify(this.relation, times(2)).isPairInRelation(2, 2);
    }

    @Test
    void isReflexiveAllFalse() {
        when(this.relation.isPairInRelation(anyInt(), anyInt())).thenReturn(false);
        assumeFalse(this.relation.isPairInRelation(1, 1));
        assumeFalse(this.relation.isPairInRelation(2, 2));
        assumeFalse(this.relation.isPairInRelation(3, 3));
        assumeFalse(this.relation.isPairInRelation(4, 4));
        assumeFalse(this.relation.isPairInRelation(5, 5));
        boolean res = RelationUtils.isReflexive(this.relation, this.set);
        assertFalse(res);
    }

    @Test
    void isSymmetricAllTrue() {
        when(this.relation.isPairInRelation(anyInt(), anyInt())).thenReturn(false);
        when(this.relation.isPairInRelation(1, 3)).thenReturn(true);
        when(this.relation.isPairInRelation(3, 1)).thenReturn(true);
        when(this.relation.isPairInRelation(2, 5)).thenReturn(true);
        when(this.relation.isPairInRelation(5, 2)).thenReturn(true);
        boolean res = RelationUtils.isSymmetric(this.relation, this.set);
        assertTrue(res);
        verify(this.relation, times(2)).isPairInRelation(1, 3);
        verify(this.relation, times(2)).isPairInRelation(3, 1);
        verify(this.relation, times(2)).isPairInRelation(2, 5);
        verify(this.relation, times(2)).isPairInRelation(5, 2);
    }

    @Test
    void isSymmetricOneFalse() {
        when(this.relation.isPairInRelation(anyInt(), anyInt())).thenReturn(false);
        when(this.relation.isPairInRelation(1, 3)).thenReturn(true);
        when(this.relation.isPairInRelation(3, 1)).thenReturn(true);
        when(this.relation.isPairInRelation(2, 5)).thenReturn(true);
        assumeFalse(this.relation.isPairInRelation(5, 2));
        boolean res = RelationUtils.isSymmetric(this.relation, this.set);
        assertFalse(res);
        verify(this.relation).isPairInRelation(2, 5);
        verify(this.relation, times(2)).isPairInRelation(5, 2);
    }

    @Test
    void isSymmetricAllFalse() {
        when(this.relation.isPairInRelation(anyInt(), anyInt())).thenReturn(false);
        when(this.relation.isPairInRelation(1, 3)).thenReturn(true);
        when(this.relation.isPairInRelation(2, 5)).thenReturn(true);
        assumeFalse(this.relation.isPairInRelation(3, 1));
        assumeFalse(this.relation.isPairInRelation(5, 2));
        boolean res = RelationUtils.isSymmetric(this.relation, this.set);
        assertFalse(res);
    }

    @Test
    void isAntisymmetricTrue() {
        when(this.relation.isPairInRelation(anyInt(), anyInt())).thenReturn(false);
        when(this.relation.isPairInRelation(1, 1)).thenReturn(true);
        when(this.relation.isPairInRelation(2, 2)).thenReturn(true);
        when(this.relation.isPairInRelation(3, 3)).thenReturn(true);
        when(this.relation.isPairInRelation(4, 4)).thenReturn(true);
        when(this.relation.isPairInRelation(5, 5)).thenReturn(true);
        boolean res = RelationUtils.isAntisymmetric(this.relation, this.set);
        assertTrue(res);
        verify(this.relation, times(2)).isPairInRelation(1, 1);
        verify(this.relation, times(2)).isPairInRelation(2, 2);
        verify(this.relation, times(2)).isPairInRelation(3, 3);
        verify(this.relation, times(2)).isPairInRelation(4, 4);
        verify(this.relation, times(2)).isPairInRelation(5, 5);
    }

    @Test
    void isAntisymmetricFalse() {
        when(this.relation.isPairInRelation(anyInt(), anyInt())).thenReturn(false);
        when(this.relation.isPairInRelation(1, 1)).thenReturn(true);
        when(this.relation.isPairInRelation(2, 2)).thenReturn(true);
        when(this.relation.isPairInRelation(3, 3)).thenReturn(true);
        when(this.relation.isPairInRelation(4, 4)).thenReturn(true);
        when(this.relation.isPairInRelation(5, 5)).thenReturn(true);
        when(this.relation.isPairInRelation(1, 3)).thenReturn(true);
        when(this.relation.isPairInRelation(3, 1)).thenReturn(true);
        boolean res = RelationUtils.isAntisymmetric(this.relation, this.set);
        assertFalse(res);
        verify(this.relation).isPairInRelation(1, 3);
        verify(this.relation).isPairInRelation(3, 1);
    }

    @Test
    void isTransitiveTrue() {
        when(this.relation.isPairInRelation(anyInt(), anyInt())).thenReturn(false);
        when(this.relation.isPairInRelation(1, 2)).thenReturn(true);
        when(this.relation.isPairInRelation(2, 3)).thenReturn(true);
        when(this.relation.isPairInRelation(1, 3)).thenReturn(true);
        boolean res = RelationUtils.isTransitive(this.relation, this.set);
        assertTrue(res);
        verify(this.relation, times(5)).isPairInRelation(1, 2);
        verify(this.relation, times(6)).isPairInRelation(2, 3);
        verify(this.relation, times(6)).isPairInRelation(1, 3);
    }

    @Test
    void isTransitiveFalse() {
        when(this.relation.isPairInRelation(anyInt(), anyInt())).thenReturn(false);
        when(this.relation.isPairInRelation(1, 2)).thenReturn(true);
        when(this.relation.isPairInRelation(2, 3)).thenReturn(true);
        assumeFalse(this.relation.isPairInRelation(1, 3));
        boolean res = RelationUtils.isTransitive(this.relation, this.set);
        assertFalse(res);
    }

    @Test
    void isOrderTrue() {
        when(this.relation.isPairInRelation(anyInt(), anyInt())).thenReturn(false);
        when(this.relation.isPairInRelation(1, 1)).thenReturn(true);
        when(this.relation.isPairInRelation(1, 2)).thenReturn(true);
        when(this.relation.isPairInRelation(1, 3)).thenReturn(true);
        when(this.relation.isPairInRelation(1, 4)).thenReturn(true);
        when(this.relation.isPairInRelation(1, 5)).thenReturn(true);
        when(this.relation.isPairInRelation(2, 2)).thenReturn(true);
        when(this.relation.isPairInRelation(2, 3)).thenReturn(true);
        when(this.relation.isPairInRelation(2, 4)).thenReturn(true);
        when(this.relation.isPairInRelation(2, 5)).thenReturn(true);
        when(this.relation.isPairInRelation(3, 3)).thenReturn(true);
        when(this.relation.isPairInRelation(3, 4)).thenReturn(true);
        when(this.relation.isPairInRelation(3, 5)).thenReturn(true);
        when(this.relation.isPairInRelation(4, 4)).thenReturn(true);
        when(this.relation.isPairInRelation(4, 5)).thenReturn(true);
        when(this.relation.isPairInRelation(5, 5)).thenReturn(true);
        assumeTrue(RelationUtils.isReflexive(this.relation, this.set));
        assumeTrue(RelationUtils.isAntisymmetric(this.relation, this.set));
        assumeTrue(RelationUtils.isTransitive(this.relation, this.set));
        boolean res = RelationUtils.isOrder(this.relation, this.set);
        assertTrue(res);
    }

    @Test
    void isOrderNotReflexive() {
        when(this.relation.isPairInRelation(anyInt(), anyInt())).thenReturn(false);
        when(this.relation.isPairInRelation(1, 2)).thenReturn(true);
        when(this.relation.isPairInRelation(1, 3)).thenReturn(true);
        when(this.relation.isPairInRelation(1, 4)).thenReturn(true);
        when(this.relation.isPairInRelation(1, 5)).thenReturn(true);
        when(this.relation.isPairInRelation(2, 3)).thenReturn(true);
        when(this.relation.isPairInRelation(2, 4)).thenReturn(true);
        when(this.relation.isPairInRelation(2, 5)).thenReturn(true);
        when(this.relation.isPairInRelation(3, 4)).thenReturn(true);
        when(this.relation.isPairInRelation(3, 5)).thenReturn(true);
        when(this.relation.isPairInRelation(4, 5)).thenReturn(true);
        assumeFalse(RelationUtils.isReflexive(this.relation, this.set));
        boolean res = RelationUtils.isOrder(this.relation, this.set);
        assertFalse(res);
    }

    @Test
    void isOrderNotAntisymmetric() {
        when(this.relation.isPairInRelation(anyInt(), anyInt())).thenReturn(false);
        when(this.relation.isPairInRelation(1, 1)).thenReturn(true);
        when(this.relation.isPairInRelation(1, 2)).thenReturn(true);
        when(this.relation.isPairInRelation(1, 3)).thenReturn(true);
        when(this.relation.isPairInRelation(1, 4)).thenReturn(true);
        when(this.relation.isPairInRelation(1, 5)).thenReturn(true);
        when(this.relation.isPairInRelation(2, 2)).thenReturn(true);
        when(this.relation.isPairInRelation(2, 3)).thenReturn(true);
        when(this.relation.isPairInRelation(2, 4)).thenReturn(true);
        when(this.relation.isPairInRelation(2, 5)).thenReturn(true);
        when(this.relation.isPairInRelation(3, 3)).thenReturn(true);
        when(this.relation.isPairInRelation(3, 4)).thenReturn(true);
        when(this.relation.isPairInRelation(3, 5)).thenReturn(true);
        when(this.relation.isPairInRelation(4, 4)).thenReturn(true);
        when(this.relation.isPairInRelation(4, 5)).thenReturn(true);
        when(this.relation.isPairInRelation(5, 4)).thenReturn(true);
        when(this.relation.isPairInRelation(5, 5)).thenReturn(true);
        assumeFalse(RelationUtils.isAntisymmetric(this.relation, this.set));
        boolean res = RelationUtils.isOrder(this.relation, this.set);
        assertFalse(res);
    }

    @Test
    void isOrderNotTransitive() {
        when(this.relation.isPairInRelation(anyInt(), anyInt())).thenReturn(false);
        when(this.relation.isPairInRelation(1, 1)).thenReturn(true);
        when(this.relation.isPairInRelation(1, 2)).thenReturn(true);
        when(this.relation.isPairInRelation(1, 3)).thenReturn(true);
        when(this.relation.isPairInRelation(1, 4)).thenReturn(true);
        when(this.relation.isPairInRelation(1, 5)).thenReturn(true);
        when(this.relation.isPairInRelation(2, 2)).thenReturn(true);
        when(this.relation.isPairInRelation(2, 3)).thenReturn(true);
        when(this.relation.isPairInRelation(2, 4)).thenReturn(true);
        when(this.relation.isPairInRelation(2, 5)).thenReturn(true);
        when(this.relation.isPairInRelation(3, 3)).thenReturn(true);
        when(this.relation.isPairInRelation(3, 4)).thenReturn(true);
        when(this.relation.isPairInRelation(4, 4)).thenReturn(true);
        when(this.relation.isPairInRelation(4, 5)).thenReturn(true);
        when(this.relation.isPairInRelation(5, 4)).thenReturn(true);
        when(this.relation.isPairInRelation(5, 5)).thenReturn(true);
        assumeFalse(RelationUtils.isTransitive(this.relation, this.set));
        boolean res = RelationUtils.isOrder(this.relation, this.set);
        assertFalse(res);
    }

    @Test
    void isEquivalenceTrue() {
        when(this.relation.isPairInRelation(anyInt(), anyInt())).thenReturn(false);
        /// equivalence class (1, 3, 5)
        when(this.relation.isPairInRelation(1, 1)).thenReturn(true);
        when(this.relation.isPairInRelation(1, 3)).thenReturn(true);
        when(this.relation.isPairInRelation(1, 5)).thenReturn(true);
        when(this.relation.isPairInRelation(3, 1)).thenReturn(true);
        when(this.relation.isPairInRelation(3, 3)).thenReturn(true);
        when(this.relation.isPairInRelation(3, 5)).thenReturn(true);
        when(this.relation.isPairInRelation(5, 1)).thenReturn(true);
        when(this.relation.isPairInRelation(5, 3)).thenReturn(true);
        when(this.relation.isPairInRelation(5, 5)).thenReturn(true);
        /// equivalence class (2, 4)
        when(this.relation.isPairInRelation(2, 2)).thenReturn(true);
        when(this.relation.isPairInRelation(2, 4)).thenReturn(true);
        when(this.relation.isPairInRelation(4, 2)).thenReturn(true);
        when(this.relation.isPairInRelation(4, 4)).thenReturn(true);
        assumeTrue(RelationUtils.isReflexive(this.relation, this.set));
        assumeTrue(RelationUtils.isSymmetric(this.relation, this.set));
        assumeTrue(RelationUtils.isTransitive(this.relation, this.set));
        boolean res = RelationUtils.isEquivalence(this.relation, this.set);
        assertTrue(res);
    }

    @Test
    void isEquivalenceNotReflexive() {
        when(this.relation.isPairInRelation(anyInt(), anyInt())).thenReturn(false);
        /// equivalence class (1, 3, 5), no (3, 3)
        when(this.relation.isPairInRelation(1, 1)).thenReturn(true);
        when(this.relation.isPairInRelation(1, 3)).thenReturn(true);
        when(this.relation.isPairInRelation(1, 5)).thenReturn(true);
        when(this.relation.isPairInRelation(3, 1)).thenReturn(true);
        when(this.relation.isPairInRelation(3, 5)).thenReturn(true);
        when(this.relation.isPairInRelation(5, 1)).thenReturn(true);
        when(this.relation.isPairInRelation(5, 3)).thenReturn(true);
        when(this.relation.isPairInRelation(5, 5)).thenReturn(true);
        /// equivalence class (2, 4)
        when(this.relation.isPairInRelation(2, 2)).thenReturn(true);
        when(this.relation.isPairInRelation(2, 4)).thenReturn(true);
        when(this.relation.isPairInRelation(4, 2)).thenReturn(true);
        when(this.relation.isPairInRelation(4, 4)).thenReturn(true);
        assumeFalse(RelationUtils.isReflexive(this.relation, this.set));
        boolean res = RelationUtils.isEquivalence(this.relation, this.set);
        assertFalse(res);
    }

    @Test
    void isEquivalenceNotSymmetric() {
        when(this.relation.isPairInRelation(anyInt(), anyInt())).thenReturn(false);
        /// equivalence class (1, 3, 5), no (3, 1)
        when(this.relation.isPairInRelation(1, 1)).thenReturn(true);
        when(this.relation.isPairInRelation(1, 3)).thenReturn(true);
        when(this.relation.isPairInRelation(1, 5)).thenReturn(true);
        when(this.relation.isPairInRelation(3, 3)).thenReturn(true);
        when(this.relation.isPairInRelation(3, 5)).thenReturn(true);
        when(this.relation.isPairInRelation(5, 1)).thenReturn(true);
        when(this.relation.isPairInRelation(5, 3)).thenReturn(true);
        when(this.relation.isPairInRelation(5, 5)).thenReturn(true);
        /// equivalence class (2, 4)
        when(this.relation.isPairInRelation(2, 2)).thenReturn(true);
        when(this.relation.isPairInRelation(2, 4)).thenReturn(true);
        when(this.relation.isPairInRelation(4, 2)).thenReturn(true);
        when(this.relation.isPairInRelation(4, 4)).thenReturn(true);
        assumeFalse(RelationUtils.isSymmetric(this.relation, this.set));
        boolean res = RelationUtils.isEquivalence(this.relation, this.set);
        assertFalse(res);
    }

    @Test
    void isEquivalenceNotTransitive() {
        when(this.relation.isPairInRelation(anyInt(), anyInt())).thenReturn(false);
        /// equivalence class (1, 3, 5), no (3, 5) and (5, 3)
        when(this.relation.isPairInRelation(1, 1)).thenReturn(true);
        when(this.relation.isPairInRelation(1, 3)).thenReturn(true);
        when(this.relation.isPairInRelation(1, 5)).thenReturn(true);
        when(this.relation.isPairInRelation(3, 1)).thenReturn(true);
        when(this.relation.isPairInRelation(3, 3)).thenReturn(true);
        when(this.relation.isPairInRelation(5, 1)).thenReturn(true);
        when(this.relation.isPairInRelation(5, 5)).thenReturn(true);
        /// equivalence class (2, 4)
        when(this.relation.isPairInRelation(2, 2)).thenReturn(true);
        when(this.relation.isPairInRelation(2, 4)).thenReturn(true);
        when(this.relation.isPairInRelation(4, 2)).thenReturn(true);
        when(this.relation.isPairInRelation(4, 4)).thenReturn(true);
        assumeFalse(RelationUtils.isTransitive(this.relation, this.set));
        boolean res = RelationUtils.isEquivalence(this.relation, this.set);
        assertFalse(res);
    }
}