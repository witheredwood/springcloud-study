# spring cloud 学习笔记

可以正常访问的版本搭配

[spring 官网 spring cloud 对应的spring boot 和其他东西的版本](https://docs.spring.io/spring-cloud/docs/Hoxton.SR12/reference/html/) ：https://docs.spring.io/spring-cloud/docs/Hoxton.SR12/reference/html/

本项目中使用的所有东西版本号如下：

```xml
<spring.cloud>Hoxton.SR12
<spring.boot>2.3.12.RELEASE
<lombok>1.18.20
<junit>4.12
<log4j>1.2.17
<!-- eureka -->
<spring-cloud-starter-netflix-eureka-server>2.2.9.RELEASE
<spring-cloud-starter-eureka>1.4.7.RELEASE
<!-- ribbon -->
<spring-cloud-starter-ribbon>1.4.7.RELEASE
<!-- hystrix -->
<spring-cloud-starter-hystrix>1.4.7.RELEASE  
<spring-cloud-starter-hystrix-dashboard>1.4.7.RELEASE  
```

基础：

- 数据库
- Mybatis
- Spring
- SpringMVC
- SpringBoot
- Dubbo、Zookeeper、分布式基础
- Maven
- Git
- Ajax、Json



**微服务4个核心问题：**

- 客户端怎么访问？
- 服务之间怎么通信？
- 服务如何治理？
- 服务挂了怎么办？

spring cloud 是微服务架构的一种解决方案。

**所有的解决方案如下：**

1. `spring cloud NetF7ix` 一站式解决方案!
   - api 网关，zuul组件
   - Feign ---Httpclinet  --- Http通信方式，同步，阻塞
   - 服务注册发现:Eureka
   - 熔断机制: `Hystrix`

2. `Apache Dubbo zookeeper`
   - API:没有，找第三方组件，或者自己实现
   - Dubbo
   - zookeeper
   - 没有:借助 Hystrix

​       Dubbo这个方案并不完善~

3. `spring cloud Alibaba `  一站式解决方案

所有的解决方案核心是：

- API
- 通信：HTTP ,RPC
- 注册和发现
- 熔断机制

**常见面试题：**

- 什么是微服务?
- 微服务之间是如何独立通讯的?
- SpringCloud和Dubbo有哪些区别?
- SpringBoot和SpringCloud，请你谈谈对他们的理解什么是服务熔断？什么是服务降级？
- 微服务的优缺点是分别是什么？说下你在项目开发中遇到的坑
- 你所知道的微服务技术栈有哪些?请列举一二
- eureka和zookeeper都可以提供服务注册与发现的功能，请说说两个的区别?



## 微服务

**优点：**

- 单一职责原则
- 每个服务足够内聚，足够小，代码容易理解，这样能聚焦一个指定的业务功能或业务需求;
- 开发简单，开发效率提高，一个服务可能就是专一的只干一件事;
- 微服务能够被小团队单独开发，这个小团队是2~5人的开发人员组成;
- 微服务是松耦合的，是有功能意义的服务，无论是在开发阶段或部署阶段都是独立的。
- 微服务能使用不同的语言开发。
- 易于和第三方集成，微服务允许容易且灵活的方式集成自动部署，通过持续集成工具，如jenkins、Hudson、bamboo
- 微服务易于被一个开发人员理解，修改和维护，这样小团队能够更关注自己的工作成果。无需通过合作才能体现价值。
- 微服务允许你利用融合最新技术。
- 微服务只是业务逻辑的代码，不会和HTML , CSS或其他界面混合
- 每个微服务都有自己的存储能力，可以有自己的数据库，也可以有统一数据库

**缺点**

- 开发人员要处理分布式系统的复杂性
- 多服务运维难度，随着服务的增加，运维的压力也在增大
- 系统部署依赖
- 服务间通信成本数据一致性
- 系统集成测试
- 性能监控....

**spring cloud 和spring boot的区别？**

- SpringBoot专注于快速方便的开发单个个体微服务。- Jar
- SpringCloud是关注全局的微服务协调整理治理框架，它将SpringBoot开发的一个个单体微服务整合并管理起来，为各个微服务之间提供:配置管理，服务发现，断路器，路由，微代理，事件总线，全局锁，决策竞选，分布式会话等等集成服务。
- SpringBoot可以离开SpringClooud独立使用，开发项目，但是SpringCloud离不开SpringBoot，属于依赖关系
- SpringBoot专注于快速、方便的开发单个个体微服务，SpringCloud关注全局的服务治理框架

**参考网址：**

- [Spring Cloud Netflix](https://spring.io/projects/spring-cloud-netflix)

- [spring cloud 中文文档](https://www.springcloud.cc/spring-cloud-dalston.html)

- [中国社区](http://springcloud.cn/)

- [中文网](https://www.springcloud.cc/)

## 1. api接口 (module1-api)

创建数据库中表对应的实体类，所有的实体类必须实现序列化。

## 2. REST环境搭建

### 服务提供者环境搭建 (module2-provider-8001)

提供不同的接口，可以访问数据库。有dao层，service 层，controller 层。

### 服务消费者环境搭建 (module3-consumer-80)

只有controller层，没有service层，通过远程调用http服务调用服务提供者中的接口。

## 3. Eureka服务注册和发现

Eureka是Netflix的一个子模块，也是核心模块之一。Eureka是一个**基于REST**的服务，用于定位服务，以实现云端中间层服务发现和故障转移，服务注册与发现对于微服务来说是非常重要的，有了服务发现与注册，只需要使用服务的标识符，就可以访问到服务，而不需要修改服务调用的配置文件了，功能类似于Dubbo的注册中心，比如Zookeeper;

**Eureka的基本架构**

- SpringCloud封装了NetFlix公司开发的Eureka模块来实现服务注册和发现(对比Zookeeper)o Eureka采用了C-S的架构设计，EurekaServer作为服务注册功能的服务器，他是服务注册中心
- 而系统中的其他微服务使用Eureka的客户端连接到EurekaServer并维持心跳连接。这样系统的维护人员就可以通过EurekaServer来监控系统中各个微服务是否正常运行，SpringCloud的一些其他模块
  (比如Zuul)就可以通过EurekaServer来发现系统中的其他微服务，并执行相关的逻辑;

**三大角色**

- `Eureka Server`：提供服务的注册于发现。zookeeper
- `Service Provider`：将自身服务注册到Eureka中，从而使消费方能够找到。
- `Service Consumer`：服务消费方从Eureka中获取注册服务列表，从而找到消费服务。



- Eureka包含两个组件: **Eureka Server** 和 **Eureka Client** 。
- `Eureka Server` 提供服务注册服务，各个节点启动后，会在EurekaServer中进行注册，这样Eureka Server中的服务注册表中将会村粗所有可用服务节点的信息，服务节点的信息可以在界面中直观的看到。
- `Eureka Client` 是一个ava客户端，用于简化EurekaServer的交互，客户端同时也具备一个内置的，使用轮询负载算法的负载均衡器。在应用启动后，将会向EurekaServer发送心跳（默认周期为30秒)。如果Eureka Server在多个心跳周期内没有接收到某个节点的心跳，EurekaServer将会从服务注册表中把这个服务节点移除掉（默认周期为90秒)I

### 搭建Eureka服务端环境 (module4-eureka-7001)

**Step1 导入依赖**

```xml
<!-- https://mvnrepository.com/artifact/org.springframework.cloud/spring-cloud-starter-netflix-eureka-server -->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
    <version>3.0.4</version>
</dependency>
```

**Step2 编写配置文件**

添加全局配置文件 `application.yaml`

```yaml
server:
  port: 7001

# Eureka 配置
eureka:
  instance:
    hostname: localhost  #  Eureka 服务端实例名字
  client:
    fetch-registry: false  # 自己是注册中心
    register-with-eureka: false # 不向注册中心注册自己
    service-url: # 监控页面
      defaultzone: http://${eureka.instance.hostname}:${server.port}/eureka/
```

**Step3 开启Eureka服务器端功能**

Eureka 服务端启动类 `EurekaServer`

```java
@SpringBootApplication
@EnableEurekaServer
public class EurekaServer {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServer.class, args);
    }
}
```

启动之后，访问7001端口



### eureka 客户端 (module5-provider-eureka-8001)

**服务注册**

修改服务消费者的代码，使其注册到 eureka 中。这里把服务消费者（`module2-provider-8001`）的代码复制了一份，重新建了一个模块 `module5-provider-eureka-8001` ，用于记录 eureka 客户端的修改。

**Step1 导入依赖**

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-eureka</artifactId>
    <version>1.4.7.RELEASE</version>
</dependency>
```

**Step2 编写配置文件**

在全局配置文件 `application.yaml` 中添加 eureka 的配置。

```yaml
# eureka 配置
eureka:
  client:
    service-url:
     defaultZone: http://localhost:7001/eureka/
  instance:
    instance-id: springcloud-prover-eureka-8001
```

**Step3 开启Eurekak客户端功能**

Eureka 服务端启动类 `EurekaServer`

```java
@SpringBootApplication
@EnableEurekaClient
public class ProviderEureka {
    public static void main (String[] args) {
        SpringApplication.run(ProviderEureka.class, args);
    }
}
```

**Step4 启动后查看是否成功**

先后启动以下模块：

- eureka 注册中心 `module4-eureka-7001`
- 服务提供者 `module5-provider-eureka-8001`

启动之后，访问 `http://localhost:7001` 端口查看。

`module4-eureka-7001` 模板使用的端口是7001，`module5-provider-eureka-8001`使用的端口是8001

### 自我保护机制

自我保护机制:好死不如赖活着
一句话总结:某时刻某一个微服务不可以用了，eureka不会立刻清理，依旧会对该微服务的信息进行保存!

- 默认情况下，如果EurekaServer在一定时间内没有接收到某个微服务实例的心跳，EurekaServer将会注销该实例（默认90秒)。但是当网络分区故障发生时，微服务与Eureka之间无法正常通行，以上行为可能变得非常危险了。

  因为微服务本身其实是健康的，此时**本不应该注销这个服务**。Eureka通过自我保护机制来解决这
  个问题。 当EurekaServer节点在短时间内丢失过多客户端时(可能友生了网络分区战厚)，那么这个节点就会进入自我保护模式。一旦进入该模式，EurekaServer就会保护服务注册表中的信息，不再删除服务注册表中
  的数据（也就是不会注销任何微服务)。当网络故障恢复后，该EurekaServer节点会自动退出自我保护模式。

- 在自我保护模式中，EurekaServer会保护服务注册表中的信息，不再注销任何服务实例。当它收到的心跳数重新恢复到阈值以上时，该EurekaServer节点就会自动退出自我保护模式。它的设计哲学就是宁可保留错误的服务注册信息，也不盲目注销任何可能健康的服务实例。一句话：好死不如赖活着

- 综上，自我保护模式是一种应对网络异常的安全保护措施。它的架构哲学是宁可同时保留所有微服务（健康的微服务和不健康的微服务都会保留)，也不盲目注销任何健康的微服务。使用自我保护模式，可以让Eureka集群更加的健壮和稳定

