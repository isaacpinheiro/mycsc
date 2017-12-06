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
            url: '/api/product/email/' + localStorage.email + '.',
            contentType: 'application/json',
            type: 'GET',
            success: function(data) {

                product = data;

                for (var i=0; i<product.length; i++) {

                    var str = '<tr><td>' + product[i].brand + '</td>' +
                                '<td>' + product[i].name + '</td>' +
                                '<td>' + product[i].description + '</td></tr>';

                    $('#table_content').html($('#table_content').html() + str);

                }

            }
        });

    }

    $('#sair_btn').click(function() {
        localStorage.removeItem('email');
        localStorage.removeItem('role');
        window.location.href = '/';
    });

});
