<template>
  <div class="page-container">
    <!-- Navbar -->
    <header>
      <nav class="navbar navbar-expand-md bg-body custom-navbar fixed-top">
        <div class="navbar-brand d-flex align-items-center justify-content-between w-100">
          <div class="text-container">
            <h1 class="greeting">HI, {{ localUser.firstName }} {{ localUser.lastName }}</h1>
            <p class="welcome">Welcome Back!</p>
          </div>
          <div class="avatar-container">
            <img src="@/assets/img/p3.jpg" alt="User Avatar" class="user-avatar">
          </div>
        </div>
      </nav>
    </header>

    <!-- Sidebar -->
    <div class="sidebar">
      <div class="sidebar-wrapper">
        <div class="logo text-center mb-4">
          <a href="/">
            <img :src="require('@/assets/img/logo.png')" alt="Logo" />
          </a>
        </div>
        
        <div id="nav1">
          <ul class="nav flex-column">
            <li class="nav-item">
              <a class="nav-link text-dark" href="/DashBordS">
                <i class="fa fa-tachometer-alt"></i> Dashboard
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link text-dark" href="StudyS">
                <i class="fa fa-book-open"></i> Study
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link text-dark" href="/Quizs">
                <i class="fa fa-check-circle fs-3"></i> Quiz
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link text-dark" href="/ProfilS">
                <i class="fa fa-user"></i> Profil
              </a>
            </li>
          </ul>
        </div>

        <div id="nav2">
          <ul class="nav flex-column">
            <li class="nav-item logout-item">
              <button class="nav-link text-dark btn" @click="confirmLogout">
                <i class="fa fa-sign-out-alt"></i> Logout
              </button>
            </li>
          </ul>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
// Import SweetAlert2
import Swal from 'sweetalert2';

export default {
  name: 'SideBar',
  data() {
    return {
      localUser: {
        firstName: '',
        lastName: '',
      },
    };
  },
  methods: {
    fetchUserName() {
      const userFirstName = localStorage.getItem('userFirstName');
      const userLastName = localStorage.getItem('userLastName');
      if (userFirstName && userLastName) {
        this.localUser.firstName = userFirstName;
        this.localUser.lastName = userLastName;
      } else {
        this.errorMessage = 'No user data found in local storage';
      }
    },
    // Show SweetAlert2 confirmation dialog
    confirmLogout() {
      Swal.fire({
        title: 'Are you sure you want to log out?',
        text: 'You will be logged out of your account!',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonText: 'Yes, log out',
        cancelButtonText: 'Cancel',
        reverseButtons: true,  // To reverse confirm/cancel button positions
      }).then((result) => {
        if (result.isConfirmed) {
          this.logout(); // Perform logout if user confirmed
        }
      });
    },
    logout() {
      // Remove user data from localStorage/sessionStorage
      localStorage.removeItem('user');
      localStorage.removeItem('userName');
      sessionStorage.removeItem('user');
      
      // Show a success message with SweetAlert2
      Swal.fire({
        title: 'Logged out successfully!',
        icon: 'success',
        timer: 1500, // Auto close after 1.5 seconds
        showConfirmButton: false,
      });

      // Redirect to login page
      this.$router.push('/login');
    },
  },
  mounted() {
    this.fetchUserName();
  },
};
</script>




<style scoped>
#nav1{
  margin-top: 50px;
}
#nav2{
  margin-top: 160px;
}
.greeting {
  font-family: Georgia, serif; /* Clean, modern sans-serif font */
  font-weight: 700; /* Bold font weight */
  font-size: 2.5rem; /* Large font size */
  color: grey; /* Bright blue color for emphasis */
  margin: 0; /* Remove default margin */
}

.welcome {
  font-family: Georgia, serif; /* Matching font for consistency */
  font-weight: 400; /* Regular font weight */
  font-size: 1.25rem; /* Smaller font size than greeting */
  color: #F6B339; /* Softer grey color */
  margin-top: 0.5rem; /* Add spacing between greeting and welcome text */
}

/* Page container to hold the sidebar and content */
.page-container {
  display: flex;
  flex-direction: row;
  min-height: 100vh;
  margin-top: 60px; /* Make room for the fixed navbar */
}

/* Navbar Styling */
.custom-navbar {
  background-color: #f8f9fa;
  padding: 10px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  z-index: 10; /* Ensure navbar is on top of the sidebar */
  margin-left: 250px; /* Add margin to make space for the sidebar */
}

.avatar-container {
  display: flex;
  align-items: center;
}

.user-avatar {
  width: 70px; /* Size of the avatar */
  height: 70px; /* Size of the avatar */
  border-radius: 50%; /* Makes it round */
  object-fit: cover; /* Ensures the image covers the circle nicely */
}

.navbar-brand {
  font-size: 20px;
  height: 75px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 100%;
}

.navbar-toggler-icon {
  color: #333;
}

/* Sidebar Styling */
.sidebar {
  width: 250px;
  position: fixed;
  top: 0;
  left: 0;
  bottom: 0;
  padding: 20px;
  background-color: #fff;
  box-shadow: 2px 0 15px rgba(0, 0, 0, 0.1);
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  z-index: 5;
  transition: width 0.3s ease-in-out;
}

.sidebar-wrapper {
  display: flex;
  flex-direction: column;
}

.logo img {
  height: 70px;
  width: 150px; /* Smaller logo size */
}

.nav-item {
  margin-bottom: 20px;
}

.nav-link {
  font-size: 16px;
  display: flex;
  align-items: center;
  padding: 12px 20px;
  border-radius: 8px;
  transition: background-color 0.3s ease, transform 0.2s;
  color: #333; /* Dark text for visibility on white background */
  text-decoration: none; /* Remove underlines */
}

.nav-link:hover {
  background-color: #f1f1f1;
  transform: scale(1.05);
}

.nav-link i {
  margin-right: 15px;
  font-size: 20px;
  transition: color 0.2s ease, transform 0.2s ease; /* Ensure smooth transition on icon hover */
}

/* Custom Color for Active Link */
.nav-item.active .nav-link {
  background-color: #fcbf49;
  color: #fff;
}

.nav-item.active .nav-link i {
  color: #fff;
}

/* Logout Item Styling */
.logout-item {
  margin-top: auto; /* Pushes the logout link to the bottom */
}

/* Navbar Item Hover Effect for Icons */
.nav-item a:hover i {
  color: #fcbf49;  /* Change color of the icon on hover */
  transform: scale(1.1);  /* Slight zoom effect */
  transition: transform 0.2s ease, color 0.2s ease;  /* Smooth transition */
}

/* Main content area */
.content {
  margin-left: 250px; /* Space for the fixed sidebar */
  flex-grow: 1;
  padding: 20px;
}

/* Responsive Design */
@media (max-width: 768px) {
  .sidebar {
    width: 200px;
  }

  .content {
    margin-left: 200px;
    width: calc(100% - 200px);
  }

  .logo img {
    width: 100px; /* Even smaller logo for smaller screens */
  }
}
</style>
