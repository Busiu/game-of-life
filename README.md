# Game of Life
### Installation & Requirements
To compile this project you need JDK with JavaFX - for instance JDK 8.\
If you have JDK with JavaFX yet - follow undermentioned steps:
1. Clone this repo.
2. Having entered it, type "javac -d  $(find src -name *.java)" to compile project.
3. Type "java gameoflife.Displayer" to run project.

### Predefined patterns:
This project contains 9 predefined patterns:
1. 10x10 grid with a single Glider.
2. 50x50 grid where every cell is random.
3. 50x50 grid with a single Queen Bee Shuttle given in project requirements.
4. 50x50 grid with a single Queen Bee Shuttle from:\
https://www.conwaylife.com/w/index.php?title=Queen_bee_shuttle
5. 50x50 grid with a single Tumbler.

Next 4 files are for test's sake - their format is wrong.

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
000?????00\
000?????00\
0000000000
