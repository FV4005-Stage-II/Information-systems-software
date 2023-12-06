document.addEventListener("DOMContentLoaded", function () {
    const sudokuContainer = document.getElementById("sudoku-container");
    const loadSudokuButton = document.getElementById("load-sudoku");
    const checkSudokuButton = document.getElementById("check-sudoku");
    const resultMessage = document.getElementById("result-message");

    let sudokuData = null;

//    loadSudokuButton.addEventListener("click", function () {
        // Выполняем AJAX-запрос для получения судоку
        fetch("/get-sudoku", {
            method: "GET"
        })
            .then(response => {
            if (!response.ok) {
                throw new Error("Ошибка при загрузке судоку");
            }
            return response.json();
        })
            .then(data => {
            // Сохраняем данные судоку
            sudokuData = data;

            // Создаем HTML-таблицу для отображения судоку
            const table = document.createElement("table");

            // Перебираем данные судоку и создаем строки и текстовые поля таблицы
            data.mat.forEach((rowData, rowIndex) => {
                const row = document.createElement("tr");
                rowData.forEach((cellData, columnIndex) => {
                    const cell = document.createElement("td");
                    const input = document.createElement("input");
                    input.type = "text";
                    input.value = cellData === 0 ? "0" : cellData;
                    input.addEventListener("input", function () {
                        // Обновляем данные судоку при вводе
                        sudokuData.mat[rowIndex][columnIndex] = parseInt(input.value) || 0;
                    });
                    cell.appendChild(input);
                    row.appendChild(cell);
                });
                table.appendChild(row);
            });

            // Очищаем контейнер судоку перед добавлением новой таблицы
            sudokuContainer.innerHTML = "";

            // Добавляем таблицу в контейнер
            sudokuContainer.appendChild(table);
        })
            .catch(error => {
            console.error("Ошибка при загрузке судоку:", error);
        });
    });

//    checkSudokuButton.addEventListener("click", function () {
//        if (!sudokuData) {
//            console.error("Судоку не загружено");
//            return;
//        }
//
//        // Отправляем данные на сервер для проверки
//        fetch("/check-sudoku", {
//            method: "POST",
//            headers: {
//                "Content-Type": "application/json"
//            },
//            body: JSON.stringify({ mat: sudokuData.mat })
//        })
//            .then(response => {
//            if (!response.ok) {
//                throw new Error("Ошибка при проверке судоку");
//            }
//            return response.json();
//        })
//            .then(result => {
//            // Обновляем сообщение о результате проверки на странице
//            const resultMessage = document.getElementById("result-message");
//            resultMessage.textContent = result.message;
//        })
//            .catch(error => {
//            console.error("Ошибка при проверке судоку:", error);
//        });
//    });
//    const checkSudokuButton = document.getElementById("check-sudoku");
//    const resultMessage = document.getElementById("result-message");

    //    checkSudokuButton.addEventListener("click", function () {
    //        // Создаем матрицу судоку из значений в таблице
    //        const sudokuData = createSudokuMatrixFromTable();
    //
    //        if (!sudokuData) {
    //            resultMessage.textContent = "Судоку не загружено";
    //            return;
    //        }
    //
    //        // Отправляем данные на сервер для проверки
    //        fetch("/check-sudoku", {
    //            method: "POST",
    //            headers: {
    //                "Content-Type": "application/json"
    //            },
    //            body: JSON.stringify({ mat: sudokuData })
    //        })
    //            .then(response => {
    //            if (!response.ok) {
    //                throw new Error("Ошибка при проверке судоку");
    //            }
    //            return response.json();
    //        })
    //            .then(result => {
    //            // Обновляем сообщение о результате проверки на странице
    //            resultMessage.textContent = result.message;
    //        })
    //            .catch(error => {
    //            // Обработка ошибки и вывод ее в <div id="result-message"></div>
    //            resultMessage.textContent = "Судоку неверно решена";
    //        });
    //    });
    //
    //    // Функция для создания матрицы судоку из значений в таблице
    //    function createSudokuMatrixFromTable() {
    //        const table = document.querySelector("table");
    //        if (!table) {
    //            return null;
    //        }
    //
    //        const sudokuData = [];
    //
    //        table.querySelectorAll("tr").forEach(row => {
    //            const rowData = [];
    //            row.querySelectorAll("td").forEach(cell => {
    //                const value = parseInt(cell.querySelector("input").value) || 0;
    //                rowData.push(value);
    //            });
    //            sudokuData.push(rowData);
    //        });
    //
    //        return sudokuData;
    //    }// Отправляем данные на сервер для проверки

setTimeout(function() {
    location.reload();
}, 5000);

//});
