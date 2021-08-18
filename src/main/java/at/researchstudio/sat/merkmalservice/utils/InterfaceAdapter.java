package at.researchstudio.sat.merkmalservice.utils;

import at.researchstudio.sat.merkmalservice.model.*;
import com.google.gson.*;
import java.lang.reflect.Type;
import java.util.Set;

public class InterfaceAdapter<T> implements JsonDeserializer<T> {

    @Override
    public final T deserialize(
            final JsonElement elem,
            final Type interfaceType,
            final JsonDeserializationContext context)
            throws JsonParseException {
        if (interfaceType.equals(EnumFeature.OptionValue.class)) {
            final JsonObject member = (JsonObject) elem;

            return context.deserialize(member, typeForOptionValue(member));
        } else {
            final JsonObject member = (JsonObject) elem;
            final JsonElement type = get(member, "featureType");

            return context.deserialize(member, typeForFeature(type));
        }
    }

    private Type typeForOptionValue(final JsonObject typeElem) {
        Set<String> keys = typeElem.keySet();

        if (keys.size() == 1) {
            String optionTypeKey = keys.iterator().next();
            switch (optionTypeKey) {
                case "stringValue":
                    return EnumFeature.MEStringValue.class;
                case "floatValue":
                    return EnumFeature.MEFloatValue.class;
                case "name":
                    return EnumFeature.MEBooleanValue.class;
                case "integerValue":
                    return EnumFeature.MEIntegerValue.class;
                default:
                    throw new JsonParseException(
                            "Could not find optiontype for String: " + optionTypeKey);
            }
        }
        throw new JsonParseException("Could not find optiontype typeElem keyset not of size 1");
    }

    private Type typeForFeature(final JsonElement typeElem) {
        final JsonElement typeString = get(typeElem.getAsJsonObject(), "type");
        switch (typeString.getAsString()) {
            case "BOOLEAN":
                return BooleanFeature.class;
            case "ENUMERATION":
                return EnumFeature.class;
            case "NUMERIC":
                return NumericFeature.class;
            case "REFERENCE":
                return ReferenceFeature.class;
            case "STRING":
                return StringFeature.class;
            default:
                throw new JsonParseException("Could not find type for String: " + typeString);
        }
    }

    private JsonElement get(final JsonObject wrapper, final String memberName) {
        final JsonElement elem = wrapper.get(memberName);

        if (elem == null) {
            throw new JsonParseException("no '" + memberName + "' member found in json file.");
        }
        return elem;
    }
}
