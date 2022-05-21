package Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ClientDB {
    Connection connection;

    public ClientDB(Connection connection) {
        this.connection = connection;
    }

    public List<Client> read(){
        List<Client> clients = new ArrayList<>();
        try{
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM CLIENTS");
            while(result.next()) {
                Client current = new Client();
                current.readFromResultSet(result);
                clients.add(current);
            }
            ((Statement) statement).close();
        }catch (Exception e){
            System.out.println(e.toString());
        }
        return clients;
    }

    public void create(Client client){
        try{
            String query = "INSERT INTO CLIENTS (ID, SEX, firstName, lastName, CNP, birthDate, email, phoneNumber) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, client.getID());
            preparedStmt.setString(2, client.getSEX());
            preparedStmt.setString(3, client.getFirstName());
            preparedStmt.setString(4, client.getLastName());
            preparedStmt.setString(5, client.getCNP());
            preparedStmt.setString(6, (new SimpleDateFormat("yyyy-MM-dd")).format(client.getBirthDate()));
            preparedStmt.setString(7, client.getEmail());
            preparedStmt.setString(8, client.getPhoneNumber());
            preparedStmt.execute();
            preparedStmt.close();
            System.out.println("Added to DB");
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }

    public void delete(Client client){
        try{
            String query = "DELETE FROM CLIENTS WHERE ID = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, client.getID());
            preparedStmt.execute();
            preparedStmt.close();
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }


}
