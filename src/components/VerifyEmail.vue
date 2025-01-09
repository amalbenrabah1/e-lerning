<template>
  <div>
    <h2>Email Verification</h2>
    <p>Click the link in your email to verify your account.</p>
    <button @click="redirectToGmail">Go to Gmail Login</button>
  </div>
</template>

<script>
export default {
  data() {
    return {
      verificationStatus: '',
    };
  },
  methods: {
    redirectToGmail() {
      // Rediriger vers la page de connexion Gmail
      window.location.href = "https://mail.google.com/mail/u/0/#inbox";
    },
    async checkVerificationStatus() {
      const token = this.$route.query.token;
      try {
        // Effectuer la vérification du token
        const response = await this.$axios.get(`http://localhost:8080/api/auth/verify?token=${token}`);
        this.verificationStatus = response.data.message;
        
        // Si la vérification est réussie, vous pouvez également effectuer la redirection
        if (this.verificationStatus === 'Email vérifié avec succès.') {
          this.redirectToGmail();  // Rediriger vers Gmail après succès
        }
      } catch (error) {
        this.verificationStatus = 'Invalid token or verification failed.';
      }
    },
  },
  mounted() {
    this.checkVerificationStatus();
  },
};
</script>
