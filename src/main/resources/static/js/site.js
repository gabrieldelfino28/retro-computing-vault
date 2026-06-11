window.addEventListener('scroll', () => {

    const nav = document.querySelector('.museum-nav');

    if (window.scrollY > 50) {
        nav.classList.add('scrolled');
    } else {
        nav.classList.remove('scrolled');
    }

});

let currentSize = 100;

document
    .getElementById('font-plus')
    .addEventListener('click', () => {

        currentSize += 5;

        document.body.style.fontSize =
            currentSize + '%';
    });

document
    .getElementById('font-minus')
    .addEventListener('click', () => {

        currentSize -= 5;

        document.body.style.fontSize =
            currentSize + '%';
    });
