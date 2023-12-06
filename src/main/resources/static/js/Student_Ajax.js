$(document).ready(function () {
    $.ajax({
        type: 'GET',
        url: '/all-student', // Путь к вашему контроллеру
        success: function(students) {

            var studentListHtml = '';
            for (var i = 0; i < students.length; i++) {
                studentListHtml += '<label id-student="' + students[i].id + '" class="students"> имя: ' + students[i].name + '<br>email: ' + students[i].email + '<br>балл: ' + students[i].exam_scores + '</label>';
                studentListHtml += '<div id-student="' + students[i].id + '" class="students"><br><button id="closeButton" class="close-button"><span>&times;</span></button></div>';
            }
            //studentListHtml += '</div>';


            $('.right-column').html(studentListHtml);
            var deleteButtons = document.querySelectorAll(".close-button");


            deleteButtons.forEach(function(button) {
                button.addEventListener("click", function() {

                    var studentId = button.parentElement.getAttribute("id-student");


                    $.ajax({
                        url: "/delete-student/" + studentId,
                        type: "DELETE",
                        success: function(response) {

                            document.querySelector(".students[id-student='"+studentId+"']").remove();
                            document.querySelector(".students[id-student='"+studentId+"']").remove();

                        },
                        error: function(error) {
                            // Обработка ошибки удаления студента
                            console.error("Ошибка удаления студента: " + error);
                        }
                    });
                });
            });
        },
        error: function() {
            console.log('Ошибка при загрузке студентов');
        }
    });


    $('#addStudentForm').submit(function (event) {
        event.preventDefault();

        // Получение данных из формы
        var formData = {
            name: $('#name').val(),
            email: $('#email').val(),
            exam_scores: $('#exam_scores').val()
        };


        $.ajax({
            type: 'POST',
            url: '/add-student',
            data: JSON.stringify(formData),
            contentType: 'application/json',
            success: function (response) {

                if (response === true) {
                    $('#resultMessage').text('Студент успешно добавлен.');
                } else {
                    $('#resultMessage').text('Ошибка при добавлении студента.');
                }
                $.ajax({
                        type: 'GET',
                        url: '/all-student', // Путь к вашему контроллеру
                        success: function(students) {

                            var studentListHtml = '';
                            for (var i = 0; i < students.length; i++) {
                                studentListHtml += '<label id-student="' + students[i].id + '" class="students"> имя: ' + students[i].name + '<br>email: ' + students[i].email + '<br>балл: ' + students[i].exam_scores + '</label>';
                                studentListHtml += '<div id-student="' + students[i].id + '" class="students"><br><button id="closeButton" class="close-button"><span>&times;</span></button></div>';
                            }
                            //studentListHtml += '</div>';


                            $('.right-column').html(studentListHtml);
                            var deleteButtons = document.querySelectorAll(".close-button");


                            deleteButtons.forEach(function(button) {
                                button.addEventListener("click", function() {

                                    var studentId = button.parentElement.getAttribute("id-student");


                                    $.ajax({
                                        url: "/delete-student/" + studentId,
                                        type: "DELETE",
                                        success: function(response) {

                                            document.querySelector(".students[id-student='"+studentId+"']").remove();
                                            document.querySelector(".students[id-student='"+studentId+"']").remove();

                                        },
                                        error: function(error) {
                                            // Обработка ошибки удаления студента
                                            console.error("Ошибка удаления студента: " + error);
                                        }
                                    });
                                });
                            });
                        },
                        error: function() {
                            console.log('Ошибка при загрузке студентов');
                        }
                    });
            },
            error: function () {
                $('#resultMessage').text('Ошибка при отправке запроса.');
            }
        });
    });


    $('#SearchStudentForm').submit(function (event) {
        event.preventDefault();


        var searchName = $('#SearchName').val();

        // Отправка запроса на сервер для поиска студента
        $.ajax({
            type: 'GET',
            url: '/search-student',
            data: { name: searchName },
            success: function (response) {

                $('#resultSearchMessage').empty();

                if (response.length > 0) {
                    var resultList = $('<ul>');
                    $.each(response, function (index, student) {
                        //console.log('Имя: ' + student.name + ', Email: ' + student.email);
                        var listItem = $('<li>').text('Имя: ' + student.name + ', Email: ' + student.email + ', балл: ' + student.exam_scores  );
                        resultList.append(listItem);
                    });
                    $('#resultSearchMessage').append(resultList);
                } else {

                    $('#resultSearchMessage').text('Студенты с таким именем не найдены.');
                }
            },
            error: function () {
                $('#resultSearchMessage').text('Ошибка при отправке запроса.');
            }
        });
    });




});
