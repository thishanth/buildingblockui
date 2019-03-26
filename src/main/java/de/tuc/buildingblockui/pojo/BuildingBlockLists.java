package de.tuc.buildingblockui.pojo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import javafx.scene.layout.GridPane;

public class BuildingBlockLists 
{
    private static LinkedList<BuildBlock> editedBuildingBlockLists = new LinkedList();
    private static LinkedList<BuildBlock> buildingBlockLists = new LinkedList();

    public static LinkedList<BuildBlock> getBuildingBlockLists() 
    {
        return buildingBlockLists;
    }
    
    
    public static void addBuildingBlockToList(BuildBlock buildBlock) 
    {
        editedBuildingBlockLists.removeIf(b -> (b.getRowIndex() == buildBlock.getRowIndex() && b.getColumnIndex() == buildBlock.getColumnIndex()));
        editedBuildingBlockLists.add(buildBlock);
        buildingBlockLists.add(buildBlock);
    }
    
    /* Create dummy nodes. DELETE THIS FUNCTION IN PRODUCTION or replace with original data from network*/
    public static void createDummy(GridPane gridPane)
    {
        BuildBlock buildBlock;
        Random r = new Random();
        int Low = 0;
        int High = 20;
        int rowIndex, columnIndex;
        
        //List<String> colors = new ArrayList<>(Arrays.asList("blue", "#7aae1a", "red", "yellow"));
        List<String> colors = new ArrayList<>(Arrays.asList("#F65314", "#00A1F1", "#7CBB00", "#FFBB00"));
        List<String> points = new ArrayList<>(Arrays.asList("2", "4"));

        for (int i = 0; i < 2; i++)
        {
            /*random row and column*/
            rowIndex = r.nextInt(High-Low) + Low;
            columnIndex = r.nextInt(High-Low) + Low;
            
            /*random color*/
            String colorStr = colors.get(r.nextInt(4-0) + 0);
            /*random number of points*/
            String pointStr = points.get(r.nextInt(2-0) + 0);
            
            buildBlock = new BuildBlock(rowIndex, columnIndex, pointStr, colorStr);
            buildingBlockLists.add(buildBlock);
        }
    }

    
    /* get the building block if its exist in the given cell */
    public static BuildBlock getInExistingBuildBlockList(int rIndex, int cIndex) 
    {
        System.out.println("row "+rIndex+" column "+cIndex);
        for (BuildBlock b : buildingBlockLists) 
        {
            if (b.getRowIndex() == rIndex && b.getColumnIndex() == cIndex) 
            {
                return b;
            }
        }
        return null;
    }
}
