const table = document.getElementById('table')
const template = document.getElementById('klijent')
const params = new URLSearchParams(window.location.search)
const searchParam = params.get('search')
const searchTitle = document.getElementById('search-title')

if (searchParam != null && searchParam != '') {
    searchTitle.innerText = 'Pretraga Klijenata'
    //fetchKlijenti('/mobilni/' + searchParam)
    fetchKlijentiBySearchByAllColumns(searchParam);
} else {
    searchTitle.innerText = 'Lista Klijenata'
    fetchKlijenti()
}

function fetchKlijenti(url = '') {
    fetch(`http://localhost:8000/api/klijent${url}`)
        .then(rsp => rsp.json())
        .then(data => {
            if (data.lenght == 0) {
                alert('Klijent nije Pronadjen!')
                fetchKlijenti()
                return
            }
            data.forEach(klijent => {
                // tr.innerText = `${klijent.id} ${klijent.ime} ${klijent.prezime} ${klijent.mobilni} ${new Date(klijent.datum_preuzimanja).toLocaleString('sr-RS')}`
                const copy = template.content.cloneNode(true) //kopija sadrzaja
                copy.querySelector('.id').innerText = klijent.id //klasa id
                copy.querySelector('.ime').innerText = klijent.ime
                copy.querySelector('.prezime').innerText = klijent.prezime
                copy.querySelector('.mobilni').innerText = klijent.mobilni
                copy.querySelector('.usluga').innerText = klijent.usluga.vrsta_usluge
                copy.querySelector('.cena').innerText = klijent.usluga.cena +'$' // dodata cena
                copy.querySelector('.datum').innerText = formatDate(klijent.datum_preuzimanja)
                copy.querySelector('.azurirano').innerText = formatDate(klijent.azurirano)
                copy.querySelector('.edit').href = `./edit.html?id=${klijent.id}`
                copy.querySelector('.remove').addEventListener('click',()=>{
                    if(confirm(`Obrisi Klijenta? ${klijent.ime} ${klijent.prezime} ${klijent.mobilni}`)){
                        fetch(`http://localhost:8000/api/klijent/${klijent.id}`,{
                            method: 'DELETE',
                            
                        })
                    .then(rsp=>{
                            if(rsp.status==204){
                                window.location.href = './indeks.html'
                                return
                            }
                            alert(`Brisanje Klijenta nije uspela(HTTP ${rsp.status})`)
                          })
                    }
                })
                table.appendChild(copy)

            })
        })

}

function fetchKlijentiBySearchByAllColumns(searchText) {
    fetch(`http://localhost:8000/api/klijent/search/${searchText}`)
        .then(rsp => rsp.json())
        .then(data => {
            if (data.lenght == 0) {
                alert('Student nije Pronadjen!')
                fetchKlijenti()
                return
            }
            data.forEach(klijent => {
                // tr.innerText = `${klijent.id} ${klijent.ime} ${klijent.prezime} ${klijent.mobilni} ${new Date(klijent.datum_preuzimanja).toLocaleString('sr-RS')}`
                const copy = template.content.cloneNode(true) //kopija sadrzaja
                copy.querySelector('.id').innerText = klijent.id //klasa id
                copy.querySelector('.ime').innerText = klijent.ime
                copy.querySelector('.prezime').innerText = klijent.prezime
                copy.querySelector('.mobilni').innerText = klijent.mobilni
                copy.querySelector('.datum').innerText = formatDate(klijent.datum_preuzimanja)
                copy.querySelector('.azurirano').innerText = formatDate(klijent.azurirano)
                copy.querySelector('.edit').href = `./edit.html?id=${klijent.id}`
                copy.querySelector('.remove').addEventListener('click',()=>{
                    if(confirm(`Obrisi Klijenta? ${klijent.ime} ${klijent.prezime} ${klijent.mobilni}`)){
                        fetch(`http://localhost:8000/api/klijent/${klijent.id}`,{
                            method: 'DELETE',
                            
                        })
                    .then(rsp=>{
                            if(rsp.status==204){
                                window.location.href = './indeks.html'
                                return
                            }
                            alert(`Brisanje Klijenta nije uspela(HTTP ${rsp.status})`)
                          })
                    }
                })
                table.appendChild(copy)

            })
        })

}
