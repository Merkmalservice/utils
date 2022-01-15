package at.researchstudio.sat.merkmalservice.model.qudt;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import org.eclipse.rdf4j.model.IRI;

public class Prefix {
    private final IRI iri;
    private final double multiplier;
    private final String symbol;
    private final String ucumCode;
    private final Set<LangString> labels;

    public Prefix(IRI iri, double multiplier, String symbol, Set<LangString> labels) {
        this.iri = iri;
        this.multiplier = multiplier;
        this.symbol = symbol;
        this.labels = labels;
        this.ucumCode = null;
    }

    public Prefix(IRI iri, double multiplier, String symbol, String ucumCode) {
        this.iri = iri;
        this.multiplier = multiplier;
        this.symbol = symbol;
        this.ucumCode = ucumCode;
        this.labels = new HashSet<>();
    }

    public IRI getIri() {
        return iri;
    }

    public double getMultiplier() {
        return multiplier;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getUcumCode() {
        return ucumCode;
    }

    public void addLabel(LangString langString) {
        this.labels.add(langString);
    }

    public Set<LangString> getLabels() {
        return labels;
    }

    public Optional<LangString> getLabelForLanguageTag(String languageTag) {
        if (languageTag == null) {
            return labels.stream().filter(s -> s.getLanguageTag().isEmpty()).findFirst();
        } else {
            return labels.stream()
                    .filter(s -> languageTag.equals(s.getLanguageTag().orElse(null)))
                    .findFirst();
        }
    }

    public boolean hasLabel(String label) {
        return labels.stream().anyMatch(s -> s.getString().equals(label));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prefix prefix = (Prefix) o;
        return Double.compare(prefix.multiplier, multiplier) == 0
                && Objects.equals(iri, prefix.iri)
                && Objects.equals(symbol, prefix.symbol)
                && Objects.equals(ucumCode, prefix.ucumCode)
                && Objects.equals(labels, prefix.labels);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iri, multiplier, symbol, ucumCode, labels);
    }

    @Override
    public String toString() {
        return "prefix:" + iri.toString().replaceAll(".+/", "");
    }
}
