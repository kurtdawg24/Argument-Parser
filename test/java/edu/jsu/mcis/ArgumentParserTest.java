package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;
import edu.jsu.mcis.*;
import org.junit.rules.ExpectedException;

public class ArgumentParserTest {

	public ArgumentParser ap;

	@Before
	public void startUp(){
		ap=new ArgumentParser();
	}

	@Rule
		public ExpectedException expectedEx = ExpectedException.none();

	@Test
	public void testOnly3numbersEntered(){
		ap.assignProgramName("VolumeCalculator");
		ap.assignProgramDescription("Calculate the volume of a box.");
		ap.addPositionalArgument("length");
		ap.addPositionalArgument("width");
		ap.addPositionalArgument("height");
		String[] data= {"7","5","2"};
		ap.parse(data);
		assertEquals(3,ap.getSizeOfHashMap());
	}

	@Test (expected=MissingArgumentException.class)
	public void testOnly1numberEntered(){
		ap.assignProgramName("VolumeCalculator");
		ap.assignProgramDescription("Calculate the volume of a box.");
		ap.addPositionalArgument("length");
		ap.addPositionalArgument("width");
		ap.addPositionalArgument("height");
		String[] data={"7"};
		ap.parse(data);
	}

	@Test (expected=MissingArgumentException.class)
	public void testLessThan3NumbersEntered(){
		ap.assignProgramName("VolumeCalculator");
		ap.assignProgramDescription("Calculate the volume of a box.");
		ap.addPositionalArgument("length");
		ap.addPositionalArgument("width");
		ap.addPositionalArgument("height");
		String[] data= {"7","5"};
		ap.parse(data);
	}

	@Test (expected=UnknownArgumentException.class)
	public void testMoreThan3NumbersEntered(){
		ap.assignProgramName("VolumeCalculator");
		ap.assignProgramDescription("Calculate the volume of a box.");
		ap.addPositionalArgument("length");
		ap.addPositionalArgument("width");
		ap.addPositionalArgument("height");
		String[] data= {"7","5", "3", "7"};
		ap.parse(data);

	}

	@Test
	public void testGetLength(){
		ap.assignProgramName("VolumeCalculator");
		ap.assignProgramDescription("Calculate the volume of a box.");
		ap.addPositionalArgument("length");
		ap.addPositionalArgument("width");
		ap.addPositionalArgument("height");
		String[] data= {"7","5", "3"};
		ap.parse(data);
		assertEquals(ap.getValue("length"), "7");
	}

	@Test
	public void testHelpMessageCalled(){
		expectedEx.expect(HelpMessageException.class);
			expectedEx.expectMessage("usage: java VolumeCalculator [length] [width] [height]" + "\n" + "Calculate the volume of a box." + "\n" + "positional arguments:" + "\n" +   "length the length of the box (float)"  + "\n" +   "width the width of the box(float)" + "\n" + "height the height of the box(float)");
		String[] data ={"-h"};
		ap.assignProgramName("VolumeCalculator");
		ap.assignProgramDescription("Calculate the volume of a box.");
		ap.parse(data);
	}
/*
	@Test
	public void testGetDefaultType(){
		ap.assignProgramName("VolumeCalculator");
		ap.assignProgramDescription("Calculate the volume of a box.");
		ap.addPositionalArgument("length");
		ap.addPositionalArgument("width");
		ap.addPositionalArgument("height");
		ap.addNamedArgument("Type");
		String[] data= {"7","5", "3"};
		ap.parse(data);
		assertEquals("Box", ap.getValue("Type"));
	}
	*/

	@Test
	public void testGetType(){
		ap.assignProgramName("VolumeCalculator");
		ap.assignProgramDescription("Calculate the volume of a box.");
		ap.addPositionalArgument("length");
		ap.addPositionalArgument("width");
		ap.addPositionalArgument("height");
		ap.addNamedArgument("Type");
		String[] data= {"7","5", "3", "--Type","circle"};
		ap.parse(data);
		assertEquals("circle", ap.getValue("Type"));
	}

