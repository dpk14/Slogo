# API Critique/Description

method should not be part of the API (i.e., it should not be public)
method should be part of the external API because it helps people on the team working in other modules write their code
method should be part of the internal API because it helps future coders within the module extend it

write a simplified (i.e., less than one page) API description of at least one part of the project (both the internal and external API for either simulation, configuration, or visualization) in a markdown file called doc/API_CELLSOCIETY.md within your team's SLogo repository.

Simulation
```java
package simulation;
public abstract class SimulationInitialization{ 
      public void setNeighborOrientation(String neighborType) 
    public void setSimulationShapes(String gridShape, String cellShape) 
    public void initializeCellGrid(int[][] indexGrid, ArrayList<ArrayList<Integer>> constructorInts, ArrayList<ArrayList<Double>> constructorDoubles) //external
    public Map<String, String> getTagNames() //
    public Cell[][] getInitializedCellGrid()
    public boolean getIsTriangle() 
}

package simulation;
public class TheMainClass extends Application { 
      public void start (Stage stage) 
}

package simulation;
public class SimHandler { 
      public SimHandler()
    public Node getGridView()
    public HBox[] getDisplayUnits()
    public void step()
    public void switchSimulation(int newIndex)
    public void resetCurrentSimulation(int resetIndex) 
}

package simulation;
public class Simulation { 
      public Simulation(Cell[][] cellGrid, boolean triangle, List<Cell> cellTypes)
    public Group getGridView() 
    public void step() 
    public void resetGrid(Cell[][] newGrid) 
    public boolean getTriangle() 
    public HBox[] getDisplayUnits()
}
```
SimulationInitialization: The use of a boolean to determine the cell shape is sloppy, because it is difficult for others to extend. If other cell shape types end up being implemented, you would have to create additional boolean getShape methods, and use that information to create completely separate implementations based on the value of the boolean. Also, because SimulationInitialization and Simulation handle and modify the same variable types, they should be unified, and all the initialization methods should be internal to the simulation as the whole. Graphics should be ma
