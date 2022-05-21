package Card;

import Transaction.Transaction;
import Transaction.TransactionCSVSingleton;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class CardCSVSingleton {
    private static CardCSVSingleton instance = null;

    private List<Card> cards = new ArrayList<>();

    public static CardCSVSingleton getInstance() {
        if(instance == null)
            instance = new CardCSVSingleton();
        return instance;
    }

    public List<Card> getCards() {return this.cards;}
    public void setCards(List<Card> cards)
    {
        this.cards = cards;
    }

    private static List<String[]> getCSVColumns(String fileName){

        List<String[]> columns = new ArrayList<>();

        try(var in = new BufferedReader(new FileReader(fileName))) {

            String line;

            while((line = in.readLine()) != null ) {
                String[] fields = line.replaceAll(" ", "").split(",");
                columns.add(fields);
            }
        } catch (IOException e) {
            System.out.println("Empty CSV file!");
        }

        return columns;
    }


    public void PopulateCardsFromCSV() throws ParseException {
        var columns = CardCSVSingleton.getCSVColumns("CSVfiles/card.csv");
        for(var fields : columns){
            var newCard = new Card(
                    Integer.parseInt(fields[0]), // CVV
                    new SimpleDateFormat("yyyy-MM-dd").parse(fields[1]),   // expiration date
                    fields[2]
            );
            cards.add(newCard);
        }
    }

    public void writeToCSVfile() {
        try
        {
            FileWriter fileWriter = new FileWriter("CSVfiles/card.csv");
            for(var card : this.cards){
                fileWriter.write(card.toCSV());
                fileWriter.write("\n");
            }
            fileWriter.close();
        }
        catch (IOException e){
            System.out.println(e.toString());
        }
    }
}
