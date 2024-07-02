let params = new URLSearchParams(window.location.search);
const id = params.get('id')

if (id == null || id == '')
    window.location.href = './indeks.html'

const breadcrumb = document.getElementById('breadcrumb')
const klijentId = document.getElementById('id')
const ime = document.getElementById('ime')
const prezime = document.getElementById('prezime')
const mobilni = document.getElementById('mobilni')
const usluga = document.getElementById('usluga')
const datum = document.getElementById('datum')//
const azurirano = document.getElementById('azurirano')
const uslugaSelect = document.getElementById('usluga')


fetch('http://localhost:8000/api/klijent/' + id, {
    method: 'GET',
    headers: {
        Accept: 'application/json',
    }
})
    .then(rsp => {
        if (rsp.status == 200)
            return rsp.json()
        alert('Klijent nije pronadjen!')
        window.location.href = './indeks.html'
    })
    .then(data => {
        breadcrumb.innerText = `${data.ime} ${data.prezime}`
        klijentId.value = data.id
        ime.value = data.ime
        prezime.value = data.prezime
        mobilni.value = data.mobilni

        // Loading
        fetch('http://localhost:8000/api/usluga')
          .then(rsp => rsp.json())
            .then(uslugaData => {
                uslugaData.forEach(usluga=> { //mozda ide data
                    const option = document.createElement('option')
                    if(usluga.id === data.usluga.id){
                        option.selected = true
                    }
                    option.value = usluga.id
                    option.text = usluga.vrsta_usluge +' ('+usluga.cena+'$)'//mozda ide .ime?
                    uslugaSelect.appendChild(option)
                })
            })

        datum.value = formatDate(data.datum_preuzimanja)
        azurirano.value = formatDate(data.azurirano)

        document.getElementById('sacuvaj').addEventListener('click', () => {
            fetch(`http://localhost:8000/api/klijent/${data.id}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    ime: ime.value,
                    prezime: prezime.value,
                    mobilni: mobilni.value,
                    usluga_id: usluga.value,
                })
            })
                .then(rsp => {
                    if (rsp.ok) {
                        window.location.href = './indeks.html'
                        return
                    }
                    alert(`Izmena Klijenta nije uspela(HTTP ${rsp.status})`)
                })
        })

    })