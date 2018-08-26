
package hackathon;

import java.io.File;
import java.io.IOException;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javax.script.ScriptException;

public class Menu {
    Button bcad = new Button("Cadastro");
    Button bdata = new Button("Dados");
    Button bgrafic = new Button("Graficos");
    private HttpJSONService http = new HttpJSONService();

    Menu(VBox vb) {
        vb.setSpacing(10);
        vb.setPadding(new Insets(5,5,5,10));
        vb.setMaxWidth(80);
        vb.setStyle("-fx-background-color: #00008B;");
        vb.getChildren().addAll(bcad, bdata, bgrafic);
        editbuttons();
    }
    
    public void loadfile(Stage stage){
        bdata.setOnAction(ev -> {
          Map json = null;
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
          alert.setTitle("Atenção");
          alert.setContentText("Voce deseja carregar um arquivo de dados(json) local?");
          Optional<ButtonType> result = alert.showAndWait();
          if(result.get() == ButtonType.OK){
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Resource File");
            fileChooser.getExtensionFilters().addAll(new ExtensionFilter("JSON Files", "*.json"));
            File file = fileChooser.showOpenDialog(stage);
            if(file != null){
              JSONParsing engine = new JSONParsing();
                try {
                    json = engine.parseJSON(readFile(file.toString()));
                } catch (IOException ex) {
                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ScriptException ex) {
                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
          }
          if (json != null){
            System.out.println("Erro na leitura do arquivo.");
          }
      });
    }
    
    public void editbuttons(){
        bcad.setAlignment(Pos.CENTER);
        bdata.setAlignment(Pos.CENTER);
        bgrafic.setAlignment(Pos.CENTER);
    }
    
    private String readFile(String pathname) throws IOException {

        File file = new File(pathname);
        StringBuilder fileContents = new StringBuilder((int)file.length());
        Scanner scan = new Scanner(file);
        String lineNext = System.getProperty("line.separator");
        try {
            while(scan.hasNextLine()) {
                fileContents.append(scan.nextLine() + lineNext);
            }
            return fileContents.toString();
        } finally {
          scan.close();
        }
    }
}
