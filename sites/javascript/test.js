function deplacer(element, vitesse, direction) {
    var element = document.getElementById(element);
 
    if (vitesse <= 20) {
        var mouvement = 1, temps = 100 / vitesse;
    }
    else if (vitesse > 20 && vitesse <= 40) {
        var mouvement = 2, temps = 200 / vitesse;
    }
    else if (vitesse > 40 && vitesse <= 60) {
        var mouvement = 3, temps = 300 / vitesse;
    }
    else if (vitesse > 60 && vitesse <= 80) {
        var mouvement = 4, temps = 400 / vitesse;
    }
    else if (vitesse > 80 && vitesse <= 100) {
        var mouvement = 5, temps = 500 / vitesse;
    }
    else {
        alert("vitesse entre 0 et 100");
    }
 
    bouger();
    return setInterval(bouger, temps);
 
    function bouger() {
        var haut = element.offsetTop,
            gauche = element.offsetLeft;
 
 
        if (direction == "droite") {
            element.style.left = gauche + mouvement + "px";
        }
        else if (direction == "gauche") {
            element.style.left = gauche - mouvement + "px";
        }
        else if (direction == "haut") {
            element.style.top = haut - mouvement + "px";
        }
        else if (direction == "bas") {
            element.style.top = haut + mouvement + "px";
        }
        else if (direction == "droite-bas") {
            element.style.left = gauche + mouvement + "px";
            element.style.top = haut + mouvement + "px";
        }
        else if (direction == "droite-haut") {
            element.style.left = gauche + mouvement + "px";
            element.style.top = haut - mouvement + "px";
        }
        else if (direction == "gauche-bas") {
            element.style.left = gauche - mouvement + "px";
            element.style.top = haut + mouvement + "px";
        }
        else if (direction == "gauche-haut") {
            element.style.left = gauche - mouvement + "px";
            element.style.top = haut - mouvement + "px";
        }
        else {
            alert("direction autorisée: gauche, droite, haut, bas, gauche-haut, gauche-bas, droite-haut, droite-bas")
        }
    }
}

