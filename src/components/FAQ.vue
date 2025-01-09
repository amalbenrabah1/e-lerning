<template>
  <section id="faq" class="faq-section">
    <h1 class="section-title">FAQ</h1>
    <div class="accordion" id="faqAccordion">
      <!-- Render each FAQ -->
      <div
        v-for="(faq, index) in faqs"
        :key="index"
        class="accordion-item"
      >
        <h2 class="accordion-header">
          <button
            class="accordion-button"
            type="button"
            @click="toggle(index)"
            :class="{'active': faq.isOpen}"
          >
            {{ faq.question }}
          </button>
        </h2>
        <div
          class="accordion-collapse"
          v-show="faq.isOpen"
        >
          <div class="accordion-body">
            {{ faq.answer }}
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
export default {
  name: "FAQ",
  data() {
    return {
      faqs: [
        {
          question: "What is the purpose of this e-learning platform?",
          answer: "Our platform aims to make learning accessible and engaging for everyone. We provide a variety of interactive courses that help you develop new skills at your own pace.",
          isOpen: false,
        },
        {
          question: "How do I sign up for a course?",
          answer: "To sign up for a course, simply create an account on our website. Browse through our course catalog, select the course you’re interested in.",
          isOpen: false,
        },
        {
          question: "Are the courses self-paced?",
          answer: "Yes, all our courses are self-paced, allowing you to learn whenever and wherever it suits you. You can take your time to complete lessons and revisit them as needed.",
          isOpen: false,
        },
      ]
    };
  },
  methods: {
    toggle(index) {
      this.faqs[index].isOpen = !this.faqs[index].isOpen;
    }
  }
};
</script>

<style scoped>
/* Main container styling */
.faq-section {
  padding: 3rem 2rem;
  background-color: white;
  text-align: center;
  margin-left:50px ;
  margin-right:50px ;
}

.section-title {
  font-size: 36px;
  font-weight: 600;
  color: #450920;
  margin-bottom: 20px;
  text-transform: uppercase;
  letter-spacing: 2px;
}

.accordion-button {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 15px;
  width: 100%;
  background-color: #fff;
  border: 1px solid #ddd;
  font-size: 18px;
  font-weight: bold;
  text-align: left;
  color: #333;
  border-radius: 8px;
  cursor: pointer;
  transition: all 0.3s ease;
}

.accordion-button.active {
  background-color: #9ac8eb;
  color: #fff;
  border-color: #007bff;
}

.accordion-button:hover {
  background-color: #f0f0f0;
}

.accordion-collapse {
  padding: 10px 15px;
  border-top: 1px solid #ddd;
}

.accordion-body {
  font-size: 16px;
  line-height: 1.6;
  color: #555;
  background-color: #fff;
  border-radius: 8px;
}

.accordion-header {
  margin-bottom: 10px;
}

/* Responsive design adjustments */
@media (max-width: 768px) {
  .section-title {
    font-size: 28px;
  }

  .accordion-button {
    font-size: 16px;
    padding: 12px;
  }

  .accordion-body {
    font-size: 14px;
  }
}
</style>
