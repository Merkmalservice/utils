package at.researchstudio.sat.merkmalservice.model.qudt;

import java.util.*;
import org.eclipse.rdf4j.model.IRI;

public class QuantityKind {
    private final IRI iri;
    private final Set<LangString> labels;
    private final Set<IRI> applicableUnits;
    private final Set<IRI> broaderQuantityKinds;
    private final IRI dimensionVector;
    private final String symbol;

    public QuantityKind(
            IRI iri,
            Set<LangString> labels,
            Set<IRI> applicableUnits,
            Set<IRI> broaderQuantityKinds,
            IRI dimensionVector,
            String symbol) {
        this.iri = iri;
        this.labels = labels;
        this.applicableUnits = new HashSet<>(applicableUnits);
        this.broaderQuantityKinds = new HashSet<>(broaderQuantityKinds);
        this.dimensionVector = dimensionVector;
        this.symbol = symbol;
    }

    public QuantityKind(IRI iri, IRI dimensionVector, String symbol) {
        this.iri = iri;
        this.dimensionVector = dimensionVector;
        this.symbol = symbol;
        this.applicableUnits = new HashSet<>();
        this.broaderQuantityKinds = new HashSet<>();
        this.labels = new HashSet<>();
    }

    public IRI getIri() {
        return iri;
    }

    public Set<IRI> getApplicableUnits() {
        return Collections.unmodifiableSet(applicableUnits);
    }

    public Set<IRI> getBroaderQuantityKinds() {
        return Collections.unmodifiableSet(broaderQuantityKinds);
    }

    public Optional<IRI> getDimensionVector() {
        return Optional.ofNullable(dimensionVector);
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

    public void addApplicableUnit(IRI unit) {
        Objects.requireNonNull(unit);
        this.applicableUnits.add(unit);
    }

    public void addBroaderQuantityKind(IRI quantitkyKind) {
        Objects.requireNonNull(quantitkyKind);
        this.broaderQuantityKinds.add(quantitkyKind);
    }
}
