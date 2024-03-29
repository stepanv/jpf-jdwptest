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

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.sun.jdi.Accessible;

/**
 * Tests for JDI com.sun.jdi.Accessible.
 */
public class AccessibleTest extends AbstractJDITest {

	private Accessible fArrayType, fClassType, fInterfaceType, fField, fMethod;

	/**
	 * Creates a new test.
	 */
	public AccessibleTest() {
		super();
	}

	/**
	 * Init the fields that are used by this test only.
	 */
	@Override
	public void localSetUp() {
		// Get the all kinds of accessible

		// ReferenceType
		fArrayType = getArrayType();
		fClassType = getMainClass();
		fInterfaceType = getInterfaceType();

		// TypeComponent
		fField = getField();
		fMethod = getMethod();
	}

	/**
	 * Test JDI isPackagePrivate().
	 */
	@Test
	public void testJDIIsPackagePrivate() {
		assertTrue("1", !fArrayType.isPackagePrivate());
		assertTrue("2", !fClassType.isPackagePrivate());
		assertTrue("3", !fInterfaceType.isPackagePrivate());
		assertTrue("4", !fField.isPackagePrivate());
		assertTrue("5", !fMethod.isPackagePrivate());
	}

	/**
	 * Test JDI isPrivate().
	 */
	@Test
	public void testJDIIsPrivate() {
		assertTrue("1", !fField.isPrivate());
		assertTrue("2", !fMethod.isPrivate());

		// NB: isPrivate() is undefined for a type
	}

	/**
	 * Test JDI isProtected().
	 */
	@Test
	public void testJDIIsProtected() {
		assertTrue("1", !fField.isProtected());
		assertTrue("2", !fMethod.isProtected());

		// NB: isProtected() is undefined for a type
	}

	/**
	 * Test JDI isPublic().
	 */
	@Test
	public void testJDIIsPublic() {
		assertTrue("1", fArrayType.isPublic());
		assertTrue("2", fClassType.isPublic());
		assertTrue("3", fInterfaceType.isPublic());
		assertTrue("4", fField.isPublic());
		assertTrue("5", fMethod.isPublic());
	}
}
