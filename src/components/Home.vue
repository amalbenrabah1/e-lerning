<template>
    <div class="home-container">
      <!-- Navbar component at the top -->
      <Navbar />
  
      <!-- Promo section -->
      <Promo />
      <Welcome/>
  
      <!-- Teachers section -->
      <Teachers />
  
      <!-- About Us section -->
      <AboutUs />
  
      <!-- FAQ section -->
      <FAQ />
  
      <!-- Contact section -->
      <Contact />
  
      <!-- Footer component at the bottom -->
      <Footer />
    </div>
  </template>
  
  <script>
  import Navbar from './Navbar.vue';
  import Promo from './Promo.vue';
  import Teachers from './Teachers.vue';
  import AboutUs from './AboutUs.vue';
  import FAQ from './FAQ.vue';
  import Contact from './Contact.vue';
  import Footer from './Footer.vue';
import Welcome from './Welcome.vue';
  
  export default {
    name: 'HomeAPP',
    components: {
      Navbar,
      Promo,
      Teachers,
      AboutUs,
      FAQ,
      Contact,
      Footer,
      Welcome
    }
  };
  </script>
  
  <style scoped>
  .home-container {
    display: flex;
    flex-direction: column;
    min-height: 100vh;
    background-color: #f7f7f7; /* Légère couleur de fond */
    font-family: 'Arial', sans-serif;
  }
  
  section {
    margin: 2rem 0; /* Ajout d'un espacement entre les sections */
    padding: 2rem;
    background-color: #fff; /* Fond blanc pour chaque section */
    border-radius: 8px; /* Coins arrondis */
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* Ombre légère */
    transition: transform 0.3s ease-in-out;
  }
  
  section:hover {
    transform: translateY(-10px); /* Effet au survol */
  }
  
  h2.section-title {
    font-size: 2rem;
    color: #333;
    text-align: center;
    margin-bottom: 1rem;
  }
  
  .contact-section iframe {
    width: 100%;
    height: 450px;
    border: none;
    border-radius: 8px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  }
  
  .contact-info p {
    font-size: 1.2rem;
    color: #555;
    text-align: center;
  }
  
  .contact-section {
    background-color: #f0f4f8; /* Fond gris clair pour la section contact */
  }
  
  footer {
    background-color: #9AC8EB;
    color: white;
    text-align: center;
    padding: 1rem;
    margin-top: 2rem;
    font-size: 1rem;
  }
  
  footer a {
    color: #fff;
    text-decoration: none;
    font-weight: bold;
  }
  
  footer a:hover {
    text-decoration: underline;
  }
  
  @media (max-width: 768px) {
    .home-container {
      padding: 1rem; /* Espacement réduit sur mobile */
    }
  
    section {
      padding: 1.5rem; /* Espacement réduit sur mobile */
    }
  
    h2.section-title {
      font-size: 1.5rem; /* Taille du titre plus petite sur mobile */
    }
  
    .contact-section iframe {
      height: 300px; /* Réduction de la taille de la carte sur mobile */
    }
  }
  </style>
  