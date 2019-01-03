/*
 * The images used are from an open-source project by 
 * Steven M. Vascellaro Stevoisiak from Long Island, New York.
 */
package chess;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.List;
import javafx.collections.ListChangeListener;
import javafx.scene.control.Alert;

/**
 *
 * @author Mariana Rafale-White (101033642)
 */
public class Assign4 extends Application implements ListChangeListener {

   private ChessBoard board;
   private Button[] whiteTakenSquare;
   private Button[] blackTakenSquare;

   @Override
   public void start(Stage primaryStage) {

      this.board = new ChessBoard();

      EventHandler<ActionEvent> handler = new SquareEventHandler(this.board);

      BorderPane root = new BorderPane();
      GridPane centre = new GridPane();
      Button btn[][] = new Button[8][8];
      for (int r = 0; r < 8; r++) {    //rows
         for (int c = 0; c < 8; c++) { //columns
            String strCoordinate = "" + (char) (c + 97) + (8 - r);

            btn[c][r] = new Button(strCoordinate);
            btn[c][r].setMinSize(90, 90);

            Coordinate coordinate = new Coordinate(strCoordinate);
            if (this.board.getSquare(coordinate).getPiece() != null) {
               String imageName = this.board.getSquare(coordinate).getPiece().getImageName();
               Image icon = new Image("chess/images/" + imageName);
               btn[c][r].setGraphic(new ImageView(icon));
            }

            if (r % 2 == 0) {
               if (c % 2 == 0) {
                  btn[c][r].setStyle("-fx-background-color: grey; -fx-border-color: white;");
               }
               else {
                  btn[c][r].setStyle("-fx-background-color: white; -fx-border-color: white;");
               }
            }
            else {
               if (c % 2 != 0) {
                  btn[c][r].setStyle("-fx-background-color: grey; -fx-border-color: white;");
               }
               else {
                  btn[c][r].setStyle("-fx-background-color: white; -fx-border-color: white;");
               }
            }
            btn[c][r].setOnAction(handler);
            centre.add(btn[c][r], c, r);
         }
      }

      VBox left = new VBox(); //white taken
      this.whiteTakenSquare = new Button[16];

      for (int i = 0; i < 16; i++) {
         this.whiteTakenSquare[i] = new Button();
         this.whiteTakenSquare[i].setStyle("-fx-background-color: grey; -fx-border-color: white;");
         this.whiteTakenSquare[i].setMinSize(90, 45);
         left.getChildren().add(this.whiteTakenSquare[i]);
      }

      VBox right = new VBox(); //black taken
      this.blackTakenSquare = new Button[16];

      for (int i = 0; i < 16; i++) {
         this.blackTakenSquare[i] = new Button();
         this.blackTakenSquare[i].setStyle("-fx-background-color: grey; -fx-border-color: white;");
         this.blackTakenSquare[i].setMinSize(90, 45);
         right.getChildren().add(this.blackTakenSquare[i]);
      }

      this.board.addTakenObserver(this);

      root.setCenter(centre);
      root.setLeft(left);
      root.setRight(right);

      Scene scene = new Scene(root, 900, 720);

      primaryStage.setTitle("Chess");
      primaryStage.setScene(scene);
      primaryStage.show();
   }

   /**
    * @param args the command line arguments
    */
   public static void main(String[] args) {
      launch(args);
   }

   public void onChanged(Change c) {
      while (c.next()) {
         if (c.wasAdded()) {
            int index = c.getFrom();
            List<Piece> pieces = c.getAddedSubList();
            for (Piece p : pieces) {
               Image icon = new Image("chess/images/" + p.getImageName());
               if (p.getColour() == ChessColour.WHITE) {
                  for (int i = 0; i < 16; i++) {
                     if (this.whiteTakenSquare[i].getGraphic() == null) {
                        this.whiteTakenSquare[i].setGraphic(new ImageView(icon));
                        break;
                     }
                  }
               }
               else {
                  for (int i = 0; i < 16; i++) {
                     if (this.blackTakenSquare[i].getGraphic() == null) {
                        this.blackTakenSquare[i].setGraphic(new ImageView(icon));
                        break;
                     }
                  }
               }
            }
         }
      }
   }
}

class SquareEventHandler implements EventHandler<ActionEvent> {

   private ChessBoard board;
   private boolean firstClick;
   private Button firstButton;
   private Button secondButton;
   private Coordinate src;
   private Coordinate dest;

   public SquareEventHandler(ChessBoard board) {
      this.board = board;
      this.firstClick = true;
      this.src = null;
      this.dest = null;
      this.firstButton = new Button();
      this.secondButton = new Button();
   }

   public void handle(ActionEvent event) {
      Object source = event.getSource();
      if (this.firstClick) {
         if (source instanceof Button) {
            this.firstButton = (Button) source;
         }
         this.firstClick = false;
      }
      else {
         if (source instanceof Button) {
            this.src = new Coordinate(this.firstButton.getText());
            this.secondButton = (Button) source;
            this.dest = new Coordinate(this.secondButton.getText());
            if (this.board.move(this.src, this.dest)) {
               this.secondButton.setGraphic(this.firstButton.getGraphic());
               this.firstButton.setGraphic(null);
            }
            else { //extra catch to alert user if clicked a move that is invalid
               Alert fail = new Alert(Alert.AlertType.INFORMATION);
               fail.setHeaderText("Invalid move");
               fail.setContentText("Try move again!");
               fail.showAndWait();
            }
         }
         this.firstClick = true;
      }
   }
}
