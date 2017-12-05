'use strict';

$(document).ready(function() {

    $('#log_in_btn').click(function() {

        var filter = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;

        var email = $('#log_in_email').val();
        var senha = $('#log_in_senha').val();

        if (email === '' || email === null) {

            $('#alertMsg').html('Por favor, informe o seu E-Mail.');
            $('#alertModal').modal();

        } else if (!filter.test(email)) {

            $('#alertMsg').html('Por favor, informe um E-Mail válido.');
            $('#alertModal').modal();

        } else if (senha === '' || senha === null) {

            $('#alertMsg').html('Por favor, informe a sua senha.');
            $('#alertModal').modal();

        } else {

            var obj = {
                email: email,
                password: senha
            };

            $.ajax({
                url: '/api/enterpriseuser/login',
                contentType: 'application/json',
                type: 'POST',
                data: JSON.stringify(obj),
                success: function(data) {

                    var msg = JSON.parse(data).msg;

                    if (msg !== 'success') {

                        $('#alertMsg').html(msg);
                        $('#alertModal').modal();

                    } else {

                        $('#log_in_email').val('');
                        $('#log_in_senha').val('');

                        window.location.href = '/edashboard';

                    }

                }
            });

        }

    });

});
