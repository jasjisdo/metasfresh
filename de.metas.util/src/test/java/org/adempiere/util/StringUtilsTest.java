package org.adempiere.util;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/*
 * #%L
 * de.metas.util
 * %%
 * Copyright (C) 2015 metas GmbH
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 2 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-2.0.html>.
 * #L%
 */


import org.junit.Assert;
import org.junit.Test;

public class StringUtilsTest
{
	@Test
	public void testTrunc()
	{
		testTrunc(null, 10, null);
		testTrunc("", 0, "");
		testTrunc("", 10, "");
		testTrunc("1234567890", 0, "");
		testTrunc("1234567890", 1, "1");
		testTrunc("1234567890", 2, "12");
		testTrunc("1234567890", 3, "123");
		testTrunc("1234567890", 4, "1234");
		testTrunc("1234567890", 5, "12345");
		testTrunc("1234567890", 6, "123456");
		testTrunc("1234567890", 7, "1234567");
		testTrunc("1234567890", 8, "12345678");
		testTrunc("1234567890", 9, "123456789");
		testTrunc("1234567890", 10, "1234567890");
		testTrunc("1234567890", 11, "1234567890");
		testTrunc("1234567890", 12, "1234567890");
		testTrunc("1234567890", 13, "1234567890");
	}

	@Test(expected = StringIndexOutOfBoundsException.class)
	public void testTrunc_invalidLength()
	{
		testTrunc("1234567890", -10, "1234567890");
	}

	private final void testTrunc(final String string, final int length, final String resultExpected)
	{
		final String resultActual = StringUtils.trunc(string, length);
		Assert.assertEquals("Invalid trunc() result for string=" + string + ", length=" + length, resultExpected, resultActual);
	}

	@Test
	public void test_isNumber_Null()
	{
		test_isNumber(null, false);
	}

	@Test
	public void test_isNumber_Empty()
	{
		test_isNumber("", false);
	}

	@Test
	public void test_isNumber_StringWithSpaces()
	{
		test_isNumber(" 123", false);
	}

	@Test
	public void test_isNumber_StandardCases()
	{
		test_isNumber("1", true);
		test_isNumber("01", true);
		test_isNumber("01234567890", true);
		test_isNumber("0000000000000", true);
	}


	@Test
	public void test_quote()
	{
		assertThat(StringUtils.formatMessage("{0} text with ' quote", "test"), is("test text with ' quote"));
		assertThat(StringUtils.formatMessage("text with ' quote {0}", "test"), is("text with ' quote test"));
		assertThat(StringUtils.formatMessage("{0} text with '' doublequote", "test"), is("test text with '' doublequote"));
		assertThat(StringUtils.formatMessage("text with '' doublequote {0}", "test"), is("text with '' doublequote test"));
	}

	@Test
	public void test_overlayAtEnd()
	{
		assertThat(StringUtils.overlayAtEnd("0000000000", "123456"), is("0000123456"));
		assertThat(StringUtils.overlayAtEnd("000a000000", "123456"), is("000a123456"));
		assertThat(StringUtils.overlayAtEnd("000", "123456"), is("123456"));
	}

	private void test_isNumber(final String string, final boolean expected)
	{
		Assert.assertEquals("Invalid StringUtils.isNumber() return for string: " + string, expected, StringUtils.isNumber(string));
	}

}
