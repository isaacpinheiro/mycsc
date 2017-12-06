'use strict';

$(document).ready(function() {

    if (localStorage.email === null || localStorage.email === undefined) {

        window.location.href = '/';

    } else {

        if (localStorage.role !== 'common') {
            window.location.href = '/edashboard';
        }

    }

    $('#sair_btn').click(function() {
        localStorage.removeItem('email');
        localStorage.removeItem('role');
        window.location.href = '/';
    });

});
