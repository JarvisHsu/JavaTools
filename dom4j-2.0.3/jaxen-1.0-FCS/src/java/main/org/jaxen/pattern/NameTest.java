/*
 * $Header: /cvsroot/jaxen/jaxen/src/java/main/org/jaxen/pattern/NameTest.java,v 1.4 2002/04/26 17:17:36 jstrachan Exp $
 * $Revision: 1.4 $
 * $Date: 2002/04/26 17:17:36 $
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
 * $Id: NameTest.java,v 1.4 2002/04/26 17:17:36 jstrachan Exp $
 */

package org.jaxen.pattern;

import org.jaxen.Context;
import org.jaxen.Navigator;

/** <p><code>NameTest</code> tests for a node name.</p>
  *
  * @author <a href="mailto:jstrachan@apache.org">James Strachan</a>
  * @version $Revision: 1.4 $
  */
public class NameTest extends NodeTest {
    
    /** The name to match against */
    private String name;
    
    /** The type of node to match - either attribute or element */
    private short nodeType;
    
    public NameTest(String name, short nodeType)   
    {
        this.name = name;
        this.nodeType = nodeType;
    }
        
    /** @return true if the pattern matches the given node
      */
    public boolean matches( Object node, Context context ) 
    {
        Navigator navigator = context.getNavigator();
        if ( nodeType == Pattern.ELEMENT_NODE ) 
        {
            return navigator.isElement( node ) 
                && name.equals( navigator.getElementName( node ) );
        }
        else if ( nodeType == Pattern.ATTRIBUTE_NODE ) 
        {
            return navigator.isAttribute( node )
                && name.equals( navigator.getAttributeName( node ) );
        }
        else
        {
            if ( navigator.isElement( node ) )
            {
                return name.equals( navigator.getElementName( node ) );
            }
            else
            if ( navigator.isAttribute( node ) )
            {
                return name.equals( navigator.getAttributeName( node ) );
            }
        }
        return false;
    }
    
    public double getPriority() 
    {
        return 0.0;
    }


    public short getMatchType() 
    {
        return nodeType;
    }
    
    public String getText() 
    {
        if ( nodeType == Pattern.ATTRIBUTE_NODE ) 
        {
            return "@" + name;
        }
        else 
        {
            return name;
        }
    }
    
    public String toString()
    {
        return super.toString() + "[ name: " + name + " type: " + nodeType + " ]";
    }
}
