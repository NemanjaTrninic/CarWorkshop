const table = document.getElementById('usluga-table')
const template = document.getElementById('usluga')


fetch('http://localhost:8000/api/usluga')
.then(rsp => rsp.json())
.then(data => {
    
    data.forEach(usluga => {
        // tr.innerText = `${klijent.id} ${klijent.ime} ${klijent.prezime} ${klijent.mobilni} ${new Date(klijent.datum_preuzimanja).toLocaleString('sr-RS')}`
        const copy = template.content.cloneNode(true) //kopija sadrzaja
        copy.querySelector('.id').innerText = usluga.id //klasa id
        copy.querySelector('.ime').innerText = usluga.vrsta_usluge
        copy.querySelector('.cena').innerText = usluga.cena +'$' //
        copy.querySelector('.datum').innerText = formatDate(usluga.datum_preuzimanja)
        copy.querySelector('.azurirano').innerText = formatDate(usluga.azurirano)
        copy.querySelector('.edit').href = `./edit_usluga.html?id=${usluga.id}`
        copy.querySelector('.remove').addEventListener('click',()=>{
            if(confirm(`Obrisi Klijenta? ${usluga.ime}`)){
                fetch(`http://localhost:8000/api/usluga/${usluga.id}`,{
                    method: 'DELETE',
                    
                })
            .then(rsp=>{
                    if(rsp.status==204){
                        window.location.href = './usluga.html'
                        return
                    }
                    alert(`Brisanje Usluge  nije uspela(HTTP ${rsp.status})`)
                  })
            }
        })
        
       table.appendChild(copy)

    })
})
