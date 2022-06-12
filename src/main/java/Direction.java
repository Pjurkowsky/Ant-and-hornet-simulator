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
}
