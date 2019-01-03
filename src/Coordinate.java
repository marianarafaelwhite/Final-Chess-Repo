/*
 * Starting version of Coordinate.java
 */
package chess;

/**
 *
 * @author Mariana Rafael-White (101033642)
 */
public class Coordinate {

   private int column;
   private int row;

   public Coordinate(int column, int row) throws IndexOutOfBoundsException {
      if ((column < 0) || (column > 7)) {
         throw new IndexOutOfBoundsException("column must be between 0 and 7,inclusive");
      }
      if ((row < 0) || (row > 7)) {
         throw new IndexOutOfBoundsException("row must be between 0 and 7,inclusive");
      }

      this.column = column;
      this.row = row;
   }

   public Coordinate(char column, char row) throws IndexOutOfBoundsException {
      if ((column < 'a') || (column > 'h')) {
         throw new IndexOutOfBoundsException("column must be between a and h,inclusive");
      }
      if ((row < '1') || (row > '8')) {
         throw new IndexOutOfBoundsException("row must be between 1 and 8,inclusive");
      }

      this.column = (int) (column);
      this.row = (int) (row);
   }

   public Coordinate(String coordinate) throws IndexOutOfBoundsException {
      if (coordinate.length() != 2) {
         throw new IllegalArgumentException("Coordinate is a 2-character string");
      }

      char column = coordinate.charAt(0);
      char row = coordinate.charAt(1);
      // this(x,y) except it must be the first statement!
      if ((column < 'a') || (column > 'h')) {
         throw new IndexOutOfBoundsException("x must be between a and h, inclusive");
      }
      if ((row < '1') || (row > '8')) {
         throw new IndexOutOfBoundsException("y must be between 1 and 8, inclusive");
      }

      this.column = (int) (column);
      this.row = (int) (row);
   }

   public char getColumn() {
      if (this.column <= 7 && this.column >= 0) {
         this.column += 97;
      }//if converting client number to char
      return (char) (this.column);
   }

   public int getColumnNumber() {
      if (this.column >= 97) {
         this.column = this.column - 97;
      }//if converting client char to int
      return this.column;
   }

   public char getRow() {
      if (this.row <= 7 && this.row >= 0) {
         this.row += 49;
      } //if converting client number to char
      return (char) (this.row);
   }

   public int getRowNumber() {
      if (this.row >= 49) {
         this.row = this.row - 49;
      } //if converting client char to int
      return this.row;
   }

   public String name() {
      return ("" + getColumn() + getRow());
   } //returns in form "a1"

   @Override // due to toString existing 
   public String toString() {
      return ("(" + getColumnNumber() + "," + getRowNumber() + ")");
   } //returns in form "(0,0)"
}
