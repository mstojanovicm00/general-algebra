package rs.raf.mstojanovic6119rn.algebra.general.homomorphism;

public class NotAHomomorphismException extends RuntimeException {
    public NotAHomomorphismException(IHomomorphism iHomomorphism) {
        super(iHomomorphism.toString());
    }
}
