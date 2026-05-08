package Model;

public class Mesa {

    private int id;
    private String status;

    public Mesa() {}

    public Mesa(int id, String status) {
        this.id = id;
        this.status = status;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}