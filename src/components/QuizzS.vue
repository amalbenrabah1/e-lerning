<template>
    <div class="quiz-container">
      <SideBar />
      <div class="quiz-content">
        <h2>{{ level }} - {{ subject }}</h2>
        <div v-if="quiz">
          <div v-for="(section, sectionIndex) in quiz.sections" :key="sectionIndex">
            <h3>{{ section.name }}</h3>
            <div v-for="(question, questionIndex) in section.questions" :key="questionIndex" class="question">
              <p>{{ question.questionText }}</p>
              <div class="options">
                <button 
                  v-for="(option, optionIndex) in question.options" 
                  :key="optionIndex" 
                  @click="checkAnswer(question, option)">
                  {{ option }}
                </button>
              </div>
              <p v-if="question.answered">Correct Answer: {{ question.correctAnswer }}</p>
            </div>
          </div>
          <button @click="finishQuiz">Finish Quiz</button>
        </div>
        <div v-else>
          <p>Loading quiz...</p>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  import SideBar from "./SideBar.vue";
  
  export default {
    name: "QuizPage",
    components: {
      SideBar,
    },
    data() {
      return {
        quiz: null,
        subject: this.$route.params.subject,
        level: this.$route.params.level,
        sectionIndex: this.$route.params.sectionIndex,
      };
    },
    methods: {
  async fetchQuizzes(subject, level) {
    try {
      const response = await fetch(`http://localhost:8080/api/quizzes/subject/${subject}/level/${level}`);
      const quizzes = await response.json();
      this.updateSections(level, quizzes);
    } catch (error) {
      console.error("Error fetching quizzes:", error);
    }
  },
  updateSections(level, quizzes) {
    if (level === "beginner") {
      this.beginnerSections = quizzes.map(quiz => ({ question: quiz.question, completed: false }));
    } else if (level === "intermediate") {
      this.intermediateSections = quizzes.map(quiz => ({ question: quiz.question, completed: false }));
    } else if (level === "advanced") {
      this.advancedSections = quizzes.map(quiz => ({ question: quiz.question, completed: false }));
    }
  },
  startQuiz(subject, level, sectionIndex) {
    this.$router.push({
      name: "QuizS",
      params: {
        subject,
        level,
        sectionIndex,
      },
    });
  },
},


    created() {
      this.fetchQuiz();
    },
  };
  </script>
  
  <style scoped>
  .quiz-container {
    display: flex;
    flex-direction: column;
    align-items: center;
    padding: 20px;
  }
  
  .quiz-content {
    width: 80%;
    margin: auto;
  }
  
  .question {
    margin-bottom: 20px;
  }
  
  .options button {
    margin: 5px;
    padding: 10px;
    background-color: #fcbf49;
    border: none;
    color: white;
    border-radius: 5px;
    cursor: pointer;
  }
  
  .options button:disabled {
    background-color: #ccc;
    cursor: not-allowed;
  }
  
  button {
    padding: 10px 20px;
    background-color: #fcbf49;
    border: none;
    color: white;
    border-radius: 5px;
    cursor: pointer;
  }
  
  button:disabled {
    background-color: #ccc;
    cursor: not-allowed;
  }
  </style>
  