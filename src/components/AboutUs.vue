<template>
  <section id="aboutus" class="about-us">
    <div class="row">
      <div class="col-md-6 about-content">
        <h1 class="section-title">About Us</h1>
        <p class="section-description">
          At our e-learning platform, we blend education with fun through gamification. Our mission is to make learning engaging and enjoyable, allowing you to earn rewards as you progress. With interactive courses and a supportive community,
          we empower you to achieve your goals while having a great time along the way!
        </p>
      </div>
      <div class="col-md-6">
        <img src="@/assets/img/images.png" alt="About Us" class="about-image" />
      </div>
    </div>
  </section>
</template>

<script>
export default {
  name: 'AboutUs'
};
</script>

<style scoped>
.about-us {
  padding: 4rem 2rem;
  text-align: center;
  display: flex;
  justify-content: space-between;
  align-items: center;
  color: white;
  margin-right: 50px;
  margin-left: 50px;
}

.row {
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.about-content {
  padding-right: 20px;
}

.about-image {
  width: 100%;
  height: auto;
  max-width: 600px; 
  margin-left: 100px;/* Adjust the width of the image */
}

.section-title {
  font-size: 40px;
  font-weight: bold;
  margin-bottom: 40px;
  text-transform: uppercase;
  letter-spacing: 3px;
  color: #450920;

}

.section-description {
  font-size: 18px;
  line-height: 1.6;
  max-width: 800px;
  margin-left: auto;
  margin-right: auto;
  font-family: 'Arial', sans-serif;
  color: gray;
}

@media (max-width: 768px) {
  .about-us {
    flex-direction: column;
    text-align: center;
  }

  .section-title {
    font-size: 28px;
  }

  .section-description {
    font-size: 16px;
  }

  .about-image {
    width: 80%;
    margin-top: 20px;
  }
}
</style>
