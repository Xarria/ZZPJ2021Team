package pl.zzpj2021.cleancode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class AverageInteger {

    Map<Integer, Integer> numberCountMap = new HashMap<>();
    private int minValue = Integer.MAX_VALUE;
    private int maxValue = Integer.MIN_VALUE;

    public AverageInteger(List<Integer> initialIntegerList) {
        countNumbers(initialIntegerList);
    }

    public AverageInteger() { }

    public void countNumbers(List<Integer> initialIntegers) {
		for (Integer index : initialIntegers) {
			incrementNumberCount(index);
		}
    }

    public void incrementNumberCount(Integer number) {
        if (numberCountMap.containsKey(number)) {
            Integer numberCount = numberCountMap.get(number);
            numberCountMap.put(number, numberCount + 1);
        }

        if (number < minValue) {
            minValue = number;
        }

        if (number > maxValue) {
            maxValue = number;
        }
    }

    public int getNumberOfIntegers(int i) {
        return numberCountMap.getOrDefault(i, 0);
    }

    public double calculateAverage() {
        double integerSum = 0;
        double integerCount = 0;
        for (Entry<Integer, Integer> u : numberCountMap.entrySet()) {
            integerCount += u.getValue();
            integerSum += u.getKey() * u.getValue();
        }
        if (integerCount == 0) {
            return 0;
        }
        return integerSum / integerCount;
    }

    public int getMinInteger() {
        return minValue;
    }

    public int getMaxInteger() {
        return maxValue;
    }


}