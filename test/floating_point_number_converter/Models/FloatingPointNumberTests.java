package floating_point_number_converter.Models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FloatingPointNumberTests
{
	private FloatingPointNumber _floatingPointNumber;

	@Test
	public void constructor_ExponentLengthAndMantissaLengthAreValid_CreatesTheNumber()
	{
		// Arrange
		int exponentLength = 4;
		int mantissaLength = 8;

		// Act
		this._floatingPointNumber =
			new FloatingPointNumber(exponentLength, mantissaLength);

		// Assert
		Assert.assertEquals(exponentLength,
			this._floatingPointNumber.getExponentLength());
		Assert.assertEquals(mantissaLength,
			this._floatingPointNumber.getMantissaLength());

		Assert.assertEquals(false, this._floatingPointNumber.getSign());

		List<Boolean> exponent = this._floatingPointNumber.getExponent();
		List<Boolean> mantissa = this._floatingPointNumber.getMantissa();

		Assert.assertEquals(exponentLength, exponent.size());
		Assert.assertEquals(mantissaLength, mantissa.size());

		for (Boolean bitValue : exponent)
		{
			Assert.assertEquals(false, bitValue);
		}

		for (Boolean bitValue : mantissa)
		{
			Assert.assertEquals(false, bitValue);
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void constructor_ExponentLengthIsEqualToZero_ThrowsIllegalArgumentException()
		throws Exception
	{
		// Arrange
		int mantissaLength = 1;

		// Act & Assert
		this._floatingPointNumber = new FloatingPointNumber(0, mantissaLength);
	}

	@Test(expected = IllegalArgumentException.class)
	public void constructor_ExponentLengthIsLessThanZero_ThrowsIllegalArgumentException()
		throws Exception
	{
		// Arrange
		int mantissaLength = 1;

		// Act & Assert
		this._floatingPointNumber = new FloatingPointNumber(-1, mantissaLength);
	}

	@Test(expected = IllegalArgumentException.class)
	public void constructor_MantissaLengthIsEqualToZero_ThrowsIllegalArgumentException()
		throws Exception
	{
		// Arrange
		int exponentLength = 1;

		// Act & Assert
		this._floatingPointNumber = new FloatingPointNumber(exponentLength, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void constructor_MantissaLengthIsLessThanZero_ThrowsIllegalArgumentException()
		throws Exception
	{
		// Arrange
		int exponentLength = 1;

		// Act & Assert
		this._floatingPointNumber = new FloatingPointNumber(exponentLength, -1);
	}

	@Test
	public void setExponent_ExponentListIsEmpty_SetsAllBitsToFalse()
		throws Exception
	{
		// Act
		this._floatingPointNumber.setExponent(new ArrayList<Boolean>());

		// Assert
		List<Boolean> exponent = this._floatingPointNumber.getExponent();

		Assert.assertEquals(this._floatingPointNumber.getExponentLength(),
			exponent.size());

		for (Boolean bitValue : exponent)
		{
			Assert.assertEquals(false, bitValue);
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void setExponent_ExponentListIsNull_ThrowsIllegalArgumentException()
		throws Exception
	{
		// Act & Assert
		this._floatingPointNumber.setExponent(null);
	}

	@Test
	public void setExponent_ExponentListSizeIsGreaterThanExponentLenght_CutsExtraBits()
		throws Exception
	{
		// Arrange
		List<Boolean> testExponent =
			Arrays.asList(true, false, true, false, true);

		// Act
		this._floatingPointNumber.setExponent(testExponent);

		// Assert
		List<Boolean> exponent = this._floatingPointNumber.getExponent();

		Assert.assertEquals(this._floatingPointNumber.getExponentLength(),
			exponent.size());

		for (int i = 0; i < exponent.size(); i++)
		{
			Assert.assertEquals(testExponent.get(i), exponent.get(i));
		}
	}

	@Test
	public void setExponent_ExponentListSizeIsLessThanExponentLenght_SetsMissingBitsToFalse()
		throws Exception
	{
		// Arrange
		List<Boolean> testExponent = Arrays.asList(false, true);

		// Act
		this._floatingPointNumber.setExponent(testExponent);

		// Assert
		List<Boolean> exponent = this._floatingPointNumber.getExponent();

		Assert.assertEquals(this._floatingPointNumber.getExponentLength(),
			exponent.size());

		for (int i = 0; i < exponent.size(); i++)
		{
			boolean bitValue =
				(i < testExponent.size()) ? testExponent.get(i) : false;

			Assert.assertEquals(bitValue, exponent.get(i));
		}
	}

	@Test
	public void setMantissa_MantissaListIsEmpty_SetsAllBitsToFalse()
		throws Exception
	{
		// Act
		this._floatingPointNumber.setMantissa(new ArrayList<Boolean>());

		// Assert
		List<Boolean> mantissa = this._floatingPointNumber.getMantissa();

		Assert.assertEquals(this._floatingPointNumber.getMantissaLength(),
			mantissa.size());

		for (Boolean bitValue : mantissa)
		{
			Assert.assertEquals(false, bitValue);
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void setMantissa_MantissaListIsNull_ThrowsIllegalArgumentException()
		throws Exception
	{
		// Act & Assert
		this._floatingPointNumber.setMantissa(null);
	}

	@Test
	public void setMantissa_MantissaListSizeIsGreaterThanMantissaLenght_CutsExtraBits()
		throws Exception
	{
		// Arrange
		List<Boolean> testMantissa =
			Arrays.asList(true, false, true, false, true);

		// Act
		this._floatingPointNumber.setMantissa(testMantissa);

		// Assert
		List<Boolean> mantissa = this._floatingPointNumber.getMantissa();

		Assert.assertEquals(this._floatingPointNumber.getMantissaLength(),
			mantissa.size());

		for (int i = 0; i < mantissa.size(); i++)
		{
			Assert.assertEquals(testMantissa.get(i), mantissa.get(i));
		}
	}

	@Test
	public void setMantissa_MantissaListSizeIsLessThanMantissaLenght_SetsMissingBitsToFalse()
		throws Exception
	{
		// Arrange
		List<Boolean> testMantissa = Arrays.asList(false, true);

		// Act
		this._floatingPointNumber.setMantissa(testMantissa);

		// Assert
		List<Boolean> mantissa = this._floatingPointNumber.getMantissa();

		Assert.assertEquals(this._floatingPointNumber.getMantissaLength(),
			mantissa.size());

		for (int i = 0; i < mantissa.size(); i++)
		{
			boolean bitValue =
				(i < testMantissa.size()) ? testMantissa.get(i) : false;

			Assert.assertEquals(bitValue, mantissa.get(i));
		}
	}

	@Before
	public void setUp() throws Exception
	{
		this._floatingPointNumber = new FloatingPointNumber(4, 4);
	}
}
