<template>
  <div class="page-container">
    <!-- Sidebar -->
    <SideBarT />

    <!-- Main Content (Course Publishing Form) -->
    <div class="content">
      <!-- Profile Section -->
      <div class="profile-container">
        <h2>Publish a New Course</h2>

        <form @submit.prevent="submitCourse" class="course-form">
          <div class="form-group">
            <label for="title">Title</label>
            <input v-model="title" id="title" type="text" required />
          </div>

          <div class="form-group">
            <label for="description">Description</label>
            <textarea v-model="description" id="description" required></textarea>
          </div>

          <div class="form-group">
            <label for="subject">Subject</label>
            <select v-model="subject" id="subject" required>
              <option disabled value="">Choose a subject</option>
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

          <div class="form-group">
            <label for="level">Level</label>
            <select v-model="level" id="level" required>
              <option disabled value="">Choose a level</option>
              <option value="Beginner">Beginner</option>
              <option value="Intermediate">Intermediate</option>
              <option value="Advanced">Advanced</option>
            </select>
          </div>

          <div class="form-group">
            <label for="file">Course PDF</label>
            <input @change="handleFileChange" id="file" type="file" required />
          </div>

          <button type="submit" class="btn-primary">Add</button>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import SideBarT from './SideBarT.vue';
import axios from 'axios';
import Swal from 'sweetalert2';

export default {
  name: 'CoursesT',
  components: {
    SideBarT,
  },
  data() {
    return {
      title: '',
      description: '',
      subject: '',
      level: '',
      file: null,
    };
  },
  methods: {
    handleFileChange(event) {
      this.file = event.target.files[0];
    },
    async submitCourse() {
      const formData = new FormData();
      formData.append('title', this.title);
      formData.append('description', this.description);
      formData.append('subject', this.subject);
      formData.append('level', this.level);
      formData.append('file', this.file);

      try {
        await axios.post('http://localhost:8080/api/courses/add', formData, {
          headers: {
            'Content-Type': 'multipart/form-data',
          },
        });

        // Show success alert
        Swal.fire({
          title: 'Course added successfully!',
          icon: 'success',
          confirmButtonColor: '#fcbf49',
          timer: 2000, // Auto-close after 2 seconds
        });

        // Clear form after submission
        this.title = '';
        this.description = '';
        this.subject = '';
        this.level = '';
        this.file = null;
      } catch (error) {
        console.error('Error adding the course', error);

        // Show error alert
        Swal.fire({
          title: 'An error occurred',
          text: 'Please try again!',
          icon: 'error',
          confirmButtonColor: '#fcbf49',
        });
      }
    },
  },
};
</script>

<style scoped>
.page-container {
  background-image: url('@/assets/img/ch.jpg'); /* Path to your image */
  background-position: center;
  background-repeat: no-repeat;
  background-size: cover;
  color: white; /* Set text color for contrast with background */
  display: flex;
  min-height: 100vh;
  margin-top: 60px;
}

.content {
  margin-left: 260px;
  padding: 40px;
  flex-grow: 1;
}

.profile-container {
  max-width: 700px;
  margin: 0 auto;
  padding: 20px;
  background: rgba(255, 255, 255, 0.8); /* Semi-transparent background */
  border-radius: 10px;
  box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
  text-align: center;
}

.profile-container h2 {
  margin-bottom: 20px;
  color: #343a40;
}

/* Form */
.course-form .form-group {
  margin-bottom: 20px;
}

.course-form label {
  font-weight: bold;
  color: #343a40;
  margin-bottom: 8px;
  display: block;
}

.course-form input,
.course-form textarea,
.course-form select {
  width: 100%;
  padding: 10px;
  font-size: 14px;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
}

.course-form input[type="file"] {
  padding: 10px 0;
  border: none;
}

.course-form button.btn-primary {
  background-color: #fcbf49;
  color: white;
  padding: 12px 20px;
  font-size: 16px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s;
  width: 100%;
}

.course-form button.btn-primary:hover {
  background-color: #f6a52d;
}

/* Add responsiveness for mobile */
@media (max-width: 768px) {
  .content {
    margin-left: 0;
    padding: 20px;
  }
  .profile-container {
    width: 100%;
  }
}
</style>
