package org.libex.base;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.ThreadSafe;

import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.common.base.Predicate;

/**
 * Utility method on Object instances.
 * 
 * @author John Butler
 * 
 */
@ThreadSafe
@ParametersAreNonnullByDefault
public final class ObjectsEx {

	/**
	 * Converts object instances to String using the object's {@code toString()}
	 * method. If null is passed, null is returned.
	 */
	@Nonnull
	public static final Function<Object, String> toStringFunction() {
		return toString;
	}

	private static final Function<Object, String> toString = new Function<Object, String>() {

		@Override
		@Nullable
		public String apply(@Nullable Object arg0) {
			return ObjectsEx.toString(arg0);
		}
	};

	/**
	 * Returns the result of calling {@code toString} on the passed instance.
	 * 
	 * @param object
	 *            the Object on which to call toString
	 * @return the result of calling {@code toString} on the passed instance,
	 *         null if null is passed
	 */
	@Nullable
	public static String toString(@Nullable Object object) {
		return (object == null) ? null : object.toString();
	}

	/**
	 * Creates a Function that returns the result of calling {@code toString} on
	 * the passed instance or the {@code defaultValue} if {@code object} is
	 * null.
	 * 
	 * @param defaultValue
	 *            the value to return if object passed to the function is null
	 * @see #toStringOrDefault(String)
	 */
	@Nonnull
	public static Function<Object, String> toStringOrDefault(
			@Nonnull final String defaultValue) {
		checkNotNull(defaultValue);

		return new Function<Object, String>() {

			@Override
			@Nonnull
			public String apply(@Nullable Object arg0) {
				return ObjectsEx.toString(arg0, defaultValue);
			}
		};
	}

	/**
	 * Returns the result of calling {@code toString} on the passed instance or
	 * the {@code defaultValue} if {@code object} is null.
	 * 
	 * @param object
	 *            the Object on which to call toString
	 * @param defaultValue
	 *            the value to return if object is null
	 * @return the result of calling {@code toString} on the passed instance,
	 *         {@code defaultValue} if null is passed
	 */
	@Nonnull
	public static String toString(@Nullable Object object,
			@Nonnull String defaultValue) {
		checkNotNull(defaultValue);

		return (object == null) ? defaultValue : object.toString();
	}

	/**
	 * Returns the first argument if it is not null otherwise returns the second
	 * argument. This method is different from {@link Objects.firstNonnull()} in
	 * that it may return null if both arguments are null.
	 * 
	 * @param first
	 * @param second
	 * @return the first argument if it is not null otherwise returns the second
	 *         argument
	 */
	@Nullable
	public static <T> T firstNotNull(@Nullable T first, @Nullable T second) {
		return (first != null) ? first : second;
	}

	public static <T> Predicate<T> equalsPredicate(@Nullable final T value) {
		return new Predicate<T>() {

			@Override
			public boolean apply(@Nullable T input) {
				return Objects.equal(value, input);
			}
		};
	}

	private ObjectsEx() {
	}
}
