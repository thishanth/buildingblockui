package de.tuc.buildingblockui.pojo;

public class BuildBlock 
{
    int rowIndex; 
    int columnIndex;
    String numberOfPoints;
    String buildingBlockColor;

    
    public BuildBlock(int rowIndex, int columnIndex, String numberOfPoints, String buildingBlockColor) 
    {
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
        this.numberOfPoints = numberOfPoints;
        this.buildingBlockColor = buildingBlockColor;
    }
    
    public Integer getRowIndex() 
    {
        return rowIndex;
    }

    public void setRowIndex(Integer rowIndex) {
        this.rowIndex = rowIndex;
    }

    public Integer getColumnIndex() {
        return columnIndex;
    }

    public void setColumnIndex(Integer columnIndex) {
        this.columnIndex = columnIndex;
    }

    public String getNumberOfPoints() {
        return numberOfPoints;
    }

    public void setNumberOfPoints(String numberOfPoints) {
        this.numberOfPoints = numberOfPoints;
    }

    public String getBuildingBlockColor() {
        return buildingBlockColor;
    }

    public void setBuildingBlockColor(String buildingBlockColor) {
        this.buildingBlockColor = buildingBlockColor;
    }
    
    
}
