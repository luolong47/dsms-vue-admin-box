/** 动态路由实现基础组件 */
/** 引入全局Layout组件 */
import Layout from '@/layout/index.vue'
/** 引入多级菜单控制器组件 */
import MenuBox from '@/components/menu/index.vue'
/** 引入带有系统自定义name的组件，方便keep-alive实现 */
import { createNameComponent } from '../createNode'
/** 引入所有的配置清单 */
import backConfig from './backConfig'
import { getMenuApi } from '@/api/user'

/** 获取后台模板配置清单 */
const getMenu = async () => {
  const result = await getMenuApi()
  const backRoutes = getComponents(result.data.list)
  return backRoutes
}

/** 循环取出component */
const getComponents = (data: any[], level = 1) => {
  const newData: any[] = data.map((item) => {
    if (item.children) {
      if (level == 1) {
        return {
          ...item,
          component: Layout,
          children: getComponents(item.children, level + 1)
        }
      } else {
        return {
          ...item,
          component: MenuBox,
          children: getComponents(item.children, level + 1)
        }
      }
    } else {
      const [first, end] = item.component.split('_')
      let component

      // 添加类型安全检查
      if (backConfig[first as keyof typeof backConfig]) {
        const moduleConfig = backConfig[first as keyof typeof backConfig]
        // 字符串索引签名断言
        component = (moduleConfig as any)[end]
        if (!component) {
          console.error(`组件 "${end}" 在 "${first}" 模块中不存在`)
        }
      } else {
        console.error(`模块 "${first}" 在backConfig中不存在`)
      }

      return {
        ...item,
        component,
      }
    }
  })
  return newData
}

export default getMenu