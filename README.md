### Map File Format
To create your own initial pattern, you have to create a new file in WorldMaps folder (by default).\
The map file format is the following:\
*world_width*\
*world_height*\
*grid containing world_width * world_height chars which meaning is the following:*
1. '0' - dead cell
2. 'X' - alive cell
3. '?' - random cell (50%/50% it is dead/alive cell)

Example file:\
10\
10\
X0X0000000\
0XX0000000\
0X00000000\
0000000000\
0000000000\
0000000000\
0000000000\
0000000000\
0000000000\
0000000000
