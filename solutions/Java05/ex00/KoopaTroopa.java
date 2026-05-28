public class KoopaTroopa {
    private String status;
    private boolean alive = true;
    private boolean reversed = false;

    public KoopaTroopa() {
        this.status = "walking";
    }

    public void stomp() {
        if (!reversed) {
            reversed = true;
            status = "stop";
        } else if (status.equals("stop")) {
            status = "spin";
        } else {
            status = "stop";
        }
    }

    public String getStatus()  { return status; }
    public boolean isAlive()   { return alive; }
}
