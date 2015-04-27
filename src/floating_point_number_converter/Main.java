package floating_point_number_converter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import floating_point_number_converter.Enums.SpecialFloatingPointNumberKind;
import floating_point_number_converter.Models.FloatingPointNumber;
import floating_point_number_converter.Utils.NumberUtils;

public class Main
{
	public static void main(String[] args)
	{
		int exponentLength = 8;
		int mantissaLength = 23;

		try
		{
			Main.printSpecialFloatingPointNumbers(exponentLength,
				mantissaLength);

			FloatingPointNumber floatingPointNumber = null;

			System.out.println("Please, enter the decimal number:");

			Scanner scanner = new Scanner(System.in);

			while (scanner.hasNextDouble())
			{
				double number = scanner.nextDouble();

				if (number == 0.0)
				{
					floatingPointNumber =
						Main.getSpecialFloatingPointNumber(
							SpecialFloatingPointNumberKind.ZeroPositive,
							exponentLength, mantissaLength);
				}
				else
				{
					floatingPointNumber =
						NumberUtils.convertToFloatingPointNumber(number,
							exponentLength, mantissaLength);
				}

				Main.printFloatingPointNumber(floatingPointNumber);
			}
		}
		catch (Exception exception)
		{
			System.err.println("Error occured:");
			System.err.println(exception);
		}
	}

	private static String getBinaryString(List<Boolean> binaryNumber)
	{
		StringBuilder stringBuilder = new StringBuilder();

		for (int i = 0, count = binaryNumber.size(); i < count; i++)
		{
			stringBuilder.append(binaryNumber.get(i) ? "1" : "0");
		}

		String string = stringBuilder.toString();

		return string;
	}

	private static int getExponentValue(FloatingPointNumber floatingPointNumber)
	{
		List<Boolean> exponent = floatingPointNumber.getExponent();

		String exponentBinaryString = Main.getBinaryString(exponent);

		int exponentValue =
			Integer.parseInt(exponentBinaryString, 2)
				- ((int) Math.pow(2.0, exponent.size() - 1) - 1);

		return exponentValue;
	}

	private static double getMantissaValue(
		FloatingPointNumber floatingPointNumber)
	{
		List<Boolean> mantissa = floatingPointNumber.getMantissa();

		double mantissaValue = floatingPointNumber.isNormilized() ? 1.0 : 0.0;

		for (int i = 0, count = mantissa.size(); i < count; i++)
		{
			if (mantissa.get(i))
			{
				mantissaValue += Math.pow(2.0, -i - 1);
			}
		}

		return mantissaValue;
	}

	private static String getSignValue(boolean sign)
	{
		String signValue = sign ? "-" : "+";

		return signValue;
	}

	private static FloatingPointNumber getSpecialFloatingPointNumber(
		SpecialFloatingPointNumberKind kind, int exponentLength,
		int mantissaLength)
	{
		FloatingPointNumber floatingPointNumber =
			new FloatingPointNumber(exponentLength, mantissaLength);

		boolean isNormalized = true;
		boolean sign = false;
		List<Boolean> exponent = new ArrayList<Boolean>();
		List<Boolean> mantissa = new ArrayList<Boolean>();

		switch (kind)
		{
			case InfinityNegative:
			{
				isNormalized = true;
				sign = true;

				for (int i = 0; i < exponentLength; i++)
				{
					exponent.add(true);
				}

				break;
			}

			case InfinityPositive:
			{
				isNormalized = true;
				sign = false;

				for (int i = 0; i < exponentLength; i++)
				{
					exponent.add(true);
				}

				break;
			}

			case MaxPositive:
			{
				isNormalized = true;
				sign = false;

				for (int i = 0; i < exponentLength; i++)
				{
					boolean exponentBit = (i != exponentLength - 1);

					exponent.add(exponentBit);
				}

				for (int i = 0; i < mantissaLength; i++)
				{
					mantissa.add(true);
				}

				break;
			}

			case MinNegative:
			{
				isNormalized = true;
				sign = true;

				for (int i = 0; i < exponentLength; i++)
				{
					boolean exponentBit = (i != exponentLength - 1);

					exponent.add(exponentBit);
				}

				for (int i = 0; i < mantissaLength; i++)
				{
					mantissa.add(true);
				}

				break;
			}

			case MinNotZero:
			{
				isNormalized = true;
				sign = false;

				for (int i = 0; i < mantissaLength; i++)
				{
					boolean mantissaBit = (i == mantissaLength - 1);

					mantissa.add(mantissaBit);
				}

				break;
			}

			case NaNNegative:
			{
				isNormalized = true;
				sign = true;

				for (int i = 0; i < exponentLength; i++)
				{
					exponent.add(true);
				}

				mantissa.add(true);

				break;
			}

			case NaNPositive:
			{
				isNormalized = true;
				sign = false;

				for (int i = 0; i < exponentLength; i++)
				{
					exponent.add(true);
				}

				mantissa.add(true);

				break;
			}

			case ZeroNegative:
			{
				isNormalized = false;
				sign = true;

				break;
			}

			case ZeroPositive:
			{
				isNormalized = false;
				sign = false;

				break;
			}
		}

		floatingPointNumber.setIsNormilized(isNormalized);
		;
		floatingPointNumber.setSign(sign);
		floatingPointNumber.setExponent(exponent);
		floatingPointNumber.setMantissa(mantissa);

		return floatingPointNumber;
	}

	private static void printFloatingPointNumber(
		FloatingPointNumber floatingPointNumber)
	{
		boolean sign = floatingPointNumber.getSign();

		String exponentBinaryString =
			Main.getBinaryString(floatingPointNumber.getExponent());
		String mantissaBinaryString =
			Main.getBinaryString(floatingPointNumber.getMantissa());

		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append(String.format(
			"Value: [%1$s1] * [2^%2$s] * [%3$s]", Main.getSignValue(sign),
			Main.getExponentValue(floatingPointNumber),
			Main.getMantissaValue(floatingPointNumber)));

		if (!floatingPointNumber.isNormilized())
		{
			stringBuilder.append(" (denormilized)");
		}

		stringBuilder.append(System.lineSeparator());

		stringBuilder.append(String.format("Binary: [%1$s] * [%2$s] * [%3$s]",
			sign ? "1" : "0", exponentBinaryString, mantissaBinaryString));

		System.out.println(stringBuilder.toString());
	}

	private static void printSpecialFloatingPointNumbers(int exponentLength,
		int mantissaLength)
	{
		for (SpecialFloatingPointNumberKind kind : SpecialFloatingPointNumberKind
				.values())
		{
			System.out.println(String.format("%1$s%2$s", kind.name(),
				System.lineSeparator()));

			Main.printFloatingPointNumber(Main.getSpecialFloatingPointNumber(
				kind, exponentLength, mantissaLength));

			System.out.println();
		}
	}
}