package rs.raf.mstojanovic6119rn.algebra.general.special;

import rs.raf.mstojanovic6119rn.algebra.general.Algebra;
import rs.raf.mstojanovic6119rn.algebra.general.operable.Operable;
import rs.raf.mstojanovic6119rn.algebra.general.operation.Constant;
import rs.raf.mstojanovic6119rn.algebra.general.operation.InnerOperation;
import rs.raf.mstojanovic6119rn.algebra.general.special.additional.Associable;
import rs.raf.mstojanovic6119rn.algebra.general.special.additional.Commutable;
import rs.raf.mstojanovic6119rn.algebra.general.special.additional.Distributable;

import java.util.Set;

public class Fields {

    public static Algebra createField(Set<Operable> operables,
                                      InnerOperation addition, InnerOperation multiplication) {
        Bodies.Body body = (Bodies.Body) Bodies.createBody(operables, addition, multiplication);
        return body.convertToField();
    }

    private Fields() {

    }

    static class Field extends Algebra implements Commutable, Associable, Distributable {
        Field(Set<Operable> operables,
             InnerOperation addition, InnerOperation minus, Constant zero,
             InnerOperation multiplication, InnerOperation multiplicativeInverse, Constant one) {
            super(operables, addition, multiplication, minus, multiplicativeInverse, zero, one);
        }
        @Override
        public boolean isAssociative() {
            return true;
        }
        @Override
        public boolean isCommutative() {
            return true;
        }
        @Override
        public boolean isDistributive() {
            return true;
        }
    }
}
