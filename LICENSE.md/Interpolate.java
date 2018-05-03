import java.lang.Math;
import java.util.*;
class Interpolate
{
	private float[][] xy;
	private int size;
	private float unknown;
	private double answer;
	private float[] SortedX;
	private float[] SortedY;
	public void setTable (float[][] xy, int size)
	{
		this.xy = xy;
		this.size = size;
		SortedX = new float[size];
		SortedY = new float[size];
	}
	public void displayTable()
	{
		System.out.println("\n    x \tf(x) ");
		for (int x = 0; x < size; x++)
		{	
			System.out.println("");
			System.out.printf((x+1) + ". ");
			for (int y = 0; y < 2; y++)
				System.out.printf(xy[x][y] + " \t ");
		}
	}
	public boolean checkTable()
	{
		boolean isValid = false;
		int repeatedXValues = 0;
		for (int i = 0; i < size; i++)
		{
			for (int j = i+1; j < size; j++)
			{
				if (xy[i][0] == xy[j][0])
					repeatedXValues++;
			}
		}
		if (repeatedXValues < size)
			isValid = true;
		return isValid;
	}
	public boolean isBetween(float number) 
	{
		float temp, ytemp;
		for (int i = 0; i < size; i++) //transfer x values into a new array
		{ 
			SortedX[i] = xy[i][0];
			SortedY[i] = xy[i][1];
		}
		for (int x = 0; x < size;  x++)
		{
			for (int y = x+1; y < size; y++)
			{
				if (SortedX[x] > SortedX[y])
				{
					temp = SortedX[y];
					ytemp = SortedY[y];
					SortedX[y] = SortedX[x];
					SortedY[y] = SortedY[x];
					SortedX[x] = temp;
					SortedY[x] = ytemp;
				}
			}
		}
		float a = SortedX[0]; //minimum
		float b = SortedX[size-1]; //maximum
    		return (b > a ? number > a && number < b : number > b && number < a);
	}
	public boolean checkUnknown(float number)
	{
		int valid = 0;
		if (isBetween(number) == true) // if unknown is within the range of known values
		{
			for (int i = 0; i < size; i++)
			{ //if unknown is not equal to any of the known values, valid++
				if (xy[i][0] != number)
					valid++;
			}
			if (valid == size)
			{//if no known value is equal to the unknown, return true
				this.unknown = number;
				return true;
			}
		}
		return false;
	}
	public boolean checkInterval()
	{
		float[] interval = new float[size-1];
		int unequal = 0;
		for (int i = 0; i < (size-1); i++) //getting the interval between known values
		{
			interval[i] = SortedX[i+1] - SortedX[i];
		}
		for (int j = 0; j < (size-1); j++)
		{
			for (int k = j+1; k < (size-1); k++)
				if (interval[j] != interval[k])
					unequal++;
		}
		if (unequal > 0)
			return false;
		return true;
	}
	public void resetValues()
	{
		answer = 0;
	}
	/***********************EQUAL INTERVALS***********************/
	public void NewtonBackward()
	{
		float s, p, h;
		float[] YTemp = new float[size];
		for (int i = 0; i < size; i++)
			YTemp[i] = SortedY[i];
		h = SortedX[2] - SortedX[1];
		s = (unknown - SortedX[size-1]) / h;
		answer = YTemp[size-1];
		p = 1;
		for(int i = size, k = 1; i >= 1 && k < size; i--, k++)
		{
			for(int j = size-1; j >= 1; j--)
               		{
				YTemp[j] = YTemp[j] - YTemp[j-1];
			}
			p = p * ((s+k-1)/k);
			answer = answer + (p*YTemp[size-1]);
		}
	}
	public void NewtonForward()
	{

		float s, p, h;
		float[] YTemp = new float[size];
		for (int i = 0; i < size; i++)
			YTemp[i] = SortedY[i];
		h = SortedX[1] - SortedX[0];
		s = (unknown-SortedX[1]) / h;
		p = 1;
		answer = YTemp[1];                                  
		for(int i = 1; i < (size-1); i++)
		{
			for(int j = 1; j < (size-i); j++)
			{
				YTemp[j] = YTemp[j+1]-YTemp[j];
			}
			p = p * (s-i+1) / i;
			answer = answer + (p*YTemp[1]);
		}
	}
	public void GaussBackward() 
	{
		float[][] YTemp = new float[size][size];
		float h, p, y1, y2, y3, y4;
		h = SortedX[2] - SortedX[1];
		for (int i = 0; i < (size-1); i++)
		{
			YTemp[i][1] = SortedY[i+1] - SortedY[i];
		}
		for (int j = 2; j < 5; j++)
		{
			for(int k = 0; k < (size-j); k++)
				YTemp[k][j] = YTemp[k+1][j-1] - YTemp[k][j-1];
		}
		int i = 0;
		do
		{
			i++;
		} while (SortedX[i] < unknown);
		i--;
		p = (unknown - SortedX[i]) / h;
		try
		{
			y1 = p*YTemp[i-1][1];
			y2 = (p*(p+1)*YTemp[i-1][2])/2; 
			y3 = ((p+1)*p*(p-1)*YTemp[i-2][3]) / 6; 
			y4 = ((p+2)*(p+1)*p*(p-1)*YTemp[i-3][4]) / 24; 
			answer = SortedY[i] + y1 + y2 + y3 + y4;
		} 
		catch (ArrayIndexOutOfBoundsException a)
		{
			answer = Float.NaN;
		}
	}
	public void GaussForward()
	{
		float[][] temp = new float[size][size];
		float h, p, y1, y2, y3, y4;
		h = SortedX[1] - SortedX[0];
		for (int i = 0; i < (size-1); i++)
		{
			temp[i][1] = SortedY[i+1] - SortedY[i];
		}
		for (int j = 2; j < 5; j++)
		{
			for(int k = 0; k < (size-j); k++)
				temp[k][j] = temp[k+1][j-1] - temp[k][j-1];
		}
		int i = 0;
		do
		{
			i++;
		} while(SortedX[i] < unknown);
		i--;
		p = (unknown - SortedX[i])/ h;
		try
		{
			y1 = p*temp[i][1];
			y2 = (p*(p-1)*temp[i-1][2])/2;
			y3 = (p*(p+1)*(p-1)*temp[i-2][3])/6;
			y4 = (p*(p-2)*(p+1)*(p-1)*temp[i-3][4])/24;
			answer = SortedY[i] + y1 + y2 + y3 + y4;
		} 
		catch (ArrayIndexOutOfBoundsException a)
		{
			answer = Float.NaN;
		}
			
	}
	public void CubicSpline()
	{
		double[] p = new double[size];
		double[] h = new double[size];
		double[] s = new double[size];
		double[][] matrix = new double[size][size];
		double y1, y2, y3, y4, temp;
		for (int i = (size-1); i > 0; i--)
		{
			p[i]=(SortedY[i]-SortedY[i-1])/(SortedX[i]-SortedX[i-1]);
			h[i-1] = SortedX[i]-SortedX[i-1];		
		}
		for (int i = 1; i < size-1; i++)
		{
			matrix[i][i] = 2*(h[i-1]+h[i]);
			if(i != 1)
			{
				matrix[i][i-1] = h[i-1];
				matrix[i-1][i] = h[i-1];
			}
			matrix[i][size-1]=6*(p[i+1]-p[i]);
		}
		for (int i = 1; i < (size-1); i++)
		{
			temp = (matrix[i+1][i] / matrix[i][i]);
			for (int j = 1; j <= (size-1); j++)
				matrix[i+1][j] = matrix[i+1][j] - (temp * matrix[i][j]);
		}
		for(int i = (size-2); i > 0; i--)
		{
			answer = 0;
			for (int j = i; j <= (size-2); j++)
				answer = answer + (matrix[i][j] * s[j]);
			s[i] = (matrix[i][size-1] - answer) / matrix[i][i];
		}
		for(int i = 0; i < (size-1); i++)
		{
			if(SortedX[i] <= unknown && unknown <= SortedX[i+1])
			{
				y1 = (s[i+1]-s[i]) / (6*h[i]);
				y2 = s[i]/2;
				y3 = ((SortedY[i+1] - SortedY[i]) / h[i]) - (((2*h[i]*s[i])+(s[i+1]*h[i])) / 6);
				y4 = SortedY[i];
				answer = (y1 * (Math.pow (unknown-SortedX[i], 3)) + y2 * (Math.pow (unknown-SortedX[i], 3)) + (y3 * (unknown-SortedX[i])) + y4);
  			}
		}
	}
	/***********************UNEQUAL INTERVALS***********************/
	public void LagrangeMethod()
	{
		float s, t;
		for(int i = 0; i < size; i++)
		{
			s = 1;
			t = 1;
			for (int j = 0; j < size; j++)
			{
				if(j != i)
				{
					s = s*(unknown-SortedX[j]);
					t = t*(SortedX[i] - SortedX[j]);
				}
			}
			answer = answer + ((s/t)*SortedY[i]);
		}
	}
	public void DividedDifferences()
	{
		float[][] matrix = new float[size][size];
		float u, temp;
		int k, h;
		for (int i = 0; i < size; i++)
		{
			matrix[0][i] = SortedY[i];
		}
		for (int i = 1; i < size; i++)
		{	
			k = i;
			for (int j = 0; j < (size-i); j++)
			{
				matrix[i][j] = (matrix[i-1][j+1]-matrix[i-1][j])/(SortedX[k]-SortedX[j]);
				k++;
			}
		}
		int i = 0;
		h = 0;
		for (int a = 0; a < size-1; a++)
		{
			if (SortedX[a] < unknown && SortedX[a+1] > unknown)
			{
				h = i;
				break;
			}
		}
		for (int a = 0; a < size-1; a++)
		{
			k = h;
			temp = 1;
			for (int j = 0; j < a; j++)
			{
				temp = temp * (unknown - SortedX[k]);
				k++;
			}
			answer = answer + (temp * matrix[a][h]);
		}
	}
	public void printAnswer()
	{
		if (Double.isNaN(answer) == true)
			System.out.println("\nSorry! Calculation cannot be made. Please try another method.");
		else
			System.out.println("\nWhen x = " + unknown + ", f(x) = " + answer);
	}
}