import { createRouter, createWebHistory } from 'vue-router';
import LoginPage from './components/LoginPage.vue'; 
import SignUp from './components/SignUp.vue';
import Navbar from './components/Navbar.vue';
import Promo from './components/Promo.vue';
import Welcome from './components/Welcome.vue';
import Teachers from './components/Teachers.vue';
import AboutUs from './components/AboutUs.vue';
import FAQ from './components/FAQ.vue';
import Contact from './components/Contact.vue';
import Footer from './components/Footer.vue';
import Home from './components/Home.vue';
import SideBar from './components/SideBar.vue';
import PricingCard from './components/PricingCard.vue';
import DashBordteacher from './components/DashBordteacher.vue';
import SideBarT from './components/SideBarT.vue';
import AdminDashbord from './components/AdminDashbord.vue';
import VerifyEmail from './components/VerifyEmail.vue';
import DashBordS from './components/DashBordS.vue';
import ProfilT from './components/ProfilT.vue';
import CoursesT from './components/CoursesT.vue';
import ProfilS from './components/ProfilS.vue';
import LevelS from './components/LevelS.vue';
import LevelSS from './components/LevelSS.vue';
import LevelSSS from './components/LevelSSS.vue';
import ResetPassword from './components/ResetPassword.vue';
import ForgotPassowrd from './components/ForgotPassword.vue';
import QuizzS from './components/QuizzS.vue'; 
import StudyS from './components/StudyS.vue';
import courselist from './components/courselist.vue';
import QuizT from './components/QuizT.vue';
import Quizs from './components/Quizs.vue';
import PricingCardquiz from './components/PricingCardquiz.vue';
import QuizList from './components/QuizList.vue';
import SideBarA from './components/SideBarA.vue';
import ListS from './components/ListS.vue';
import ListTeachers from './components/ListTeachers.vue';

// Define routes
const routes = [
  { path: '/', component: Home },
  { path: '/login', name: 'login', component: LoginPage },
  { path: '/signup', component: SignUp },
  { path: '/navbar', component: Navbar, meta: { requiresAuth: true } },
  { path: '/sidebarT', component: SideBarT, meta: { requiresAuth: true } },
  { path: '/promo', component: Promo, meta: { requiresAuth: true } },
  { path: '/teachers', component: Teachers, meta: { requiresAuth: true } },
  { path: '/aboutus', component: AboutUs, meta: { requiresAuth: true } },
  { path: '/admindashbord', name: "aDashboard", component: AdminDashbord, meta: { requiresAuth: true } },
  { path: '/faq', component: FAQ, meta: { requiresAuth: true } },
  { path: '/contact', component: Contact, meta: { requiresAuth: true } },
  { path: '/footer', component: Footer, meta: { requiresAuth: true } },
  { path: '/sidebar', component: SideBar, meta: { requiresAuth: true } },
  { path: '/pricingcard', component: PricingCard, meta: { requiresAuth: true } },
  { path: '/welcome', component: Welcome, meta: { requiresAuth: true } },
  { path: '/dashbordteacher', name: 'tDashboard', component: DashBordteacher, meta: { requiresAuth: true } },
  { path: '/forgotpassword', name: 'ForgotPassword', component: ForgotPassowrd },
  { path: '/verifyemail', name: 'verification', component: VerifyEmail },
  { path: '/dashbords', name: 'sDashboard', component: DashBordS, meta: { requiresAuth: true } },
  { path: '/coursesT', name: 'CoursesT', component: CoursesT, meta: { requiresAuth: true } },
  { path: '/ProfilS', name: 'ProfilS', component: ProfilS, meta: { requiresAuth: true } },
  { path: '/LevelS', name: 'LevelS', component: LevelS, meta: { requiresAuth: true } },
  { path: '/LevelSS', name: 'LevelSS', component: LevelSS, meta: { requiresAuth: true } },
  { path: '/LevelSSS', name: 'LevelSSS', component: LevelSSS, meta: { requiresAuth: true } },
  { path: '/resetpassword', name: 'ResetPassword', component: ResetPassword },
  { path: '/quizzS', name: 'QuizzS', component: QuizzS, meta: { requiresAuth: true } },
  { path: '/studyS', name: 'StudyS', component: StudyS, meta: { requiresAuth: true } },
  { path: '/courselist', name: 'coursList', component: courselist, props: true, meta: { requiresAuth: true } },
  { path: '/quizT', name: 'QuizT', component: QuizT, meta: { requiresAuth: true } },
  { path: '/quizs', name: 'Quizs', component: Quizs, meta: { requiresAuth: true } },
  { path: '/quizlist', name: 'QuizList', component: QuizList, meta: { requiresAuth: true } },
  { path: '/pricingcardquiz', name: 'PricingCardquiz', component: PricingCardquiz, meta: { requiresAuth: true } },
  { path: '/sidebarA', name: 'SideBarA', component: SideBarA, meta: { requiresAuth: true } },
  { path: '/listS', name: 'ListS', component: ListS, meta: { requiresAuth: true } },
  { path: '/listTeachers', name: 'ListTeachers', component: ListTeachers, meta: { requiresAuth: true } },
  { path: '/ProfilT', name: 'ProfilT', component: ProfilT, meta: { requiresAuth: true } }
];

// Create the router
const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
});

// Global navigation guard
router.beforeEach((to, from, next) => {
  console.log('Route guard déclenchée pour', to.name);
  const isAuthenticated = localStorage.getItem("email");
  if (to.meta.requiresAuth && !isAuthenticated) {
    console.log('Utilisateur non authentifié, redirection...');
    next({ name: 'login' });
  } else {
    console.log('Accès autorisé.');
    next();
  }
});


export default router;
