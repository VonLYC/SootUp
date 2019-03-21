package de.upb.soot.jimple.basic;

import de.upb.soot.jimple.Jimple;
import de.upb.soot.signatures.PrimitiveTypeSignature;
import de.upb.soot.signatures.ReferenceTypeSignature;
import de.upb.soot.signatures.TypeSignature;
import de.upb.soot.signatures.UnknownTypeSignature;
import de.upb.soot.signatures.VoidTypeSignature;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*-
 * #%L
 * Soot - a J*va Optimization Framework
 * %%
 * Copyright (C) 2004 Jennifer Lhotak
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 2.1 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 *
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */

/**
 * Generates locals for Body.
 *
 * @author Linghui Luo
 */
public class LocalGenerator {
  private List<Local> locals = new ArrayList<>();
  private Local thisLocal;
  private Map<Integer, Local> paraLocals = new HashMap<>();

  public LocalGenerator() {}

  /** generate this local with given type */
  public Local generateThisLocal(TypeSignature type) {
    if (this.thisLocal == null) {
      this.thisLocal = generateField(type);
    }
    return this.thisLocal;
  }

  /** generates a new @Local given the type for field. */
  public Local generateField(TypeSignature type) {
    return generate(type, true);
  }

  /** generates a new @Local given the type for local. */
  public Local generateLocal(TypeSignature type) {
    return generate(type, false);
  }

  public Local generateParameterLocal(TypeSignature type, int index) {
    if (!this.paraLocals.containsKey(index)) {
      Local paraLocal = generate(type, false);
      this.paraLocals.put(index, paraLocal);
    }
    return this.paraLocals.get(index);
  }

  private Local generate(TypeSignature type, boolean isField) {
    StringBuilder name = new StringBuilder(7);
    if (!isField) {
      name.append("$");
    }

    if (type.equals(PrimitiveTypeSignature.getIntSignature())) {
      appendNextIntName(name);
    } else if (type.equals(PrimitiveTypeSignature.getByteSignature())) {
      appendNextByteName(name);
    } else if (type.equals(PrimitiveTypeSignature.getShortSignature())) {
      appendNextShortName(name);
    } else if (type.equals(PrimitiveTypeSignature.getBooleanSignature())) {
      appendNextBooleanName(name);
    } else if (type.equals(VoidTypeSignature.getInstance())) {
      appendNextVoidName(name);
    } else if (type.equals(PrimitiveTypeSignature.getCharSignature())) {
      appendNextCharName(name);
    } else if (type.equals(PrimitiveTypeSignature.getDoubleSignature())) {
      appendNextDoubleName(name);
    } else if (type.equals(PrimitiveTypeSignature.getFloatSignature())) {
      appendNextFloatName(name);
    } else if (type.equals(PrimitiveTypeSignature.getLongSignature())) {
      appendNextLongName(name);
    } else if (type instanceof ReferenceTypeSignature) {
      appendNextRefLikeTypeName(name);
    } else if (type.equals(UnknownTypeSignature.getInstance())) {
      appendNextUnknownTypeName(name);
    } else {
      throw new RuntimeException("Unhandled Type of Local variable to Generate - Not Implemented");
    }
    return createLocal(name.toString(), type);
  }

  private int tempInt = 0;
  private int tempVoid = 0;
  private int tempBoolean = 0;
  private int tempLong = 0;
  private int tempDouble = 0;
  private int tempFloat = 0;
  private int tempRefLikeType = 0;
  private int tempByte = 0;
  private int tempShort = 0;
  private int tempChar = 0;
  private int tempUnknownType = 0;

  private void appendNextIntName(StringBuilder name) {
    name.append("i").append(tempInt++);
  }

  private void appendNextCharName(StringBuilder name) {
    name.append("c").append(tempChar++);
  }

  private void appendNextVoidName(StringBuilder name) {
    name.append("v").append(tempVoid++);
  }

  private void appendNextByteName(StringBuilder name) {
    name.append("b").append(tempByte++);
  }

  private void appendNextShortName(StringBuilder name) {
    name.append("s").append(tempShort++);
  }

  private void appendNextBooleanName(StringBuilder name) {
    name.append("z").append(tempBoolean++);
  }

  private void appendNextDoubleName(StringBuilder name) {
    name.append("d").append(tempDouble++);
  }

  private void appendNextFloatName(StringBuilder name) {
    name.append("f").append(tempFloat++);
  }

  private void appendNextLongName(StringBuilder name) {
    name.append("l").append(tempLong++);
  }

  private void appendNextRefLikeTypeName(StringBuilder name) {
    name.append("r").append(tempRefLikeType++);
  }

  private void appendNextUnknownTypeName(StringBuilder name) {
    name.append("u").append(tempUnknownType++);
  }

  private Local createLocal(String name, TypeSignature sootType) {
    Local sootLocal = Jimple.newLocal(name, sootType);
    locals.add(sootLocal);
    return sootLocal;
  }

  /** Return all locals created for the body referenced in this LocalGenrator. */
  public List<Local> getLocals() {
    return this.locals;
  }

  public Local getThisLocal() {
    return this.thisLocal;
  }

  public Local getParameterLocal(int i) {
    return this.paraLocals.get(i);
  }
}
