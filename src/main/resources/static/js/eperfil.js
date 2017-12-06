'use strict';

$(document).ready(function() {

    var enterpriseUser = null;

    if (localStorage.email === null || localStorage.email === undefined) {

        window.location.href = '/';

    } else {

        if (localStorage.role !== 'enterprise') {
            window.location.href = '/dashboard';
        }

        $('#email_label').html(localStorage.email);

        $.ajax({
            url: '/api/enterpriseuser/email/' + localStorage.email + '.',
            contentType: 'application/json',
            type: 'GET',
            success: function(data) {

                enterpriseUser = data;

                $('#perfil_email').val(enterpriseUser.user.email);
                $('#perfil_nome').val(enterpriseUser.tradeName);
                $('#perfil_cnpj').val(enterpriseUser.regCode);
                $('#perfil_pais').val(enterpriseUser.country);

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
        var cnpj = $('#perfil_cnpj').val();
        var pais = $('#perfil_pais').val();

        if (nome === '' || nome === null) {

            $('#alertMsg').html('Por favor, informe o nome da sua empresa.');
            $('#alertModal').modal();

        } else if (cnpj === '' || cnpj === null) {

            $('#alertMsg').html('Por favor, informe o cnpj da sua empresa.');
            $('#alertModal').modal();

        } else if (pais === '' || pais === null) {

            $('#alertMsg').html('Por favor, informe o país de origem da sua empresa.');
            $('#alertModal').modal();

        } else {

            enterpriseUser.tradeName = nome;
            enterpriseUser.regCode = cnpj;
            enterpriseUser.country = pais;

            $.ajax({
                url: '/api/enterpriseuser',
                contentType: 'application/json',
                type: 'POST',
                data: JSON.stringify(enterpriseUser),
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
