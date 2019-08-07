/* Soot - a J*va Optimization Framework
 * Copyright (C) 1997-1999 Etienne Gagnon
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the
 * Free Software Foundation, Inc., 59 Temple Place - Suite 330,
 * Boston, MA 02111-1307, USA.
 */

/*
 * Modified by the Sable Research Group and others 1997-1999.
 * See the 'credits' file distributed with Soot for the complete list of
 * contributors.  (Soot is distributed at http://www.sable.mcgill.ca/soot)
 */

package de.upb.soot.jimple.common.stmt;

import de.upb.soot.jimple.basic.PositionInfo;
import de.upb.soot.jimple.basic.Value;
import de.upb.soot.jimple.basic.ValueBox;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractOpStmt extends AbstractStmt {

  protected final ValueBox opBox;

  protected AbstractOpStmt(ValueBox opBox, PositionInfo positionInfo) {
    super(positionInfo);
    this.opBox = opBox;
  }

  public final Value getOp() {
    return opBox.getValue();
  }

  public final ValueBox getOpBox() {
    return opBox;
  }

  @Override
  public final List<ValueBox> getUseBoxes() {

    List<ValueBox> list = new ArrayList<>(opBox.getValue().getUseBoxes());
    list.add(opBox);

    return list;
  }

  protected boolean equivTo(AbstractOpStmt o) {
    return opBox.getValue().equivTo(o.getOp());
  }

  @Override
  public int equivHashCode() {
    return opBox.getValue().equivHashCode();
  }
}
