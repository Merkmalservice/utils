package at.researchstudio.sat.merkmalservice.model.qudt;

import java.util.*;
import org.eclipse.rdf4j.model.IRI;

public class Unit {
    private final IRI iri;
    private final IRI prefix;
    private final Double conversionMultiplier;
    private final Double conversionOffset;
    private final Set<IRI> quantityKinds;
    private final String symbol;
    private final Set<LangString> labels;
    private final IRI scalingOf;
    private final IRI dimensionVector;

    public Unit(
            IRI iri,
            IRI prefix,
            IRI scalingOf,
            IRI dimensionVector,
            Double conversionMultiplier,
            Double conversionOffset,
            Set<IRI> quantityKinds,
            String symbol,
            Set<LangString> labels) {
        this.iri = iri;
        this.prefix = prefix;
        this.scalingOf = scalingOf;
        this.dimensionVector = dimensionVector;
        this.conversionMultiplier = conversionMultiplier;
        this.conversionOffset = conversionOffset;
        this.quantityKinds = new HashSet<>(quantityKinds);
        this.symbol = symbol;
        this.labels = labels;
    }

    public Unit(
            IRI iri,
            IRI prefix,
            IRI scalingOf,
            IRI dimensionVector,
            Double conversionMultiplier,
            Double conversionOffset,
            String symbol) {
        this.iri = iri;
        this.prefix = prefix;
        this.scalingOf = scalingOf;
        this.dimensionVector = dimensionVector;
        this.conversionMultiplier = conversionMultiplier;
        this.conversionOffset = conversionOffset;
        this.quantityKinds = new HashSet<>();
        this.symbol = symbol;
        this.labels = new HashSet<>();
    }

    public IRI getIri() {
        return iri;
    }

    public Optional<IRI> getPrefix() {
        return Optional.ofNullable(prefix);
    }

    public Optional<IRI> getScalingOf() {
        return Optional.ofNullable(scalingOf);
    }

    public Optional<IRI> getDimensionVector() {
        return Optional.ofNullable(dimensionVector);
    }

    public Optional<Double> getConversionMultiplier() {
        return Optional.ofNullable(conversionMultiplier);
    }

    public Optional<Double> getConversionOffset() {
        return Optional.ofNullable(conversionOffset);
    }

    public Set<IRI> getQuantityKinds() {
        return Collections.unmodifiableSet(quantityKinds);
    }

    public Optional<String> getSymbol() {
        return Optional.ofNullable(symbol);
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

    public void addQuantityKind(IRI quantityKind) {
        this.quantityKinds.add(quantityKind);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Unit unit = (Unit) o;
        return Objects.equals(iri, unit.iri);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iri);
    }

    @Override
    public String toString() {
        return "unit:" + iri.toString().replaceAll(".+/([^/]+)", "$1");
    }
}
