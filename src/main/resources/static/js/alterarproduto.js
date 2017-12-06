'use strict';

$(document).ready(function() {

    var product = null;

    if (localStorage.email === null || localStorage.email === undefined) {

        window.location.href = '/';

    } else {

        if (localStorage.role !== 'enterprise') {
            window.location.href = '/dashboard';
        }

        $('#email_label').html(localStorage.email);

        $.ajax({
            url: '/api/product/' + localStorage.alterarProdutoId,
            contentType: 'application/json',
            type: 'GET',
            success: function(data) {

                product = data;

                $('#produto_marca').val(product.brand);
                $('#produto_nome').val(product.name);
                $('#produto_descricao').val(product.description);

            }
        });

    }

    $('#sair_btn').click(function() {
        localStorage.removeItem('email');
        localStorage.removeItem('role');
        window.location.href = '/';
    });

    $('#salvar_btn').click(function() {

        var marca = $('#produto_marca').val();
        var nome = $('#produto_nome').val();
        var descricao = $('#produto_descricao').val();

        if (marca === '' || marca === null) {

            $('#alertMsg').html('Por favor, informe a marca do produto.');
            $('#alertModal').modal();

        } else if (nome === '' || nome === null) {

            $('#alertMsg').html('Por favor, informe o nome do produto.');
            $('#alertModal').modal();

        } else if (descricao === '' || descricao === null) {

            $('#alertMsg').html('Por favor, informe a descrição do produto.');
            $('#alertModal').modal();

        } else {

            product.brand = marca;
            product.name = nome;
            product.description = descricao;

            $.ajax({
                url: '/api/product',
                contentType: 'application/json',
                type: 'POST',
                data: JSON.stringify(product),
                success: function(data) {

                    var msg = JSON.parse(data).msg;

                    if (msg !== 'success') {

                        $('#alertMsg').html(msg);
                        $('#alertModal').modal();

                    } else {

                        localStorage.removeItem('alterarProdutoId');
                        window.location.href = '/eprodutos';

                    }

                }
            });

        }

    });

});
