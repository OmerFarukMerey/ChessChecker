import java.util.Arrays;

public class Board {
    private Piece[][] board;

    public Board() // constructing our board.
    {
        this.board = new Piece[8][8];
        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board.length; j++)
                board[i][j] = null;
    }

    public boolean putPiece(Piece p, String pos) // putting our piece in the wanted position
    {
        p.setPosition(pos);
        int index1 = stringToIndex1(pos);
        int index2 = stringToIndex2(pos);
        if (0 <= index1 && 0 <= index2 && index1 < 8 && index2 < 8)
            board[index1][index2] = p;
        else
            return false;
        return true;
    }

    public Piece getPiece(String pos) // returning our piece at the specific index.
    {
        int index1 = stringToIndex1(pos);
        int index2 = stringToIndex2(pos);
        return board[index1][index2];
    }

    public void removePiece(String s) // remove method for checkmate.
    {
        int x = stringToIndex1(s);
        int y = stringToIndex2(s);
        board[x][y] = null;
    }

    public boolean check(String color) // checks if the color side is on check
    {
        //Let's get the king index.
        int kingindex1 = 0, kingindex2 = 0;
        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board.length; j++)
                if (board[i][j] instanceof King && board[i][j].getColor().equals(color))
                {
                    kingindex1 = i;
                    kingindex2 = j;
                }
        for (int i = kingindex1 - 1; i <= kingindex1 + 1; i++)
            for (int j = kingindex2 - 1;  j <= kingindex2 + 1; j++)
                if (0<=i && i<8 && 0<=j && j<8 && board[i][j] instanceof King && !board[i][j].getColor().equals(color))
                    return true;
        if (color.equals("white"))
        {
            if (0 <= kingindex1 - 1 && 0 <= kingindex2 - 1 && board[kingindex1-1][kingindex2 - 1] instanceof Pawn && board[kingindex1-1][kingindex2 - 1].getColor().equals("Black"))
                return true;
            if (kingindex1 + 1 < 8 && 0 <= kingindex2 - 1 && board[kingindex1 + 1][kingindex2 - 1] instanceof Pawn && board[kingindex1 + 1][kingindex2 - 1].getColor().equals("Black"))
                return true;
        }

        else if (color.equals("black"))
        {
            if (kingindex1 + 1 < 8 && 0 <= kingindex2 - 1 && board[kingindex1 + 1][kingindex2 - 1] instanceof Pawn && board[kingindex1 + 1][kingindex2 - 1].getColor().equals("White"))
                return true;
            if (kingindex1 + 1 < 8 && kingindex2 + 1 < 8 && board[kingindex1 + 1][kingindex2 + 1] instanceof Pawn && board[kingindex1 + 1][kingindex2 + 1].getColor().equals("Black"))
                return true;
        }

        for (int i = kingindex1 - 1, j = kingindex2 - 1; 0 <= i && 0 <= j; i--, j--)
            if ((board[i][j] instanceof Queen || board[i][j] instanceof Bishop && !board[i][j].getColor().equals(color)))
                return true;
            else if (board[i][j] != null && board[i][j].getColor().equals(color))
                break;

        for (int i = kingindex1 - 1, j = kingindex2 + 1; 0 <= i && j < 8; i--, j++)
            if ((board[i][j] instanceof Queen || board[i][j] instanceof Bishop) && !board[i][j].getColor().equals(color))
                return true;
            else if (board[i][j] != null && board[i][j].getColor().equals(color))
                break;

        for (int i = kingindex1 + 1, j = kingindex2 - 1; i < 8&& 0 <= j; i++, j--)
            if ((board[i][j] instanceof Queen || board[i][j] instanceof Bishop) && !board[i][j].getColor().equals(color))
                return true;
            else if (board[i][j] != null && board[i][j].getColor().equals(color))
                break;

        for (int i = kingindex1 + 1, j = kingindex2 + 1; i < 8 && j < 8; i++, j++)
            if ((board[i][j] instanceof Queen || board[i][j] instanceof Bishop) && !board[i][j].getColor().equals(color) )
                return true;
            else if (board[i][j] != null && board[i][j].getColor().equals(color))
                break;

        for (int i = kingindex1 - 1; 0 <= i; i--)
            if ((board[i][kingindex2] instanceof Queen || board[i][kingindex2] instanceof Rook) && !board[i][kingindex2].getColor().equals(color))
                return true;
            else if (board[i][kingindex2] != null && board[i][kingindex2].getColor().equals(color))
                break;

        for (int i = kingindex1 + 1; i < 8; i++)
            if ((board[i][kingindex2] instanceof Queen || board[i][kingindex2] instanceof Rook) && !board[i][kingindex2].getColor().equals(color))
                return true;
            else if (board[i][kingindex2] != null && board[i][kingindex2].getColor().equals(color))
                break;

        for (int i = kingindex2 - 1; 0 <= i; i--)
            if ((board[kingindex1][i] instanceof Queen || board[kingindex1][i] instanceof Rook) && !board[kingindex1][i].getColor().equals(color))
                return true;
            else if (board[kingindex1][i] != null && board[kingindex1][i].getColor().equals(color))
                break;

        for (int i = kingindex2 + 1; i < 8; i++)
            if ((board[kingindex1][i] instanceof Queen || board[kingindex1][i] instanceof Rook) && !board[kingindex1][i].getColor().equals(color))
                return true;
            else if (board[kingindex1][i] != null && board[kingindex1][i].getColor().equals(color))
                break;

        if (0 <= kingindex1 - 2 && kingindex2 + 1 < 8 && board[kingindex1 - 2][kingindex2 + 1] instanceof Knight && !board[kingindex1 - 2][kingindex2 + 1].getColor().equals(color))
            return true;

        if (0 <= kingindex1 - 2 && 0 <= kingindex2 - 1 && board[kingindex1 - 2][kingindex2 - 1] instanceof Knight && !board[kingindex1 - 2][kingindex2 - 1].getColor().equals(color))
            return true;

        if (0 <= kingindex1 - 1 && kingindex2 + 2 < 8 && board[kingindex1 - 1][kingindex2 + 2] instanceof Knight && !board[kingindex1 - 1][kingindex2 + 2].getColor().equals(color))
            return true;

        if (0 <= kingindex1 - 1 &&  0 <= kingindex2 - 2 && board[kingindex1 - 1][kingindex2 - 2] instanceof Knight && !board[kingindex1 - 1][kingindex2 - 2].getColor().equals(color))
            return true;

        if (kingindex1 + 2 < 8 && kingindex2 + 1 < 8 && board[kingindex1 + 2][kingindex2 + 1] instanceof Knight && !board[kingindex1 + 2][kingindex2 + 1].getColor().equals(color))
            return true;

        if (kingindex1 + 2 < 8 && 0 <= kingindex2 - 1 && board[kingindex1 + 2][kingindex2 - 1] instanceof Knight && !board[kingindex1 + 2][kingindex2 - 1].getColor().equals(color))
            return true;

        if (kingindex1 + 1 < 8 && 0 <= kingindex2 - 2 && board[kingindex1 + 1][kingindex2 - 2] instanceof Knight && !board[kingindex1 + 1][kingindex2 - 2].getColor().equals(color))
            return true;

        if (kingindex1 + 1 < 8 && kingindex2 + 2 < 8 && board[kingindex1 + 1][kingindex2 + 2] instanceof Knight && !board[kingindex1 + 1][kingindex2 + 2].getColor().equals(color))
            return true;

        return false;
    }

    public boolean checkMate(String color) // if "color"ed king is in checkmate
    {
        if (!check(color))
            return false;
        String same_color_positions = "";
        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board.length; j++)
                if (board[i][j] != null && board[i][j].getColor().equals(color))
                    same_color_positions += indexToString(i,j) + ",";
        int len = same_color_positions.length() - same_color_positions.replace(",","").length();
        String[] all = new String[len];
        for (int k = 0; k < all.length; k++)
        {
            int index = same_color_positions.indexOf(",");
            String temp = same_color_positions.substring(0,index);
            same_color_positions = same_color_positions.substring(index+1);
            all[k] = temp;
        }

        for (int i = 0; i < all.length; i++)
        {
            Piece my_piece = getPiece(all[i]);
            int x = my_piece.stringToIndex1(my_piece.getPosition());
            int y = my_piece.stringToIndex2(my_piece.getPosition());
            String[] my_piece_pos_moves = getAllPossibleMoves(x,y);
            for (int j = 0; j < my_piece_pos_moves.length; j++)
            {
                putPiece(my_piece, my_piece_pos_moves[j]);
                removePiece(indexToString(x,y));
                if (!check(color))
                    return false;
                removePiece(my_piece_pos_moves[j]);
                putPiece(my_piece, indexToString(x,y));
            }
        }
        return true;
    }

    public int stringToIndex1(String position) // it is finished
    {
        return 8 - Integer.parseInt(position.substring(1));
    }

    public int stringToIndex2(String position) // it is finished
    {
        return position.charAt(0) - 'a';
    }

    public String indexToString(int letter, int number) // it is finished
    {
        String pos = "";
        number += 97;
        return pos + (char)number + (8 - letter);
    }

    public String[] getAllPossibleMoves(int x, int y)
    {
        if (board[x][y] instanceof King)
        {
            String current_position = board[x][y].getPosition();
            int index1 = this.stringToIndex1(current_position);
            int index2 = this.stringToIndex2(current_position);
            String possible_moves = "";
            for (int i = index1-1; i <= index1 +1; i++)
                for (int j = index2-1; j <= index2 +1; j++)
                    if (0 <= i && i < 8 && 0 <= j && j < 8 && (board[i][j] == null || !board[i][j].getColor().equals(board[x][y].getColor())))
                        possible_moves += indexToString(i,j) + ",";
            int len = possible_moves.length() - possible_moves.replace(",","").length();
            String[] all = new String[len];
            for (int k = 0; k < all.length; k++)
            {
                int index = possible_moves.indexOf(",");
                String temp = possible_moves.substring(0,index);
                possible_moves = possible_moves.substring(index+1);
                all[k] = temp;
            }
            Arrays.sort(all);
            return all;
        }
        else if (board[x][y] instanceof Bishop)
        {
            String current_position = board[x][y].getPosition();
            int index1 = this.stringToIndex1(current_position);
            int index2 = this.stringToIndex2(current_position);
            String possible_moves = "";

            for (int i = index1 + 1, j = index2 + 1; i < 8 && j < 8; i++,j++)
                if (board[i][j] == null)
                    possible_moves += indexToString(i,j) + ",";
                else if (!board[i][j].getColor().equals(board[x][y].getColor()))
                {
                    possible_moves += indexToString(i,j) + ",";
                    break;
                }

            for (int i = index1 + 1, j = index2 - 1; i < 8 && 0 <= j; i++ , j--)
                if (board[i][j] == null)
                    possible_moves += indexToString(i,j) + ",";
                else if (!board[i][j].getColor().equals(board[x][y].getColor()))
                {
                    possible_moves += indexToString(i,j) + ",";
                    break;
                }

            for (int i = index1 - 1, j = index2 + 1; 0 <= i && j < 8; i-- , j++)
                if (board[i][j] == null)
                    possible_moves += indexToString(i,j) + ",";
                else if (!board[i][j].getColor().equals(board[x][y].getColor()))
                {
                    possible_moves += indexToString(i,j) + ",";
                    break;
                }

            for (int i = index1 - 1, j = index2 - 1; 0 <= i && 0 <= j; i--, j--)
                if (board[i][j] == null)
                    possible_moves += indexToString(i,j) + ",";
                else if (!board[i][j].getColor().equals(board[x][y].getColor()))
                {
                    possible_moves += indexToString(i,j) + ",";
                    break;
                }
            int len = possible_moves.length() - possible_moves.replace(",","").length();
            String[] all = new String[len];
            for (int k = 0; k < all.length; k++)
            {
                int index = possible_moves.indexOf(",");
                String temp = possible_moves.substring(0,index);
                possible_moves = possible_moves.substring(index+1);
                all[k] = temp;
            }
            Arrays.sort(all);
            return all;
        }
        else if (board[x][y] instanceof Knight)
        {
            String current_position = board[x][y].getPosition();
            int index1 = this.stringToIndex1(current_position);
            int index2 = this.stringToIndex2(current_position);
            String all_moves = "";
            if (0 <= index1 - 2 && index2 + 1 < 8 && !board[index1-2][index2+1].getColor().equals(board[x][y].getColor()))
                all_moves += indexToString(index1-2,index2+1) + ",";

            if (0 <= index1 - 2 && 0 <= index2 - 1 && !board[index1-2][index2-1].getColor().equals(board[x][y].getColor()))
                all_moves += indexToString(index1-2,index2-1) + ",";

            if (0 <= index1 - 1 && index2 + 2 < 8 && !board[index1-1][index2+2].getColor().equals(board[x][y].getColor()))
                all_moves += indexToString(index1-1,index2+2) + ",";

            if (0 <= index1 - 1 &&  0 <= index2 - 2 && !board[index1-1][index2-2].getColor().equals(board[x][y].getColor()))
                all_moves += indexToString(index1-1,index2-2) + ",";

            if (index1 + 2 < 8 && index2 + 1 < 8 && !board[index1+2][index2+1].getColor().equals(board[x][y].getColor()))
                all_moves += indexToString(index1+2,index2+1) + ",";

            if (index1 + 2 < 8 && 0 <= index2 - 1 && !board[index1+2][index2-1].getColor().equals(board[x][y].getColor()))
                all_moves += indexToString(index1+2,index2-1) + ",";

            if (index1 + 1 < 8 && 0 <= index2 - 2 && !board[index1+1][index2-1].getColor().equals(board[x][y].getColor()))
                all_moves += indexToString(index1+1,index2-2) + ",";

            if (index1 + 1 < 8  && index2 + 2 < 8 && !board[index1+1][index2+2].getColor().equals(board[x][y].getColor()))
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
        else if (board[x][y] instanceof Pawn)
        {
            String current_position = board[x][y].getPosition();
            int index1 = this.stringToIndex1(current_position);
            int index2 = this.stringToIndex2(current_position);
            String all_moves = "";
            if (board[x][y].getColor().equals("black") && index1 + 1 < 8 && board[index1+1][y] == null)
                all_moves += indexToString(index1+1,index2) + ",";
            if (board[x][y].getColor().equals("black") && index1 + 1 < 8 && 0 <= index2 - 1 && board[index1+1][index2-1] != null && !board[index1+1][index2-1].getColor().equals(board[x][y].getColor()))
                all_moves += indexToString(index1+1,index2 - 1) + ",";
            if (board[x][y].getColor().equals("black") && index1 + 1 < 8 && index2 + 1 < 8 && board[index1+1][index2+1] != null&& !board[index1+1][index2+1].getColor().equals(board[x][y].getColor()))
                all_moves += indexToString(index1+1,index2 + 1) + ",";
            if (board[x][y].getColor().equals("black") && index1 == 1 && board[index1+2][index2] == null && board[index1+1][index2] == null)
                all_moves += indexToString(index1+2, index2) + ",";

            if (board[x][y].getColor().equals("white") && 0 <= index1 - 1 && board[index1-1][y] == null)
                all_moves += indexToString(index1 - 1,index2) + ",";
            if (board[x][y].getColor().equals("white") && 0 <= index1 - 1 && 0 <= index2 - 1 && 0 <= index2 - 1 && board[index1-1][index2-1] != null && !board[index1-1][index2-1].getColor().equals(board[x][y].getColor()))
                all_moves += indexToString(index1 - 1,index2 - 1) + ",";
            if (board[x][y].getColor().equals("white") && 0 <= index1 - 1 &&  index2 + 1 < 8 && 0 <= index2 - 1 && board[index1-1][index2+1] != null && !board[index1-1][index2+1].getColor().equals(board[x][y].getColor()))
                all_moves += indexToString(index1 - 1,index2 + 1) + ",";
            if (board[x][y].getColor().equals("white") && index1 == 6 && board[index1-2][index2] == null && board[index1-1][index2] == null)
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
        else if (board[x][y] instanceof Rook)
        {
            String current_position = board[x][y].getPosition();
            int index1 = this.stringToIndex1(current_position);
            int index2 = this.stringToIndex2(current_position);
            String all_moves = "";

            for (int i = index1 + 1; i < 8; i++)
                if (board[i][index2] == null)
                    all_moves += indexToString(i,index2) + ",";
                else if (!board[i][index2].getColor().equals(board[x][y].getColor()))
                {
                    all_moves += indexToString(i,index2) + ",";
                    break;
                }
            for (int i = index1 - 1; 0 <= i; i--)
                if (board[i][index2] == null)
                    all_moves += indexToString(i,index2) + ",";
                else if (!board[i][index2].getColor().equals(board[x][y].getColor()))
                {
                    all_moves += indexToString(i,index2) + ",";
                    break;
                }

            for (int i = index2 + 1; i < 8; i++)
                if (board[index1][i] == null)
                    all_moves += indexToString(index1,i) + ",";
                else if (!board[index1][i].getColor().equals(board[x][y].getColor()))
                {
                    all_moves += indexToString(index1,i) + ",";
                    break;
                }

            for (int i = index2 - 1; 0 <= i; i--)
                if (board[index1][i] == null)
                    all_moves += indexToString(index1, i) + ",";
                else if (!board[index1][i].getColor().equals(board[x][y].getColor()))
                {
                    all_moves += indexToString(index1,i) + ",";
                    break;
                }

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
        else if (board[x][y] instanceof Queen)
        {
            String current_position = board[x][y].getPosition();
            int index1 = this.stringToIndex1(current_position);
            int index2 = this.stringToIndex2(current_position);
            String all_moves = "";

            for (int i = index1 + 1; i < 8; i++)
                if (board[i][index2] == null)
                    all_moves += indexToString(i,index2) + ",";
                else if (!board[i][index2].getColor().equals(board[x][y].getColor()))
                {
                    all_moves += indexToString(i,index2) + ",";
                    break;
                }
            for (int i = index1 - 1; 0 <= i; i--)
                if (board[i][index2] == null)
                    all_moves += indexToString(i,index2) + ",";
                else if (!board[i][index2].getColor().equals(board[x][y].getColor()))
                {
                    all_moves += indexToString(i,index2) + ",";
                    break;
                }

            for (int i = index2 + 1; i < 8; i++)
                if (board[index1][i] == null)
                    all_moves += indexToString(index1,i) + ",";
                else if (!board[index1][i].getColor().equals(board[x][y].getColor()))
                {
                    all_moves += indexToString(index1,i) + ",";
                    break;
                }

            for (int i = index2 - 1; 0 <= i; i--)
                if (board[index1][i] == null)
                    all_moves += indexToString(index1, i) + ",";
                else if (!board[index1][i].getColor().equals(board[x][y].getColor()))
                {
                    all_moves += indexToString(index1,i) + ",";
                    break;
                }
            for (int i = index1 + 1, j = index2 + 1; i < 8 && j < 8; i++,j++)
                if (board[i][j] == null)
                    all_moves += indexToString(i,j) + ",";
                else if (!board[i][j].getColor().equals(board[x][y].getColor()))
                {
                    all_moves += indexToString(i,j) + ",";
                    break;
                }

            for (int i = index1 + 1, j = index2 - 1; i < 8 && 0 <= j; i++ , j--)
                if (board[i][j] == null)
                    all_moves += indexToString(i,j) + ",";
                else if (!board[i][j].getColor().equals(board[x][y].getColor()))
                {
                    all_moves += indexToString(i,j) + ",";
                    break;
                }

            for (int i = index1 - 1, j = index2 + 1; 0 <= i && j < 8; i-- , j++)
                if (board[i][j] == null)
                    all_moves += indexToString(i,j) + ",";
                else if (!board[i][j].getColor().equals(board[x][y].getColor()))
                {
                    all_moves += indexToString(i,j) + ",";
                    break;
                }

            for (int i = index1 - 1, j = index2 - 1; 0 <= i && 0 <= j; i--, j--)
                if (board[i][j] == null)
                    all_moves += indexToString(i,j) + ",";
                else if (!board[i][j].getColor().equals(board[x][y].getColor()))
                {
                    all_moves += indexToString(i,j) + ",";
                    break;
                }
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
        return new String[] {""};
    }
}
