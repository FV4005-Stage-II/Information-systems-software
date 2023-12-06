package com.example.POIS.Controllers;


import com.example.POIS.service.SudokuService;
import com.example.POIS.sudokuGeneration.Sudoku;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequiredArgsConstructor
@Api(tags = "Sudoku Controller", description = "API для работы с судоку")
public class MainPageController {

    Sudoku sudoku = new Sudoku();
    @Autowired
    SudokuService sudokuService;


    @ApiOperation("получить судоку")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Судоку успешно получена",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Sudoku.class))
                    }),
            @ApiResponse(
                    responseCode = "400",
                    description = "Некорректный запрос")
    })
    @GetMapping("/get-sudoku")
    @ResponseBody
    public Sudoku addSudoku(Model model) {
        return sudokuService.getSudoku();
    }

    @PostMapping("/check-sudoku")
    @ResponseBody
    public ResponseEntity<String> checkSudoku(@RequestBody Sudoku sudoku) {
        // Проводите проверку судоку и возвращайте результат в формате JSON
        if (sudoku.isValidSudoku(sudoku.getMat())) {
            return ResponseEntity.ok("{\"message\": \"Судоку верно решена\"}");
        } else {
            return ResponseEntity.badRequest().body("{\"message\": \"Судоку неверно решена\"}");
        }
    }
}
