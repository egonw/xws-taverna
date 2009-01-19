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

public class XMPPConfigurationBean {

	private String clientJID = "edit me!"; // e.g. egonw@ws1.bmc.uu.se/home
	private String password = "edit me!";  // e.g. SECRET
	private String host = "edit me!";      // e.g. ws1.bmc.uu.se
	private String port = "edit me!";      // e.g. 5222
	private String serviceJID = "edit me!";// e.g. cdk.ws1.bmc.uu.se
	private String function = "edit me!";  // e.g. calculateMass

	public String getClientJID() {
		return clientJID;
	}
	public void setClientJID(String value) {
		clientJID = value;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String value) {
		password = value;
	}
	
	public String getHost() {
		return host;
	}
	public void setHost(String value) {
		host = value;
	}
	
	public String getPort() {
		return port;
	}
	public void setPort(String value) {
		port = value;
	}
	
	public String getServiceJID() {
		return serviceJID;
	}
	public void setServiceJID(String value) {
		serviceJID = value;
	}
	
	public String getFunction() {
		return function;
	}
	public void setFunction(String value) {
		function = value;
	}
	
}
