// دریافت اطلاعات کاربر از localStorage
let user = JSON.parse(localStorage.getItem("loggedUser"));

if (!user) {
    alert("Please login first");
    window.location.href = "login.html";
}

// نمایش نام و نقش کاربر
document.getElementById("fullName").innerText =
    user.fullName || (user.firstName + " " + user.lastName);

document.getElementById("role").innerText = user.role;

// ساخت منو
let menu = document.getElementById("menu");

if (user.role === "TEACHER") {
    menu.innerHTML = `
        <button class="btn btn-primary m-2" onclick="createCourse()">Create Course</button>
        <button class="btn btn-primary m-2" onclick="createExam()">Create Exam</button>
        <button class="btn btn-primary m-2" onclick="createQuestion()">Create Question</button>
        <button class="btn btn-info m-2" onclick="viewCourses()">View Courses</button>
        <button class="btn btn-danger m-2" onclick="logout()">Logout</button>
    `;
} else if (user.role === "STUDENT") {
    menu.innerHTML = `
        <button class="btn btn-warning m-2" onclick="viewCourses()">View Courses</button>
        <button class="btn btn-warning m-2" onclick="viewExams()">View Exams</button>
        <button class="btn btn-warning m-2" onclick="answerExam()">Answer Exam</button>
        <button class="btn btn-danger m-2" onclick="logout()">Logout</button>
    `;
}