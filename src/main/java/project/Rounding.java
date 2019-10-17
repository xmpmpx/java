package project;

import com.openjaw.utils.MathUtilities;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

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
        if (StringUtils.isNotBlank(fareRoundingUnit) && StringUtils.isNotBlank(otherRoundingUnit)) {
            String unit = isFare ? fareRoundingUnit : otherRoundingUnit;
            String rounded = MathUtilities.roundUp(converted, unit);
            return formatDecimal(rounded, unit);
        }
        return Double.toString(converted);
    }

    private static String formatDecimal(String rounded, String unit) {
        int unitFraction = unit.contains(".") ? unit.substring(unit.indexOf(".") + 1).length() : 0;
        int roundedFraction = rounded.contains(".") ? rounded.substring(rounded.indexOf(".") + 1).length() : 0;
        int trailingZerosNum = unitFraction - roundedFraction;
        String dot = trailingZerosNum > 0 && roundedFraction == 0 ? "." : "";
        return rounded + dot + StringUtils.repeat("0", trailingZerosNum);
    }

    protected static FormatInfo getFormatInfo(String roundingUnit) {
        FormatInfo fi = new FormatInfo();
        fi.length = roundingUnit.length();
        if (!roundingUnit.contains(".")) {
            fi.isWholeNum = true;
            fi.intpart = roundingUnit;
            fi.fractpart = null;
            fi.decimal = 0;
        } else {
            fi.intpart = roundingUnit.substring(0, roundingUnit.indexOf("."));
            String fractpart = roundingUnit.substring(roundingUnit.indexOf(".") + 1);
            fi.decimal = fractpart.length();
            if (fractpart.matches("0+")) {
                fi.isWholeNum = true;
                fi.fractpart = fractpart;
            } else {
                fi.isWholeNum = false;
                fi.fractpart = fractpart.replaceFirst("0+$", "");
            }
        }
        System.out.println(roundingUnit + " | " + fi);
        return fi;
    }

   static class FormatInfo {
        boolean isWholeNum;
        int decimal;
        int length;
        String intpart;
        String fractpart;

       @Override
       public String toString() {
           return "FormatInfo{" +
                   "isWholeNum=" + isWholeNum +
                   ", decimal=" + decimal +
                   ", length=" + length +
                   ", intpart='" + intpart + '\'' +
                   ", fractpart='" + fractpart + '\'' +
                   '}';
       }
   }

    public static void main(String[] args) {
        for (String rounding : ROUNDINGS) {
            //getFormat(rounding);
        }
        String step = "1.000";
        System.out.println(formatDecimal(MathUtilities.roundUp(0.0000000001, step), step));
        System.out.println(formatDecimal(MathUtilities.roundUp(0.1007, step), step));
        System.out.println(formatDecimal(MathUtilities.roundUp(0.107, step), step));
        System.out.println(formatDecimal(MathUtilities.roundUp(0.17, step), step));
        System.out.println(formatDecimal(MathUtilities.roundUp(0.10, step), step));
        System.out.println(formatDecimal(MathUtilities.roundUp(0.1, step), step));
        System.out.println(formatDecimal(MathUtilities.roundUp(0.1000, step), step));
        System.out.println(formatDecimal(MathUtilities.roundUp(100, step), step));
        System.out.println(formatDecimal(MathUtilities.roundUp(100.01, step), step));
        System.out.println(formatDecimal(MathUtilities.roundUp(0.1010, step), step));
        System.out.println(formatDecimal(MathUtilities.roundUp(1, step), step));
        System.out.println(formatDecimal(MathUtilities.roundUp(1.0001, step), step));
        System.out.println(formatDecimal(MathUtilities.roundUp(0.10, step), step));
        System.out.println(formatDecimal(MathUtilities.roundUp(0.11, step), step));
        //System.out.println(getFormattedRate(123.45, "0.10", "0.10", false));
    }
}
