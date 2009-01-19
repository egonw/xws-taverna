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
package net.sf.taverna.t2.activities.xmpp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import net.sf.taverna.t2.activities.testutils.ActivityInvoker;
import net.sf.taverna.t2.workflowmodel.AbstractPort;

import org.junit.Test;

public class XMPPActivityTest {

	@Test
	public void testInvoke() throws Exception {
		XMPPConfigurationBean bean = new XMPPConfigurationBean();
//		bean.setValue("this_is_a_string");
		XMPPActivity activity = new XMPPActivity();
		activity.configure(bean);
		
		assertEquals("there should be 1 input",1,activity.getInputPorts().size());
		assertEquals("there should be 1 output",1,activity.getOutputPorts().size());
		assertEquals("the output port name should be value","iodata-out",((AbstractPort)activity.getOutputPorts().toArray()[0]).getName());
		
		Map<String, Class<?>> expectedOutputs = new HashMap<String, Class<?>>();
		expectedOutputs.put("value", String.class);

//		Map<String,Object> outputs = ActivityInvoker.invokeAsyncActivity(activity, new HashMap<String, Object>(), expectedOutputs);
		
//		System.out.println(outputs);
//		assertEquals("there should be 1 output",1,outputs.size());
//		assertTrue("there should be an output named iodata-out",outputs.containsKey("iodata-out"));
//		assertTrue("The output type should be String",outputs.get("iodata-out") instanceof String);
	}
}
