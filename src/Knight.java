import java.util.Arrays;
public class Knight extends Piece
{
    public Knight(String color)
    {
        super(color);
    }

    public String[] getAllMoves() // considering all L moves.
    {
        String current_position = this.getPosition();
        int index1 = this.stringToIndex1(current_position);
        int index2 = this.stringToIndex2(current_position);
        String all_moves = "";
        if (0 <= index1 - 2 && index2 + 1 < 8)
            all_moves += indexToString(index1-2,index2+1) + ",";

        if (0 <= index1 - 2 && 0 <= index2 - 1)
            all_moves += indexToString(index1-2,index2-1) + ",";

        if (0 <= index1 - 1 && index2 + 2 < 8)
            all_moves += indexToString(index1-1,index2+2) + ",";

        if (0 <= index1 - 1 &&  0 <= index2 - 2)
            all_moves += indexToString(index1-1,index2-2) + ",";

        if (index1 + 2 < 8 && index2 + 1 < 8)
            all_moves += indexToString(index1+2,index2+1) + ",";

        if (index1 + 2 < 8 && 0 <= index2 - 1)
            all_moves += indexToString(index1+2,index2-1) + ",";

        if (index1 + 1 < 8 && 0 <= index2 - 2)
            all_moves += indexToString(index1+1,index2-2) + ",";

        if (index1 + 1 < 8  && index2 + 2 < 8)
            all_moves += indexToString(index1+1,index2+2) + ",";
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
