public class Hornet extends Entity {


    Hornet(int x, int y) {
        this.x = x;
        this.y = y;
    }

    private void attak() {
        //boolean hasattacked = false;
        for (int i = getY() - 1; i < getY() + 1; i++) {
            if (getY() - 1 < 0)
                continue;
            if (getY() + 1 > map.length)
                continue;
            for (int j = getX() - 1; j < getX() + 1; j++) {
                if (getX() - 1 < 0)
                    continue;
                if (getX() + 1 > map[i].length)
                    continue;

                if (map[i][j] == 1 ) {
                    map[i][j] = 0;
                    //hasattacked = true;
                }
            }
        }
    }

    public int[][] update(int map[][]) {
        this.map = map;
        map[getY()][getX()] = 0;
        movement(map);
        map[getY()][getX()] = 3;
        attak();
        return map;
    }
}