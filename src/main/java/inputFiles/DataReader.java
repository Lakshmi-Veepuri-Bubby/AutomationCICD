package inputFiles;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class DataReader {

    public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {

        //Another way to using JSON
        //Create a JSON file("
        //Convert Json to string using file utils
        String jsonInput= FileUtils.readFileToString(new File(filePath));
        //convert string JSON to Hashmap using Jackson Databind dependency adding to pom.xml
        ObjectMapper mapper= new ObjectMapper();
        List<HashMap<String,String>> data=mapper.readValue(jsonInput, new TypeReference<List<HashMap<String,String>>>() {
        });
        return data;
    }
}
