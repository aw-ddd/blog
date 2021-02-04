import Vue from 'vue'
import VueRouter from 'vue-router'
import BlogEdit from '../views/blog/BlogEdit.vue'
import BlogShow from "../views/blog/BlogShow";
import BlogList from "../views/blog/BlogList";
import Login from "../views/login/Login";

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: '/',
    component: Login
  },
  {
    path: '/blogList',
    name: 'BlogList',
    component: BlogList
  },
  {
    path: '/blogEdit',
    name: 'BlogEdit',
    component: BlogEdit
  },
  {
    path: '/blogShow',
    name:'BlogShow',
    component: BlogShow
  },
  {
    path: '/login',
    name:'Login',
    component: Login
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
