package hackathon;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Hackathon extends Application {
    
    ListView<String> list;
    ArrayList<Produto> pdt;
    Label label1, label2, label3;
    String dia, mes, ano;
    int id;
    VBox botoes = new VBox();
    
    public Hackathon() {
        list = new ListView<String>();
        pdt = new ArrayList();
        label1 = new Label();
        label2 = new Label();
        label3 = new Label();
        
        pdt.add(new Produto("Abacaxi", 1, 2, "asda"));
        pdt.add(new Produto("Maca", 1, 0, "asdfasa"));
        pdt.add(new Produto("Pera", 1, (float)0, "asfsda"));
        pdt.add(new Produto("Abacate", 1, (float)0, "afasfassda"));
    }

    @Override
    public void start(Stage stage) {
        BorderPane borderPane = new BorderPane();
        Menu menu = new Menu();
        System.out.println("cuzao");
        File file = new File("src/hackathon/produto.json");
        System.out.println("cuzao");
        menu.loadfile(stage, pdt, file);
        System.out.println("cuzao");
        File file2 = new File("/home/natan/Downloads/sap-hackathon-master/hackathon/Hackaton/src/hackathon/remessa.json");
        menu.loadfile(stage, pdt, file2);
        
        borderPane.setTop(getTop());
        borderPane.setLeft(getLeft());
        borderPane.setCenter(getCenter());

        
        
        Scene scene = new Scene(borderPane, 700, 600);
        stage.setScene(scene);
        stage.setTitle("Redutor de Perdas");
        stage.setResizable(false);
        stage.show();
    }
    
    private Pane getTop() {
        Pane pane = new Pane();
        
        Label label = new Label("INTERFACE PARA REDUÇÃO DE PERDAS");
        label.setStyle("-fx-font: 26px \"Georgia\"; -fx-text-fill: white");

        HBox title = new HBox(label);
        title.setAlignment(Pos.CENTER);
        title.setStyle("-fx-background-color: black; -fx-padding: 40.0;");
        title.setMinWidth(710);
        
        pane.getChildren().add(title);
        
        return pane;
    }

    private Pane getCenter() {
        Pane pane = new Pane();
        
        HBox hb = new HBox();
        
        label1 = new Label("PRODUTO: " + "-");
        label1.setStyle("-fx-font: 26px \"Georgia\"; -fx-text-fill: black; -fx-padding: 30 50;");
       
        Image img = new Image("File:src/imagem.png", true);
        ImageView imageView = new ImageView(img);
        imageView.setFitHeight(90);
        imageView.setFitWidth(90);
        
        hb.getChildren().addAll(imageView, label1);
        
        Label msg1 = new Label("Quantia estimada para compra:");
        msg1.setStyle("-fx-font: 18px \"Georgia\"; -fx-text-fill: black");
        label2 = new Label("-");
        label2.setStyle("-fx-font: 24px \"Georgia\"; -fx-text-fill: black");
        
        String str = "nada";
        label3 = new Label("OBSERVAÇÕES: " + str);
        label3.setStyle("-fx-font: 16px \"Georgia\"; -fx-text-fill: black; -fx-padding: 20px");
        
        HBox p = new HBox();
        p.setSpacing(10);
        p.setAlignment(Pos.CENTER);
        p.getChildren().addAll(msg1, label2);
        p.setStyle("-fx-padding: 40.0;");
        
        
    
        botoes.setSpacing(10);
        botoes.setAlignment(Pos.CENTER);
        botoes.getChildren().addAll(hb, p, label3);
        botoes.setStyle("-fx-padding: 40.0;");
        
        
        pane.getChildren().add(botoes);
        
        return pane;
    }
    
    private void updateGrafico() {
        System.out.println(id);
        label1.setText("PRODUTO: " + pdt.get(id).getNome());
        
        System.out.println(String.valueOf(getSoma()));
        label2.setText(String.valueOf(getSoma()));
        
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        
        LineChart<Number,Number> lineChart;
        
        xAxis.setLabel("Month");
        
        lineChart = new LineChart<Number,Number>(xAxis,yAxis);
        
        lineChart.setTitle("Grafico de Unidades");
        lineChart.setTranslateZ(-100);
        
        
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Produtos Recebidos");
        
        XYChart.Series series2 = new XYChart.Series();
        series1.setName("Produtos Perdidos");
        
        XYChart.Series series3 = new XYChart.Series();
        series1.setName("Produtos Vendidos");
        
        Calendar cal;
        cal = Calendar.getInstance();
        SimpleDateFormat sd = new SimpleDateFormat("MM-dd-yyyy 00:00:00");
        try{
            Date date = sd.parse(mes + '-' + dia + '-' + ano + " 00:00:00");
            cal.setTime(date);
        } catch (ParseException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        List<Remessa> r = pdt.get(id).Busca_Dados_Dia(cal);
        
        for(Remessa i: r){
            series1.getData().add(new XYChart.Data(i.Qtd_Entrada,i.Data_Entrada.get(Calendar.DAY_OF_MONTH)));
            
            series2.getData().add(new XYChart.Data(i.Qtd_Perda,i.Data_Entrada.get(Calendar.DAY_OF_MONTH)));
            
            series3.getData().add(new XYChart.Data(i.Qtd_Entrada-i.Qtd_Perda,i.Data_Entrada.get(Calendar.DAY_OF_MONTH)));
        }
        
        lineChart.getData().addAll(series1,series2,series3);
        
        lineChart.setMaxSize(400, 250);
        
        botoes.getChildren().add(lineChart);
    }
    
    private double getSoma() {
        Calendar cal;
        SimpleDateFormat sd = new SimpleDateFormat("MM-dd-yyyy 00:00:00");
        try {
            Date date = sd.parse(mes + '-' + dia + '-' + ano + " 00:00:00");
            cal = Calendar.getInstance();
            cal.setTime(date);
            estatistica est = new estatistica();
            return est.calcula_proc(pdt.get(id).Busca_Dados_Dia(cal));
        } catch (ParseException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    private Pane getLeft() {
        Pane central = new Pane();
        
        int i = 0;
        for (; i<pdt.size(); i++) {
            System.out.println(pdt.get(i).getNome());
        }
        
        ObservableList<String> items =FXCollections.observableArrayList ();
        for(Produto p: pdt){
            items.add(p.getNome());
        }
        
        list.setItems(items);
        list.setPrefWidth(150);
        list.setPrefHeight(370);
        
        Label labelData = new Label("Data para busca:");
        labelData.setStyle("-fx-font: 16px \"Georgia\"; -fx-text-fill: black");
        
        TextField diaa = new TextField();
        diaa.setPromptText("Dia");
        diaa.setMaxWidth(38);
        TextField mess = new TextField();
        mess.setPromptText("Mês");
        mess.setMaxWidth(38);
        TextField anoo = new TextField();
        anoo.setPromptText("Ano");
        anoo.setMaxWidth(55);
        
        Button enviar = new Button("Enviar");
        enviar.setStyle("-fx-font: 14px Georgia; -fx-padding: 7 53px;");
        enviar.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                dia = diaa.getText();
                mes = mess.getText();
                ano = anoo.getText();
                id = list.getFocusModel().getFocusedIndex();
                updateGrafico();
            }
        });
        
        HBox data = getHBox();
        data.getChildren().addAll(diaa, mess, anoo);
        
        VBox vb = getVBox();
        vb.getChildren().addAll(labelData, data, enviar, list);
        
        central.getChildren().add(vb);
        
        return central;
    }
    
    private VBox getVBox() {
        VBox vb = new VBox();
        vb.setSpacing(10);
        vb.setAlignment(Pos.CENTER);
        vb.setStyle("-fx-padding: 10.0;");
        return vb;
    }
    
    private HBox getHBox() {
        HBox hb = new HBox();
        hb.setSpacing(10);
        hb.setAlignment(Pos.CENTER);
        return hb;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
