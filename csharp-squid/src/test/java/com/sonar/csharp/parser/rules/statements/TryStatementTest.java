/*
 * Copyright (C) 2010 SonarSource SA
 * All rights reserved
 * mailto:contact AT sonarsource DOT com
 */
package com.sonar.csharp.parser.rules.statements;

import static com.sonar.sslr.test.parser.ParserMatchers.parse;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.sonar.csharp.api.CSharpGrammar;
import com.sonar.csharp.parser.CSharpParser;

public class TryStatementTest {

  CSharpParser p = new CSharpParser();
  CSharpGrammar g = p.getGrammar();

  @Before
  public void init() {
    p.setRootRule(g.tryStatement);
  }

  @Test
  public void testOk() {
    g.block.mock();
    g.catchClauses.mock();
    g.finallyClause.mock();
    assertThat(p, parse("try block catchClauses"));
    assertThat(p, parse("try block finallyClause"));
    assertThat(p, parse("try block catchClauses finallyClause"));
  }

  @Test
  public void testRealLife() throws Exception {
    assertThat(p,
        parse("try { RegisterAppDomainEvents(); } catch(System.Security.SecurityException)  { LogLog.Debug(\"LoggerManager\"); }"));
  }

}
