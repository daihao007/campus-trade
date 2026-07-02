# CampusTrade 开发日志

> 项目：CampusTrade - 校园二手交易平台

---

## 项目目标

开发一个基于 Spring Boot + Vue3 的校园二手交易平台，实现用户登录、商品发布、商品浏览、收藏、留言等功能，并采用前后端分离架构。

---

# 第一阶段：项目初始化

## Day 1

### 完成内容

- [x] 创建 GitHub 仓库
- [x] 创建项目目录结构
- [x] 编写 README
- [x] 创建 sql/init.sql

### 数据库设计

已完成数据表：

- [x] user
- [x] category
- [x] product
- [x] favorite
- [x] message

### Git Commit

```bash
feat: initialize project structure
```

### 遇到的问题

无

### 学习收获

- 学会设计数据库表之间的关系
- 理解一对多、多对多关系
- 学会使用外键约束

---

# 下一步计划

- [ ] 初始化 Spring Boot
- [ ] 配置 MyBatis-Plus
- [ ] 连接 MySQL
- [ ] 完成用户注册接口