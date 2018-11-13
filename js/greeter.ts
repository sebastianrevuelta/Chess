function greeter(person) {
    return "Hello, " + person;
}

let user = "Sebas";

let match = Java.type("com.sebas.core.Match");

document.title = "VulnChess"
document.body.innerHTML = greeter(user);
