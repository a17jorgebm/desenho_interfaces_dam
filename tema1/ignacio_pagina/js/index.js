document.addEventListener('DOMContentLoaded',(e)=>{
    let destacadas = document.getElementById('main-pajaritas-destacadas')
    let pajaritasDestacadas = document.querySelectorAll('#main-pajaritas-destacadas .destacada')


    destacadas.addEventListener('click', (e)=>{
        for (let p of pajaritasDestacadas) {
            if (p.contains(e.target)) {
                let id = p.dataset.id;
                window.location.href = 'pajarita.html?id=' + id;
                break; // Stop the loop once a match is found
            }
        }
    })



    // categorias
    let containerCategorias = document.getElementById('container-categorias')

    containerCategorias.addEventListener('click', (e) => {
        let clickeado = e.target.closest('.categoria')
        if (clickeado){
            window.location.href = "catalogo.html"
        }

    })
})