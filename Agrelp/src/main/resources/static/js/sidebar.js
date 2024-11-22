// Selecionando o botão e a sidebar
const toggleSidebar = document.getElementById('toggle-sidebar');
const sidebar = document.querySelector('.sidebar');

// Adicionando evento de clique
toggleSidebar.addEventListener('change', function () {
    if (this.checked) {
        sidebar.classList.add('open');
    } else {
        sidebar.classList.remove('open');
    }
});
