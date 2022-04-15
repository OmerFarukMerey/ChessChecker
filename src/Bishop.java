import java.util.Arrays;

public class Bishop extends Piece
{
    public Bishop(String color)
    {
        super(color);
    }

    public String[] getAllMoves() // considering all diagonals
    {
        String current_position = this.getPosition();
        int index1 = this.stringToIndex1(current_position);
        int index2 = this.stringToIndex2(current_position);
        String all_moves = "";

        for (int i = index1 + 1, j = index2 + 1; i < 8 && j < 8; i++,j++)
            all_moves += indexToString(i,j) + ",";

        for (int i = index1 + 1, j = index2 - 1; i < 8 && 0 <= j; i++ , j--)
            all_moves += indexToString(i,j) + ",";

        for (int i = index1 - 1, j = index2 + 1; 0 <= i && j < 8; i-- , j++)
            all_moves += indexToString(i,j) + ",";

        for (int i = index1 - 1, j = index2 - 1; 0 <= i && 0 <= j; i--, j--)
            all_moves += indexToString(i,j) + ",";

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

    public boolean canMove(String pos) // returning if the place is valid to move in an empty board
    {
        String[] allMoves = getAllMoves();
        for (int i = 0; i < allMoves.length; i++)
            if (allMoves[i].equals(pos))
                return true;
        return false;
    }
}
