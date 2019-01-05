# java-swing-hexes

This is a bare-bones implementation of hexagonial tiles in a 2D environment using JavaSwing.

I have included some basic function of generating, placing and moving agents on a topological map. I use an axial coordinate system and as default the offset-to-right map layout.

The implementation is based on Red Blob Games <a href="https://www.redblobgames.com/grids/hexagons/">fantastic guide on hexagonal grids</a>.

The relevant java file is `/src/gui/Grid.java`, which is the JPanel that draws the game world. This is the only class that needs to do mathy polygonial stuff (and the only interesting one). Agents and the gameworld classes use the usual 2d coordinate system just as if they were using square tiles.

The rest of the files are simply there to make this work as a demo so you can see how the world looks like. Inside `/src/simulation/Simulation.java`, you can adjust the number of hexes (by changing world size X and Y) and the number of agents and ticks (rounds). 

The Grid class has three elements that are interesting:
1. `int HEXSIZE = 15` [line 11] -> adjusts the size of a hexagon
2. `boolean isPointyTop = true` [line 12] -> if `true`, hexagons are pointy. If `false`, hexagons are flat.
3. `if (ay % 2 == 1)` [line 66] or 
   `if (ax % 2 == 1)` [line 75] -> this checks whether the current row or column is odd or even. If you change the `1` to `0`, the offset will start with the first (0th) row instead of the second (1st) row in the Pointy case, and column in the Flat case. This is pure preference for this demo.

Screenshot of a 10x10 hex map with 13 yellow agents, moving randomly:

![Screenshot of hexagons](hex_snap.PNG?raw=true "10x10 Hexagons")

Difference between the pointy and the flat hexagons is also illustrated using the two screenshots. I have coloured the first row of tiles to show how they are aligned. Hex <0,0> is red, Hex <0,1> is orange, and all subsequent <0,X> Hexes are yellow.

![Screenshots comparing pointy and flat hexagons](hex_pointy_flat_comp.png?raw=true "Pointy and Flat Hexagons")
