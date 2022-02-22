package at.researchstudio.sat.merkmalservice.utils;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

public class ExcludeInstanceValuesStrategy implements ExclusionStrategy {
    @Override
    public boolean shouldSkipField(FieldAttributes fieldAttributes) {
        return fieldAttributes.getName().equals("instanceValues");
    }

    @Override
    public boolean shouldSkipClass(Class<?> aClass) {
        return false;
    }
}
