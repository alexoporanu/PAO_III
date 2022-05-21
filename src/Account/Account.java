package Account;

import Card.Card;
import Transaction.Transaction;

import java.util.*;

public class Account {
    protected String IBAN;
    protected int balance;
    protected String currency;
    protected int clientID;

    private List<Card> cards = new ArrayList<>();

    public Account(){};

    public Account(String IBAN, int balance, String currency, int customerID) {
        this.IBAN = IBAN;
        this.balance = balance;
        this.currency = currency;
        this.clientID = customerID;
    }

    public Account(int customerID) {
        this.clientID = customerID;
    }

    public List<Transaction> transactionsMade(List<Transaction> L){
        List<Transaction> transactions = new ArrayList<>();
        for(Transaction transaction: L)
            if(transaction.getSourceIBAN().equals(this.IBAN))
                transactions.add(transaction);
        return transactions;
    }

    public List<Transaction> transactionsReceived(List<Transaction> L){
        List<Transaction> transactions = new ArrayList<>();
        for(Transaction transaction: L)
            if(transaction.getDestIBAN().equals(this.IBAN))
                transactions.add(transaction);
        return transactions;
    }

    public List<Transaction> transactionsByDayReceived(List<Transaction> L, int year, int month, int day){
        List<Transaction> toReturn = new ArrayList<>();
        for(Transaction transaction: L) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(transaction.getDate());

            int yy = calendar.get(Calendar.YEAR);

            int mm = calendar.get(Calendar.MONTH);

            int dd = calendar.get(Calendar.DAY_OF_MONTH);

            if(transaction.getDestIBAN().equals(this.IBAN) && yy == year && dd == day && mm == month)
                toReturn.add(transaction);
        }
        return toReturn;
    }

    public List<Transaction> transactionsByDayMade(List<Transaction> L, int year, int month, int day){
        List<Transaction> toReturn = new ArrayList<>();
        for(Transaction transaction: L) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(transaction.getDate());

            int yy = calendar.get(Calendar.YEAR);

            int mm = calendar.get(Calendar.MONTH);

            int dd = calendar.get(Calendar.DAY_OF_MONTH);

            if(transaction.getSourceIBAN().equals(this.IBAN) && yy == year && dd == day && mm == month)
                toReturn.add(transaction);
        }
        return toReturn;
    }

    public void read(Scanner in) {
        System.out.println("Introduceti account data\n");
        in = new Scanner(System.in);
        System.out.println("IBAN: ");
        this.IBAN = in.nextLine();
        System.out.println("Balance: ");
        this.balance = Integer.parseInt(in.nextLine());
        System.out.println("Currency: ");
        this.currency = in.nextLine();
    }

    public String getIBAN() {
        return IBAN;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getCustomerID() {
        return clientID;
    }

    public void setCustomerID(int customerID) {
        this.clientID = customerID;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public String toString() {
        String toReturn = "";
        toReturn += "IBAN: ";
        toReturn += IBAN;
        toReturn += "\n";
        toReturn += "Balance: ";
        toReturn += balance;
        toReturn += "\n";
        toReturn += "Currency: ";
        toReturn += currency;
        toReturn += "\n";
        toReturn += "Customer ID: ";
        toReturn += clientID;
        return toReturn;

    }
    public void addCard(Card card) {
        this.cards.add(card);

    }
    public static void main(String[] args) {
    }

}
