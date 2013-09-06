/*******************************************************************************
 * Copyright (c) 2000, 2011 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.debug.jdi.tests;

import static org.junit.Assert.assertEquals;

import com.sun.jdi.StringReference;
import com.sun.jdi.event.ModificationWatchpointEvent;
import com.sun.jdi.request.WatchpointRequest;

/**
 * Tests for JDI com.sun.jdi.ModificationWatchpointEvent.
 */
public class ModificationWatchpointEventTest extends AbstractJDITest {

	private ModificationWatchpointEvent fWatchpointEvent;
	private WatchpointRequest fWatchpointRequest;
	/**
	 * Creates a new test.
	 */
	public ModificationWatchpointEventTest() {
		super();
	}
	/**
	 * Init the fields that are used by this test only.
	 */
	@Override
	public void localSetUp() {
		// Trigger a static modification watchpoint event
		fWatchpointRequest = getStaticModificationWatchpointRequest();
		fWatchpointEvent =
			(ModificationWatchpointEvent) triggerAndWait(fWatchpointRequest,
				"StaticModificationWatchpointEvent",
				false);
		// Interrupt the VM so that we can test valueToBe()
	}
	/**
	 * Make sure the test leaves the VM in the same state it found it.
	 */
	@Override
	public void localTearDown() {
		// Ensure that the modification of the "fString" field has completed
		fVM.resume();
		waitUntilReady();

		// Remove the modification watchpoint request
		fVM.eventRequestManager().deleteEventRequest(fWatchpointRequest);

		// Set the value of the "fString" field back to its original value
		resetStaticField();
	}


	/**
	 * Test JDI valueToBe().
	 */
	public void testJDIValueToBe() {
		assertEquals(
			"1",
			"Hello Universe",
			((StringReference) fWatchpointEvent.valueToBe()).value());
	}
}
