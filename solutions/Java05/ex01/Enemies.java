public class Enemies {
    public static Enemy[] makeEnemyArray(int[] ids) {
        Enemy[] result = new Enemy[ids.length];
        for (int i = 0; i < ids.length; i++) {
            result[i] = (ids[i] == 0) ? new Goomba() : new KoopaTroopa();
        }
        return result;
    }
}
