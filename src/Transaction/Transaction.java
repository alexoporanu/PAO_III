package Transaction;
//import package Client;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {

    private int moneyAmount;
    private String currency;
    private String sourceIBAN, destIBAN;
    private Date Date;

    public Date getDate() {
        return Date;
    }

    public Transaction(int moneyAmount, String currency, String sourceIBAN, String destIBAN, Date Date) {
        this.moneyAmount = moneyAmount;
        this.currency = currency;
        this.sourceIBAN = sourceIBAN;
        this.destIBAN = destIBAN;
        this.Date = Date;
    }

    public Transaction(ResultSet in) throws SQLException {
        this.sourceIBAN = in.getString("sourceIBAN");
        this.destIBAN = in.getString("destIBAN");
        this.moneyAmount = in.getInt("moneyAmount");
        this.Date = in.getDate("Date");
        this.currency = in.getString("Currency");
    }

    public int getMoneyAmount() {
        return moneyAmount;
    }

    public void setMoneyAmount(int moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setDate(java.util.Date date) {
        Date = date;
    }

    public String getSourceIBAN() {
        return sourceIBAN;
    }

    public void setSourceIBAN(String sourceIBAN) {
        this.sourceIBAN = sourceIBAN;
    }

    public String getDestIBAN() {
        return destIBAN;
    }

    public void setDestIBAN(String destIBAN) {
        this.destIBAN = destIBAN;
    }

    @Override
    public String toString() {
        String toReturn = "";
        toReturn += "Money amount: " + moneyAmount + "\n";
        toReturn += "Currency: " + currency + "\n";
        toReturn += "Source IBAN: " + sourceIBAN + "\n";
        toReturn += "Destination IBAN: " + destIBAN + "\n";
        return toReturn;
    }

    public String toCSV() {
        return Integer.toString(this.moneyAmount) + "," + this.currency + "," + this.sourceIBAN + "," + this.destIBAN + "," + (new SimpleDateFormat("yyyy-MM-dd")).format(this.Date);
    }

}
