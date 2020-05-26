package nl.rug.oop.cardgame;

public enum Card {

    //plus cards
    PLUS_ONE        (Type.PLUS,    1),
    PLUS_TWO        (Type.PLUS,    2),
    PLUS_THREE      (Type.PLUS,    3),
    PLUS_FOUR       (Type.PLUS,    4),
    PLUS_FIVE       (Type.PLUS,    5),
    PLUS_SIX        (Type.PLUS,    6),

    //min cards
    MIN_ONE         (Type.MIN,    1),
    MIN_TWO         (Type.MIN,    2),
    MIN_THREE       (Type.MIN,    3),
    MIN_FOUR        (Type.MIN,    4),
    MIN_FIVE        (Type.MIN,    5),
    MIN_SIX         (Type.MIN,    6),

    //+- cards
    MULTI_ONE       (Type.MULTI,    1),
    MULTI_TWO       (Type.MULTI,    2),
    MULTI_THREE     (Type.MULTI,    3),
    MULTI_FOUR      (Type.MULTI,    4),
    MULTI_FIVE      (Type.MULTI,    5),
    MULTI_SIX       (Type.MULTI,    6),

    //special
    SPEC_TIE        (Type.SPECIAL,    Value.TIE),
    SPEC_DOUBLE     (Type.SPECIAL,    Value.DOUBLE),
    SPEC_TWO_FOUR   (Type.SPECIAL,    Value.TWO_FOUR),
    SPEC_THREE_SIX  (Type.SPECIAL,    Value.THREE_SIX),
    SPEC_ONE_TWO    (Type.SPECIAL,    Value.ONE_TWO),

    //constants
    CON_ONE         (Type.CONSTANT,    1),
    CON_TWO         (Type.CONSTANT,    2),
    CON_THREE       (Type.CONSTANT,    3),
    CON_FOUR        (Type.CONSTANT,    4),
    CON_FIVE        (Type.CONSTANT,    5),
    CON_SIX         (Type.CONSTANT,    6),
    CON_SEVEN       (Type.CONSTANT,    7),
    CON_EIGHT       (Type.CONSTANT,    8),
    CON_NINE        (Type.CONSTANT,    9),
    CON_TEN         (Type.CONSTANT,    10);



    public enum Colour {
        BLUE,
        RED,
        MULTI,
        YELLOW,
        GREEN
    }

    public enum Type {
        PLUS (Colour.BLUE),
        MIN (Colour.RED),
        MULTI (Colour.MULTI),
        SPECIAL (Colour.YELLOW),
        CONSTANT (Colour.GREEN);

        private final Colour colour;

        Type(Colour colour) {
            this.colour = colour;
        }

        public Colour getColour() {
            return colour;
        }
    }

    public enum Value {
        ONE,
        TWO,
        THREE,
        FOUR,
        FIVE,
        SIX,
        SEVEN,
        EIGHT,
        NINE,
        TEN,
        TIE,
        DOUBLE,
        TWO_FOUR,
        THREE_SIX,
        ONE_TWO
    }

    private final Type type;
    private final Value value;
    private final Colour colour;
    private final int number;

    Card(Type type, Value value) {
        this.type = type;
        this.value = value;
        this.colour = type.getColour();
        this.number = 0;
    }

    Card(Type type, int number) {
        this.type = type;
        this.number = number;
        this.colour = type.getColour();
        this.value = null;
    }

    public Type getType() {
        return type;
    }

    public Value getValue() {
        return value;
    }

    public Colour getColour() {
        return colour;
    }

    public int getNumber() {
        return number;
    }
}
