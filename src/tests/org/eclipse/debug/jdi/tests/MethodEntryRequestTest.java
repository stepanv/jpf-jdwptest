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
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.sun.jdi.ClassType;
import com.sun.jdi.Method;
import com.sun.jdi.ReferenceType;
import com.sun.jdi.ThreadReference;
import com.sun.jdi.event.Event;
import com.sun.jdi.event.MethodEntryEvent;
import com.sun.jdi.request.MethodEntryRequest;

/**
 * Tests a method entry request
 */
public class MethodEntryRequestTest extends AbstractJDITest {

	/**
	 * @see org.eclipse.debug.jdi.tests.AbstractJDITest#localSetUp()
	 */
	@Override
	public void localSetUp() {
	}

	/**
	 * @see org.eclipse.debug.jdi.tests.AbstractJDITest#localTearDown()
	 */
	@Override
	public void localTearDown() {
		fVM.resume();
		waitUntilReady();
	}




	/**
	 * Creates and returns a new <code>MethodEntryRequest</code>
	 * @return a new <code>MethodEntryRequest</code>
	 */
	protected MethodEntryRequest getMethodEntryRequest() {
		return fVM.eventRequestManager().createMethodEntryRequest();
	}

	/**
	 * Tests a request without any filtering 
	 */
	@Test
	public void testJDIWithoutFilter() {
		MethodEntryRequest request = getMethodEntryRequest();

		Event e = triggerAndWait(request, "BreakpointEvent", true);
		assertEquals(request, e.request());

		MethodEntryEvent event = (MethodEntryEvent) e;
		assertEquals(getThread(), event.thread());
		fVM.eventRequestManager().deleteEventRequest(request);
	}

	/**
	 * Test a request with class exclusion filtering
	 */
	@Test
	public void testJDIWithClassExclusionFilter() {
		MethodEntryRequest request = getMethodEntryRequest();
		request.addClassExclusionFilter("org.eclipse.debug.jdi.tests.program.*");

		Event e = triggerAndWait(request, "BreakpointEvent", true);
		assertEquals(request, e.request());

		MethodEntryEvent event = (MethodEntryEvent) e;
		Method m = event.method();
		ReferenceType r = m.location().declaringType();
		assertTrue("1", !r.name().startsWith("org.eclipse.debug.jdi.tests.program."));
		fVM.eventRequestManager().deleteEventRequest(request);
	}

	/**
	 * Tests a method entry request with a specified class filter
	 */
	@Test
	public void testJDIWithClassFilter1() {
		MethodEntryRequest request = getMethodEntryRequest();
		ClassType clazz = getClass("gov.nasa.jpf.ConsoleOutputStream");
		request.addClassFilter(clazz);

		Event e = triggerAndWait(request, "BreakpointEvent", true);
		assertEquals(request, e.request());

		MethodEntryEvent event = (MethodEntryEvent) e;
		Method m = event.method();
		ReferenceType r = m.location().declaringType();
		assertEquals(clazz, r);
		fVM.eventRequestManager().deleteEventRequest(request);
	}

	/**
	 * Retests a method entry request with a specified class filter
	 */
	@Test
	public void testJDIWithClassFilter2() {
		MethodEntryRequest request = getMethodEntryRequest();
		request.addClassFilter("gov.nasa.jpf.ConsoleOutputStream");

		Event e = triggerAndWait(request, "BreakpointEvent", true);
		assertEquals(request, e.request());

		MethodEntryEvent event = (MethodEntryEvent) e;
		Method m = event.method();
		ReferenceType r = m.location().declaringType();
		assertEquals("gov.nasa.jpf.ConsoleOutputStream", r.name());
		fVM.eventRequestManager().deleteEventRequest(request);
	}

	/**
	 * Tests a method entry request with a thread filter
	 */
	@Test
	public void testJDIWithThreadFilter() {
		MethodEntryRequest request = getMethodEntryRequest();
		ThreadReference thr = getMainThread();
		request.addThreadFilter(thr);

		Event e = triggerAndWait(request, "BreakpointEvent", true);
		assertEquals(request, e.request());

		MethodEntryEvent event = (MethodEntryEvent) e;
		assertEquals(thr, event.thread());
		fVM.eventRequestManager().deleteEventRequest(request);
	}
}
