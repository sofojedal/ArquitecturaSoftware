package Modelo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CSVModel {
    private String csvFile;
    private String csvSeparator;
    private StringBuilder data;
    private String[] columnHeaders;

    public CSVModel(String csvFile) {
        this.csvFile = csvFile;
        this.csvSeparator = ";";
        this.data = new StringBuilder();
        readCSV();
    }

    private void readCSV() {
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            if ((line = br.readLine()) != null) {
                columnHeaders = line.split(csvSeparator);
            }
            while ((line = br.readLine()) != null) {
                String[] rowData = line.split(csvSeparator);
                for (String item : rowData) {
                    data.append(item).append(" | ");
                }
                data.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getData() {
        return data.toString();
    }

    public String[] getColumnHeaders() {
        return columnHeaders;
    }
}



