package Model;

public class CharBajt{
    // reprezentacja
    byte bajt;
    String binnary;
    char charr;

    public CharBajt(byte bajt, String binnary, char charr) {
        this.bajt = bajt;
        this.binnary = binnary;
        this.charr = charr;
    }

    public byte getBajt() {
        return bajt;
    }

    public char getCharr() {
        return charr;
    }

    @Override
    public String toString() {
        return bajt + " :  "+ binnary;
    }

    public String getNameByte() {
        return bajt + " :  "+ binnary;
    }
    public String getNameChar() {
        return charr+"";
    }
}
