package Account;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;

public class SavingsAccount extends Account {
    private int interestRate;

    public SavingsAccount(int interestRate, String IBAN, int balance, String currency, int customerID) {
        super(IBAN, balance, currency, customerID);
        this.interestRate = interestRate;
    }

    public SavingsAccount(ResultSet in) throws SQLException {
            this.IBAN = in.getString("IBAN");
            this.balance = in.getInt("balance");
            this.currency = in.getString("currency");
            this.clientID = in.getInt("customerID");
            this.interestRate = in.getInt("interestRate");
    }

    public SavingsAccount(int customerID) {
        super(customerID);
    }

    public int getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(int interestRate) {
        this.interestRate = interestRate;
    }

    public void read(Scanner scanner) {
        super.read(scanner);
        scanner = new Scanner(System.in);
        System.out.println("interestRate: ");
        this.interestRate = Integer.parseInt(scanner.nextLine());
    }

    @Override
    public String toString() {
        String toReturn = super.toString();
        toReturn += "\nInterest rate: ";
        toReturn += interestRate;
        return toReturn;
    }
}
