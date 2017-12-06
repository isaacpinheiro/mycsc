'use strict';

$(document).ready(function() {

    var commonUser = {};

    if (localStorage.email === null || localStorage.email === undefined) {

        window.location.href = '/';

    } else {

        if (localStorage.role !== 'common') {
            window.location.href = '/edashboard';
        }

        $('#email_label').html(localStorage.email);
        $('#perfil_email').val(localStorage.email);

        $.ajax({
            url: '/api/commonuser/email/' + localStorage.email + '.',
            contentType: 'application/json',
            type: 'GET',
            success: function(data) {

                if (data !== '') {
                    commonUser = data;
                    $('#perfil_nome').val(commonUser.name);
                }

            }
        });

    }

    $('#sair_btn').click(function() {
        localStorage.removeItem('email');
        localStorage.removeItem('role');
        window.location.href = '/';
    });

    $('#salvar_btn').click(function() {

        var nome = $('#perfil_nome').val();

        if (nome === '' || nome === null) {

            $('#alertMsg').html('Por favor, informe o seu nome.');
            $('#alertModal').modal();

        } else {

            commonUser.name = nome;

            if (commonUser.user === undefined) commonUser.user = {};
            commonUser.user.email = localStorage.email;

            $.ajax({
                url: '/api/commonuser',
                contentType: 'application/json',
                type: 'POST',
                data: JSON.stringify(commonUser),
                success: function(data) {

                    var msg = JSON.parse(data).msg;

                    if (msg !== 'success') {

                        $('#alertMsg').html(msg);
                        $('#alertModal').modal();

                    } else {

                        $('#successMsg').html('Alterações salvas com sucesso.');
                        $('#successModal').modal();

                    }

                }
            });

        }

    });

});
