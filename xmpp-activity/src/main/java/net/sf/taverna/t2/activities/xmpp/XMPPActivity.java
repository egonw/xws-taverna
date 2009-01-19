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

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import net.bioclipse.xws.JavaDOMTools;
import net.bioclipse.xws.client.Client;
import net.bioclipse.xws.client.IExecutionPipe;
import net.bioclipse.xws.client.IXmppItem;
import net.bioclipse.xws.client.adhoc.IFunction;
import net.bioclipse.xws.client.adhoc.IProcess;
import net.bioclipse.xws.client.adhoc.IService;
import net.bioclipse.xws.client.listeners.ISimpleProcessListener;
import net.bioclipse.xws.exceptions.XmppException;
import net.bioclipse.xws.exceptions.XwsException;

import net.sf.taverna.t2.annotation.annotationbeans.MimeType;
import net.sf.taverna.t2.reference.ReferenceService;
import net.sf.taverna.t2.reference.ReferenceServiceException;
import net.sf.taverna.t2.reference.T2Reference;
import net.sf.taverna.t2.workflowmodel.EditException;
import net.sf.taverna.t2.workflowmodel.EditsRegistry;
import net.sf.taverna.t2.workflowmodel.OutputPort;
import net.sf.taverna.t2.workflowmodel.processor.activity.AbstractAsynchronousActivity;
import net.sf.taverna.t2.workflowmodel.processor.activity.ActivityConfigurationException;
import net.sf.taverna.t2.workflowmodel.processor.activity.ActivityInputPort;
import net.sf.taverna.t2.workflowmodel.processor.activity.AsynchronousActivityCallback;

import org.w3c.dom.Element;

/**
 * <p>
 * An Activity that holds a constant string value. It is automatically configured to have no input ports
 * and only one output port named <em>value</em>.<br>
 *
 * @author Stuart Owen
 *
 */
public class XMPPActivity extends AbstractAsynchronousActivity<XMPPConfigurationBean>{

	private static final Logger logger = Logger.getLogger(XMPPActivity.class);
			
	private XMPPConfigurationBean config=null;
	
	@Override
	public void configure(XMPPConfigurationBean conf)
			throws ActivityConfigurationException {
		this.config=conf;
		if (inputPorts.size() == 0) {
			addInput("iodata-in", 0, true, null, String.class);
		}
		if (outputPorts.size() == 0) {
			addOutput("iodata-out", 0, "text/xml");
		}
	}

	public String getStringValue() {
		return "XMPP Service: " + config.getServiceJID();
	}
	
	@Override
	public XMPPConfigurationBean getConfiguration() {
		return config;
	}

	@Override
	public void executeAsynch(final Map<String, T2Reference> data,
			final AsynchronousActivityCallback callback) {
		callback.requestRun(new Runnable() {

			public void run() {
				ReferenceService referenceService = callback.getContext().getReferenceService();
				try {
					ActivityInputPort inputPort = getInputPort("iodata-in");
					Object input = referenceService.renderIdentifier(data
							.get("iodata-in"), inputPort
							.getTranslatedElementClass(), callback
							.getContext()); // should be a String

					// make XMPP connection (FIXME: should reuse the connection, I guess)
					IExecutionPipe epipe = new IExecutionPipe() {
			            public void exec(Runnable r) {
			                r.run();
			            }
			        };

			        Client client = new Client(
			        	config.getClientJID(),
			        	config.getPassword(),
			        	config.getHost(),
			        	Integer.valueOf(config.getPort()),
			        	epipe
			        );
			        client.connect();
			        IService service = getService(client, config.getServiceJID());
			        IFunction dummyFunction = service.getFunction(config.getFunction());
		            Element result = dummyFunction.invokeSync("" + input, 45000);
					
					Map<String,T2Reference> outputData = new HashMap<String, T2Reference>();
					T2Reference id = referenceService.register(
					    JavaDOMTools.w3cElementToString(result),
					    0, true, callback.getContext()
					);
					outputData.put("iodata-out", id);
					callback.receiveResult(outputData, new int[0]);
				} catch (XmppException e) {
					callback.fail(e.getMessage(),e);
				} catch (XwsException e) {
					callback.fail(e.getMessage(),e);
				} catch (InterruptedException e) {
					callback.fail(e.getMessage(),e);
				} catch (ReferenceServiceException e) {
					callback.fail(e.getMessage(),e);
				}
			}
			
		});
		
	}

    private IService getService(Client client, String serviceJID)
    throws XmppException, XwsException, InterruptedException {
        IXmppItem item = client.getXmppItem(serviceJID, "");
        item = item.discoverSync(30000);

        if (item instanceof IService) {
            return (IService)item;
        } else {
            return null;
        }
    }
	
	public ActivityInputPort getInputPort(String name) {
		for (ActivityInputPort port : getInputPorts()) {
			if (port.getName().equals(name)) {
				return port;
			}
		}
		return null;
	}

	protected void addOutput(String portName, int portDepth, String type) {
		OutputPort port = EditsRegistry.getEdits().createActivityOutputPort(
				portName, portDepth, portDepth);
		MimeType mimeType = new MimeType();
		mimeType.setText(type);
		try {
			EditsRegistry.getEdits().getAddAnnotationChainEdit(port, mimeType).doEdit();
		} catch (EditException e) {
			logger.debug("Error adding MimeType annotation to port", e);
		}
		outputPorts.add(port);
	}

}
