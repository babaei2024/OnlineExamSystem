let user = JSON.parse(localStorage.getItem("loggedUser"));

if (!user) {
    alert("Please login first");
    window.location.href = "login.html";
}

document.getElementById("fullName").innerText = user.fullName;
document.getElementById("role").innerText = user.role;

let menu = document.getElementById("menu");

if (user.role === "TEACHER") {
    menu.innerHTML = `
        <button class="btn btn-success m-2" onclick="createCourse()">Create Course</button>
        <button class="btn btn-primary m-2" onclick="createExam()">Create Exam</button>
        <button class="btn btn-warning m-2" onclick="createQuestion()">Create Question</button>
        <button class="btn btn-info m-2" onclick="viewCourses()">View Courses</button>
        <button class="btn btn-danger m-2" onclick="logout()">Logout</button>
    `;
} else {
    menu.innerHTML = `
        <button class="btn btn-info m-2" onclick="viewCourses()">View Courses</button>
        <button class="btn btn-secondary m-2" onclick="viewExams()">View Exams</button>
        <button class="btn btn-warning m-2" onclick="answerExam()">Answer Exam</button>
        <button class="btn btn-danger m-2" onclick="logout()">Logout</button>
    `;
}

function viewCourses() {
    window.location.href = "view-courses.html";   // صفحه لیست دوره‌ها
}

function logout() {
    localStorage.removeItem("loggedUser");
    window.location.href = "login.html";
}

// for later
function createCourse() { alert("Create Course Clicked"); }
function createExam() { alert("Create Exam Clicked"); }
function createQuestion() { alert("Create Question Clicked"); }
function viewExams() { alert("View Exams Clicked"); }
function answerExam() { alert("Answer Exam Clicked"); }