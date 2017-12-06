'use strict';

$(document).ready(function() {

    var product = null;
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
            url: '/api/product/email/' + localStorage.email + '.',
            contentType: 'application/json',
            type: 'GET',
            success: function(data) {

                product = data.reverse();
                noOfPages = Math.ceil(product.length / numPerPage);
                paginationData = product.slice((currentPage - 1) * numPerPage, currentPage * numPerPage);

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

                    var str = '<tr><td>' + paginationData[i].brand + '</td>' +
                                '<td>' + paginationData[i].name + '</td>' +
                                '<td>' + paginationData[i].description + '</td></tr>';

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
            paginationData = product.slice((currentPage - 1) * numPerPage, currentPage * numPerPage);

            $('#current_page').html(currentPage);
            $('#table_content').html('');

            for (var i=0; i<paginationData.length; i++) {

                var str = '<tr><td>' + paginationData[i].brand + '</td>' +
                            '<td>' + paginationData[i].name + '</td>' +
                            '<td>' + paginationData[i].description + '</td></tr>';

                $('#table_content').append(str);

            }

        }

    });

    $('#pages').on('click', '#next', function() {

        if (currentPage < noOfPages) {

            currentPage++;
            paginationData = product.slice((currentPage - 1) * numPerPage, currentPage * numPerPage);

            $('#current_page').html(currentPage);
            $('#table_content').html('');

            for (var i=0; i<paginationData.length; i++) {

                var str = '<tr><td>' + paginationData[i].brand + '</td>' +
                            '<td>' + paginationData[i].name + '</td>' +
                            '<td>' + paginationData[i].description + '</td></tr>';

                $('#table_content').append(str);

            }

        }

    });

    $('#pages').on('click', '.set_page', function() {

        currentPage = parseInt($(this).html());
        paginationData = product.slice((currentPage - 1) * numPerPage, currentPage * numPerPage);

        $('#current_page').html(currentPage);
        $('#table_content').html('');

        for (var i=0; i<paginationData.length; i++) {

            var str = '<tr><td>' + paginationData[i].brand + '</td>' +
                        '<td>' + paginationData[i].name + '</td>' +
                        '<td>' + paginationData[i].description + '</td></tr>';

            $('#table_content').append(str);

        }

    });

});
