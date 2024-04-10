package com.example.poanimacao;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Quick extends Application {
    AnchorPane pane;
    Button botao_inicio;
    private Button vet[];
    public static void main(String[] args)
    {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Pesquisa e Ordenacao");
        AnchorPane pane = new AnchorPane();
        Button botao_inicio = new Button();
        botao_inicio.setLayoutX(440);
        botao_inicio.setLayoutY(100);
        botao_inicio.setText("Quick Sort");
        pane.getChildren().add(botao_inicio);

        Label label = new Label("Vetor Inicial");
        label.setLayoutX(5);
        label.setLayoutY(210);
        pane.getChildren().add(label);

        Label[] labels = new Label[20];

        labels[0] = new Label("quickSP(0,TL-1){");
        labels[1] = new Label("    int i=ini, j=fim,aux;");
        labels[2] = new Label("    boolean flag=true;");
        labels[3] = new Label("    while(i<j){");
        labels[4] = new Label("         if(flag)");
        labels[5] = new Label("              while(i<j && vet[i]<=vet[j])");
        labels[6] = new Label("                   i++;");
        labels[7] = new Label("         else");
        labels[8] = new Label("              while(i<j && vet[i]<=vet[j])");
        labels[9] = new Label("                   j--;");
        labels[10] = new Label("        aux=vet[i];");
        labels[11] = new Label("        vet[i]=vet[j];");
        labels[12] = new Label("        vet[j]=aux;");
        labels[13] = new Label("        flag=!flag;");
        labels[14] = new Label("    }");
        labels[15] = new Label("    if(ini<i-1)");
        labels[16] = new Label("         quickSP(ini,i-1);");
        labels[17] = new Label("    if(j+1<fim)");
        labels[18] = new Label("         quickSP(j+1,fim);");
        labels[19] = new Label("}");

        botao_inicio.setOnAction(e->{ quickSort(labels);});

        for (int i = 0; i < labels.length; i++) {
            labels[i].setLayoutX(1000);
            labels[i].setLayoutY(60 + i * 30);
            pane.getChildren().add(labels[i]);
        }

        Set<Integer> numerosUtilizados = new HashSet<>();
        vet = new Button[10];
        Random random = new Random();
        for (int i = 0; i < vet.length; i++) {
            int numero;
            do {
                numero = random.nextInt(10);
            } while (numerosUtilizados.contains(numero));
            numerosUtilizados.add(numero);

            vet[i] = new Button(String.valueOf(numero));
            vet[i].setLayoutX(110 + i * 80);
            vet[i].setLayoutY(200);
            vet[i].setMinHeight(40); vet[i].setMinWidth(40);
            vet[i].setFont(new Font(14));
            pane.getChildren().add(vet[i]);
        }

        Scene scene = new Scene(pane, 1400, 700);
        stage.setScene(scene);
        stage.show();
    }

    public void quickSort(Label[] labels)
    {
        Task<Void> task = new Task<Void>(){
            @Override
            protected Void call() {
                quick(labels,0,9);
                return null;
            }
        };
        Thread thread = new Thread(task);
        thread.start();
    }

    public void quick(Label[] labels, int ini, int fim){
        Image arrowImage = new Image(getClass().getResourceAsStream("/arrow.png"));
        ImageView arrowView = new ImageView(arrowImage);
        arrowView.setFitWidth(20);
        arrowView.setFitHeight(20);
        pane.getChildren().add(arrowView);
        String azul ="-fx-background-color: #4596dc;";
        int i=ini, j=fim;
        String aux;
        boolean flag=true;
        Platform.runLater(() -> {
            labels[0].setStyle(azul);
            vet[0].setStyle("-fx-background-color: #ee4545;");
            arrowView.setLayoutX(vet[0].getLayoutX() + vet[0].getWidth() / 2 - arrowView.getFitWidth() / 2);
            arrowView.setLayoutY(260);
        });
        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while(i<j){
            if(flag)
                while(i<j && Integer.parseInt(vet[i].getText())<=Integer.parseInt(vet[j].getText()))
                    i++;
            else
                while(i<j && Integer.parseInt(vet[i].getText())<=Integer.parseInt(vet[j].getText()))
                    j--;
            aux=vet[i].getText();
            vet[i].setText(vet[j].getText());
            vet[j].setText(aux);
            flag=!flag;
        }
        if(ini<i-1)
            quick(labels,ini,i-1);
        if(j+1<fim)
            quick(labels,j+1,fim);
    }
}
