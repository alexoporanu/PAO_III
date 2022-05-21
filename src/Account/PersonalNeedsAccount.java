package Account;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PersonalNeedsAccount extends Account {
    private String need;

    PersonalNeedsAccount(String IBAN, int balance, String currency, int customerID, String need) {
        super(IBAN, balance, currency, customerID);
        this.need = need;
    }

    public PersonalNeedsAccount(int customerID) {
        super(customerID);
    }

    public PersonalNeedsAccount(ResultSet in) throws SQLException {
        this.IBAN = in.getString("IBAN");
        this.balance = in.getInt("balance");
        this.currency = in.getString("currency");
        this.clientID = in.getInt("customerID");
        this.need = in.getString("need");
    }

    public void read(Scanner scanner) {
        super.read(scanner);
        scanner = new Scanner(System.in);
        System.out.println("Need: ");
        this.need = scanner.nextLine();
    }

    public String getNeed() {
        return this.need;
    }

    @Override
    public String toString() {
        String toReturn = super.toString();
        toReturn += "\nNeed: ";
        toReturn += need;
        return toReturn;
    }
}
