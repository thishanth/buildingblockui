package de.tuc.buildingblockui.utils;

import de.tuc.buildingblockui.pojo.BuildBlock;
import de.tuc.buildingblockui.pojo.BuildingBlockLists;
import java.util.LinkedList;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

public class SemanticRules 
{

    /*function to check the list of sementic rules*/
    public static boolean checkSementicRules(int rowIndex, int columnIndex, GridPane gridPane) 
    {
        checkBridge(rowIndex, columnIndex, gridPane);
        return false;
    }
    
    
    /*this function checking the following two things
        1. check the below column is empty or not
        2. check if clicked grid is a bridge*/
    private static boolean checkBridge(int rowIndex, int columnIndex, GridPane gridPane) 
    {        
        boolean belowEmpty;
        if (rowIndex == ConfigProperty.getNumberOfGridRows()) { return true; }

        belowEmpty = isBelowColumnEmpty(rowIndex, columnIndex, gridPane);// checking the below colom empty or not
        
        if (columnIndex == ConfigProperty.getNumberOfGridColumns() || columnIndex == 0) 
        {
            return !belowEmpty;
        }
        else
        {
            //check the if its trying to place in a bridge.
            //check the bridge
        }
        return false;
    }

    
    /*this function check the bwlow column empty or not*/
    private static boolean isBelowColumnEmpty(int rowIndex, int columnIndex, GridPane gridPane) 
    {
        
        for (Node node : gridPane.getChildren()) 
        {
            if(GridPane.getRowIndex(node) >= rowIndex && GridPane.getColumnIndex(node) == columnIndex) 
            {
                return checkIfBuildingBlockExist(GridPane.getRowIndex(node), GridPane.getColumnIndex(node));
            }
        }
        return false;
    }

    
    /*this function is a helper to isBelowColumnEmpty function. this is checking the following two things.
      All the grids includes labels(blank label and information(points + color) added label)
      so it is important to check the selected grid and below grid have valuable label(points + color)  
      Hence, this function checking the above stated conditions*/
    private static boolean checkIfBuildingBlockExist(int rowIndex, int columnIndex) 
    {
        LinkedList<BuildBlock> buildingBlockLists = BuildingBlockLists.getBuildingBlockLists();
        int currentRowIndex = rowIndex - 1;
        System.out.println("ROW "+rowIndex+" COL "+columnIndex);
        
        for (BuildBlock buildingBlock : buildingBlockLists) 
        {
            if ((buildingBlock.getRowIndex() == rowIndex && buildingBlock.getColumnIndex() == columnIndex) || 
                (buildingBlock.getRowIndex() == currentRowIndex && buildingBlock.getColumnIndex() == columnIndex)) 
            {
                System.out.println("***************++++++++++++++++");
                System.out.println("row "+rowIndex+" col "+columnIndex);
                System.out.println("***************++++++++++++++++");
                return true;
            }
        }
        return false;
    }
    
}
