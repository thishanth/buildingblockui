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
    
    public static void addBuildingBlockToList(BuildBlock buildBlock) 
    {
        editedBuildingBlockLists.removeIf(b -> (b.getRowIndex() == buildBlock.getRowIndex() && b.getColumnIndex() == buildBlock.getColumnIndex()));
        editedBuildingBlockLists.add(buildBlock);
    }
    
    public static void createDummy(GridPane gridPane)
    {
        BuildBlock buildBlock;
        Random r = new Random();
        int Low = 0;
        int High = 20;
        int rowIndex, columnIndex;
        
        List<String> colors = new ArrayList<>(Arrays.asList("blue", "green", "red", "yellow"));
        List<String> points = new ArrayList<>(Arrays.asList("2", "4"));

        for (int i = 0; i < 101; i++) 
        {
            rowIndex = r.nextInt(High-Low) + Low;
            columnIndex = r.nextInt(High-Low) + Low;
            
            String colorStr = colors.get(r.nextInt(4-0) + 0);
            String pointStr = points.get(r.nextInt(2-0) + 0);
            
            buildBlock = new BuildBlock(rowIndex, columnIndex, pointStr, colorStr);
            buildingBlockLists.add(buildBlock);
        }
    }

    
    public static BuildBlock checkExistingBuildBlockList(int j, int i) 
    {
        for (BuildBlock b : buildingBlockLists) 
        {
            if (b.getRowIndex() == j && b.getColumnIndex() == i) 
            {
                return b;
            }
        }
        return null;
    }
}
