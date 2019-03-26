package de.tuc.buildingblockui.gui;

import de.tuc.buildingblockui.pojo.BuildBlock;
import de.tuc.buildingblockui.pojo.BuildingBlockLists;
import de.tuc.buildingblockui.utils.ConfigProperty;
import de.tuc.buildingblockui.utils.SemanticRules;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class WorkingArea 
{
    private static GridPane gridPane;
    private static Stage primaryStage;
    
    public static void initWorkingArea(Stage stage) 
    {
        primaryStage = stage;
        primaryStage.setTitle("Woriking Area");

        gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);

        Label label;
        BuildBlock buildBlock = null;
        BuildingBlockLists.createDummy(gridPane);/*only to create dummy nodes. DELETE THIS LINE IN PRODUCTION or replace with original data from network*/
        
        for (int i = 0; i <= ConfigProperty.getNumberOfGridColumns(); i++) 
        {
            for (int j = 0; j <= ConfigProperty.getNumberOfGridRows(); j++) 
            {
                /* get the building block if its exist in the given cell */
                buildBlock = BuildingBlockLists.getInExistingBuildBlockList(i,j);
                /*if the particular cell is empty then create empty building block*/
                if(buildBlock != null)
                {
                    label = new Label("      "+buildBlock.getNumberOfPoints());
                    label.setStyle("-fx-border-color: #DCDCDC; -fx-background-color: "+buildBlock.getBuildingBlockColor()+";");
                }
                else /*if the particular building block is not empty then create a proper build block using its values*/
                {
                    label = new Label("        ");
                    label.setStyle("-fx-border-color: #DCDCDC; -fx-background-color: white;");
                }
                gridPane.add(label, j, i, 1, 1);
            }
        }

        Scene scene = new Scene(gridPane, 800, 400);
        primaryStage.setScene(scene);
        //primaryStage.setScene(scene2);
        primaryStage.show();
        
        /*this event will triger if mouse clicked in any cell*/
        gridPane.addEventHandler(MouseEvent.MOUSE_PRESSED, e ->
        {            
            for( Node node: gridPane.getChildren()) 
            {
                if( node instanceof Label) 
                {
                    if( node.getBoundsInParent().contains(e.getSceneX(),  e.getSceneY()))/*get the clicked cell*/ 
                    {   
                        replaceNode(GridPane.getRowIndex( node), GridPane.getColumnIndex( node), node);             
                        System.out.println( "Node: " + GridPane.getRowIndex( node) + "/" + GridPane.getColumnIndex( node));
                    }
                }
            }
        });
    }

    
    private static void replaceNode(Integer rowIndex, Integer columnIndex, Node node) 
    {
        Platform.runLater(new Runnable() 
        {
            @Override
            public void run() 
            {
                ToolBox.showToolBox(rowIndex, columnIndex, node);/*show options to create or edit or delete building block*/
            }
        });
    }
    
    /*Edit the grid cell*/
    public static void updateGrid(BuildBlock buildBlock, int rowIndex, int columnIndex, Node node)
    {
        gridPane.getChildren().remove(node);
        Label label = new Label("      "+buildBlock.getNumberOfPoints());
        label.setStyle("-fx-border-color: #DCDCDC; -fx-background-color: "+buildBlock.getBuildingBlockColor()+";");
        gridPane.add(label, columnIndex, rowIndex, 1, 1);
        
        //boolean b = SemanticRules.checkSementicRules(rowIndex, columnIndex, gridPane);
    }
    
        
    /*Delete the grid cell and update with empty label */
    public static void deleteGrid(int rowIndex, int columnIndex, Node node)
    {
        gridPane.getChildren().remove(node);
        
        Label label = new Label("        ");
        label.setStyle("-fx-border-color: #DCDCDC; -fx-background-color: white;");
        gridPane.add(label, columnIndex, rowIndex, 1, 1);
        
        //boolean b = SemanticRules.checkSementicRules(rowIndex, columnIndex, gridPane);
    }

}