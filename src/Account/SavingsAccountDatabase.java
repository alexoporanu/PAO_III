package Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class SavingsAccountDatabase {
        Connection connection;

        public SavingsAccountDatabase(Connection connection) {
            this.connection = connection;
        }

        public List<SavingsAccount> read(){
            List<SavingsAccount> savingsAccounts = new ArrayList<>();
            try{
                Statement statement = connection.createStatement();
                ResultSet result = statement.executeQuery("SELECT * FROM SAccounts");
                while(result.next()) {
                    SavingsAccount current = new SavingsAccount(result);
                    savingsAccounts.add(current);
                }
                statement.close();
            }catch (Exception e){
                System.out.println(e.toString());
            }
            return savingsAccounts;
        }

        public void create(SavingsAccount savingsAccount){
            try{
                String query = "INSERT INTO SAccounts (IBAN, balance, currency, customerId, interestRate) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement preparedStmt = connection.prepareStatement(query);
                preparedStmt.setString(1, savingsAccount.getIBAN());
                preparedStmt.setInt(2, savingsAccount.getBalance());
                preparedStmt.setString(3, savingsAccount.getCurrency());
                preparedStmt.setInt(4, savingsAccount.getCustomerID());
                preparedStmt.setInt(5, savingsAccount.getInterestRate());
                preparedStmt.execute();
                preparedStmt.close();
            }catch (Exception e){
                System.out.println(e.toString());
            }
        }

        public void delete(SavingsAccount savingsAccount){
            try{
                String query = "DELETE FROM SAccounts WHERE IBAN = ?";
                PreparedStatement preparedStmt = connection.prepareStatement(query);
                preparedStmt.setString(1, savingsAccount.getIBAN());
                preparedStmt.execute();
                preparedStmt.close();
            }catch (Exception e){
                System.out.println(e.toString());
            }
        }
}
