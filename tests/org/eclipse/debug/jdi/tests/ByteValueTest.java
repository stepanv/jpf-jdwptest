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

import com.sun.jdi.ByteValue;

/**
 * Tests for JDI com.sun.jdi.ByteValue.
 */
public class ByteValueTest extends AbstractJDITest {

	private ByteValue fValue;
	/**
	 * Creates a new test.
	 */
	public ByteValueTest() {
		super();
	}
	/**
	 * Init the fields that are used by this test only.
	 */
	@Override
	public void localSetUp() {
		// Get byte value for 1
		fValue = fVM.mirrorOf((byte) 1);
	}


	/**
	 * Test JDI equals() and hashCode().
	 */
	public void testJDIEquality() {
		assertTrue("1", fValue.equals(fVM.mirrorOf((byte) 1)));
		assertTrue("2", !fValue.equals(fVM.mirrorOf((byte) 2)));
		assertTrue("3", !fValue.equals(new Object()));
		assertTrue("4", !fValue.equals(null));
		assertEquals("5", fValue.hashCode(), fVM.mirrorOf((byte) 1).hashCode());
		assertTrue("6", fValue.hashCode() != fVM.mirrorOf((byte) 2).hashCode());
	}
	/**
	 * Test JDI value().
	 */
	public void testJDIValue() {
		assertTrue("1", 1 == fValue.value());
	}
}
