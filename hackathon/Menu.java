
package hackathon;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class Menu {
    Button bcad = new Button("Cadastro");
    Button bdata = new Button("Dados");
    Button bgrafic = new Button("Graficos");

    Menu(VBox vb) {
        vb.setSpacing(10);
        vb.setPadding(new Insets(5,5,5,10));
        vb.setMaxWidth(80);
        vb.setStyle("-fx-background-color: #00008B;");
        vb.getChildren().addAll(bcad, bdata, bgrafic);
        editbuttons();
    }
    
    public void editbuttons(){
        bcad.setAlignment(Pos.CENTER);
        bdata.setAlignment(Pos.CENTER);
        bgrafic.setAlignment(Pos.CENTER);
        bcad.setMinSize(120, 120);
    }
}
