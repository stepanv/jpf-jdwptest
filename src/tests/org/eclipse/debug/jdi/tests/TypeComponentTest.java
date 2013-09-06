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

import com.sun.jdi.ReferenceType;
import com.sun.jdi.TypeComponent;

/**
 * Tests for JDI com.sun.jdi.TypeComponent.
 */
public class TypeComponentTest extends AbstractJDITest {

	private TypeComponent fField, fMethod;
	/**
	 * Creates a new test.
	 */
	public TypeComponentTest() {
		super();
	}
	/**
	 * Init the fields that are used by this test only.
	 */
	@Override
	public void localSetUp() {
		// Get field fObject in org.eclipse.debug.jdi.tests.program.MainClass
		fField = getField();

		// Get method print(OutputStream)
		fMethod = getMethod();
	}


	/**
	 * Test JDI declaringType().
	 */
	@Test
	public void testJDIDeclaringType() {
		ReferenceType mainClass = getMainClass();

		ReferenceType declaringType = fField.declaringType();
		assertEquals("1", mainClass, declaringType);

		declaringType = fMethod.declaringType();
		assertEquals("2", mainClass, declaringType);
	}
	/**
	 * Test JDI isFinal().
	 */
	@Test
	public void testJDIIsFinal() {
		assertTrue("1", !fField.isFinal());
		assertTrue("2", !fMethod.isFinal());
	}
	/**
	 * Test JDI isStatic().
	 */
	@Test
	public void testJDIIsStatic() {
		assertTrue("1", fField.isStatic());
		assertTrue("2", !fMethod.isStatic());
	}
	/**
	 * Test JDI isSynthetic().
	 */
	@Test
	public void testJDIIsSynthetic() {
		if (!fVM.canGetSyntheticAttribute()) {
			return;
		}

		assertTrue("1", !fField.isSynthetic());
		assertTrue("2", !fMethod.isSynthetic());
	}
	/**
	 * Test JDI name().
	 */
	@Test
	public void testJDIName() {
		assertEquals("1", "fObject", fField.name());
		assertEquals("2", "print", fMethod.name());
	}
	/**
	 * Test JDI signature().
	 */
	@Test
	public void testJDISignature() {
		assertEquals(
			"1",
			"Lorg/eclipse/debug/jdi/tests/program/MainClass;",
			fField.signature());
		assertEquals("2", "(Ljava/io/OutputStream;)V", fMethod.signature());
	}
}
