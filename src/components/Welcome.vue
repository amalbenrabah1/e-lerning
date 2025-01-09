<template>
    <div class="container" id="container">
      <div class="row">
        <div class="col-md-6 text-center">
          <h1 class="promo-title">Welcome to our online learning platform</h1>
          <p class="promo-text">
            Ready to dive into something awesome?<br>
            Here, you’ll find cool courses that make learning fun and easy.<br>
            Whether you’re just starting or leveling up, we’ve got everything you need to grow your skills.<br>
            Join the community, explore new ideas, and learn at your own pace.<br>
            Let’s make learning exciting! Our Teachers
          </p>
          <router-link to="/login" class="btn btn-primary">Get Started</router-link>
          
        </div>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    name: 'WelcomeAPP',
  };
  </script>
  
  <style scoped>
  /* Centering the row and adjusting height */
  .row {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    text-align: center; /* Ensure text is centered */
  }
  
  /* Styling for the promo title */
  .promo-title {
    font-size: 40px;
    font-weight: 600;
    color: #450920; /* Changed to a more vibrant color */
    text-transform: uppercase;
    margin-bottom: 20px;
    letter-spacing: 2px; /* Adding some space between letters for better readability */
  }
  
  /* Styling for the promo text */
  .promo-text {
    font-size: 18px;
    line-height: 1.8;
    color: #2d3436; /* Darker, more readable text */
    margin-bottom: 30px;
    font-family: 'Arial', sans-serif;
  }
  
  /* Styling for the button */
  .btn-primary {
    background-color: #8e44ad; /* Vibrant purple color */
    border-color: #8e44ad;
    padding: 14px 35px;
    font-size: 20px;
    font-weight: 600;
    text-transform: uppercase;
    border-radius: 50px; /* More rounded corners */
    transition: background-color 0.3s ease, transform 0.2s ease;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  }
  
  .btn-primary:hover {
    background-color: #9b59b6; /* Slightly lighter purple on hover */
    border-color: #9b59b6;
    transform: translateY(-2px); /* Button lift effect */
  }
  
  .btn-primary:active {
    transform: translateY(0); /* Button returns to original position when clicked */
  }
  
  /* Media Query for smaller devices */
  @media (max-width: 768px) {
    .promo-title {
      font-size: 30px;
    }
  
    .promo-text {
      font-size: 16px;
    }
  
    .btn-primary {
      font-size: 18px;
      padding: 12px 25px;
    }
  }
  </style>
  