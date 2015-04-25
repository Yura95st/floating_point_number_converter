package floating_point_number_converter.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import floating_point_number_converter.Models.FloatingPointNumber;

public class NumberUtils
{
	/**
	 * Convert absolute fractional part of the specified number to it's binary
	 * representation.
	 *
	 * @param number
	 *            the number
	 * @param bitsLength
	 *            the bits length
	 * @return the list
	 */
	public static List<Boolean> convertFractionalPartToBinaryNumber(
		double number, int bitsLength)
		{
		Guard.moreThanZero(bitsLength, "bitsLength");

		double fractionalPart = Math.abs(NumberUtils.getFractionalPart(number));

		List<Boolean> binaryNumber = new ArrayList<Boolean>();

		for (int i = 0; i < bitsLength; i++)
		{
			fractionalPart *= 2;

			boolean bitValue = (fractionalPart >= 1);

			binaryNumber.add(bitValue);

			fractionalPart = NumberUtils.getFractionalPart(fractionalPart);
		}

		return binaryNumber;
		}

	/**
	 * Convert the specified number to it's binary representation.
	 *
	 * @param number
	 *            the number
	 * @param bitsLength
	 *            the bits' length
	 * @return the list
	 */
	public static List<Boolean> convertToBinaryNumber(int number, int bitsLength)
	{
		Guard.notNegative(number, "number");
		Guard.moreThanZero(bitsLength, "bitsLength");

		List<Boolean> binaryNumber = new ArrayList<Boolean>();

		while (number != 0)
		{
			boolean bitValue = (number % 2 != 0);

			binaryNumber.add(bitValue);

			number /= 2;
		}

		for (int i = binaryNumber.size(); i < bitsLength; i++)
		{
			binaryNumber.add(false);
		}

		Collections.reverse(binaryNumber);

		binaryNumber = binaryNumber.subList(0, bitsLength);

		return binaryNumber;
	}

	/**
	 * Convert specified number to it's floating point representation according
	 * to the IEEE 754 standard.
	 *
	 * @param number
	 *            the number
	 * @param exponentLength
	 *            the exponent length
	 * @param mantissaLength
	 *            the mantissa length
	 * @return the floating point number
	 */
	public static FloatingPointNumber convertToFloatingPointNumber(
		double number, int exponentLength, int mantissaLength)
	{
		Guard.moreThanZero(exponentLength, "exponentLength");
		Guard.moreThanZero(mantissaLength, "mantissaLength");

		FloatingPointNumber floatingPointNumber =
				new FloatingPointNumber(exponentLength, mantissaLength);

		boolean sign = number < 0;

		floatingPointNumber.setSign(sign);

		number = Math.abs(number);

		int exponent = (int) Math.pow(2.0, exponentLength - 1) - 1;

		// Normalize number
		while (number >= 2 || (number > 0 && number < 1))
		{
			if (number >= 2)
			{
				number /= 2.0;
				exponent++;
			}
			else if (number < 1)
			{
				number *= 2.0;

				// Prevents overflowing
				if (exponent > 0)
				{
					exponent--;
				}
			}
		}

		boolean isNormalized = exponent != 0 || number == 0;

		floatingPointNumber.setIsNormilized(isNormalized);

		List<Boolean> exponentBinary =
				NumberUtils.convertToBinaryNumber(exponent, exponentLength);

		double fractionalPart = NumberUtils.getFractionalPart(number);

		List<Boolean> fractionalPartBinary =
				NumberUtils.convertFractionalPartToBinaryNumber(fractionalPart,
					mantissaLength);

		floatingPointNumber.setExponent(exponentBinary);
		floatingPointNumber.setMantissa(fractionalPartBinary);

		return floatingPointNumber;
	}

	/**
	 * Gets the fractional part of the specified number.
	 *
	 * @param number
	 *            the number
	 * @return the fractional part
	 */
	public static double getFractionalPart(double number)
	{
		double fractionalPart = number - NumberUtils.getIntegerPart(number);

		return fractionalPart;
	}

	/**
	 * Gets the integer part of the specified number.
	 *
	 * @param number
	 *            the number
	 * @return the integer part
	 */
	public static int getIntegerPart(double number)
	{
		int integerPart = (int) number;

		return integerPart;
	}
}
