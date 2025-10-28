document.addEventListener("DOMContentLoaded", () => {
  const forms = document.querySelectorAll("form"); // Seleciona todos os formulários na página

  forms.forEach((form) => {
    const messageDiv = document.getElementById("confirmationMessage");

    if (!messageDiv) {
      console.error("Div de mensagem de confirmação não encontrada!");
      return;
    }

    const successMessage = form.dataset.successMessage || "Adicionado com sucesso ao sistema!"; // Mensagem padrão
    const submitDelay = parseInt(form.dataset.submitDelay, 10) || 500; // Tempo padrão de atraso

    form.addEventListener("submit", (event) => {
      event.preventDefault(); // Impede o envio imediato do formulário

      // Exibe a mensagem de sucesso
      messageDiv.innerHTML = `
        <div class="success">
          <div class="success__icon">
            <i class="fa-solid fa-circle-check"></i>
          </div>
          <div class="success__title">${successMessage}</div>
        </div>
      `;
      messageDiv.classList.remove("d-none");

      // Envia o formulário após o atraso
      setTimeout(() => form.submit(), submitDelay);
    });
  });
});
