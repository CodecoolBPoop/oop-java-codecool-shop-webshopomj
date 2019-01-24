var shoppingCartCounter = document.getElementsByClassName('btn btn-success');

console.log(document.cookie[1]);


for (var i = 0; i < shoppingCartCounter.length; i++) {
    shoppingCartCounter[i].addEventListener('click', function (alert) {  })
}



$().ready(function ()  {
    let btns = document.querySelectorAll("button.icon-button");
    btns.forEach(function (btn) {
        btn.addEventListener("click", function(event){
            $.ajax({
                type: "POST",
                url: "/rem_cart",
                data: {id:event.target.id},
                dataType: "JSON"
            });

            console.log("del");
            console.log(event.target["id"]);
        });
    })
});