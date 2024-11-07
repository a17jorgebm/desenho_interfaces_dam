// Selecciona el elemento footer
const footer = document.querySelector('footer');
const smallHeader= document.querySelector('.header-pantalla-small')
const bigHeader = document.querySelector('.logo-emerald-and-datos-contacto')

// Define el callback para el Intersection Observer
const observerCallback = (entries) => {
    //si el menu grande esta oculto, se comp
    const displayValueBigHeader=window.getComputedStyle(bigHeader, null).display

    if (displayValueBigHeader==='none'){
        entries.forEach(entry => {
            if (entry.isIntersecting) {
                smallHeader.classList.add('oculto')
            } else {
                smallHeader.classList.remove('oculto')
            }
        });
    }
};

// Configura el Intersection Observer
const observerOptions = {
    root: null,  // Usa el viewport como área de referencia
    threshold: 0.2 // cando o 20% do footer se esta vendo
};

const observer = new IntersectionObserver(observerCallback, observerOptions);

// Inicia el observador sobre el footer
observer.observe(footer);

//corrige bug por si el menu se oculta
window.addEventListener('resize', () => {
    observer.disconnect()
    observer.observe(footer)
})

//facer que o logo pequeno apareza
const miniLogoMenu=document.querySelector('#mini-logo-emerald-menu img')
const observerLogoCallBack = (entries) => {
    entries.forEach(entry => {
        if (entry.isIntersecting){
            miniLogoMenu.classList.remove('logo-visible')
        }else {
            miniLogoMenu.classList.add('logo-visible')
        }
        console.log(miniLogoMenu)
    })
}

const observerLogoOptions={
    root: null,
    threshold: 0
}

const observerLogo= new IntersectionObserver(observerLogoCallBack,observerLogoOptions)
observerLogo.observe(bigHeader)