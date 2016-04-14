import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ачкасов Антон
 */
public class LuckyTickets {

    private static final int TOTAL_NUMBER_OF_TICKETS = 999999;

    public void test() {
        Map<Integer, List<Integer>> luckyTickets = new HashMap<Integer, List<Integer>>();
        int thousandNumber;
        for (int i = 0; i <= TOTAL_NUMBER_OF_TICKETS; i++) {
            thousandNumber = i / 100000;
            List<Integer> list;
            if (!luckyTickets.containsKey(thousandNumber)) {
                list = new ArrayList<Integer>();
                luckyTickets.put(thousandNumber, list);
            } else {
                list = luckyTickets.get(thousandNumber);
            }

            if (isLucky(i)) {
                list.add(i);
            }
        }

        printLuckyTickets(luckyTickets);
    }

    private boolean isLucky(int value) {
        int rightPart = value / 1000;
        int leftPart = value % 1000;
        return rightPart == leftPart;
    }

    private void printLuckyTickets(Map<Integer, List<Integer>> luckyTickets) {
        for (Map.Entry<Integer, List<Integer>> integerListEntry : luckyTickets.entrySet()) {
            System.out.println("Thousand: " + integerListEntry.getKey());
            System.out.println("Total number of lucky tickets: " + integerListEntry.getValue().size());
            for (Integer integer : integerListEntry.getValue()) {
                System.out.println("Lucky ticket: " + integer);
            }
        }
    }
}
