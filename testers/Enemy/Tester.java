import java.lang.reflect.*;

public class Tester {
    static final java.io.PrintStream OUT = System.out;
    static int p = 0, t = 0;

    static void chk(String n, Object e, Object g) {
        t++; if (e.equals(g)) { p++; OUT.println("OK " + n); }
        else OUT.println("KO " + n + ": expected [" + e + "] got [" + g + "]");
    }
    static void chkB(String n, boolean r) {
        t++; if (r) { p++; OUT.println("OK " + n); }
        else OUT.println("KO " + n);
    }

    public static void main(String[] args) {
        chkB("Enemy is abstract",         Modifier.isAbstract(Enemy.class.getModifiers()));
        chkB("Goomba extends Enemy",      Enemy.class.isAssignableFrom(Goomba.class));
        chkB("KoopaTroopa extends Enemy", Enemy.class.isAssignableFrom(KoopaTroopa.class));

        // {0,1,0} -> Goomba, KoopaTroopa, Goomba
        Enemy[] arr = Enemies.makeEnemyArray(new int[]{0, 1, 0});
        chkB("length == 3",           arr.length == 3);
        chkB("arr[0] is Goomba",      arr[0] instanceof Goomba);
        chkB("arr[1] is KoopaTroopa", arr[1] instanceof KoopaTroopa);
        chkB("arr[2] is Goomba",      arr[2] instanceof Goomba);

        // {1,0} -> KoopaTroopa, Goomba
        Enemy[] arr2 = Enemies.makeEnemyArray(new int[]{1, 0});
        chkB("arr2 length == 2",       arr2.length == 2);
        chkB("arr2[0] is KoopaTroopa", arr2[0] instanceof KoopaTroopa);
        chkB("arr2[1] is Goomba",      arr2[1] instanceof Goomba);

        // Polymorphic stomp via Enemy reference
        chkB("Goomba isAlive before stomp", arr[0].isAlive());
        arr[0].stomp();
        chk("Goomba status after stomp",    "dead", arr[0].getStatus());
        chkB("Goomba isAlive after stomp",  !arr[0].isAlive());

        arr[1].stomp();
        chk("KoopaTroopa status after 1st stomp", "stop", arr[1].getStatus());
        chkB("KoopaTroopa isAlive after stomp",    arr[1].isAlive());

        OUT.println(p + "/" + t + " passed");
        System.exit(p == t ? 0 : 1);
    }
}
