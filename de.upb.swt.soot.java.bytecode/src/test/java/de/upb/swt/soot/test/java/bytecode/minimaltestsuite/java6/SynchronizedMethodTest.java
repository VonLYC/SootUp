package de.upb.swt.soot.test.java.bytecode.minimaltestsuite.java6;

import categories.Java8Test;
import de.upb.swt.soot.core.signatures.MethodSignature;
import de.upb.swt.soot.test.java.bytecode.minimaltestsuite.MinimalBytecodeTestSuiteBase;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.experimental.categories.Category;

/** @author Kaustubh Kelkar */
@Category(Java8Test.class)
public class SynchronizedMethodTest extends MinimalBytecodeTestSuiteBase {

  public MethodSignature getMethodSignature() {
    return identifierFactory.getMethodSignature(
        "run", getDeclaredClassSignature(), "void", Collections.emptyList());
  }

  @Override
  public void defaultTest() {
    super.defaultTest();
    /** TODO assertTrue(method.isSynchronized()); */
  }

  @Override
  public List<String> expectedBodyStmts() {
    return Stream.of(
            "l0 := @this: SynchronizedMethod",
            "$stack2 = l0.<SynchronizedMethod: SenderMethod sender>",
            "$stack1 = l0.<SynchronizedMethod: java.lang.String msg>",
            "virtualinvoke $stack2.<SenderMethod: void send(java.lang.String)>($stack1)",
            "return")
        .collect(Collectors.toCollection(ArrayList::new));
  }
}
