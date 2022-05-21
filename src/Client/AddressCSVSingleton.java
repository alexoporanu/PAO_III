package Client;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class AddressCSVSingleton {
    private static AddressCSVSingleton instance = null;

    private List<Address> addresses = new ArrayList<Address>();

    public static AddressCSVSingleton getInstance() {
        if(instance == null)
            instance = new AddressCSVSingleton();
        return instance;
    }

    public List<Address> getAddresses() {return this.addresses;}
    public void setAddresses(List<Address> addresses)
    {
        this.addresses = addresses;
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

    public void PopulateAddressesFromCSV() {
        List<String[]> columns = AddressCSVSingleton.getCSVColumns("CSVfiles/address.csv");
        for(var fields : columns){
            var newAddress = new Address(
                    fields[0], // country
                    fields[1], // county
                    fields[2], // city
                    fields[3] // street
            );
            addresses.add(newAddress);
        }
    }

    public void writeToCSVfile() {
        try
        {
            FileWriter fileWriter = new FileWriter("CSVfiles/address.csv");
            for(var address : this.addresses){
                fileWriter.write(address.toCSV());
                fileWriter.write("\n");
            }
            fileWriter.close();
        }
        catch (IOException e){
            System.out.println(e.toString());
        }
    }

}
