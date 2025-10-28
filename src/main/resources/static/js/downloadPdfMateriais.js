document.addEventListener("DOMContentLoaded", function() {
	const downloadButton = document.getElementById('download-pdf-3');

	if (downloadButton) {
		downloadButton.addEventListener('click', function() {
			const element = document.querySelector('.table'); // Seleciona a tabela para gerar o PDF

			const { jsPDF } = window.jspdf;
			const doc = new jsPDF();

			// Contagem de ferramentas
			const tableRows = element.querySelectorAll('tbody tr');
			const toolCount = tableRows.length; // Conta as linhas da tabela (ferramentas)

			// Definir título, subtítulo e outras informações
			const title = "Relatório de Materiais Cadastrados";
			const subtitle = "Informações detalhadas sobre os materiais do sistema.";
			const date = new Date().toLocaleDateString(); // Data atual
			const author = "Sistema de Gestão Agrícola"; // Autor ou nome do sistema

			// Adiciona título e subtítulo
			doc.setFontSize(18);
			doc.text(title, 14, 20); // Título na posição (14, 20)


			doc.setFontSize(12);
			doc.text(subtitle, 14, 30); // Subtítulo logo abaixo do título

			// Adiciona as informações adicionais (data e autor)
			doc.setFontSize(10);
			doc.text(`Data de Geração: ${date}`, 14, 40); // Data do relatório
			doc.text(`Autor: ${author}`, 14, 45); // Autor ou nome do sistema

			// Adiciona a contagem de ferramentas no PDF
			doc.text(`Total de Materiais: ${toolCount}`, 14, 50); // Contagem

			// Extraindo os dados da tabela
			const rows = [];
			const header = ['Nome', 'Categoria', 'Descrição', 'Quantidade']; // Cabeçalho da tabela
			rows.push(header);

			// Preenchendo as linhas da tabela
			tableRows.forEach(row => {
				const cells = row.querySelectorAll('td');
				const rowData = Array.from(cells).map(cell => cell.innerText);
				rows.push(rowData);
			});

			// Gerando a tabela no PDF
			doc.autoTable({
				head: [header], // Cabeçalho da tabela
				body: rows.slice(1), // Dados da tabela
				startY: 60, // Posição inicial no eixo Y (depois das informações)
				headStyles: {
					fillColor: [100, 100, 100], // Cor de fundo do cabeçalho
					textColor: 255, // Cor do texto
					fontSize: 12, // Tamanho da fonte do cabeçalho
					halign: 'center', // Alinhamento do texto
				},
				bodyStyles: {
					fontSize: 10, // Tamanho da fonte das células
					cellPadding: 5, // Espaçamento das células
					halign: 'center', // Alinhamento do texto
				},
				alternateRowStyles: {
					fillColor: [245, 245, 245], // Cor de fundo para linhas alternadas
				},
			});

			// Baixar o PDF gerado
			doc.save('relatorio-materiais.pdf');
		});
	}
});
