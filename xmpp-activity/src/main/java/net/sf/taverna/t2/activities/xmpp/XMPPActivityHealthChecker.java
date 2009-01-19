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

import net.sf.taverna.t2.workflowmodel.health.HealthChecker;
import net.sf.taverna.t2.workflowmodel.health.HealthReport;
import net.sf.taverna.t2.workflowmodel.health.HealthReport.Status;

public class XMPPActivityHealthChecker implements HealthChecker<XMPPActivity> {

	public boolean canHandle(Object subject) {
		return subject!=null && subject instanceof XMPPActivity;
	}

	public HealthReport checkHealth(XMPPActivity activity) {
		String value = activity.getConfiguration().getClientJID();
//		if (value==null) {
//			return new HealthReport("XMPP Activity","The clientJID is null",Status.SEVERE);
//		}
//		if ("edit me!".equals(value)) {
//			return new HealthReport("XMPP Activity","The clientJID is still the default",Status.WARNING);
//		}
//		value = activity.getConfiguration().getPassword();
//		if (value==null) {
//			return new HealthReport("XMPP Activity","The password is null",Status.SEVERE);
//		}
//		if ("edit me!".equals(value)) {
//			return new HealthReport("XMPP Activity","The password is still the default",Status.WARNING);
//		}
//		value = activity.getConfiguration().getHost();
//		if (value==null) {
//			return new HealthReport("XMPP Activity","The host is null",Status.SEVERE);
//		}
//		if ("edit me!".equals(value)) {
//			return new HealthReport("XMPP Activity","The host is still the default",Status.WARNING);
//		}
//		value = activity.getConfiguration().getPort();
//		if (value==null) {
//			return new HealthReport("XMPP Activity","The port is null",Status.SEVERE);
//		}
//		if ("edit me!".equals(value)) {
//			return new HealthReport("XMPP Activity","The port is still the default",Status.WARNING);
//		}
//		value = activity.getConfiguration().getServiceJID();
//		if (value==null) {
//			return new HealthReport("XMPP Activity","The serviceJID is null",Status.SEVERE);
//		}
//		if ("edit me!".equals(value)) {
//			return new HealthReport("XMPP Activity","The serviceJID is still the default",Status.WARNING);
//		}
		return new HealthReport("XMPP Activity","OK",Status.OK);
	}

}