	@Test
	public void testGetColor(){
		ap.assignProgramName("VolumeCalculator");
		ap.assignProgramDescription("Calculate the volume of a box.");
		ap.addPositionalArgument("length");
		ap.addPositionalArgument("width");
		ap.addPositionalArgument("height");
		ap.addNamedArgument("Type");
		ap.addNamedArgument("Color");
		String[] data= {"7","5", "3", "--Type","circle","--Color","Blue"};
		ap.parse(data);
		assertEquals("Blue", ap.getValue("Color"));
	}

	@Test
	public void testGetPizza(){
		ap.assignProgramName("VolumeCalculator");
		ap.assignProgramDescription("Calculate the volume of a box.");
		ap.addPositionalArgument("length");
		ap.addPositionalArgument("width");
		ap.addPositionalArgument("height");
		ap.addNamedArgument("Pizza");
		ap.addNamedArgument("Color");
		String[] data= {"7","5", "3", "--Pizza","cheese","--Color","Blue"};
		ap.parse(data);
		assertEquals("cheese", ap.getValue("Pizza"));
	}

	@Test
	public void testGetDigit(){
		ap.assignProgramName("VolumeCalculator");
		ap.assignProgramDescription("Calculate the volume of a box.");
		ap.addPositionalArgument("length");
		ap.addPositionalArgument("width");
		ap.addPositionalArgument("height");
		ap.addNamedArgument("Type");
		ap.addNamedArgument("Digits");
		String[] data= {"7","5", "3", "--Type","circle","--Digits","1"};
		ap.parse(data);
		assertEquals("1", ap.getValue("Digits"));
	}

	@Test
	public void testGetTypeAnywhere(){
		ap.assignProgramName("VolumeCalculator");
		ap.assignProgramDescription("Calculate the volume of a box.");
		ap.addPositionalArgument("length");
		ap.addPositionalArgument("width");
		ap.addPositionalArgument("height");
		ap.addNamedArgument("Type");
		ap.addNamedArgument("Digits");
		String[] data= {"--Type","circle","7","5","--Digits","4","3"};
		ap.parse(data);
		assertEquals("circle", ap.getValue("Type"));
	}

	@Test
	public void testGetDigitsAnywhere(){
		ap.assignProgramName("VolumeCalculator");
		ap.assignProgramDescription("Calculate the volume of a box.");
		ap.addPositionalArgument("length");
		ap.addPositionalArgument("width");
		ap.addPositionalArgument("height");
		ap.addNamedArgument("Type");
		ap.addNamedArgument("Digits");
		String[] data= {"--Type","circle","2","5","--Digits","7","3"};
		ap.parse(data);
		assertEquals("7", ap.getValue("Digits"));
	}

	@Test
	public void testGetLengthAnywhere(){
		ap.assignProgramName("VolumeCalculator");
		ap.assignProgramDescription("Calculate the volume of a box.");
		ap.addPositionalArgument("length");
		ap.addPositionalArgument("width");
		ap.addPositionalArgument("height");
		ap.addNamedArgument("Type");
		ap.addNamedArgument("Digits");
		String[] data= {"--Type","circle","2","5","--Digits","7","3"};
		ap.parse(data);
		assertEquals("2", ap.getValue("length"));
	}
/*
	@Test
	public void testGetDigits(){
		ap.assignProgramName("VolumeCalculator");
		ap.assignProgramDescription("Calculate the volume of a box.");
		ap.addPositionalArgument("length");
		ap.addPositionalArgument("width");
		ap.addPositionalArgument("height");
		ap.addNamedArgument("Digits");
		String[] data= {"7","5", "3"};
		ap.parse(data);
		assertEquals("4", ap.getValue("Digits"));
	}
	*/

	@Test
	public void testTypeInt(){
		ap.assignProgramName("VolumeCalculator");
		ap.assignProgramDescription("Calculate the volume of a box.");
		ap.addPositionalArgument("length",Argument.Type.INT);
		ap.addPositionalArgument("width",Argument.Type.INT);
		ap.addPositionalArgument("height",Argument.Type.INT);
		String[] data= {"7","5", "3"};
		ap.parse(data);
		int num=7;
		assertEquals(ap.getValue("length"), num);
	}

