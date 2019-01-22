var shoppingCartCounter = document.getElementsByClassName('btn btn-success');

console.log(document.cookie[1]);


for (var i = 0; i < shoppingCartCounter.length; i++) {
    shoppingCartCounter[i].addEventListener('click', function (alert) {  })
}

window.onload = function () {
    let btns = document.querySelectorAll("button.icon-button");
    console.log("found the trash")
    btns.forEach(function (btn) {
        btn.addEventListener("click", function(){
            btn.parentElement.parentElement.parentElement.remove();
            console.log("del");
        });
    });

};