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
package net.sf.taverna.t2.activities.xmpp.actions;

import java.awt.Frame;
import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import net.sf.taverna.t2.activities.xmpp.XMPPActivity;
import net.sf.taverna.t2.activities.xmpp.XMPPConfigurationBean;
import net.sf.taverna.t2.workbench.ui.actions.activity.ActivityConfigurationAction;

public class XMPPActivityConfigurationAction extends
		ActivityConfigurationAction<XMPPActivity, XMPPConfigurationBean> {

	private static final long serialVersionUID = 2518716677779186972L;
	private final Frame owner;

	public XMPPActivityConfigurationAction(XMPPActivity activity,Frame owner) {
		super(activity);
		this.owner = owner;
	}

	public void actionPerformed(ActionEvent e) {
		XMPPConfigurationBean bean = new XMPPConfigurationBean();
		String value = getActivity().getConfiguration().getClientJID();
		String newValue = JOptionPane.showInputDialog(owner,"Enter Client JID",value);
		if (newValue!=null) {
			bean.setClientJID(newValue);
		}
		value = getActivity().getConfiguration().getPassword();
		newValue = JOptionPane.showInputDialog(owner,"Enter Client JID password",value);
		if (newValue!=null) {
			bean.setPassword(newValue);
		}
		value = getActivity().getConfiguration().getHost();
		newValue = JOptionPane.showInputDialog(owner,"Enter Jabber host",value);
		if (newValue!=null) {
			bean.setHost(newValue);
		}
		value = getActivity().getConfiguration().getPort();
		newValue = JOptionPane.showInputDialog(owner,"Enter Jabber host port",value);
		if (newValue!=null) {
			bean.setPort(newValue);
		}
		value = getActivity().getConfiguration().getServiceJID();
		newValue = JOptionPane.showInputDialog(owner,"Enter Service JID",value);
		if (newValue!=null) {
			bean.setServiceJID(newValue);
		}
		value = getActivity().getConfiguration().getFunction();
		newValue = JOptionPane.showInputDialog(owner,"Enter Service Function",value);
		if (newValue!=null) {
			bean.setFunction(newValue);
		}
		configureActivity(bean);
	}

}
