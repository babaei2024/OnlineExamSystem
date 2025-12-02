// گرفتن examId از URL
const params = new URLSearchParams(window.location.search);
const examId = params.get("examId");

if (!examId) {
    alert("Exam ID not found!");
    window.location.href = "view-exams.html";
}

// دریافت لیست سوال‌ها
fetch("http://localhost:8080/api/questions/exam/" + examId)
    .then(res => res.json())
    .then(data => {
        let html = "<h3>Questions</h3><ul>";

        if (data.length === 0) {
            html += "<li class='text-danger'>No questions found for this exam.</li>";
        } else {
            data.forEach(q => {
                html += <li><strong>${q.questionText}</strong></li>;
            });
        }

        html += "</ul>";
        document.getElementById("questionsList").innerHTML = html;
    })
    .catch(err => console.error("Error:", err));

function goBack() {
    window.location.href = "view-exams.html";
}