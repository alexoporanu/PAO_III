package Client;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ClientCSVSingleton {
    private static ClientCSVSingleton instance = null;

    private List<Client> clients = new ArrayList<Client>();

    public static ClientCSVSingleton getInstance()
    {
        if (instance == null)
            instance = new ClientCSVSingleton();
        return instance;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public List<Client> getClients() {
        return clients;
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

    public void PopulateClientsFromCsv() {
        try{
            var columns = ClientCSVSingleton.getCSVColumns("CSVfiles/client.csv");
            for(var fields : columns){
                var newCustomer = new Client(
                        fields[0], // SEX
                        fields[1], // firstName
                        fields[2], // lastName
                        fields[3], // email
                        fields[4], // phoneNumber
                        new SimpleDateFormat("yyyy-MM-dd").parse(fields[5]),    // Date
                        fields[6], // CNP
                        new Address(fields[7], fields[8], fields[9], fields[10]) // Address
                );
                clients.add(newCustomer);
            }
        }
        catch (ParseException e){
            System.out.println(e.toString());
        }
    }

    public void writeToCSVFile(){
        try{
            FileWriter fileWriter = new FileWriter("CSVfiles/client.csv");
            for(var customer : this.clients){
                fileWriter.write(customer.toCSV());
                fileWriter.write("\n");
            }
            fileWriter.close();
        }catch (IOException e){
            System.out.println(e.toString());
        }
    }
}
