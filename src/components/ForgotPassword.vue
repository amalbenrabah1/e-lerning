<template>
  <div class="forgot-password" :style="backgroundStyle">
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
                  Forgot Password
                </h2>
                <p class="text-center mb-4">
                  Enter your email address to receive a password reset link.
                </p>
                <form @submit.prevent="sendResetEmail">
                  <div class="form-group">
                    <input
                      v-model="email"
                      type="email"
                      class="form-control"
                      placeholder="Enter your email"
                      required
                    />
                  </div>
                  <button class="btn btn-primary w-100 mt-3" type="submit">Send Reset Link</button>
                </form>
                <p class="text-center mt-3">
                  Remembered your password? <router-link to="/login">Login here</router-link>.
                </p>
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
import Swal from 'sweetalert2';
import Footer from '@/components/Footer.vue';

export default {
  data() {
    return {
      email: '',
    };
  },
  computed: {
    backgroundStyle() {
      return {
        background: `url(${require('@/assets/img/bac.png')}) no-repeat`,
        backgroundSize: 'cover',
        backgroundPosition: 'center',
        width: '100vw',
        height: '100vh',
      };
    },
  },
  methods: {
    sendResetEmail() {
      fetch('http://localhost:8080/api/auth/forgotpassword', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ email: this.email }),
      })
        .then((response) => {
          if (response.ok) {
            Swal.fire({
              icon: 'success',
              title: 'Reset link sent!',
              text: 'Check your email for the reset link.',
            });
          } else {
            throw new Error('Failed to send reset email.');
          }
        })
        .catch((error) => {
          console.error('Error:', error.message);
          Swal.fire({
            icon: 'error',
            title: 'Error',
            text: error.message || 'Something went wrong.',
          });
        });
    },
  },
  components: {
    Footer,
  },
};
</script>

<style scoped>
/* Add your style here */
</style>
