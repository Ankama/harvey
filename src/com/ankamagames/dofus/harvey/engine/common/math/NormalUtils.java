/**
 *
 */
package com.ankamagames.dofus.harvey.engine.common.math;

import org.eclipse.jdt.annotation.NonNullByDefault;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public class NormalUtils
{
	private static final double LOG_MAX = 7.09782712893383996732E2;
//	private static final double HALF_ERF = 0.4769362762044697;

	public static double erfc(final double x)
	{
		double a, b, c, m, n;

		final double P[] = {
			2.46196981473530512524E-10, 5.64189564831068821977E-1, 7.46321056442269912687E0,
			4.86371970985681366614E1, 1.96520832956077098242E2, 5.26445194995477358631E2,
			9.34528527171957607540E2, 1.02755188689515710272E3, 5.57535335369399327526E2
			};
		final double Q[] = {
									  1.32281951154744992508E1, 8.67072140885989742329E1,
			3.54937778887819891062E2, 9.75708501743205489753E2, 1.82390916687909736289E3,
			2.24633760818710981792E3, 1.65666309194161350182E3, 5.57535340817727675546E2
			};

		final double R[] = {
			5.64189583547755073984E-1, 1.27536670759978104416E0, 5.01905042251180477414E0,
			6.16021097993053585195E0, 7.40974269950448939160E0, 2.97886665372100240670E0
			};
		final double S[] = {
									  2.26052863220117276590E0, 9.39603524938001434673E0,
			1.20489539808096656605E1, 1.70814450747565897222E1, 9.60896809063285878198E0,
			3.36907645100081516050E0
			};

		if(x < 0.0)
			a = -x;
		else
			a = x;

		if(a < 1.0)
			return 1.0 - erf(x);

		c = -x * x;

		if(c < -LOG_MAX)
		{
			if(x < 0)
				return (2.0);
			else
				return (0.0);
		}

		c = Math.exp(c);

		if(a < 8.0)
		{
			m = polyEval(a, P, 8);
			n = polyEvalAssuming1(a, Q, 8);
		}
		else
		{
			m = polyEval(a, R, 5);
			n = polyEvalAssuming1(a, S, 6);
		}

		b = (c * m) / n;

		if(x < 0)
			b = 2.0 - b;

		if(b == 0.0)
		{
			if(x < 0)
				return 2.0;
			else
				return (0.0);
		}

		return b;
	}

	public static double erf(final double x)
	{
		double y, z;
		final double T[] = {9.60497373987051638749E0, 9.00260197203842689217E1, 2.23200534594684319226E3, 7.00332514112805075473E3, 5.55923013010394962768E4};
		final double U[] = {3.35617141647503099647E1, 5.21357949780152679795E2, 4.59432382970980127987E3, 2.26290000613890934246E4, 4.92673942608635921086E4};

		if(Math.abs(x) > 1.0)
			return (1.0 - erfc(x));
		z = x * x;
		y = x * polyEval(z, T, 4) / polyEvalAssuming1(z, U, 5);
		return y;
	}

    public static double erf(final double x1, final double x2)
	{
    	return erf(x2) - erf(x1);
//		if(x1 > x2)
//			return -erf(x2, x1);
//
//		return ((x1 < -HALF_ERF) ? ((x2 < 0.0) ? erfc(-x2) - erfc(-x1) : erf(x2) - erf(x1)) : ((x2 > HALF_ERF && x1 > 0.0) ? erfc(x1) - erfc(x2) : erf(x2) - erf(x1)));
    }

    private static double polyEval(final double x, final double coefs[], final int degree)
	{
		double ans;

		ans = coefs[0];

		for(int i = 1 ; i <= degree ; i++)
			ans = ans * x + coefs[i];

		return ans;
	}

    private static double polyEvalAssuming1(final double x, final double coefs[], final int degree)
	{
		double ans;

		ans = x + coefs[0];

		for(int i = 1 ; i < degree ; i++)
			ans = ans * x + coefs[i];

		return ans;
	}
}