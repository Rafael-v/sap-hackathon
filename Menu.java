import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import java.util.Map;
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
    private HttpJSONService http = new HttpJSONService();
    private List<List> jsondata;
    private List<List> jsoncolumn;

    public Menu() {
        editbuttons();
    }
    
    public void loadfile(Stage stage, ArrayList<Produto> pdt, File file){
            Map json = null;
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
            jsondata = (List) json.get("DATA");
            jsoncolumn = (List) json.get("COLUMNS");
            if(jsoncolumn.size() == 3){
                for(int i = 0; i < jsondata.size(); i++){
                    List datai = (List) jsondata.get(i);
                    Produto ptemp = new Produto((String) datai.get(0),(int) datai.get(1),(float) 0.0,(String) datai.get(2));
                    pdt.add(ptemp);
                }
            }
            //Nao ta funcionando -- nao carregar o arquivo remessa
            else if(jsoncolumn.size() == 4){
                if(pdt != null){
                    for(int i = 0; i < jsondata.size(); i++){
                        int index = -1;
                        for(int j = 0; j < pdt.size(); j++){
                            
                            if(pdt.get(j).Nome.compareTo((String) jsondata.get(i).get(0)) == 0){
                                index = j;
                                break;
                            }
                        }
                        SimpleDateFormat sd = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
                        try {
                            Date date = sd.parse((String) jsondata.get(i).get(1));
                            Calendar cal = Calendar.getInstance();
                            cal.setTime(date);
                            Remessa remessa = new Remessa(cal, (int) jsondata.get(i).get(2), (int) jsondata.get(i).get(3));
                            if(index != -1){
                                pdt.get(index).setRemessa(remessa);}
                        } catch (ParseException ex) {
                            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                        }
                       
                    }
                }
                else{
                    System.out.println("Produtos nao importados.");
                }
            }
            System.out.println(jsondata.toString());
          if (json == null){
              System.out.println("Erro na leitura do arquivo.");
          }
    }
    
    public void editbuttons(){
        bcad.setAlignment(Pos.CENTER);
        bdata.setAlignment(Pos.CENTER);
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
