class MoreInfo
{
	private String interpolation = "\n\tInterpolation is a process which requires estimating the value(s) of a function for arguments between known values, which are either in form of table of values or a set of measurement which actually represent a set points along a continuous function. If the unknown value lies within the range of known values, it is known as interpolation and when it lies outside this range then it is known as extrapolation. \n\tSuppose we are given the following value of y=f(x) for a set values of x:\nX: x0 x1 x2...xn \nY: y0 y1 y2...yn\nThen the process of finding the value of y corresponding to any value of x=xi between x0 and xn is called interpolation. \n\tIn numerical analysis, polynomial interpolation is the interpolation of a given data set by a polynomial: given some points, find a polynomial which goes exactly through these points. \n\n\nSources: https://en.wikipedia.org/wiki/Polynomial_interpolation \nhttps://www.mathworks.com/matlabcentral/fileexchange/42741-gaussian-forward-interpolation-formula";
	private String newtonmethod = "\n\tOut of the many techniques of interpolation, Newton's Forward and Backward Interpolation are two very widely used formulas. Both of Newton’s formulas are based on finite difference calculus. These formulas are very often used in engineering and related science fields. \n\n\nSource: http://www.codewithc.com/c-program-for-newton-forward-interpolation/";
	private String gaussmethod = "\n\tThe advantage of Gauss' interpolation formulas consists in the fact that this selection of interpolation nodes ensures the best approximation of the residual term of all possible choices, while the ordering of the nodes by their distances from the interpolation point reduces the numerical error in the interpolation.\n\n\nSource: https://www.encyclopediaofmath.org/index.php/Gauss_interpolation_formula";
	private String lagrangemethod = "\n\tIn numerical analysis, Lagrange polynomials are used for polynomial interpolation. Although named after Joseph Louis Lagrange, who published it in 1795, it was first discovered in 1779 by Edward Waring and it is also an easy consequence of a formula published in 1783 by Leonhard Euler. \n\n\nSource: https://en.wikipedia.org/wiki/Lagrange_polynomial";
	private String divideddifferences = "\n\tA Newton polynomial, named after its inventor Isaac Newton, is the interpolation polynomial for a given set of data points in the Newton form. The Newton polynomial is sometimes called Newton's divided differences interpolation polynomial because the coefficients of the polynomial are calculated using divided differences. \n\n\nSource: https://en.wikipedia.org/wiki/Newton_polynomial";
	private String cubicspline = "\n\tSpline interpolation is a form of interpolation where the interpolant is a special type of piecewise polynomial called a spline. Spline interpolation is often preferred over polynomial interpolation because the interpolation error can be made small even when using low degree polynomials for the spline. Spline interpolation avoids the problem of Runge's phenomenon, in which oscillation can occur between points when interpolating using high degree polynomials. \n\n\nSource: https://en.wikipedia.org/wiki/Spline_interpolation";
	public String getInterpolation()
	{
		return interpolation;
	}
	public String getNewton()
	{
		return newtonmethod;
	}
	public String getGauss()
	{
		return gaussmethod;
	}
	public String getLagrange()
	{
		return lagrangemethod;
	}
	public String getDivided()
	{
		return divideddifferences;
	}
	public String getCubic()
	{
		return cubicspline;
	}

}