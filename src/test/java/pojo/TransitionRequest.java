package pojo;

public class TransitionRequest {

    private Transition transition;

    public TransitionRequest() {}

    public Transition getTransition() {
        return transition;
    }

    public void setTransition(Transition transition) {
        this.transition = transition;
    }

    public static class Transition {
        private String id;

        public Transition() {}

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}