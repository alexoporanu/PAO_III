import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Audit {
    FileWriter auditWriter;

    private DateTimeFormatter helper = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public Audit() {
        try{
            this.auditWriter = new FileWriter("CSVfiles/audit.csv");
        }
        catch (IOException e){
            System.out.println(e.toString());
        }
    }


    public void CSVPrint(String command) throws IOException {
        String toPrint = "";
        toPrint += command + ",";
        toPrint += helper.format(LocalDateTime.now());
        auditWriter.append(toPrint);
        auditWriter.append("\n");
        auditWriter.flush();
    }

}
