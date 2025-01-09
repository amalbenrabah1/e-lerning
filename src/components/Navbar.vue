<template>
    <header>
      <nav class="navbar navbar-expand-md bg-body custom-navbar fixed-top">
        <div class="container d-flex align-items-center justify-content-between">
          <a href="/" class="navbar-brand">
            <img :src="require('@/assets/img/logo.png')" width="81" height="80" alt="Logo" />
          </a>
          <button data-bs-toggle="collapse" class="navbar-toggler" data-bs-target="#navcol-2">
            <span class="visually-hidden">Toggle navigation</span>
            <span class="navbar-toggler-icon"></span>
          </button>
          <div class="collapse navbar-collapse" id="navcol-2">
            <ul class="navbar-nav mx-auto">
              <li class="nav-item" v-for="(item, index) in navItems" :key="index">
                <a class="nav-link" :href="`#${item.id}`">
                  <strong><span>{{ item.text }}</span></strong>
                </a>
              </li>
            </ul>
            <div class="ms-auto">
              <router-link to="/login" class="btn btn-primary ms-md-2">Login</router-link>
              <router-link to="/signup" class="btn btn-primary ms-md-2">Sign Up</router-link>
            </div>
          </div>
        </div>
      </nav>
    </header>
  </template>
  
  <script>
  export default {
    name: 'MainNavbar',
    data() {
      return {
        navItems: [
          { text: 'Home', id: 'container'},
          { text: 'Our teachers', id: 'teacher' },
          { text: 'About Us', id: 'aboutus' },
          { text: 'FAQ', id: 'faq' },
          { text: 'Contact', id: 'contact' },
        ]
      }
    }
  }
  </script>
  
  <style scoped>
  .custom-navbar {
    padding: 5px 10px; /* Réduire la marge intérieure de la navbar */
  }
  
  .custom-navbar .nav-link {
    font-family: 'Abril Fatface', serif; /* Garde la police de ton choix */
    font-size: 18px; /* Ajuste la taille de la police */
    line-height: 1.2; /* Ajuste la hauteur de ligne */
  }
  
  .custom-navbar .btn {
    font-size: 16px; /* Taille du texte du bouton */
    padding: 5px 10px; /* Ajuste le padding du bouton */
  }
  
  .btn-primary {
    transition: background-color 0.3s ease;
  }
  
  .btn-primary:hover {
    background-color: rgb(218, 132, 16); /* Change la couleur au survol */
  }
  
  /* Optionnel: Si tu veux ajouter un peu d'ombre pour un effet de "flottant" */
  .custom-navbar {
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  }
  
  /* Ajoute un peu de marge en haut pour éviter que la navbar ne chevauche le contenu */
  body {
    padding-top: 70px; /* Ajuste selon la hauteur de ta navbar */
  }
  </style>
  