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
    Button botao1, botao2;
    Label labelaux;
    private Button vet[];
    Label label = new Label("Vetor Inicial");
    Label[] labels = new Label[23];
    String verde = "-fx-background-color: #46c846;";
    String azul = "-fx-background-color: #4596dc;";
    String vermelho = "-fx-background-color: #d62626;";
    Label descricao;

    Image arrowImage = new Image(getClass().getResourceAsStream("/arrow.png"));
    ImageView arrowView = new ImageView(arrowImage);
    Image arrowImage2 = new Image(getClass().getResourceAsStream("/arrow.png"));
    ImageView arrowView2 = new ImageView(arrowImage2);

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
        botao_inicio.setText("Quick Sort Sem Pivô");
        pane.getChildren().add(botao_inicio);

        descricao = new Label();
        descricao.setLayoutX(50);
        descricao.setLayoutY(550);
        descricao.setFont(new Font("Arial", 20));
        descricao.setText("Inicie o algoritmo.");
        pane.getChildren().add(descricao);

        label.setLayoutX(5);
        label.setLayoutY(210);
        pane.getChildren().add(label);


        arrowView.setFitWidth(20);
        arrowView.setFitHeight(20);
        arrowView.setLayoutX(10000);
        arrowView.setLayoutY(10000);
        pane.getChildren().add(arrowView);

        arrowView2.setFitWidth(20);
        arrowView2.setFitHeight(20);
        arrowView2.setLayoutX(10000);
        arrowView2.setLayoutY(10000);
        pane.getChildren().add(arrowView2);


        labels[0] = new Label("public void quickSort(){");
        labels[1] = new Label("    quickSP(0, TL-);");
        labels[2] = new Label("}");
        labels[3] = new Label("quickSP(int ini, int fim){");
        labels[4] = new Label("    int i=ini, j=fim,aux;");
        labels[5] = new Label("    boolean flag=true;");
        labels[6] = new Label("    while(i<j){");
        labels[7] = new Label("         if(flag)");
        labels[8] = new Label("              while(i<j && vet[i]<=vet[j])");
        labels[9] = new Label("                   i++;");
        labels[10] = new Label("         else");
        labels[11] = new Label("              while(i<j && vet[i]<=vet[j])");
        labels[12] = new Label("                   j--;");
        labels[13] = new Label("         aux=vet[i];");
        labels[14] = new Label("         vet[i]=vet[j];");
        labels[15] = new Label("         vet[j]=aux;");
        labels[16] = new Label("         flag=!flag;");
        labels[17] = new Label("     }");
        labels[18] = new Label("     if(ini<i-1)");
        labels[19] = new Label("          quickSP(ini,i-1);");
        labels[20] = new Label("     if(j+1<fim)");
        labels[21] = new Label("          quickSP(j+1,fim);");
        labels[22] = new Label("}");

        botao_inicio.setOnAction(e->{ quickSort();});

        for (int i = 0; i < labels.length; i++) {
            labels[i].setLayoutX(1000);
            labels[i].setLayoutY(15 + i * 30);
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

        botao1 = new Button();
        botao1.setLayoutX(400);
        botao1.setLayoutY(50);
        botao1.setMinHeight(40);
        botao1.setMinWidth(40);
        botao1.setFont(new Font(14));

        botao2 = new Button();
        botao2.setLayoutX(500);
        botao2.setLayoutY(50);
        botao2.setMinHeight(40);
        botao2.setMinWidth(40);
        botao2.setFont(new Font(14));

        labelaux= new Label();
        labelaux.setLayoutX(465);
        labelaux.setLayoutY(50);
        labelaux.setMinHeight(40);
        labelaux.setMinWidth(40);
        labelaux.setFont(new Font(14));

        pane.getChildren().add(botao1);
        pane.getChildren().add(botao2);
        pane.getChildren().add(labelaux);

        Scene scene = new Scene(pane, 1400, 700);
        stage.setScene(scene);
        stage.show();
    }

    public void quickSort() {
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() {
                Platform.runLater(() -> {
                    labels[1].setStyle(azul);
                });
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                quickSP(0, vet.length - 1, 0);
                Platform.runLater(() -> {
                    labels[18].setStyle("");
                    labels[21].setStyle("");
                    labels[20].setStyle("");
                    labels[19].setStyle("");
                });
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < vet.length; i++) {
                    int finalI = i;
                    Platform.runLater(() -> {
                        vet[finalI].setLayoutY(200);
                        vet[finalI].setStyle(verde);
                    });
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Platform.runLater(() -> {
                    label.setText("Vetor Ordenado");
                    pane.getChildren().remove(arrowView);
                    pane.getChildren().remove(arrowView2);
                    botao1.setText("");
                    botao2.setText("");
                    descricao.setText("O vetor está ordenado.");
                });
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };
        Thread thread = new Thread(task);
        thread.start();
    }

    public void quickSP(int ini, int fim, int desloc){
        int i = ini, j = fim;
        boolean flag = true;
        Button aux;
        Platform.runLater(() -> {
            labels[1].setStyle("");
            labels[18].setStyle("");
            labels[21].setStyle("");
            labels[20].setStyle("");
            labels[19].setStyle("");
            labels[6].setStyle(azul);
            descricao.setText("Conforme o valor do flag, verifica se elementos das posições i e j estão ordenados entre eles.");
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int k = i; k <= j; k++) {
            int finalK = k;
            Platform.runLater(() -> {
                vet[finalK].setLayoutY(vet[finalK].getLayoutY() + desloc);
            });
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        while (i < j) {
            Platform.runLater(() -> {
                labels[16].setStyle("");
                labels[6].setStyle("");
                labels[7].setStyle(azul);
                descricao.setText("Conforme o valor do flag, verifica se elementos das posições i e j estão ordenados entre eles.");
            });
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (flag) {
                Platform.runLater(() -> {
                    labelaux.setText("<");
                });
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int finalI6 = i;
                int finalJ6 = j;
                Platform.runLater(() -> {
                    labels[7].setStyle("");
                    labels[8].setStyle(azul);
                    arrowView.setLayoutX(vet[finalI6].getLayoutX()+10);
                    arrowView.setLayoutY(vet[finalI6].getLayoutY()+50);
                    arrowView2.setLayoutX(vet[finalJ6].getLayoutX()+10);
                    arrowView2.setLayoutY(vet[finalJ6].getLayoutY()+50);
                    botao1.setText(vet[finalI6].getText());
                    botao2.setText(vet[finalJ6].getText());

                });
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                while (i < j && Integer.parseInt(vet[i].getText()) <= Integer.parseInt(vet[j].getText())) {
                    int ai = i, aj = j;
                    i++;
                    Platform.runLater(() -> {
                        labels[9].setStyle("");
                        botao1.setStyle("");
                        botao2.setStyle("");
                        botao1.setText(vet[ai].getText());
                        botao2.setText(vet[aj].getText());
                        arrowView.setLayoutX(vet[ai].getLayoutX()+10);
                        arrowView.setLayoutY(vet[ai].getLayoutY()+50);
                        arrowView2.setLayoutX(vet[aj].getLayoutX()+10);
                        arrowView2.setLayoutY(vet[aj].getLayoutY()+50);
                    });
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Platform.runLater(() -> {
                        labels[9].setStyle("");
                        labels[8].setStyle(azul);
                        botao1.setStyle(verde);
                        botao2.setStyle(verde);
                    });
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Platform.runLater(() -> {
                        botao1.setStyle("");
                        botao2.setStyle("");
                        labels[9].setStyle(azul);
                        labels[8].setStyle("");
                        arrowView.setLayoutX(vet[ai+1].getLayoutX()+10);
                        arrowView.setLayoutY(vet[ai+1].getLayoutY()+50);
                        arrowView2.setLayoutX(vet[aj].getLayoutX()+10);
                        arrowView2.setLayoutY(vet[aj].getLayoutY()+50);
                    });
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Platform.runLater(() -> {
                        labels[8].setStyle(azul);
                        labels[9].setStyle("");
                    });
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                int finalI1 = i;
                int finalJ1 = j;
                Platform.runLater(() -> {
                    labels[8].setStyle("");
                    botao1.setStyle(vermelho);
                    botao2.setStyle(vermelho);
                    botao1.setText(vet[finalI1].getText());
                    botao2.setText(vet[finalJ1].getText());
                    arrowView.setLayoutX(vet[finalI1].getLayoutX()+10);
                    arrowView.setLayoutY(vet[finalI1].getLayoutY()+50);
                    arrowView2.setLayoutX(vet[finalJ1].getLayoutX()+10);
                    arrowView2.setLayoutY(vet[finalJ1].getLayoutY()+50);
                });
                try {
                    Thread.sleep(950);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                Platform.runLater(() -> {
                    labels[7].setStyle("");
                    labels[10].setStyle(azul);
                });
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.runLater(() -> {
                    labelaux.setText(">");
                });
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int finalI7 = i;
                int finalJ7 = j;
                Platform.runLater(() -> {
                    labels[7].setStyle("");
                    labels[10].setStyle("");
                    labels[11].setStyle(azul);
                    arrowView.setLayoutX(vet[finalI7].getLayoutX()+10);
                    arrowView.setLayoutY(vet[finalI7].getLayoutY()+50);
                    arrowView2.setLayoutX(vet[finalJ7].getLayoutX()+10);
                    arrowView2.setLayoutY(vet[finalJ7].getLayoutY()+50);
                    botao1.setText(vet[finalJ7].getText());
                    botao2.setText(vet[finalI7].getText());
                });
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                while (i < j && Integer.parseInt(vet[i].getText()) <= Integer.parseInt(vet[j].getText())) {
                    int ai = i, aj = j;
                    j--;
                    Platform.runLater(() -> {
                        labels[12].setStyle("");
                        botao1.setStyle("");
                        botao2.setStyle("");
                        botao1.setText(vet[aj].getText());
                        botao2.setText(vet[ai].getText());
                        arrowView.setLayoutX(vet[ai].getLayoutX()+10);
                        arrowView.setLayoutY(vet[ai].getLayoutY()+50);
                        arrowView2.setLayoutX(vet[aj].getLayoutX()+10);
                        arrowView2.setLayoutY(vet[aj].getLayoutY()+50);
                    });
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Platform.runLater(() -> {
                        labels[12].setStyle("");
                        labels[11].setStyle(azul);
                        botao1.setStyle(verde);
                        botao2.setStyle(verde);
                    });
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Platform.runLater(() -> {
                        botao1.setStyle("");
                        botao2.setStyle("");
                        labels[12].setStyle(azul);
                        labels[11].setStyle("");
                    });
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Platform.runLater(() -> {
                        labels[11].setStyle(azul);
                        labels[12].setStyle("");
                        arrowView.setLayoutX(vet[ai].getLayoutX()+10);
                        arrowView.setLayoutY(vet[ai].getLayoutY()+50);
                        arrowView2.setLayoutX(vet[aj-1].getLayoutX()+10);
                        arrowView2.setLayoutY(vet[aj-1].getLayoutY()+50);
                    });
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                int finalI2 = i;
                int finalJ2 = j;
                Platform.runLater(() -> {
                    labels[11].setStyle("");
                    botao1.setStyle(vermelho);
                    botao2.setStyle(vermelho);
                    botao1.setText(vet[finalJ2].getText());
                    botao2.setText(vet[finalI2].getText());
                    arrowView.setLayoutX(vet[finalJ2].getLayoutX()+10);
                    arrowView.setLayoutY(vet[finalJ2].getLayoutY()+50);
                    arrowView2.setLayoutX(vet[finalI2].getLayoutX()+10);
                    arrowView2.setLayoutY(vet[finalI2].getLayoutY()+50);
                });
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            int finalI = i;
            int finalJ5 = j;
            Platform.runLater(() -> {
                labels[8].setStyle("");
                labels[9].setStyle("");
                labels[11].setStyle("");
                labels[12].setStyle("");
                if(finalI != finalJ5){
                    descricao.setText("Realiza a permutação entre os valores não ordenados entre sí.");
                    labels[13].setStyle(azul);
                    labels[14].setStyle(azul);
                    labels[15].setStyle(azul);
                }
            });
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int l = 0; l < 10; l++) {
                int finalI3 = i;
                Platform.runLater(() -> vet[finalI3].setLayoutY(vet[finalI3].getLayoutY() - 5));
                int finalJ3 = j;
                Platform.runLater(() -> vet[finalJ3].setLayoutY(vet[finalJ3].getLayoutY() + 5));
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (int l = 0; l < (j - i) * 80 / 5; l++) {
                int finalI4 = i;
                Platform.runLater(() -> vet[finalI4].setLayoutX(vet[finalI4].getLayoutX() + 5));
                int finalJ = j;
                Platform.runLater(() -> vet[finalJ].setLayoutX(vet[finalJ].getLayoutX() - 5));
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (int l = 0; l < 10; l++) {
                int finalI5 = i;
                Platform.runLater(() -> vet[finalI5].setLayoutY(vet[finalI5].getLayoutY() + 5));
                int finalJ4 = j;
                Platform.runLater(() -> vet[finalJ4].setLayoutY(vet[finalJ4].getLayoutY() - 5));
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Platform.runLater(() -> {
                botao1.setStyle("");
                botao2.setStyle("");
            });
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            aux = vet[i];
            vet[i] = vet[j];
            vet[j] = aux;
            flag = !flag;
            Platform.runLater(() -> {
                labels[13].setStyle("");
                labels[14].setStyle("");
                labels[15].setStyle("");
                labels[16].setStyle(azul);
            });
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Platform.runLater(() -> {
                labels[16].setStyle("");
                labels[6].setStyle(azul);
            });
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Platform.runLater(() -> {
            labels[6].setStyle("");
            labels[16].setStyle("");
            labels[18].setStyle(azul);
            descricao.setText("Realiza chamada recursiva passando os intervalos do vetor que não possuem elementos ordenados.");
        });
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(ini < i - 1) {
            Platform.runLater(() -> {
                labels[18].setStyle("");
                labels[19].setStyle(azul);
            });
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            quickSP(ini, i - 1, 50);
        }
        Platform.runLater(() -> {
            labels[16].setStyle("");
            labels[18].setStyle("");
            labels[20].setStyle(azul);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(j + 1 < fim) {
            Platform.runLater(() -> {
                labels[20].setStyle("");
                labels[21].setStyle(azul);
            });
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            quickSP(j + 1, fim, 50);
        }
    }
}
