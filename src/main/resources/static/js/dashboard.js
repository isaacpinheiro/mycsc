'use strict';

$(document).ready(function() {

    var enterpriseUser = null;

    if (localStorage.email === null || localStorage.email === undefined) {

        window.location.href = '/';

    } else {

        if (localStorage.role !== 'common') {
            window.location.href = '/edashboard';
        }

        $('#email_label').html(localStorage.email);

        $.ajax({
            url: '/api/commonuser/email/' + localStorage.email + '.',
            contentType: 'application/json',
            type: 'GET',
            success: function(data) {

                if (data === '') {
                    window.location.href = '/perfil';
                }

            }
        });

        $.ajax({
            url: '/api/enterpriseuser/',
            contentType: 'application/json',
            type: 'GET',
            success: function(data) {

                enterpriseUser = data;

                for (var i=0; i<enterpriseUser.length; i++) {

                    var str = '<option value="' + enterpriseUser[i].user.email + '">' + 
                        enterpriseUser[i].tradeName + '</option>';

                    $('#empresas_opt').append(str);

                }

            }
        });

    }

    $('#sair_btn').click(function() {
        localStorage.removeItem('email');
        localStorage.removeItem('role');
        window.location.href = '/';
    });

    $('#enviar_btn').click(function() {

        var email = $('#empresas_opt').val();
        var tipo = $('#tipo_mensagem').val();
        var content = $('#content').val();

        if (content === '' || content === null) {

            $('#alertMsg').html('Por favor, informe o conteúdo do mensagem.');
            $('#alertModal').modal();

        } else {

            var mensagem = {
                messageType: tipo,
                content: content,
                commonUser: {
                    user: {
                        email: localStorage.email
                    }
                },
                enterpriseUser: {
                    user: {
                        email: email
                    }
                }
            };

            $.ajax({
                url: '/api/message',
                contentType: 'application/json',
                type: 'POST',
                data: JSON.stringify(mensagem),
                success: function(data) {

                    var msg = JSON.parse(data).msg;

                    if (msg !== 'success') {

                        $('#alertMsg').html(msg);
                        $('#alertModal').modal();

                    } else {

                        $('#successMsg').html('Mensagem enviada com sucesso.');
                        $('#successModal').modal();

                    }

                }
            });

        }

    });

});
