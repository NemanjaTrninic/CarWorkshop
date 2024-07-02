const searchInput = document.getElementById('search')


if(searchParam == '' && searchInput != ''){
    searchInput.value = searchParam
}

if (searchParam != null && searchParam != ''){
    searchInput.value = searchParam
}

searchInput.addEventListener('keypress',(e)=>{
    if(e.key == 'Enter')
        doSearch()
})

searchInput.addEventListener('change',(e)=>{
    
        doSearch()
})

document.getElementById('search-btn')
    .addEventListener('click', () => {
       doSearch()
    })

function doSearch(){
    if(searchInput.value == '') return

    window.location.href = `./indeks.html?search=${search.value}`
}

function formatDate(iso) {
    if (iso == null) return 'N/A'
    return new Date(iso).toLocaleString('sr-RS')
}
