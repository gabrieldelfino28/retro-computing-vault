// Navbar dinâmica
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