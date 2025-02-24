![lmage](https://github.com/user-attachments/assets/b850b057-38e4-4c21-be91-fdba0b9447d2)

- *后端(Spring Boot) :选用MyBatis进行数据访问层开发，全局装配了异常处理器，采用双检锁单例模式进行管理，使用JUnit Jupiter API 的@Test 注释和Assertions lei实现对用户模块的单元测试，覆盖率高达90%。

- *_前端(Vue.js)_: Vue.js用于实现用户界面，构建过程使用了ant design vue组件库，使用pinia库管理全局状态，发送请求给Django API并展示响应数据。

- *###通信方式:前端通过Axios(或其他HTTP 客户端库)向Django API发送请求，并通过JSON 格式交换数据。

- a.Spring Boot  **_项目_**
安装最新idae,用文件中maven构建依赖和jdk(自行下载，推荐20.3版本，不易出问题)，然后自建数据库连接运行UserCenterApplication.·即可

- 默认情况下，后端会运行在http://127.0.0.1:8080

 b.vue  **_项目_**
- 安装Node.js 
- 安装npm(pnpm)和yarn(基本知识自行在网上搜索指令)
- 安装Axios和 Pinia库  pnpm install axios ，pnpm install pinia，当然如果用 npm 也是同理
- 安装 vue 脚手架 npm install -g @vue/cli

- 通过 yarn 和 package.json 构建和安装相关依赖

- 运行

pnpm(npm) run serve
默认情况下前端端口会运行在8081或者8080下，后端接口请求头部已经引用了允许跨域请求头，可根据自己端口号修改即可，
或者自行配置proxy_pass代理跨域
### 运行中可能存在的问题

- 前端在编写过程中使用了prettier进行代码规范化，可能不符合您的IDE设置，如果遇到格式之类的错误，可尝试 npm run lint --fix 进行修复
（如文件代码规范支持代码_行尾序列_为LF,大多数情况为CRLF，导致大面积报错，记得修改设置即可）

- 后端端口号被占用导致的错误
解决办法： [https://blog.csdn.net/weixin_46030002/article/details/126649348](url)
项目有点庞大许多逻辑是作者手搓，可能存在小bug，还请海涵。
