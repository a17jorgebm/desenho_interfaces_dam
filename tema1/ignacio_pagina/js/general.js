{
    document.addEventListener('DOMContentLoaded', () => {
        let shopCart = document.getElementById('shop-cart')
        let closeShopCart = document.getElementById('close-shop-cart')
        let openShopCart = document.getElementById('open-shop-cart')

        closeShopCart.addEventListener('click', (e) => {
            shopCart.classList.remove('show')
        })

        openShopCart.addEventListener('click', (e) => {
            shopCart.classList.add('show')
        })

        /* adding product to cart */
        let addProductButtons = document.querySelectorAll('.add-cart')
        console.log(addProductButtons)
        let messageProductAdded = document.getElementById('message-product-added-to-cart')
        addProductButtons.forEach(button => {
            button.addEventListener('click', ()=>{
                let isShowing = messageProductAdded.classList.contains('show-message')
                if (!isShowing){
                    messageProductAdded.classList.add('show-message')
                    setTimeout(()=>{
                        messageProductAdded.classList.remove('show-message')
                    },2000)
                }
            })
        })
    })
}