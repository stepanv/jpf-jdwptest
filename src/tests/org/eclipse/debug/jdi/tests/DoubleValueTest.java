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

import com.sun.jdi.DoubleValue;

/**
 * Tests for JDI com.sun.jdi.DoubleValue.
 */
public class DoubleValueTest extends AbstractJDITest {

	private DoubleValue fValue;
	/**
	 * Creates a new test.
	 */
	public DoubleValueTest() {
		super();
	}
	/**
	 * Init the fields that are used by this test only.
	 */
	@Override
	public void localSetUp() {
		// Get double value for 12345.6789
		fValue = fVM.mirrorOf(12345.6789);
	}


	/**
	 * Test JDI equals() and hashCode().
	 */
	@Test
	public void testJDIEquality() {
		assertTrue("1", fValue.equals(fVM.mirrorOf(12345.6789)));
		assertTrue("2", !fValue.equals(fVM.mirrorOf(98765.4321)));
		assertTrue("3", !fValue.equals(new Object()));
		assertTrue("4", !fValue.equals(null));
		assertEquals(
			"5",
			fValue.hashCode(),
			fVM.mirrorOf(12345.6789).hashCode());
		assertTrue("6", fValue.hashCode() != fVM.mirrorOf(98765.4321).hashCode());
	}
	/**
	 * Test JDI value().
	 */
	@Test
	public void testJDIValue() {
		assertTrue("1", 12345.6789 == fValue.value());
	}
}
