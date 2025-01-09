<template>
  <div class="dashboard-container">
    <!-- SideBar alignée à gauche -->
    <SideBar />

    <!-- Contenu principal -->
    <div class="main-content">
      <!-- Explication de l'application -->
      <div class="intro">
        <h1>Welcome to Your Learning Dashboard</h1>
        <p>
          This platform is designed to help you learn and grow! Here, you can 
          access courses, test your knowledge with quizzes, and manage your profile.
        </p>
      </div>

      <!-- Cartes principales -->
      <div class="cards-container">
        <div class="row">
          <!-- Cours -->
          <div class="card card-courses" @click="navigateTo('courses')">
            <h2>Courses</h2>
            <p>Explore a variety of courses available for each subject and start learning now.</p>
          </div>

          <!-- Quiz -->
          <div class="card card-Quiz" @click="navigateTo('quizs')">
            <h2>Quiz</h2>
            <p>Test your knowledge by taking quizzes and track your progress.</p>
          </div>
        </div>

        <div class="row">
          <!-- Niveaux -->
          <div class="card card-levels" @click="navigateTo('levels')">
            <h2>Levels</h2>
            <p>Check your level in different subjects by answering quizzes for each level.</p>
          </div>

          <!-- Profil -->
          <div class="card card-profile" @click="navigateTo('profile')">
            <h2>Profile</h2>
            <p>View and edit your personal information and keep your profile up to date.</p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import SideBar from './SideBar.vue';

export default {
  name: 'sDashboard',
  components: {
    SideBar,
  },
  methods: {
    navigateTo(page) {
      if (page === 'courses') {
        this.$router.push('/StudyS');
      } else if (page === 'quizs') {
        this.$router.push('/QuizS');
      } else if (page === 'levels') {
        this.$router.push('/LevelS');
      } else if (page === 'profile') {
        this.$router.push('/ProfilS');
      }
    },
  },
};
</script>

<style scoped>
.dashboard-container {
  display: flex;
  min-height: 100vh;
  background-image: url('@/assets/img/back.jpg');
  background-size: cover;
  background-position: center;
  background-repeat: no-repeat;
  color: #333;
}

.main-content {
  flex-grow: 1;
  padding: 2rem;
  margin-left: 250px;
}

.intro {
  margin-top: 20px;
  text-align: center;
  margin-bottom: 2rem;
  color: #555;
}

.intro h1 {
  font-size: 2.5rem;
  margin-bottom: 1rem;
  font-weight: bold;
}

.intro p {
  font-size: 1.2rem;
  line-height: 1.6;
}

.cards-container {
  display: flex;
  flex-direction: column;
  gap: 2rem;
}

.row {
  display: flex;
  justify-content: space-around;
  gap: 2rem;
}

.card {
  flex: 1; /* Pour que les cartes prennent la même largeur */
  max-width: 45%; /* Limite la largeur */
  border: none;
  border-radius: 15px;
  min-height: 220px;
  padding: 2rem;
  text-align: center;
  color: white;
  box-shadow: 0 6px 12px rgba(0, 0, 0, 0.1);
  transition: transform 0.3s ease, box-shadow 0.3s ease, background 0.3s ease;
  cursor: pointer;
  background: linear-gradient(135deg, #e0e0e0, #c2c2c2);
}

/* Hover: effet de zoom et ombre plus prononcée */
.card:hover {
  transform: scale(1.05);
  box-shadow: 0 12px 24px rgba(0, 0, 0, 0.2);
}

/* Carte pour Courses */
.card-courses {
  background: linear-gradient(135deg, #f7d6e0, #f3a7c3);
}

.card-courses:hover {
  background: linear-gradient(135deg, #f4c2d2, #ec95af);
}

/* Carte pour Levels */
.card-levels {
  background: linear-gradient(135deg, #d4e9f9, #a6d2f5);
}

.card-levels:hover {
  background: linear-gradient(135deg, #bedcf6, #8dc5ef);
}

/* Carte pour Profile */
.card-profile {
  background: linear-gradient(135deg, #d6f5d6, #a4e3a4);
}

.card-profile:hover {
  background: linear-gradient(135deg, #c2ebc2, #8fd68f);
}

/* Carte pour Quiz */
.card-Quiz {
  background: linear-gradient(135deg, #fbe4d5, #f7b894);
}

.card-Quiz:hover {
  background: linear-gradient(135deg, #f9d1b1, #f6a775);
}

.card h2 {
  margin-bottom: 1rem;
  font-size: 1.8rem;
  color: #030303;
}

.card p {
  font-size: 1.1rem;
  line-height: 1.5;
  color: rgba(24, 24, 24, 0.8);
}

@media (max-width: 1024px) {
  .row {
    flex-direction: column;
    align-items: center;
    gap: 1.5rem;
  }

  .card {
    max-width: 90%;
  }
}

@media (max-width: 768px) {
  .main-content {
    margin-left: 0;
    padding: 1rem;
  }

  .card {
    padding: 1.5rem;
    max-width: 100%;
  }
}
</style>
