const sendMessage = () => {
    const supplier = getSelectedValue(document.getElementById('supplier-drop-down'))
    const category = getSelectedValue(document.getElementById('category-drop-down'))

    window.location.href = `http://localhost:8888/?category=${category}&supplier=${supplier}`
}
const getSelectedValue = selector => selector.options[selector.selectedIndex].text