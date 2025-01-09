<template>
  <div class="dashboard-container">
    <!-- Sidebar aligné à gauche -->
    <SideBar />

    <div class="main-content">
      <h1 class="page-title">Liste des Cours</h1>

      <div v-if="courses.length === 0" class="no-courses">
        <p>Aucun cours disponible.</p>
      </div>

      <div v-for="course in courses" :key="course.id" class="course-card">
        <h2 class="course-title">{{ course.title }}</h2>
        <p class="course-description">{{ course.description }}</p>
        <p class="course-details">Sujet : {{ course.subject }}</p>
        <p class="course-details">Niveau : {{ course.level }}</p>
        <button @click="downloadFile(course.id)" class="download-btn">Télécharger le Cours PDF</button>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import Swal from "sweetalert2";
import SideBar from './SideBar.vue';

export default {
  name: "CourseList",
  components: {
    SideBar,
  },
  data() {
    return {
      courses: [],
    };
  },
  created() {
    this.fetchCourses();
  },
  methods: {
    async fetchCourses() {
      try {
        const response = await axios.get("http://localhost:8080/api/courses/");
        this.courses = response.data;

        // Filtrer les cours si un sujet est passé dans l'URL
        const subject = this.$route.query.subject;
        if (subject) {
          this.courses = this.courses.filter(course => course.subject === subject);
        }
      } catch (error) {
        console.error("Erreur lors de la récupération des cours", error);
      }
    },

    async downloadFile(courseId) {
      try {
        // Afficher une alerte de chargement
        Swal.fire({
          title: "Téléchargement en cours",
          text: "Veuillez patienter pendant que nous téléchargeons le cours.",
          icon: "info",
          allowOutsideClick: false,
          showConfirmButton: false,
          didOpen: () => {
            Swal.showLoading(); // Montre l'animation de chargement
          },
        });

        // Requête pour télécharger le fichier
        const response = await axios.get(`http://localhost:8080/api/courses/download/${courseId}`, {
          responseType: "blob", // Pour récupérer le fichier sous forme de blob
        });

        // Création du fichier blob pour le téléchargement
        const blob = new Blob([response.data]);
        const url = window.URL.createObjectURL(blob);
        const link = document.createElement("a");
        link.href = url;
        link.setAttribute("download", `course_${courseId}.pdf`);
        document.body.appendChild(link);
        link.click();

        // Fermer l'alerte de chargement et afficher une alerte de succès
        Swal.fire({
          title: "Succès !",
          text: "Le cours a été téléchargé avec succès.",
          icon: "success",
          confirmButtonText: "OK",
          confirmButtonColor: "#4caf50",
        });
      } catch (error) {
        // Afficher une alerte d'erreur
        Swal.fire({
          title: "Erreur",
          text: "Une erreur est survenue lors du téléchargement du cours. Veuillez réessayer.",
          icon: "error",
          confirmButtonText: "OK",
          confirmButtonColor: "#f44336",
        });
        console.error("Erreur lors du téléchargement du fichier", error);
      }
    },
  },
};
</script>

<style scoped>
.dashboard-container {
  background-image: url('@/assets/img/ch.jpg'); /* Path to your image */
  background-position: center;
  background-repeat: repeat;
  color: white;
}

.dashboard-container {
  display: flex;
  min-height: 100vh;
  background-color: #f7f9fc;
  margin-top: 60px;
}

.main-content {
  margin-left: 250px;
  margin-top: 2rem;
  padding: 2rem;
  width: 100%;
  overflow-y: auto;
}

.page-title {
  font-size: 2rem;
  color: #333;
  margin-bottom: 1rem;
}

.no-courses {
  font-size: 1.2rem;
  color: #888;
  text-align: center;
}

.course-card {
  background-color: #fff;
  border: 1px solid #ddd;
  border-radius: 8px;
  padding: 1.5rem;
  margin-bottom: 1.5rem;
  box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
  transition: box-shadow 0.3s ease;
}

.course-card:hover {
  box-shadow: 0px 8px 20px rgba(0, 0, 0, 0.15);
}

.course-title {
  font-size: 1.6rem;
  color: #333;
  margin-bottom: 0.5rem;
}

.course-description {
  font-size: 1.1rem;
  color: #555;
  margin-bottom: 0.8rem;
}

.course-details {
  font-size: 1rem;
  color: #777;
  margin-bottom: 0.5rem;
}

.download-btn {
  background-color: #4caf50;
  color: white;
  padding: 0.8rem 1.5rem;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-size: 1rem;
  transition: background-color 0.3s ease, transform 0.2s ease;
}

.download-btn:hover {
  background-color: #45a049;
  transform: scale(1.05);
}

.download-btn:active {
  transform: scale(1);
}
</style>
