package com.vinberts.charactermaker;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }

//	public void testRandomness() {
//		MersenneTwister mersenneTwister = new MersenneTwister();
//
//		Random r = new Random();
//		BufferedImage bimg = new BufferedImage(256, 256,
//				BufferedImage.TYPE_BYTE_BINARY);
//		int w = bimg.getWidth();
//		for (int y = 0; y < bimg.getHeight(); y++) {
//			for (int x = 0; x < w; x++) {
//				int bit;
//				if (mersenneTwister.nextBoolean()) {
//					bit = 1;
//				}
//				else {
//					bit = 0;
//				}
//
//				bimg.setRGB(x, y, (bit == 0) ? 0 : 0xffffff);
//			}
//		}
//
//		File outputfile = new File("saved" + System.currentTimeMillis() + ".png");
//		try {
//			ImageIO.write(bimg, "png", outputfile);
//		} catch (IOException e) {
//			e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//		}
//
//	}
}
