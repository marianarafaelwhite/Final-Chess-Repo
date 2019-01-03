/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

/**
 *
 * @author Mariana Rafael-White (101033642)
 */
public class Bishop extends Piece {

   public Bishop(ChessColour colour) {
      super(colour, ChessPieces.BISHOP);
   }

   public boolean isLegalMove(ChessBoard board, Coordinate src, Coordinate dest) {
      
      int destColNum = dest.getColumnNumber();
      int destRowNum = dest.getRowNumber();
      int srcColNum = src.getColumnNumber();
      int srcRowNum = src.getRowNumber();
      
      if(super.isLegalMove(board, src, dest)){
         if(Math.abs(destColNum - srcColNum)==Math.abs(destRowNum - srcRowNum)){
            return true;
         }
      }
      return false;
   }

}
