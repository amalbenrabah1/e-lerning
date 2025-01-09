<template>
  <div class="reset-password">
    <header>
      <nav class="navbar navbar-expand-md bg-body custom-navbar fixed-top">
        <div class="container d-flex align-items-center justify-content-between">
          <a href="/" class="navbar-brand">
            <img :src="require('@/assets/img/logo.png')" width="81" height="80" alt="Logo" />
          </a>
        </div>
      </nav>
    </header>
    <main class="d-flex align-items-center" style="background: var(--bs-secondary-color); padding-bottom: 46px;">
      <div class="container" style="margin-top: 84px;">
        <div class="row justify-content-center">
          <div class="col-md-8">
            <div class="card shadow-lg o-hidden border-0 my-5">
              <div class="card-body p-5" style="background: #f2ebe2;">
                <h2 class="text-center" style="color: rgb(218,132,16); font-family: 'Abril Fatface', serif;">
                  Reset Password
                </h2>
                <p class="text-center mb-4">
                  Enter your new password below.
                </p>
                <form @submit.prevent="resetPassword">
                  <div class="form-group">
                    <input
                      v-model="password"
                      type="password"
                      class="form-control"
                      placeholder="New Password"
                      required
                    />
                  </div>
                  <button class="btn btn-primary w-100 mt-3" type="submit">Reset Password</button>
                </form>
                <div v-if="errorMessage" class="alert alert-danger mt-3">
                  {{ errorMessage }}
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </main>
    <Footer />
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      password: '',
      errorMessage: ''
    };
  },
  methods: {
    async resetPassword() {
      const token = this.$route.query.token;

      // Vérifie que le token est valide (composé de 3 parties)
      if (!token || token.split('.').length !== 3) {
        alert("Token invalide");
        return;
      }

      try {
        const response = await axios.post('http://localhost:8081/api/auth/resetpassword?token=' + token, {
          newPassword: this.password
        });

        if (response.status === 200) {
          alert('Mot de passe réinitialisé avec succès');
          this.$router.push('/login'); // Redirige vers la page de connexion après succès
        } else {
          this.errorMessage = 'Échec de la réinitialisation du mot de passe: ' + response.data.message;
        }
      } catch (error) {
        console.error('Erreur:', error);
        alert('Une erreur est survenue lors de la réinitialisation du mot de passe.');
      }
    }
  }
};
</script>

<style scoped>
/* Ajoutez votre style ici */
</style>
