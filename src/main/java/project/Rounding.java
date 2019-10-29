package project;

import com.openjaw.utils.MathUtilities;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testng.AssertJUnit.assertEquals;

public class Rounding {

    public static final List<Double> NUMBERS = new ArrayList<>(Arrays.asList(
            1.2345, 2.3456, 3.4567, 4.5678,
            1.234, 2.345, 3.456, 4.567,
            1.23, 2.35, 3.45, 4.56,
            1.2, 2.3, 3.4, 4.5,
            100.15, 100.0, 100.01, 105.0, 103.0,
            0.0000001, 0.1007, 0.1, 0.1000));

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
            for (Double number : NUMBERS) {
        for (String rounding : ROUNDINGS) {
                String formattedRate = getFormattedRate(number, rounding, rounding, false);
                System.out.println("assertEquals(\""+formattedRate+"\", messageWrapper.getFormattedRate("+number+",\""+ rounding+"\",\""+
                        rounding+"\",false));");
            }
        }

        String step = "0.10";
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
        System.out.println(formatDecimal(MathUtilities.roundUp(12.3456, step), step));
        //System.out.println(getFormattedRate(123.45, "0.10", "0.10", false));

        assertEquals("2.00", getFormattedRate(1.2345,"1.00","1.00",false));
        assertEquals("3.00", getFormattedRate(2.3456,"1.00","1.00",false));
        assertEquals("4.00", getFormattedRate(3.4567,"1.00","1.00",false));
        assertEquals("5.00", getFormattedRate(4.5678,"1.00","1.00",false));
        assertEquals("2.00", getFormattedRate(1.234,"1.00","1.00",false));
        assertEquals("3.00", getFormattedRate(2.345,"1.00","1.00",false));
        assertEquals("4.00", getFormattedRate(3.456,"1.00","1.00",false));
        assertEquals("5.00", getFormattedRate(4.567,"1.00","1.00",false));
        assertEquals("2.00", getFormattedRate(1.23,"1.00","1.00",false));
        assertEquals("3.00", getFormattedRate(2.35,"1.00","1.00",false));
        assertEquals("4.00", getFormattedRate(3.45,"1.00","1.00",false));
        assertEquals("5.00", getFormattedRate(4.56,"1.00","1.00",false));
        assertEquals("2.00", getFormattedRate(1.2,"1.00","1.00",false));
        assertEquals("3.00", getFormattedRate(2.3,"1.00","1.00",false));
        assertEquals("4.00", getFormattedRate(3.4,"1.00","1.00",false));
        assertEquals("5.00", getFormattedRate(4.5,"1.00","1.00",false));
        assertEquals("101.00", getFormattedRate(100.15,"1.00","1.00",false));
        assertEquals("100.00", getFormattedRate(100.0,"1.00","1.00",false));
        assertEquals("101.00", getFormattedRate(100.01,"1.00","1.00",false));
        assertEquals("105.00", getFormattedRate(105.0,"1.00","1.00",false));
        assertEquals("103.00", getFormattedRate(103.0,"1.00","1.00",false));
        assertEquals("1.00", getFormattedRate(1.0E-7,"1.00","1.00",false));
        assertEquals("1.00", getFormattedRate(0.1007,"1.00","1.00",false));
        assertEquals("1.00", getFormattedRate(0.1,"1.00","1.00",false));
        assertEquals("1.00", getFormattedRate(0.1,"1.00","1.00",false));
        assertEquals("1.24", getFormattedRate(1.2345,"0.01","0.01",false));
        assertEquals("2.35", getFormattedRate(2.3456,"0.01","0.01",false));
        assertEquals("3.46", getFormattedRate(3.4567,"0.01","0.01",false));
        assertEquals("4.57", getFormattedRate(4.5678,"0.01","0.01",false));
        assertEquals("1.24", getFormattedRate(1.234,"0.01","0.01",false));
        assertEquals("2.35", getFormattedRate(2.345,"0.01","0.01",false));
        assertEquals("3.46", getFormattedRate(3.456,"0.01","0.01",false));
        assertEquals("4.57", getFormattedRate(4.567,"0.01","0.01",false));
        assertEquals("1.23", getFormattedRate(1.23,"0.01","0.01",false));
        assertEquals("2.36", getFormattedRate(2.35,"0.01","0.01",false));
        assertEquals("3.46", getFormattedRate(3.45,"0.01","0.01",false));
        assertEquals("4.56", getFormattedRate(4.56,"0.01","0.01",false));
        assertEquals("1.20", getFormattedRate(1.2,"0.01","0.01",false));
        assertEquals("2.30", getFormattedRate(2.3,"0.01","0.01",false));
        assertEquals("3.40", getFormattedRate(3.4,"0.01","0.01",false));
        assertEquals("4.50", getFormattedRate(4.5,"0.01","0.01",false));
        assertEquals("100.16", getFormattedRate(100.15,"0.01","0.01",false));
        assertEquals("100.00", getFormattedRate(100.0,"0.01","0.01",false));
        assertEquals("100.02", getFormattedRate(100.01,"0.01","0.01",false));
        assertEquals("105.00", getFormattedRate(105.0,"0.01","0.01",false));
        assertEquals("103.00", getFormattedRate(103.0,"0.01","0.01",false));
        assertEquals("0.01", getFormattedRate(1.0E-7,"0.01","0.01",false));
        assertEquals("0.11", getFormattedRate(0.1007,"0.01","0.01",false));
        assertEquals("0.10", getFormattedRate(0.1,"0.01","0.01",false));
        assertEquals("0.10", getFormattedRate(0.1,"0.01","0.01",false));
        assertEquals("1.30", getFormattedRate(1.2345,"0.10","0.10",false));
        assertEquals("2.40", getFormattedRate(2.3456,"0.10","0.10",false));
        assertEquals("3.50", getFormattedRate(3.4567,"0.10","0.10",false));
        assertEquals("4.60", getFormattedRate(4.5678,"0.10","0.10",false));
        assertEquals("1.30", getFormattedRate(1.234,"0.10","0.10",false));
        assertEquals("2.40", getFormattedRate(2.345,"0.10","0.10",false));
        assertEquals("3.50", getFormattedRate(3.456,"0.10","0.10",false));
        assertEquals("4.60", getFormattedRate(4.567,"0.10","0.10",false));
        assertEquals("1.30", getFormattedRate(1.23,"0.10","0.10",false));
        assertEquals("2.40", getFormattedRate(2.35,"0.10","0.10",false));
        assertEquals("3.50", getFormattedRate(3.45,"0.10","0.10",false));
        assertEquals("4.60", getFormattedRate(4.56,"0.10","0.10",false));
        assertEquals("1.20", getFormattedRate(1.2,"0.10","0.10",false));
        assertEquals("2.30", getFormattedRate(2.3,"0.10","0.10",false));
        assertEquals("3.40", getFormattedRate(3.4,"0.10","0.10",false));
        assertEquals("4.50", getFormattedRate(4.5,"0.10","0.10",false));
        assertEquals("100.20", getFormattedRate(100.15,"0.10","0.10",false));
        assertEquals("100.00", getFormattedRate(100.0,"0.10","0.10",false));
        assertEquals("100.10", getFormattedRate(100.01,"0.10","0.10",false));
        assertEquals("105.00", getFormattedRate(105.0,"0.10","0.10",false));
        assertEquals("103.00", getFormattedRate(103.0,"0.10","0.10",false));
        assertEquals("0.10", getFormattedRate(1.0E-7,"0.10","0.10",false));
        assertEquals("0.20", getFormattedRate(0.1007,"0.10","0.10",false));
        assertEquals("0.10", getFormattedRate(0.1,"0.10","0.10",false));
        assertEquals("0.10", getFormattedRate(0.1,"0.10","0.10",false));
        assertEquals("5", getFormattedRate(1.2345,"5","5",false));
        assertEquals("5", getFormattedRate(2.3456,"5","5",false));
        assertEquals("5", getFormattedRate(3.4567,"5","5",false));
        assertEquals("5", getFormattedRate(4.5678,"5","5",false));
        assertEquals("5", getFormattedRate(1.234,"5","5",false));
        assertEquals("5", getFormattedRate(2.345,"5","5",false));
        assertEquals("5", getFormattedRate(3.456,"5","5",false));
        assertEquals("5", getFormattedRate(4.567,"5","5",false));
        assertEquals("5", getFormattedRate(1.23,"5","5",false));
        assertEquals("5", getFormattedRate(2.35,"5","5",false));
        assertEquals("5", getFormattedRate(3.45,"5","5",false));
        assertEquals("5", getFormattedRate(4.56,"5","5",false));
        assertEquals("5", getFormattedRate(1.2,"5","5",false));
        assertEquals("5", getFormattedRate(2.3,"5","5",false));
        assertEquals("5", getFormattedRate(3.4,"5","5",false));
        assertEquals("5", getFormattedRate(4.5,"5","5",false));
        assertEquals("105", getFormattedRate(100.15,"5","5",false));
        assertEquals("100", getFormattedRate(100.0,"5","5",false));
        assertEquals("105", getFormattedRate(100.01,"5","5",false));
        assertEquals("105", getFormattedRate(105.0,"5","5",false));
        assertEquals("105", getFormattedRate(103.0,"5","5",false));
        assertEquals("5", getFormattedRate(1.0E-7,"5","5",false));
        assertEquals("5", getFormattedRate(0.1007,"5","5",false));
        assertEquals("5", getFormattedRate(0.1,"5","5",false));
        assertEquals("5", getFormattedRate(0.1,"5","5",false));
        assertEquals("2.0", getFormattedRate(1.2345,"1.0","1.0",false));
        assertEquals("3.0", getFormattedRate(2.3456,"1.0","1.0",false));
        assertEquals("4.0", getFormattedRate(3.4567,"1.0","1.0",false));
        assertEquals("5.0", getFormattedRate(4.5678,"1.0","1.0",false));
        assertEquals("2.0", getFormattedRate(1.234,"1.0","1.0",false));
        assertEquals("3.0", getFormattedRate(2.345,"1.0","1.0",false));
        assertEquals("4.0", getFormattedRate(3.456,"1.0","1.0",false));
        assertEquals("5.0", getFormattedRate(4.567,"1.0","1.0",false));
        assertEquals("2.0", getFormattedRate(1.23,"1.0","1.0",false));
        assertEquals("3.0", getFormattedRate(2.35,"1.0","1.0",false));
        assertEquals("4.0", getFormattedRate(3.45,"1.0","1.0",false));
        assertEquals("5.0", getFormattedRate(4.56,"1.0","1.0",false));
        assertEquals("2.0", getFormattedRate(1.2,"1.0","1.0",false));
        assertEquals("3.0", getFormattedRate(2.3,"1.0","1.0",false));
        assertEquals("4.0", getFormattedRate(3.4,"1.0","1.0",false));
        assertEquals("5.0", getFormattedRate(4.5,"1.0","1.0",false));
        assertEquals("101.0", getFormattedRate(100.15,"1.0","1.0",false));
        assertEquals("100.0", getFormattedRate(100.0,"1.0","1.0",false));
        assertEquals("101.0", getFormattedRate(100.01,"1.0","1.0",false));
        assertEquals("105.0", getFormattedRate(105.0,"1.0","1.0",false));
        assertEquals("103.0", getFormattedRate(103.0,"1.0","1.0",false));
        assertEquals("1.0", getFormattedRate(1.0E-7,"1.0","1.0",false));
        assertEquals("1.0", getFormattedRate(0.1007,"1.0","1.0",false));
        assertEquals("1.0", getFormattedRate(0.1,"1.0","1.0",false));
        assertEquals("1.0", getFormattedRate(0.1,"1.0","1.0",false));
        assertEquals("100", getFormattedRate(1.2345,"100","100",false));
        assertEquals("100", getFormattedRate(2.3456,"100","100",false));
        assertEquals("100", getFormattedRate(3.4567,"100","100",false));
        assertEquals("100", getFormattedRate(4.5678,"100","100",false));
        assertEquals("100", getFormattedRate(1.234,"100","100",false));
        assertEquals("100", getFormattedRate(2.345,"100","100",false));
        assertEquals("100", getFormattedRate(3.456,"100","100",false));
        assertEquals("100", getFormattedRate(4.567,"100","100",false));
        assertEquals("100", getFormattedRate(1.23,"100","100",false));
        assertEquals("100", getFormattedRate(2.35,"100","100",false));
        assertEquals("100", getFormattedRate(3.45,"100","100",false));
        assertEquals("100", getFormattedRate(4.56,"100","100",false));
        assertEquals("100", getFormattedRate(1.2,"100","100",false));
        assertEquals("100", getFormattedRate(2.3,"100","100",false));
        assertEquals("100", getFormattedRate(3.4,"100","100",false));
        assertEquals("100", getFormattedRate(4.5,"100","100",false));
        assertEquals("200", getFormattedRate(100.15,"100","100",false));
        assertEquals("100", getFormattedRate(100.0,"100","100",false));
        assertEquals("200", getFormattedRate(100.01,"100","100",false));
        assertEquals("200", getFormattedRate(105.0,"100","100",false));
        assertEquals("200", getFormattedRate(103.0,"100","100",false));
        assertEquals("100", getFormattedRate(1.0E-7,"100","100",false));
        assertEquals("100", getFormattedRate(0.1007,"100","100",false));
        assertEquals("100", getFormattedRate(0.1,"100","100",false));
        assertEquals("100", getFormattedRate(0.1,"100","100",false));
        assertEquals("10", getFormattedRate(1.2345,"10","10",false));
        assertEquals("10", getFormattedRate(2.3456,"10","10",false));
        assertEquals("10", getFormattedRate(3.4567,"10","10",false));
        assertEquals("10", getFormattedRate(4.5678,"10","10",false));
        assertEquals("10", getFormattedRate(1.234,"10","10",false));
        assertEquals("10", getFormattedRate(2.345,"10","10",false));
        assertEquals("10", getFormattedRate(3.456,"10","10",false));
        assertEquals("10", getFormattedRate(4.567,"10","10",false));
        assertEquals("10", getFormattedRate(1.23,"10","10",false));
        assertEquals("10", getFormattedRate(2.35,"10","10",false));
        assertEquals("10", getFormattedRate(3.45,"10","10",false));
        assertEquals("10", getFormattedRate(4.56,"10","10",false));
        assertEquals("10", getFormattedRate(1.2,"10","10",false));
        assertEquals("10", getFormattedRate(2.3,"10","10",false));
        assertEquals("10", getFormattedRate(3.4,"10","10",false));
        assertEquals("10", getFormattedRate(4.5,"10","10",false));
        assertEquals("110", getFormattedRate(100.15,"10","10",false));
        assertEquals("100", getFormattedRate(100.0,"10","10",false));
        assertEquals("110", getFormattedRate(100.01,"10","10",false));
        assertEquals("110", getFormattedRate(105.0,"10","10",false));
        assertEquals("110", getFormattedRate(103.0,"10","10",false));
        assertEquals("10", getFormattedRate(1.0E-7,"10","10",false));
        assertEquals("10", getFormattedRate(0.1007,"10","10",false));
        assertEquals("10", getFormattedRate(0.1,"10","10",false));
        assertEquals("10", getFormattedRate(0.1,"10","10",false));


