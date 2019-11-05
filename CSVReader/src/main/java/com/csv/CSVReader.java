package com.csv;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Component
public class CSVReader {

	CSVParser csvParser;
	int badRecords=0, goodRecords=0;
	String CSV_FILE;
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    public String createParser(CustomerRepository customerRepository, String path) {
    	int size=0;
    	List<CSVRecord> records;
        try {
           Reader reader = Files.newBufferedReader(Paths.get(path));
           csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
           getDirectory(path);
           records=csvParser.getRecords();
           size=records.size();
           
        } catch (IOException e){
          return "Error reading file in selected directory. "
        		 + "Check if file exists and has the correct format.";
        }
        if (size>0) {
        	ArrayList<Customer> list=new ArrayList<Customer>();
        	ArrayList<CSVRecord> badRecordsList=new ArrayList<CSVRecord>();
            for (int j=1;j<records.size(); j++) {
            	CSVRecord csvRecord=records.get(j);
            	if (checkRecordData(csvRecord)) {
                Customer customer=new Customer();
                customer.setFirstName(addQuotes(csvRecord.get(0)));
                customer.setLastName(addQuotes(csvRecord.get(1)));
                customer.setEmail(addQuotes(csvRecord.get(2)));
                customer.setSex(addQuotes(csvRecord.get(3)));
                customer.setPicture(addQuotes(csvRecord.get(4)));
                customer.setPayment(addQuotes(csvRecord.get(5)));
                customer.setPrice(addQuotes(csvRecord.get(6)));
                customer.setValue1(addQuotes(csvRecord.get(7)));
                customer.setValue2(addQuotes(csvRecord.get(8)));
                customer.setLocation(addQuotes(csvRecord.get(9)));
                list.add(customer);
                goodRecords++;
            	} else {
            	badRecords++;
            	badRecordsList.add(csvRecord);
            	}
            }
            customerRepository.saveAll(list);
            Thread thread=new Thread(new Runnable() {
            	public void run() {
            	saveBadRecords(badRecordsList);
            	}
            });
            thread.start();
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
            LOGGER.info(sb.toString());
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
    public void saveBadRecords(ArrayList<CSVRecord> list) {
    		CSVPrinter csvPrinter=null;
    		try {
                BufferedWriter writer = Files.newBufferedWriter(Paths.get(CSV_FILE));
    			
                csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                        .withHeader("A", "B", "C", "D", "E", "F", "G", "H", "I", "J"));

            		for (int i=0; i<list.size(); i++) {
                    csvPrinter.printRecord(list.get(i));
            		}
                    csvPrinter.flush();     
                    csvPrinter.close();
            	
    		} catch (IOException e){
    			
    			LOGGER.error("Error saving file with bad records.");
            
            } 
    		
    	}
    public void getDirectory(String path) {
    	String directory=Paths.get(path).getParent().toString();
        Date date = new Date();
        String strDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
        CSV_FILE=directory+"\\"+"bad-data "+strDate+".csv";
    	}
    public String addQuotes(String s) {
    	if (s.contains(",")) {
    		return "\""+s+"\"";
    		} else {
    		return s;
    		}
    	}
	}
