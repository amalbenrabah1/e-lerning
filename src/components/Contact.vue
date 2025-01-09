<template>
  <section id="contact" class="contact-section" style="background-color: white;">
    <h2 class="section-title">Contact Information</h2>
    <div class="contact-info">
      <p><strong>Email:</strong> <a href="mailto:contact@agricola.com">contact@iset.com</a></p>
      <p><strong>Phone:</strong> <a href="tel:+21626212112">(216) 26 212 112</a></p>
    </div>
    <div class="map-container">
      <iframe 
        src="https://www.google.com/maps/embed?pb=!1m14!1m8!1m3!1d12785.711775826701!2d10.2699248!3d36.7603!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x12fd49fa15643927%3A0xad64c8c462b52435!2sHigher%20Institute%20of%20Technological%20Studies%20of%20Rades!5e0!3m2!1sen!2stn!4v1730914812958!5m2!1sen!2stn" 
        width="100%" 
        height="100%" 
        style="border:0;" 
        allowfullscreen 
        loading="lazy" 
        referrerpolicy="no-referrer-when-downgrade"
      ></iframe>
    </div>
  </section>
</template>

<script>
export default {
  name: 'ContactAPP'
};
</script>

<style scoped>
.contact-section {
  padding: 3rem;
  background-color: #e0f7fa; /* Lighter blue background */
  border-radius: 12px;
  text-align: center;
  max-width: 900px;
  margin: 3rem auto;
  box-shadow: 0 6px 12px rgba(0, 0, 0, 0.1);
}

.section-title {
  font-size: 2.5rem;
  font-weight: 600;
  margin-bottom: 1.5rem;
  color: #450920; /* Darker purple color */
  text-transform: uppercase;
}

.contact-info {
  font-size: 1.2rem;
  color: #34495e;
  margin-bottom: 2rem;
  line-height: 1.8;
}

.contact-info a {
  color: #1e88e5; /* Link color */
  text-decoration: none;
}

.contact-info a:hover {
  text-decoration: underline;
}

.map-container {
  position: relative;
  padding-top: 56.25%; /* 16:9 Aspect Ratio */
  overflow: hidden;
  border-radius: 12px;
  box-shadow: 0 6px 12px rgba(0, 0, 0, 0.1);
}

.map-container iframe {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
}

@media (max-width: 768px) {
  .section-title {
    font-size: 2rem;
  }

  .contact-info {
    font-size: 1rem;
  }
}
</style>
