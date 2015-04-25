package floating_point_number_converter.Models;

import java.util.ArrayList;
import java.util.List;

import floating_point_number_converter.Utils.Guard;

public class FloatingPointNumber
{
	private final List<Boolean> _exponent;

	private final int _exponentLength;

	private boolean _isNormalized;

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
		this._isNormalized = true;

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

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (obj == null)
		{
			return false;
		}
		if (this.getClass() != obj.getClass())
		{
			return false;
		}
		FloatingPointNumber other = (FloatingPointNumber) obj;
		if (this._exponent == null)
		{
			if (other._exponent != null)
			{
				return false;
			}
		}
		else if (!this._exponent.equals(other._exponent))
		{
			return false;
		}
		if (this._exponentLength != other._exponentLength)
		{
			return false;
		}
		if (this._mantissa == null)
		{
			if (other._mantissa != null)
			{
				return false;
			}
		}
		else if (!this._mantissa.equals(other._mantissa))
		{
			return false;
		}
		if (this._mantissaLength != other._mantissaLength)
		{
			return false;
		}
		if (this._sign != other._sign)
		{
			return false;
		}
		return true;
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

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result =
			prime * result
				+ ((this._exponent == null) ? 0 : this._exponent.hashCode());
		result = prime * result + this._exponentLength;
		result =
			prime * result
				+ ((this._mantissa == null) ? 0 : this._mantissa.hashCode());
		result = prime * result + this._mantissaLength;
		result = prime * result + (this._sign ? 1231 : 1237);
		return result;
	}

	public boolean isNormilized()
	{
		return this._isNormalized;
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

	public void setIsNormilized(boolean isNormalized)
	{
		this._isNormalized = isNormalized;
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
