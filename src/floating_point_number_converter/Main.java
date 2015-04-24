package floating_point_number_converter;

import java.util.List;
import java.util.Scanner;

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
			FloatingPointNumber floatingPointNumber = null;

			System.out.println("Please, enter the decimal number:");

			Scanner scanner = new Scanner(System.in);

			while (scanner.hasNextDouble())
			{
				double number = scanner.nextDouble();

				floatingPointNumber =
					NumberUtils.convertToFloatingPointNumber(number,
						exponentLength, mantissaLength);

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

	private static int getExponentValue(List<Boolean> exponent)
	{
		String exponentBinaryString = Main.getBinaryString(exponent);

		int exponentValue =
			Integer.parseInt(exponentBinaryString, 2)
				- ((int) Math.pow(2.0, exponent.size() - 1) - 1);

		return exponentValue;
	}

	private static double getMantissaValue(List<Boolean> mantissa)
	{
		double mantissaValue = 1.0;

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

	private static void printFloatingPointNumber(
		FloatingPointNumber floatingPointNumber)
	{
		boolean sign = floatingPointNumber.getSign();
		List<Boolean> exponent = floatingPointNumber.getExponent();
		List<Boolean> mantissa = floatingPointNumber.getMantissa();

		String exponentBinaryString = Main.getBinaryString(exponent);
		String mantissaBinaryString = Main.getBinaryString(mantissa);

		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append(String.format(
			"Value: [%1$s1] * [2^%2$s] * [%3$s]", Main.getSignValue(sign),
			Main.getExponentValue(exponent), Main.getMantissaValue(mantissa)));

		stringBuilder.append(System.lineSeparator());

		stringBuilder.append(String.format("Binary: [%1$s] * [%2$s] * [%3$s]",
			sign ? "1" : "0", exponentBinaryString, mantissaBinaryString));

		System.out.println(stringBuilder.toString());
	}
}