        assertEquals("2.00", getFormattedRate(1.2345,"1.00","1.00",false));
        assertEquals("1.24", getFormattedRate(1.2345,"0.01","0.01",false));
        assertEquals("1.30", getFormattedRate(1.2345,"0.10","0.10",false));
        assertEquals("5", getFormattedRate(1.2345,"5","5",false));
        assertEquals("2.0", getFormattedRate(1.2345,"1.0","1.0",false));
        assertEquals("100", getFormattedRate(1.2345,"100","100",false));
        assertEquals("10", getFormattedRate(1.2345,"10","10",false));
        assertEquals("3.00", getFormattedRate(2.3456,"1.00","1.00",false));
        assertEquals("2.35", getFormattedRate(2.3456,"0.01","0.01",false));
        assertEquals("2.40", getFormattedRate(2.3456,"0.10","0.10",false));
        assertEquals("5", getFormattedRate(2.3456,"5","5",false));
        assertEquals("3.0", getFormattedRate(2.3456,"1.0","1.0",false));
        assertEquals("100", getFormattedRate(2.3456,"100","100",false));
        assertEquals("10", getFormattedRate(2.3456,"10","10",false));
        assertEquals("4.00", getFormattedRate(3.4567,"1.00","1.00",false));
        assertEquals("3.46", getFormattedRate(3.4567,"0.01","0.01",false));
        assertEquals("3.50", getFormattedRate(3.4567,"0.10","0.10",false));
        assertEquals("5", getFormattedRate(3.4567,"5","5",false));
        assertEquals("4.0", getFormattedRate(3.4567,"1.0","1.0",false));
        assertEquals("100", getFormattedRate(3.4567,"100","100",false));
        assertEquals("10", getFormattedRate(3.4567,"10","10",false));
        assertEquals("5.00", getFormattedRate(4.5678,"1.00","1.00",false));
        assertEquals("4.57", getFormattedRate(4.5678,"0.01","0.01",false));
        assertEquals("4.60", getFormattedRate(4.5678,"0.10","0.10",false));
        assertEquals("5", getFormattedRate(4.5678,"5","5",false));
        assertEquals("5.0", getFormattedRate(4.5678,"1.0","1.0",false));
        assertEquals("100", getFormattedRate(4.5678,"100","100",false));
        assertEquals("10", getFormattedRate(4.5678,"10","10",false));
        assertEquals("2.00", getFormattedRate(1.234,"1.00","1.00",false));
        assertEquals("1.24", getFormattedRate(1.234,"0.01","0.01",false));
        assertEquals("1.30", getFormattedRate(1.234,"0.10","0.10",false));
        assertEquals("5", getFormattedRate(1.234,"5","5",false));
        assertEquals("2.0", getFormattedRate(1.234,"1.0","1.0",false));
        assertEquals("100", getFormattedRate(1.234,"100","100",false));
        assertEquals("10", getFormattedRate(1.234,"10","10",false));
        assertEquals("3.00", getFormattedRate(2.345,"1.00","1.00",false));
        assertEquals("2.35", getFormattedRate(2.345,"0.01","0.01",false));
        assertEquals("2.40", getFormattedRate(2.345,"0.10","0.10",false));
        assertEquals("5", getFormattedRate(2.345,"5","5",false));
        assertEquals("3.0", getFormattedRate(2.345,"1.0","1.0",false));
        assertEquals("100", getFormattedRate(2.345,"100","100",false));
        assertEquals("10", getFormattedRate(2.345,"10","10",false));
        assertEquals("4.00", getFormattedRate(3.456,"1.00","1.00",false));
        assertEquals("3.46", getFormattedRate(3.456,"0.01","0.01",false));
        assertEquals("3.50", getFormattedRate(3.456,"0.10","0.10",false));
        assertEquals("5", getFormattedRate(3.456,"5","5",false));
        assertEquals("4.0", getFormattedRate(3.456,"1.0","1.0",false));
        assertEquals("100", getFormattedRate(3.456,"100","100",false));
        assertEquals("10", getFormattedRate(3.456,"10","10",false));
        assertEquals("5.00", getFormattedRate(4.567,"1.00","1.00",false));
        assertEquals("4.57", getFormattedRate(4.567,"0.01","0.01",false));
        assertEquals("4.60", getFormattedRate(4.567,"0.10","0.10",false));
        assertEquals("5", getFormattedRate(4.567,"5","5",false));
        assertEquals("5.0", getFormattedRate(4.567,"1.0","1.0",false));
        assertEquals("100", getFormattedRate(4.567,"100","100",false));
        assertEquals("10", getFormattedRate(4.567,"10","10",false));
        assertEquals("2.00", getFormattedRate(1.23,"1.00","1.00",false));
        assertEquals("1.23", getFormattedRate(1.23,"0.01","0.01",false));
        assertEquals("1.30", getFormattedRate(1.23,"0.10","0.10",false));
        assertEquals("5", getFormattedRate(1.23,"5","5",false));
        assertEquals("2.0", getFormattedRate(1.23,"1.0","1.0",false));
        assertEquals("100", getFormattedRate(1.23,"100","100",false));
        assertEquals("10", getFormattedRate(1.23,"10","10",false));
        assertEquals("3.00", getFormattedRate(2.35,"1.00","1.00",false));
        assertEquals("2.36", getFormattedRate(2.35,"0.01","0.01",false));
        assertEquals("2.40", getFormattedRate(2.35,"0.10","0.10",false));
        assertEquals("5", getFormattedRate(2.35,"5","5",false));
        assertEquals("3.0", getFormattedRate(2.35,"1.0","1.0",false));
        assertEquals("100", getFormattedRate(2.35,"100","100",false));
        assertEquals("10", getFormattedRate(2.35,"10","10",false));
        assertEquals("4.00", getFormattedRate(3.45,"1.00","1.00",false));
        assertEquals("3.46", getFormattedRate(3.45,"0.01","0.01",false));
        assertEquals("3.50", getFormattedRate(3.45,"0.10","0.10",false));
        assertEquals("5", getFormattedRate(3.45,"5","5",false));
        assertEquals("4.0", getFormattedRate(3.45,"1.0","1.0",false));
        assertEquals("100", getFormattedRate(3.45,"100","100",false));
        assertEquals("10", getFormattedRate(3.45,"10","10",false));
        assertEquals("5.00", getFormattedRate(4.56,"1.00","1.00",false));
        assertEquals("4.56", getFormattedRate(4.56,"0.01","0.01",false));
        assertEquals("4.60", getFormattedRate(4.56,"0.10","0.10",false));
        assertEquals("5", getFormattedRate(4.56,"5","5",false));
        assertEquals("5.0", getFormattedRate(4.56,"1.0","1.0",false));
        assertEquals("100", getFormattedRate(4.56,"100","100",false));
        assertEquals("10", getFormattedRate(4.56,"10","10",false));
        assertEquals("2.00", getFormattedRate(1.2,"1.00","1.00",false));
        assertEquals("1.20", getFormattedRate(1.2,"0.01","0.01",false));
        assertEquals("1.20", getFormattedRate(1.2,"0.10","0.10",false));
        assertEquals("5", getFormattedRate(1.2,"5","5",false));
        assertEquals("2.0", getFormattedRate(1.2,"1.0","1.0",false));
        assertEquals("100", getFormattedRate(1.2,"100","100",false));
        assertEquals("10", getFormattedRate(1.2,"10","10",false));
        assertEquals("3.00", getFormattedRate(2.3,"1.00","1.00",false));
        assertEquals("2.30", getFormattedRate(2.3,"0.01","0.01",false));
        assertEquals("2.30", getFormattedRate(2.3,"0.10","0.10",false));
        assertEquals("5", getFormattedRate(2.3,"5","5",false));
        assertEquals("3.0", getFormattedRate(2.3,"1.0","1.0",false));
        assertEquals("100", getFormattedRate(2.3,"100","100",false));
        assertEquals("10", getFormattedRate(2.3,"10","10",false));
        assertEquals("4.00", getFormattedRate(3.4,"1.00","1.00",false));
        assertEquals("3.40", getFormattedRate(3.4,"0.01","0.01",false));
        assertEquals("3.40", getFormattedRate(3.4,"0.10","0.10",false));
        assertEquals("5", getFormattedRate(3.4,"5","5",false));
        assertEquals("4.0", getFormattedRate(3.4,"1.0","1.0",false));
        assertEquals("100", getFormattedRate(3.4,"100","100",false));
        assertEquals("10", getFormattedRate(3.4,"10","10",false));
        assertEquals("5.00", getFormattedRate(4.5,"1.00","1.00",false));
        assertEquals("4.50", getFormattedRate(4.5,"0.01","0.01",false));
        assertEquals("4.50", getFormattedRate(4.5,"0.10","0.10",false));
        assertEquals("5", getFormattedRate(4.5,"5","5",false));
        assertEquals("5.0", getFormattedRate(4.5,"1.0","1.0",false));
        assertEquals("100", getFormattedRate(4.5,"100","100",false));
        assertEquals("10", getFormattedRate(4.5,"10","10",false));
        assertEquals("101.00", getFormattedRate(100.15,"1.00","1.00",false));
        assertEquals("100.16", getFormattedRate(100.15,"0.01","0.01",false));
        assertEquals("100.20", getFormattedRate(100.15,"0.10","0.10",false));
        assertEquals("105", getFormattedRate(100.15,"5","5",false));
        assertEquals("101.0", getFormattedRate(100.15,"1.0","1.0",false));
        assertEquals("200", getFormattedRate(100.15,"100","100",false));
        assertEquals("110", getFormattedRate(100.15,"10","10",false));
        assertEquals("100.00", getFormattedRate(100.0,"1.00","1.00",false));
        assertEquals("100.00", getFormattedRate(100.0,"0.01","0.01",false));
        assertEquals("100.00", getFormattedRate(100.0,"0.10","0.10",false));
        assertEquals("100", getFormattedRate(100.0,"5","5",false));
        assertEquals("100.0", getFormattedRate(100.0,"1.0","1.0",false));
        assertEquals("100", getFormattedRate(100.0,"100","100",false));
        assertEquals("100", getFormattedRate(100.0,"10","10",false));
        assertEquals("101.00", getFormattedRate(100.01,"1.00","1.00",false));
        assertEquals("100.02", getFormattedRate(100.01,"0.01","0.01",false));
        assertEquals("100.10", getFormattedRate(100.01,"0.10","0.10",false));
        assertEquals("105", getFormattedRate(100.01,"5","5",false));
        assertEquals("101.0", getFormattedRate(100.01,"1.0","1.0",false));
        assertEquals("200", getFormattedRate(100.01,"100","100",false));
        assertEquals("110", getFormattedRate(100.01,"10","10",false));
        assertEquals("105.00", getFormattedRate(105.0,"1.00","1.00",false));
        assertEquals("105.00", getFormattedRate(105.0,"0.01","0.01",false));
        assertEquals("105.00", getFormattedRate(105.0,"0.10","0.10",false));
        assertEquals("105", getFormattedRate(105.0,"5","5",false));
        assertEquals("105.0", getFormattedRate(105.0,"1.0","1.0",false));
        assertEquals("200", getFormattedRate(105.0,"100","100",false));
        assertEquals("110", getFormattedRate(105.0,"10","10",false));
        assertEquals("103.00", getFormattedRate(103.0,"1.00","1.00",false));
        assertEquals("103.00", getFormattedRate(103.0,"0.01","0.01",false));
        assertEquals("103.00", getFormattedRate(103.0,"0.10","0.10",false));
        assertEquals("105", getFormattedRate(103.0,"5","5",false));
        assertEquals("103.0", getFormattedRate(103.0,"1.0","1.0",false));
        assertEquals("200", getFormattedRate(103.0,"100","100",false));
        assertEquals("110", getFormattedRate(103.0,"10","10",false));
        assertEquals("1.00", getFormattedRate(1.0E-7,"1.00","1.00",false));
        assertEquals("0.01", getFormattedRate(1.0E-7,"0.01","0.01",false));
        assertEquals("0.10", getFormattedRate(1.0E-7,"0.10","0.10",false));
        assertEquals("5", getFormattedRate(1.0E-7,"5","5",false));
        assertEquals("1.0", getFormattedRate(1.0E-7,"1.0","1.0",false));
        assertEquals("100", getFormattedRate(1.0E-7,"100","100",false));
        assertEquals("10", getFormattedRate(1.0E-7,"10","10",false));
        assertEquals("1.00", getFormattedRate(0.1007,"1.00","1.00",false));
        assertEquals("0.11", getFormattedRate(0.1007,"0.01","0.01",false));
        assertEquals("0.20", getFormattedRate(0.1007,"0.10","0.10",false));
        assertEquals("5", getFormattedRate(0.1007,"5","5",false));
        assertEquals("1.0", getFormattedRate(0.1007,"1.0","1.0",false));
        assertEquals("100", getFormattedRate(0.1007,"100","100",false));
        assertEquals("10", getFormattedRate(0.1007,"10","10",false));
        assertEquals("1.00", getFormattedRate(0.1,"1.00","1.00",false));
        assertEquals("0.10", getFormattedRate(0.1,"0.01","0.01",false));
        assertEquals("0.10", getFormattedRate(0.1,"0.10","0.10",false));
        assertEquals("5", getFormattedRate(0.1,"5","5",false));
        assertEquals("1.0", getFormattedRate(0.1,"1.0","1.0",false));
        assertEquals("100", getFormattedRate(0.1,"100","100",false));
        assertEquals("10", getFormattedRate(0.1,"10","10",false));
        assertEquals("1.00", getFormattedRate(0.1,"1.00","1.00",false));
        assertEquals("0.10", getFormattedRate(0.1,"0.01","0.01",false));
        assertEquals("0.10", getFormattedRate(0.1,"0.10","0.10",false));
        assertEquals("5", getFormattedRate(0.1,"5","5",false));
        assertEquals("1.0", getFormattedRate(0.1,"1.0","1.0",false));
        assertEquals("100", getFormattedRate(0.1,"100","100",false));
        assertEquals("10", getFormattedRate(0.1,"10","10",false));

    }
}
