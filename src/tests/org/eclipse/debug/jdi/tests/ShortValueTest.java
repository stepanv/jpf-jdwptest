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

import com.sun.jdi.ShortValue;

/**
 * Tests for JDI com.sun.jdi.ShortValue.
 */
public class ShortValueTest extends AbstractJDITest {

	private ShortValue fValue;
	/**
	 * Creates a new test.
	 */
	public ShortValueTest() {
		super();
	}
	/**
	 * Init the fields that are used by this test only.
	 */
	@Override
	public void localSetUp() {
		// Get short value for 12345
		fValue = fVM.mirrorOf((short) 12345);
	}


	/**
	 * Test JDI equals() and hashCode().
	 */
	@Test
	public void testJDIEquality() {
		assertTrue("1", fValue.equals(fVM.mirrorOf((short) 12345)));
		assertTrue("2", !fValue.equals(fVM.mirrorOf((short) 54321)));
		assertTrue("3", !fValue.equals(new Object()));
		assertTrue("4", !fValue.equals(null));
		assertEquals(
			"5",
			fValue.hashCode(),
			fVM.mirrorOf((short) 12345).hashCode());
		assertTrue(
			"6",
			fValue.hashCode() != fVM.mirrorOf((short) 54321).hashCode());
	}
	/**
	 * Test JDI value().
	 */
	@Test
	public void testJDIValue() {
		assertTrue("1", (short) 12345 == fValue.value());
	}
}
