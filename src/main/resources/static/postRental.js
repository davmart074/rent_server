function postReservation(event) {
    console.log(event);

    event.preventDefault();

const form=new FormData(event.currentTarget);
    console.log(form);

    fetch('/post-reservation', {
        method: 'post',
        body: form

    })
    .then(res => res.text())
    .then(result => {
        console.log(result);

        console.log(result == "bad date")
        if(result == "bad date"){
            alert("Helytelen dátumok.")
        }
        else if(result == "not available at this interval"){
            alert("A választott autó sajnos nem elérhető a választott napokon.")
        }
        else {
            alert("A foglalás sikeres!")
        }

    })
    .catch((err) => {
        console.log(err)
    });
}

const megrendeloForm = document.getElementById('megrendelo-form');
megrendeloForm.addEventListener('submit', postReservation);

console.log(megrendeloForm);