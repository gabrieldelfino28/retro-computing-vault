document.addEventListener("DOMContentLoaded", () => {
    carregarEquipe();
});

async function carregarEquipe() {
    const orientadoresContainer = document.getElementById('orientadores-container');
    const desenvolvedoresContainer = document.getElementById('desenvolvedores-container');

    try {
        const response = await fetch('/json/team.json');
        if (!response.ok) throw new Error('Erro ao carregar o arquivo JSON');

        const teamEnum = await response.json();

        const tituloOrientadores = `
            <div class="col-12 mb-3 animated-card" style="animation-delay: 0.1s;">
                <h3>Orientadores</h3>
                <hr class="fatec-hr small">
            </div>
        `;
        const tituloDesenvolvedores = `
            <div class="col-12 mb-3 animated-card" style="animation-delay: 0.1s;">
                <h3>Desenvolvedores</h3>
                <hr class="fatec-hr small">
            </div>
        `;

        orientadoresContainer.innerHTML = tituloOrientadores;
        desenvolvedoresContainer.innerHTML = tituloDesenvolvedores;

        // Contadores para criar o efeito cascata nas animações
        let orientadorCount = 1;
        let devCount = 1;

        Object.values(teamEnum).forEach(member => {
            const linkedinColor = "#0077b5";
            const githubColor = "#2b3137";

            // Botão condicional do GitHub
            const githubButton = (member.github !== '#')
                ? `<a href="${member.github}" 
                      class="social-btn btn-github" 
                      target="_blank" 
                      style="--hover-color: ${githubColor}">
                      <i class="bi bi-github"></i>
                   </a>`
                : '';

            // Calcula o atraso baseado em qual seção o card vai entrar
            const currentDelay = member.isOrientador ? (orientadorCount * 0.2) : (devCount * 0.2);
            const cardHtml = `
                <div class="col-md-4 animated-card" style="animation-delay: ${currentDelay}s;">
                    <div class="team-card text-center p-3 border rounded shadow-sm h-100">
                        
                        <!-- Container da Foto com o Contorno SVG Animado -->
                        <div class="avatar-container mb-3">
                            <svg class="avatar-border" viewBox="0 0 160 160">
                                <circle cx="80" cy="80" r="78" />
                            </svg>
                            <img src="${member.foto}" 
                                 alt="Foto de ${member.nome}" 
                                 class="team-photo img-fluid">
                        </div>
                        
                        <h4>${member.nome}</h4>
                        <p class="text-muted">${member.cargo}</p>
                        
                        <div class="social-container mt-3">
                            <a href="${member.linkedin}" 
                               class="social-btn btn-linkedin" 
                               target="_blank" 
                               style="--hover-color: ${linkedinColor}">
                               <i class="bi bi-linkedin"></i>
                            </a>
                            ${githubButton}
                        </div>
                    </div>
                </div>
            `;

            if (member.isOrientador) {
                orientadoresContainer.innerHTML += cardHtml;
                orientadorCount++;
            } else {
                desenvolvedoresContainer.innerHTML += cardHtml;
                devCount++;
            }
        });

    } catch (error) {
        console.error("Erro na renderização da equipe:", error);
        const errorMsg = `<p class="text-danger text-center">Não foi possível carregar as informações da equipe.</p>`;
        orientadoresContainer.innerHTML = errorMsg;
        desenvolvedoresContainer.innerHTML = errorMsg;
    }
}
