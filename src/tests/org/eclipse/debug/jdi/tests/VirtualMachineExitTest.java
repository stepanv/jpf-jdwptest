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
 * Tests for JDI com.sun.jdi.VirtualMachine.exit().
 */
public class VirtualMachineExitTest extends AbstractJDITest {
	/**
	 * Creates a new test .
	 */
	public VirtualMachineExitTest() {
		super();
	}
	/**
	 * Init the fields that are used by this test only.
	 */
	@Override
	public void localSetUp() {
	}
	/**
	 * Make sure the test leaves the VM in the same state it found it.
	 */
	@Override
	public void localTearDown() {
		// Finish the shut down
		shutDownTarget();

		// Start up again
		launchTargetAndStartProgram();
	}


	/**
	 * Test JDI exit().
	 */
	@Test
	public void testJDIExit() {
		try {
			fVM.exit(0);
		} catch (VMDisconnectedException e) {
			assertTrue("1", false);
		}

		try {
			Thread.sleep(200);
			assertTrue("2", !vmIsRunning());
			fVM.allThreads();
			assertTrue("3", false);
		} catch (VMDisconnectedException e) {
		} catch (InterruptedException e) {
		}
	}
}
