package pojo;

public class UpdateSummaryRequest {

    private Fields fields;

    public UpdateSummaryRequest() {}

    public Fields getFields() {
        return fields;
    }

    public void setFields(Fields fields) {
        this.fields = fields;
    }

    public static class Fields {

        private String summary;

        public Fields() {}

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }
    }
}
