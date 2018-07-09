package de.upb.soot.jimple.common.type;

import de.upb.soot.Scene;
import de.upb.soot.jimple.basic.Numberable;
import de.upb.soot.jimple.visitor.IAcceptor;

import java.io.Serializable;

/**
 * Represents types within Soot, eg <code>int</code>, <code>java.lang.String</code>.
 * 
 *
 */
@SuppressWarnings("serial")
public abstract class Type implements IAcceptor, Serializable, Numberable {
  public Type() {
    Scene.getInstance().getTypeNumberer().add(this);
  }

  /** Returns a textual representation of this type. */
  @Override
  public abstract String toString();

  /** Returns a textual (and quoted as needed) representation of this type for serialization, e.g. to .jimple format */
  public String toQuotedString() {
    return toString();
  }

  /**
   * Returns a textual (and quoted as needed) representation of this type for serialization, e.g. to .jimple format Replaced
   * by toQuotedString; only here for backwards compatibility.
   */
  @Deprecated
  public String getEscapedName() {
    return toQuotedString();
  }

  /** Converts the int-like types (short, byte, boolean and char) to IntType. */
  public static Type toMachineType(Type t) {
    if (t.equals(ShortType.getInstance()) || t.equals(ByteType.getInstance()) || t.equals(BooleanType.getInstance())
        || t.equals(CharType.getInstance())) {
      return IntType.getInstance();
    } else {
      return t;
    }
  }

  /** Returns the least common superclass of this type and other. */
  public Type merge(Type other, Scene cm) {
    // method overriden in subclasses UnknownType and RefType
    throw new RuntimeException("illegal type merge: " + this + " and " + other);
  }

  public void setArrayType(ArrayType at) {
    arrayType = at;
  }

  public ArrayType getArrayType() {
    return arrayType;
  }

  public ArrayType makeArrayType() {
    return ArrayType.getInstance(this, 1);
  }

  /**
   * Returns <code>true</code> if this type is allowed to appear in final (clean) Jimple code.
   * 
   * @return
   */
  public boolean isAllowedInFinalCode() {
    return false;
  }

  @Override
  public final int getNumber() {
    return number;
  }

  @Override
  public final void setNumber(int number) {
    this.number = number;
  }

  protected ArrayType arrayType;
  private int number = 0;
}
