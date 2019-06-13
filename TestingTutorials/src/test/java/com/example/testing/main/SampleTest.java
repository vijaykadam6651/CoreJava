package com.example.testing.main;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

//Let's import Mockito statically so that the code looks clearer
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SampleTest {

	@Test
	public void testMock() {
		// mock creation
		List mockedList = mock(List.class);

		// using mock object
		mockedList.add("one");
		mockedList.clear();

		// verification
		verify(mockedList).add("one");
		verify(mockedList).clear();

		// You can mock concrete classes, not just interfaces
		LinkedList mockedLinkedList = mock(LinkedList.class);
		// stubbing
		when(mockedList.get(0)).thenReturn("first");
		// when(mockedList.get(1)).thenThrow(new RuntimeException());

		// following prints "first"
		System.out.println(mockedList.get(0));

		// following throws runtime exception
		// System.out.println(mockedList.get(1));

		// following prints "null" because get(999) was not stubbed
		System.out.println(mockedList.get(999));

		// Although it is possible to verify a stubbed invocation, usually it's just
		// redundant
		// If your code cares what get(0) returns, then something else breaks (often
		// even before verify() gets executed).
		// If your code doesn't care what get(0) returns, then it should not be stubbed.
		verify(mockedList).get(0);

		// stubbing using built-in anyInt() argument matcher
		when(mockedList.get(anyInt())).thenReturn("element");

		// stubbing using custom matcher (let's say isValid() returns your own matcher
		// implementation):
		//when(mockedList.contains(argThat(isValid()))).thenReturn(true);

		// following prints "element"
		System.out.println(mockedList.get(999));

		// you can also verify using an argument matcher
		//verify(mockedList).get(anyInt());

		// argument matchers can also be written as Java 8 Lambdas
		// verify(mockedList).add(argThat(//someString -> someString.length() > 5));

		// spy the real object
		List list = new LinkedList();
		List spy = spy(list);

		// optionally ,you can stub out some method
		when(spy.size()).thenReturn(100);

		// using the spy call the real methods
		spy.add("one");
		spy.add("two");
		// prints "one" - the first element of a list
		System.out.println(spy.get(0));

		// size() method was stubbed - 100 is printed
		System.out.println(spy.size());

		// optionally, you can verify
		verify(spy).add("one");
		verify(spy).add("two");

	}

	private ArgumentMatcher isValid() {
		// TODO Auto-generated method stub
		return null;
	}
}
