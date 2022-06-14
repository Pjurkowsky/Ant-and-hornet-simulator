public enum Direction {
    TOPLEFT, TOP, TOPRIGHT,
    LEFT, CENTRE, RIGHT,
    BOTTOMLEFT, BOTTOM, BOTTOMRIGHT;

    public static Direction fromInteger(int x) {
        switch (x) {
            case 0:
                return TOPLEFT;
            case 1:
                return TOP;
            case 2:
                return TOPRIGHT;
            case 3:
                return LEFT;
            case 4:
                return CENTRE;
            case 5:
                return RIGHT;
            case 6:
                return BOTTOMLEFT;
            case 7:
                return BOTTOM;
            case 8:
                return BOTTOMRIGHT;
        }
        return CENTRE;
    }

    public static int toInteger(Direction x) {
        switch (x) {
            case TOPLEFT:
                return 0;
            case TOP:
                return 1;
            case TOPRIGHT:
                return 2;
            case LEFT:
                return 3;
            case CENTRE:
                return 4;
            case RIGHT:
                return 5;
            case BOTTOMLEFT:
                return 6;
            case BOTTOM:
                return 7;
            case BOTTOMRIGHT:
                return 8;
        }
        return -1;
    }
}
