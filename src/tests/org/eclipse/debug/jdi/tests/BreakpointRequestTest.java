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

import com.sun.jdi.request.BreakpointRequest;

/**
 * Tests for JDI com.sun.jdi.request.BreakpointRequest.
 */
public class BreakpointRequestTest extends AbstractJDITest {

	private BreakpointRequest fRequest;
	/**
	 * Creates a new test .
	 */
	public BreakpointRequestTest() {
		super();
	}
	/**
	 * Init the fields that are used by this test only.
	 */
	@Override
	public void localSetUp() {
		// Get the breakpoint request
		fRequest = getBreakpointRequest();
	}
	/**
	 * Make sure the test leaves the VM in the same state it found it.
	 */
	@Override
	public void localTearDown() {
		// Delete the breakpoint request we created in this test
		fVM.eventRequestManager().deleteEventRequest(fRequest);
	}


	/**
	 * Test JDI location().
	 */
	@Test
	public void testJDILocation() {
		assertEquals("1", getLocation(), fRequest.location());
	}
}
