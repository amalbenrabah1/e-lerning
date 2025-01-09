<template>
  <div class="dashboard-container">
    <!-- SideBar aligné à gauche -->
    <SideBarT />

    <!-- Contenu principal avec le message et les deux cartes -->
    <div class="main-content container mt-5 text-center">
      <p class="thank-you-message">
        <span class="highlight">Thank you,</span> for your willingness to publish courses for free on our app.  
        Your contributions will help children study independently through the app.  
        Here's what you can do:
      </p>
      <div class="row">
        <div class="pastel-card">
          <router-link to="/CoursesT" class="card-link">
            <h3>Publish Courses</h3>
          </router-link>
          <p>Upload courses based on subjects and levels.</p>
        </div>
        <div class="pastel-card">
          <router-link to="/QuizT" class="card-link">
            <h3>Publish Quiz</h3>
          </router-link>
          <p>Upload Quiz based on subjects and levels.</p>
        </div>
        <div class="pastel-card">
          <router-link to="/ProfilT" class="card-link">
            <h3>Update Profile</h3>
          </router-link>
          <p>Modify your personal information to keep your profile up-to-date.</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import SideBarT from './SideBarT.vue';

export default {
  name: 'DashBoardTeacher',
  components: {
    SideBarT,
  },
};
</script>

<style scoped>
.dashboard-container {
  background-image: url('@/assets/img/testingg.jpg');
  background-position: center;
  background-repeat: repeat;
  color: white;
  display: flex;
  min-height: 100vh;
  background-color: #f7f9fc;
}

.main-content {
  flex-grow: 1;
  padding: 1rem;
  margin-top: 200px;
  margin-left: 200px;
  align-self: flex-start;
  text-align: center;
}

.thank-you-message {
  margin-top: 2px;
  font-size: 1.5rem;
  color: #333;
  margin-bottom: 2rem;
  line-height: 1.8; /* Espace entre les lignes pour un aspect paragraphe */
  max-width: 600px; /* Largeur limitée pour un meilleur centrage */
  margin-left: auto;
  margin-right: auto;
}

.thank-you-message .highlight {
  color: #ff7f50; /* Couleur personnalisée pour "Thank you" */
  font-weight: bold;
}

.row {
  display: flex;
  flex-wrap: wrap;
  gap: 2rem;
  justify-content: center;
  align-items: center;
}

.pastel-card {
  background-color: #ffe4e1; /* Pastel pink */
  border: 1px solid #ddd;
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  padding: 1.5rem;
  width: 300px;
  text-align: center;
  color: #333;
  transition: transform 0.2s, box-shadow 0.2s;
}

.pastel-card h3 {
  margin-bottom: 1rem;
  font-size: 1.5rem;
}

.pastel-card p {
  font-size: 1rem;
  margin: 0;
}

.pastel-card:hover {
  transform: scale(1.05);
  box-shadow: 0 6px 12px rgba(0, 0, 0, 0.15);
}

.card-link {
  text-decoration: none;
  color: #333;
}

.card-link:hover h3 {
  color: #ff7f50; /* Change la couleur au survol */
}
</style>
