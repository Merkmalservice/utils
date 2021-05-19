package at.researchstudio.sat.merkmalservice.utils;


import at.researchstudio.sat.merkmalservice.model.Feature;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Utils {
    public static void writeToJson(String outputFileName, List<Feature> extractedFeatures) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FileWriter jsonFileWriter = new FileWriter(outputFileName);
        gson.toJson(extractedFeatures, jsonFileWriter);
    }
}