- 在SpringCloud中，可以使用eureka.server.enable-se1f-preservation = false 禁用自我保护模式【不推荐关闭自我保护机制】

### 集群环境配置

增加3个eureka注册中心模块用于测试集群环境，除了端口号，其余的代码与 `module4-eureka-7001` 相同，这3个模板命名为 `module6-eureka-7001` ,  `module6-eureka-7002` , `module6-eureka-7003`

**Step1 修改3个eureka注册中心**

修改第一个模块 `module6-eureka-7001` 中的全局配置文件 `application.yaml` 

```yaml
server:
  port: 7001

# Eureka 配置
eureka:
  instance:
    hostname: eureka1.com  #  Eureka 服务端实例名字
  client:
    fetch-registry: false  # 自己是注册中心
    register-with-eureka: false # 不向注册中心注册自己
    service-url: # 监控页面
      defaultzone: http://eureka2.com:7002/eureka/,http://eureka3.com:7003/eureka/
```

其他两个模块文件只是与这一个模块的端口号不同

如果将hosts文件中 `127.0.0.1`  设置为多个不同的名字 `eureka1.com `  , `eureka2.com ` , `eureka3.com ` ，记得测试完将添加的删除。

**Step2 修改服务提供者**

在 `module5-provider-eureka-8001` 中的全局配置文件 `application.yaml` 中将单机修改为集群环境下的 `defaultZone` ：

