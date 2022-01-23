function search() {
    const a = document.getElementById("searchCtnt").value;
    var select = document.getElementById("searchVal");
    select = select.options[select.selectedIndex].value;
    $.ajax({
        type: 'GET',
        url: '/feed/search',
        data: {'ctnt': a, 'value': select},
        dataType: 'JSON',
        contentType: "application/json; charset=UTF-8",
        success: function (data) {

        },
        error: function () {
            alert("실패");
        }
    });
}

