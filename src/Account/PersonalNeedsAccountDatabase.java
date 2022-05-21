package Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PersonalNeedsAccountDatabase {
    Connection connection;

    public PersonalNeedsAccountDatabase(Connection connection) {
        this.connection = connection;
    }

    public List<PersonalNeedsAccount> read(){
        List<PersonalNeedsAccount> personalNeedsAccounts = new ArrayList<>();
        try{
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM PNAccounts");
            while(result.next()) {
                PersonalNeedsAccount current = new PersonalNeedsAccount(result);
                personalNeedsAccounts.add(current);
            }
            statement.close();
        }catch (Exception e){
            System.out.println(e.toString());
        }
        return personalNeedsAccounts;
    }

    public void create(PersonalNeedsAccount personalNeedsAccount){
        try{
            String query = "INSERT INTO PNAccounts (IBAN, balance, currency, customerId, need) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, personalNeedsAccount.getIBAN());
            preparedStmt.setInt(2, personalNeedsAccount.getBalance());
            preparedStmt.setString(3, personalNeedsAccount.getCurrency());
            preparedStmt.setInt(4, personalNeedsAccount.getCustomerID());
            preparedStmt.setString(5, personalNeedsAccount.getNeed());
            preparedStmt.execute();
            preparedStmt.close();
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }

    public void delete(PersonalNeedsAccount savingsAccount){
        try{
            String query = "DELETE FROM PNAccounts WHERE IBAN = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, savingsAccount.getIBAN());
            preparedStmt.execute();
            preparedStmt.close();
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }
}
