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

import com.sun.jdi.AbsentInformationException;
import com.sun.jdi.Location;
import com.sun.jdi.Method;
import com.sun.jdi.ReferenceType;

/**
 * Tests for JDI com.sun.jdi.Location.
 */
public class LocationTest extends AbstractJDITest {

	private Location fLocation;
	/**
	 * Creates a new test.
	 */
	public LocationTest() {
		super();
	}
	/**
	 * Init the fields that are used by this test only.
	 */
	@Override
	public void localSetUp() {
		// Ensure we're in a good state
		fVM.resume();
		waitUntilReady();

		// Get the location of the stack frame running the method MainClass.run()
		fLocation = getLocation();

	}


	/**
	 * Test JDI codeIndex().
	 */
	@Test
	public void testJDICodeIndex() {
		fLocation.codeIndex();
	}
	/**
	 * Test JDI declaringType().
	 */
	@Test
	public void testJDIDeclaringType() {
		ReferenceType expected = getMainClass();
		ReferenceType declaringType = fLocation.declaringType();
		assertEquals("1", expected.name(), declaringType.name());
		// Use name to work around a pb in Sun's VM
	}
	/**
	 * Test JDI equals() and hashCode().
	 */
	@Test
	public void testJDIEquality() {
		assertTrue("1", fLocation.equals(fLocation));
		Location other = getFrame(0).location();
		assertTrue("2", !fLocation.equals(other));
		assertTrue("3", !fLocation.equals(new Object()));
		assertTrue("4", !fLocation.equals(null));
		assertTrue("5", fLocation.hashCode() != other.hashCode());
	}
	/**
	 * Test JDI lineNumber().
	 */
	@Test
	public void testJDILineNumber() {
		assertEquals("1", 185, fLocation.lineNumber());
	}
	/**
	 * Test JDI method().
	 */
	@Test
	public void testJDIMethod() {
		Method method = fLocation.method();
		assertEquals("1", "print", method.name());
	}
	/**
	 * Test JDI sourceName().
	 */
	@Test
	public void testJDISourceName() {
		String sourceName = null;
		try {
			sourceName = fLocation.sourceName();
		} catch (AbsentInformationException e) {
			assertTrue("1", false);
		}
		assertEquals("2", "MainClass.java", sourceName);
	}
}
