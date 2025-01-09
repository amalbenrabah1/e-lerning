<template>
  <div :style="backgroundStyle">
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
            <div class="ms-auto">
              <router-link to="/login" class="btn btn-primary ms-md-2">Login</router-link>
              <router-link to="/signup" class="btn btn-primary ms-md-2">Sign Up</router-link>
            </div>
          </div>
        </div>
      </nav>
    </header>
    <main class="d-flex align-items-center" style="background: var(--bs-secondary-color); padding-bottom: 46px;">
      <div class="container" style="margin-top: 84px;">
        <div class="row justify-content-center">
          <div class="col-xxl-11">
            <div class="card shadow-lg o-hidden border-0 my-5">
              <div class="card-body p-0" style="background: #f2ebe2;">
                <div class="row">
                  <div class="col-lg-6 d-none d-lg-flex">
                    <img :src="require('@/assets/img/login.png')" width="504" height="400" alt="Login" />
                  </div>
                  <div class="col-lg-6">
                    <div class="p-5">
                      <h1 style="color: rgb(218,132,16); font-family: 'Abril Fatface', serif; text-align: center;">Welcome Back!</h1>
                      <form class="user" @submit.prevent="loginuser">
                        <div>
                          <input v-model="email" class="form-control" type="email" placeholder="Enter your email address" required />
                        </div>
                        <div style="position: relative;">
                          <div class="form-group" style="position: relative;">
                            <input
                              v-model="password"
                              :type="isPasswordVisible ? 'text' : 'password'"
                              class="form-control"
                              placeholder="Password"
                              @input="validatePassword"
                              required
                            />
                            <button
                              type="button"
                              class="btn btn-sm toggle-password"
                              @click="togglePasswordVisibility"
                              :aria-label="isPasswordVisible ? 'Hide password' : 'Show password'"
                              style="position: absolute; right: 10px; top: 50%; transform: translateY(-50%); background: none; border: none;"
                            >
                              <i :class="isPasswordVisible ? 'fas fa-eye-slash' : 'fas fa-eye'"></i>
                            </button>
                          </div>
                        </div>
                        <button class="btn btn-primary d-block w-100" type="submit">Login</button>
                        <p class="text-center mt-3">
                          <router-link to="/forgotpassword">Forgot Password?</router-link>
                        </p>
                        <hr />
                      </form>
                      <p class="text-center" style="font-size: 16px;">Don't have an account? <router-link to="/signup">Click here</router-link> to sign up</p>
                    </div>
                  </div>
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
import Footer from '@/components/Footer.vue';
import Swal from 'sweetalert2';  // Import SweetAlert2

export default {
  data() {
    return {
      email: '',
      password: '',
      message: '',
      isPasswordVisible: false,
      users: [] 
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
    loginuser() {
      fetch("http://localhost:8080/api/auth/login", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          email: this.email,
          password: this.password,
        }),
      })
      .then((response) => {
        if (response.ok) {
          return response.json();  // Récupère la réponse JSON
        } else {
          return response.json().then((data) => {
            throw new Error(data.message || "Identifiants invalides !");
          });
        }
      })
      .then((data) => {
        console.log("Réponse complète reçue :", data);
        if (data && data.role && data.email){
          localStorage.setItem("role", data.role);
          localStorage.setItem("userEmail", this.email);

          // Ajout de la redirection en fonction du rôle
          const roleRedirectMapping = {
            admin: "aDashboard",
            student: "sDashboard",
            teacher: "tDashboard",
          };

          const route = roleRedirectMapping[data.role];
          if (route) {
            this.$router.push({ name: route });
          } else {
            console.error("Rôle inconnu");
          }

          // Afficher une alerte de succès avec SweetAlert2
          Swal.fire({
            title: 'Success!',
            text: 'You have successfully logged in.',
            icon: 'success',
            confirmButtonColor: '#4caf50',
          });
        } else {
          throw new Error("Réponse invalide");
        }
      })
      .catch((error) => {
        console.error("Erreur :", error.message);
        // Afficher un message d'erreur avec SweetAlert2
        Swal.fire({
          title: 'Error!',
          text: error.message,
          icon: 'error',
          confirmButtonColor: '#f44336',
        });
      });
    },
    togglePasswordVisibility() {
      this.isPasswordVisible = !this.isPasswordVisible;
    }
  },
  components: {
    Footer,
  },
};
</script>

<style scoped>
.custom-navbar {
  padding: 5px 10px;
}
.toggle-password {
  background: none;
  border: none;
  color: #6c757d;
  cursor: pointer;
  outline: none;
}
.toggle-password:hover {
  color: #495057;
}
.custom-navbar .nav-link {
  font-family: 'Abril Fatface', serif;
  font-size: 18px;
  line-height: 1.2;
}
.custom-navbar .btn {
  font-size: 16px;
  padding: 5px 10px;
}
.btn-primary:hover {
  background-color: rgb(218, 132, 16);
}
input.form-control {
  border: 1px solid #ced4da;
  border-radius: 0.25rem;
  padding: 10px;
  margin-bottom: 16px;
}
#footerpad {
  padding: 20px 0;
  text-align: center;
}
body {
  padding-top: 100px;
}
</style>
