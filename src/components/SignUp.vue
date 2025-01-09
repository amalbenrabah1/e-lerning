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
                    <img :src="require('@/assets/img/singup.png')" width="504" height="400" alt="Signup" />
                  </div>
                  <div class="col-lg-6">
                    <div class="p-5">
                      <h1 style="color: rgb(218,132,16); font-family: 'Abril Fatface', serif; text-align: center;">Join Us!</h1>
                      <form class="user" @submit.prevent="registerUser">
                        <!-- First Name -->
                        <div class="form-group">
                          <input v-model="firstName" class="form-control" type="text" placeholder="First name" @input="validateFirstname" required>
                          <p v-if="firstnameError" class="error">
                            <i class="fas fa-exclamation-circle"></i> {{ firstnameError }}
                          </p>
                        </div>

                        <!-- Last Name -->
                        <div class="form-group">
                          <input v-model="lastName" class="form-control" type="text" placeholder="Last name" @input="validateLastname" required>
                          <p v-if="lastnameError" class="error">
                            <i class="fas fa-exclamation-circle"></i> {{ lastnameError }}
                          </p>
                        </div>

                        <!-- Email -->
                        <div class="form-group">
                          <input v-model="email" class="form-control" type="email" placeholder="Enter your email address" @input="validateEmail" required>
                          <p v-if="emailError" class="error">
                            <i class="fas fa-exclamation-circle"></i> {{ emailError }}
                          </p>
                        </div>

                         <!-- Role Selection (Styled Radio Buttons) -->
                         <div class="role-selection">
                          <label class="role-label" style="color: rgb(218,132,16); font-family: 'Abril Fatface', serif; text-align: center;">Choose  if your are a :</label>
                          <div class="radio-container">
                            <label class="radio-option" :class="{ selected: role === 'student' }">
                              <input type="radio" id="roleStudent" value="student" v-model="role" required />
                              <span class="radio-custom"></span> Student
                            </label>
                            <label class="radio-option" :class="{ selected: role === 'teacher' }">
                              <input type="radio" id="roleTeacher" value="teacher" v-model="role" required />
                              <span class="radio-custom"></span> Teacher
                            </label>
                          </div>
                        </div>

                       <!-- Password -->
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
  <p v-if="passwordError" class="error">
    <i class="fas fa-exclamation-circle"></i> {{ passwordError }}
  </p>
</div>

                        <!-- Confirm Password -->
<div class="form-group" style="position: relative;">
  <input
    v-model="confirmPassword"
    :type="isConfirmPasswordVisible ? 'text' : 'password'"
    class="form-control"
    placeholder="Confirm Password"
    @input="validateConfirmPassword"
    required
  />
  <button
    type="button"
    class="btn btn-sm toggle-password"
    @click="toggleConfirmPasswordVisibility"
    :aria-label="isConfirmPasswordVisible ? 'Hide password' : 'Show password'"
    style="position: absolute; right: 10px; top: 50%; transform: translateY(-50%); background: none; border: none;"
  >
    <i :class="isConfirmPasswordVisible ? 'fas fa-eye-slash' : 'fas fa-eye'"></i>
  </button>
  <p v-if="confirmPasswordError" class="error">
    <i class="fas fa-exclamation-circle"></i> {{ confirmPasswordError }}
  </p>
</div>

                       

                        <button class="btn btn-primary d-block w-100" type="submit" :disabled="!isValidForm">Sign Up</button>
                        <hr>
                      </form>
                      <p v-if="registrationStatus">Please check your email for verification.</p>
                      <p class="text-center" style="font-size: 16px;">You already have an account? <router-link to="/login">Click here</router-link> to login</p>
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
import axios from 'axios';
import Swal from 'sweetalert2'; // Import SweetAlert2

