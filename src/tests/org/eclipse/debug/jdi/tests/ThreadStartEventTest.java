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

import com.sun.jdi.event.ThreadStartEvent;

/**
 * Tests for JDI com.sun.jdi.event.ThreadStartEvent.
 */
public class ThreadStartEventTest extends AbstractJDITest {

	private ThreadStartEvent fEvent;
	/**
	 * Creates a new test.
	 */
	public ThreadStartEventTest() {
		super();
	}
	/**
	 * Init the fields that are used by this test only.
	 */
	@Override
	public void localSetUp() {
		// Make sure the entire VM is not suspended before we start a new thread
		// (otherwise this new thread will start suspended and we will never get the
		// ThreadStart event)
		fVM.resume();

		// Trigger a thread start event
		fEvent =
			(ThreadStartEvent) triggerAndWait(fVM
				.eventRequestManager()
				.createThreadStartRequest(),
				"ThreadStartEvent",
				true);
	}
	/**
	 * Make sure the test leaves the VM in the same state it found it.
	 */
	@Override
	public void localTearDown() {
		// The test has resumed the test thread, so suspend it
		waitUntilReady();
	}


	/**
	 * Test JDI thread().
	 */
	@Test
	public void testJDIThread() {
		assertEquals("1", "Test Thread Start Event", fEvent.thread().name());
	}
}
