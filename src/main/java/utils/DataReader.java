package utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class DataReader {

    public static Object[][] readCsv(String fileName) {
        List<String[]> rows = new ArrayList<>();

        try {
            InputStream stream = DataReader.class.getClassLoader().getResourceAsStream(fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            String line = reader.readLine();

            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    rows.add(line.split(","));
                }
            }
            reader.close();
        } catch (Exception e) {
            throw new RuntimeException("Could not read file: " + fileName, e);
        }

        Object[][] data = new Object[rows.size()][];
        for (int i = 0; i < rows.size(); i++) {
            data[i] = rows.get(i);
        }
        return data;
    }
}
