# 数据结构管理系统（DSMS）

## 项目概述

数据结构管理系统(Data Structure Management System)是一个前后端分离的系统，用于管理和展示不同类型的数据结构。系统提供了丰富的可视化界面和操作功能，支持各种数据结构的管理、展示和操作。

## 技术栈

### 前端技术栈

- **框架**：Vue 3 + TypeScript
- **构建工具**：Vite
- **UI 组件库**：Element Plus
- **状态管理**：Vuex 4
- **路由管理**：Vue Router 4
- **HTTP 请求**：Axios
- **国际化**：Vue-i18n
- **可视化图表**：ECharts
- **富文本编辑器**：TinyMCE
- **其他工具库**：SortableJS, Cropperjs, xlsx 等

### 后端技术栈

- **框架**：Spring Boot 3.4.4
- **数据库访问**：Spring Data JPA
- **安全框架**：Spring Security
- **响应式编程**：Spring WebFlux
- **数据库**：H2 (开发环境)
- **工具库**：Lombok, Hutool

## 系统功能

### 核心功能

1. **数据结构可视化展示**：支持多种数据结构的可视化展示
2. **数据结构管理**：提供数据结构的增删改查操作
3. **用户权限管理**：基于角色的用户权限控制
4. **系统配置管理**：提供系统参数配置功能

### 特色功能

1. **多种表格管理**：
   - 业务表格
   - 分类联动表格
   - 树联动表格
2. **多语言支持**：内置中英文支持，可扩展其他语言
3. **主题定制**：支持主题切换和定制
4. **响应式布局**：适配不同尺寸的设备
5. **图表可视化**：内置多种数据图表展示

## 项目结构

```
├── dsms-web/               # 前端项目
│   ├── src/                # 源代码
│   │   ├── api/            # API接口
│   │   ├── assets/         # 静态资源
│   │   ├── components/     # 公共组件
│   │   ├── config/         # 配置文件
│   │   ├── layout/         # 布局组件
│   │   ├── router/         # 路由配置
│   │   ├── store/          # 状态管理
│   │   ├── utils/          # 工具函数
│   │   ├── views/          # 页面视图
│   │   ├── App.vue         # 根组件
│   │   └── main.ts         # 入口文件
│   └── package.json        # 项目依赖
└── dsms-backend/           # 后端项目
    ├── src/                # 源代码
    │   └── main/java       # Java代码
    │       └── io/github/luolong47/dsmsbackend
    └── pom.xml             # Maven配置
```

## 安装与运行

### 前端项目

```bash
# 进入前端项目目录
cd dsms-web

# 安装依赖
yarn install

# 开发环境运行
yarn dev

# 生产环境构建
yarn build
```

### 后端项目

```bash
# 进入后端项目目录
cd dsms-backend

# 使用Maven编译
./mvnw clean package

# 运行jar包
java -jar target/dsms-backend-0.0.1-SNAPSHOT.jar
```

## 环境要求

- Node.js >= 14
- Java >= 17
- Maven >= 3.6

## 开发指南

详细的开发文档请参考项目中的相关文档。主要的开发流程包括：

1. 前端组件开发
2. API接口定义与实现
3. 数据结构模型定义
4. 权限与安全控制
5. 业务逻辑实现

## 贡献指南

欢迎提交问题和功能需求，或者直接提交代码贡献。贡献前请先阅读项目的贡献指南。

## 许可证

本项目使用 [LICENSE](LICENSE) 许可证。 