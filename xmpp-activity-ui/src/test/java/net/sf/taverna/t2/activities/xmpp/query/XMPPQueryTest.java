/*******************************************************************************
 * Copyright (C) 2007 The University of Manchester   
 *               2009 Egon Willighagen <egon.willighagen@gmail.com>
 * 
 *  Modifications to the initial code base are copyright of their
 *  respective authors, or their employers as appropriate.
 * 
 *  This program is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU Lesser General Public License
 *  as published by the Free Software Foundation; either version 2.1 of
 *  the License, or (at your option) any later version.
 *    
 *  This program is distributed in the hope that it will be useful, but
 *  WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *  Lesser General Public License for more details.
 *    
 *  You should have received a copy of the GNU Lesser General Public
 *  License along with this program; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307
 ******************************************************************************/
package net.sf.taverna.t2.activities.xmpp.query;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import net.sf.taverna.t2.partition.Query;

import org.junit.Test;

public class XMPPQueryTest {

	@Test
	public void doQuery() {
		Query<?> q = new XMPPQuery(null);
		q.doQuery();
		assertEquals("There should be 1 item found",1,q.size());
		assertTrue("The item should be a XMPPActivityItem",q.toArray()[0] instanceof XMPPActivityItem);
	}
}
