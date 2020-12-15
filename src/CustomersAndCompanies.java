import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class CustomersAndCompanies {
    public static void main(String[] args) throws IOException {

        String customers_path = "customers.csv";
        String companies_path = "companies.csv";

        List<Customer> customers = new ArrayList<>();
        List<Company> companies = new ArrayList<>();
        Customer.read_csv(customers, customers_path);
        Company.read_csv(companies, companies_path);

        Map<String, Integer> unique_last_names = new HashMap<>(get_unique_names(customers));
        List<List<String>> unique_last_names_report = getReport(unique_last_names);
        csv_writer("unique_last_names_report.csv", unique_last_names_report);

        Map<String, Integer> companies_workers = new HashMap<>(companies_workers(customers,companies));
        List<List<String>> companies_workers_report = getReport(companies_workers);
        csv_writer("companies_workers_report.csv", companies_workers_report);

        Map<String, String> oldest_workers = new HashMap<>(get_oldest_worker(customers, companies));
        List<List<String>> oldest_workers_report = getReportStrStr(oldest_workers);
        csv_writer("oldest_workers_report.csv", oldest_workers_report);

        Map<String, String> second_oldest_worker = new HashMap<>(get_second_oldest_worker(customers, companies));
        List<List<String>> second_oldest_worker_report = getReportStrStr(second_oldest_worker);
        csv_writer("second_oldest_worker_report.csv", second_oldest_worker_report);

        try(FileWriter csv_writer = new FileWriter("not_in_IBM.csv")){
            for(String customer: not_in_IBM(customers,companies)){
                csv_writer.append(String.join(";", customer));
                csv_writer.append("\n");
            }
        }
    }

    private static List<List<String>> getReportStrStr(Map<String, String> oldest_workers) {
        List<List<String>> oldest_workers_report = new ArrayList<>();

        for (Map.Entry<String, String> item : oldest_workers.entrySet()
        ) {
            List<String> row = new ArrayList<>();
            row.add(item.getKey());
            row.add(item.getValue().toString());
            oldest_workers_report.add(row);
        }
        return oldest_workers_report;
    }

    private static List<List<String>> getReport(Map<String, Integer> unique_last_names) {
        List<List<String>> unique_last_names_report = new ArrayList<>();

        for (Map.Entry<String, Integer> item : unique_last_names.entrySet()
        ) {
            List<String> row = new ArrayList<>();
            row.add(item.getKey());
            row.add(item.getValue().toString());
            unique_last_names_report.add(row);
        }
        return unique_last_names_report;
    }

    private static void csv_writer(String path, List<List<String>> rows) throws IOException {
        try(FileWriter csv_writer = new FileWriter(path)){
            for (List<String> row : rows) {
                csv_writer.append(String.join(";", row));
                csv_writer.append("\n");
            }
        }
    }
    private static List<String> not_in_IBM(List<Customer> customers, List<Company> companies){
        int ibm_id = -1;
        List<String> not_in_ibm = new ArrayList<>();
        for (Company company: companies
             ) {
                if(company.getCompany_name().equals("IBM")){
                    ibm_id= company.getId();
                    break;
                }
        }
        for(Customer customer:customers){
            if(customer.getCompany_id()!=ibm_id){
                not_in_ibm.add(customer.getFirst_name()+" "+customer.getLast_name());
            }
        }
        return not_in_ibm;
    }
    private static Map<String, String>  get_second_oldest_worker(List<Customer> customers, List<Company> companies){
        Map<String, String> workers = new HashMap<String, String>();
        for (Company company: companies
        ) {
            List<Customer> company_customers = new ArrayList<>();
            for(Customer customer:customers){
                if (company.getId()==customer.getCompany_id()){
                    company_customers.add(customer);
                }
            }
            if(company_customers.size()>1){
                Collections.sort(company_customers);
                workers.put(company.getCompany_name(),
                        company_customers.get(company_customers.size()-2).getFirst_name()+" "+
                                company_customers.get(company_customers.size()-2).getLast_name());
            }
            else if (company_customers.size()>0){
                workers.put(company.getCompany_name(),
                        company_customers.get(0).getFirst_name()+" "+
                                company_customers.get(0).getLast_name());
            }
        }
        return workers;
    }

    private static Map<String, String>  get_oldest_worker(List<Customer> customers, List<Company> companies){
        Map<String, String> workers = new HashMap<String, String>();
        for (Company company: companies
             ) {
                List<Customer> company_customers = new ArrayList<>();
                for(Customer customer:customers){
                    if (company.getId()==customer.getCompany_id()){
                        company_customers.add(customer);
                    }
                }
                if(company_customers.size()>0){
                        Collections.sort(company_customers);
                        workers.put(company.getCompany_name(),
                                company_customers.get(company_customers.size()-1).getFirst_name()+" "+
                                company_customers.get(company_customers.size()-1).getLast_name());
            }
        }
        return workers;

    }
    private static Map<String, Integer>  companies_workers(List<Customer> customers, List<Company> companies){
        Map<String, Integer> workers = new HashMap<String, Integer>();
        for (Customer customer: customers
             ) {
            String company_name=" ";
            for (Company company: companies
                 ) {
                if (company.getId()==customer.getCompany_id()){
                    company_name = company.getCompany_name();
                }
            }
            if (!company_name.equals(" ")){
                if(workers.containsKey(company_name)){
                    workers.put(company_name, workers.get(company_name)+1);
    
                }
                else{
                    workers.put(company_name,1);
                }
            }
        }
        return workers;
    }

    private static Map<String, Integer>  get_unique_names(List<Customer> customers) {
        Map<String, Integer> unique_last_names = new HashMap<String, Integer>();
        for (Customer customer: customers) {
            if(unique_last_names.containsKey(customer.getLast_name())){
                unique_last_names.put(customer.getLast_name(), unique_last_names.get(customer.getLast_name())+1);

            }
            else{
                unique_last_names.put(customer.getLast_name(),1);
            }
        }
        return unique_last_names;
    }


}
