'use strict';

$(document).ready(function() {

    var commonUser;
    var product;

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
                } else {
                    commonUser = data;
                }

            }
        });

        $.ajax({
            url: '/api/product/' + localStorage.mensagemProdutoId,
            contentType: 'application/json',
            type: 'GET',
            success: function(data) {
                product = data;
                $('#mensagem_empresa').val(product.enterpriseUser.tradeName);
                $('#mensagem_produto').val(product.name);
            }
        });

    }

    $('#sair_btn').click(function() {
        localStorage.removeItem('email');
        localStorage.removeItem('role');
        window.location.href = '/';
    });

    $('#enviar_btn').click(function() {

        var tipoMensagem = $('#mensagem_tipo').val();
        var anonimo = $('#mensagem_anonimo').is(':checked');
        var conteudo = $('#mensagem_conteudo').val();

        if (conteudo === '' || conteudo === null) {

            $('#alertMsg').html('Por favor, informe o conteúdo da mensagem.');
            $('#alertModal').modal();

        } else {

            var message = {
                messageType: tipoMensagem,
                anonymous: anonimo,
                content: conteudo,
                commonUser: commonUser,
                enterpriseUser: product.enterpriseUser,
                product: product
            };

            $.ajax({
                url: '/api/message',
                contentType: 'application/json',
                type: 'POST',
                data: JSON.stringify(message),
                success: function(data) {

                    var msg = JSON.parse(data).msg;

                    if (msg !== 'success') {

                        $('#alertMsg').html(msg);
                        $('#alertModal').modal();

                    } else {

                        $('#successMsg').html('Mensagem enviada com sucesso com sucesso.');
                        $('#successModal').modal();

                    }

                }
            });

        }

    });

    $('#close_btn').click(function() {
        localStorage.removeItem('mensagemProdutoId');
        window.location.href = '/dashboard';
    });

});
