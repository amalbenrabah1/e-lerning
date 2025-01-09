<template>
  <div class="list-teachers">
    <SideBarA />
    <div class="content">
      <h1>List of Teachers</h1>
      <table class="table table-bordered">
        <thead>
          <tr>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Email</th>
            <th>Actions</th> 
          </tr>
        </thead>
        <tbody>
          <tr v-for="teacher in teachers" :key="teacher.id">
            <td>{{ teacher.firstName }}</td>
            <td>{{ teacher.lastName }}</td>
            <td>{{ teacher.email }}</td>
            <td>
              <button 
                class="btn btn-primary" 
                @click="openUpdateModal(teacher)">Update</button>
              <button 
                class="btn btn-danger" 
                @click="deleteTeacher(teacher.id)">Delete</button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>

    <!-- Modal for Updating a Teacher -->
    <div v-if="showUpdateModal" class="modal">
      <div class="modal-content">
        <span class="close" @click="closeUpdateModal">&times;</span>
        <h2>Update Teacher</h2>
        <form @submit.prevent="updateTeacher">
          <div class="form-group">
            <label for="firstName">First Name:</label>
            <input type="text" id="firstName" v-model="selectedTeacher.firstName" required />
          </div>
          <div class="form-group">
            <label for="lastName">Last Name:</label>
            <input type="text" id="lastName" v-model="selectedTeacher.lastName" required />
          </div>
          <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" id="email" v-model="selectedTeacher.email" required />
          </div>
          <button type="submit" class="btn btn-primary">Save Changes</button>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import SideBarA from "@/components/SideBarA.vue";
import axios from "axios";

export default {
  name: "ListTeachers",
  components: {
    SideBarA
  },
  data() {
    return {
      teachers: [],
      showUpdateModal: false,
      selectedTeacher: null
    };
  },
  methods: {
    fetchTeachers() {
      axios.get("http://localhost:8080/api/info")
        .then(response => {
          const users = response.data;
          this.teachers = users.filter(user => user.role === 'teacher');
        })
        .catch(error => {
          console.error("Error fetching teachers:", error);
        });
    },
    openUpdateModal(teacher) {
      this.selectedTeacher = { ...teacher }; // Clone the teacher object
      this.showUpdateModal = true;
    },
    closeUpdateModal() {
      this.showUpdateModal = false;
      this.selectedTeacher = null;
    },
    updateTeacher() {
      if (!this.selectedTeacher || !this.selectedTeacher.id) return;

      axios.put(`http://localhost:8080/api/info/${this.selectedTeacher.id}`, this.selectedTeacher)
        .then(() => {
          alert("Teacher updated successfully");
          this.closeUpdateModal();
          this.fetchTeachers(); // Refresh the list
        })
        .catch(error => {
          console.error("Error updating teacher:", error);
          alert("Error updating teacher");
        });
    },
    deleteTeacher(teacherId) {
      if (!teacherId) return;

      axios.delete(`http://localhost:8080/api/info/${teacherId}`)
        .then(() => {
          alert("Teacher deleted successfully");
          this.fetchTeachers(); // Refresh the list
        })
        .catch(error => {
          console.error("Error deleting teacher:", error);
          alert("Error deleting teacher");
        });
    }
  },
  mounted() {
    this.fetchTeachers();
  }
};
</script>

<style scoped>
.list-teachers {
  display: flex;
  flex-direction: row;
  margin-top: 150px;
  margin-right: 200px;
}

.content {
  margin-left: 250px;
  flex-grow: 1;
  padding: 20px;
}

.table {
  width: 100%;
  margin-top: 20px;
  border-collapse: collapse;
}

th, td {
  padding: 10px;
  text-align: left;
}

th {
  background-color: #fcbf49;
  color: white;
}

tr:nth-child(even) {
  background-color: #f1f1f1;
}

.btn-primary {
  background-color: #26e083;
  color: white;
  padding: 5px 10px;
  border-radius: 5px;
  border: none;
  margin-right: 5px;
}

.btn-primary:hover {
  background-color: #108a2b;
}

.btn-danger {
  background-color: #dc3545;
  color: white;
  padding: 5px 10px;
  border-radius: 5px;
  border: none;
}

.modal {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
}

.modal-content {
  background: white;
  padding: 20px;
  border-radius: 5px;
  width: 400px;
}

.close {
  position: absolute;
  top: 10px;
  right: 20px;
  font-size: 1.5em;
  cursor: pointer;
}

.form-group {
  margin-bottom: 10px;
}

.form-group label {
  display: block;
  margin-bottom: 5px;
}

.form-group input {
  width: 100%;
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 4px;
}
</style>