	@Test
	public void testTypeBoolean(){
		ap.assignProgramName("VolumeCalculator");
		ap.assignProgramDescription("Calculate the volume of a box.");
		ap.addPositionalArgument("length",Argument.Type.INT);
		ap.addPositionalArgument("Dog",Argument.Type.BOOLEAN);
		ap.addPositionalArgument("height",Argument.Type.STRING);
		String[] data= {"7","true", "3"};
		ap.parse(data);
		boolean value=true;
		assertEquals(true, ap.getValue("Dog"));
	}

	@Test
	public void testTypeString(){
		ap.assignProgramName("VolumeCalculator");
		ap.assignProgramDescription("Calculate the volume of a box.");
		ap.addPositionalArgument("length",Argument.Type.INT);
		ap.addPositionalArgument("Dog",Argument.Type.STRING);
		ap.addPositionalArgument("height",Argument.Type.FLOAT);
		String[] data= {"7","true", "4.0"};
		ap.parse(data);
		assertEquals(ap.getValue("Dog"), "true");
	}

	@Test
	public void testTypeFloat(){
		ap.assignProgramName("VolumeCalculator");
		ap.assignProgramDescription("Calculate the volume of a box.");
		ap.addPositionalArgument("length",Argument.Type.INT);
		ap.addPositionalArgument("Dog",Argument.Type.FLOAT);
		ap.addPositionalArgument("height",Argument.Type.FLOAT);
		String[] data= {"7","8", "4"};
		ap.parse(data);
		float number=8.0f;
		assertEquals(ap.getValue("Dog"), number);
	}

	@Test (expected=IncorrectDataTypeException.class)
	public void testInvalidTypeFloat(){
		ap.assignProgramName("VolumeCalculator");
		ap.assignProgramDescription("Calculate the volume of a box.");
		ap.addPositionalArgument("length",Argument.Type.FLOAT);
		ap.addPositionalArgument("width",Argument.Type.FLOAT);
		ap.addPositionalArgument("height",Argument.Type.FLOAT);
		String[] data= {"7","something", "4"};
		ap.parse(data);
	}

	@Test
	public void testIncorrectDataTypeExceptionMessageCorrect() {
		expectedEx.expect(IncorrectDataTypeException.class);
			expectedEx.expectMessage("usage: java VolumeCalculator [length] [width] [height]. VolumeCalculator.java: error: the following arguments are required: length width height");
		ap.assignProgramName("VolumeCalculator");
		ap.assignProgramDescription("Calculate the volume of a box.");
		ap.addPositionalArgument("length",Argument.Type.FLOAT);
		ap.addPositionalArgument("width",Argument.Type.FLOAT);
		ap.addPositionalArgument("height",Argument.Type.FLOAT);
		String[] data= {"7","something", "4"};
		ap.parse(data);
	}

	@Test
	public void testHelpMessageStringBuilder() {
		ap.addPositionalArgument("length");
		ap.addPositionalArgument("width");
		ap.addPositionalArgument("height");
		assertEquals("[lenth] [width] [height]", ap.returnMissingArguments());
	}

	@Test (expected=IncorrectDataTypeException.class)
	public void testInvalidTypeInteger(){
		ap.assignProgramName("VolumeCalculator");
		ap.assignProgramDescription("Calculate the volume of a box.");
		ap.addPositionalArgument("length",Argument.Type.FLOAT);
		ap.addPositionalArgument("width",Argument.Type.INT);
		ap.addPositionalArgument("height",Argument.Type.FLOAT);
		String[] data= {"7","something", "4"};
		ap.parse(data);
	}

	//Need a help message exception class.
	//Exception class written, need to get it working.
	@Test
	public void testGetHelpMessageShortNameAnywhere(){
		expectedEx.expect(HelpMessageException.class);
			expectedEx.expectMessage("usage: java VolumeCalculator [length] [width] [height]" + "\n" + "Calculate the volume of a box." + "\n" + "positional arguments:" + "\n" +   "length the length of the box (float)"  + "\n" +   "width the width of the box(float)" + "\n" + "height the height of the box(float)");
		ap.assignProgramName("VolumeCalculator");
		ap.assignProgramDescription("Calculate the volume of a box.");
		ap.addPositionalArgument("length");
		ap.addPositionalArgument("width");
		ap.addPositionalArgument("height");
		String[] data= {"7","5", "--h", "4"};
		ap.parse(data);
	}

