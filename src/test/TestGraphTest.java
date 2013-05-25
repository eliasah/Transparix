package test;

import static org.junit.Assert.*;
import itinerary.Graph;

import org.junit.Before;
import org.junit.Test;

public class TestGraphTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		int i=0;
		for(i=0; i<1000;i++)
			new Graph();
	}

}
