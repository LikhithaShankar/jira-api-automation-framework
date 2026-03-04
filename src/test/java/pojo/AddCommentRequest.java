package pojo;

import java.util.List;

public class AddCommentRequest {

    private Body body;

    public AddCommentRequest() {}

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public static class Body {
        private String type;
        private int version;
        private List<Content> content;

        public Body() {}

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }

        public List<Content> getContent() {
            return content;
        }

        public void setContent(List<Content> content) {
            this.content = content;
        }
    }

    public static class Content {
        private String type;
        private List<Text> content;

        public Content() {}

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public List<Text> getContent() {
            return content;
        }

        public void setContent(List<Text> content) {
            this.content = content;
        }
    }

    public static class Text {
        private String text;
        private String type;

        public Text() {}

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}