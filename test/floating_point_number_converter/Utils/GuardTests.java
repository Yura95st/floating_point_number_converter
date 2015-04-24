package floating_point_number_converter.Utils;

import org.junit.Test;

public class GuardTests
{
	@Test(expected = IllegalArgumentException.class)
	public void moreThanZero_NumberIsEqualToZero_ThrowsIllegalArgumentException()
	{
		// Arrange
		int number = 0;

		// Act & Assert
		Guard.moreThanZero(number, "number");
	}

	@Test(expected = IllegalArgumentException.class)
	public void moreThanZero_NumberIsLessThanZero_ThrowsIllegalArgumentException()
	{
		// Arrange
		int number = -1;

		// Act & Assert
		Guard.moreThanZero(number, "number");
	}

	@Test
	public void moreThanZero_NumberIsMoreThanZero_DoesNotThrowAnyException()
	{
		// Arrange
		int number = 1;

		// Act & Assert
		Guard.moreThanZero(number, "number");
	}

	@Test
	public void notNegative_NumberIsEqualToZero_DoesNotThrowAnyException()
	{
		// Arrange
		int number = 0;

		// Act & Assert
		Guard.notNegative(number, "number");
	}

	@Test(expected = IllegalArgumentException.class)
	public void notNegative_NumberIsLessThanZero_ThrowsIllegalArgumentException()
	{
		// Arrange
		int number = -1;

		// Act & Assert
		Guard.notNegative(number, "number");
	}

	@Test
	public void notNegative_NumberIsMoreThanZero_DoesNotThrowAnyException()
	{
		// Arrange
		int number = 1;

		// Act & Assert
		Guard.notNegative(number, "number");
	}

	@Test
	public void notNull_ObjectIsNotNull_DoesNotThrowAnyException()
	{
		// Arrange
		Object tempObject = new Object();

		// Act & Assert
		Guard.notNull(tempObject, "tempObject");
	}

	@Test(expected = IllegalArgumentException.class)
	public void notNull_ObjectIsNull_ThrowsIllegalArgumentException()
	{
		// Arrange
		Object tempObject = null;

		// Act & Assert
		Guard.notNull(tempObject, "tempObject");
	}

	@Test(expected = IllegalArgumentException.class)
	public void notNullOrEmpty_StringIsEmpty_DoesNotThrowAnyException()
	{
		// Arrange
		String tempString = "";

		// Act & Assert
		Guard.notNullOrEmpty(tempString, "tempString");
	}

	@Test(expected = IllegalArgumentException.class)
	public void notNullOrEmpty_StringIsNull_ThrowsIllegalArgumentException()
	{
		// Arrange
		String tempString = null;

		// Act & Assert
		Guard.notNullOrEmpty(tempString, "tempString");
	}

	@Test
	public void notNullOrEmpty_StringIsValid_DoesNotThrowAnyException()
	{
		// Arrange
		String tempString = "tempString";

		// Act & Assert
		Guard.notNullOrEmpty(tempString, "tempString");
	}

}
