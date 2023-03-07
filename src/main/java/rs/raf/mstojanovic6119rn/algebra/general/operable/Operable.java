package rs.raf.mstojanovic6119rn.algebra.general.operable;

import java.util.Objects;

/**
 * Operable represents any object that can be operated with. Operable is an operand of
 * algebraic operations.
 *
 * @author mstojanovic6119rn
 */
public class Operable {

    private final Object content;

    Operable(Object content) {
        this.content = content;
    }

    @Override
    public int hashCode() {
        return Objects.hash(content);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj instanceof Operable) {
            Operable other = (Operable) obj;
            return this.content.hashCode() == other.content.hashCode();
        }
        return false;
    }

    @Override
    public String toString() {
        return String.valueOf(this.content);
    }

    /**
     * Getter for field <code>content</code>
     *
     * @return object that is wrapped with this <code>Operable</code>.
     */
    public Object getContent() {
        return content;
    }
}
