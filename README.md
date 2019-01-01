一个自定义spring-boot-starter 例子：给一个字符串加上前缀和后缀。

可以先在其根目录执行 `mvn install` 操作来把其安装到本地的 Maven 的 Repository 中，之后新建一个 spring-boot 项目，在 pom.xml 引入如下的依赖：

```xml
<dependency>
    <groupId>com.test</groupId>
    <artifactId>example-spring-boot-starter</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```

在该spring-boot项目的application.properties中添加如下：

```properties
example.service.enabled=true
example.service.prefix=####
example.service.suffix=@@@@
```

使用：

```java
// 自动注入
@Autowired
private ExampleService exampleService;

// 输出 ####1@@@@
System.out.println(exampleService.wrap("1"));
```

```java
// 当然也可以这么用
// 输出 #1$
System.out.println((new ExampleService("$", "#")).wrap("1"));
```

### 加载方式

自定义的`starter`有两种方式来通知spring容器导入自己的auto-configuration类。

#### 1. spring.factories

在`starter`项目的`resources/META-INF/spring.factories`文件中加入需要自动化配置类的全限定名称。

`spring boot`项目中的`EnableAutoConfigurationImportSelector`会自动去每个jar的相应文件下查看`spring.factories`文件内容，并将其中的类加载出来在auto-configuration过程中进行配置。而`EnableAutoConfigurationImportSelector`在`@EnableAutoConfiguration`注解中被`import`。

这种方式下。只要是引入该starter，那么spring.factories中的auto-configuration类就会被装载，但是如果你希望有更加灵活的方式，那么就使用自定义注解来引入装配类。

#### 2. 注解

```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(ExampleAutoConfigure.class)
@Documented
public @interface EnableService {

}
```

定义如上所示的注解，然后注释掉`spring.factories`的内容，这样spring-boot项目中如果需要使用，则需要在对应的类上加上 `@EnableService` 注解，其它同上。



### 参考

- 自定义你的 spring-boot-starter：https://github.com/RitterHou/hello-starter
- 快速开发一个自定义Spring Boot Starter：https://www.jianshu.com/p/45538b44e04e
- Spring Boot (一): Spring Boot starter自定义：https://www.jianshu.com/p/4735fe7ae921
- SpringBoot编写自定义的starter：https://fangjian0423.github.io/2016/11/16/springboot-custom-starter/
- Spring注解】@Import注解：https://blog.csdn.net/qq_27470131/article/details/79539525