export default {
  data() {
    return {
      firstName: '',
      lastName: '',
      email: '',
      password: '',
      confirmPassword: '',
      role: 'student',
      firstnameError: '',
      lastnameError: '',
      emailError: '',
      passwordError: '',
      confirmPasswordError: '',
      isPasswordVisible: false,
      isConfirmPasswordVisible: false,
      registrationStatus: false,
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
    isValidForm() {
      return (
        !this.firstnameError &&
        !this.lastnameError &&
        !this.emailError &&
        !this.passwordError &&
        !this.confirmPasswordError &&
        this.firstName &&
        this.lastName &&
        this.email &&
        this.password &&
        this.confirmPassword
      );
    },
  },
  methods: {
    validateFirstname() {
      if (!this.firstName) {
        this.firstnameError = "Le prénom est obligatoire.";
      } else if (this.firstName.length < 2) {
        this.firstnameError = "Le prénom doit contenir au moins 2 caractères.";
      } else {
        this.firstnameError = "";
      }
    },
    validateLastname() {
      if (!this.lastName) {
        this.lastnameError = "Le nom est obligatoire.";
      } else if (this.lastName.length < 2) {
        this.lastnameError = "Le nom doit contenir au moins 2 caractères.";
      } else {
        this.lastnameError = "";
      }
    },
    validateEmail() {
      const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
      if (!this.email) {
        this.emailError = "L'email est obligatoire.";
      } else if (!emailRegex.test(this.email)) {
        this.emailError = "Veuillez entrer un email valide.";
      } else {
        this.emailError = "";
      }
    },
    validatePassword() {
      const specialCharacterRegex = /[!@#$%^&*(),.?":{}|<>]/;
      const uppercaseRegex = /[A-Z]/;
      const lowercaseRegex = /[a-z]/;
      const digitRegex = /[0-9]/;

      if (!this.password) {
        this.passwordError = "Le mot de passe est obligatoire.";
      } else if (!uppercaseRegex.test(this.password)) {
        this.passwordError = "Le mot de passe doit contenir au moins une lettre majuscule.";
      } else if (!lowercaseRegex.test(this.password)) {
        this.passwordError = "Le mot de passe doit contenir au moins une lettre minuscule.";
      } else if (!digitRegex.test(this.password)) {
        this.passwordError = "Le mot de passe doit contenir au moins un chiffre.";
      } else if (!specialCharacterRegex.test(this.password)) {
        this.passwordError = "Le mot de passe doit contenir au moins un caractère spécial.";
      } else {
        this.passwordError = "";
      }
    },
    validateConfirmPassword() {
      if (!this.confirmPassword) {
        this.confirmPasswordError = "Veuillez confirmer le mot de passe.";
      } else if (this.confirmPassword !== this.password) {
        this.confirmPasswordError = "Les mots de passe ne correspondent pas.";
      } else {
        this.confirmPasswordError = "";
      }
    },
    async registerUser() {
      try {
        await axios.post('http://localhost:8080/api/auth/register', {
          firstName: this.firstName,
          lastName: this.lastName,
          email: this.email,
          password: this.password,
          role: this.role, 
        });
        this.registrationStatus = true; 
        

        // Display success alert with SweetAlert2
        Swal.fire({
          icon: 'success',
          title: 'Registration successful!',
          text: 'Please check your email for verification.',
          confirmButtonText: 'OK'
        });
        this.$router.push({ name: 'verification' });
      } catch (error) {
        // Display error alert with SweetAlert2
        Swal.fire({
          icon: 'error',
          title: 'Registration failed',
          text: error.response?.data || 'Unknown error',
          confirmButtonText: 'Try Again'
        });
      }
    },
    togglePasswordVisibility() {
      this.isPasswordVisible = !this.isPasswordVisible;
    },
    toggleConfirmPasswordVisibility() {
      this.isConfirmPasswordVisible = !this.isConfirmPasswordVisible;
    },
  },
  components: {
    Footer,
  },
};
</script>


<style scoped>
.toggle-password {
  background: none;
  border: none;
  color: #6c757d;
  cursor: pointer;
  outline: none;
  padding: 0;
}

.toggle-password:hover {
  color: #495057;
}
.role-selection {
  margin-bottom: 20px;
}
.error {
  color: #d9534f;
  font-size: 0.9rem;
  margin-top: 5px;
  display: flex;
  align-items: center;
}

.error i {
  margin-right: 5px;
  color: #d9534f;
}

.btn-primary {
  transition: background-color 0.3s ease;
}

.btn-primary:hover {
  background-color: #dba10f;
}

.role-selection {
  margin-top: 20px;
}

.radio-container {
  display: flex;
  gap: 30px;
}

.radio-option {
  display: flex;
  align-items: center;
  cursor: pointer;
  font-size: 1rem;
}

.radio-custom {
  width: 18px;
  height: 18px;
  border-radius: 50%;
  border: 2px solid #ccc;
  margin-right: 10px;
  transition: background-color 0.3s;
}

.radio-option.selected .radio-custom {
  background-color: #dba10f;
  border-color: #dba10f;
}

.radio-option input[type="radio"] {
  display: none;
}

.radio-option:hover .radio-custom {
  border-color: #dba10f;
}

.radio-label {
  margin-top: 10px;
}

.form-group {
  margin-bottom: 20px; /* Added margin between input fields */
}

input[type="text"],
input[type="email"],
input[type="password"] {
  padding: 10px;
  font-size: 1rem;
  border-radius: 5px;
  border: 1px solid #ccc;
  width: 100%;
}

input[type="radio"] {
  display: none;
}
</style>
