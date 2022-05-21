package Card;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class Card {
    private int CVV;
    private Date expirationDate;
    private String cardNumber;

    public Card(int CVV, Date expirationDate, String cardNumber) {
        this.CVV = CVV;
        this.expirationDate = expirationDate;
        this.cardNumber = cardNumber;
    }
    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getCVV() {
        return CVV;
    }

    public void setCVV(int CVV) {
        this.CVV = CVV;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public static void main(String[] args) {
        System.out.println(Generators.numberGenerator());
        System.out.println(Generators.cvvGenerator());
    }

    @Override
    public String toString() {
        String toReturn = "";
        toReturn += "CVV: ";
        toReturn += this.CVV;
        toReturn += "\n";
        toReturn += "Card Number: ";
        toReturn += this.cardNumber;
        toReturn += "\n";
        toReturn += "Expiration Date: ";
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.expirationDate);
        toReturn += (calendar.get(Calendar.DAY_OF_MONTH));
        toReturn += "-";
        toReturn += (calendar.get((Calendar.MONTH)));
        toReturn += "-";
        toReturn += (calendar.get((Calendar.YEAR)));
        toReturn += "\n";
        return toReturn;
    }

    public String toCSV() {
        return Integer.toString(this.CVV) + "," + (new SimpleDateFormat("yyyy-MM-dd")).format(this.expirationDate) +  "," + this.cardNumber;
    }
}


