package csw.utility;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.springframework.util.StringUtils;



/**
 * General utils containing non specific methods.
 *
 * @author gabriel.schaidhauer
 */
public class GeneralUtils {
	
	/**
	 * Generates a random generic password based on a seed,
	 * containing a specific number or characters.
	 *
	 * @param seed
	 *            {@link Long}
	 * @param size
	 *            {@link Integer} being the length of the password.
	 * @return {@link String} with the generated password.
	 */
	public static String generateNumberPassword(final Long seed, final Integer size) {
		final Long timestamp = Calendar.getInstance().getTimeInMillis();
		final Random random = new Random(seed + timestamp);
		final StringBuilder passwordBuilder = new StringBuilder();
		
		for (int i = 0; i < size; i++) {
			passwordBuilder.append(random.nextInt(9));
			
		}
		
		return passwordBuilder.toString();
	}

	/**
	 * Verifies emptiness in any object.
	 *
	 * @param object
	 *            object to be validated.
	 * @return if the object is null or not.
	 */
	public static Boolean isEmpty(final Object object) {

		if (Objects.isNull(object)) { // Check if the object is null.
			return Boolean.TRUE;
		} else if (object instanceof Collection) {

			/*
			 * Case the object is a collection,
			 * verifies if it is empty.
			 */
			return ((Collection) object).isEmpty();
		} else if (object instanceof Map) {

			/*
			 * Case the object is a map, check if the map
			 * is empty.
			 */
			return ((Map) object).isEmpty();
		} else if (object instanceof Number) {

			/*
			 * If the object is a number, check if it has a value.
			 */
			final Number n = (Number) object;
			return ((n.byteValue() == 0) && (n.doubleValue() == 0.0) && (n.floatValue() == 0F) && (n.intValue() == 0)
					&& (n.longValue() == 0L) && (n.shortValue() == 0));
		} else if (object instanceof CharSequence) {

			/*
			 * If the object is a charsequence verify it`s emptyness based on
			 * apache StringUtils.
			 */
			return StringUtils.isEmpty(object.toString());
		} else if (object.getClass().isArray()) {

			/*
			 * If the object is a array, it verifies if
			 * it has at least one item on the array, and this
			 * item is not empty.
			 */
			for (int i = 0; i < Array.getLength(object); i++) {
				if (!isEmpty(Array.get(object, i))) {
					return Boolean.FALSE;
				}
			}
			return Boolean.TRUE;
		} else if (object instanceof Character) {

			/*
			 * If the object is a char, it will be empty when
			 * i`ts value is '\0'.
			 */
			return (((Character) object).equals('\0'));
		} else if (object.getClass().isEnum()) {

			/*
			 * If the object is a enum, it will be presumed
			 * filled.
			 */
			return Boolean.FALSE;
		} else if (object instanceof Boolean) {

			/*
			 * If it is boolean, it is presumed
			 * filled.
			 */
			return Boolean.FALSE;
		} else {

			/*
			 * If it`s type is not treated before,
			 * it will verify the emptiness through reflection.
			 */
			Class<?> clazz = object.getClass();

			/*
			 * if the object inherits it`s behavior from another,
			 * a loop is made recursively checking the emptiness, for each
			 * super class. It is made because the reflection API do not
			 * return the super attributes.
			 */
			while (clazz.getSuperclass() != null) {
				final Field[] fields = clazz.getDeclaredFields();

				/*
				 * Get every field from class ad check it`s emptiness.
				 */
				for (final Field field : fields) {
					field.setAccessible(Boolean.TRUE);
					try {
						if (!isEmpty(field.get(object))) {
							return Boolean.FALSE;
						}
					} catch (IllegalArgumentException | IllegalAccessException | StackOverflowError e) {
						return Boolean.FALSE;
					}
				}

				clazz = clazz.getSuperclass();
			}

			/*
			 * Case every verification prove that the object is empty,
			 * it return True.
			 */
			return Boolean.TRUE;
		}
	}

	/**
	 * Returns the value for a percentual of a value.
	 *
	 * @param percentual
	 *            percentual to calculate.
	 * @param value
	 *            base value for calculation
	 * @return percentual value.
	 */
	public static final BigDecimal percentualOf(final Double percentual, final BigDecimal value) {

		final BigDecimal multiplier = BigDecimal.valueOf(percentual / 100);
		final BigDecimal percentualValue = value.multiply(multiplier);

		return percentualValue;
	}

	/***
	 * Converts a date into a XMLGregorianCalendar object.
	 *
	 * @param from
	 *            {@link Date}
	 * @return {@link XMLGregorianCalendar}
	 * @throws DatatypeConfigurationException
	 */
	public static final XMLGregorianCalendar toXMLGregorianCalendar(final Date from)
			throws DatatypeConfigurationException {
		final GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(from);
		return DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);

	}
	
	public static BigDecimal totalizaValoresPagos() {
		//Totaliza os valores pagos nas compras do dia na hora do fechamento
		return null;
	}
	
}
