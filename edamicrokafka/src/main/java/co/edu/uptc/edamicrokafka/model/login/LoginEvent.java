package co.edu.uptc.edamicrokafka.model.login;

public class LoginEvent {
    private EventLoginType eventType;
    private String data;

    public EventLoginType getEventType() { return eventType; }
    public void setEventType(EventLoginType eventType) { this.eventType = eventType; }
    public String getData() { return data; }
    public void setData(String data) { this.data = data; }
}
