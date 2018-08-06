package myweather.myweather.modelo;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MedicaoUtils {

    /**
     * Converte valor de temperatura Fahrenheit para Celsius.
     * @param fahrenheit - Temperatura em Fahrenheit
     * @return double com temperatura em Celsius com precisao de uma casa decimal.
     */
    public static double toCelsius(double fahrenheit) {
        double celsius = ((fahrenheit - 32) * 5/9);
        return BigDecimal.valueOf(celsius).setScale(1, RoundingMode.HALF_EVEN).doubleValue();
    }
}
