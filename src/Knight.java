/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

import static java.lang.Math.abs;

/**
 *
 * @author Mariana Rafael-White (101033642)
 */
public class Knight extends Piece {

   public Knight(ChessColour colour) {
      super(colour, ChessPieces.KNIGHT);
   }

   public boolean isLegalMove(ChessBoard board, Coordinate src, Coordinate dest) {

      int destColNum = dest.getColumnNumber();
      int destRowNum = dest.getRowNumber();
      int srcColNum = src.getColumnNumber();
      int srcRowNum = src.getRowNumber();

      if (super.isLegalMove(board, src, dest)) {
         //checks for a move left or right by 1, up or down by 2:
         if (abs(destColNum - srcColNum) == 1 && abs(destRowNum - srcRowNum) == 2) {
            return true;
         } //checks for a move left or right by 2, up or down by 1:
         else if (abs(destColNum - srcColNum) == 2 && abs(destRowNum - srcRowNum) == 1) {
            return true;
         }
      }
      //if above cases not true, it is an illegal move:
      return false;
   }

}
