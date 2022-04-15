import java.util.Arrays;

public class Pawn extends Piece
{
    public Pawn(String color)
    {
        super(color);
    }

    public String[] getAllMoves() // one up and two diagonals.
    {
        String current_position = this.getPosition();
        int index1 = this.stringToIndex1(current_position);
        int index2 = this.stringToIndex2(current_position);
        String all_moves = "";
        if (this.getColor().equals("black") &&  index1 + 1 < 8)
            all_moves += indexToString(index1+1,index2) + ",";
        if (this.getColor().equals("black") && index1 + 1 < 8 && 0 <= index2 - 1)
            all_moves += indexToString(index1+1,index2 - 1) + ",";
        if (this.getColor().equals("black") && index1 + 1 < 8 && index2 + 1 < 8)
            all_moves += indexToString(index1+1,index2 + 1) + ",";
        if (this.getColor().equals("black") && index1 == 1)
            all_moves += indexToString(index1+2, index2) + ",";

        if (this.getColor().equals("white") && 0 <= index1 - 1)
            all_moves += indexToString(index1 - 1,index2) + ",";
        if (this.getColor().equals("white") && 0 <= index1 - 1 && 0 <= index2 - 1)
            all_moves += indexToString(index1 - 1,index2 - 1) + ",";
        if (this.getColor().equals("white") && 0 <= index1 - 1 &&  index2 + 1 < 8)
            all_moves += indexToString(index1 - 1,index2 + 1) + ",";
        if (this.getColor().equals("white") && index1 == 6)
            all_moves += indexToString(index1-2, index2) + ",";
        int len = all_moves.length() - all_moves.replace(",","").length();
        String[] all = new String[len];
        for (int k = 0; k < all.length; k++)
        {
            int index = all_moves.indexOf(",");
            String temp = all_moves.substring(0,index);
            all_moves = all_moves.substring(index+1);
            all[k] = temp;
        }
        Arrays.sort(all);
        return all;
    }

    public boolean canMove(String pos) // for valid empty board moves
    {
        String[] allMoves = getAllMoves();
        for (int i = 0; i < allMoves.length; i++)
            if (allMoves[i].equals(pos))
                return true;
        return false;
    }
}
