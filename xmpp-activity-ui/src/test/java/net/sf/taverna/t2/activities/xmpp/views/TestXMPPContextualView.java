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
package net.sf.taverna.t2.activities.xmpp.views;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import net.sf.taverna.t2.activities.xmpp.XMPPActivity;
import net.sf.taverna.t2.activities.xmpp.XMPPConfigurationBean;
import net.sf.taverna.t2.activities.xmpp.actions.XMPPActivityConfigurationAction;
import net.sf.taverna.t2.workbench.ui.views.contextualviews.ContextualView;
import net.sf.taverna.t2.workbench.ui.views.contextualviews.activity.ContextualViewFactory;
import net.sf.taverna.t2.workbench.ui.views.contextualviews.activity.ContextualViewFactoryRegistry;
import net.sf.taverna.t2.workflowmodel.processor.activity.Activity;
import net.sf.taverna.t2.workflowmodel.processor.activity.ActivityConfigurationException;

import org.junit.Before;
import org.junit.Test;

public class TestXMPPContextualView {
	Activity<?> activity;
	
	@Before
	public void setup() throws ActivityConfigurationException {
		activity=new XMPPActivity();
		XMPPConfigurationBean b=new XMPPConfigurationBean();
		b.setClientJID("elvis");
		((XMPPActivity)activity).configure(b);
	}
	@SuppressWarnings("unchecked")
	@Test
	public void testDisovery() throws Exception {ContextualViewFactory factory = ContextualViewFactoryRegistry.getInstance().getViewFactoryForObject(activity);
		assertTrue("Factory should be XMPPActivityViewFactory",factory instanceof XMPPActivityViewFactory);
		ContextualView view = factory.getView(activity);
		assertTrue("The view should be XMPPActivityContextualView",view instanceof XMPPActivityContextualView);
	}
	
	@Test
	public void testGetConfigureAction() throws Exception {
		ContextualView view = new XMPPActivityContextualView(activity);
		assertNotNull("The action should not be null",view.getConfigureAction(null));
		assertTrue("Should be a XMPPActivityConfigurationAction",view.getConfigureAction(null) instanceof XMPPActivityConfigurationAction);
	}
	
}
