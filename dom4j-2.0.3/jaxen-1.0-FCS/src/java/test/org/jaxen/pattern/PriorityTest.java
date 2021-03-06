/*
 * $Header: /cvsroot/jaxen/jaxen/src/java/test/org/jaxen/pattern/PriorityTest.java,v 1.3 2002/04/26 17:17:37 jstrachan Exp $
 * $Revision: 1.3 $
 * $Date: 2002/04/26 17:17:37 $
 *
 * ====================================================================
 *
 * Copyright (C) 2000-2002 bob mcwhirter & James Strachan.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions, and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions, and the disclaimer that follows 
 *    these conditions in the documentation and/or other materials 
 *    provided with the distribution.
 *
 * 3. The name "Jaxen" must not be used to endorse or promote products
 *    derived from this software without prior written permission.  For
 *    written permission, please contact license@jaxen.org.
 * 
 * 4. Products derived from this software may not be called "Jaxen", nor
 *    may "Jaxen" appear in their name, without prior written permission
 *    from the Jaxen Project Management (pm@jaxen.org).
 * 
 * In addition, we request (but do not require) that you include in the 
 * end-user documentation provided with the redistribution and/or in the 
 * software itself an acknowledgement equivalent to the following:
 *     "This product includes software developed by the
 *      Jaxen Project (http://www.jaxen.org/)."
 * Alternatively, the acknowledgment may be graphical using the logos 
 * available at http://www.jaxen.org/
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE Jaxen AUTHORS OR THE PROJECT
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 *
 * ====================================================================
 * This software consists of voluntary contributions made by many 
 * individuals on behalf of the Jaxen Project and was originally 
 * created by bob mcwhirter <bob@werken.com> and 
 * James Strachan <jstrachan@apache.org>.  For more information on the 
 * Jaxen Project, please see <http://www.jaxen.org/>.
 * 
 * $Id: PriorityTest.java,v 1.3 2002/04/26 17:17:37 jstrachan Exp $
 */



package org.jaxen.pattern;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

import org.jaxen.pattern.Pattern;
import org.jaxen.pattern.PatternParser;

import org.saxpath.XPathSyntaxException;

/** Tests the use of priority in the Pattern implementations.
  *
  * @author <a href="mailto:jstrachan@apache.org">James Strachan</a>
  * @version $Revision: 1.3 $
  */
public class PriorityTest extends TestCase
{
    public PriorityTest(String name)
    {
        super( name );
    }

    public static void main(String[] args) 
    {
        TestRunner.run( suite() );
    }
    
    public static Test suite() 
    {
        return new TestSuite( PriorityTest.class );
    }
    
    public void setUp()
    {
    }

    public void tearDown()
    {
    }

    public void testDocumentNode() throws Exception
    {
        testPriority( "/", -0.5, Pattern.DOCUMENT_NODE );
    }
    
    public void testNameNode() throws Exception
    {
        testPriority( "foo", 0, Pattern.ELEMENT_NODE );
    }
    
    public void testQNameNode() throws Exception
    {
        testPriority( "foo:bar", 0, Pattern.ELEMENT_NODE );
    }
    
    public void testFilter() throws Exception
    {
        testPriority( "foo[@id='123']", 0.5, Pattern.ELEMENT_NODE );
    }
    
    public void testURI() throws Exception
    {
        testPriority( "foo:*", -0.25, Pattern.ELEMENT_NODE );
    }

    public void testNodeType() throws Exception
    {
        testPriority( "text()", -0.5, Pattern.TEXT_NODE );
    }
    
    public void testAttribute() throws Exception
    {
        testPriority( "@*", -0.5, Pattern.ATTRIBUTE_NODE );
    }
    
    public void testAnyNode() throws Exception
    {
        testPriority( "*", -0.5, Pattern.ELEMENT_NODE );
    }
    
    protected void testPriority(String expr, double priority, short nodeType) throws Exception 
    {
        System.out.println( "parsing: " + expr );
        
        Pattern pattern = PatternParser.parse( expr );
        double d = pattern.getPriority();
        short nt = pattern.getMatchType();
        
        System.out.println( "expr: " + expr + " has priority: " + d + " nodeType: " + nt );
        System.out.println( "pattern: " + pattern );
        
        assertEquals( "expr: " + expr, new Double(priority), new Double(d) );
        assertEquals( "nodeType: " + expr, new Short(nodeType), new Short(nt) );
    }
}
