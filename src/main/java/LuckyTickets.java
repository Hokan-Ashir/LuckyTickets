import java.util.*;

public class LuckyTickets {

    public void test(int numberOfTickets) {
        double squareRoot = Math.sqrt(numberOfTickets);
        if (squareRoot != (int) squareRoot) {
            System.out.println("Invalid number of total tickets, must have integer square root value");
            return;
        }

        final double ticketsLog10 = Math.log10(numberOfTickets);
        if (ticketsLog10 != (int) ticketsLog10) {
            System.out.println("Invalid number of total tickets, must have integer log_10 value");
            return;
        }

        if (ticketsLog10 % 2 != 0) {
            System.out.println("Invalid number of total tickets, must have even log_10 value");
            return;
        }

        Map<Integer, Map<Integer, List<Integer>>> luckyTickets = new TreeMap<>();
        int minimumPart = (int) Math.pow(10.0, Math.ceil(ticketsLog10 / 2.0));
        int temp = numberOfTickets;
        while (temp != minimumPart) {
            temp = temp / 10;
            luckyTickets.put(temp, new TreeMap<Integer, List<Integer>>());
        }

        for (int i = 0; i < numberOfTickets; i++) {
            boolean isLucky = isLucky(i, minimumPart);
            if (!isLucky) {
                continue;
            }

            for (Map.Entry<Integer, Map<Integer, List<Integer>>> integerMapEntry : luckyTickets.entrySet()) {
                final Integer order = integerMapEntry.getKey();
                List<Integer> list = getLuckyTicketList(integerMapEntry.getValue(), i / order);
                list.add(i);
            }
        }

        printLuckyTickets(luckyTickets);
    }

    private List<Integer> getLuckyTicketList(Map<Integer, List<Integer>> map, int order) {
        List<Integer> list;
        if (!map.containsKey(order)) {
            list = new ArrayList<>();
            map.put(order, list);
        } else {
            list = map.get(order);
        }

        return list;
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

    private void printLuckyTickets(Map<Integer, Map<Integer, List<Integer>>> luckyTickets) {
        for (Map.Entry<Integer, Map<Integer, List<Integer>>> integerMapEntry : luckyTickets.entrySet()) {
            final Integer order = integerMapEntry.getKey();
            System.out.println("\nOder: " + order);
            for (Map.Entry<Integer, List<Integer>> integerListEntry : integerMapEntry.getValue().entrySet()) {
                System.out.println("Range: " + integerListEntry.getKey() * order + " - " + ((integerListEntry.getKey() + 1) * order - 1));
                System.out.println("Total number of lucky tickets: " + integerListEntry.getValue().size());
                /*for (Integer integer : integerListEntry.getValue()) {
                    System.out.println("Lucky ticket: " + integer);
                }*/
            }
        }
    }
}
