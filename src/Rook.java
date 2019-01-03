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
public class Rook extends Piece {

   public Rook(ChessColour colour) {
      super(colour, ChessPieces.ROOK);
   }

   public boolean isLegalMove(ChessBoard board, Coordinate src, Coordinate dest) {
      
      int destColNum = dest.getColumnNumber();
      int destRowNum = dest.getRowNumber();
      int srcColNum = src.getColumnNumber();
      int srcRowNum = src.getRowNumber();
      
      if(super.isLegalMove(board, src, dest)){
         if(Math.abs(destColNum - srcColNum)==0 || Math.abs(destRowNum - srcRowNum)==0 ){
            return true;
         }
      }
      // to be implemented later, for now it returns false
      return false;
   }

}
