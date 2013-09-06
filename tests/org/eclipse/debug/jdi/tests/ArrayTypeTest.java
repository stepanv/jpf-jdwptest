/*******************************************************************************
 * Copyright (c) 2000, 2012 IBM Corporation and others.
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

import com.sun.jdi.ArrayReference;
import com.sun.jdi.ArrayType;
import com.sun.jdi.ClassNotLoadedException;
import com.sun.jdi.InvalidTypeException;
import com.sun.jdi.StringReference;
import com.sun.jdi.Type;

/**
 * Tests for JDI com.sun.jdi.ArrayType
 * and JDWP Array command set.
 */
public class ArrayTypeTest extends AbstractJDITest {

	private ArrayType fType;
	/**
	 * Creates a new test.
	 */
	public ArrayTypeTest() {
		super();
	}
	/**
	 * Init the fields that are used by this test only.
	 */
	@Override
	public void localSetUp() {
		// Get array type
		fType = getArrayType();
	}


	/**
	 * Test JDI componentSignature().
	 */
	@Test
	public void testJDIComponentSignature() {
		String signature = fType.componentSignature();
		assertEquals("1", "Ljava/lang/String;", signature);
	}
	/**
	 * Test JDI componentType().
	 */
	@Test
	public void testJDIComponentType() {
		Type expected = fVM.classesByName("java.lang.String").get(0);
		Type type = null;
		try {
			type = fType.componentType();
		} catch (ClassNotLoadedException e) {
			assertTrue("1", false);
		}
		assertEquals("2", expected, type);
	}
	/**
	 * Test JDI componentTypeName().
	 */
	@Test
	public void testJDIComponentTypeName() {
		String typeName = fType.componentTypeName();
		assertEquals("1", "java.lang.String", typeName);
	}
	/**
	 * Test JDI newInstance(long).
	 */
	@Test
	public void testJDINewInstance() {
		ArrayReference instance = fType.newInstance(1);
		assertTrue("1", instance.type().equals(fType));
		assertEquals("2", 1, instance.length());
		assertTrue("3", null == instance.getValue(0));

		ArrayReference instance2 = fType.newInstance(5);
		try {
			instance2.setValue(3, fVM.mirrorOf("Yo"));
		} catch (InvalidTypeException exc) {
		} catch (ClassNotLoadedException exc) {
		}
		assertTrue("4", instance2.getValue(2) == null);
		assertEquals(
			"5",
			((StringReference) (instance2.getValue(3))).value(),
			"Yo");
	}
}
