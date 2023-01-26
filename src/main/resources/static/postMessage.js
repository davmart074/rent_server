function postMessage(event) {
    console.log(event);

    event.preventDefault();

const form=new FormData(event.currentTarget);
    console.log(form);

    fetch('/post-message', {
        method: 'post',
        body: form

    })
    .then(res => res.text())
    .then(result => {
        console.log(result);

        
    })
    .catch((err) => {
        console.log(err)
    });
}

const messageForm = document.getElementById('rental-message');
messageForm.addEventListener('submit', postMessage);