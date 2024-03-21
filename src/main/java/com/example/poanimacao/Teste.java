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
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;
import javafx.scene.text.Font;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Teste extends Application {
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
        botao_inicio.setText("Counting Sort");
        pane.getChildren().add(botao_inicio);

        Label label = new Label("Vetor Inicial");
        label.setLayoutX(5);
        label.setLayoutY(210);
        pane.getChildren().add(label);

        Label labelContagem = new Label("Vetor Contagem");
        labelContagem.setLayoutX(5);
        labelContagem.setLayoutY(310);
        pane.getChildren().add(labelContagem);

        Label labelSaida = new Label("Vetor Ordenado");
        labelSaida.setLayoutX(5);
        labelSaida.setLayoutY(410);
        pane.getChildren().add(labelSaida);

        Label[] labels = new Label[15]; // Ajuste o tamanho conforme o número de linhas do código fornecido

// Crie os labels e defina o texto de cada um com uma linha do código
        labels[0] = new Label("int maior=vet[0];");
        labels[1] = new Label("for(int i=1;i<TL;i++)");
        labels[2] = new Label("    if(vet[i]>maior)");
        labels[3] = new Label("        maior=vet[i];");
        labels[4] = new Label("int cont[]= new int[maior+1];");
        labels[5] = new Label("for(int i=0;i<TL;i++)");
        labels[6] = new Label("    cont[vet[i]]++;");
        labels[7] = new Label("for(int i=1;i<=maior;i++)");
        labels[8] = new Label("    cont[i]=cont[i-1]+cont[i];");
        labels[9] = new Label("int saida[]=new int[TL];");
        labels[10] = new Label("    saida[cont[vet[i]]-1]=vet[i];");
        labels[11] = new Label("    cont[vet[i]]--;");
        labels[12] = new Label("}");
        labels[13] = new Label("for(int i=0;i<TL;i++)");
        labels[14] = new Label("    vet[i]=saida[i];");

        botao_inicio.setOnAction(e->{ countingSort(pane,labels);});

// Adicione os labels à sua interface gráfica
        for (int i = 0; i < labels.length; i++) {
            labels[i].setLayoutX(1000);
            labels[i].setLayoutY(60 + i * 30); // Ajuste a posição conforme necessário
            pane.getChildren().add(labels[i]);
        }

        Set<Integer> numerosUtilizados = new HashSet<>();
        vet = new Button[10]; // Change size to 10
        Random random = new Random();
        for (int i = 0; i < vet.length; i++) {
            int numero;
            do {
                numero = random.nextInt(10); // Gera um número aleatório de 0 a 9
            } while (numerosUtilizados.contains(numero)); // Verifica se o número já foi utilizado
            numerosUtilizados.add(numero); // Adiciona o número aos utilizados

            vet[i] = new Button(String.valueOf(numero));
            vet[i].setLayoutX(110 + i * 80);
            vet[i].setLayoutY(200);
            vet[i].setMinHeight(40); vet[i].setMinWidth(40);
            vet[i].setFont(new Font(14));
            pane.getChildren().add(vet[i]);
        }

        Scene scene = new Scene(pane, 1400, 600);
        stage.setScene(scene);
        stage.show();
    }

    public void countingSort(AnchorPane pane, Label[] labels)
    {
        Image arrowImage = new Image(getClass().getResourceAsStream("/arrow.png"));
        ImageView arrowView = new ImageView(arrowImage);
        arrowView.setFitWidth(20); // Ajuste a largura da seta conforme necessário
        arrowView.setFitHeight(20); // Ajuste a altura da seta conforme necessário
        pane.getChildren().add(arrowView);

        Task<Void> task = new Task<Void>(){
            @Override
            protected Void call() {
                // Counting Sort
                Button[] sorted = new Button[vet.length];
                int maior = Integer.parseInt(vet[0].getText());
                Platform.runLater(() -> vet[0].setStyle("-fx-background-color: #ee4545;"));
                Platform.runLater(() -> {
                    arrowView.setLayoutX(vet[0].getLayoutX() + vet[0].getWidth() / 2 - arrowView.getFitWidth() / 2);
                    arrowView.setLayoutY(260); // Posição vertical da seta
                });
                try {
                    Thread.sleep(800);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int indexMaior = 0;
                for (int i = 1; i < vet.length; i++) {
                    final int index = i; // Cópia da variável indexMaior
                    if (Integer.parseInt(vet[i].getText()) > maior) {
                        int finalIndexMaior = indexMaior;
                        Platform.runLater(() -> vet[finalIndexMaior].setStyle(""));
                        maior = Integer.parseInt(vet[i].getText());
                        Platform.runLater(() -> vet[index].setStyle("-fx-background-color: #ee4545;"));
                        indexMaior = index;
                    }
                    Platform.runLater(() -> {
                        arrowView.setLayoutX(110 + index * 80); // Posição horizontal da seta
                        arrowView.setLayoutY(260); // Posição vertical da seta
                    });
                    try {
                        Thread.sleep(800);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (i == vet.length - 1) { // Última iteração
                        int finalIndexMaior = indexMaior;
                        Platform.runLater(() -> vet[finalIndexMaior].setStyle(""));
                    }
                }
                Platform.runLater(() -> pane.getChildren().remove(arrowView));
                try {
                    Thread.sleep(800);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Button[] count = new Button[maior + 1];
                int finalMaior = maior;
                for (int i = 0; i <= finalMaior; i++) {
                    int finalI = i;
                    Platform.runLater(() -> {
                    // Criação dos botões representando o vetor count
                        count[finalI] = new Button("0");
                        count[finalI].setLayoutX(110 + finalI * 80);
                        count[finalI].setLayoutY(300); // Posição vertical dos botões count
                        count[finalI].setMinHeight(40);
                        count[finalI].setMinWidth(40);
                        count[finalI].setFont(new Font(14));
                        pane.getChildren().add(count[finalI]);
                    });
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                Image arrowImage2 = new Image(getClass().getResourceAsStream("/arrow.png"));
                ImageView arrowView2 = new ImageView(arrowImage2);
                arrowView2.setFitWidth(20);
                arrowView2.setFitHeight(20);
                Platform.runLater(() -> {pane.getChildren().add(arrowView2);});
                Platform.runLater(() -> {pane.getChildren().add(arrowView);});

                for (int i = 0; i < vet.length; i++) {
                    int value = Integer.parseInt(vet[i].getText());
                    int finalI = i;
                    Platform.runLater(() -> {
                        count[value].setText(String.valueOf(Integer.parseInt(count[value].getText()) + 1));
                        arrowView.setLayoutX(110 + finalI * 80); // Posição horizontal da seta
                        arrowView.setLayoutY(260); // Posição vertical da seta
                        arrowView2.setLayoutX(110 + value * 80); // Posição horizontal da seta
                        arrowView2.setLayoutY(360); // Posição vertical da seta
                    });
                    try {
                        Thread.sleep(800);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                Platform.runLater(() -> pane.getChildren().remove(arrowView));
                Platform.runLater(() -> pane.getChildren().remove(arrowView2));
                try {
                    Thread.sleep(800);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.runLater(() -> {pane.getChildren().add(arrowView2);});
                arrowView2.setLayoutX(110 * 80); // Posição horizontal da seta
                arrowView2.setLayoutY(360);
                int value = Integer.parseInt(count[0].getText());
                System.out.println(value);
                for (int i = 1; i < count.length; i++) {
                    final int index = i;
                    Platform.runLater(() -> {
                        int value1 = Integer.parseInt(count[index].getText());
                        int value2 = Integer.parseInt(count[index - 1].getText());
                        int sum = value1 + value2;
                        count[index].setText(String.valueOf(sum));
                        arrowView2.setLayoutX(110 + index * 80); // Posição horizontal da seta
                        arrowView2.setLayoutY(360); // Posição vertical da seta
                    });
                    try {
                        Thread.sleep(800);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Platform.runLater(() -> {pane.getChildren().remove(arrowView2);});
                Button[] saida = new Button[vet.length];

                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < vet.length; i++) {
                    int finalI = i;
                    Platform.runLater(() -> {
                        // Criação dos botões representando o vetor count
                        saida[finalI] = new Button("0");
                        saida[finalI].setLayoutX(110 + finalI * 80);
                        saida[finalI].setLayoutY(400); // Posição vertical dos botões count
                        saida[finalI].setMinHeight(40);
                        saida[finalI].setMinWidth(40);
                        saida[finalI].setFont(new Font(14));
                        pane.getChildren().add(saida[finalI]);
                    });
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                Image arrowImage3 = new Image(getClass().getResourceAsStream("/arrow.png"));
                ImageView arrowView3 = new ImageView(arrowImage2);
                arrowView3.setFitWidth(20);
                arrowView3.setFitHeight(20);
                Platform.runLater(() -> {pane.getChildren().add(arrowView2);});
                Platform.runLater(() -> {pane.getChildren().add(arrowView);});
                Platform.runLater(() -> {pane.getChildren().add(arrowView3);});

                for (int i = saida.length - 1; i >= 0; i--) {
                    int finalI = i;
                    Platform.runLater(() -> {
                        int posVet = Integer.parseInt(vet[finalI].getText());
                        int posCount = Integer.parseInt(count[posVet].getText());
                        saida[posCount-1].setText(vet[finalI].getText());
                        count[posVet].setText(String.valueOf(Integer.parseInt(count[posVet].getText()) - 1));
                        arrowView.setLayoutX(110 + finalI * 80); // Posição horizontal da seta
                        arrowView.setLayoutY(260); // Posição vertical da seta
                        arrowView2.setLayoutX(110 + posVet * 80); // Posição horizontal da seta
                        arrowView2.setLayoutY(360); // Posição vertical da seta
                        arrowView3.setLayoutX(110 + (posCount-1) * 80); // Posição horizontal da seta
                        arrowView3.setLayoutY(460); // Posição vertical da seta
                    });
                    try {
                        Thread.sleep(800);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Platform.runLater(() -> {pane.getChildren().remove(arrowView3);});
                for (int i = 0; i < vet.length; i++) {
                    int finalI = i;
                    Platform.runLater(() -> {
                        vet[finalI].setText(saida[finalI].getText());
                        arrowView.setLayoutX(110 + finalI * 80); // Posição horizontal da seta
                        arrowView.setLayoutY(260); // Posição vertical da seta
                        arrowView2.setLayoutX(110 + finalI * 80); // Posição horizontal da seta
                        arrowView2.setLayoutY(460); // Posição vertical da seta
                    });
                    try {
                        Thread.sleep(800);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                return null;
            }
        };
        Thread thread = new Thread(task);
        thread.start();
    }
}
