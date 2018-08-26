package hackathon;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
  
public class Hackathon extends Application {
  
  public static void main(String[] args) {
    launch(args);
  }
  
  @Override
  public void start(Stage stage) {
    BorderPane bp = new BorderPane();
    Pane pane = new Pane();
    pane.setStyle("-fx-background-color: blue;");
    pane.setPrefSize(200,120);
    bp.setTop(pane);
    VBox vb = new VBox();
    Menu menu = new Menu(vb);
    bp.setLeft(vb);
    stage.setScene(new Scene(bp, 800, 600));
    stage.show();  
  } 
}