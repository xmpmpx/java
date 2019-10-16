package project;

import com.google.common.math.DoubleMath;

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

    public static final List<Double> ROUNDINGS = Arrays.asList(
            1.00, 0.01, 0.10, 5.0, 1.0, 100.0, 10.0);


    protected static String getFormattedRate(double converted, double fareRoundingUnit, double otherRoundingUnit, boolean isFare) {
        if (fareRoundingUnit != 0 && otherRoundingUnit != 0) {
            String format = isFare ? getFormat(fareRoundingUnit) : getFormat(otherRoundingUnit);
            DecimalFormat decimalFormat = new DecimalFormat(format, new DecimalFormatSymbols(Locale.UK));
            decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
            return decimalFormat.format(BigDecimal.valueOf(converted));
        }
        return Double.toString(converted);
    }

    protected static String getFormat(double roundingUnit) {
        if(DoubleMath.isMathematicalInteger(roundingUnit)) {
            return "#0";
        }
        String unit = Double.toString(roundingUnit);
        String fractpart = unit.substring(unit.indexOf(".")+1);
        return "#0." + fractpart.replaceAll(".", "#");
    }

    public static void main(String[] args) {

    }
}
