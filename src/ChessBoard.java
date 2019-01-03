/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

import java.util.ArrayList;
import javafx.collections.*;
import javafx.collections.ListChangeListener;

/**
 *
 * @author Mariana Rafael-White (101033642)
 */
public class ChessBoard {

   private Square board[][];
   private ChessColour activeColour;
   private int fullMove;

   //lists to keep track of taken white pieces and taken black pieces:
   private ArrayList<Piece> whiteTakenPieces;
   private ArrayList<Piece> blackTakenPieces;
   private ObservableList<Piece> whiteTakenList;
   private ObservableList<Piece> blackTakenList;

   public ChessBoard() {
      this.board = new Square[8][8];
      for (int r = 0; r < 8; r++) {    //rows
         for (int c = 0; c < 8; c++) { //columns
            this.board[c][r] = new Square(new Coordinate(c, r));
         }
      }
      reset();
      this.activeColour = ChessColour.WHITE;
      this.fullMove = 1;
      this.whiteTakenPieces = new ArrayList<>();
      this.blackTakenPieces = new ArrayList<>();
      this.whiteTakenList = FXCollections.observableList(whiteTakenPieces);
      this.blackTakenList = FXCollections.observableList(blackTakenPieces);

   }

   public ChessBoard(Coordinate positions[], Piece pieces[]) throws IllegalArgumentException {
      if (positions.length != pieces.length) {
         throw new IllegalArgumentException("The list of positions must correspond to the list of pieces");
      }

      this.board = new Square[8][8];
      for (int r = 0; r < 8; r++) {       //rows
         for (int c = 0; c < 8; c++) {   //columns
            this.board[c][r] = new Square(new Coordinate(c, r));
         }
      }
      for (int i = 0; i < positions.length; i++) {
         this.board[positions[i].getColumnNumber()][positions[i].getRowNumber()].addPiece(pieces[i]);
      }
      this.activeColour = ChessColour.WHITE;
      this.fullMove = 1;
      this.whiteTakenPieces = new ArrayList<>();
      this.blackTakenPieces = new ArrayList<>();
   }

   private void reset() {
      // White rows
      this.board[0][0].addPiece(new Rook(ChessColour.WHITE));
      this.board[7][0].addPiece(new Rook(ChessColour.WHITE));
      this.board[1][0].addPiece(new Knight(ChessColour.WHITE));
      this.board[6][0].addPiece(new Knight(ChessColour.WHITE));
      this.board[2][0].addPiece(new Bishop(ChessColour.WHITE));
      this.board[5][0].addPiece(new Bishop(ChessColour.WHITE));
      this.board[3][0].addPiece(new Queen(ChessColour.WHITE));
      this.board[4][0].addPiece(new King(ChessColour.WHITE));

      for (int c = 0; c < 8; c++) {
         this.board[c][1].addPiece(new Pawn(ChessColour.WHITE));
      }

      // Black rows
      this.board[0][7].addPiece(new Rook(ChessColour.BLACK));
      this.board[7][7].addPiece(new Rook(ChessColour.BLACK));
      this.board[1][7].addPiece(new Knight(ChessColour.BLACK));
      this.board[6][7].addPiece(new Knight(ChessColour.BLACK));
      this.board[2][7].addPiece(new Bishop(ChessColour.BLACK));
      this.board[5][7].addPiece(new Bishop(ChessColour.BLACK));
      this.board[3][7].addPiece(new Queen(ChessColour.BLACK));
      this.board[4][7].addPiece(new King(ChessColour.BLACK));

      for (int c = 0; c < 8; c++) {
         this.board[c][6].addPiece(new Pawn(ChessColour.BLACK));
      }

      // Middle rows : Make sure they are UNOCCUPIED by deleting
      Piece p;
      for (int c = 0; c < 8; c++) {
         for (int r = 2; r < 6; r++) {
            p = this.board[c][r].deletePiece();
         }
      }

   }

   protected Square getSquare(Coordinate c) {
      return this.board[c.getColumnNumber()][c.getRowNumber()];
   }

   public boolean move(Coordinate src, Coordinate dest) {
      Square srcSquare = this.getSquare(src);
      if (!srcSquare.isOccupied()) {
         return false;
      }
      Piece p = srcSquare.getPiece();
      if (!p.getColour().equals(this.activeColour)) {
         return false;
      }
      if (p.isLegalMove(this, src, dest)) {
         Square destSquare = this.getSquare(dest);
         Piece takenPiece = destSquare.addPiece(p);

         //add taken pieces to lists of taken pieces
         if (takenPiece != null && takenPiece.getColour() == ChessColour.BLACK) {
            this.blackTakenList.add(takenPiece);
         }
         else if (takenPiece != null && takenPiece.getColour() == ChessColour.WHITE) {
            this.whiteTakenList.add(takenPiece);
         }

         srcSquare.deletePiece();
         this.activeColour = (this.activeColour == ChessColour.BLACK) ? ChessColour.WHITE : ChessColour.BLACK;
         if (this.activeColour == ChessColour.WHITE) {
            this.fullMove++;
         }
         // fullMove is incremented only *AFTER* BLACK has moved. 
         return true;
      }
      else {
         return false;
      }
   }

   @Override
   public String toString() {
      StringBuilder stringBoard = new StringBuilder("Board:Board\n");

      for (int r = 7; r >= 0; r--) {      //rows
         for (int c = 0; c < 8; c++) {   //columns
            if (this.board[c][r].getPiece() == null) {
               stringBoard.append(" _");
            }
            else {
               stringBoard.append(this.board[c][r].getPiece().getShortName()).append("_");
            }

            if (c == 7) {
               stringBoard.append("\n");
            }
         }
      }
      return stringBoard.toString();
   }

   public String toFEN() {
      StringBuilder boardFEN = new StringBuilder("FEN:");

      for (int r = 7; r >= 0; r--) {
         for (int c = 0; c < 8; c++) {
            if (this.board[c][r].getPiece() == null) {
               boardFEN.append(" ");
            }
            else {
               boardFEN.append(this.board[c][r].getPiece().getShortName());
            }

            if (c == 7) {
               boardFEN.append("/");
            }

            if (r == 0 && c == 7 && this.activeColour == ChessColour.WHITE) {
               boardFEN.append(" w ").append(this.fullMove);
            }
            else if (r == 0 && c == 7 && this.activeColour == ChessColour.BLACK) {
               boardFEN.append(" b ").append(this.fullMove);
            }
         }
      }
      return boardFEN.append("\n").toString();
   }

// Returns a string containing the list of short names of all pieces taken.
//All WHITE pieces are listed first, followed by a comma,
//followed by the list of all BLACK pieces (no spaces).
   public String toTakenString() {
      StringBuilder taken = new StringBuilder("");
      for (int i = 0; i < this.whiteTakenPieces.size(); i++) {
         taken.append(this.whiteTakenPieces.get(i).getShortName());
      }
      taken.append(",");
      for (int i = 0; i < this.blackTakenPieces.size(); i++) {
         taken.append(this.blackTakenPieces.get(i).getShortName());
      }
      return taken.toString();
   }

   public void addTakenObserver(ListChangeListener listener) {
      this.whiteTakenList.addListener(listener);
      this.blackTakenList.addListener(listener);
   }

}
