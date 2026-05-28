public class Goomba {
    private String status;
    private boolean alive;

    public Goomba() {
        this.status = "walking";
        this.alive = true;
    }

    public void stomp() {
        this.status = "dead";
        this.alive = false;
    }

    public String getStatus()  { return status; }
    public boolean isAlive()   { return alive; }
}
