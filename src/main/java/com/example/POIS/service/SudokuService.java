package com.example.POIS.service;


import com.example.POIS.sudokuGeneration.Sudoku;
import org.springframework.stereotype.Service;

@Service
public class SudokuService {
    Sudoku sudoku;

    public Sudoku getSudoku() {
        sudoku = new Sudoku();
        return sudoku;
    }

    public boolean checkSudoku(int[][] _sudoku) {
        return sudoku.isValidSudoku(_sudoku);
    }
}
