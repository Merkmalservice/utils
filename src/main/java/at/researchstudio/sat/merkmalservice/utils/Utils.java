package at.researchstudio.sat.merkmalservice.utils;

import at.researchstudio.sat.merkmalservice.model.EnumFeature;
import at.researchstudio.sat.merkmalservice.model.Feature;
import at.researchstudio.sat.merkmalservice.model.JsonModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
    public static final Pattern UNICODE_REPLACEMENT_PATTERN =
            Pattern.compile("\\\\X2\\\\(.*?)\\\\X0\\\\", Pattern.CASE_INSENSITIVE);

    public static void writeToJson(
            String outputFileName, List<Feature> extractedFeatures, boolean withDescription)
            throws IOException {
        try (FileWriter jsonFileWriter = new FileWriter(outputFileName, StandardCharsets.UTF_8)) {
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

    public static List<Feature> readFromJson(File file) throws FileNotFoundException {
        // New JSON Schema Version
        /** { features: [...], featureGroups: [...] } */
        try {
            Gson gson =
                    new GsonBuilder()
                            .registerTypeAdapter(Feature.class, new InterfaceAdapter<Feature>())
                            .registerTypeAdapter(
                                    EnumFeature.OptionValue.class,
                                    new InterfaceAdapter<EnumFeature.OptionValue>())
                            .create();
            JsonReader reader = new JsonReader(new FileReader(file.getPath()));
            JsonModel jsonModel = gson.fromJson(reader, JsonModel.class);
            return jsonModel.getFeatures();
        } catch (Exception e) {
            // Fallback: Old Version
            /** [features] */
            Gson gson =
                    new GsonBuilder()
                            .registerTypeAdapter(Feature.class, new InterfaceAdapter<Feature>())
                            .registerTypeAdapter(
                                    EnumFeature.OptionValue.class,
                                    new InterfaceAdapter<EnumFeature.OptionValue>())
                            .create();
            JsonReader reader = new JsonReader(new FileReader(file.getPath()));
            Feature[] features = gson.fromJson(reader, Feature[].class);
            return Arrays.asList(features);
        }
    }

    public static void writeToJson(String outputFileName, List<Feature> extractedFeatures)
            throws IOException {
        writeToJson(outputFileName, extractedFeatures, true);
    }

    /**
     * Converts any given String occuring in an ifc-file to utf-8 (replace \X2\*\X0\ with the
     * correct special character
     */
    public static String convertIFCStringToUtf8(String s) {
        Matcher matcher = UNICODE_REPLACEMENT_PATTERN.matcher(s);
        return matcher.replaceAll(
                replacer -> {
                    String hexValue = replacer.group().substring(4, 8);
                    int charValue = Integer.parseInt(hexValue, 16);
                    return Character.toString((char) charValue);
                });
    }
}
