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

import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import com.sun.jdi.ClassType;
import com.sun.jdi.InterfaceType;
import com.sun.jdi.Method;

/**
 * Tests for JDI com.sun.jdi.InterfaceType
 * and JDWP Interface command set.
 */
public class InterfaceTypeTest extends AbstractJDITest {

	private InterfaceType fType;
	/**
	 * Creates a new test.
	 */
	public InterfaceTypeTest() {
		super();
	}
	/**
	 * Init the fields that are used by this test only.
	 */
	@Override
	public void localSetUp() {
		// Get interface type "org.eclipse.debug.jdi.tests.program.Printable"
		fType = getInterfaceType();
	}


	/**
	 * Test JDI allFields().
	 */
	@Test
	public void testJDIAllFields() {
		assertEquals("1", 1, fType.allFields().size());
	}
	/**
	 * Test JDI allMethods().
	 */
	@Test
	public void testJDIAllMethods() {
		boolean found = false;
		Iterator<?> it = fType.allMethods().iterator();
		while (it.hasNext()) {
			Method mth = (Method) it.next();
			if (mth.name().equals("print")) {
				found = true;
			}
		}
		assertTrue("1", fType.allMethods().size() == 1);
		assertTrue("2", found);
	}
	/**
	 * Test JDI implementors().
	 */
	@Test
	public void testJDIImplementors() {
		List<?> implementors = fType.implementors();
		assertEquals("1", 1, implementors.size());
		ClassType implementor = (ClassType) implementors.get(0);
		assertEquals("2", getMainClass(), implementor);
	}
	/**
	 * Test JDI subinterfaces().
	 */
	@Test
	public void testJDISubinterfaces() {
		List<?> subinterfaces = fType.subinterfaces();
		assertEquals("1", 0, subinterfaces.size());
	}
	/**
	 * Test JDI superinterfaces().
	 */
	@Test
	public void testJDISuperinterfaces() {
		List<?> superinterfaces = fType.superinterfaces();
		assertEquals("1", 1, superinterfaces.size());
		InterfaceType superinterface = (InterfaceType) superinterfaces.get(0);
		InterfaceType expected =
			(InterfaceType) fVM.classesByName("java.lang.Cloneable").get(0);
		assertEquals("2", expected, superinterface);
	}
}
