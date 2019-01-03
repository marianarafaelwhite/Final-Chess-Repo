package chess;

import static java.lang.Math.abs;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Mariana Rafael-White (101033642)
 */
public class Pawn extends Piece {

   public Pawn(ChessColour colour) {
      super(colour, ChessPieces.PAWN);
   }

   public boolean isLegalMove(ChessBoard board, Coordinate src, Coordinate dest) {

      int destColNum = dest.getColumnNumber();
      int destRowNum = dest.getRowNumber();
      int srcColNum = src.getColumnNumber();
      int srcRowNum = src.getRowNumber();
      boolean isOccupied = board.getSquare(dest).isOccupied();

      if (super.isLegalMove(board, src, dest)) {
         //white pieces:
         if (board.getSquare(src).getPiece().getColour() == ChessColour.WHITE) {
            //checks for a diagonal capture move (destination must be occupied already)
            if (isOccupied) {
               return abs(destColNum - srcColNum) == 1 && destRowNum == src.getRowNumber() + 1;
            }

            if (srcRowNum == 1) {   //if pawn is in its starting row, it is on its first move
               //checks for a move forward by 2 as long as it is the first move for that pawn
               if (destColNum == srcColNum && destRowNum == srcRowNum + 2) {
                  return true;
               }
            }

            //checks for a move forward by 1
            if (destColNum == srcColNum && destRowNum == srcRowNum + 1) {
               return true;
            }

         } //black pieces:
         else if (board.getSquare(src).getPiece().getColour() == ChessColour.BLACK) {
            //checks for a diagonal capture move (destination must be occupied already)
            if (isOccupied) {
               return abs(destColNum - srcColNum) == 1 && destRowNum == src.getRowNumber() - 1;
            }

            if (srcRowNum == 6) {   //if pawn is in its starting row, it is on its first move
               //checks for a move forward by 2 as long as it is the first move for that pawn
               if (destColNum == srcColNum && destRowNum == srcRowNum - 2) {
                  return true;
               }
            }

            //checks for a move forward by 1
            if (destColNum == srcColNum && destRowNum == srcRowNum - 1) {
               return true;
            }
         }
      }
      //if above cases not true, it is an illegal move:
      return false;
   }

}
