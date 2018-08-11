package de.tuc.buildingblockui;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.stage.Stage;
import de.tuc.buildingblockui.gui.WorkingArea;
import de.tuc.buildingblockui.pojo.BuildingBlockLists;


public class MainApp extends Application 
{
    @Override
    public void start(Stage primaryStage) throws Exception 
    {
        WorkingArea.initWorkingArea(primaryStage);        
    }

    
    public static void main(String[] args) 
    {
        launch(args);
    }

}
