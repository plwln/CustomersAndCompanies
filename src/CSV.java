import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CSV {
    public static void make_random_csv_content() throws IOException {
        Random rnd = new Random();
        String[] first_names = new String[]{"John", "Alex", "Alice", "Kate", "Stephen"};
        String[] last_names = new String[]{"Doe", "Johnson", "Jordan", "Green", "Irving"};

        FileWriter csv_writer = new FileWriter("customers.csv");
        for(int i=0; i<100; i++){
            csv_writer.append(i+";"+first_names[rnd.nextInt(first_names.length)]+
                    ";"+last_names[rnd.nextInt(last_names.length)]+";"+(rnd.nextInt(57)+18)+";"+
                    rnd.nextInt(5)+"\n");
        }
        csv_writer.flush();
        csv_writer.close();
    }

    public static void main(String[] args) throws IOException {
        make_random_csv_content();
    }
}
