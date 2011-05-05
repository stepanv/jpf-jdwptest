/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.debug.jdi.tests;

import com.sun.jdi.CharValue;

/**
 * Tests for JDI com.sun.jdi.CharValue.
 */
public class CharValueTest extends AbstractJDITest {

	private CharValue fValue;
	/**
	 * Creates a new test.
	 */
	public CharValueTest() {
		super();
	}
	/**
	 * Init the fields that are used by this test only.
	 */
	public void localSetUp() {
		// Get char value for 'a'
		fValue = fVM.mirrorOf('a');
	}
	/**
	 * Run all tests and output to standard output.
	 * @param args
	 */
	public static void main(java.lang.String[] args) {
		new CharValueTest().runSuite(args);
	}
	/**
	 * Gets the name of the test case.
	 * @see junit.framework.TestCase#getName()
	 */
	public String getName() {
		return "com.sun.jdi.CharValue";
	}
	/**
	 * Test JDI equals() and hashCode().
	 */
	public void testJDIEquality() {
		assertTrue("1", fValue.equals(fVM.mirrorOf('a')));
		assertTrue("2", !fValue.equals(fVM.mirrorOf('b')));
		assertTrue("3", !fValue.equals(new Object()));
		assertTrue("4", !fValue.equals(null));
		assertEquals("5", fValue.hashCode(), fVM.mirrorOf('a').hashCode());
		assertTrue("6", fValue.hashCode() != fVM.mirrorOf('b').hashCode());
	}
	/**
	 * Test JDI value().
	 */
	public void testJDIValue() {
		assertTrue("1", 'a' == fValue.value());
	}
}
