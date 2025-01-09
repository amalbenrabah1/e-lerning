<template>
  <div class="list-students">
    <SideBarA />
    <div class="content">
      <h1>List of Students</h1>
      <table class="table table-bordered">
        <thead>
          <tr>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Email</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody v-if="students.length > 0">
          <tr v-for="student in students" :key="student.id">
            <td>{{ student.firstName }}</td>
            <td>{{ student.lastName }}</td>
            <td>{{ student.email }}</td>
            <td>
              <button 
                class="btn btn-primary" 
                @click="openUpdateModal(student)">Update</button>
              <button 
                class="btn btn-danger" 
                @click="deleteStudent(student.id)">Delete</button>
            </td>
          </tr>
        </tbody>
        <tbody v-else>
          <tr><td colspan="4">No students found</td></tr>
        </tbody>
      </table>
    </div>

    <!-- Modal for Updating a Student -->
    <div v-if="showUpdateModal" class="modal">
      <div class="modal-content">
        <span class="close" @click="closeUpdateModal">&times;</span>
        <h2>Update Student</h2>
        <form @submit.prevent="updateStudent">
          <div class="form-group">
            <label for="firstName">First Name:</label>
            <input type="text" id="firstName" v-model="selectedStudent.firstName" required />
          </div>
          <div class="form-group">
            <label for="lastName">Last Name:</label>
            <input type="text" id="lastName" v-model="selectedStudent.lastName" required />
          </div>
          <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" id="email" v-model="selectedStudent.email" required />
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
import Swal from "sweetalert2";

export default {
  name: "ListStudents",
  components: {
    SideBarA,
  },
  data() {
    return {
      students: [],
      showUpdateModal: false,
      selectedStudent: null,
    };
  },
  methods: {
    fetchUsers() {
      axios
        .get("http://localhost:8080/api/info")
        .then((response) => {
          this.students = response.data.filter((user) => user.role === "student");
        })
        .catch((error) => {
          console.error("Error fetching users:", error);
        });
    },
    openUpdateModal(student) {
      this.selectedStudent = { ...student }; // Clone the student object
      this.showUpdateModal = true;
    },
    closeUpdateModal() {
      this.showUpdateModal = false;
      this.selectedStudent = null;
    },
    updateStudent() {
      if (!this.selectedStudent || !this.selectedStudent.id) return;

      axios
        .put(`http://localhost:8080/api/info/${this.selectedStudent.id}`, this.selectedStudent)
        .then(() => {
          Swal.fire({
            title: 'Success!',
            text: 'Student updated successfully.',
            icon: 'success',
            confirmButtonText: 'OK',
          });
          this.closeUpdateModal();
          this.fetchUsers(); // Refresh the list
        })
        .catch((error) => {
          console.error("Error updating student:", error);
          Swal.fire({
            title: 'Error!',
            text: 'Error updating student.',
            icon: 'error',
            confirmButtonText: 'OK',
          });
        });
    },
    deleteStudent(studentId) {
      if (!studentId) return;

      Swal.fire({
        title: 'Are you sure?',
        text: "This action cannot be undone.",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonText: 'Yes, delete it!',
        cancelButtonText: 'No, keep it',
      }).then((result) => {
        if (result.isConfirmed) {
          axios
            .delete(`http://localhost:8080/api/info/${studentId}`)
            .then(() => {
              Swal.fire({
                title: 'Deleted!',
                text: 'Student deleted successfully.',
                icon: 'success',
                confirmButtonText: 'OK',
              });
              this.fetchUsers(); // Refresh the list
            })
            .catch((error) => {
              console.error("Error deleting student:", error);
              Swal.fire({
                title: 'Error!',
                text: 'Error deleting student.',
                icon: 'error',
                confirmButtonText: 'OK',
              });
            });
        }
      });
    },
  },
  mounted() {
    this.fetchUsers();
  },
};

</script>

<style scoped>
.list-students {
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

th,
td {
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

.btn-danger {
  background-color: #dc3545;
  color: white;
  padding: 5px 10px;
  border-radius: 5px;
  border: none;
}

.btn-primary {
  background-color: #26e083;
  color: white;
  padding: 5px 10px;
  border-radius: 5px;
  border: none;
  margin-right: 5px;
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

.btn-primary:hover {
  background-color: #108a2b;
}
</style>
