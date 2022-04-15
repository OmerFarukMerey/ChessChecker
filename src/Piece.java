public class Piece {
    private String position;
    private String color = "";

    public Piece(String color) // setting our color.
    {
        this.color = color;
    }

    //Leaving a note: Mister Tan you said that Piece class was left to us. I made this class such a piece that it can go wherever it wants so that's why it returns all the notations. I hope ı didn't anything wrong.
    public boolean canMove(String newPosition)
    {
        return true;
    }

    public String[] getAllMoves()
    {
        return new String[] {"a1","a2","a3","a4","a5","a6","a7","a8","b1","b2","b3","b4","b5","b6","b7","b8","c1","c2","c3","c4","c5","c6","c7","c8","d1","d2","d3","d4","d5","d6","d7","d8","e1","e2","e3","e4","e5","e6","e7","e8","f1","f2","f3","f4","f5","f6","f7","f8","g1","g2","g3","g4","g5","g6","g7","g8","h1","h2","h3","h4","h5","h6","h7","h8"};
    }

    public void setPosition(String pos) // setting our position
    {
        this.position = pos;
    }

    public String getPosition()
    {
        return position;
    } // returning our position

    public String getColor()
    {
        return color;
    } // returning our color.

    public int stringToIndex1(String position) // returning the index type of the string for the first index
    {
        return 8 - Integer.parseInt(this.getPosition().substring(1,2));
    }

    public int stringToIndex2(String position) // returning the index type of the string for the first index
    {
        return position.charAt(0) - 'a';
    }

    public String indexToString(int letter, int number) // returning the indexes into a string position
    {
        String pos = "";
        number += 97;
        return pos + (char)number + (8 - letter);
    }
}
