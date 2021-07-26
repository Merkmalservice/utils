package at.researchstudio.sat.merkmalservice.utils;

import at.researchstudio.sat.merkmalservice.model.Feature;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Utils {
    public static void writeToJson(
            String outputFileName, List<Feature> extractedFeatures, boolean withDescription)
            throws IOException {

        try (FileWriter jsonFileWriter = new FileWriter(outputFileName)) {
            if (withDescription) {
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                gson.toJson(extractedFeatures, jsonFileWriter);
            } else {
                Gson gson =
                        new GsonBuilder()
                                .setExclusionStrategies(new ExcludeDescriptionStrategy())
                                .setPrettyPrinting()
                                .create();
                gson.toJson(extractedFeatures, jsonFileWriter);
            }
        } catch (IOException ioException) {
            throw ioException;
        }
    }

    public static void writeToJson(String outputFileName, List<Feature> extractedFeatures)
            throws IOException {
        writeToJson(outputFileName, extractedFeatures, true);
    }
}
