<template>
  <div class="page-container">
    <!-- Navbar -->
    <header>
      <nav class="navbar navbar-expand-md bg-body custom-navbar fixed-top">
        <div class="navbar-brand d-flex align-items-center justify-content-between w-100">
          <div class="text-container">
            <h1 class="greeting">HI, ADMIN</h1>
            <p class="welcome">Welcome Back!</p>
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

        <!-- Navigation Links -->
        <div id="nav1">
          <ul class="nav flex-column">
            <li class="nav-item logout-item">
              <a class="nav-link text-dark" href="/admindashbord">
                <i class="fa fa-tachometer-alt"></i>
                Dashborde
              </a>
            </li>

            <li class="nav-item logout-item">
              <button class="nav-link text-dark btn" @click="goToList('students')">
                <i class="fa fa-users"></i>
                List of Students
              </button>
            </li>
            <li class="nav-item logout-item">
              <button class="nav-link text-dark btn" @click="goToList('teachers')">
                <i class="fa fa-chalkboard-teacher"></i>
                List of Teachers
              </button>
            </li>
          </ul>
        </div>

        <!-- Logout Button -->
        <div id="nav2">
          <ul class="nav flex-column">
            <li class="nav-item logout-item">
              <button class="nav-link text-dark btn" @click="logout">
                <i class="fa fa-sign-out-alt"></i>
                Logout
              </button>
            </li>
          </ul>
        </div>
      </div>
    </div>
    
    <!-- Main Content -->
    <div class="content">
      <!-- Add your content here -->
    </div>
  </div>
</template>

<script>
import Swal from "sweetalert2";

export default {
  name: "SideBarA",
  data() {
    return {
      userName: "",
    };
  },
  methods: {
    fetchUserName() {
      console.log("Fetching username...");
      fetch("http://localhost:8080/api/info/${this.userId}", {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
        },
      })
        .then((response) => {
          if (!response.ok) {
            throw new Error("Error fetching user data");
          }
          return response.json();
        })
        .then((data) => {
          this.userName = data?.name || "User"; // Default to "User"
        })
        .catch((error) => {
          console.error("Error:", error.message);
          this.userName = "User"; // Default name in case of error
        });
    },
    logout() {
      Swal.fire({
        title: 'Are you sure?',
        text: 'You are about to log out. Do you want to proceed?',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonText: 'Yes, log me out!',
        cancelButtonText: 'Cancel',
        reverseButtons: true
      }).then((result) => {
        if (result.isConfirmed) {
          localStorage.removeItem("user");
          sessionStorage.removeItem("user");

          Swal.fire({
            title: 'Logged out!',
            text: 'You have been logged out successfully.',
            icon: 'success',
            confirmButtonText: 'OK',
          }).then(() => {
            this.$router.push("/login");
          });
        }
      });
    },
    goToList(role) {
      if (role === "students") {
        this.$router.push("/listS"); // Redirect to student list
      } else if (role === "teachers") {
        this.$router.push("/listTeachers"); // Redirect to teacher list
      }
    }
  },
  mounted() {
    this.fetchUserName(); // Fetch username when the component is mounted
  }
};
</script>


<style scoped>
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
  z-index: 10;
  margin-left: 250px;
}

.avatar-container {
  display: flex;
  align-items: center;
}
#nav1{
  margin-top: 100px;

}

#nav2{
  margin-top: 260px;

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
}

.nav-item.active .nav-link {
  background-color: #fcbf49;
  color: #fff;
}

.nav-item.active .nav-link i {
  color: #fff;
}

/* Logout Item Styling */
.logout-item {
  margin-top: auto;
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
