document.addEventListener('DOMContentLoaded', e=>{
    let contenedorFiltros = document.querySelector('.contenedor-filtros')
    let tiposFiltro = document.querySelectorAll('.wrapper-tipo-filtro')
    contenedorFiltros.addEventListener('click', e => {
        let elemento = e.target
        console.log(elemento)
        if (elemento.classList.contains('opciones-tipo-filtro')){
            console.log(elemento)
        }
    })
})