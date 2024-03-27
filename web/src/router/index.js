import { createRouter, createWebHistory } from 'vue-router';
import FindStudentView from '../views/FindStudentView';
import QueryScoreView from '../views/QueryScoreView';
import LoginView from '../views/LoginView';
import RegisterView from '../views/RegisterView';
import store from '../store/index';
import FindBusView from '../views/FindBusView';
const routes = [

  {
    path: "/findstudent/",
    name: "findstudent",
    component: FindStudentView,
    meta: {
      requestAuth: true,
    }
  },
  {
    path: "/querybus/",
    name: "home",
    component: FindBusView,
    meta: {
      requestAuth: true,
    }
  },
  {
    path: "/queryscore/",
    name: "queryscore",
    component: QueryScoreView,
    meta: {
      requestAuth: true,
    }
  },
  {
    path: "/login/",
    name: "login",
    component: LoginView,
    meta: {
      requestAuth: false,
    }
  },
  {
    path: "/register/",
    name: "register",
    component: RegisterView,
    meta: {
      requestAuth: false,
    }
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})
router.beforeEach((to, from, next) => {
  if (to.meta.requestAuth && !store.state.user.is_login) {
    next({ name: "login" });
  } else {
    next();
  }
});
export default router
