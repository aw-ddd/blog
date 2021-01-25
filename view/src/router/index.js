import Vue from 'vue'
import VueRouter from 'vue-router'
import BlogEdit from '../views/blog/BlogEdit.vue'
import BlogShow from "../views/blog/BlogShow";
import BlogList from "../views/blog/BlogList";

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: BlogList
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
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
