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

import com.sun.jdi.ClassNotLoadedException;
import com.sun.jdi.Field;

/**
 * Tests for JDI com.sun.jdi.Field.
 */
public class FieldTest extends AbstractJDITest {

	private Field fField;
	/**
	 * Creates a new test.
	 */
	public FieldTest() {
		super();
	}
	/**
	 * Init the fields that are used by this test only.
	 */
	@Override
	public void localSetUp() {
		// Get static field "fObject"
		fField = getField();
	}


	/**
	 * Test JDI equals() and hashCode().
	 */
	@Test
	public void testJDIEquality() {
		assertTrue("1", fField.equals(fField));
		Field other = getField("fString");
		assertTrue("2", !fField.equals(other));
		assertTrue("3", !fField.equals(new Object()));
		assertTrue("4", !fField.equals(null));
	}
	/**
	 * Test JDI isTransient().
	 */
	@Test
	public void testJDIIsTransient() {
		assertTrue("1", !fField.isTransient());
	}
	/**
	 * Test JDI isVolatile().
	 */
	@Test
	public void testJDIIsVolatile() {
		assertTrue("1", !fField.isVolatile());
	}
	/**
	 * Test JDI type().
	 */
	@Test
	public void testJDIType() {
		try {
			assertEquals("1", getMainClass(), fField.type());
		} catch (ClassNotLoadedException e) {
			assertTrue("2", false);
		}
	}
	/**
	 * Test JDI typeName().
	 */
	@Test
	public void testJDITypeName() {
		assertEquals(
			"1",
			"org.eclipse.debug.jdi.tests.program.MainClass",
			fField.typeName());
	}
}
