# BoardGameConsole

## Synopsis

A console (no UI ) application for a game in which one player moves a single piece around a chessboard. Other than the use of a chessboard, there are no similarities between this game and chess. 
Instead the rules of the game are as follows:
- The board is 8 by 8 in size
- A player starts on the bottom-left square
- A player can move their piece up, down, left or right one square by entering U, D, L or R
respectively
- A random number of squares are ‘landmines’
- The aim of the game is to get to the top side of the board without hitting more than 2
landmines.
- Following each move the game displays the new location and whether any landmines were hit,
plus whether the game has been lost (more than 2 land mines hit) or won (reached the top of the board)


## Build

mvn clean package

## Run

java -jar target/playtime-0.0.1-SNAPSHOT.jar
