<template>
  <div class="level-container">
    <SideBar />
    <div class="level">
      <h2>{{ level.name }}</h2>
      <p>{{ level.description }}</p>
      <div class="questions-container">
        <button
          :disabled="!isLevelUnlocked(level.key)"
          @click="goToQuizList(selectedSubject, level.key)"
        >
          Voir les quiz
        </button>
      </div>
      <div v-if="!isLevelUnlocked(level.key)" class="alert failure">
        <span class="alert-icon">❌</span>
        Vous devez réussir ce niveau pour finir l'application.
      </div>
    </div>
  </div>
</template>

<script>
import SideBar from "./SideBar.vue";

export default {
  name: "LevelSSS",
  components: {
    SideBar,
  },
  data() {
    return {
      selectedSubject: this.$route.query.subject, // Sélectionner le sujet depuis le routeur
      levels: [
        { name: "Advanced Level", key: "advanced", description: "Répondez aux questions de niveau avancé." },
      ],
      unlockedLevels: ['beginner', 'intermediate', 'advanced'],  // Niveau avancé débloqué
    };
  },
  methods: {
    goToQuizList(subject, level) {
      this.$router.push({
        name: "QuizList",
        query: { subject, level },
      });
    },
    isLevelUnlocked(levelKey) {
      return this.unlockedLevels.includes(levelKey);
    },
    completeLevel(levelKey) {
      if (levelKey === "advanced") {
        // Processus de réussite finale
        this.$router.push({ name: "FinishPage" });  // Rediriger vers une page de fin ou félicitations
      }
    },
  },
};
</script>


<style scoped>
.level-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 50px;
  margin-left: 200px;
  background-image: url('@/assets/img/back.jpg');
  background-position: center;
  background-repeat: no-repeat;
  background-size: cover;
  min-height: 100vh;
  color: #333;
}

.level {
  width: 100%;
  max-width: 300px;
  margin: 20px;
  padding: 20px;
  border: 1px solid #ddd;
  border-radius: 12px;
  background-color: rgba(255, 255, 255, 0.8);
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  transition: transform 0.3s ease, box-shadow 0.3s ease;
  text-align: center;
}

.level:hover {
  transform: translateY(-8px);
  box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
}

.level h2 {
  font-size: 1.5rem;
  margin-bottom: 10px;
  color: #f77f00;
}

.level p {
  font-size: 1rem;
  color: #555;
  margin-bottom: 20px;
}

.questions-container {
  display: flex;
  justify-content: center;
}

button {
  padding: 12px 24px;
  background-color: #4caf50;
  border: none;
  color: white;
  border-radius: 25px;
  font-size: 1rem;
  font-weight: bold;
  cursor: pointer;
  transition: background-color 0.3s ease, transform 0.3s ease;
}

button:hover {
  background-color: #43a047;
  transform: scale(1.05);
}

button:disabled {
  background-color: #ccc;
  cursor: not-allowed;
  transform: none;
}

.alert {
  padding: 15px;
  margin-top: 20px;
  border-radius: 8px;
  font-size: 16px;
  display: flex;
  align-items: center;
  transition: transform 0.3s ease;
}

.alert-icon {
  margin-right: 10px;
}

.success {
  background-color: #28a745;
  color: white;
}

.failure {
  background-color: #dc3545;
  color: white;
}

@media (min-width: 768px) {
  .level-container {
    flex-direction: row;
    flex-wrap: wrap;
    justify-content: center;
  }

  .level {
    margin: 20px;
    flex-basis: calc(33.33% - 40px);
  }
}
</style>
