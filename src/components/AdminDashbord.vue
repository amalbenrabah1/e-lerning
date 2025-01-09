<template>
  <div class="admin-dashboard">
    <!-- Sidebar -->
    <SideBarA />

    <!-- Main Content -->
    <div class="content">
      <h1>Admin Dashboard</h1>

      <!-- Cards to display Teachers and Students -->
      <div class="row">
        <div class="col-md-6">
          <!-- Card for Teachers -->
          <div class="card">
            <div class="card-body">
              <h5 class="card-title">Teachers</h5>
              <p class="card-text">{{ teachers.length }} Teachers</p>
              <button @click="viewTeachers" class="btn btn-primary">View Teachers</button>
            </div>
          </div>
        </div>

        <div class="col-md-6">
          <!-- Card for Students -->
          <div class="card">
            <div class="card-body">
              <h5 class="card-title">Students</h5>
              <p class="card-text">{{ students.length }} Students</p>
              <button @click="viewStudents" class="btn btn-primary">View Students</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import SideBarA from "@/components/SideBarA.vue"; // Assurez-vous que le chemin est correct
import axios from "axios";
export default {
  name: "AdminDashboard",
  components: {
    SideBarA
  },
  data() {
    return {
      teachers: [],
      students: []
    };
  },
  methods: {
    fetchUsers() {
      axios.get("http://localhost:8080/api/info") // Remplacez par votre URL d'API
        .then(response => {
          this.users = response.data;  // Stocke tous les utilisateurs
          console.log("Données des utilisateurs reçues :", this.users);  // Affichage des données dans la console
          this.filterUsers();  // Séparer les utilisateurs en étudiants et enseignants
        })
        .catch(error => {
          console.error("Erreur lors de la récupération des utilisateurs :", error);
        });
    },
    filterUsers() {
      // Filtrer les étudiants et enseignants
      this.students = this.users.filter(user => user.role === 'student');
      this.teachers = this.users.filter(user => user.role === 'teacher');
      
      console.log("Étudiants :", this.students);  // Afficher les étudiants filtrés
      console.log("Enseignants :", this.teachers);  // Afficher les enseignants filtrés
    },
    viewTeachers() {
      this.$router.push("/ListTeachers"); // Redirige vers une page des enseignants
    },
    viewStudents() {
      this.$router.push("/ListS"); // Redirige vers une page des étudiants
    }
  },
  mounted() {
    this.fetchUsers(); // Récupère les utilisateurs à l'initialisation
  }
};
</script>

<style scoped>
.admin-dashboard {
  display: flex;
  flex-direction: row;
  margin-top: 100px;
  background-image: url('@/assets/img/testingg.jpg');
  background-position: center;
  background-repeat: repeat;
  
}

.content {
  margin-left: 250px; /* Décalage pour la barre latérale */
  flex-grow: 1;
  margin-right: 250px;
  padding: 20px;
  
}

.card {
  border: 1px solid #ccc;
  border-radius: 8px;
  margin-bottom: 20px;
  height: 100%;
}

.card-body {
  padding: 15px;
}

.card-title {
  font-size: 1.2rem;
  font-weight: bold;
}

.card-text {
  font-size: 1rem;
}

.btn {
  background-color: #fcbf49;
  color: white;
  padding: 5px 10px;
  border-radius: 5px;
}

.btn:hover {
  background-color: #ff9800;
}
</style>
