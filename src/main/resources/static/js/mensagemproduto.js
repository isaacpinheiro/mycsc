'use strict';

$(document).ready(function() {

    var commonUser = null;

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

    }

});
