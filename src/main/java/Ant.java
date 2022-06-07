public class Ant extends Entity {

    private boolean hasFood = false;


    Ant(int x, int y) {
        this.x = x;
        this.y = y;
    }


    // 0 0 0
    // 0 1 0
    // 0 0 0
    private void eat() {
        for(int i = getY() - 1; i < getY() + 1; i++) {
            if(getY() - 1 < 0)
                continue;
            if (getY() + 1  > map.length)
                continue;
            for(int j = getX() - 1; j < getX() + 1; j++){
                if (getX() - 1 < 0)
                    continue;
                if (getX() + 1> map[i].length)
                    continue;

                if(map[i][j] == 6 && !hasFood) {
                    map[i][j] = 0;
                    hasFood = true;
                }
            }
        }


    }

    public int[][] update(int map[][]){
        this.map = map;
        map[getY()][getX()] = 0;
        movement(map);
        map[getY()][getX()] = 1;
        eat();
        return map;
    }

}
