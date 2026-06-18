document.addEventListener("DOMContentLoaded", () => {
    const cardIlustracao = document.querySelector('.fatec-card');
    const modal = document.getElementById('redirect-modal');
    let jaClicou = false;

    if (cardIlustracao && modal) {
        cardIlustracao.addEventListener('click', () => {
            if (jaClicou) return;
            jaClicou = true;

            cardIlustracao.classList.add('animating-border');

            // Aguarda os 600ms da animação da borda terminar
            setTimeout(() => {
                modal.classList.add('active');
                // Aplica o blur na raiz do site para pegar Navbars, Headers e Backgrounds
                document.documentElement.classList.add('global-blur-active');

                jaClicou = false;
                cardIlustracao.classList.remove('animating-border');
            }, 50);
        });
    }
});

function fecharModal() {
    document.getElementById('redirect-modal').classList.remove('active');
    document.documentElement.classList.remove('global-blur-active');
}
