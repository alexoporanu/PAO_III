package Transaction;

import Client.Address;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class TransactionCSVSingleton {
    private static TransactionCSVSingleton instance = null;

    private List<Transaction> transactions = new ArrayList<>();

    public static TransactionCSVSingleton getInstance() {
        if(instance == null)
            instance = new TransactionCSVSingleton();
        return instance;
    }

    public List<Transaction> getTransactions() {return this.transactions;}
    public void setTransactions(List<Transaction> transactions)
    {
        this.transactions = transactions;
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


    public void PopulateTransactionsFromCSV() throws ParseException {
        var columns = TransactionCSVSingleton.getCSVColumns("CSVfiles/transaction.csv");
        for(var fields : columns){
            var newTransaction = new Transaction(
                    Integer.parseInt(fields[0]), // moneyAmount
                    fields[1], // currency
                    fields[2], // sourceIBAN
                    fields[3], // destIBAN
                    new SimpleDateFormat("yyyy-MM-dd").parse(fields[4])   // Date
            );
            transactions.add(newTransaction);
        }
    }

    public void writeToCSVfile() {
        try
        {
            FileWriter fileWriter = new FileWriter("CSVfiles/transaction.csv");
            for(var transaction : this.transactions){
                fileWriter.write(transaction.toCSV());
                fileWriter.write("\n");
            }
            fileWriter.close();
        }
        catch (IOException e){
            System.out.println(e.toString());
        }
    }

}
