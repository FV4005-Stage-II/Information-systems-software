package com.example.POIS.Controllers;

import com.example.POIS.data.Student;
import com.example.POIS.dto.StudentDto;
import com.example.POIS.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Api(tags = "Sudoku Controller", description = "API для работы с студентами")
@Validated
public class StudentController {
    @Autowired
    StudentService studentService;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<String> validationError(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        final List<FieldError> fieldErrors = result.getFieldErrors();

        return new ResponseEntity<String>("ERROR", HttpStatus.BAD_REQUEST);
    }

    @ApiOperation("Добавить студента")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Студент успешно добавлен",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Boolean.class))
                    }),
            @ApiResponse(
                    responseCode = "400",
                    description = "Некорректный запрос")
    })
    @PostMapping("/add-student")
    @ResponseBody
    public boolean addStudent(@RequestBody @Valid StudentDto newStudentDto, BindingResult result)  {
        if (result.hasErrors()) {

            return false;
        }
        else {
            return studentService.saveStudent(newStudentDto);
        }
    }

    @ApiOperation("найти студента")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Студент успешно найден",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Student.class))
                    }),
            @ApiResponse(
                    responseCode = "400",
                    description = "Студента не существует")
    })
    @GetMapping("/search-student")
    @ResponseBody
    public List<Student> searchStudent(@RequestParam(name = "name")
                                       @NotBlank(message = "Имя не должно быть пустым")
                                       @NotEmpty(message = "Имя не должно быть пустым") String name) {
        return studentService.getStudent(name);
    }

    @GetMapping("/all-student")
    @ResponseBody
    public List<Student> getAllStudents() {
        return studentService.getAllStudent();
    }

    @DeleteMapping("/delete-student/{studentId}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long studentId) {
        try {
            studentService.deleteStudent(studentId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ошибка удаления студента: " + e.getMessage());
        }
    }
}
