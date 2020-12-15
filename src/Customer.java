import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

public class Customer implements Comparable<Customer>{

    private int id;
    private String first_name;
    private String last_name;
    private int age;
    private int company_id;

    public Customer(int id, String first_name, String last_name, int age, int company_id){
        setId(id);
        setFirst_name(first_name);
        setLast_name(last_name);
        setAge(age);
        setCompany_id(company_id);
    }

    public static void read_csv(List<Customer> lst, String path){
        try(BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while((line = reader.readLine())!=null){
                String[] fields = line.split(";");
                lst.add(new Customer(Integer.parseInt(fields[0]),fields[1],fields[2],Integer.parseInt(fields[3]),
                        Integer.parseInt(fields[4])));
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        } ;
    }



    public int getAge() {
        return age;
    }

    public int getCompany_id() {
        return company_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public int getId() {
        return id;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    @Override
    public int compareTo(Customer customer) {
        return Integer.compare(getAge(), customer.getAge());
    }
}