```yaml
# eureka 配置
eureka:
  client:
    service-url:
      # 集群
      defaultZone: http://localhost:7001/eureka/,http://localhost:7002/eureka/,http://localhost:7003/eureka/
  instance:
    instance-id: springcloud-prover-eureka-8001
```

**Step3 启动后查看是否成功**

启动以下模块：

- eureka 注册中心 `module6-eureka-7001`
- eureka 注册中心 `module6-eureka-7002`
- eureka 注册中心 `module6-eureka-7003`

启动三个模块后查看，理论上可以在 `http://localhost:7001` 的页面上看到`eureka2.com ` , `eureka3.com `的名字，但是 我的只能看到 `localhost`，集群环境没有模拟成功。所以就把上面的 `eureka1.com ` , `eureka2.com ` , `eureka3.com ` 又全部改成了 `localhost` ，项目中的代码写的也是 `localhost`。

### CAP 原则

CAP是什么?

- C (Consistency) 强一致性

- A (Availability)可用性
- P (Partition tolerance)分区容错性

CAP原则最多只能两个原则。

### Eureka 和 Zookeeper 区别

**Zookeeper保证的是CP**
当向注册中心查询服务列表时，我们可以容忍注册中心返回的是几分钟以前的注册信息，但不能接受服务直接down掉不可用。也就是说，服务注册功能对可用性的要求要高于一致性。但是zk会出现这样一种情况，当master节点因为网络故障与其他节点失去联系时，剩余节点会重新进行leader选举。问题在于，选举leader的时间太长，30~120s，且选举期间整个zk集群都是不可用的，这就导致在选举期间注册服务瘫痪。在云部署的环境下，因为网络问题使得k集群失去master节点是较大概率会发生的事件，虽然服务最终能够恢复，但是漫长的选举时间导致的注册长期不可用是不能容忍的。

**Eureka保证的是AP**
Eureka看明白了这一点，因此在设计时就优先保证可用性。Eureka各个节点都是平等的，几个节点挂掉不会影响正常节点的工作，剩余的节点依然可以提供注册和查询服务。而Eureka的客户端在向某个Eureka注册时，如果
发现连接失败，则会自动切换至其他节点，只要有一台Eureka还在，就能保住注册服务的可用性，只不过查到的信息可能不是最新的，除此之外，Eureka还有一种自我保护机制，如果在15分钟内超过85%的节点都没有正常的心跳，那么Eureka就认为客户端与注册中心出现了网络故障，此时会出现以下几种情况:
1.Eureka不再从注册列表中移除因为长时间没收到心跳而应该过期的服务
2.Eureka仍然能够接受新服务的注册和查询请求，但是不会被同步到其他节点上(即保证当前节点依然可用)3.当网络稳定时，当前实例新的注册信息会被同步到其他节点中
因此，Eureka可以很好的应对因网络故障导致部分节点失去联系的情况，而不会像zookeeper那样使整个注册服务瘫痪

