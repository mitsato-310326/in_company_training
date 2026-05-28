public class Goomba extends Enemy {
    public Goomba() {
        this.status = "walking";
        this.alive = true;
    }

    public void stomp() {
        this.status = "dead";
        this.alive = false;
    }
}
