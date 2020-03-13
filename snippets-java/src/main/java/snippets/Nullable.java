package snippets;

import java.util.function.Supplier;

public class Nullable {

	public static <T> T get(Supplier<T> supplier, T defaultValue) {
		try {
			return supplier.get();
		} catch (NullPointerException e) {
			return defaultValue;
		}
	}

	public static <T> T get(Supplier<T> supplier) {
		return get(supplier, null);
	}

	public static void get(Runnable procedure) {
		get(procedure, null);
	}

	public static void get(Runnable runnable, Runnable exceptionAction) {
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
		Double n1D = Nullable.get(() -> n1.doubleValue());
		Double n2D = Nullable.get(() -> n2.doubleValue());
		return biSupplier(n1D, n2D, () -> n1D + n2D, defaultValue);
	}

	public static Double sub(Number n1, Number n2, Double defaultValue) {
		Double n1D = Nullable.get(() -> n1.doubleValue());
		Double n2D = Nullable.get(() -> n2.doubleValue());
		return biSupplier(n1D, n2D, () -> n1D - n2D, defaultValue);
	}

	public static <T> T biSupplier(T x, T y, Supplier<T> supplier) {
		return biSupplier(x, y, supplier, null);
	}

	public static <T> T biSupplier(T x, T y, Supplier<T> supplier, T defaultValue) {
		if (x == null && y == null)
			return defaultValue;
		if (x == null)
			return y;
		if (y == null)
			return x;
		try {
			return supplier.get();
		} catch (NullPointerException e) {
			return defaultValue;
		}
	}

}