## 4. Ribbon 负载均衡 (module7-ribbon-consumer-80)

### 介绍

Ribbon 为客户端提供负载均衡。

ribbon是什么?

- Spring Cloud Ribbon是基于Netflix Ribbon实现的一套**客户端负载均衡的工具**。
- 简单的说，Ribbon是Netflix发布的开源项目，主要功能是提供客户端的软件负载均衡算法，将NetFlix的中间层服务连接在一起。Ribbon的客户端组件提供一系列完整的配置项如:连接超时、重试等等。简单的说，就是在配置文件中列出LoadBalancer(简称LB:负载均衡）后面所有的机器，Ribbon会自动的帮助你基于某种规则(如简单轮询，随机连接等等）去连接这些机器。我们也很容易使用Ribbon实现自定义的负载均衡算法!

ribbon能干嘛?

- LB，即负载均衡(Load Balance)，在微服务或分布式集群中经常用的一种应用。
- 负载均衡简单的说就是将用户的请求平摊的分配到多个服务上，从而达到系统的HA(高可用)
- 常见的负载均衡软件有Nginx，Lvs等等
- dubbo、SpringCloud中均给我们提供了负载均衡，SpringCloud的负载均衡算法可以自定义
- 负载均衡简单分类：
  - 集中式LB
    即在服务的消费方和提供方之间使用独立的LB设施，如Nginx，由该设施负责把访问请求通过某种策略转发至服务的提供方!
  - 进程式LB
    将LB逻辑集成到消费方，消费方从服务注册中心获知有哪些地址可用，然后自己再从这些地址中选出一个合适的服务器。
    Ribbon就属于进程内LB，它只是一个类库，集成于消费方进程，消费方通过它来获取到服务提供方的地址!

Ribbon和 Eureka整合以后，客户端可以直接调用，不用关心IP地址和端口号。

创建一个新模块，模块的内容与模块 `module3-consumer-80` 一样，在此基础上，增加 ribbon 的内容。

### 搭建 Ribbon 环境

**Step1 导入依赖**

导入 ribbon 和 eureka 的依赖

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-ribbon</artifactId>
    <version>1.4.6.RELEASE</version>
</dependency>
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-eureka</artifactId>
    <version>1.4.6.RELEASE</version>
</dependency>
```

**Step2 编写配置文件**

在配置文件 `applicationi.yaml` 中增加 eureka 的配置

```yaml
# eureka 配置
eureka:
  client:
    register-with-eureka: false # 不向eureka注册自己
    service-url:
      defaultZone: http://localhost:7001/eureka/,http://localhost:7002/eureka/,http://localhost:7003/eureka/
```

**Step3 开启eureka客户端功能**

在启动类 `Module7_RibbonConsumer_80` 中增加注解 `@EnableEurekaClient` 

```java
@SpringBootApplication
@EnableEurekaClient
public class Module7_RibbonConsumer_80 {
    public static void main(String[] args) {
        SpringApplication.run(Module7_RibbonConsumer_80.class, args);
    }
}

```

**Step4 开启 ribbon 负载均衡**

在 `ConfigBean` 类中配置负载均衡实现 RestTemplate， 也就是增加 `@LoadBalanced` 注解。

```java
@Bean
@LoadBalanced  // 配置负载均衡实现 RestTemplate
public RestTemplate getRestTemplate() {
    return new RestTemplate();
}
```

**Step5  使用应用名访问**

在 `DeptController` 类中将地址访问前缀由 `http://localhost:8001` 修改为 `http://MODULE5-PROVIDER-EUREKA`。

```java
// 负载均衡
private static final String REST_URL_SUFFIX = "http://MODULE5-PROVIDER-EUREKA";
```

`MODULE5-PROVIDER-EUREKA` 是访问 `http://localhost:7001` 时 `Application` 中的应用名，也是在模块5 `module5-provider-eureka-8001 ` 中设置的spring的应用名

```yaml
# spring 配置
spring:
  application:
    name: module5-provider-eureka
```

**Step6 启动模块后查看是否成功**

先后启动以下模块

-  eureka注册中心模块（ `module6-eureka-7001` 、`module6-eureka-7002`），可以只启动 7001 查看
- 服务提供者模块 `module5-provider-eureka-8001`
- 服务消费者模块 `module7-ribbon-consumer-80`

启动之后，访问以下网址查看是否成功

- 访问`http://localhost:7001` 查看服务是否启动，如果启动，会在 `Application` 版块看到应用名为**MODULE5-PROVIDER-EUREKA** 的服务用例。

- 访问 `http://http://localhost/consumer/dept/list` 查看，如果页面显示数据库中的数据，则ribbon成功使用。我数据库中的数据是下面的数据：

```
[{"deptno":1,"dname":"开发部","db_source":"clouddemo"},{"deptno":2,"dname":"人事部","db_source":"clouddemo"},{"deptno":3,"dname":"市场部","db_source":"clouddemo"},{"deptno":4,"dname":"财务部","db_source":"clouddemo"},{"deptno":5,"dname":"运维部","db_source":"clouddemo"}]
```

### 实现负载均衡

这里实现的是：一个消费者访问某一个服务，该服务有3个提供者支持，消费者访问服务和服务地址均不变，但是提供服务的提供者在3个提供者中变化。

**Step1 创建数据库**

创建2个数据库 `clouddemo2` ，`clouddemo3`，数据与 `clouddemo1` 大致相同，值修改其中 `db_source` 

**Step2 创建模块**

复制模块 `module5-provider-eureka-8001` ，创建2个新的模块 `module8-provider-eureka-8002` 、`module8-provider-eureka-8003` 。新创建的2个模块，除了端口号（分别为 8002、8003）和实例id（`instance-id`），其余与 `module5-provider-eureka-8001` 相同

**Step3 启动查看是否成功**

先后启动以下模块：

-  eureka注册中心模块 `module6-eureka-7001` 
-  服务提供者模块 `module5-provider-eureka-8001` 、 `module8-provider-eureka-8002`  、 `module8-provider-eureka-8003` 
-  服务消费者模块 `module7-ribbon-consumer-80`

我在启动是遇到的一个问题是：服务提供者添加热部署，就只能启动一个提供者，无法同时启动多个提供者。所以，需要将3个服务提供者中的热部署依赖删除。

启动之后，

- 访问地址 `http://localhost:7001` 

  启动的服务列表中有 `SPRINGCLOUD-PROVIDER-DEPT` 的服务 ，` Availability Zones` 中显示有3个。

  ![image-20211202134516058](https://gitee.com/withered-wood/picture/raw/master/20211202134517.png)

- 访问地址 `http://localhost/consumer/dept/list` ：

```
[{"deptno":1,"dname":"开发部","db_source":"clouddemo3"},{"deptno":2,"dname":"人事部","db_source":"clouddemo3"},{"deptno":3,"dname":"市场部","db_source":"clouddemo3"},{"deptno":4,"dname":"财务部","db_source":"clouddemo3"},{"deptno":5,"dname":"运维部","db_source":"clouddemo3"}]
```

```
[{"deptno":1,"dname":"开发部","db_source":"clouddemo1"},{"deptno":2,"dname":"人事部","db_source":"clouddemo1"},{"deptno":3,"dname":"市场部","db_source":"clouddemo1"},{"deptno":4,"dname":"财务部","db_source":"clouddemo1"},{"deptno":5,"dname":"运维部","db_source":"clouddemo1"}]
```

```
[{"deptno":1,"dname":"开发部","db_source":"clouddemo2"},{"deptno":2,"dname":"人事部","db_source":"clouddemo2"},{"deptno":3,"dname":"市场部","db_source":"clouddemo2"},{"deptno":4,"dname":"财务部","db_source":"clouddemo2"},{"deptno":5,"dname":"运维部","db_source":"clouddemo2"}]
```

这里三个提供者模块是依次提供服务，采用的是轮询的方法。 

### 自定义负载均衡算法

```java
//在微服务启动的时候就能去加载我们自定义Ribbon类
Ribbonclient(name = "SPRINGCLOUD-PROVIDER-DEPT" ,configuration = KuangRule.class)
```

## 5. Feign 负载均衡

feign是声明式的web service客户端，它让微服务之间的调用变得更简单了，类似controller调用service。SpringCloud集成了Ribbon和Eureka，可在使用Feign时提供负载均衡的http客户端。
只需要创建一个接口，然后添加注解即可!

feign，主要是社区，大家都习惯面向接口编程。这个是很多开发人员的规范。调用微服务访问两种方法

- 微服务名字：ribbon
- 接口和注解：feign 

**Feign能干什么?**

- Feign旨在使编写Java Http客户端变得更容易
- 前面在使用Ribbon + RestTemplate时，利用RestTemplate对Http请求的封装处理，形成了一套模板化的调用方法。但是在实际开发中，由于对服务依赖的调用可能不止一处，往往一个接口会被多处调用，所以通常都会针对每个微服务自行封装一些客户端类来包装这些依赖服务的调用。所以，Feign在此基础上做了进一步封装，由他来帮助我们定义和实现依赖服务接口的定义，在Feign的实现下，我们只需要创建一个接口并使用注解的方式来配置它(类似于以前Dao接口上标注Mapper注解，现在是一个微服务接口上面标注一个Feign注解即可。）即可完成对服务提供方的接口绑定，简化了使用Spring Cloud Ribbon时，自动封装服务调用客户端的开发量。

**Feign集成了Ribbon**
利用Ribbon维护了MicroServiceCloud-Dept的服务列表信息，并且通过轮询实现了客户端的负载均衡，而与Ribbon不同的是，通过Feign只需要定义服务绑定接口且以声明式的方法，优雅而且简单的实现了服务调用。

### 修改api （module9-feign-api）

将模块 `module1-api` 的内容复制一份，新建一个模块 `module9-feign-api` ，在模块 `module1-api` 的基础上增加 feign 的部分。

### 修改消费者 （module9-feign-consumer-80）

将模块 `module7-ribbon-consumer-80` 的内容复制一份，新建一个模块 `module9-feign-consumer-80` ，在模块 `module7-ribbon-consumer-80` 的基础上将 ribbon 修改为 feign 的部分。

**启动查看是否成功**

- eureka 注册中心 `module6-eureka-7001`
- 服务提供者 `module8-provider-eureka-8002`、 `module8-provider-eureka-8003`
- 服务消费者 `module9-feign-consumer-80`

启动之后，查看网址：

- `http://localhost:7001/` ：查看微服务是否启动成功

- `http://localhost/consumer/dept/list` ：

  刷新页面，查看页面是否能依次显示 两个数据库中的数据。如果能依次显示出数据，则成功。

## 6. Hystrix 服务熔断

**分布式系统面临的问题**

复杂分布式体系结构中的应用程序有数十个依赖关系，每个依赖关系在某些时候将不可避免的失败!

**服务雪崩**
多个微服务之间调用的时候，假设微服务A调用微服务B和微服务C，微服务B和微服务C又调用其他的微服务，这就是所谓的“扇出"、如果扇出的链路上某个微服务的调用响应时间过长或者不可用，对微服务A的调用就会占用越来越多的系统资源，进而引起系统崩溃，所谓的“雪崩效应"。

对于高流量的应用来说，单一的后端依赖可能会导致所有服务器上的所有资源都在几秒中内饱和。比失败更糟糕的是，这些应用程序还可能导致服务之间的延迟增加，备份队列，线程和其他系统资源紧张，导致整个系统发生更多的级联故障，这些都表示需要对故障和延迟进行隔离和管理，以便单个依赖关系的失败，不能取消整个应用程序或系统。

我们需要 弃车保帅

**什么是Hystrix**

Hystrix是一个用于处理分布式系统的延迟和容错的开源库，在分布式系统里，许多依赖不可避免的会调用失败，比如超时，异常等，Hystrix能够保证在一个依赖出问题的情况下，不会导致整体服务失败，避免级联故障，以提高分布式系统的弹性。

"断路器"本身是一种开关装置，当某个服务单元发生故障之后，通过断路器的故障监控（类似熔断保险丝)，**向调用方返回一个服务预期的，可处理的备选响应(FallBack)，而不是长时间的等待或者抛出调用方法无法处理的异常**，**这样就可以保证了服务调用方的线程不会被长时间**，不必要的占用，从而避免了故障在分布式系统中的蔓延，乃至雪崩。



**Hystrix能做什么**

- 服务降级
- 服务熔断
- 服务限流
- 接近实时的监控
- .....



**服务熔断**

熔断机制是对应雪崩效应的一种微服务链路保护机制。

当扇出链路的某个微服务不可用或者响应时间太长时，会进行服务的降级，进而**熔断该节点微服务的调用，快速返回错误的响应信息**。当检测到该节点微服务调用响应正常后恢复调用链路。在SpringCloud框架里熔断机制通过Hystrix实现。Hystrix会监控微服务间调用的状况，当失败的调用到一定阈值，缺省是5秒内20次调用失败就会启动熔断机制。熔断机制的注解是**@HystrixCommand**。



```yaml
#Eureka的配置,服务注册到哪里leureka:
client:
service-url:
defaultZone: http://eureka7e01.com:7ee1/eureka/ ,http://eureka7002.com:7e2/eureka , http: /leuneka7003 .com:70c3/eureka/instance:
instance-id: springcloud-provider-dept8001 #修改eureka上的默认描述信息!prefer-ip-address: true# true,可以显示服务的IP地址~

```

### 服务熔断

**Step1 导入依赖**

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-hystrix</artifactId>
    <version>1.4.7.RELEASE</version>
</dependency>
```

**Step2 开启服务熔断功能**

在启动类 `Module10_HystrixProviderEureka_8001` 中添加注解 `@EnableCircuitBreaker`

```java
@SpringBootApplication
@EnableEurekaClient  // 服务启动后自动注册到 Eureka 中
@EnableDiscoveryClient
@EnableCircuitBreaker()  // 开启服务熔断
public class Module10_HystrixProviderEureka_8001 {
    public static void main (String[] args) {
        SpringApplication.run(Module10_HystrixProviderEureka_8001.class, args);
    }
}
```

**Step3 添加服务熔断**

在 `DeptController` 中添加以下代码

```java
@GetMapping("/get/{id}")
@HystrixCommand(fallbackMethod = "getHystrix")
public Dept getById(@PathVariable("id") Long id) {
    Dept dept = deptService.getById(id);
    if (dept == null) {
        throw new RuntimeException("id => " + id + " 不存在");
    }
    return dept;
}

public Dept getHystrix(Long id) {
    return new Dept()
        .setDeptno(id)
        .setDname("id => " + id + " 不存在")
        .setDb_source("不存在该数据库");
}
```

**Step4 启动查看是否成功**

先后启动以下模块：

- eureka 注册中心 `module6-eureka-7001`
- 服务提供者 `module10-hystrix-provider-8001`
- 服务消费者 `module3-consumer-80`

启动之后，查看以下网址：

- `http://localhost:7001/` ：查看微服务是否启动成功

- 查询数据库中 `id = 2` 的数据 `http://localhost/consumer/dept/get/2` ：

  页面上显示 

  ```
  {"deptno":2,"dname":"人事部","db_source":"clouddemo1"}
  ```

- 查询数据库中不存在的id `http://localhost/consumer/dept/get/10`

  页面上显示 

  ```
  {"deptno":10,"dname":"id => 10 不存在","db_source":"不存在该数据库"}
  ```

  到此，测试完成。

### 服务降级

服务熔断：

​	服务端~某个服务超时或者异常，引起熔断~，保险丝~

服务降级：

-  客户端、从整体网站请求负载考虑~，当某个服务熔断或者关闭之后，服务将不再被调用~

-  此时在客户端，我们可以准备一个 FallbackFactory，返回一个默认的值(缺省值)，
- 整体的服务水平下降了~但是，好歹能用·比直接挂掉强~

### 监控

**监控页面**

在客户端编写监控页面。

**Step1 导入依赖**

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-hystrix-dashboard</artifactId>
    <version>1.4.7.RELEASE</version>
</dependency>
```

**Step2 开启监控功能**

在启动类 `module11_consumer_hystrix_dashboard_80` 中添加注解 `@EnableHystrixDashboard`

```java
@SpringBootApplication
@EnableHystrixDashboard
public class module11_consumer_hystrix_dashboard_80 {
    public static void main(String[] args) {
        SpringApplication.run(module11_consumer_hystrix_dashboard_80.class, args);
    }
}
```

**Step3 编写全局配置文件**

在 `application.yaml` 中添加以下代码

```yaml
server:
  port: 9001
```

**Step4 启动查看是否成功**

先后启动以下模块：

- eureka 注册中心 `module6-eureka-7001`
- 服务提供者 `module10-hystrix-provider-8001`
- 服务消费者 `module11_consumer_hystrix_dashboard_80`

启动之后，查看以下网址：

- `http://localhost:7001/` ：查看微服务是否启动成功
- 监控页面 `http://localhost:9001/hystrix` ：看到野猪页面就成功了

**在监控中放服务**

需要在服务中导入 actuator 监控依赖。复制模块 `module5-provider-eureka-8001` ，新建模块 `module11-provider-hystrix-dashboard-8001	`

流监控

**Step1 导入依赖**

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-hystrix-dashboard</artifactId>
    <version>1.4.7.RELEASE</version>
</dependency>
```

**Step2 开启监控功能**

在启动类 `module11_consumer_hystrix_dashboard_80` 中添加注解 `@EnableHystrixDashboard`

```java
@SpringBootApplication
@EnableHystrixDashboard
public class module11_consumer_hystrix_dashboard_80 {
    public static void main(String[] args) {
        SpringApplication.run(module11_consumer_hystrix_dashboard_80.class, args);
    }
}
```

**Step3 编写全局配置文件**

在 `application.yaml` 中添加以下代码

```yaml
server:
  port: 9001
  
# hystrix dashboard 设置
hystrix:
  dashboard:
    proxy-stream-allow-list: localhost
```

**Step4 启动查看是否成功**

先后启动以下模块：

- eureka 注册中心 `module6-eureka-7001`
- 监控模块 `module11-consumer-hystrix-dashboard-9001`
- 服务提供者 `module11-provider-hystrix-dashboard-8001`

启动之后，查看以下网址：

- `http://localhost:7001/` ：查看微服务是否启动成功

- 监控页面 `http://localhost:9001/hystrix` ：看到野猪页面就成功了

  监控此地址：http://localhost:8001/actuator/hystrix.stream

## 7. Zuul 路由网关

**什么是Zuul?**
Zuul包含了对请求的**路由和过滤**两个最主要的功能:

其中路由功能负责将外部请求转发到具体的微服务实例上，是实现外部访问统一入口的基础，而过滤器功能则负责对请求的处理过程进行干预，是实现请求校验，服务聚合等功能的基础。Zuul和Eureka进行整合将Zuul自身注册为Eureka服务治理下的应用，同时从Eureka中获得其他微服务的消息，也即以后的访问微服务都是通过Zuul跳转后获得。

Zuul服务最终还是会注册进Eureka ，提供：代理＋路由＋过滤三大功能!

### 搭建路由

复制模块 `module11-consumer-hystrix-dashboard-9001` ，新建模块 `module12-zuul-9527	`

**Step1 导入依赖**

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-zuul</artifactId>
    <version>1.4.7.RELEASE</version>
</dependency>
```

**Step2 编写全局配置文件**

zuul 需要注册到 Eureka 中。

在 `application.yaml` 中添加以下代码

```yaml
server:
  port: 9527

# spring 配置
spring:
  application:
    name: springcloud-zuul

# Eureka 设置
eureka:
  client:
    service-url: http://localhost:7001/eureka,http://localhost:7002/eureka,http://localhost:7003/eureka
  instance:
    instance-id: zuul-9527.com

info:
  app.name: springcloud-zuul.com
  company.name: withered.com
```

**Step3 开启zuul**

在启动类 `module12_zuul_9527` 中添加注解 `@EnableZuulProxy`

```java
@SpringBootApplication
@EnableZuulProxy
public class module12_zuul_9527 {
    public static void main(String[] args) {
        SpringApplication.run(module12_zuul_9527.class, args);
    }
}

```

**Step4 启动查看是否成功**

先后启动以下模块：

- eureka 注册中心 `module6-eureka-7001`
- 服务提供者 `module5-provider-eureka-8001` （ `module8-provider-eureka-8002` 、`module8-provider-eureka-8003` 也可以）
- 监控模块 `module11-consumer-hystrix-dashboard-9001`

启动之后，查看以下网址：

- `http://localhost:7001/` ：查看微服务是否启动成功
- 查看接口是否可用`http://localhost:8001/dept/list` ：可以查询到数据库数据
- 查看是否可用通过路由访问 `http://localhost:9527/springcloud-provider-dept/dept/list` ：可以查询到数据库数据

下面配置 zuul ，隐藏应用名 `springcloud-provider-dept` ，在 `application.yaml` 中添加以下代码：

```yaml
zuul:
  routes:
  	# 配置某个应用的路由
    mydept.serviceId: springcloud-provider-dept
    mydept.path: /mydept/**
  ignored-services: "*"  # 不能使用应用名访问，隐藏全部的应用名
  prefix: /withered  # 设置统一的访问前缀，要在9527后添加 /withered 才能访问
```

启动之后，查看是否可用通过路由访问 `http://localhost:9527/withered/mydept/dept/list` ：可以查询到数据库数据就成功了。

## 8. config

**分布式系统面临的--配置文件的问题**

微服务意味着要将单体应用中的业务拆分成一个个子服务，每个服务的粒度相对较小，因此系统中会出现大量的服务，由于每个服务都需要必要的配置信息才能运行，所以一套集中式的，动态的配置管理设施是必不可少的。
SpringCloud提供了ConfigServer来解决这个问题，我们每一个微服务自己带着一个application.yml，那上百的的配置文件要修改起来，岂不是要发疯!

**Springcloud 分布式配置中心**

![Springcloud 分布式配置中心](https://gitee.com/withered-wood/picture/raw/master/20211206111626.jpeg)

Spring Cloud Config为微服务架构中的微服务提供集中化的外部配置支持，配置服务器为各个不同微服务应用的所有环节提供了一个中心化的外部配置。

Spring Cloud Config 分为**服务端**和**客户端**两部分;

服务端也称为分布式配置中心，它是一个独立的微服务应用，用来连接配置服务器并为客户端提供获取配置信息，加密，解密信息等访问接口。

客户端则是通过指定的配置中心来管理应用资源，以及与业务相关的配置内容，并在启动的时候从配置中心获取和加载配置信息。配置服务器默认采用git来存储配置信息，这样就有助于对环境配置进行版本管理。并且可以通过git客户端工具来方便的管理和访问配置内容。

**SpringCloud config分布式配置中心能干嘛**

- 集中管理配置文件
- 不同环境，不同配置，动态化的配置更新，分环境部署，比如/dev /test/ /prod /beta /release
- 运行期间动态调整配置，不再需要在每个服务部署的机器上编写配置文件，服务会向配置中心统一拉取配置自己的信息。
- 当配置发生变动时，服务不需要重启，即阿感知到配置的变化，并应用新的配置
- 将配置信息以REST接口的形式暴露

### 服务器端连接Git

通过 `config-server` 可以连接到git，访问其中的资源以及配置。

**Step1 导入依赖**

```xml
<dependencies>
    <!-- config -->
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-config-server</artifactId>
    </dependency>
    <!-- web -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
</dependencies>
```

**Step2 编写全局配置文件**

在 `application.yaml` 中添加以下代码

```yaml
server:
  port: 3344
  
# spring 配置
spring:
  application:
    name: springcloud-config-server
```

**Step3 开启zuul**

在启动类 `module13_config_3344` 中添加注解 `@EnableConfigServer`

```java
@SpringBootApplication
@EnableConfigServer
public class module13_config_3344 {
    public static void main(String[] args) {
        SpringApplication.run(module13_config_3344.class, args);
    }
}

```

**Step4 启动查看是否成功**

### 客户端连接服务端



### 远程配置



## END

