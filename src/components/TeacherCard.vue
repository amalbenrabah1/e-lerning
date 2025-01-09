<template>
    <div class="card">
      <img :src="teacher.img" class="card-img-top" alt="Teacher" />
      <div class="card-body">
        <h5 class="card-title">{{ teacher.name }}</h5>
        <p class="card-text">{{ teacher.subject }}</p>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    name: 'TeacherCard',
    props: {
      teacher: Object
    }
  };
  </script>
  
  <style scoped>
  .card {
    width: 200px;
    margin: 10px;
  }
  </style>
  