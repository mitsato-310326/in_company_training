public class KoopaTroopa extends Enemy {
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
}
