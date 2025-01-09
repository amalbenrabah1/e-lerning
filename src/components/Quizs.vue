<template>
  <div class="dashboard-container">
    <!-- SideBar aligné à gauche -->
    <SideBar />

    <!-- Contenu principal contenant les cartes de cours -->
    <div class="main-content container mt-5 text-center">
      <!-- Message d'introduction -->
      <p class="intro-message">
        Here are the quizzes currently available. Start your learning journey now!
      </p>
      <div class="row">
        <PricingCardquiz
          v-for="(course, index) in courses"
          :key="index"
          :title="course.title"
          :image="course.image"
        />
      </div>
    </div>
  </div>
</template>

<script>
import SideBar from './SideBar.vue';
import PricingCardquiz from './PricingCardquiz.vue';

export default {
  name: 'QuizS',
  components: {
    SideBar,
    PricingCardquiz,
  },
  data() {
    return {
      courses: [
        { title: 'Frensh', hours: 20, image: require('@/assets/img/fr.jpg') },
        { title: 'Arabe', hours: 25, image: require('@/assets/img/rb.jpg') },
        { title: 'Espangol', hours: 36, image: require('@/assets/img/spanish.jpg') },
        { title: 'English', hours: 20, image: require('@/assets/img/anglais.jpg') },
        { title: 'Itelien', hours: 20, image: require('@/assets/img/ital.jpg') },
        { title: 'Japonais', hours: 20, image: require('@/assets/img/japa.jpg') },
        { title: 'Turc', hours: 20, image: require('@/assets/img/turc.jpg') },
        { title: 'Allemand', hours: 20, image: require('@/assets/img/germand.jpg') },
      ],
    };
  },
};
</script>

<style scoped>
.dashboard-container {
  background-image: url('@/assets/img/ch.jpg');
  background-position: center;
  background-repeat: repeat;
  color: white;
}

.dashboard-container {
  display: flex;
  min-height: 100vh;
  background-color: #f7f9fc;
}

.sidebar {
  flex-shrink: 0;
  width: 250px;
}

.main-content {
  flex-grow: 1;
  padding: 1rem;
  margin-top: 200px;
  margin-left: 200px;
  text-align: center;
}

.intro-message {
  font-size: 1.2rem;
  margin-bottom: 2rem;
  font-weight: bold;
  color: #333;
}

.row {
  display: flex;
  flex-wrap: wrap;
  gap: 2rem;
  justify-content: center;
}
</style>
