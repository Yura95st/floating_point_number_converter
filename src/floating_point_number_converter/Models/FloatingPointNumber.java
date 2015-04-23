package floating_point_number_converter.Models;

import java.util.ArrayList;
import java.util.List;

import floating_point_number_converter.Utils.Guard;

public class FloatingPointNumber
{
	private final List<Boolean> _exponent;

	private final int _exponentLength;

	private final List<Boolean> _mantissa;

	private final int _mantissaLength;

	private boolean _sign;

	public FloatingPointNumber(int exponentLength, int mantissaLength)
	{
		Guard.moreThanZero(exponentLength, "exponentLength");
		Guard.moreThanZero(mantissaLength, "mantissaLength");

		this._exponentLength = exponentLength;
		this._mantissaLength = mantissaLength;

		this._sign = false;

		this._exponent = new ArrayList<Boolean>(this._exponentLength);

		for (int i = 0; i < this._exponentLength; i++)
		{
			this._exponent.add(false);
		}

		this._mantissa = new ArrayList<Boolean>(this._mantissaLength);

		for (int i = 0; i < this._mantissaLength; i++)
		{
			this._mantissa.add(false);
		}
	}

	public List<Boolean> getExponent()
	{
		return new ArrayList<Boolean>(this._exponent);
	}

	public int getExponentLength()
	{
		return this._exponentLength;
	}

	public List<Boolean> getMantissa()
	{
		return new ArrayList<Boolean>(this._mantissa);
	}

	public int getMantissaLength()
	{
		return this._mantissaLength;
	}

	public boolean getSign()
	{
		return this._sign;
	}

	public void setExponent(List<Boolean> exponent)
	{
		Guard.notNull(exponent, "exponent");

		this._exponent.clear();

		int count = exponent.size();

		for (int i = 0; i < this._exponentLength; i++)
		{
			boolean value = false;

			if (i < count)
			{
				value = exponent.get(i);
			}

			this._exponent.add(value);
		}
	}

	public void setMantissa(List<Boolean> mantissa)
	{
		Guard.notNull(mantissa, "mantissa");

		this._mantissa.clear();

		int count = mantissa.size();

		for (int i = 0; i < this._mantissaLength; i++)
		{
			boolean value = false;

			if (i < count)
			{
				value = mantissa.get(i);
			}

			this._mantissa.add(value);
		}
	}

	public void setSign(boolean sign)
	{
		this._sign = sign;
	}
}
