import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/browser',
    name: 'Browser',
    component: () => import('@/views/BrowserView.vue')
  },
  {
    path: '/features',
    name: 'Features',
    component: () => import('@/views/FeaturesView.vue')
  },
  {
    path: '/leaderboard',
    name: 'Leaderboard',
    component: () => import('@/views/LeaderboardView.vue')
  },
  {
    path: '/citations',
    name: 'PaperCitations',
    component: () => import('@/views/CitationsView.vue')
  },
  {
    path: '/community',
    name: 'Community',
    component: () => import('@/views/CommunityView.vue')
  },
  {
    path: '/description',
    name: 'Description',
    component: () => import('@/views/DescriptionView.vue')
  },
  {
    path: '/cases',
    name: 'Cases',
    component: () => import('@/views/CasesView.vue')
  },
  {
    path: '/download',
    name: 'Download',
    component: () => import('@/views/DownloadView.vue')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router