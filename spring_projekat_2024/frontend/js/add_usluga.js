const ime = document.getElementById('ime')
const prezime = document.getElementById('prezime')
const mobilni = document.getElementById('mobilni')
const cena = document.getElementById('cena')

document.getElementById('sacuvaj').addEventListener('click', () => {

    if (vrsta_usluge.value == null || vrsta_usluge.value == '') {
        alert('Naziv usluge mora biti popunjeno!')
        return
    }
    fetch('http://localhost:8000/api/usluga', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            vrsta_usluge: vrsta_usluge.value,
            cena: cena.value
        })
    }).then(rsp => {
            if (rsp.ok) {
                window.location.href = './usluga.html'
                return
            }
            alert(`Izmena Usluge nije uspelo(HTTP ${rsp.status})`)
        })

   
})
