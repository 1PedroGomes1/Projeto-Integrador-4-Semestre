document.addEventListener("DOMContentLoaded", () => {
  const confirmationMessageDiv = document.getElementById("confirmationMessage");

  if (!confirmationMessageDiv) {
    console.error("Elemento para mensagens de confirmação não encontrado!");
    return;
  }

  // Função para exibir a mensagem de confirmação
  const showConfirmationMessage = (message) => {
    confirmationMessageDiv.innerHTML = `
      <div class="success">
        <div class="success__icon">
          <i class="fa-solid fa-circle-check"></i>
        </div>
        <div class="success__title">${message}</div>
      </div>
    `;
    confirmationMessageDiv.classList.remove("d-none");

    // Ocultar a mensagem após 3 segundos
    setTimeout(() => {
      confirmationMessageDiv.classList.add("d-none");
    }, 3000);
  };

  // Adicionar evento de clique nos links de exclusão
  const deleteLinks = document.querySelectorAll("a.btn-outline-danger");
  deleteLinks.forEach((link) => {
    link.addEventListener("click", (event) => {
      event.preventDefault();

      if (confirm("Tem certeza que deseja deletar este item?")) {
        showConfirmationMessage("Item deletado com sucesso!");
        // Simula o redirecionamento ou recarrega a página após a exclusão
        setTimeout(() => {
          window.location.href = link.getAttribute("href");
        }, 500); // Ajuste o tempo conforme necessário
      }
    });
  });
});
