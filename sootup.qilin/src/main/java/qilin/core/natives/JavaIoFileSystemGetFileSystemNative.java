/* Qilin - a Java Pointer Analysis Framework
 * Copyright (C) 2021-2030 Qilin developers
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3.0 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 *
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <https://www.gnu.org/licenses/lgpl-3.0.en.html>.
 */

package qilin.core.natives;

import qilin.util.PTAUtils;
import sootup.core.jimple.basic.Local;
import sootup.core.model.SootMethod;
import sootup.core.views.View;

public class JavaIoFileSystemGetFileSystemNative extends NativeMethod {
  public JavaIoFileSystemGetFileSystemNative(View view, SootMethod method) {
    super(view, method);
  }

  /** ********************** java.io.FileSystem ********************** */
  /**
   * Returns a variable pointing to the file system constant
   *
   * <p>public static native java.io.FileSystem getFileSystem(); only exists in old version of
   * JDK(e.g., JDK6).
   */
  protected void simulateImpl() {
    Local newLocal0 = getNew(PTAUtils.getClassType("java.io.UnixFileSystem"));
    addInvoke(newLocal0, "<java.io.UnixFileSystem: void <init>()>");
    addReturn(newLocal0);
  }
}
