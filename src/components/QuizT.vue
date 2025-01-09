<template>
  <div class="page-container">
    <!-- Sidebar -->
    <SideBarT />
    <div class="content">
      <div class="profile-container">
        <h1 class="form-title">Add a Quiz</h1>
        <form @submit.prevent="submitQuiz" class="quiz-form">
          <div class="form-group">
            <label>Question:</label>
            <input v-model="quiz.question" class="form-input" required />
          </div>
          <div class="form-group">
            <label>Options:</label>
            <input v-model="quiz.options[0]" class="form-input" placeholder="Option 1" required />
            <input v-model="quiz.options[1]" class="form-input" placeholder="Option 2" required />
            <input v-model="quiz.options[2]" class="form-input" placeholder="Option 3" required />
          </div>
          <div class="form-group">
            <label>Correct Index:</label>
            <input
              v-model.number="quiz.correctIndex"
              class="form-input"
              type="number"
              min="0"
              max="2"
              required
            />
          </div>
          <div class="form-group">
            <label>Level:</label>
            <select v-model="quiz.level" class="form-select" required>
              <option disabled value="">Select a level</option>
              <option value="Beginner">Beginner</option>
              <option value="Intermediate">Intermediate</option>
              <option value="Advanced">Advanced</option>
            </select>
          </div>
          <div class="form-group">
            <label>Subject:</label>
            <select v-model="quiz.subject" class="form-select" required>
              <option disabled value="">Select a subject</option>
              <option value="Spanish">Spanish</option>
              <option value="Italian">Italian</option>
              <option value="English">English</option>
              <option value="Arabic">Arabic</option>
              <option value="French">French</option>
              <option value="Turkish">Turkish</option>
              <option value="Japanese">Japanese</option>
              <option value="German">German</option>
            </select>
          </div>
          <button type="submit" class="btn-submit">Add</button>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import SideBarT from './SideBarT.vue';
import Swal from 'sweetalert2';

export default {
  name: 'QuizT',
  components: {
    SideBarT,
  },
  data() {
    return {
      quiz: {
        question: '',
        options: ['', '', ''],
        correctIndex: 0,
        level: '',
        subject: '',
      },
    };
  },
  methods: {
    async submitQuiz() {
      try {
        await axios.post('http://localhost:8080/api/quizzes', this.quiz);
        Swal.fire({
          title: 'Success!',
          text: 'Quiz added successfully.',
          icon: 'success',
          confirmButtonText: 'OK',
          background: '#fff3cd',
          iconColor: '#4bbf73',
        });
        this.quiz = { question: '', options: ['', '', ''], correctIndex: 0, level: '', subject: '' };
        this.$emit('quizAdded');
      } catch (error) {
        console.error(error);
        Swal.fire({
          title: 'Error!',
          text: "There was an error while adding the quiz.",
          icon: 'error',
          confirmButtonText: 'Try Again',
          background: '#f8d7da',
          iconColor: '#f44336',
        });
      }
    },
  },
};
</script>
<style scoped>
.page-container {
  display: flex;
  min-height: 100vh;
  background-color: #f9f9f9;
  font-family: 'Arial', sans-serif;
}

/* Content */
.content {
  flex: 1;
  padding: 20px;
  display: flex;
  justify-content: center;
  align-items: center;
}

/* Form Container */
.profile-container {
  background: white;
  padding: 30px;
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  max-width: 600px;
  width: 100%;
}

.form-title {
  font-size: 24px;
  font-weight: bold;
  color: #333;
  text-align: center;
  margin-bottom: 20px;
}

/* Form */
.quiz-form .form-group {
  margin-bottom: 15px;
}

.quiz-form label {
  font-size: 16px;
  color: #555;
  margin-bottom: 5px;
  display: block;
}

.form-input,
.form-select {
  width: 100%;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
  font-size: 14px;
  margin-top: 5px;
}

.form-input:focus,
.form-select:focus {
  border-color: #007bff;
  outline: none;
  box-shadow: 0 0 3px rgba(0, 123, 255, 0.5);
}

.btn-submit {
  width: 100%;
  background-color: #fcbf49;
  color: white;
  padding: 10px 15px;
  font-size: 16px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s;
}

button:hover {
  background-color: #f6a52d;
}

/* Responsive */
@media (max-width: 768px) {
  .profile-container {
    padding: 20px;
  }

  .form-title {
    font-size: 20px;
  }

  .btn-submit {
    font-size: 14px;
    padding: 8px;
  }
}
</style>
