import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class Company{

    private int id;
    private String company_name;

    public Company(int id, String company_name){
        setId(id);
        setCompany_name(company_name);
    }

    public static void read_csv(List<Company> lst, String path){
        try(BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while((line = reader.readLine())!=null){
                String[] fields = line.split(";");
                lst.add(new Company(Integer.parseInt(fields[0]),fields[1]));
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        } ;
    }

    public int getId() {
        return id;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }
}
