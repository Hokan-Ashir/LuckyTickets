package lucky;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public enum Mappings {
    INSTANCE;

    private List<Integer> luckyTickets;

    public List<Integer> getLuckyTickets(int numberOfTickets) {
        checkNumberOfTickets(numberOfTickets);

        int minimumPart = getMinimumDivisionOrder(numberOfTickets);

        if (luckyTickets != null && luckyTickets.size() == numberOfTickets) {
            return luckyTickets;
        } else if (luckyTickets != null && luckyTickets.size() > numberOfTickets) {
            for (int i = numberOfTickets; i < luckyTickets.size(); i++) {
                luckyTickets.remove(i);
            }

            return luckyTickets;
        } else if (luckyTickets != null && luckyTickets.size() < numberOfTickets) {
            addLuckyTickets(luckyTickets.size(), numberOfTickets, minimumPart);

            return luckyTickets;
        } else {
            luckyTickets = new LinkedList<>();
            addLuckyTickets(0, numberOfTickets, minimumPart);

            return luckyTickets;
        }
    }

    private void checkNumberOfTickets(long numberOfTickets) {
        if (numberOfTickets <= 0) {
            throw new RuntimeException("Invalid number of total tickets, must be positive");
        }

        double squareRoot = Math.sqrt(numberOfTickets);
        if (squareRoot != (long) squareRoot) {
            throw new RuntimeException("Invalid number of total tickets, must have integer square root value");
        }

        final double ticketsLog10 = Math.log10(numberOfTickets);
        if (ticketsLog10 != (long) ticketsLog10) {
            throw new RuntimeException("Invalid number of total tickets, must have integer log_10 value");
        }

        if (ticketsLog10 % 2 != 0) {
            throw new RuntimeException("Invalid number of total tickets, must have even log_10 value");
        }
    }

    private void addLuckyTickets(int minimumBorder, int numberOfTickets, int minimumPart) {
        for (int i = minimumBorder; i < numberOfTickets; i++) {
            if (!isLucky(i, minimumPart)) {
                continue;
            }

            luckyTickets.add(i);
        }
    }

    private int getMinimumDivisionOrder(int numberOfTickets) {
        final double ticketsLog10 = Math.log10(numberOfTickets);
        return (int) Math.pow(10.0, Math.ceil(ticketsLog10 / 2.0));
    }


    private boolean isLucky(int value, int partValue) {
        int temp = value / partValue;
        int rightPart = getSumOfNumberDigits(temp);
        temp = value % partValue;
        int leftPart = getSumOfNumberDigits(temp);
        return rightPart == leftPart;
    }

    private int getSumOfNumberDigits(int temp) {
        int leftPart = 0;
        while(temp > 0) {
            leftPart += temp % 10;
            temp /= 10;
        }
        return leftPart;
    }

    public Map<Integer, Map<Integer, Integer>> getLuckyTicketsDistribution(int numberOfTickets) {
        List<Integer> tickets = getLuckyTickets(numberOfTickets);
        Map<Integer, Map<Integer, Integer>> distributionMap = new TreeMap<>();

        int minimumPart = getMinimumDivisionOrder(numberOfTickets);
        int temp = numberOfTickets;
        while (temp != minimumPart) {
            temp = temp / 10;
            distributionMap.put(temp, new TreeMap<Integer, Integer>());
        }

        for (Integer ticket : tickets) {
            for (Map.Entry<Integer, Map<Integer, Integer>> integerMapEntry : distributionMap.entrySet()) {
                final Integer order = integerMapEntry.getKey();
                incrementValue(integerMapEntry.getValue(), ticket / order);
            }
        }

        return distributionMap;
    }

    private void incrementValue( Map<Integer, Integer>  map, int order) {
        Integer value;
        if (!map.containsKey(order)) {
            value = 0;
            map.put(order, value);
        } else {
            value = map.get(order);
        }

        value++;
        map.put(order, value);
    }
}
