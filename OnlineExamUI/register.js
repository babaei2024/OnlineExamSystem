function register() {

    let user = {
        firstName: document.getElementById("firstName").value,
        lastName: document.getElementById("lastName").value,
        email: document.getElementById("email").value,
        password: document.getElementById("password").value
    };

    let role = document.getElementById("role").value;

    fetch("http://localhost:8080/api/users/register?role=" + role, {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(user)
    })
    .then(res => res.json())
    .then(data => {
        document.getElementById("result").innerText = "Registered ✔";
    })
    .catch(err => alert("Error: " + err));
}
