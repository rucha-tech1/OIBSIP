# Number Guessing Game

## Project Description

Number Guessing Game is a console-based Java game where the player tries to guess a randomly generated number.

The game provides different difficulty levels and gives the player a limited number of attempts. It also includes hints, scoring, high score tracking, winning streaks, and game statistics.

## Features

- Three difficulty levels
- Random number generation
- Limited attempts based on difficulty
- Too High and Too Low hints
- Odd/Even hint system
- Score calculation
- High score tracking
- Winning streak tracking
- Games played and games won statistics
- Win rate calculation
- Play again option
- Final game summary

## Difficulty Levels

| Difficulty | Number Range | Attempts |
|------------|--------------|----------|
| Easy | 1 - 50 | 7 |
| Medium | 1 - 100 | 5 |
| Hard | 1 - 500 | 3 |

## Scoring System

The score depends on:

- Difficulty level
- Number of attempts used
- Whether a hint was used

Higher difficulty gives a higher score.

Using a hint reduces the final score.

## Technologies Used

- Java
- Scanner
- Random
- Conditional Statements
- Loops
- Boolean Logic

## How to Run

### 1. Compile the program

```bash
javac NumberGuessingGame.java
