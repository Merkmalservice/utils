package at.researchstudio.sat.merkmalservice.model.qudt;

import java.util.Collections;
import java.util.Set;

public class Quantity {
    Set<QuantityValue> quantityValues;

    public Quantity(Set<QuantityValue> quantityValues) {
        this.quantityValues = quantityValues;
    }

    public Set<QuantityValue> getQuantityValues() {
        return Collections.unmodifiableSet(quantityValues);
    }
}
