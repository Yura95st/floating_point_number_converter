package floating_point_number_converter.Utils;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import floating_point_number_converter.Models.FloatingPointNumber;

public class NumberUtilsTests
{
	@Test(expected = IllegalArgumentException.class)
	public void convertFractionalPartToBinaryNumber_BitsLengthIsEqualToZero_ThrowsIllegalArgumentException()
	{
		// Arrange
		double number = 1.0;

		// Act & Assert
		NumberUtils.convertFractionalPartToBinaryNumber(number, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void convertFractionalPartToBinaryNumber_BitsLengthIsNegative_ThrowsIllegalArgumentException()
	{
		// Arrange
		double number = 1.0;

		// Act & Assert
		NumberUtils.convertFractionalPartToBinaryNumber(number, -1);
	}

	@Test
	public void convertFractionalPartToBinaryNumber_NumberFractionalPartDoesNotFitInTheBitsLength_CutsExtraBits()
	{
		// Arrange
		double number = 4.6;

		List<Boolean> testBinaryNumber =
			Arrays.asList(true, false, false, true, true, false);

		// Act
		List<Boolean> binaryNumber =
			NumberUtils.convertFractionalPartToBinaryNumber(number,
				testBinaryNumber.size());

		// Assert
		Assert.assertEquals(testBinaryNumber, binaryNumber);
	}

	@Test
	public void convertFractionalPartToBinaryNumber_NumberFractionalPartIsEqualToZero_ReturnsValidBinaryNumber()
	{
		// Arrange
		double number = 4.0;

		List<Boolean> testBinaryNumber = Arrays.asList(false, false);

		// Act
		List<Boolean> binaryNumber =
			NumberUtils.convertFractionalPartToBinaryNumber(number,
				testBinaryNumber.size());

		// Assert
		Assert.assertEquals(testBinaryNumber, binaryNumber);
	}

	@Test
	public void convertFractionalPartToBinaryNumber_NumberIsNegative_ReturnsValidBinaryNumber()
	{
		// Arrange
		double number = -4.25;

		List<Boolean> testBinaryNumber =
			Arrays.asList(false, true, false, false);

		// Act
		List<Boolean> binaryNumber =
			NumberUtils.convertFractionalPartToBinaryNumber(number,
				testBinaryNumber.size());

		// Assert
		Assert.assertEquals(testBinaryNumber, binaryNumber);
	}

	@Test
	public void convertFractionalPartToBinaryNumber_NumberIsPositive_ReturnsValidBinaryNumber()
	{
		// Arrange
		double number = 4.25;

		List<Boolean> testBinaryNumber =
			Arrays.asList(false, true, false, false);

		// Act
		List<Boolean> binaryNumber =
			NumberUtils.convertFractionalPartToBinaryNumber(number,
				testBinaryNumber.size());

		// Assert
		Assert.assertEquals(testBinaryNumber, binaryNumber);
	}

	@Test(expected = IllegalArgumentException.class)
	public void convertToBinaryNumber_BitsLengthIsEqualToZero_ThrowsIllegalArgumentException()
	{
		// Arrange
		int number = 1;

		// Act & Assert
		NumberUtils.convertToBinaryNumber(number, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void convertToBinaryNumber_BitsLengthIsNegative_ThrowsIllegalArgumentException()
	{
		// Arrange
		int number = 1;

		// Act & Assert
		NumberUtils.convertToBinaryNumber(number, -1);
	}

	@Test
	public void convertToBinaryNumber_NumberDoesNotFitInTheBitsLength_CutsExtraBits()
	{
		// Arrange
		int number = 4;

		List<Boolean> testBinaryNumber = Arrays.asList(true, false);

		// Act
		List<Boolean> binaryNumber =
			NumberUtils.convertToBinaryNumber(number, testBinaryNumber.size());

		// Assert
		Assert.assertEquals(testBinaryNumber, binaryNumber);
	}

	@Test
	public void convertToBinaryNumber_NumberIsEqualToZero_ReturnsValidBinaryNumber()
	{
		// Arrange
		int number = 0;

		List<Boolean> testBinaryNumber = Arrays.asList(false, false, false);

		// Act
		List<Boolean> binaryNumber =
			NumberUtils.convertToBinaryNumber(number, testBinaryNumber.size());

		// Assert
		Assert.assertEquals(testBinaryNumber, binaryNumber);
	}

	@Test
	public void convertToBinaryNumber_NumberIsGreaterThanZero_ReturnsValidBinaryNumber()
	{
		// Arrange
		int number = 3;

		List<Boolean> testBinaryNumber = Arrays.asList(true, true);

		// Act
		List<Boolean> binaryNumber =
			NumberUtils.convertToBinaryNumber(number, testBinaryNumber.size());

		// Assert
		Assert.assertEquals(testBinaryNumber, binaryNumber);
	}

	@Test(expected = IllegalArgumentException.class)
	public void convertToBinaryNumber_NumberIsLessThanZero_ThrowsIllegalArgumentException()
	{
		// Arrange
		int bitsLength = 1;

		// Act & Assert
		NumberUtils.convertToBinaryNumber(-1, bitsLength);
	}

	@Test(expected = IllegalArgumentException.class)
	public void convertToFloatingPointNumber_ExponentLengthIsEqualToZero_ThrowsIllegalArgumentException()
	{
		// Arrange
		double number = 1.0;
		int mantissaLength = 1;

		// Act & Assert
		NumberUtils.convertToFloatingPointNumber(number, 0, mantissaLength);
	}

	@Test(expected = IllegalArgumentException.class)
	public void convertToFloatingPointNumber_ExponentLengthIsLessThanZero_ThrowsIllegalArgumentException()
	{
		// Arrange
		double number = 1.0;
		int mantissaLength = 1;

		// Act & Assert
		NumberUtils.convertToFloatingPointNumber(number, -1, mantissaLength);
	}

	@Test(expected = IllegalArgumentException.class)
	public void convertToFloatingPointNumber_MantissaLengthIsEqualToZero_ThrowsIllegalArgumentException()
	{
		// Arrange
		double number = 1.0;
		int exponentLength = 1;

		// Act & Assert
		NumberUtils.convertToFloatingPointNumber(number, exponentLength, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void convertToFloatingPointNumber_MantissaLengthIsLessThanZero_ThrowsIllegalArgumentException()
	{
		// Arrange
		double number = 1.0;
		int exponentLength = 1;

		// Act & Assert
		NumberUtils.convertToFloatingPointNumber(number, exponentLength, -1);
	}

	@Test
	public void convertToFloatingPointNumber_NumberExponentDoesNotFitInMantissaLength_CutsExtraBits()
	{
		// Arrange
		double number = 0.5;
		int exponentLength = 1;
		int mantissaLength = 2;

		FloatingPointNumber testFloatingPointNumber =
			new FloatingPointNumber(exponentLength, mantissaLength);

		testFloatingPointNumber.setExponent(Arrays.asList(false));
		testFloatingPointNumber.setMantissa(Arrays.asList(false, false));
		testFloatingPointNumber.setSign(false);
		testFloatingPointNumber.setIsNormilized(false);

		// Act
		FloatingPointNumber floatingPointNumber =
			NumberUtils.convertToFloatingPointNumber(number, exponentLength,
				mantissaLength);

		// Assert
		Assert.assertEquals(testFloatingPointNumber, floatingPointNumber);
	}

	@Test
	public void convertToFloatingPointNumber_NumberIsNotNormilized_ReturnsValidFloatingPointNumber()
	{
		// Arrange
		double number = 0.2;
		int exponentLength = 1;
		int mantissaLength = 2;

		FloatingPointNumber testFloatingPointNumber =
			new FloatingPointNumber(exponentLength, mantissaLength);

		testFloatingPointNumber.setExponent(Arrays.asList(false));
		testFloatingPointNumber.setMantissa(Arrays.asList(true, false));
		testFloatingPointNumber.setSign(false);
		testFloatingPointNumber.setIsNormilized(false);

		// Act
		FloatingPointNumber floatingPointNumber =
			NumberUtils.convertToFloatingPointNumber(number, exponentLength,
				mantissaLength);

		// Assert
		Assert.assertEquals(testFloatingPointNumber, floatingPointNumber);
	}

	@Test
	public void convertToFloatingPointNumber_NumberIsNegative_ReturnsValidFloatingPointNumber()
	{
		// Arrange
		double number = -2.25;
		int exponentLength = 2;
		int mantissaLength = 4;

		FloatingPointNumber testFloatingPointNumber =
			new FloatingPointNumber(exponentLength, mantissaLength);

		testFloatingPointNumber.setExponent(Arrays.asList(true, false));
		testFloatingPointNumber.setMantissa(Arrays.asList(false, false, true,
			false));
		testFloatingPointNumber.setSign(true);

		// Act
		FloatingPointNumber floatingPointNumber =
			NumberUtils.convertToFloatingPointNumber(number, exponentLength,
				mantissaLength);

		// Assert
		Assert.assertEquals(testFloatingPointNumber, floatingPointNumber);
	}

	@Test
	public void convertToFloatingPointNumber_NumberIsPositive_ReturnsValidFloatingPointNumber()
	{
		// Arrange
		double number = 2.25;
		int exponentLength = 2;
		int mantissaLength = 4;

		FloatingPointNumber testFloatingPointNumber =
			new FloatingPointNumber(exponentLength, mantissaLength);

		testFloatingPointNumber.setExponent(Arrays.asList(true, false));
		testFloatingPointNumber.setMantissa(Arrays.asList(false, false, true,
			false));
		testFloatingPointNumber.setSign(false);

		// Act
		FloatingPointNumber floatingPointNumber =
			NumberUtils.convertToFloatingPointNumber(number, exponentLength,
				mantissaLength);

		// Assert
		Assert.assertEquals(testFloatingPointNumber, floatingPointNumber);
	}

	@Test
	public void convertToFloatingPointNumber_NumberMantissaDoesNotFitInMantissaLength_CutsExtraBits()
	{
		// Arrange
		double number = 0.3;
		int exponentLength = 4;
		int mantissaLength = 4;

		FloatingPointNumber testFloatingPointNumber =
			new FloatingPointNumber(exponentLength, mantissaLength);

		testFloatingPointNumber.setExponent(Arrays.asList(false, true, false,
			true));
		testFloatingPointNumber.setMantissa(Arrays.asList(false, false, true,
			true, false, false, true));
		testFloatingPointNumber.setSign(false);

		// Act
		FloatingPointNumber floatingPointNumber =
			NumberUtils.convertToFloatingPointNumber(number, exponentLength,
				mantissaLength);

		// Assert
		Assert.assertEquals(testFloatingPointNumber, floatingPointNumber);
	}

	@Test
	public void getFractionalPart_NumberFractionalPartIsEqualToZero_ReturnsZero()
	{
		// Arrange
		double testFractionalPart = 0.0;
		double number = 1 + testFractionalPart;

		// Act
		double fractionalPart = NumberUtils.getFractionalPart(number);

		// Assert
		Assert.assertEquals(testFractionalPart, fractionalPart, 1e-64);
	}

	@Test
	public void getFractionalPart_NumberIsNegative_ReturnsValidFractionalPart()
	{
		// Arrange
		double testFractionalPart = -0.9876;
		double number = -1 + testFractionalPart;

		// Act
		double fractionalPart = NumberUtils.getFractionalPart(number);

		// Assert
		Assert.assertEquals(testFractionalPart, fractionalPart, 1e-64);
	}

	@Test
	public void getFractionalPart_NumberIsPositive_ReturnsValidFractionalPart()
	{
		// Arrange
		double testFractionalPart = 0.9876;
		double number = 1 + testFractionalPart;

		// Act
		double fractionalPart = NumberUtils.getFractionalPart(number);

		// Assert
		Assert.assertEquals(testFractionalPart, fractionalPart, 1e-64);
	}

	@Test
	public void getFractionalPart_NumberIsZero_ReturnsZero()
	{
		// Arrange
		double testFractionalPart = 0.0;
		double number = 0.0;

		// Act
		double fractionalPart = NumberUtils.getFractionalPart(number);

		// Assert
		Assert.assertEquals(testFractionalPart, fractionalPart, 1e-64);
	}

	@Test
	public void getIntegerPart_NumberIsNegative_ReturnsValidIntegerPart()
	{
		// Arrange
		int testIntegerPart = -1;
		double number = testIntegerPart - 0.9876;

		// Act
		int integerPart = NumberUtils.getIntegerPart(number);

		// Assert
		Assert.assertEquals(testIntegerPart, integerPart);
	}

	@Test
	public void getIntegerPart_NumberIsNegativeAndGreaterThanMinusOne_ReturnsZero()
	{
		// Arrange
		int testIntegerPart = 0;
		double number = -0.9876;

		// Act
		int integerPart = NumberUtils.getIntegerPart(number);

		// Assert
		Assert.assertEquals(testIntegerPart, integerPart);
	}

	@Test
	public void getIntegerPart_NumberIsPositive_ReturnsValidIntegerPart()
	{
		// Arrange
		int testIntegerPart = 1;
		double number = testIntegerPart + 0.9876;

		// Act
		int integerPart = NumberUtils.getIntegerPart(number);

		// Assert
		Assert.assertEquals(testIntegerPart, integerPart);
	}

	@Test
	public void getIntegerPart_NumberIsPositiveAndLessThanOne_ReturnsZero()
	{
		// Arrange
		int testIntegerPart = 0;
		double number = 0.9876;

		// Act
		int integerPart = NumberUtils.getIntegerPart(number);

		// Assert
		Assert.assertEquals(testIntegerPart, integerPart);
	}
}
