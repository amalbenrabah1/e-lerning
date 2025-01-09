<template>
  <section id="teacher" class="teachers-section">
    <h2 class="section-title">Our Teachers</h2>
    <p class="section-description">
      Our teachers are experienced professionals dedicated to helping you learn and achieve your goals with expert guidance and support.
    </p>
    <div class="teachers-container">
      <TeacherCard v-for="teacher in teachers" :key="teacher.name" :teacher="teacher" />
    </div>
  </section>
</template>

<script>
import TeacherCard from './TeacherCard.vue';

export default {
  name: 'TeachersLIST',
  components: { TeacherCard },
  data() {
    return {
      teachers: [
        { name: 'Mrs. Mansouri', subject: 'Physique', img: require('@/assets/img/p3.jpg') },
        { name: 'Mr. Lasoued', subject: 'Mathématique', img: require('@/assets/img/p1.jpg') },
        { name: 'Mr. Sedik', subject: 'Arabe', img: require('@/assets/img/p2.jpg') },
        { name: 'Mrs. Nasri', subject: 'Anglais', img: require('@/assets/img/p4.jpg') },
      ],
    };
  },
};
</script>

<style scoped>
/* Overall Section Styling */
.teachers-section {
  padding: 50px 20px;
  background-color: white; /* Light gray background for the section */
  text-align: center;
  border-radius: 5px;
  margin-left: 50px;
  margin-right: 50px;
}

/* Title Styling */
.section-title {
  font-size: 36px;
  font-weight: bold;
  color: #450920; /* Darker text for contrast */
  margin-bottom: 20px;
  text-transform: uppercase;
  letter-spacing: 2px;
}

/* Description Styling */
.section-description {
  font-size: 18px;
  color: #7f8c8d;
  margin-bottom: 40px;
  line-height: 1.6;
  font-family: 'Arial', sans-serif;
  max-width: 800px;
  margin-left: auto;
  margin-right: auto;
}

/* Teachers Container Styling */
.teachers-container {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-around;
  gap: 20px;
  margin-top: 30px;
}

/* Teacher Card Styling */
.teacher-card {
  background-color: #ffffff;
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  overflow: hidden;
  width: 250px;
  text-align: center;
  transition: transform 0.3s ease, box-shadow 0.3s ease;
}

.teacher-card img {
  width: 100%;
  height: 200px;
  object-fit: cover;
}

.teacher-card-content {
  padding: 20px;
}

.teacher-card h3 {
  font-size: 20px;
  font-weight: 600;
  color: #2c3e50;
}

.teacher-card p {
  font-size: 16px;
  color: #7f8c8d;
  margin-top: 5px;
}

.teacher-card:hover {
  transform: translateY(-5px); /* Lift effect on hover */
  box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
}

/* Media Query for Smaller Devices */
@media (max-width: 768px) {
  .teachers-container {
    justify-content: center;
  }

  .teacher-card {
    width: 200px;
  }

  .section-title {
    font-size: 28px;
  }

  .section-description {
    font-size: 16px;
  }
}
</style>
