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

import org.junit.Test;

import com.sun.jdi.event.ClassPrepareEvent;
import com.sun.jdi.request.ClassPrepareRequest;

/**
 * Tests for JDI com.sun.jdi.event.ClassPrepareEvent.
 */
public class ClassPrepareEventTest extends AbstractJDITest {
	private ClassPrepareRequest fRequest;
	private ClassPrepareEvent fEvent;
	/**
	 * Creates a new test.
	 */
	public ClassPrepareEventTest() {
		super();
	}
	/**
	 * Init the fields that are used by this test only.
	 */
	@Override
	public void localSetUp() {
		// Trigger a class prepare event
		fRequest = fVM.eventRequestManager().createClassPrepareRequest();
		fEvent =
			(ClassPrepareEvent) triggerAndWait(fRequest,
				"ClassPrepareEvent",
				true);
	}
	/**
	 * Make sure the test leaves the VM in the same state it found it.
	 */
	@Override
	public void localTearDown() {
		// The test has resumed the test thread, so suspend it
		waitUntilReady();

		// Delete the class prepare request
		fVM.eventRequestManager().deleteEventRequest(fRequest);
	}


	/**
	 * Test JDI referenceType().
	 */
	@Test
	public void testJDIReferenceType() {
		assertEquals(
			"1",
			"org.eclipse.debug.jdi.tests.program.TestClass",
			fEvent.referenceType().name());
	}
	/**
	 * Test JDI thread().
	 */
	@Test
	public void testJDIThread() {
		assertEquals("1", "Test Thread", fEvent.thread().name());
	}
}
