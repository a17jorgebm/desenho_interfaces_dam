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
})