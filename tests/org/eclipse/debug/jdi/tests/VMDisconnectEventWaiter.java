/*******************************************************************************
 * Copyright (c) 2000, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.debug.jdi.tests;

import com.sun.jdi.event.VMDisconnectEvent;
/**
 * Listen for VMDisconnectEvent.
 */
public class VMDisconnectEventWaiter extends EventWaiter {
	/**
	 * Creates a VMDisconnectEventWaiter.
	 */
	public VMDisconnectEventWaiter(
		com.sun.jdi.request.EventRequest request,
		boolean shouldGo) {
		super(request, shouldGo);
	}
	public boolean vmDisconnect(VMDisconnectEvent event) {
		notifyEvent(event);
		return fShouldGo;
	}
}
