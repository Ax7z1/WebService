# WebService
## 一 什么是websevice
webservice是部署在web上的，可访问的应用程序。  主要是对外提供业务接口（业务功能）。
日常生活中的webservice，比如，天气预告，查询手机归属地。

## 二  webservice的组成
![image](https://user-images.githubusercontent.com/88520528/223308142-8b5936db-6b70-4cc3-8826-8e675382bae5.png)

说明：

注册方：表示服务的注册服务器，所有的服务都要在注册方进行注册。

提供者：服务的实现方，要在注册方进行注册。服务的地址、接口、接口中的方法等等

消费者：服务的调用方，服务的客户端。广义的概念，包括：web应用、app、组件等等

## 三  传统webservice的技术标准

- xml技术    web服务具有平台无关性、语言无关性。

- soap 协议   Simple  Object  Access  简单对象访问协议  

- wsdl   web服务描述语言（xml文件），用于描述web服务
  ​     
Dubbo的微服务框架

 RPC调用  ：  远程方法调用    Remote Process Call


四   Restful服务(重点)
- json数据的交互
- http协议
- 无需 wsdl文件 

       Springboot+Springcloud的应用开发
       Restful服务：远程资源定位

五  开发传统的webservice
     Apache CXF = Celtix + Xfire，开始叫 Apache CeltiXfire，后来更名为 Apache CXF 了，以下简称为 CXF。Apache CXF 是一个开源的 web Services 框架，CXF 帮助您构建和开发 web Services ，它支持多种协议，比如：SOAP1.1,1,2XML/HTTP、RESTful 或者CORBA

Cxf是基于SOA总线结构，依靠spring完成模块的集成，实现SOA方式
开发webservice的步骤：
1. 创建web项目，导入cxf类库

2. web.xml中配置cxf的中央控制器

  ```xml
  <!-- CXF 中央控制器 -->
  <servlet>
      <servlet-name>cxfServlet</servlet-name>
      <servlet-class>org.apache.cxf.transport.servlet.CXFServlet</servlet-class>
      <load-on-startup>1</load-on-startup>
  </servlet>
  <servlet-mapping>
      <servlet-name>cxfServlet</servlet-name>
      <url-pattern>/services/*</url-pattern>
  </servlet-mapping>

  ```

3. 实现业务

- 接口

  ```java
  @WebService    //表示 当前是一个可发布的web接口
  public interface ProductService {
      
      public List<Product> findAllProducts();
  }
  ```

- 实现类

```java
public class ProductServiceImpl implements ProductService {
    @Override
    public List<Product> findAllProducts() {
        List<Product> list = new ArrayList<>();
        for (int i = 1; i <=10 ; i++) {
            list.add(new Product(i,"华为手机"+i,5000D,"中国深圳"));
        }
        return list;
    }
}

```

4.以Spring的方式来发布webservice，添加applicationContext.xml

```xml
<!-- 发布应用程序接口 -->
<jaxws:endpoint id="productServiceImpl" 
                implementor="com.gec.ws.service.ProductServiceImpl" 
                address="/productService"></jaxws:endpoint>
```

5. 访问webservice的wsdl文件

<http://localhost:8080/services/productService?wsdl>



6. 访问webservice的客户端 

- 以spring的方式创建远程方法调用的客户端 

  ```java
  /**
   * 远程调用的程序 （独立）
   * 远程的边界怎么判断 ？ 以JVM为基础
   */
  public class SpringWebserviceClient {

      public static void main(String[] args) {

          //读取spring容器
          ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
          //远程方法调用的代理接口实例
          ProductService productService = (ProductService) context.getBean("productService");
          List<Product> list = productService.findAllProducts();  //远程方法调用
          for (Product product : list) {
              System.out.println(product.getId()+","+product.getName()+","+product.getPrice()+","+product.getAddress());
          }
      }
  }
  ```

- 以代理的方式访问

  ```java
  public class ProxyWebserviceClient {

      public static void main(String[] args) {

          JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
          factory.setServiceClass(ProductService.class);   //设置代理接口class
          factory.setAddress("http://localhost:8080/services/productService");

          ProductService productService = (ProductService) factory.create();
          List<Product> list = productService.findAllProducts();  //远程方法调用
          for (Product product : list) {
              System.out.println(product.getId()+","+product.getName()+","+product.getPrice()+","+product.getAddress());
          }
      }
  }
  ```

  五  Restful风格的服务（重点）

  概念：网络上所有的资源（业务数据）都有一个唯一标识（URI）来定位。只要获到唯一标识就可以对资源进行操作（增删改查操作）。一般在SSM项目中，使用Springmvc发布服务接口（API）。

  简单来讲，restful服务就是“资源定位操作”

  Restful服务常用动作：

  C   POST    添加

  R   GET       查询

  U   PUT        编辑

  D   DELETE 删除

  ​

完成一个完整的Restful服务接口

```java

@RestController
public class ItemsController {
    @Autowired
    private ItemsService itemsService;

    @GetMapping("/findAll")
    public RespBean findAll() {
        List<Items> list = itemsService.findAll();
        return  RespBean.ok("所有产品列表",list);
    }

    @GetMapping("/findOne/{id}")
    public RespBean findOne(@PathVariable Integer id) {
        Items items = itemsService.findItemsById(id);
        return RespBean.ok("单件商品",items);
    }

    @PostMapping("/addItems")
    public RespBean addItems(@RequestBody Items items) {
        itemsService.addItems(items);
        return RespBean.ok("修改成功",items);
    }

    @PutMapping("/updateItems")
    public RespBean updateItems(@RequestBody Items items) {
        itemsService.updateItems(items);
        return RespBean.ok("编辑成功");
    }

    @DeleteMapping("/deleteItem/{id}")
    public RespBean deleteItem(@PathVariable Integer id) {
        itemsService.deleteItems(id);
        return RespBean.ok("删除成功");
    }
}
```



测试接口：

- 使用idea插件
- 使用postman
- 使用swagger

创建Swagger在线文档

实现步骤：

- 添加swagger的依赖

  ```xml
  <dependency>
      <groupId>io.springfox</groupId>
      <artifactId>springfox-swagger2</artifactId>
      <version>2.7.0</version>
  </dependency>
  <dependency>
      <groupId>io.springfox</groupId>
      <artifactId>springfox-swagger-ui</artifactId>
      <version>2.7.0</version>
  </dependency>
  ```

  ​

- 添加swagger

  ```java
  @Configuration
  @EnableSwagger2
  public class Swagger2Config {
      @Bean
      public Docket createRestApi(){
          return new Docket(DocumentationType.SWAGGER_2)
                  .apiInfo(apiInfo())
                  .select()
                  //为当前包下controller生成API文档
                  .apis(RequestHandlerSelectors.basePackage("cn.items.ssm.controller"))
                  //为有@Api注解的Controller生成API文档
  //                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                  //为有@ApiOperation注解的方法生成API文档
  //                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                  .paths(PathSelectors.any())
                  .build();
      }

      private ApiInfo apiInfo() {
          return new ApiInfoBuilder()
                  .title("SwaggerUI演示")
                  .description("商品微服务")
                  .contact("陈志凌")
                  .version("1.0")
                  .build();
      }
  }
  ```

  ​

- 添加swagger注解

  主要学习三个常用注解，分别作用于类、方法、参数列表

  ```java

  @Api(tags = "ItemsController",description = "商品后台管理服务接口")
  @RestController
  public class ItemsController {
      @Autowired
      private ItemsService itemsService;

      @ApiOperation("查询所有的商品信息")
      @GetMapping("/findAll")
      public RespBean findAll() {
          List<Items> list = itemsService.findAll();
          return  RespBean.ok("所有产品列表",list);
      }

      @ApiOperation("根据id查询单件商品信息")
      @GetMapping("/findOne/{id}")
      public RespBean findOne(@PathVariable Integer id) {
          Items items = itemsService.findItemsById(id);
          return RespBean.ok("单件商品",items);
      }

      @ApiOperation("添加商品信息")
      @PostMapping("/addItems")
      public RespBean addItems(@RequestBody @ApiParam("商品业务对象") Items items) {
          itemsService.addItems(items);
          return RespBean.ok("修改成功",items);
      }

      @ApiOperation("根据id修改单件商品信息")
      @PutMapping("/updateItems")
      public RespBean updateItems(@RequestBody  @ApiParam("商品业务对象") Items items) {
          itemsService.updateItems(items);
          return RespBean.ok("编辑成功");
      }

      @ApiOperation("根据id删除单件商品信息")
      @DeleteMapping("/deleteItem/{id}")
      public RespBean deleteItem(@PathVariable Integer id) {
          itemsService.deleteItems(id);
          return RespBean.ok("删除成功");
      }
  }
  ```

  swagger文档访问地址：

  <http://localhost:8099/swagger-ui.html>

  效果：



六  传统webservcie（RPC服务）与restful服务的区别

（1）传统的webservice服务是以RPC为基础的调用，Remote Process Call远程方法调用





说明：

- 客户端要把远程方法调用的参数封装 到xml文档中传递到服务端 ，进行远程方法的调用。
- 客户端必须引用服务端的接口类型，作为代理接口实例，用于远程方法调用。
- RPC的调用一般用于同构平台，比如，编辑语言相同。
- 通信协议一般是SOAP，性能较好。
- 微服务框架：dubbo， 采用了RPC调用



（2）Restful服务的特点



说明：

- Restful服务其实就是资源定位操作，并不是远程方法调用。
- 较轻量级，实现原理比较简单。
- 数据以json交互。
- 访问性能较差，以http作为协议
- 同构和异构平台都可以广泛使用。
- 微服务架构 ：springboot+springcloud



使用RestTemplate，做http交互访问

```java

@Controller   //返回视图
public class ItemsWebController {

    @Autowired
    private RestTemplate restTemplate;  //http的交互工具

    public String baseURL = "http://localhost:8099/";

    @GetMapping("/toAdd")
    public String toAdd() {
        return "addItem";
    }

    @GetMapping("/queryItems")
    public ModelAndView queryItems() {
        RespBean respBean = restTemplate.getForObject(baseURL+"findAll", RespBean.class);

        ModelAndView mv = new ModelAndView();
        mv.addObject("itemList",respBean.getData());
        mv.setViewName("itemlist");
        return  mv;
    }

    @PostMapping("/addItemsSubmit")
    public String addItemsSubmit(Items items) {
        RespBean respBean = restTemplate.postForObject(baseURL+"addItems",items,RespBean.class);
        System.out.println(respBean.getMessage());
        if (respBean.getCode()==200){
            return "redirect:/queryItems";
        } else {
            return "addItem";
        }

    }

    @GetMapping("/findItemById")
    public ModelAndView findItemById(int id) {
       RespBean respBean = restTemplate.getForObject(baseURL+"findOne/"+id,RespBean.class);
       ModelAndView mv = new ModelAndView();
       mv.addObject("itemDetail",respBean.getData());
       mv.setViewName("editItem");
       return  mv;
    }
    
    @PostMapping("/updateItemsSubmit")
    public String updateItemsSubmit(Items items) {
        restTemplate.put(baseURL+"updateItems",items);
        return "redirect:/queryItems";
    }
    
    @GetMapping("/deleteItems")
    public String deleteItems(int id) {
        restTemplate.delete(baseURL+"deleteItem/"+id);
        return "redirect:/queryItems";
    }
}
```

