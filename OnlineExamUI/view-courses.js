fetch("http://localhost:8081/api/courses")
    .then(res => res.json())
    .then(data => {
        let rows = "";
        data.forEach((c, index) => {
            rows += `
                <tr>
                    <td>${index + 1}</td>
                    <td>${c.courseName}</td>
                    <td>${c.teacher.firstName} ${c.teacher.lastName}</td>
                    <td>
                        <button class="btn btn-sm btn-primary" onclick="viewExams(${c.id})">
                            View Exams
                        </button>
                    </td>
                </tr>
            `;
        });
        document.getElementById("courseList").innerHTML = rows;
    })
    .catch(err => console.error("Error loading courses:", err));


function viewExams(courseId) {
    window.location.href = "view-exams.html?courseId=" + courseId;
    // در مرحله بعد صفحه لیست امتحان‌ها ساخته می‌شود
}

function goBack() {
    window.location.href = "dashboard.html";
}