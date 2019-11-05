package com.csv;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
@Component
public class CSVReader {

	CSVParser csvParser;
	int badRecords=0, goodRecords=0;
    public String createParser(CustomerRepository customerRepository, String path) {
    	int size=0;
    	List<CSVRecord> records;
        try {
           Reader reader = Files.newBufferedReader(Paths.get(path));
           csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
           records=csvParser.getRecords();
           size=records.size();
           
        } catch (IOException e){
          return "Error reading file in selected directory. "
        		 + "Check if file exists and has the correct format.";
        }
        if (size>0) {
        	ArrayList<Customer> list=new ArrayList<Customer>();
            for (int j=1;j<records.size(); j++) {
            	CSVRecord csvRecord=records.get(j);
            	if (checkRecordData(csvRecord)) {
                Customer customer=new Customer();
                customer.setFirstName(csvRecord.get(0));
                customer.setLastName(csvRecord.get(1));
                customer.setEmail(csvRecord.get(2));
                customer.setSex(csvRecord.get(3));
                customer.setPicture(csvRecord.get(4));
                customer.setPayment(csvRecord.get(5));
                customer.setPrice(csvRecord.get(6));
                customer.setValue1(csvRecord.get(7));
                customer.setValue2(csvRecord.get(8));
                customer.setLocation(csvRecord.get(9));
                list.add(customer);
                goodRecords++;
            	} else {
            	badRecords++;
            	}
            }
            customerRepository.saveAll(list);
            closeParser();
            StringBuilder sb=new StringBuilder();
            sb.append("Data was imported successfully.");
            sb.append("<br>Count of records received: ");
            sb.append(goodRecords+badRecords);
            sb.append(".");
            sb.append("<br>Count of records successful: ");
            sb.append(goodRecords);
            sb.append(".");
            sb.append("<br>Count of records failed: ");
            sb.append(badRecords);
            sb.append(".");
            return sb.toString();
        } else {
        	closeParser();
        	return "No data found in the selected file.";
        }
    }
    public void closeParser() {
    	try {
			csvParser.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    public boolean checkRecordData(CSVRecord record) {
    	for (int i=0;i<10;i++) {
    		if (record.get(i).length()==0) {
    			return false;
    		}
    	}
    	return true;
    }
}
