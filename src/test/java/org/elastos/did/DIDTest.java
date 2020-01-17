/*
 * Copyright (c) 2019 Elastos Foundation
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package org.elastos.did;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.elastos.did.exception.MalformedDIDException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DIDTest {
	private static final String testMethodSpecificID = "icJ4z2DULrHEzYSvjKNJpKyhqFDxvYV7pN";
	private static final String testDID = "did:elastos:icJ4z2DULrHEzYSvjKNJpKyhqFDxvYV7pN";

	private DID did;

    @BeforeEach
    public void setup() throws MalformedDIDException {
    	did = new DID(testDID);
    }

	@Test
	public void testConstructor() throws MalformedDIDException {
		DID did = new DID(testDID);
		assertEquals(testDID, did.toString());

		did = new DID("did:elastos:1234567890");
		assertEquals("did:elastos:1234567890", did.toString());
	}

	@Test
	public void testConstructorError1() {
		assertThrows(MalformedDIDException.class, () -> {
			new DID("id:elastos:1234567890");
		});
	}

	@Test
	public void testConstructorError2() {
		assertThrows(MalformedDIDException.class, () -> {
			new DID("did:example:1234567890");
		});
	}

	@Test
	public void testConstructorError3() {
		assertThrows(MalformedDIDException.class, () -> {
			new DID("did:elastos:");
		});
	}

	@Test
	public void testGetMethod()  {
		assertEquals(DID.METHOD, did.getMethod());
	}

	@Test
	public void testGetMethodSpecificId() {
		assertEquals(testMethodSpecificID, did.getMethodSpecificId());
	}

	@Test
	public void testToString() {
		assertEquals(testDID, did.toString());
	}

	@Test
	public void testHashCode() throws MalformedDIDException {
		DID other = new DID(testDID);
		assertEquals(did.hashCode(), other.hashCode());

		other = new DID("did:elastos:1234567890");
		assertNotEquals(did.hashCode(), other.hashCode());
	}

	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void testEquals() throws MalformedDIDException {
		DID other = new DID(testDID);
		assertTrue(did.equals(other));
		assertTrue(did.equals(testDID));

		other = new DID("did:elastos:1234567890");
		assertFalse(did.equals(other));
		assertFalse(did.equals("did:elastos:1234567890"));
	}
}
