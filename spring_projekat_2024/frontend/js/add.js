const ime = document.getElementById('ime')
const prezime = document.getElementById('prezime')
const mobilni = document.getElementById('mobilni')
const uslugaSelect = document.getElementById('usluga')
const cena = document.getElementById('cena')
const addClientButton = document.getElementById('addClientButton')

fetch('http://localhost:8000/api/usluga')
          .then(rsp => rsp.json())
            .then(uslugaData => {
                uslugaData.forEach(usluga => {
                    const option = document.createElement('option')
                    option.value = usluga.id
                    option.text = usluga.vrsta_usluge + ' ('+usluga.cena+'$)'//
                    uslugaSelect.appendChild(option)
                })
                document.getElementById('sacuvaj').addEventListener('click', () => {

                    if (ime.value == null || ime.value == '') {
                        alert('Ime ne sme biti prazno!')
                        return
                    }
                
                    if (prezime.value == null || prezime.value == '') {
                        alert('Prezime ne sme biti prazno!')
                        return
                    }
                
                    if (mobilni.value == null || mobilni.value == '') {
                        alert('Mobilni mora biti unet!')
                        return
                    }

                
                
                    fetch('http://localhost:8000/api/klijent', {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json'
                        },
                        body: JSON.stringify({
                            ime: ime.value,
                            prezime: prezime.value,
                            mobilni: mobilni.value,
                            usluga_id: usluga.value
                        })
                    }).then(rsp => {
                            if (rsp.ok) {
                                window.location.href = './indeks.html'
                                return
                            }
                            alert(`Izmena Klijenta nije uspela(HTTP ${rsp.status})`)
                        })
                
                   
                })
                
            })


addClientButton.addEventListener('click',(e)=>{
    windows.location.href ="./add.html"
})