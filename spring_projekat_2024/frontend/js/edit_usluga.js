let params = new URLSearchParams(window.location.search);
const id = params.get('id')

if (id == null || id == '')
    window.location.href = './indeks.html'

const breadcrumb = document.getElementById('breadcrumb')
const klijentId =  document.getElementById('id')
const ime =  document.getElementById('vrsta_usluge')
const cena =  document.getElementById('cena')
const datum =  document.getElementById('datum')//
const azurirano =  document.getElementById('azurirano')

fetch('http://localhost:8000/api/usluga/' + id,{
    method: 'GET',
    headers: {
      Accept: 'application/json',
    }
    })
    .then(rsp => {
        if (rsp.status === 200)
            return rsp.json()
        alert('Usluga nije pronadjena!')
        window.location.href = './usluga.html'
    })
    .then(data => {
        breadcrumb.innerText = `${data.vrsta_usluge}` //prepravka
        klijentId.value = data.id
        vrsta_usluge.value = data.vrsta_usluge // prepravka
        cena.value = data.cena //
        datum.value = formatDate(data.datum_preuzimanja)
        azurirano.value = formatDate(data.azurirano)

        document.getElementById('sacuvaj').addEventListener('click',()=>{
            fetch(`http://localhost:8000/api/usluga/${data.id}`,{
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify( {
                    vrsta_usluge: vrsta_usluge.value  //prepravka
                })
            })
            .then(rsp=>{
                if(rsp.ok){
                    window.location.href = './usluga.html'
                    return
                }
                alert(`Izmena Usluge nije uspela(HTTP ${rsp.status})`)
              })
        })

    })