	@Test
	public void testGetHelpMessageLongNameAnywhere(){
		expectedEx.expect(HelpMessageException.class);
			expectedEx.expectMessage("usage: java VolumeCalculator [length] [width] [height]" + "\n" + "Calculate the volume of a box." + "\n" + "positional arguments:" + "\n" +   "length the length of the box (float)"  + "\n" +   "width the width of the box(float)" + "\n" + "height the height of the box(float)");
		ap.assignProgramName("VolumeCalculator");
		ap.assignProgramDescription("Calculate the volume of a box.");
		ap.addPositionalArgument("length");
		ap.addPositionalArgument("width");
		ap.addPositionalArgument("height");
		String[] data= {"7","5", "--help", "4"};
		ap.parse(data);
	}

	@Test
	public void testGetHelpMessageAnywhereWithNamedArguments(){
		expectedEx.expect(HelpMessageException.class);
			expectedEx.expectMessage("usage: java VolumeCalculator [length] [width] [height]" + "\n" + "Calculate the volume of a box." + "\n" + "positional arguments:" + "\n" +   "length the length of the box (float)"  + "\n" +   "width the width of the box(float)" + "\n" + "height the height of the box(float)");
		ap.assignProgramName("VolumeCalculator");
		ap.assignProgramDescription("Calculate the volume of a box.");
		ap.addPositionalArgument("length");
		ap.addPositionalArgument("width");
		ap.addPositionalArgument("height");
		ap.addNamedArgument("Type");
		ap.addNamedArgument("Digits");
		String[] data= {"7","--Type","circle","5", "--h", "4","--Digits","2"};
		ap.parse(data);
	}

	@Test
	public void testShortNamedArgument(){
		ap.assignProgramName("VolumeCalculator");
		ap.assignProgramDescription("Calculate the volume of a box.");
		ap.addPositionalArgument("length");
		ap.addPositionalArgument("width");
		ap.addPositionalArgument("height");
		ap.addNamedArgument("Type");
		ap.addNamedArgument("Digits");
		String[] data= {"7","-t","circle","5", "4","--Digits","2"};
		ap.parse(data);
		assertEquals("circle",ap.getValue("Type"));
	}

	@Test
	public void testShortNamedColorArgument(){
		ap.assignProgramName("VolumeCalculator");
		ap.assignProgramDescription("Calculate the volume of a box.");
		ap.addPositionalArgument("length");
		ap.addPositionalArgument("width");
		ap.addPositionalArgument("height");
		ap.addNamedArgument("color");
		ap.addNamedArgument("Digits");
		String[] data= {"7","-c","red","5", "4","--Digits","2"};
		ap.parse(data);
		assertEquals("red",ap.getValue("color"));
	}

	@Test
	public void testGetHelp(){
		expectedEx.expect(HelpMessageException.class);
			expectedEx.expectMessage("usage: java VolumeCalculator [length] [width] [height]" + "\n" + "Calculate the volume of a box." + "\n" + "positional arguments:" + "\n" +   "length the length of the box (float)"  + "\n" +   "width the width of the box(float)" + "\n" + "height the height of the box(float)");
		String[] data ={"--h"};
		ap.assignProgramName("VolumeCalculator");
		ap.assignProgramDescription("Calculate the volume of a box.");
		ap.addPositionalArgument("length");
		ap.addPositionalArgument("width");
		ap.addPositionalArgument("height");
		ap.parse(data);
	}

	@Test
	public void testGetProgramName(){
		ap.assignProgramName("VolumeCalculator");
		assertEquals (ap.getProgramName(), "VolumeCalculator");
	}

	@Test
	public void testAssignProgramDescription(){
		ap.assignProgramDescription("Calculate the volume of a box");
		assertEquals (ap.getProgramDescription(), "Calculate the volume of a box");
	}

	@Test
	public void testForLoopReturnsCorrectMissingArguments(){
		//nothing at the moment
	}
}
