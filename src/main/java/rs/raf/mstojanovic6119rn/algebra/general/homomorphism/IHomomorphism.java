package rs.raf.mstojanovic6119rn.algebra.general.homomorphism;

import rs.raf.mstojanovic6119rn.algebra.general.operable.Operable;

public interface IHomomorphism {
    Operable apply(Operable operable);
    boolean finale();
}
