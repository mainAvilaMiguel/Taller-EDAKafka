package co.edu.uptc.edamicrokafka.model.customer;

public class CustomerEvent {
    private EventCustomerType eventType;
    private String data;


    public EventCustomerType getEventType() { return eventType; }
    public void setEventType(EventCustomerType eventType) { this.eventType = eventType; }
    public String getData() { return data; }
    public void setData(String data) { this.data = data; }
}
