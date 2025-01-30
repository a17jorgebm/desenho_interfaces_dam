{
document.addEventListener('DOMContentLoaded', e=>{
    const formulario=document.getElementById('elemento-form-contacto')
    const botonEnvio=formulario.querySelector('#btn-enviar-datos-contacto')
    const camposRequeridos=document.querySelectorAll('#elemento-form-contacto .required')
    const mensajeError= formulario.querySelector('#error-message')
    const inputEmail=formulario.querySelector('#contacto-email')
    const mensajeConfirmacion=document.getElementById('mensaje-form-enviado')

    botonEnvio.addEventListener('click' ,(ev) => {
        let camposCorrectos=true
        for(let campo of camposRequeridos){
            if(campo.value.trim() ===''){
                campo.classList.add('campo-incompleto')
                camposCorrectos=false
            }
        }
        if(camposCorrectos){
            if (isValidEmail(inputEmail.value)){
                formulario.submit()
            }else {
                verificarEmailElemento(inputEmail)
                addEventoFormActualizarErrores()
            }
        }else{
            cambiarMensajeError("Por favor, completa todos los campos*")
            mostrarMensajeError(true)
            addEventoFormActualizarErrores()
        }
    })

    function addEventoFormActualizarErrores(){
        formulario.addEventListener('input', (ev) => {
            let target=ev.target
            if(target.classList.contains('txt-form')){
                if(target.value.trim()!==''){
                    target.classList.remove('campo-incompleto')
                }else{
                    target.classList.add('campo-incompleto')
                }
            }

            let camposCompletados=camposRequeridosCompletados()

            if (!camposCompletados) cambiarMensajeError("Por favor, completa todos los campos*")

            camposCompletados
                ? mostrarMensajeError(false)
                : mostrarMensajeError(true)

            if (camposCompletados){
                verificarEmailElemento(inputEmail)
            }
        })
    }

    function verificarEmailElemento(inputTarget){
        if (!isValidEmail(inputTarget.value)){
            cambiarMensajeError("Inserta un email válido*")
            inputTarget.classList.add('campo-incompleto')
            mostrarMensajeError(true)
        }else {
            inputTarget.classList.remove('campo-incompleto')
            mostrarMensajeError(false)
        }
    }

//muestra o no el mensaje de error
    function mostrarMensajeError(mostrar){
        mostrar
            ? mensajeError.classList.add('opacidad-1')
            : mensajeError.classList.remove('opacidad-1')
    }

    function cambiarMensajeError(mensaje){
        mensajeError.innerText=mensaje
    }

    function isValidEmail(email) {
        const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        return emailPattern.test(email);
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
})
}