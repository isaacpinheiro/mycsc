'use strict';

$(document).ready(function() {

    if (localStorage.email !== null && localStorage.email !== undefined) {

        if (localStorage.role === 'common') {
            window.location.href = '/dashboard';
        } else if (localStorage.role === 'enterprise') {
            window.location.href = '/edashboard';
        }

    }

    $('#sign_up_btn').click(function() {

        var filter = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;

        var email = $('#sign_up_email').val();
        var senha = $('#sign_up_senha').val();
        var confSenha = $('#sign_up_conf_senha').val();
        var nome = $('#sign_up_nome').val();
        var cnpj = $('#sign_up_cnpj').val();
        var pais = $('#sign_up_pais').val();

        if (email === '' || email === null) {

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

        } else if (nome === '' || nome === null) {

            $('#alertMsg').html('Por favor, informe o nome da sua empresa.');
            $('#alertModal').modal();

        } else if (cnpj === '' || cnpj === null) {

            $('#alertMsg').html('Por favor, informe o cnpj da sua empresa.');
            $('#alertModal').modal();

        } else if (pais === '' || pais === null) {

            $('#alertMsg').html('Por favor, informe o país de origem da sua empresa.');
            $('#alertModal').modal();

        } else {

            var obj = {
                tradeName: nome,
                regCode: cnpj,
                country: pais,
                user: {
                    email: email,
                    password: senha
                }
            };

            $.ajax({
                url: '/api/enterpriseuser',
                contentType: 'application/json',
                type: 'POST',
                data: JSON.stringify(obj),
                success: function(data) {

                    var msg = JSON.parse(data).msg;

                    if (msg !== 'success') {

                        $('#alertMsg').html(msg);
                        $('#alertModal').modal();

                    } else {

                        $('#sign_up_email').val('');
                        $('#sign_up_senha').val('');
                        $('#sign_up_conf_senha').val('');
                        $('#sign_up_nome').val('');
                        $('#sign_up_cnpj').val('');
                        $('#sign_up_pais').val('');

                        localStorage.email = obj.user.email;
                        localStorage.role = 'enterprise';

                        window.location.href = '/edashboard';

                    }

                }
            });

        }

    });

});
