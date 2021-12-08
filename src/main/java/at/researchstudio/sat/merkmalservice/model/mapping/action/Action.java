package at.researchstudio.sat.merkmalservice.model.mapping.action;

import at.researchstudio.sat.merkmalservice.model.IBuilder;
import at.researchstudio.sat.merkmalservice.model.mapping.action.add.AddAction;
import at.researchstudio.sat.merkmalservice.model.mapping.action.convert.ConvertAction;
import at.researchstudio.sat.merkmalservice.model.mapping.action.delete.DeleteAction;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "__typename")
@JsonSubTypes({
    @JsonSubTypes.Type(value = AddAction.class, name = "AddAction"),
    @JsonSubTypes.Type(value = DeleteAction.class, name = "DeleteAction")
})
public interface Action {

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder implements IBuilder<Action> {
        private IBuilder<? extends Action> builder;

        public AddAction.Builder addAction() {
            this.builder = new AddAction.Builder();
            return (AddAction.Builder) this.builder;
        }

        public DeleteAction.Builder deleteAction() {
            this.builder = new DeleteAction.Builder();
            return (DeleteAction.Builder) this.builder;
        }

        public ConvertAction.Builder convertAction() {
            this.builder = new ConvertAction.Builder();
            return (ConvertAction.Builder) this.builder;
        }

        public Action build() {
            return builder.build();
        }
    }
}
