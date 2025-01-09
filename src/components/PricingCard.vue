<template>
  <div class="pricing-card" @click="redirectToCoursePage">
    <div class="pricing-card-header">
      {{ title }}
      <div class="hours">{{ hours }} HOURS</div>
    </div>
    <div class="image-container">
      <img :src="image" alt="Course Image" />
    </div>
    <div class="pricing-card-footer">
      START COURSE
    </div>
  </div>
</template>

<script>
export default {
  name: "PricingCard",
  props: {
    title: String,
    hours: Number,
    image: String,
  },
  methods: {
    redirectToCoursePage() {
      this.$router.push({ name: 'coursList', query: { subject: this.title } });
    }
  }
};
</script>

<style scoped>
.pricing-card {
  width: 200px; /* Largeur minimale */
  max-width: 250px; /* Largeur maximale */
  background-color: #fff;
  border-radius: 8px;
  overflow: hidden;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
  text-align: center;
  transition: transform 0.3s ease, box-shadow 0.3s ease; /* Transition plus fluide */
  margin-bottom: 1.5rem; /* Espacement entre les cartes */
  flex-shrink: 0; /* Empêche le rétrécissement */
}

.pricing-card:hover {
  transform: translateY(-10px); /* Légère élévation lors du hover */
  box-shadow: 0 12px 24px rgba(0, 0, 0, 0.25); /* Ombre plus marquée */
}

.pricing-card-header {
  background-color: #8b1a33; /* Couleur de l'en-tête */
  color: #fff;
  padding: 1rem;
  font-size: 1.1rem;
  font-weight: bold;
  text-transform: uppercase;
}

.hours {
  font-size: 0.9rem; /* Taille de police plus petite */
  font-weight: 700;
  margin-top: 5px;
}

.image-container {
  overflow: hidden;
}

.pricing-card img {
  width: 100%;
  height: 150px; /* Hauteur ajustée */
  object-fit: cover;
  transition: transform 0.3s ease; /* Transition pour l'effet de zoom */
}

.pricing-card:hover img {
  transform: scale(1.08); /* Zoom plus fluide sur l'image */
}

.pricing-card-footer {
  padding: 0.8rem;
  background-color: #333;
  color: #fff;
  font-size: 0.9rem; /* Taille de police plus petite */
  font-weight: bold;
  text-transform: uppercase;
  cursor: pointer;
  transition: background-color 0.3s ease, transform 0.3s ease; /* Transition pour l'effet du fond */
}

.pricing-card-footer:hover {
  background-color: #8b1a33; /* Changer la couleur du fond du footer */
  transform: translateY(4px); /* Légère élévation lors du hover sur le footer */
}
</style>
