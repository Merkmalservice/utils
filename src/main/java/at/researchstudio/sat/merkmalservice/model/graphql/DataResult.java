package at.researchstudio.sat.merkmalservice.model.graphql;

public class DataResult {
    private GraphqlResult data;

    public DataResult() {}

    public DataResult(GraphqlResult data) {
        this.data = data;
    }

    public GraphqlResult getData() {
        return data;
    }

    public void setData(GraphqlResult data) {
        this.data = data;
    }
}
