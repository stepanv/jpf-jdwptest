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

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.sun.jdi.VMDisconnectedException;

/**
 * Tests for JDI com.sun.jdi.event.VMDisconnectEvent.
 */
public class VMDisposeTest extends AbstractJDITest {
	/**
	 * Creates a new test.
	 */
	public VMDisposeTest() {
		super();
	}
	/**
	 * Init the fields that are used by this test only.
	 */
	@Override
	public void localSetUp() {
	}


	/**
	 * Test that we received the event.
	 */
	@Test
	public void testJDIVMDispose() {
		fVM.dispose();
		try {
			fVM.allThreads();
			assertTrue("1", false);
		} catch (VMDisconnectedException e) {
		}

		try {
			// Reconnect to running VM.
			connectToVM();
			fVM.allThreads();
		} catch (VMDisconnectedException e) {
			assertTrue("3", false);
		}
	}
}
