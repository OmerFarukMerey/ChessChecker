import java.util.Arrays;

public class Queen extends Piece
{
    public Queen(String color)
    {
        super(color);
    }

    public String[] getAllMoves() // combine of two pieces = Bishop + Rook. (just copied :P)
    {
        String current_position = this.getPosition();
        int index1 = this.stringToIndex1(current_position);
        int index2 = this.stringToIndex2(current_position);
        String all_moves = "";

        for (int i = index1 + 1; i < 8; i++)
            all_moves += indexToString(i,index2) + ",";
        for (int i = index1 - 1; 0 <= i; i--)
            all_moves += indexToString(i,index2) + ",";
        for (int i = index2 + 1; i < 8; i++)
            all_moves += indexToString(index1,i) + ",";
        for (int i = index2 - 1; 0 <= i; i--)
            all_moves += indexToString(index1, i) + ",";
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

    public boolean canMove(String pos) // for valid empty board moves
    {
        String[] allMoves = getAllMoves();
        for (int i = 0; i < allMoves.length; i++)
            if (allMoves[i].equals(pos))
                return true;
        return false;
    }
}
