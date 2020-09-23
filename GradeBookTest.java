import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Joseph Ghormley
 *
 */
class GradeBookTest 
{
GradeBook g1,g2;
	@BeforeEach
	void setUp() throws Exception
	{
		g1=new GradeBook(5);
		g1.addScore(50.0);
		g1.addScore(75.0);
		g2=new GradeBook(5);
		g2.addScore(80.0);
		g2.addScore(90.0);
	
	}
	

	@AfterEach
	void tearDown() throws Exception 
	{
		g1=null;
		g2=null;
	}

	@Test
	void testAddScore() 
	{
		assertEquals(2.0,g1.getScoreSize(),0.01);
		assertEquals(2.0,g2.getScoreSize(),0.01);
		assertTrue(g1.toString().equals("50.0 75.0 "));
		assertTrue(g2.toString().equals("80.0 90.0 "));
	}

	@Test
	void testSum() 
	{
		assertEquals(125,g1.sum(), .0001);
		assertEquals(170,g2.sum(), .0001);
	}

	@Test
	void testMinimum()
	{
		assertEquals(50, g1.minimum(), .001);
		assertEquals(80, g2.minimum(), .001);
	}

	@Test
	void testFinalScore() 
	{
		assertEquals(75.0, g1.finalScore(),.0001);
		assertEquals(90.0, g2.finalScore(),.0001);
	}

	@Test
	void testGetScoreSize() 
	{
		assertEquals(2.0,g1.getScoreSize(),0.01);
		assertEquals(2.0,g2.getScoreSize(),0.01);
	}

	@Test
	void testToString() 
	{
		assertTrue(g1.toString().equals("50.0 75.0 "));
		assertTrue(g2.toString().equals("80.0 90.0 "));
	}

}

