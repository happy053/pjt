function search() {
    const a = document.getElementById("searchCtnt").value;
    var select = document.getElementById("search");
    select = select.options[select.selectedIndex].value;
    alert(select);
}