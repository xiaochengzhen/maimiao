package com.example.generatefile.service;

import com.example.generatefile.handle.CCLTN05Handler;
import com.example.generatefile.handle.GenerateFilesHandler;
import com.example.generatefile.model.CCLTN05Request;
import com.example.generatefile.model.GenerateDTO;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @description 
 * @author xiaobo
 * @date 2024/2/2 12:47
 */
public class CCLTN05GUIFileGenerator extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        // 创建界面元素
        Label tradeDateLabel = new Label("交易时间(yyyyMMdd):");
        TextField tradeDateField = new TextField();
        Label settlementDateLabel = new Label("结算时间(yyyyMMdd):");
        TextField settlementDateField = new TextField();

        Label valueLabel = new Label("标的代码|方向（B/S）|交易时间（HHMM）|数量|价格");
        List<TextField> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            TextField valueField = new TextField();
            list.add(valueField);
        }

        Button generateButton = new Button("生成文件");

        GridPane gridPane = new GridPane();
        //Setting size for the pane
        gridPane.setMinSize(400, 200);
        //Setting the padding
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        //Setting the vertical and horizontal gaps between the columns
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        //Setting the Grid alignment
       // gridPane.setAlignment(Pos.CENTER);
        //Arranging all the nodes in the grid
        gridPane.add(tradeDateLabel, 0, 0);
        gridPane.add(tradeDateField, 0, 1);
        gridPane.add(settlementDateLabel, 0, 3);
        gridPane.add(settlementDateField, 0, 4);
        gridPane.add(valueLabel, 0, 5);
        for (int i = 0; i < list.size(); i++) {
            TextField textField = list.get(i);
            gridPane.add(textField, 0, 6+i);
        }
        gridPane.add(generateButton, 0, 6+list.size());
        //Styling nodes
        generateButton.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
       // valueField.setStyle("-fx-font: normal bold 20px 'serif' ");
        /* settlementDateField.setStyle("-fx-font: normal bold 20px 'serif' ");*/
        gridPane.setStyle("-fx-background-color: BEIGE;");
        //Creating a scene object
        Scene scene = new Scene(gridPane);
        //Setting title to the Stage
        stage.setTitle("CCLTN05文件生成");
        //Adding scene to the stage
        stage.setScene(scene);
        //Displaying the contents of the stage
        stage.show();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(null);
        alert.setHeaderText(null);

        // 设置按钮点击事件
        generateButton.setOnAction(e -> {
            try {
                String text1 = tradeDateField.getText();
                String text2 = settlementDateField.getText();
                if (StringUtils.isBlank(text1) || StringUtils.isBlank(text1) || CollectionUtils.isEmpty(list)) {
                    alert.setContentText("老哥，填信息");
                } else {
                    CCLTN05Request ccltn05Request = new CCLTN05Request();
                    ccltn05Request.setTradeDate(text1);
                    ccltn05Request.setSettlementDate(text2);
                    List<CCLTN05Request.GenerateSymbol> generateSymbols = new ArrayList<>();
                    for (TextField textField : list) {
                        String text = textField.getText();
                        if (StringUtils.isNotBlank(text)) {
                            CCLTN05Request.GenerateSymbol generateSymbol = new CCLTN05Request.GenerateSymbol();
                            String[] split = StringUtils.split(text, "|");
                            generateSymbol.setStockCode(split[0]);
                            generateSymbol.setSell(split[1]);
                            generateSymbol.setTradeTime(split[2]);
                            generateSymbol.setTradeQuantity(split[3]);
                            generateSymbol.setTradePrice(split[4]);
                            generateSymbols.add(generateSymbol);
                        }
                    }
                    ccltn05Request.setGenerateSymbols(generateSymbols);
                    generateFile(ccltn05Request);
                    alert.setContentText("文件生成成功，D:/CCLTN05.txt");
                    stage.close();
                }
            } catch (Exception ex) {
                alert.setContentText("文件生成失败");
                ex.printStackTrace();
            }
            alert.showAndWait();
        });

    }

    private void generateFile(CCLTN05Request ccltn05Request) {
        GenerateDTO generateDTO = new GenerateDTO();
        generateDTO.setGenerateFilesRequest(ccltn05Request);
        GenerateFilesHandler generateFilesHandler = new CCLTN05Handler();
        generateFilesHandler.generateFiles(generateDTO);
    }
}
