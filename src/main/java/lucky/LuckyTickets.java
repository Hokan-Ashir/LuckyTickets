package lucky;

import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LuckyTickets {

    public int nextLuckyTicket(int ticketNumber, int numberOfTickets) {
        if (ticketNumber >= numberOfTickets) {
            throw new RuntimeException("Invalid ticket number, must be less than number of tickets");
        }

        List<Integer> luckyTickets = Mappings.INSTANCE.getLuckyTickets(numberOfTickets);
        for (Integer luckyTicket : luckyTickets) {
            if (luckyTicket > ticketNumber) {
                return luckyTicket;
            }
        }

        return 0;
    }

    public void displayLuckyTicketsDistribution(int numberOfTickets) {
        Map<Integer, Map<Integer, Integer>> luckyTicketsDistribution =
                Mappings.INSTANCE.getLuckyTicketsDistribution(numberOfTickets);

        XYChart chart = new XYChartBuilder().width(1800).height(1024).
                title(getClass().getSimpleName()).xAxisTitle("Tickets").yAxisTitle("Amount of lucky tickets").build();

        for (Map.Entry<Integer, Map<Integer, Integer>> integerMapEntry : luckyTicketsDistribution.entrySet()) {
            final Integer order = integerMapEntry.getKey();
            final String chartName = "Order: " + order;
            List<Integer> xData = new ArrayList<>();
            List<Integer> yData = new ArrayList<>();
            for (Map.Entry<Integer, Integer> integerListEntry : integerMapEntry.getValue().entrySet()) {
                xData.add(integerListEntry.getKey() * order);
                yData.add(integerListEntry.getValue());
            }
            chart.addSeries(chartName, xData, yData);
        }

        new SwingWrapper<>(chart).displayChart();
    }

    public void printLuckyTicketsDistribution(int numberOfTickets) {
        Map<Integer, Map<Integer, Integer>> luckyTicketsDistribution =
                Mappings.INSTANCE.getLuckyTicketsDistribution(numberOfTickets);

        for (Map.Entry<Integer, Map<Integer, Integer>> integerMapEntry : luckyTicketsDistribution.entrySet()) {
            final Integer order = integerMapEntry.getKey();
            System.out.println("\nOder: " + order);
            for (Map.Entry<Integer, Integer> integerListEntry : integerMapEntry.getValue().entrySet()) {
                System.out.println("Range: " + integerListEntry.getKey() * order + " - " + ((integerListEntry.getKey() + 1) * order - 1));
                System.out.println("Total number of lucky tickets: " + integerListEntry.getValue());
            }
        }
    }

    public void printLuckyTickets(int numberOfTickets) {
        List<Integer> luckyTickets = Mappings.INSTANCE.getLuckyTickets(numberOfTickets);
        System.out.println("Lucky tickets:");
        for (Integer luckyTicket : luckyTickets) {
            System.out.println(luckyTicket);
        }
    }
}
