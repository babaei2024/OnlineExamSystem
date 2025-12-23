// گرفتن courseId از URL
const params = new URLSearchParams(window.location.search);
const courseId = params.get("courseId");

if (!courseId) {
    alert("Course ID not found!");
    window.location.href = "view-courses.html";
}

// گرفتن لیست امتحان‌های دوره
fetch("http://localhost:8081/api/exams/course/" + courseId)
    .then(res => res.json())
    .then(exams => {
        let rows = "";
        if (exams.length === 0) {
            rows = `
                <tr>
                    <td colspan="3" class="text-danger text-center">
                        No exams found for this course
                    </td>
                </tr>
            `;
        } else {
            exams.forEach((exam, index) => {
                rows += `
                    <tr>
                        <td>${index + 1}</td>
                        <td>${exam.examTitle}</td>
                        <td>
                            <button onclick="viewQuestions(${exam.id})">
                                View Questions
                            </button>
                        </td>
                    </tr>
                `;
            });
        }
        document.getElementById("examList").innerHTML = rows;
    })
    .catch(err => console.error("Error loading exams:", err));


function viewQuestions(examId) {
    window.location.href = "view-questions.html?examId=" + examId;
}

function goBack() {
    window.location.href = "view-courses.html";
}