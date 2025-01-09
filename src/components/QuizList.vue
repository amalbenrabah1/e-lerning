<template>
  <div class="page-container">
    <SideBar />
    <div class="content">
      <div class="quiz-list">
        <h2 class="title">Interactive Quiz</h2>
        <ul class="quiz-items">
          <li v-for="(quizItem, index) in filteredQuizzes" :key="quizItem.id" class="quiz-item">
            <p><strong>Question {{ index + 1 }}:</strong> {{ quizItem.question }}</p>
            <p><strong>Level:</strong> {{ quizItem.level }}</p>
            <p><strong>Subject:</strong> {{ quizItem.subject }}</p>
            <p><strong>Options:</strong></p>
            <ul class="options-list">
              <li v-for="(option, idx) in quizItem.options" :key="idx" class="option-item">
                <label>
                  <input
                    type="radio"
                    :name="'quiz-' + index"
                    :value="idx"
                    v-model="userAnswers[index]"
                    class="radio-input"
                  />
                  {{ option }}
                </label>
              </li>
            </ul>
            <p v-if="results.length > 0">
              <span :class="{'correct-answer': results[index], 'wrong-answer': !results[index]}">
                {{ results[index] ? 'Correct' : 'Incorrect' }}
              </span>
            </p>
          </li>
        </ul>

        <button @click="checkAllAnswers" class="check-btn">Check All</button>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import SideBar from "./SideBar.vue";
import Swal from "sweetalert2";

export default {
  name: "QuizList",
  components: {
    SideBar,
  },
  data() {
    return {
      quizzes: [],
      filteredQuizzes: [],
      selectedSubject: this.$route.query.subject,
      selectedLevel: this.$route.query.level,
      userAnswers: [],
      results: [],
    };
  },
  methods: {
    async fetchQuizzes() {
      try {
        const response = await axios.get("http://localhost:8080/api/quizzes", {
          headers: {
            Authorization: `Bearer ${localStorage.getItem("token")}`,
          },
        });
        this.quizzes = response.data;
        this.filterQuizzes();
      } catch (error) {
        console.error("Error loading quizzes:", error);
        alert("Error loading quizzes");
      }
    },
    filterQuizzes() {
      this.filteredQuizzes = this.quizzes.filter((quiz) => {
        const subject = quiz.subject ? quiz.subject.toLowerCase() : "";
        const level = quiz.level ? quiz.level.toLowerCase() : "";
        return (
          subject === this.selectedSubject.toLowerCase() &&
          level === this.selectedLevel.toLowerCase()
        );
      });
      this.userAnswers = Array(this.filteredQuizzes.length).fill(null);
    },
    checkAllAnswers() {
  this.results = this.filteredQuizzes.map((quizItem, index) => {
    const userAnswerIndex = this.userAnswers[index];
    const correctAnswerIndex = quizItem.correctIndex;
    return userAnswerIndex === correctAnswerIndex;
  });

  const correctAnswersCount = this.results.filter((result) => result).length;
  const totalQuizzes = this.results.length;

  if (correctAnswersCount === totalQuizzes) {
    localStorage.setItem(`${this.selectedLevel}Completed`, "true");

    // Remove starCount if not used
    let title = "";
    let text = "";
    let icon = "success";
   

    if (this.selectedLevel === "beginner") {
      title = "Bravo! 🌟";
      text = "You answered all questions correctly in the Beginner level!";
      
    } else if (this.selectedLevel === "intermediate") {
      title = "Great Job! 🌟🌟";
      text = "You answered all questions correctly in the Intermediate level!";
      
    } else if (this.selectedLevel === "advanced") {
      title = "Amazing! 🌟🌟🌟";
      text = "You answered all questions correctly in the Advanced level!";
      
    }

    
    // Show sweet alert
    Swal.fire({
      title: title,
      text: text,
      icon: icon,
      confirmButtonText: "Proceed to next level",
      confirmButtonColor: "#4caf50",
    }).then(() => {
      this.$router.push("/levels");
    });
  } else {
    Swal.fire({
      title: "Oops 😔",
      text: "You didn't answer all the questions correctly. Try again!",
      icon: "error",
      confirmButtonText: "Retry",
      confirmButtonColor: "#dc3545",
    });
  }
}
},
  created() {
    this.fetchQuizzes();
  },
};
</script>

<style scoped>
.correct-answer {
  color: #28a745;
  font-weight: bold;
  animation: fadeIn 0.5s ease;
}

.wrong-answer {
  color: #dc3545;
  font-weight: bold;
  animation: fadeIn 0.5s ease;
}

@keyframes fadeIn {
  0% {
    opacity: 0;
    transform: translateY(-10px);
  }
  100% {
    opacity: 1;
    transform: translateY(0);
  }
}

.page-container {
  display: flex;
  min-height: 100vh;
  background-color: #f4f7fb;
}

.content {
  flex: 1;
  padding: 40px;
  display: flex;
  justify-content: center;
  align-items: flex-start;
}

.quiz-list {
  width: 100%;
  max-width: 800px;
  margin-top: 30px;
  background: #ffffff;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  padding: 20px;
}

.quiz-items {
  list-style-type: none;
  padding: 0;
}

.quiz-item {
  background: #f9fafb;
  padding: 15px;
  margin-bottom: 15px;
  border-radius: 8px;
  box-shadow: 0 2px 6px rgba(0, 0, 0, 0.1);
}

.quiz-item p {
  margin: 5px 0;
  color: #333;
}

.options-list {
  list-style-type: none;
  padding: 0;
}

.option-item {
  margin: 10px 0;
}

.radio-input {
  margin-right: 10px;
}

.check-btn {
  padding: 10px 20px;
  background-color: #4caf50;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.check-btn:hover {
  background-color: #45a049;
}
</style>
