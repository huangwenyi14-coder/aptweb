<template>
  <div id="manage">
    <el-container>
      <el-header
          style="
            background-color: white;
            height: 62px;
            display: flex;
            align-items: center;
            justify-content: space-between;
            padding: 0 20px;
            position: fixed;
            top: 0;
            left: 0;
            right: 0;
            z-index: 1000;
            box-shadow: inset 0 -2px 0 cornflowerblue;
          "
      >
        <!-- logo+标题 -->
        <router-link
            to="/"
            style="display: flex; align-items: center; text-decoration: none;"
            @click.native="handleLogoClick"
        >
          <div style="display: flex; align-items: center;">
            <img src="@/assets/logo.png" alt="" style="width: 40px; position: relative; top: 5px">
            <span style="font-size: 18px; color: cornflowerblue; margin-left: 12px">
              {{ windowWidth > 768 ? 'ZAPT Dataset Browser' : 'ZAPT' }}
            </span>
          </div>
        </router-link>

        <!-- 大屏幕：显示完整菜单 -->
        <el-menu
            v-if="windowWidth > 1280"
            :default-active="activeIndex"
            mode="horizontal"
            style="border-bottom: none; height: 57px;margin-bottom: 4px"
            background-color="white"
            text-color="#666"
            active-text-color="cornflowerblue"
            @select="handleMenuSelect"
        >
          <el-menu-item index="0">首页</el-menu-item>
          <el-menu-item index="1">数据集浏览器</el-menu-item>
          <el-menu-item index="2">数据集特色</el-menu-item>
          <el-menu-item index="3">排行榜</el-menu-item>
          <el-menu-item index="4">论文引用</el-menu-item>
          <el-menu-item index="5">社区</el-menu-item>
          <el-menu-item index="6">数据集说明</el-menu-item>
          <el-menu-item index="7">实践案例</el-menu-item>
          <el-menu-item index="8">数据集下载</el-menu-item>
        </el-menu>

        <!-- 小屏幕：显示菜单按钮 -->
        <el-dropdown v-else @command="handleMenuCommand">
          <el-button type="primary" icon="el-icon-menu" style="background-color: cornflowerblue; border-color: cornflowerblue;">
            菜单
          </el-button>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item command="0" :icon="activeIndex === '0' ? 'el-icon-check' : ''">
              首页
            </el-dropdown-item>
            <el-dropdown-item command="1" :icon="activeIndex === '1' ? 'el-icon-check' : ''">
              数据集浏览器
            </el-dropdown-item>
            <el-dropdown-item command="2" :icon="activeIndex === '2' ? 'el-icon-check' : ''">
              数据集特色
            </el-dropdown-item>
            <el-dropdown-item command="3" :icon="activeIndex === '3' ? 'el-icon-check' : ''">
              排行榜
            </el-dropdown-item>
            <el-dropdown-item command="4" :icon="activeIndex === '4' ? 'el-icon-check' : ''">
              论文引用
            </el-dropdown-item>
            <el-dropdown-item command="5" :icon="activeIndex === '5' ? 'el-icon-check' : ''">
              社区
            </el-dropdown-item>
            <el-dropdown-item command="6" :icon="activeIndex === '6' ? 'el-icon-check' : ''">
              数据集说明
            </el-dropdown-item>
            <el-dropdown-item command="7" :icon="activeIndex === '7' ? 'el-icon-check' : ''">
              实践案例
            </el-dropdown-item>
            <el-dropdown-item command="8" :icon="activeIndex === '8' ? 'el-icon-check' : ''">
              数据集下载
            </el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>

        <!-- 右侧用户区域 -->
        <div style="display: flex; align-items: center;">
          <el-button
              v-if="windowWidth > 480"
              type="text"
              icon="el-icon-bell"
              style="font-size: 18px; color: #666; margin-right: 15px"
          ></el-button>
          <el-dropdown>
            <span style="display: flex; align-items: center; cursor: pointer">
              <el-avatar size="small" style="background-color: cornflowerblue; margin-right: 5px">用户</el-avatar>
              <i v-if="windowWidth > 480" class="el-icon-arrow-down el-icon--right"></i>
            </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>个人中心</el-dropdown-item>
              <el-dropdown-item>账号设置</el-dropdown-item>
              <el-dropdown-item divided>退出登录</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </div>
      </el-header>

      <el-main style="background-color:#99CCFF;min-height:100vh;padding-top: 70px;">
        <router-view/>
      </el-main>
    </el-container>
  </div>
</template>
<style>
.el-menu{
  border-right: none !important;
}
</style>
<script>
export default {
  name: 'Manage',
  data() {
    return {
      activeIndex: '0',  // 当前选中的菜单项
      windowWidth: window.innerWidth,
      // 菜单路由映射表：菜单编号 -> 路由路径
      menuRoutes: {
        '0': '/home',
        '1': '/browser',
        '2': '/features',
        '3': '/leaderboard',
        '4': '/citations',
        '5': '/community',
        '6': '/description',
        '7': '/cases',
        '8': '/download'
      },
      // 记录当前路径，用于避免重复跳转
      currentPath: '/home'
    }
  },
  mounted() {
    window.addEventListener('resize', this.handleResize)
    // 页面加载时，根据当前路由设置选中状态
    this.setActiveMenuFromRoute()
    this.currentPath = this.$route.path
  },
  beforeDestroy() {
    window.removeEventListener('resize', this.handleResize)
  },
  methods: {
    // 处理logo点击
    handleLogoClick() {
      this.navigateToRoute('0')  // 使用统一的跳转方法
    },
    handleResize() {
      this.windowWidth = window.innerWidth
    },

    // 处理水平菜单的选择
    handleMenuSelect(index) {
      this.navigateToRoute(index)
    },

    // 处理下拉菜单的选择
    handleMenuCommand(command) {
      this.navigateToRoute(command)
    },

    // 统一的跳转方法（修复重复跳转问题）
    navigateToRoute(menuIndex) {
      // 1. 获取对应的路由路径
      const routePath = this.menuRoutes[menuIndex]

      // 2. 检查是否已经是当前路径，避免重复跳转
      if (routePath === this.currentPath) {
        console.log('已经是当前页面，跳过跳转')
        return
      }

      // 3. 更新选中状态
      this.activeIndex = menuIndex

      // 4. 执行路由跳转
      if (routePath) {
        this.$router.push(routePath)
        this.currentPath = routePath  // 更新当前路径记录
        console.log('跳转到:', routePath)
      } else {
        console.warn('未找到菜单对应的路由:', menuIndex)
      }
    },

    // 根据当前路由设置选中状态
    setActiveMenuFromRoute() {
      const currentPath = this.$route.path
      this.currentPath = currentPath  // 更新当前路径记录

      // 查找当前路径对应的菜单编号
      for (const [menuIndex, routePath] of Object.entries(this.menuRoutes)) {
        if (currentPath === routePath ) {
          this.activeIndex = menuIndex
          return
        }
      }
      this.activeIndex = '0'
    }
  },

  // 监听路由变化，更新菜单选中状态
  watch: {
    '$route'(to) {
      this.setActiveMenuFromRoute()
    }
  }
}
</script>