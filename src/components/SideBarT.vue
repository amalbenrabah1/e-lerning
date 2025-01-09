<template>
  <div class="page-container">
    <!-- Navbar -->
    <header>
      <nav class="navbar navbar-expand-md bg-body custom-navbar fixed-top">
        <div class="navbar-brand d-flex align-items-center justify-content-between w-100">
          <div class="text-container">
            <h1 class="greeting">HI, MR(S)  {{ localUser.firstName }} {{ localUser.lastName }} </h1>
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
        <!-- Logo Section -->
        <div class="logo text-center mb-4">
          <a href="/">
            <img :src="require('@/assets/img/logo.png')" alt="Logo" />
          </a>
        </div>

        <div id="nav1">
          <!-- Navigation Menu -->
          <ul class="nav flex-column">
            <!-- Dashboard Link -->
            <li class="nav-item">
              <a class="nav-link text-dark" href="/DashBordteacher">
                <i class="fa fa-tachometer-alt"></i>
                Dashboard
              </a>
            </li>

            <!-- Study Link -->
            <li class="nav-item">
              <a class="nav-link text-dark" href="/CoursesT">
                <i class="fa fa-book-open"></i>
                Courses
              </a>
            </li>

            <li class="nav-item">
              <a class="nav-link text-dark" href="/QuizT">
                <i class="fa fa-check-circle fs-3"></i>
                Quiz
              </a>
            </li>

            <!-- Profile Link -->
            <li class="nav-item">
              <a class="nav-link text-dark" href="ProfilT">
                <i class="fa fa-user"></i>
                Profile
              </a>
            </li>
          </ul>
        </div>

        <!-- Logout Link at the Bottom -->
        <div id="nav2">
          <ul class="nav flex-column">
            <li class="nav-item logout-item">
              <button class="nav-link text-dark btn" @click="confirmLogout">
                <i class="fa fa-sign-out-alt"></i>
                Logout
              </button>
            </li>
          </ul>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Swal from 'sweetalert2';
export default {
  name: 'SideBarT',
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
#nav1 {
  margin-top: 50px;
}
#nav2 {
  margin-top: 230px;
}
.greeting {
  font-family: Georgia, serif;
  font-weight: 700;
  font-size: 2.5rem;
  color: grey;
  margin: 0;
}

.welcome {
  font-family: Georgia, serif;
  font-weight: 400;
  font-size: 1.25rem;
  color: #F6B339;
  margin-top: 0.5rem;
}

.page-container {
  display: flex;
  flex-direction: row;
  min-height: 100vh;
  margin-top: 60px;
}

.custom-navbar {
  background-color: #f8f9fa;
  padding: 10px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  z-index: 10;
  margin-left: 250px;
}

.avatar-container {
  display: flex;
  align-items: center;
}

.user-avatar {
  width: 70px;
  height: 70px;
  border-radius: 50%;
  object-fit: cover;
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
  width: 150px;
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
  color: #333;
  text-decoration: none;
}

.nav-link:hover {
  background-color: #f1f1f1;
  transform: scale(1.05);
}

.nav-link i {
  margin-right: 15px;
  font-size: 20px;
  transition: color 0.2s ease, transform 0.2s ease;
}

.nav-item.active .nav-link {
  background-color: #fcbf49;
  color: #fff;
}

.nav-item.active .nav-link i {
  color: #fff;
}

.logout-item {
  margin-top: auto;
}

.nav-item a:hover i {
  color: #fcbf49;
  transform: scale(1.1);
  transition: transform 0.2s ease, color 0.2s ease;
}

.content {
  margin-left: 250px;
  flex-grow: 1;
  padding: 20px;
}

@media (max-width: 768px) {
  .sidebar {
    width: 200px;
  }

  .content {
    margin-left: 200px;
    width: calc(100% - 200px);
  }

  .logo img {
    width: 100px;
  }
}
</style>
