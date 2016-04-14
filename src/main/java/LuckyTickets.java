import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LuckyTickets {

    private static final int TOTAL_NUMBER_OF_TICKETS = 1000000;

    public void test() {
        double squareRoot = Math.sqrt(TOTAL_NUMBER_OF_TICKETS);
        if (squareRoot != (int) squareRoot) {
            System.out.println("Invalid number of total tickets, must have integer square root value");
            return;
        }

        Map<Integer, Map<Integer, List<Integer>>> luckyTickets = new HashMap<>();
        int minimumPart = (int) Math.pow(10.0, Math.ceil(Math.log10(TOTAL_NUMBER_OF_TICKETS) / 2.0));
        int temp = TOTAL_NUMBER_OF_TICKETS;
        while (temp != minimumPart) {
            temp = temp / 10;
            luckyTickets.put(temp, new HashMap<Integer, List<Integer>>());
        }

        for (int i = 0; i < TOTAL_NUMBER_OF_TICKETS; i++) {
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
