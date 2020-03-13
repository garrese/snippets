package snippets;

import java.util.function.Supplier;

public class Nullable {

	public static <T> T m(Supplier<T> supplier, T defaultValue) {
		try {
			return supplier.get();
		} catch (NullPointerException e) {
			return defaultValue;
		}
	}

	public static <T> T m(Supplier<T> supplier) {
		return m(supplier, null);
	}

	public static void m(Runnable procedure) {
		m(procedure, null);
	}

	public static void m(Runnable runnable, Runnable exceptionAction) {
		try {
			runnable.run();
		} catch (NullPointerException e) {
			if (exceptionAction != null)
				exceptionAction.run();
		}
	}

	public static Double sum(Number n1, Number n2) {
		return sum(n1, n2, null);
	}

	public static Double sub(Number n1, Number n2) {
		return sub(n1, n2, null);
	}

	public static Double sum(Number n1, Number n2, Double defaultValue) {
		Double n1D = Nullable.m(() -> n1.doubleValue());
		Double n2D = Nullable.m(() -> n2.doubleValue());
		return supplier(n1D, n2D, () -> n1D + n2D, defaultValue);
	}

	public static Double sub(Number n1, Number n2, Double defaultValue) {
		Double n1D = Nullable.m(() -> n1.doubleValue());
		Double n2D = Nullable.m(() -> n2.doubleValue());
		return supplier(n1D, n2D, () -> n1D - n2D, defaultValue);
	}

	public static <T> T supplier(T sum1, T sum2, Supplier<T> supplier) {
		return supplier(sum1, sum2, supplier, null);
	}

	public static <T> T supplier(T sum1, T sum2, Supplier<T> supplier, T defaultValue) {
		if (sum1 == null && sum2 == null)
			return defaultValue;
		if (sum1 == null)
			return sum2;
		if (sum2 == null)
			return sum1;
		try {
			return supplier.get();
		} catch (NullPointerException e) {
			return defaultValue;
		}
	}

}
