package at.researchstudio.sat.merkmalservice.model.qudt;

import java.util.Objects;
import java.util.Optional;

public class LangString {
    private final String string;
    private final String languageTag;

    public LangString(String string) {
        this(string, null);
    }

    public LangString(String string, String languageTag) {
        Objects.requireNonNull(string);
        this.string = string;
        this.languageTag = languageTag;
    }

    public String getString() {
        return string;
    }

    public Optional<String> getLanguageTag() {
        return Optional.ofNullable(languageTag);
    }

    @Override
    public String toString() {
        return "'" + string + "'" + (languageTag == null ? "" : "@" + languageTag);
    }
}
