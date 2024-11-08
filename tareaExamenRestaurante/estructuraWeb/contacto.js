{
    
const formulario=document.getElementById('elemento-form-contacto')
const botonEnvio=formulario.querySelector('#btn-enviar-datos-contacto')
const camposRequeridos=document.querySelectorAll('#elemento-form-contacto .required')

botonEnvio.addEventListener('click' ,(ev) => {
    let camposCorrectos=true
    for(let campo of camposRequeridos){
        if(campo.value.trim() ===''){
            campo.classList.add('campo-incompleto')
            camposCorrectos=false
        }
    }
    if(camposCorrectos){
        console.log('guay')
    }else{
        mostrarErrorCamposIncompletos()
    }
})

function mostrarErrorCamposIncompletos(){
    formulario.addEventListener('input', (ev) => {
        let target=ev.target
        if(target.classList.contains('txt-form')){
            if(target.value.trim()!==''){
                target.classList.remove('campo-incompleto')
            }else{
                target.classList.add('campo-incompleto')
            }
        }
    })
}

function camposRequeridosCompletados(){
    let camposCorrectos=true
    for(let campo of camposRequeridos){
        if(campo.value.trim() ===''){
            campo.classList.add('campo-incompleto')
            camposCorrectos=false
        }
    }
    return camposCorrectos
}

}