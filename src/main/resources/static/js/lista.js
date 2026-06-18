let deleteUrl = '';

document.addEventListener('DOMContentLoaded', () => {
    const modal = document.getElementById('delete-modal');
    if (modal) document.body.appendChild(modal);
});

function abrirModalExclusao(btn) {
    const id = btn.getAttribute('data-id');
    const modelo = btn.getAttribute('data-modelo');

    document.getElementById('delete-modal-message').innerHTML =
        `Tem certeza que deseja excluir <span style="color: var(--fatec-red); font-weight: 700;">"${modelo}"</span>? Esta ação não pode ser desfeita.`;

    // Extrai o prefixo do path (/computador, /console, /dispositivo-movel)
    const basePath = '/' + window.location.pathname.split('/')[1];
    deleteUrl = `${basePath}/excluir/${id}`;

    document.getElementById('delete-modal').classList.add('active');
    document.documentElement.classList.add('global-blur-active');
}

function confirmarExclusao() {
    const form = document.getElementById('delete-form');
    form.action = deleteUrl;
    form.submit();
}

function fecharModalExclusao() {
    document.getElementById('delete-modal').classList.remove('active');
    document.documentElement.classList.remove('global-blur-active');
}