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
public class Piece {

   private ChessColour colour;
   private ChessPieces name;
   private char shortName;
   private String imageName;

   public Piece(ChessColour colour, ChessPieces name) throws IllegalArgumentException {
      this.colour = colour;
      this.name = name;
      this.shortName = name.getShortName();
      if (colour == ChessColour.BLACK) {
         this.shortName = Character.toLowerCase(this.shortName);
      }
      this.imageName =  this.colour.name().toLowerCase() + "_" + this.name.name().toLowerCase() + ".png";
   }

   public ChessColour getColour() {
      return this.colour;
   }

   public ChessPieces getName() {
      return this.name;
   }

   public char getShortName() {
      return this.shortName;
   }
   
   public String getImageName(){
      return this.imageName;
   }

   @Override //due to toString existing
   public String toString() {
      return ("" + this.colour + " " + this.name);
   }

   public boolean isLegalMove(ChessBoard board, Coordinate src, Coordinate dest) {

      if (board.getSquare(dest).isOccupied()) {
         //check if destination is occupied by a friendly piece
         if (board.getSquare(dest).getPiece().getColour() == board.getSquare(src).getPiece().getColour()) {
            return false;
         }
      }
      return true;
   }
}
