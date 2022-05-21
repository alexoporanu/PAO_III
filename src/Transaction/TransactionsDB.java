package Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class TransactionsDB {
    Connection connection;

    public TransactionsDB(Connection connection){
        this.connection = connection;
    }

    public List<Transaction> read(){
        List<Transaction> transactions = new ArrayList<>();
        try{
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM Transactions");
            while(result.next()) {
                Transaction current = new Transaction(result);
                transactions.add(current);
            }
            statement.close();
        }catch (Exception e){
            System.out.println(e.toString());
        }
        return transactions;
    }

    public void create(Transaction transaction){
        try{
            String query = "INSERT INTO Transactions (moneyAmount, currency, sourceIBAN, destIBAN, date) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setDouble(1, transaction.getMoneyAmount());
            preparedStmt.setString(2, transaction.getCurrency());
            preparedStmt.setString(3, transaction.getSourceIBAN());
            preparedStmt.setString(4, transaction.getDestIBAN());
            preparedStmt.setString(5, (new SimpleDateFormat("yyyy-MM-dd h:m:s")).format(transaction.getDate()));
            preparedStmt.execute();
            preparedStmt.close();
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }

    public void delete(Transaction transaction){
        try{
            String query = "DELETE FROM Transactions WHERE sourceIBAN = ? AND destIBAN = ? AND currency = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, transaction.getSourceIBAN());
            preparedStmt.setString(2, transaction.getDestIBAN());
            preparedStmt.setString(3, transaction.getCurrency());
            preparedStmt.execute();
            preparedStmt.close();
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }


}
