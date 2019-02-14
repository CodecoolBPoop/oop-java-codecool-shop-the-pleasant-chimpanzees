function handleAddProduct(e) {

    const productId = e.target.value
    const isUserLoggedIn = () => !!document.getElementById('user_name')

    if(!isUserLoggedIn()){
        const STORAGE_KEY = 'cart'
        const isCartInStorage = () => localStorage.getItem('cart') != null

        if(isCartInStorage()){
            const storage = JSON.parse(localStorage.getItem('cart'))
            const product = storage.cart.find(x => x.id === parseInt(productId))

            if(product){
                product.count += 1
            }else{
                storage.cart.push({id: parseInt(productId), count: 1})
            }

            saveToLocalStorage(storage)

        }else{
            saveToLocalStorage({[STORAGE_KEY]: [{id: parseInt(productId), count: 1}]})
        }
    }
}

function saveToLocalStorage(obj) {
    localStorage.setItem('cart', JSON.stringify(obj))
}

function setAddProductEventHandler(){
    const btns = document.getElementsByClassName('add_to_checkout_btn')

    for(let btn of btns){
        btn.addEventListener('click', handleAddProduct)
    }

}


function filterSetup(){
    const sendMessage = () => {
        const supplier = getSelectedValue(document.getElementById('supplier-drop-down'))
        const category = getSelectedValue(document.getElementById('category-drop-down'))

        window.location.href = `http://localhost:8888/?category=${category}&supplier=${supplier}`
    }
    const getSelectedValue = selector => selector.options[selector.selectedIndex].text
}

function main(){
    //$(() => $('[data-toggle="popover"]').popover())
    filterSetup();
    setAddProductEventHandler();
}

main();