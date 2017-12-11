'use strict';

$(document).ready(function() {

    var message;

    if (localStorage.email === null || localStorage.email === undefined) {

        window.location.href = '/';

    } else {

        if (localStorage.role !== 'enterprise') {
            window.location.href = '/dashboard';
        }

        $('#email_label').html(localStorage.email);

        $.ajax({
            url: '/api/message/' + localStorage.visualizarMensageId,
            contentType: 'application/json',
            type: 'GET',
            success: function(data) {
                message = data;

                if (message.anonymous) {
                    $('#mensagem_de').val('Anônimo');
                    $('#mensagem_email').val('Anônimo');
                } else {
                    $('#mensagem_de').val(message.commonUser.name);
                    $('#mensagem_email').val(message.commonUser.user.email);
                }

                $('#mensagem_tipo').val(message.messageType);

                if (message.product === null) $('#mensagem_produto').val('');
                else $('#mensagem_produto').val(message.product.name);

                $('#mensagem_conteudo').val(message.content);

            }
        });

    }

    $('#sair_btn').click(function() {
        localStorage.removeItem('email');
        localStorage.removeItem('role');
        window.location.href = '/';
    });

    $('#voltar_btn').click(function() {
        localStorage.removeItem('visualizarMensageId');
        window.location.href = '/edashboard';
    });

});
