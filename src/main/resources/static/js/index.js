'use strict';

$(document).ready(function() {

    $('#log_in_btn').click(function() {

        var filter = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;

        var email = $('#log_in_email').val();
        var senha = $('#log_in_senha').val();

        $('#alertMsg').html();
        $('#alertModal').modal();

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

            $('#alertMsg').html('Sucesso');
            $('#alertModal').modal();

        }

    });

    $('#sign_up_btn').click(function() {

        var filter = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;

        var nome = $('#sign_up_nome').val();
        var email = $('#sign_up_email').val();
        var senha = $('#sign_up_senha').val();
        var confSenha = $('#sign_up_conf_senha').val();

        if (nome === '' || nome === null) {

            $('#alertMsg').html('Por favor, informe o seu Nome Completo.');
            $('#alertModal').modal();

        } else if (email === '' || email === null) {

            $('#alertMsg').html('Por favor, informe o seu E-Mail.');
            $('#alertModal').modal();

        } else if (!filter.test(email)) {

            $('#alertMsg').html('Por favor, informe um E-Mail válido.');
            $('#alertModal').modal();

        } else if (senha === '' || senha === null) {

            $('#alertMsg').html('Por favor, informe a sua senha.');
            $('#alertModal').modal();

        } else if (confSenha === '' || confSenha === null) {

            $('#alertMsg').html('Por favor, confirme a sua senha.');
            $('#alertModal').modal();

        } else if (confSenha !== senha) {

            $('#alertMsg').html('A confirmação está diferente da senha.');
            $('#alertModal').modal();

        } else {

            $('#alertMsg').html('Sucesso');
            $('#alertModal').modal();

        }

    });

});
