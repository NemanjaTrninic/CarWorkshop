let params = new URLSearchParams(window.location.search);
const id = params.get('id')

if (id == null || id == '')
    window.location.href = './indeks.html'


fetch('http://localhost:8000/api/klijent/' + id)
    /* , {
    method: 'GET',
    headers: {
        Accept: 'application/json',
    }
    }) */
     .then(rsp => {
        if (rsp.status == 200)
            return rsp.json()
        alert('Klijent nije pronadjen!')
        window.location.href = './indeks.html'
    }) 
    .then(klijent=>{
        const klijentBreadcrumb = document.getElementById('klijent')
        klijentBreadcrumb.href = `./edit.html?id=${klijent.id}`
        klijentBreadcrumb.innerText = `${klijent.ime} ${klijent.prezime} ${klijent.mobilni}`

        const template = document.getElementById('posao')
        const list = document.getElementById('posao') //
        fetch('http://localhost:8000/api/posao/')
            .then(rsp=>rsp.json())
            .then(posaoList=>{
                posaoList.forEach(posao=>{
                    const copy = template.content.cloneNode(true)
                    copy.querySelector('.form-check-label').innerText = posao.ime
                    const posaoCheck = copy.querySelector('.form-check-input')
                    if(klijent.posao.map(p=>p.id).includes(posao)){ //
                        console.log(posao.id)
                        posaoCheck.checked=true
                    }
                    posaoCheck.addEventListener('change',()=>{
                        fetch(`http://localhost:8000/api/klijent/posao/${klijent.id}`,{
                            method: 'PUT',
                            headers: {
                                'Content-Type': 'application/json'
                            },
                            body: JSON.stringify({ //
                               posao: posao,
                               active: posaoCheck.checked
                            })
            
                        }).then(rsp=>rsp.text())
                    })
                })
                list.appendChild(copy)
            })

    })
