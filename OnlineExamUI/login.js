function login() {
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;

    fetch("http://localhost:8080/api/users/login", {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify({ email, password })
    })
    .then(res => res.json())
    .then(data => {

        if (data.userId) {
            localStorage.setItem("loggedUser", JSON.stringify(data));
            window.location.href = "dashboard.html";
        } else {
            document.getElementById("result").innerText = data.message;
        }
    })
    .catch(err => alert("Error: " + err));
}
