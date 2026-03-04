package pojo;

public class CreateIssueRequest {

    private IssueFields fields;

    public CreateIssueRequest() {}  

    public CreateIssueRequest(IssueFields fields) {
        this.fields = fields;
    }

    public IssueFields getFields() {
        return fields;
    }

    public void setFields(IssueFields fields) {
        this.fields = fields;
    }
}