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

import com.sun.jdi.LongValue;

/**
 * Tests for JDI com.sun.jdi.LongValue.
 */
public class LongValueTest extends AbstractJDITest {

	private LongValue fValue;
	/**
	 * Creates a new test.
	 */
	public LongValueTest() {
		super();
	}
	/**
	 * Init the fields that are used by this test only.
	 */
	@Override
	public void localSetUp() {
		// Get long value for 123456789l
		fValue = fVM.mirrorOf(123456789l);
	}


	/**
	 * Test JDI equals() and hashCode().
	 */
	@Test
	public void testJDIEquality() {
		assertTrue("1", fValue.equals(fVM.mirrorOf(123456789l)));
		assertTrue("2", !fValue.equals(fVM.mirrorOf(987654321l)));
		assertTrue("3", !fValue.equals(new Object()));
		assertTrue("4", !fValue.equals(null));
		assertEquals(
			"5",
			fValue.hashCode(),
			fVM.mirrorOf(123456789l).hashCode());
		assertTrue("6", fValue.hashCode() != fVM.mirrorOf(987654321l).hashCode());
	}
	/**
	 * Test JDI value().
	 */
	@Test
	public void testJDIValue() {
		assertTrue("1", 123456789l == fValue.value());
	}
}
