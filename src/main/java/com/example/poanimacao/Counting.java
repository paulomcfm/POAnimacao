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
import javafx.stage.Stage;
import javafx.scene.text.Font;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Counting extends Application {
    AnchorPane pane;
    Button botao_inicio;
    private Button vet[];
    Label descricao;
    public static void main(String[] args)
    {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Pesquisa e Ordenacao");
        pane = new AnchorPane();
        pane.setStyle("-fx-background-color: white;");
        Button botao_inicio = new Button();
        botao_inicio.setLayoutX(50);
        botao_inicio.setLayoutY(100);
        botao_inicio.setText("Counting Sort");
        pane.getChildren().add(botao_inicio);

        descricao = new Label();
        descricao.setLayoutX(150);
        descricao.setLayoutY(100);
        descricao.setFont(new Font("Arial", 20));
        descricao.setText("Inicie o algoritmo.");
        pane.getChildren().add(descricao);


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

        Label[] labels = new Label[16];

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
        labels[10] = new Label("for(int i=TL-1;i>=0;i--){");
        labels[11] = new Label("    saida[cont[vet[i]]-1]=vet[i];");
        labels[12] = new Label("    cont[vet[i]]--;");
        labels[13] = new Label("}");
        labels[14] = new Label("for(int i=0;i<TL;i++)");
        labels[15] = new Label("    vet[i]=saida[i];");

        botao_inicio.setOnAction(e->{ countingSort(labels);});

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

        Scene scene = new Scene(pane, 1400, 600);
        stage.setScene(scene);
        stage.show();
    }

    public void countingSort(Label[] labels)
    {
        Image arrowImage = new Image(getClass().getResourceAsStream("/arrow.png"));
        ImageView arrowView = new ImageView(arrowImage);
        arrowView.setFitWidth(20);
        arrowView.setFitHeight(20);
        pane.getChildren().add(arrowView);
        String azul ="-fx-background-color: #4596dc;";
        Task<Void> task = new Task<Void>(){
            @Override
            protected Void call() {
                int maior = Integer.parseInt(vet[0].getText());
                Platform.runLater(() -> {
                    labels[0].setStyle(azul);
                    vet[0].setStyle("-fx-background-color: #ee4545;");
                    arrowView.setLayoutX(vet[0].getLayoutX() + vet[0].getWidth() / 2 - arrowView.getFitWidth() / 2);
                    arrowView.setLayoutY(260);
                    descricao.setText("Procurando o maior elemento do vetor.");
                });
                try {
                    Thread.sleep(800);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int indexMaior = 0;
                for (int i = 1; i < vet.length; i++) {
                    Platform.runLater(() -> {
                        labels[0].setStyle("");
                        labels[1].setStyle(azul);
                    });
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    final int index = i;
                    Platform.runLater(() -> {
                        labels[1].setStyle("");
                        labels[2].setStyle(azul);
                    });
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Platform.runLater(() -> {
                        labels[2].setStyle("");
                    });
                    if (Integer.parseInt(vet[i].getText()) > maior) {
                        int finalIndexMaior = indexMaior;
                        Platform.runLater(() -> {
                            arrowView.setLayoutX(110 + index * 80);
                            arrowView.setLayoutY(260);
                            vet[finalIndexMaior].setStyle("");
                            vet[index].setStyle("-fx-background-color: #ee4545;");
                            labels[3].setStyle(azul);
                        });
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        maior = Integer.parseInt(vet[i].getText());
                        indexMaior = index;
                    }
                    Platform.runLater(() -> {
                        labels[3].setStyle("");
                        labels[1].setStyle(azul);
                        arrowView.setLayoutX(110 + index * 80);
                        arrowView.setLayoutY(260);
                    });
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (i == vet.length - 1) {
                        int finalIndexMaior = indexMaior;
                        Platform.runLater(() -> vet[finalIndexMaior].setStyle(""));
                    }
                }
                Platform.runLater(() -> {
                    pane.getChildren().remove(arrowView);
                    labels[1].setStyle("");
                });
                try {
                    Thread.sleep(800);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Button[] count = new Button[maior + 1];
                Platform.runLater(() -> {
                    descricao.setText("Cria-se um vetor do tamanho do maior elemento mais um.");
                    labels[4].setStyle(azul);
                });
                try {
                    Thread.sleep(800);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int finalMaior = maior;
                for (int i = 0; i <= finalMaior; i++) {
                    int finalI = i;
                    Platform.runLater(() -> {
                        count[finalI] = new Button("0");
                        count[finalI].setLayoutX(110 + finalI * 80);
                        count[finalI].setLayoutY(300);
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
                Platform.runLater(() -> {
                    labels[4].setStyle("");
                    descricao.setText("Incrementando o vetor de contagem na posição do elemento em um.");
                });
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                for (int i = 0; i < vet.length; i++) {
                    Platform.runLater(() -> {
                        labels[5].setStyle(azul);
                        labels[6].setStyle("");
                    });
                    try {
                        Thread.sleep(800);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    int value = Integer.parseInt(vet[i].getText());
                    int finalI = i;
                    Platform.runLater(() -> {
                        labels[5].setStyle("");
                        labels[6].setStyle(azul);
                        count[value].setText(String.valueOf(Integer.parseInt(count[value].getText()) + 1));
                        arrowView.setLayoutX(110 + finalI * 80);
                        arrowView.setLayoutY(260);
                        arrowView2.setLayoutX(110 + value * 80);
                        arrowView2.setLayoutY(360);
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
                Platform.runLater(() -> {
                    pane.getChildren().add(arrowView2);
                    labels[6].setStyle("");
                    labels[7].setStyle(azul);
                    descricao.setText("Incrementa o valor da posição com o valor da posição anterior.");
                });
                arrowView2.setLayoutX(110 * 80);
                arrowView2.setLayoutY(360);
                try {
                    Thread.sleep(800);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int value = Integer.parseInt(count[0].getText());
                System.out.println(value);

                for (int i = 1; i < count.length; i++) {
                    final int index = i;
                    Platform.runLater(() -> {
                        labels[7].setStyle("");
                        labels[8].setStyle(azul);
                        int value1 = Integer.parseInt(count[index].getText());
                        int value2 = Integer.parseInt(count[index - 1].getText());
                        int sum = value1 + value2;
                        count[index].setText(String.valueOf(sum));
                        arrowView2.setLayoutX(110 + index * 80);
                        arrowView2.setLayoutY(360);
                    });
                    try {
                        Thread.sleep(800);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Platform.runLater(() -> {
                        labels[8].setStyle("");
                        labels[7].setStyle(azul);
                    });
                    try {
                        Thread.sleep(800);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Platform.runLater(() -> {
                    descricao.setText("Cria-se um vetor do mesmo tamanho do vetor inicial para receber os elementos ordenados.");
                    pane.getChildren().remove(arrowView2);
                });
                Button[] saida = new Button[vet.length];

                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                for (int i = 0; i < vet.length; i++) {
                    int finalI = i;
                    Platform.runLater(() -> {
                        labels[7].setStyle("");
                        labels[9].setStyle(azul);
                        saida[finalI] = new Button("0");
                        saida[finalI].setLayoutX(110 + finalI * 80);
                        saida[finalI].setLayoutY(400);
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

                ImageView arrowView3 = new ImageView(arrowImage2);
                arrowView3.setFitWidth(20);
                arrowView3.setFitHeight(20);
                Platform.runLater(() -> {pane.getChildren().add(arrowView2);});
                Platform.runLater(() -> {pane.getChildren().add(arrowView);});
                Platform.runLater(() -> {pane.getChildren().add(arrowView3);});
                Platform.runLater(() -> {
                    labels[9].setStyle("");
                    labels[10].setStyle(azul);
                    descricao.setLayoutY(50);
                    descricao.setText("Varrendo o vetor inicial em ordem descrescente, o vetor ordenado recebe o valor inicial\n referente a posição do vetor de contagem.\nDecrementa-se o vetor de contagem naquela posição para o caso de valores\n iguais no vetor inicial.");
                });
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int i = saida.length - 1; i >= 0; i--) {
                    int finalI = i;
                    Platform.runLater(() -> {
                        labels[10].setStyle("");
                        labels[11].setStyle(azul);
                        int posVet = Integer.parseInt(vet[finalI].getText());
                        int posCount = Integer.parseInt(count[posVet].getText());
                        saida[posCount-1].setText(vet[finalI].getText());
                        arrowView.setLayoutX(110 + finalI * 80);
                        arrowView.setLayoutY(260);
                        arrowView2.setLayoutX(110 + posVet * 80);
                        arrowView2.setLayoutY(360);
                        arrowView3.setLayoutX(110 + (posCount-1) * 80);
                        arrowView3.setLayoutY(460);
                    });
                    try {
                        Thread.sleep(800);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Platform.runLater(() -> {
                        labels[11].setStyle("");
                        labels[12].setStyle(azul);
                        int posVet = Integer.parseInt(vet[finalI].getText());
                        count[posVet].setText(String.valueOf(Integer.parseInt(count[posVet].getText()) - 1));
                    });
                    try {
                        Thread.sleep(800);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Platform.runLater(() -> {
                        labels[12].setStyle("");
                        labels[10].setStyle(azul);
                    });
                    try {
                        Thread.sleep(800);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Platform.runLater(() -> {pane.getChildren().remove(arrowView3);});
                Platform.runLater(() -> {
                    labels[10].setStyle("");
                    labels[14].setStyle(azul);
                    descricao.setLayoutY(100);
                    descricao.setText("O vetor inicial recebe os valores ordenados do vetor ordenado.");
                });
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                for (int i = 0; i < vet.length; i++) {
                    int finalI = i;
                    Platform.runLater(() -> {
                        labels[14].setStyle("");
                        labels[15].setStyle(azul);
                        vet[finalI].setText(saida[finalI].getText());
                        vet[finalI].setStyle("-fx-background-color: #4ee749;");
                        arrowView.setLayoutX(110 + finalI * 80);
                        arrowView.setLayoutY(260);
                        arrowView2.setLayoutX(110 + finalI * 80);
                        arrowView2.setLayoutY(460);
                    });
                    try {
                        Thread.sleep(800);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Platform.runLater(() -> {
                        labels[15].setStyle("");
                        labels[14].setStyle(azul);
                    });
                    try {
                        Thread.sleep(800);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Platform.runLater(() -> {
                    labels[14].setStyle("");
                    pane.getChildren().remove(arrowView);
                    pane.getChildren().remove(arrowView2);
                    descricao.setText("O vetor está ordenado.");
                });
                try {
                    Thread.sleep(800);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                return null;
            }
        };
        Thread thread = new Thread(task);
        thread.start();
    }
}
