import java.math.BigInteger;

public class MathMethods extends java.lang.Object {

	public static void main(String[] args) {
		String chooseMethod = args[0];

		if (chooseMethod.equals("factorial") && Integer.parseInt(args[1]) >= 0) {
			System.out.println(factorial(Integer.parseInt(args[1])));
		} else if (chooseMethod.equals("fibonacci") && Integer.parseInt(args[1]) >= 0) {
			System.out.println(fibonacci(Integer.parseInt(args[1])));
		} else if (chooseMethod.equals("gcd")) {
			System.out.println(gcd(Long.parseLong(args[1]), Long.parseLong(args[2])));
		} else if (chooseMethod.equals("lcm")) {
			System.out.println(lcm(Long.parseLong(args[1]), Long.parseLong(args[2])));
		} else if (chooseMethod.equals("poly")) {
			double[] coefficients = new double[args.length - 1];
			for (int i = 2; i < args.length; i++) {
				coefficients[i - 2] = Double.parseDouble(args[i]);
			}
			System.out.println(poly(Double.parseDouble(args[1]), coefficients));
		} else if (chooseMethod.equals("sqrt") && Double.parseDouble(args[1]) >= 0) {
			System.out.println(sqrt(Double.parseDouble(args[1]), Double.parseDouble(args[2])));
		} else if (chooseMethod.equals("root") && Double.parseDouble(args[1]) >= 0) {
			System.out.println(root(Integer.parseInt(args[1]), Double.parseDouble(args[2]), Double.parseDouble(args[3])));
		} else if (chooseMethod.equals("power")) {
			System.out.println(power(Double.parseDouble(args[1]), Integer.parseInt(args[2])));
		} else {
			throw new UnsupportedOperationException();
		}
	}

	public static BigInteger factorial(int n) {
		BigInteger result = new BigInteger(Integer.toString(n));
		while (n > 1) {
			n--;
			result = result.multiply(BigInteger.valueOf(n));
		}
		if (result.equals("0")) {
			return new BigInteger("1");
		}
		return result;
	}

	public static BigInteger fibonacci(int n) {
		BigInteger temp = new BigInteger("0");
		BigInteger result = new BigInteger("0");
		BigInteger previousResult = new BigInteger("1");
		for (int i = 0; i < n; i++) {
			result = result.add(previousResult);
			previousResult = temp;
			temp = result;
		}
		return result;
	}

	public static long gcd(long x, long y) {
		return recurseGCD(Math.max(x, y), Math.min(x, y));
	}

	private static long recurseGCD(long max, long min) {
		long addition = max - (min * Math.floorDiv(max, min));

		if (addition > 0) {
			return recurseGCD(min, addition);
		}
		return min;
	}

	public static long lcm(long x, long y) {
		return (x * y) / gcd(x, y);
	}

	public static double poly(double x, double[] coeff) {
		double sum = 0;
		for (int i = coeff.length - 1; i > 0; i--) {
			sum = (sum + coeff[i]) * x;
		}
		return sum + coeff[0];
	}

	public static double sqrt(double x, double epsilon) {
		double guess = x / 2;
		while (guess * guess < x - epsilon || guess * guess > x + epsilon) {
			if (guess * guess > x - epsilon) {
				guess = guess / 2;
			} else if (guess * guess < x + epsilon) {
				guess = guess * 1.5;
			}
		}
		return guess;
	}

	public static double root(int n, double x, double epsilon) {
		if (n < 0) {
			return 0;
		}
		double guess = x / 2;
		while (power(guess, n) < x - epsilon || power(guess, n) > x + epsilon) {
			if (power(guess, n) > x - epsilon) {
				guess = guess / 2;
			} else if (power(guess, n) < x + epsilon) {
				guess = guess * 1.5;
			}
		}
		return guess;
	}

	public static double power(double x, int n) {
		if (n < 0) {
			return 1 / recursePower(x, Math.abs(n));
		} else {
			return recursePower(x, n);
		}
	}

	private static double recursePower(double x, int n) {
		double result = 1;
		if (n == 0) {
			return result;
		} else if (n % 2 == 1) {
			return x * power(x, (n - 1));
		} else {
			result = power(x, n / 2);
			return result * result;
		}
	}
}
