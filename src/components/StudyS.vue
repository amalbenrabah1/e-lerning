<template>
    <div class="dashboard-container">
      <!-- SideBar aligné à gauche -->
      <SideBar />
  
      <!-- Contenu principal contenant les cartes de cours -->
      <div class="main-content container mt-5 text-center">
        <!-- Message d'introduction -->
        <p class="intro-message">
          Here are the courses currently available. Start your learning journey now!
        </p>
        <div class="row">
          <PricingCard
            v-for="(course, index) in courses"
            :key="index"
            :title="course.title"
            :hours="course.hours"
            :image="course.image"
          />
        </div>
      </div>
    </div>
  </template>
  
  <script>
  import SideBar from './SideBar.vue'; // Change to SideBar.vue
  import PricingCard from './PricingCard.vue';
  
  export default {
    name: 'studyS',
    components: {
      SideBar,
      PricingCard,
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
    background-image: url('@/assets/img/ch.jpg'); /* Path to your image */
    background-position: center; /* Centers the image within the container */
    background-repeat: repeat; /* Prevents the image from repeating */
    color: white; /* Set text color for contrast with background */
  }
  
  .dashboard-container {
    display: flex;
    min-height: 100vh;
    background-color: #f7f9fc;
  }
  
  .sidebar {
    flex-shrink: 0;
    width: 250px; /* Largeur du SideBar */
  }
  
  .main-content {
    flex-grow: 1;
    padding: 1rem;
    margin-top: 200px;
    margin-left: 200px;
    align-self: flex-start; /* Aligne le contenu au début du conteneur */
    text-align: center;
  }
  
  .intro-message {
    font-size: 1.2rem;
    color: #333;
    margin-bottom: 2rem;
    font-weight: bold;
  }
  
  .row {
    display: flex;
    flex-wrap: wrap;
    gap: 2rem;
    justify-content: center;
    align-items: flex-start;
  }
  
  .pricing-card {
    background-color: #fff;
    border-radius: 12px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    padding: 1.5rem;
    width: 350px; /* Augmentation de la largeur des cartes */
    height: 390px; /* Augmentation de la hauteur des cartes */
    text-align: center;
    color: #333;
    display: flex;
    flex-direction: column;
    justify-content: space-between;
  }
  
  .pricing-card img {
    max-width: 100%;
    height: 200px; /* Ajustement pour les images */
    border-radius: 8px;
    object-fit: cover;
  }
  
  .pricing-card h3 {
    font-size: 1.5rem;
    margin-top: 1rem;
  }
  
  .pricing-card p {
    font-size: 1rem;
    color: #e67e22;
  }
  
  .pricing-card .start-course-btn {
    margin-top: 1rem;
    padding: 0.75rem 1.5rem;
    background-color: #007bff; /* Couleur par défaut */
    color: #fff;
    border: none;
    border-radius: 8px;
    cursor: pointer;
    font-size: 1rem;
    transition: background-color 0.3s ease;
  }
  
  .pricing-card .start-course-btn:hover {
    background-color: #e67e22; /* Couleur lorsqu'on passe la souris */
  }
  </style>
  