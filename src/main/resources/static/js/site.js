// Navbar com sombra dinâmica
window.addEventListener('scroll', () => {

    const nav = document.querySelector('.museum-nav');

    if (window.scrollY > 50) {
        nav.classList.add('scrolled');
    } else {
        nav.classList.remove('scrolled');
    }

});


// Acessibilidade - tamanho da fonte
let currentSize = 100;

const MIN_SIZE = 80;
const MAX_SIZE = 140;

const btnPlus = document.getElementById('font-plus');
const btnMinus = document.getElementById('font-minus');

if (btnPlus && btnMinus) {

    btnPlus.addEventListener('click', () => {

        if (currentSize < MAX_SIZE) {
            currentSize += 5;
            document.body.style.fontSize = currentSize + '%';
        }
    });

    btnMinus.addEventListener('click', () => {

        if (currentSize > MIN_SIZE) {
            currentSize -= 5;
            document.body.style.fontSize = currentSize + '%';
        }
    });
}

const hero = document.querySelector('.hero');

window.addEventListener('scroll', () => {

    const blur = Math.min(window.scrollY / 150, 5);

    hero.style.setProperty(
        '--hero-blur',
        `${blur}px`
    );

});

const heroBg = document.querySelector('.hero-bg');

window.addEventListener('scroll', () => {

    const scroll = window.scrollY;

    const parallax = scroll * 0.35;

    const blur =
        Math.min(scroll / 150, 5);

    heroBg.style.transform =
        `translateY(${parallax}px) scale(1)`;

    heroBg.style.filter =
        `blur(${blur}px)`;
});

document.addEventListener("DOMContentLoaded", () => {
    // IMPORTANTE: Altere o seletor abaixo (.navbar) para a classe real da sua barra vermelha do topo
    const navbarVermelha = document.querySelector('.navbar') || document.querySelector('header');
    const breadcrumb = document.querySelector('.breadcrumb-bar');

    if (breadcrumb && navbarVermelha) {
        window.addEventListener('scroll', () => {
            // Pega as posições físicas dos dois elementos na tela em tempo real
            const rectNavbar = navbarVermelha.getBoundingClientRect();
            const rectBreadcrumb = breadcrumb.getBoundingClientRect();

            // O ponto limite é onde a base da navbar vermelha termina na tela
            const limiteNavbar = rectNavbar.bottom;

            // Verifica se o breadcrumb começou a passar por baixo da barra do topo
            if (rectBreadcrumb.top <= limiteNavbar) {
                // Descobre quantos pixels do breadcrumb já entraram debaixo da navbar
                const pixelsEscondidos = limiteNavbar - rectBreadcrumb.top;
                const alturaBreadcrumb = rectBreadcrumb.height;

                // Transforma isso em uma porcentagem de 0 a 1 para suavizar os filtros
                const progressoSumisso = Math.min(Math.max(pixelsEscondidos / alturaBreadcrumb, 0), 1);

                // Aplica gradualmente o blur e a opacidade proporcionalmente ao movimento
                const blurValue = (progressoSumisso * 10).toFixed(1); // Desfoca até 10px progressivamente
                const opacityValue = (1 - progressoSumisso).toFixed(2); // Vai sumindo aos poucos

                breadcrumb.style.filter = `blur(${blurValue}px)`;
                breadcrumb.style.opacity = opacityValue;
            } else {
                // Se estiver totalmente visível abaixo da navbar, limpa os efeitos
                breadcrumb.style.filter = "blur(0px)";
                breadcrumb.style.opacity = "1";
            }
        });
    }
});