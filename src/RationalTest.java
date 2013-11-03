import junit.framework.TestCase;

import org.junit.Test;

public class RationalTest extends TestCase {
	
    protected Rational HALF;

    protected void setUp() {
      HALF = new Rational( 1, 2 );
      Rational.setTolerance(new Rational(1, 1000));
    }

    // Create new test
    public RationalTest (String name) {
        super(name);
    }

    public void testEquality() {
        assertEquals(new Rational(1,3), new Rational(1,3));
        assertEquals(new Rational(1,3), new Rational(2,6));
        assertEquals(new Rational(3,3), new Rational(1,1));
    }

    // Test for nonequality
    public void testNonEquality() {
        assertFalse(new Rational(2,3).equals(
            new Rational(1,3)));
    }

    public void testAccessors() {
    	assertEquals(new Rational(2,3).numerator(), 2);
    	assertEquals(new Rational(2,3).denominator(), 3);
    }

    public void testRoot() {
        Rational s = new Rational( 1, 4 );
        Rational sRoot = null;
        try {
            sRoot = s.root();
        } catch (IllegalArgumentToSquareRootException e) {
            e.printStackTrace();
        }
        assertTrue( sRoot.isLessThan( HALF.plus( Rational.getTolerance() ) ) 
                        && HALF.minus( Rational.getTolerance() ).isLessThan( sRoot ) );
    }

    @Test(expected = ArithmeticException.class) 
    public void testRationalWithZero() {
        try {
        	Rational s = new Rational( 0, 0 );
        	assertTrue(true);
        } catch (ArithmeticException e) {
            assertFalse(true);
        }
    }

    public void testBigMultiplication() {
    	Rational s = new Rational( 1000000000, 1 );
    	Rational t = new Rational( 1000000000, 1 );
    	Rational u = s.times(t);
    	assertTrue(u.numerator() > 0);
    }

    public void testMultiplyDivide() {
    	Rational s = new Rational( 2, 1 );
    	Rational t = new Rational( 1, 2 );
    	Rational u = s.times(t);
    	Rational v = u.divides(s);
    	assertTrue(v.equals(t));
    }

    public void testDivideByZero() {
    	Rational s = new Rational( 0, 1 );
    	Rational t = new Rational( 1, 2 );
    	assertTrue(t.divides(s).equals(new Rational(3, 0)));
    }
    
    public void testAdditionSubtraction() {
    	Rational s = new Rational( 2, -1 );
    	Rational t = new Rational( 1, -2 );
    	Rational u = s.plus(t);
    	Rational v = u.minus(s);
    	assertTrue(v.equals(t));
    }
    
    public void testExtremeSubtraction() {
    	Rational s = new Rational( 0, 1 );
    	Rational t = new Rational( 100, 2 );
    	Rational v = s.minus(t);
    	assertTrue(v.equals(new Rational(-50, 1)));
    }    
    
    @Test(expected = IllegalArgumentToSquareRootException.class) 
    public void testNegativeRoot() {
    	Rational s = new Rational( -1, 4 );
    	try {
			Rational root = s.root();
		} catch (IllegalArgumentToSquareRootException e) {

		}
    }

    @Test(expected = IllegalArgumentToSquareRootException.class) 
    public void testLargeRootInRange() {
    	Rational s = new Rational( 46341, 1 );
    	try {
			Rational root = s.root();
		} catch (IllegalArgumentToSquareRootException e) {
			assertFalse(true);
		}
    }

    public void testNegativeZero() {
    	Rational s = new Rational( -0, -1 );
		Rational abs = s.abs();
		assertEquals(abs, new Rational( 0, 1 ));
    }

    public void testAbsoluteValueNegatives() {
    	Rational s = new Rational( -1, -1 );
		Rational abs = s.abs();
		assertEquals(abs, new Rational( 1, 1 ));
    }

    public void testAbsoluteValueMixed() {
    	Rational s = new Rational( -1, 1 );
		Rational abs = s.abs();
		assertEquals(abs, new Rational( 1, 1 ));
    }
    
    public void testToString() {
    	Rational s = new Rational(13, -11);
    	assertEquals(s.toString(), "13/-11");
    }

    public void testEqualsNull() {
    	assertFalse(new Rational( 2, 1 ).equals(null));
    }

    public void testEqualsObject() {
    	Object o = new Object();
    	assertFalse(new Rational( 2, 1 ).equals(o));
    }
    
    public void testGCDPrimes() {
    	Rational s = new Rational( -13, -29 );
    	assertEquals(s.numerator(), -13);
    	assertEquals(s.denominator(), -29);
    }

    public static void main(String args[]) {
        String[] testCaseName = 
            { RationalTest.class.getName() };
        // junit.swingui.TestRunner.main(testCaseName);
        junit.textui.TestRunner.main(testCaseName);
    }
}