package lucky;

public class Main {

    public static void main(String[] args) {
        LuckyTickets luckyTickets = new LuckyTickets();
        int numberOfTickets = 1000000;
        luckyTickets.printLuckyTickets(numberOfTickets);
        luckyTickets.printLuckyTicketsDistribution(numberOfTickets);
        luckyTickets.displayLuckyTicketsDistribution(numberOfTickets);

        int ticketNumber = 100321;
        int nextLuckyTicket = luckyTickets.nextLuckyTicket(ticketNumber, numberOfTickets);
        System.out.println("Your next lucky ticket will be: " + nextLuckyTicket);
    }
}
