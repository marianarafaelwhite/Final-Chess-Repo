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
public class King extends Piece {

   public King(ChessColour colour) {
      super(colour, ChessPieces.KING);
   }

   public boolean isLegalMove(ChessBoard board, Coordinate src, Coordinate dest) {

      int destColNum = dest.getColumnNumber();
      int destRowNum = dest.getRowNumber();
      int srcColNum = src.getColumnNumber();
      int srcRowNum = src.getRowNumber();

      if (super.isLegalMove(board, src, dest)) {
         //checks for move that could be right or left by 1, up or down by 1 OR just up or down by 1
         if ((abs(destColNum - srcColNum) == 1 || abs(destColNum - srcColNum) == 0) && abs(destRowNum - srcRowNum) == 1) {
            return true;
         } //checks for move could be right or left by 1 and up or down by 1 OR just left or right by 1 
         else if (abs(destColNum - srcColNum) == 1 && (abs(destRowNum - srcRowNum) == 1 || abs(destRowNum - srcRowNum) == 0)) {
            return true;
         }
      }
      //if above cases not true, it is an illegal move:
      return false;
   }

}
