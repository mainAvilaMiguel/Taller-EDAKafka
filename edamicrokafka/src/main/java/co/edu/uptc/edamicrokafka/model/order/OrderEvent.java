package co.edu.uptc.edamicrokafka.model.order;

public class OrderEvent {
    private EventOrderType eventType;
    private String data;

    public EventOrderType getEventType() { return eventType; }
    public void setEventType(EventOrderType eventType) { this.eventType = eventType; }
    public String getData() { return data; }
    public void setData(String data) { this.data = data; }
}
