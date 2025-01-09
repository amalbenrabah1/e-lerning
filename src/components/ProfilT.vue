<template>
  <div class="page-container">
    <SideBarT :user-avatar="userAvatar" @update-avatar="updateAvatar" />

    <div class="content">
      <div class="profile-container">
        <h2>Profile Information</h2>
        <form @submit.prevent="updateProfile">
          <img :src="localUser.avatar" alt="Avatar" v-if="localUser.avatar" class="avatar-preview mt-3" />
          <div v-if="localUser" class="form-group">

            
            <label for="firstName">First Name:</label>
            <input id="firstName" v-model="localUser.firstName" type="text" class="form-control" />
          </div>
          <div v-if="localUser" class="form-group">
            <label for="lastName">Last Name:</label>
            <input id="lastName" v-model="localUser.lastName" type="text" class="form-control" />
          </div>
          <div v-if="localUser" class="form-group">
            <label for="email">Email:</label>
            <input id="email" v-model="localUser.email" type="email" class="form-control" />
          </div>
          <!-- Avatar upload -->
          <div class="form-group">
            <label for="avatar">Avatar:</label>
            <input type="file" id="avatar" @change="handleAvatarChange" class="form-control" />
            </div>
          <button type="submit" class="btn btn-primary">Save Changes</button>
        </form>
        <div v-if="errorMessage || successMessage" class="custom-alert" :class="{ success: successMessage, error: errorMessage }">
          <p>{{ successMessage || errorMessage }}</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import SideBarT from '@/components/SideBarT.vue';
import Swal from 'sweetalert2';

export default {
  data() {
    return {
      localUser: {
        firstName: '',
        lastName: '',
        email: '',
        password: '',
      },
      userAvatar: localStorage.getItem('userAvatar') || null,
      errorMessage: '',
    };
  },
  created() {
    this.loadUserData();
  },
  methods: {
    loadUserData() {
      // Get user data from localStorage
      const userEmail = localStorage.getItem('userEmail');
      const userFirstName = localStorage.getItem('userFirstName');
      const userLastName = localStorage.getItem('userLastName');
      const userAvatar = localStorage.getItem('userAvatar');

      // If user data exists, load it into localUser and userAvatar
      if (userEmail && userFirstName && userLastName) {
        this.localUser.email = userEmail;
        this.localUser.firstName = userFirstName;
        this.localUser.lastName = userLastName;
        if (userAvatar) {
          this.userAvatar = userAvatar;
        }
      } else {
        this.errorMessage = 'No user data found in local storage';
      }
    },
    handleAvatarChange(event) {
  const file = event.target.files[0];
  if (file) {
    const reader = new FileReader();
    reader.onload = () => {
      this.localUser.avatar = reader.result;  // Mise à jour de l'avatar dans localUser
      localStorage.setItem('userAvatar', this.localUser.avatar);  // Sauvegarde dans localStorage
    };
    reader.readAsDataURL(file);
  }
},
    async updateProfile() {
      // Send updated data to backend or save to localStorage
      const updatedData = {
        firstName: this.localUser.firstName,
        lastName: this.localUser.lastName,
        email: this.localUser.email,
        password: this.localUser.password,
        avatar: this.localUser.avatar,
      };

      console.log('Updated Profile Data:', updatedData);

      localStorage.setItem('userFirstName', this.localUser.firstName);
      localStorage.setItem('userLastName', this.localUser.lastName);
      localStorage.setItem('userEmail', this.localUser.email);
      localStorage.setItem('userAvatar', this.localUser.avatar);

      // Show success alert
      Swal.fire({
        title: 'Profile Updated!',
        text: 'Your profile has been updated successfully.',
        icon: 'success',
        confirmButtonText: 'OK',
        confirmButtonColor: '#fcbf49',
      });
    },
  },
  components: {
    SideBarT,
  },
};
</script>

<style scoped>
/* Add your existing styles here */

.avatar-preview {
  width: 100px;
  height: 100px;
  object-fit: cover;
  border-radius: 50%;
  margin-top: 10px;
}


.page-container {
  display: flex;
  flex-direction: row;
  min-height: 100vh;
  margin-top: 100px;
  background-image: url('@/assets/img/back.jpg');
  background-position: center;
  background-repeat: repeat;
}

.content {
  margin-left: 250px;
  padding: 20px;
  flex-grow: 1;
}

.profile-container {
  max-width: 600px;
  margin: 0 auto;
  padding: 20px;
  background-color: #f8f9fa;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}
.profile-container {
  max-width: 600px;
  margin: 0 auto;
  padding: 20px;
  background-color: #f8f9fa;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.user-avatar-preview {
  width: 100px;
  height: 100px;
  border-radius: 50%;
  object-fit: cover;
  margin-top: 10px;
}

.form-group {
  margin-bottom: 15px;
}

.form-control {
  width: 100%;
  padding: 8px;
  margin-top: 5px;
  border: 1px solid #ccc;
  border-radius: 4px;
}

button {
  background-color: #fcbf49;
  color: white;
  font-size: 16px;
  padding: 10px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

button:hover {
  background-color: #f6a52d;
}

.error-message {
  color: red;
  margin-top: 20px;
}
.custom-alert {
  position: fixed;
  top: 20px;
  right: 20px;
  padding: 15px 20px;
  border-radius: 8px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  font-size: 16px;
  color: white;
  animation: fadeInOut 4s ease-in-out;
}

.custom-alert.success {
  background-color: #4caf50; /* Vert pour succès */
}

.custom-alert.error {
  background-color: #f44336; /* Rouge pour erreur */
}

@keyframes fadeInOut {
  0% {
    opacity: 0;
    transform: translateY(-10px);
  }
  10%, 90% {
    opacity: 1;
    transform: translateY(0);
  }
  100% {
    opacity: 0;
    transform: translateY(-10px);
  }
}

button {
  background-color: #fcbf49;
  color: white;
  font-size: 16px;
  padding: 10px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

button:hover {
  background-color: #f6a52d;
}

.form-group {
  margin-bottom: 15px;
}

.form-control {
  width: 100%;
  padding: 8px;
  margin-top: 5px;
  border: 1px solid #ccc;
  border-radius: 4px;
}

.error-message {
  color: red;
  margin-top: 20px;
}
</style>
