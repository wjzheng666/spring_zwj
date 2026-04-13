import {
  createWebHistory,
  createRouter,
} from "vue-router";
import SpecialListView from '../views/SpecialListView.vue'
import SpecialDetailView from '../views/SpecialDetailView.vue'
const routes = [
  {
    path: "/",
    component: SpecialListView,
    name:"special-list"
  },{
    path: "/details/:id",
    component: SpecialDetailView,
    name:"details"
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
