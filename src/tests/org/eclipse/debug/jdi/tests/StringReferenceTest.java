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

import org.junit.Test;

import com.sun.jdi.StringReference;

/**
 * Tests for JDI com.sun.jdi.StringReference
 * and JDWP String command set.
 */
public class StringReferenceTest extends AbstractJDITest {

	private StringReference fString;
	/**
	 * Creates a new test.
	 */
	public StringReferenceTest() {
		super();
	}
	/**
	 * Init the fields that are used by this test only.
	 */
	@Override
	public void localSetUp() {
		// Get static field "fString"
		fString = getStringReference();
	}


	/**
	 * Test JDI value() and JDWP 'String - Get value'.
	 */
	@Test
	public void testJDIValue() {
		String value = fString.value();
		assertEquals("1", "Hello World", value);
	}
}
