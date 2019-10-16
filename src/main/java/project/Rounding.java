package project;

import com.google.common.math.DoubleMath;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class Rounding {

    public static final List<? extends Number> NUMBERS = Arrays.asList(
            1.2345, 2.3456, 3.4567, 4.5678,
            1.234, 2.345, 3.456, 4.567,
            1.23, 2.35, 3.45, 4.56,
            1.2, 2.3, 3.4, 4.5,
            100.15, 100.0, 105.0, 100, 103);

    public static final List<String> ROUNDINGS = Arrays.asList(
            "1.00", "0.01", "0.10", "5", "1.0", "100", "10");


    protected static String getFormattedRate(double converted, String fareRoundingUnit, String otherRoundingUnit, boolean isFare) {
        if (StringUtils.isNotBlank(fareRoundingUnit)&& StringUtils.isNotBlank(otherRoundingUnit)) {
            String format = isFare ? getFormat(otherRoundingUnit) : getFormat(otherRoundingUnit);
            DecimalFormat decimalFormat = new DecimalFormat(format, new DecimalFormatSymbols(Locale.UK));
            decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
            String format1 = decimalFormat.format(BigDecimal.valueOf(converted));

            return StringUtils.rightPad(format1, format.length(), "0");
        }
        return Double.toString(converted);
    }

    protected static String getFormat(String roundingUnit) {
        if (!roundingUnit.contains(".")) {
            return "#0";
        }//do poprawienia
        String fractpart = roundingUnit.substring(roundingUnit.indexOf(".") + 1);
        return "#0." + fractpart.replaceAll(".", "#");
    }

    public static void main(String[] args) {
        for (String rounding : ROUNDINGS) {
            System.out.println(getFormat(rounding));
        }

        System.out.println(getFormattedRate(123.45, "0.10", "0.10", false));
    }
}
