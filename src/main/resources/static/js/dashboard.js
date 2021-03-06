'use strict';

function enviarMensagemEmpresa(id) {
    localStorage.mensagemEmpresaId = id;
    window.location.href = '/mensagemempresa';
}

function enviarMensagemProduto(id) {
    localStorage.mensagemProdutoId = id;
    window.location.href = '/mensagemproduto';
}

$(document).ready(function() {

    var enterpriseUser = null;
    var numPerPageEmpresa = 10;
    var noOfPagesEmpresa;
    var currentPageEmpresa = 1;
    var paginationDataEmpresa;

    var product = null;
    var numPerPageProduto = 10;
    var noOfPagesProduto;
    var currentPageProduto = 1;
    var paginationDataProduto;


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

    }

    $('#sair_btn').click(function() {
        localStorage.removeItem('email');
        localStorage.removeItem('role');
        window.location.href = '/';
    });

    $('#buscar_btn').click(function() {

        var buscarPor = $('#buscar_por').val();
        var busca = $('#busca').val();
        var obj = null;

        if (buscarPor === 'Empresa') {

            obj = {
                tradeName: busca
            };

            $.ajax({
                url: '/api/enterpriseuser/search',
                contentType: 'application/json',
                type: 'POST',
                data: JSON.stringify(obj),
                success: function(data) {

                    enterpriseUser = data.reverse();
                    noOfPagesEmpresa = Math.ceil(enterpriseUser.length / numPerPageEmpresa);
                    paginationDataEmpresa = enterpriseUser.slice((currentPageEmpresa - 1) * numPerPageEmpresa, currentPageEmpresa * numPerPageEmpresa);

                    $('#current_page_empresa').html(currentPageEmpresa);
                    $('#no_of_pages_empresa').html(noOfPagesEmpresa);

                    $('#pages_empresa').append('<li><a id="previous_empresa">Previous</a></li>');

                    for (var i=1; i<=noOfPagesEmpresa; i++) {

                        var str = '<li><a class="set_page_empresa">' + i + '</a></li>';
                        $('#pages_empresa').append(str);

                    }

                    $('#pages_empresa').append('<li><a id="next_empresa">Next</a></li>');

                    $('#table_content_empresa').html('');

                    for (var i=0; i<paginationDataEmpresa.length; i++) {

                        var str = '<tr><td>' + paginationDataEmpresa[i].country + '</td>' +
                                    '<td>' + paginationDataEmpresa[i].tradeName + '</td>' +
                                    '<td><a href="#" class="enviar-bnt" onclick="enviarMensagemEmpresa(' + paginationDataEmpresa[i].id + ')">Enviar Mensagem</a></td></tr>';

                        $('#table_content_empresa').append(str);

                    }

                }
            });

            $('#busca_div').css("display", "none");
            $('#busca_empresa_div').css("display", "block");

        } else if (buscarPor === 'Produto') {

            obj = {
                name: busca
            };

            $.ajax({
                url: '/api/product/search',
                contentType: 'application/json',
                type: 'POST',
                data: JSON.stringify(obj),
                success: function(data) {

                    product = data.reverse();
                    noOfPagesProduto = Math.ceil(product.length / numPerPageProduto);
                    paginationDataProduto = product.slice((currentPageProduto - 1) * numPerPageProduto, currentPageProduto * numPerPageProduto);

                    $('#current_page_produto').html(currentPageProduto);
                    $('#no_of_pages_produto').html(noOfPagesProduto);

                    $('#pages_produto').append('<li><a id="previous_produto">Previous</a></li>');

                    for (var i=1; i<=noOfPagesProduto; i++) {

                        var str = '<li><a class="set_page_produto">' + i + '</a></li>';
                        $('#pages_produto').append(str);

                    }

                    $('#pages_produto').append('<li><a id="next_produto">Next</a></li>');

                    $('#table_content_produto').html('');

                    for (var i=0; i<paginationDataProduto.length; i++) {

                        var str = '<tr><td>' + paginationDataProduto[i].brand + '</td>' +
                                    '<td>' + paginationDataProduto[i].name + '</td>' +
                                    '<td>' + paginationDataProduto[i].enterpriseUser.tradeName + '</td>' +
                                    '<td><a href="#" class="enviar-bnt" onclick="enviarMensagemProduto(' + paginationDataProduto[i].id + ')">Enviar Mensagem</a></td></tr>';

                        $('#table_content_produto').append(str);

                    }

                }
            });

            $('#busca_div').css("display", "none");
            $('#busca_produto_div').css("display", "block");

        }

    });

    $("#limpar_busca_empresa_btn").click(function() {

        $('#buscar_por').val('Empresa');
        $('#busca').val('');

        $('#busca_empresa_div').css("display", "none");
        $('#busca_div').css("display", "block");

        enterpriseUser = null;
        numPerPageEmpresa = 10;
        noOfPagesEmpresa;
        currentPageEmpresa = 1;
        paginationDataEmpresa = [];

        $('#current_page_empresa').html("");
        $('#no_of_pages_empresa').html("");
        $('#pages_empresa').html("");
        $('#table_content_empresa').html("");

    });

    $("#limpar_busca_produto_btn").click(function() {

        $('#buscar_por').val('Empresa');
        $('#busca').val('');

        $('#busca_produto_div').css("display", "none");
        $('#busca_div').css("display", "block");

        product = null;
        numPerPageProduto = 10;
        noOfPagesProduto;
        currentPageProduto = 1;
        paginationDataProduto = [];

        $('#current_page_produto').html("");
        $('#no_of_pages_produto').html("");
        $('#pages_produto').html("");
        $('#table_content_produto').html("");

    });

    $('#pages_empresa').on('click', '#previous_empresa', function() {

        if (currentPageEmpresa > 1) {

            currentPageEmpresa--;
            paginationDataEmpresa = enterpriseUser.slice((currentPageEmpresa - 1) * numPerPageEmpresa, currentPageEmpresa * numPerPageEmpresa);

            $('#current_page_empresa').html(currentPageEmpresa);
            $('#table_content_empresa').html('');

            for (var i=0; i<paginationDataEmpresa.length; i++) {

                var str = '<tr><td>' + paginationDataEmpresa[i].country + '</td>' +
                            '<td>' + paginationDataEmpresa[i].tradeName + '</td>' +
                            '<td><a href="#" class="enviar-bnt" onclick="enviarMensagemEmpresa(' + paginationDataEmpresa[i].id + ')">Enviar Mensagem</a></td></tr>';

                $('#table_content_empresa').append(str);

            }

        }

    });

    $('#pages_empresa').on('click', '#next_empresa', function() {

        if (currentPageEmpresa < noOfPagesEmpresa) {

            currentPageEmpresa++;
            paginationDataEmpresa = enterpriseUser.slice((currentPageEmpresa - 1) * numPerPageEmpresa, currentPageEmpresa * numPerPageEmpresa);

            $('#current_page_empresa').html(currentPageEmpresa);
            $('#table_content_empresa').html('');

            for (var i=0; i<paginationDataEmpresa.length; i++) {

                var str = '<tr><td>' + paginationDataEmpresa[i].country + '</td>' +
                            '<td>' + paginationDataEmpresa[i].tradeName + '</td>' +
                            '<td><a href="#" class="enviar-bnt" onclick="enviarMensagemEmpresa(' + paginationDataEmpresa[i].id + ')">Enviar Mensagem</a></td></tr>';

                $('#table_content_empresa').append(str);

            }

        }

    });

    $('#pages_empresa').on('click', '.set_page_empresa', function() {

        currentPageEmpresa = parseInt($(this).html());
        paginationDataEmpresa = enterpriseUser.slice((currentPageEmpresa - 1) * numPerPageEmpresa, currentPageEmpresa * numPerPageEmpresa);

        $('#current_page_empresa').html(currentPageEmpresa);
        $('#table_content_empresa').html('');

        for (var i=0; i<paginationDataEmpresa.length; i++) {

            var str = '<tr><td>' + paginationDataEmpresa[i].country + '</td>' +
                        '<td>' + paginationDataEmpresa[i].tradeName + '</td>' +
                        '<td><a href="#" class="enviar-bnt" onclick="enviarMensagemEmpresa(' + paginationDataEmpresa[i].id + ')">Enviar Mensagem</a></td></tr>';

            $('#table_content_empresa').append(str);

        }

    });

    $('#pages_produto').on('click', '#previous_produto', function() {

        if (currentPageProduto > 1) {

            currentPageProduto--;
            paginationDataProduto = product.slice((currentPageProduto - 1) * numPerPageProduto, currentPageProduto * numPerPageProduto);

            $('#current_page_produto').html(currentPageProduto);
            $('#table_content_produto').html('');

            for (var i=0; i<paginationDataProduto.length; i++) {

                var str = '<tr><td>' + paginationDataProduto[i].brand + '</td>' +
                            '<td>' + paginationDataProduto[i].name + '</td>' +
                            '<td>' + paginationDataProduto[i].enterpriseUser.tradeName + '</td>' +
                            '<td><a href="#" class="enviar-bnt" onclick="enviarMensagemProduto(' + paginationDataProduto[i].id + ')">Enviar Mensagem</a></td></tr>';

                $('#table_content_produto').append(str);

            }

        }

    });

    $('#pages_produto').on('click', '#next_produto', function() {

        if (currentPageProduto < noOfPagesProduto) {

            currentPageProduto++;
            paginationDataProduto = product.slice((currentPageProduto - 1) * numPerPageProduto, currentPageProduto * numPerPageProduto);

            $('#current_page_produto').html(currentPageProduto);
            $('#table_content_produto').html('');

            for (var i=0; i<paginationDataProduto.length; i++) {

                var str = '<tr><td>' + paginationDataProduto[i].brand + '</td>' +
                            '<td>' + paginationDataProduto[i].name + '</td>' +
                            '<td>' + paginationDataProduto[i].enterpriseUser.tradeName + '</td>' +
                            '<td><a href="#" class="enviar-bnt" onclick="enviarMensagemProduto(' + paginationDataProduto[i].id + ')">Enviar Mensagem</a></td></tr>';

                $('#table_content_produto').append(str);

            }

        }

    });

    $('#pages_produto').on('click', '.set_page_produto', function() {

        currentPageProduto = parseInt($(this).html());
        paginationDataProduto = product.slice((currentPageProduto - 1) * numPerPageProduto, currentPageProduto * numPerPageProduto);

        $('#current_page_produto').html(currentPageProduto);
        $('#table_content_produto').html('');

        for (var i=0; i<paginationDataProduto.length; i++) {

            var str = '<tr><td>' + paginationDataProduto[i].brand + '</td>' +
                        '<td>' + paginationDataProduto[i].name + '</td>' +
                        '<td>' + paginationDataProduto[i].enterpriseUser.tradeName + '</td>' +
                        '<td><a href="#" class="enviar-bnt" onclick="enviarMensagemProduto(' + paginationDataProduto[i].id + ')">Enviar Mensagem</a></td></tr>';

            $('#table_content_produto').append(str);

        }

    });

});
