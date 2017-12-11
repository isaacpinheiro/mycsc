'use strict';

function visualizarMensagem(id) {
    alert(id);
    //localStorage.visualizarMensageId = id;
    //window.location.href = '/visualizarmensagem';
}

$(document).ready(function() {

    var message = null;
    var numPerPage = 10;
    var noOfPages;
    var currentPage = 1;
    var paginationData;

    if (localStorage.email === null || localStorage.email === undefined) {

        window.location.href = '/';

    } else {

        if (localStorage.role !== 'enterprise') {
            window.location.href = '/dashboard';
        }

        $('#email_label').html(localStorage.email);

        $.ajax({
            url: '/api/message/email/' + localStorage.email + '.',
            contentType: 'application/json',
            type: 'GET',
            success: function(data) {

                message = data.reverse();
                noOfPages = Math.ceil(message.length / numPerPage);
                paginationData = message.slice((currentPage - 1) * numPerPage, currentPage * numPerPage);

                $('#current_page').html(currentPage);
                $('#no_of_pages').html(noOfPages);

                $('#pages').append('<li><a id="previous">Previous</a></li>');

                for (var i=1; i<=noOfPages; i++) {

                    var str = '<li><a class="set_page">' + i + '</a></li>';
                    $('#pages').append(str);

                }

                $('#pages').append('<li><a id="next">Next</a></li>');

                $('#table_content').html('');

                for (var i=0; i<paginationData.length; i++) {

                    var str = '';

                    if (paginationData[i].anonymous) {
                        str += '<tr><td>Anônimo</td>' +
                                '<td>Anônimo</td>';
                    } else {
                        str += '<tr><td>' + paginationData[i].commonUser.name + '</td>' +
                                '<td>' + paginationData[i].commonUser.user.email + '</td>';
                    }

                    str += '<td>' + paginationData[i].messageType + '</td>';

                    if (paginationData[i].product === null) {
                        str += '<td></td>';
                    } else {
                        str += '<td>' + paginationData[i].product.name + '</td>'
                    }

                    str += '<td><a href="#" onclick="visualizarMensagem(' + paginationData[i].id + ')">Visualizar Mensagem</a></td></tr>';

                    $('#table_content').append(str);

                }

            }
        });

    }

    $('#sair_btn').click(function() {
        localStorage.removeItem('email');
        localStorage.removeItem('role');
        window.location.href = '/';
    });

    $('#pages').on('click', '#previous', function() {

        if (currentPage > 1) {

            currentPage--;
            paginationData = message.slice((currentPage - 1) * numPerPage, currentPage * numPerPage);

            $('#current_page').html(currentPage);
            $('#table_content').html('');

            for (var i=0; i<paginationData.length; i++) {

                var str = '';

                if (paginationData[i].anonymous) {
                    str += '<tr><td>Anônimo</td>' +
                            '<td>Anônimo</td>';
                } else {
                    str += '<tr><td>' + paginationData[i].commonUser.name + '</td>' +
                            '<td>' + paginationData[i].commonUser.user.email + '</td>';
                }

                str += '<td>' + paginationData[i].messageType + '</td>';

                if (paginationData[i].product === null) {
                    str += '<td></td>';
                } else {
                    str += '<td>' + paginationData[i].product.name + '</td>'
                }

                str += '<td><a href="#" onclick="visualizarMensagem(' + paginationData[i].id + ')">Visualizar Mensagem</a></td></tr>';

                $('#table_content').append(str);

            }

        }

    });

    $('#pages').on('click', '#next', function() {

        if (currentPage < noOfPages) {

            currentPage++;
            paginationData = message.slice((currentPage - 1) * numPerPage, currentPage * numPerPage);

            $('#current_page').html(currentPage);
            $('#table_content').html('');

            for (var i=0; i<paginationData.length; i++) {

                var str = '';

                if (paginationData[i].anonymous) {
                    str += '<tr><td>Anônimo</td>' +
                            '<td>Anônimo</td>';
                } else {
                    str += '<tr><td>' + paginationData[i].commonUser.name + '</td>' +
                            '<td>' + paginationData[i].commonUser.user.email + '</td>';
                }

                str += '<td>' + paginationData[i].messageType + '</td>';

                if (paginationData[i].product === null) {
                    str += '<td></td>';
                } else {
                    str += '<td>' + paginationData[i].product.name + '</td>'
                }

                str += '<td><a href="#" onclick="visualizarMensagem(' + paginationData[i].id + ')">Visualizar Mensagem</a></td></tr>';

                $('#table_content').append(str);

            }

        }

    });

    $('#pages').on('click', '.set_page', function() {

        currentPage = parseInt($(this).html());
        paginationData = message.slice((currentPage - 1) * numPerPage, currentPage * numPerPage);

        $('#current_page').html(currentPage);
        $('#table_content').html('');

        for (var i=0; i<paginationData.length; i++) {

            var str = '';

            if (paginationData[i].anonymous) {
                str += '<tr><td>Anônimo</td>' +
                        '<td>Anônimo</td>';
            } else {
                str += '<tr><td>' + paginationData[i].commonUser.name + '</td>' +
                        '<td>' + paginationData[i].commonUser.user.email + '</td>';
            }

            str += '<td>' + paginationData[i].messageType + '</td>';

            if (paginationData[i].product === null) {
                str += '<td></td>';
            } else {
                str += '<td>' + paginationData[i].product.name + '</td>'
            }

            str += '<td><a href="#" onclick="visualizarMensagem(' + paginationData[i].id + ')">Visualizar Mensagem</a></td></tr>';

            $('#table_content').append(str);

        }

    });

});
