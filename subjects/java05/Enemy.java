public abstract class Enemy {
    protected String status;
    protected boolean alive = true;

    public abstract void stomp();

    public String getStatus() { return status; }
    public boolean isAlive()  { return alive; }
}
