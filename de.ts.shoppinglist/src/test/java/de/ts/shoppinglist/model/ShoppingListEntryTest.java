package de.ts.shoppinglist.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ShoppingListEntryTest {

	private ShoppingListEntry classUnderTest;
	private ShoppingItem item;
	private ShoppingItemQuantity shoppingQuantity;

	@Before
	public void setup() {
		this.item = new ShoppingItem("Test");
		this.shoppingQuantity = new ShoppingItemQuantity(1);
		this.classUnderTest = new ShoppingListEntry(item, shoppingQuantity);
	}

	@Test
	public void testIsValid() throws Exception {

		boolean expected = true;

		boolean actual = classUnderTest.isValid();

		assertEquals(expected, actual);
	}

	@Test
	public void testIsNotValidItemNull() throws Exception {

		boolean expected = false;

		classUnderTest.setItem(null);

		boolean actual = classUnderTest.isValid();

		assertEquals(expected, actual);
	}

	@Test
	public void testIsNotValidQuantityNull() throws Exception {

		boolean expected = false;

		classUnderTest.setQuantity(null);

		boolean actual = classUnderTest.isValid();

		assertEquals(expected, actual);
	}

	@Test
	public void testIsNotValidItemEmpty() throws Exception {

		boolean expected = false;

		classUnderTest.setItem(new ShoppingItem(""));

		boolean actual = classUnderTest.isValid();

		assertEquals(expected, actual);
	}

	@Test
	public void testIsNotValidQuantityZero() throws Exception {

		boolean expected = false;

		classUnderTest.setQuantity(new ShoppingItemQuantity(0));

		boolean actual = classUnderTest.isValid();

		assertEquals(expected, actual);
	}

	@Test
	public void testEqualsWithItself() throws Exception {

		boolean expected = true;

		boolean actual = classUnderTest.equals(classUnderTest);

		assertEquals(expected, actual);
	}

	@Test
	public void testEqualsWithSameId() throws Exception
	{

		boolean expected = true;

		ShoppingListEntry otherClass = new ShoppingListEntry(null, shoppingQuantity);
		long id = classUnderTest.getId();
		otherClass.setId(id);

		boolean actual = classUnderTest.equals(otherClass);

		assertEquals(expected, actual);
	}

	@Test
	public void testNotEqualsWithOtherId() throws Exception {

		boolean expected = false;

		ShoppingListEntry otherClass = new ShoppingListEntry(item, shoppingQuantity);
		boolean actual = classUnderTest.equals(otherClass);

		assertEquals(expected, actual);
	}

}
