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
public class Square {

   private Coordinate coordinate;
   private Piece piece;

   public Square(Coordinate c) {
      this.coordinate = c;
      this.piece = null;
   }

   public Square(Coordinate c, Piece p) {
      this.coordinate = c;
      this.piece = p;
   }

   public char getColumn() {
      return this.coordinate.getColumn();
   }

   public char getRow() {
      return this.coordinate.getRow();
   }

   public int getColumnNumber() {
      return this.coordinate.getColumnNumber();
   }

   public int getRowNumber() {
      return this.coordinate.getRowNumber();
   }

   public Coordinate getCoordinate() {
      return this.coordinate;
   }

   public Piece getPiece() {
      return this.piece;
   }

   public Piece addPiece(Piece newPiece) {
      if (isOccupied()) {
         Piece previousPiece = this.piece;
         this.piece = newPiece;
         return previousPiece;
      }
      this.piece = newPiece;
      return null;
   }

   public Piece deletePiece() {
      if (isOccupied()) {
         Piece previousPiece = this.piece;
         this.piece = null;
         return previousPiece;
      }
      return null;
   }

   public boolean isOccupied() {
      return this.piece != null;
   }

   @Override //due to toString existing
   public String toString() {
      String p = (this.piece == null) ? " " : piece.toString();
      return ("Square" + coordinate.toString() + ":" + p);
   }
}
