package Card;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class Generators {
    public static int cvvGenerator() {
        var rand = new Random();
        return 100 + rand.nextInt(899);
    }

    public static String numberGenerator() {
        Random rnd = new Random();
        String id_1 = String.format("%04d", rnd.nextInt(10000));
        String id_2 = String.format("%04d", rnd.nextInt(10000));
        String id_3 = String.format("%04d", rnd.nextInt(10000));
        String id_4 = String.format("%04d", rnd.nextInt(10000));
        return id_1 + " " + id_2 + " " + id_3 + " " + id_4;
    }

    public static Date dateGenerator() {
        Random rnd = new Random();
        int randomYear = 2022 + rnd.nextInt(10);
        int dayOfYear = rnd.nextInt(365);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, randomYear);
        calendar.set(Calendar.DAY_OF_YEAR, dayOfYear);
        Date randomDate = calendar.getTime();
        return randomDate;
    }

    public static Card randomCardGenerator() {
        int randomCVV = cvvGenerator();
        String randomNumber = numberGenerator();
        Date randomDate = dateGenerator();
        return new Card(randomCVV, randomDate, randomNumber);
    }
}
