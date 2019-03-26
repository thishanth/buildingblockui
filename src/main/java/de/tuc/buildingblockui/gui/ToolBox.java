package de.tuc.buildingblockui.gui;

import de.tuc.buildingblockui.pojo.BuildBlock;
import de.tuc.buildingblockui.pojo.BuildingBlockLists;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ToolBox 
{
    private static String numberOfPoints = null;
    private static String colorOfBuildBlock = null;
    private static Stage stage = null;
    private static BuildBlock buildBlock = null;

    
    /*show options to create or edit building block*/
    public static void showToolBox(int rowIndex, int columnIndex, Node node) 
    {
        numberOfPoints = null;
        colorOfBuildBlock = null;
        
        stage = new Stage();
        stage.setTitle("Tool Box");
        Label label = new Label("Number of Points");
        label.setStyle("-fx-font-size: 18px;");
                
        HBox root = new HBox();
        Scene scene = new Scene(root, 300, 220);
        stage.setScene(scene);

        ToggleGroup group = new ToggleGroup();
        RadioButton rb1 = new RadioButton("2    ");
        rb1.setToggleGroup(group);
        rb1.setStyle("-fx-font-size: 15px;");
        RadioButton rb2 = new RadioButton("4");
        rb2.setToggleGroup(group);
        rb2.setStyle("-fx-font-size: 15px;");
        
        root.getChildren().add(rb1);
        root.getChildren().add(rb2); 

        Label label2 = new Label("Color");
        label2.setStyle("-fx-font-size: 18px;");    
        
        HBox colorRoot = new HBox();
        ToggleGroup colorGroup = new ToggleGroup();
        RadioButton green = new RadioButton("#7CBB00");
        green.setToggleGroup(colorGroup);
        green.setStyle("-fx-font-size: 15px; -fx-background-color: #7CBB00;");
        RadioButton red = new RadioButton("#FFBB00");
        red.setToggleGroup(colorGroup);
        red.setStyle("-fx-font-size: 15px; -fx-background-color: #FFBB00;");
        RadioButton blue = new RadioButton("#00A1F1");
        blue.setToggleGroup(colorGroup);
        blue.setStyle("-fx-font-size: 15px; -fx-background-color: #00A1F1;");
        RadioButton gray = new RadioButton("#F65314");
        gray.setToggleGroup(colorGroup);
        gray.setStyle("-fx-font-size: 15px; -fx-background-color: #F65314;");
        colorRoot.getChildren().addAll(green, red, blue, gray);
        
        HBox button = new HBox();
        Button ok = new Button("Save");
        Button cancel = new Button("Cancel");
        Button delete = new Button("Delete");
        Button lockTile = new Button("Acquire Lock");
        Button updateOnFabric = new Button("Save in Fabric Network");
        button.getChildren().addAll(ok, cancel, delete);
        
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10); 
        gridPane.setVgap(10); 
        gridPane.add(label, 0, 0);
        gridPane.add(root, 0, 1);
        gridPane.add(label2, 0, 3);
        gridPane.add(colorRoot, 0, 4);
        gridPane.add(button, 0, 5);
        gridPane.add(lockTile, 0, 7);
        gridPane.add(updateOnFabric, 0, 8);

        scene.setRoot(gridPane);
        stage.show();
        
        /*number of points radio button*/
        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>()
        {
            public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) 
            {
                RadioButton chk = (RadioButton)new_toggle.getToggleGroup().getSelectedToggle();
                numberOfPoints = Character.toString(chk.getText().toString().charAt(0));                
            } 
        });
        
        
        /*color of the buildblock*/        
        colorGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>()
        {
            public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) 
            {
                RadioButton chk = (RadioButton)new_toggle.getToggleGroup().getSelectedToggle();
                colorOfBuildBlock = chk.getText().toString();
            } 
        });
        
        
        /*EVENT: button Okay clicked*/
        ok.setOnAction(new EventHandler<ActionEvent>() 
        {
            @Override
            public void handle(ActionEvent event) 
            {
                if (numberOfPoints != null && colorOfBuildBlock != null) 
                {
                    stage.close();                    
                    buildBlock = new BuildBlock(rowIndex, columnIndex, numberOfPoints, colorOfBuildBlock);
                    WorkingArea.updateGrid(buildBlock, rowIndex, columnIndex, node);
                    BuildingBlockLists.addBuildingBlockToList(buildBlock);
                    System.out.println("All sets!");
                }
                else
                {
                    Label warningLabel = new Label("Please select the color and \n number of points");
                    warningLabel.setStyle("-fx-font-size: 18px;");
                    gridPane.add(warningLabel, 0, 6);
                    buildBlock = null;
                }
            }
        });
        
        
        /*EVENT: button Calcel clicked*/
        cancel.setOnAction(new EventHandler<ActionEvent>() 
        {
            @Override
            public void handle(ActionEvent event) 
            {
                stage.close();
                System.out.println("cancel clicked!");
            }
        });
        
        
        /*EVENT: button Delete clicked*/
        delete.setOnAction(new EventHandler<ActionEvent>() 
        {
            @Override
            public void handle(ActionEvent event) 
            {
                if (BuildingBlockLists.getInExistingBuildBlockList(rowIndex, columnIndex) != null) 
                {
                    System.out.println("Valuable building block!");
                    buildBlock = new BuildBlock(rowIndex, columnIndex, "", "white");
                    WorkingArea.deleteGrid(rowIndex, columnIndex, node);
                    BuildingBlockLists.addBuildingBlockToList(buildBlock);
                    stage.close();   
                }
                System.out.println("Delete clicked!");
            }
        });
    }
    
}
