 

# 一、项目概述

不管是企业还是学校，会议室都是工作中常使用的重要地点。而如今在会议室使用越来越频繁的情况下，设计一个会议室管理系统，能有效地减少会议室使用冲突的问题，从而提高办公的效率。

本系统旨在对会议室预约进行管理，帮助使用者快速了解会议室的使用情况，从而合理安排开会时间，避免时间上的冲突。同时，还可以帮助管理者了解会议室的损耗情况，合理地调配资源，对会议室进行维护。

在没有管理系统的前提下，用户想要找到一个合适的会议室需要花上不少功夫，包括但不限于提前通知自己的使用时间，提交书面申请等。对于管理者来说，没有平台的协助，难以与用户进行及时的沟通，会造成用户体验的下降。因此我们认为一个好的管理系统除了基本的管理功能，还需要提供一个供用户与管理者交流的平台。

所以我们设计了信息反馈的功能，用户可以在这里提出修改建议等。管理者可以通过后台查看这些信息，并通过用户预留的练习方式进行反馈。

# 二、项目总体介绍

**项目总体设计**：

会议室预约系统主要分为两个级别的用户：管理员、用户（员工）。

**开发环境**：Springboot架构、MySQL数据库、jdk17 、Tomcat 、layui框架

**功能模块介绍：**

###### 管理员端：

- 登录 ：用户提供有效的管理员用户名和对应的密码，系统与预存信息进行比较，确认无误后，记录下用户登录成功的状态信息，进入可供管理员进行管理操作的用户界面。
- 添加用户：管理员用户设置用户名，密码以及真实姓名，以此来添加用户。
- 会议室审核：在管理员正常登录条件下，对可执行操作的会议室进行审核。
- 会议室管理：管理员正常登录之后，可进入会议室管理界面进行管理
- 用户管理：管理员进入用户管理可视化界面，对用户进行管理操作
- 发布通知公告：管理员进入发布通知界面，编写发布通知内容，发布时间，点击确认发布按钮等。
- 查看会议室使用情况：管理员点击查看使用情况，可视化界面显示会议室的使用情况图表。

###### 用户（员工）端：

- 登录：输入管理事先分配好的用户名和密码，用户能够正常登录系统。
- 会议室查询：管理员已经进行该用户的创建与信息完成，用户能够正常登录系统
- 会议室预约：用户预约可执行的会议室，经过管理员审核后，用户界面可接收到预约成功信息。
- 管理我的预约：用户操作可执行的会议室，对自己的会议进行修改时间，删除等。
- 查看我的会议：用户正常登录之后，能够进入我的会议可视化界面，查看当前用户的会议室预约情况。
- 查看当前会议室情况：用户预约可执行的会议室，经过管理员审核后，用户界面可接收到预约成功信息。
- 查看通知公告：用户能够查看管理员发布的通知。

# 三、关键功能介绍

## 3.1 功能设计思路及关键运行截图

用token来限制用户对界面的访问

这里的token实际上是指一个加密的字符串，由用户名，密码和UUID组合加密得出的。

```java
Map<String, Object> info = new HashMap<>();

​      info.put("username", username);

​      info.put("pass", password);

String token = JwtUtil.sign(userId, info);
```

这里加载了JWT插件，借用JWT对字符串进行加密。

首先写一个拦截器，拦截器从request中获取token的值。

```java
String temp = request.getSession().getAttribute("token").toString();
```

再将拦截器添加到指定的路径

```java
public void addInterceptors(InterceptorRegistry registry) {

​    registry.addInterceptor(jwtInterceptor())

​        .addPathPatterns("/work/**", "/testBoot/**", "/delet", "/insert");

}
```

最后添加Controller类，在登录时设置token的值

```java
session.setAttribute("token", token);
```

设置token的具体实现

```java
public class JwtUtil {

  /**

   \* 过期5分钟

   */

  private static final long EXPIRE_TIME = 5 * 60 * 1000;

 

  /**

   \* jwt密钥

   */

  private static final String SECRET = "jwt_secret";

 

  /**

   \* 生成jwt字符串，五分钟后过期 JWT(json web token)

   \* 

   \* @param userId

   \* @param info,Map的value只能存放值的类型为：Map，List，Boolean，Integer，Long，Double，String and

   \*                                      Date

   \* @return

   */

  public static String sign(String userId, Map<String, Object> info) {

​    try {

​      Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);

​      Algorithm algorithm = Algorithm.HMAC256(SECRET);

​      return JWT.create()

​          // 将userId保存到token里面

​          .withAudience(userId)

​          // 存放自定义数据

​          .withClaim("info", info)

​          // 五分钟后token过期

​          .withExpiresAt(date)

​          // token的密钥

​          .sign(algorithm);

​    } catch (Exception e) {

​      e.printStackTrace();

​      return null;

​     }

  }

 

  /**

   \* 根据token获取userId

   \* 

   \* @param token

   \* @return

   */

  public static String getUserId(String token) {

​    try {

​      String userId = JWT.decode(token).getAudience().get(0);

​      return userId;

​    } catch (JWTDecodeException e) {

​      return null;

​    }

  }

 

  /**

   \* 根据token获取自定义数据info

   \* 

   \* @param token

   \* @return

   */

  public static Map<String, Object> getInfo(String token) {

​    try {

​      return JWT.decode(token).getClaim("info").asMap();

​    } catch (JWTDecodeException e) {

​      return null;

​    }

  }

 

  /**

   \* 校验token

   \* 

   \* @param token

   \* @return

   */

  public static boolean checkSign(String token) {

​    try {

​      Algorithm algorithm = Algorithm.HMAC256(SECRET);

​      JWTVerifier verifier = JWT.require(algorithm)

​          // .withClaim("username, username)

​          .build();

​      verifier.verify(token);

​      return true;

​    } catch (JWTVerificationException e) {

​      throw new RuntimeException("token 无效，请重新获取");

​    }

  }

}
```



## 3.2 功能3设计思路及关键运行截图

功能：用户查看和预约功能

设计思路：通过内置数据库访问接口，将查询后的结果展示在html界面上

关键代码：

数据库访问的xml配置

```xml
<select id="getAll" resultType="com.example.demo.domain.Rooms">

​    select * from rooms where roomsId = #{roomsId}

  </select>

 

  <delete id="delete">

​    delete from rooms where roomsId = #{roomsId}

  </delete>

 

  <insert id="insert" parameterType="java.util.List">

​    insert into rooms(roomsId,roomsName,roomsFloor,roomsLayer,roomsNum,roomsType,present) 

​    values

​    <foreach collection="Rooms" item="rooms" separator=",">

​      (#{message.roomsId},#{message.roomsName},#{message.roomsFloor},#{message.roomsLayer},#{message.roomsNum},#{message.roomsType},#{message.present})

​    </foreach>

  </insert>

 

  <update id="update">

​    update rooms set roomsId=#{roomsId},roomsName=#{roomsName},roomsFloor=#{roomsFloor},roomsLayer=#{roomsLayer},roomsNum=#{roomsNum},roomsType=#{roomsType},present=#{present}

</update>
```

Mapper的配置

```java
@Repository

public interface RoomsMapper {

  Rooms getAll(int roomsId);

 

  boolean delete(int roomsId);

 

  void insert(List<Rooms> rooms);

}

Service实现

@Autowired

  RoomsMapper roomsMapper;

 

  public Rooms getAll(int roomsId) {

​    return roomsMapper.getAll(roomsId);

  }

 

  public boolean delete(int roomId) {

​    return roomsMapper.delete(roomId);

  }

 

  public void insert(List<Rooms> rooms) {

​    roomsMapper.insert(rooms);

}
```

Controller实现

```java
@RequestMapping("getAllRooms")

  public Test<Rooms> getAllRooms() {

​    Test<Rooms> test = new Test<>();

​    test.setCode(0);

​    test.setCount(1000);

​    test.setMsg("");

​    test.setData(jdbcService.getAllRooms());

​    return test;

}
```

Jdbc操作数据库

```java
public class JdbcService {

  @Autowired

  private JdbcTemplate jdbcTemplate;

 

  public List<Rooms> getAllRooms() {

​    String sql = "select * from rooms";

​    List<Rooms> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Rooms.class));

 

​    return list;

  }

 

  public List<User> getAllUser() {

​    String sql = "select * from user";

​    List<User> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));

 

​    return list;

  }

}
```

前端jquery语句发送请求

```javascript
$("#btn").click(function () {

​      $.ajax({

​        url: '/testBoot/getuserid',

​        type: "get",

​        success: function (data) {

​           alert(data);

​        },

​        error: function () {

​          alert("123");

​        }

​      });

​      alert(JSON.stringify(tempaa));

​    });
```

本系统中关于数据的操作，均使用该方法

Controller最后会返回json类型的值，利用layui自带的数据表格模板对json文件进行解析和展示

```javascript
table.render({

​    elem: '#test'

​    , url: '/testBoot/getAllRooms'

​    , cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增

​    , toolbar: '#toolbarDemo'

​    , cols: [[

​     { type: 'radio' }

​     , { field: 'roomsId', title: 'ID', sort: true, align: 'center' }

​     , { field: 'roomsName', title: '房间名称', align: 'center' } 

​     , { field: 'roomsType', title: '房间类型', align: 'center' }

​     , { field: 'roomsFloor', title: '楼号', align: 'center' ,sort:"true"}

​     , { field: 'roomsLayer', title: '层数', align: 'center',sort:"true" }

​     , { field: 'roomsNum', title: '房间号', align: 'center' ,sort:"true"}

​     , { field: 'present', title: '状态', align: 'center' }

​    ]]

   });
```

 

运行截图：选择会议室

  图4-2-1



选择预约时间并预约会议室

 

图4-2-2

 

## 3.3 功能3设计思路及关键运行截图

功能：信息反馈

设计思路：用户将想要反映的信息输入到表单中，再由表单提交到后台，管理员在后台可以对用户反映的信息进行操作

关键代码：

采用layui的form模板对数据进行提交

```javascript
form.on('submit(demo1)', function (data) {

​        layer.alert(JSON.stringify(data.field), {

​          title: '最终的提交信息'

​        })

​        return false;

​      });
```

 

这里展示表单中单个项目的html代码

<div class="layui-input-inline">

```xml
            <select name="roomnum" lay-verify="required" autocomplete="off">

​              <option value="">请输入</option>

​              <option value="1">1</option>

​              <option value="2">2</option>

​            </select>

​          </div>


```

 

运行截图：

用户提交信息界面：

 

图4-3-1

管理员后台操作界面：

  

图4-3-2

## 3.4 功能设计思路及关键运行截图

功能：管理员对后台数据进行操作

设计思路：信息由数据表的形式展示出来，通过sql语句对数据进行增删改查

关键代码：

以会议室信息的增删改查为例：

利用jdbc获取全部会议室信息

```java
@Service

public class JdbcService {

  @Autowired

  private JdbcTemplate jdbcTemplate;

 

  public List<Rooms> getAllRooms() {

​    String sql = "select * from rooms";

​    List<Rooms> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Rooms.class));

 

​    return list;

}}
```

利用mybatis实现对数据的增删改查：

```xml
<select id="getAll" resultType="com.example.demo.domain.Rooms">

​    select * from rooms where roomsId = #{roomsId}

  </select>

 

  <delete id="delete">

​    delete from rooms where roomsId = #{roomsId}

  </delete>

 

  <insert id="insert" parameterType="java.util.List">

​    insert into rooms(roomsId,roomsName,roomsFloor,roomsLayer,roomsNum,roomsType,present) 

​    values

​    <foreach collection="Rooms" item="rooms" separator=",">

​      (#{message.roomsId},#{message.roomsName},#{message.roomsFloor},#{message.roomsLayer},#{message.roomsNum},#{message.roomsType},#{message.present})

​    </foreach>

  </insert>

 

  <update id="update">

​    update rooms set roomsId=#{roomsId},roomsName=#{roomsName},roomsFloor=#{roomsFloor},roomsLayer=#{roomsLayer},roomsNum=#{roomsNum},roomsType=#{roomsType},present=#{present}

</update>
```

前端使用layui框架和jquery实现对后台传输数据的处理：

```javascript
table.on('toolbar(test)', function (obj) {

​        var checkStatus = table.checkStatus(obj.config.id)

​          , data = checkStatus.data; //获取选中的数据

​        switch (obj.event) {

​          case 'add':

​            layer.msg('添加');

​            break;

​          case 'update':

​            if (data.length === 0) {

​              layer.msg('请选择一行');

​            } else if (data.length > 1) {

​               layer.msg('只能同时编辑一个');

​            } else {

​              layer.alert('编辑 [id]：' + checkStatus.data[0].roomsId);

​            }

​            break;

​          case 'delete':

​            if (data.length === 0) {

​              layer.msg('请选择一行');

​            } else {

​              layer.confirm('真的删除行么', function (index) {

​                

​                layer.close(index);

​              });

​              //for(var i = 0; i < data.length;i++){

​              //  deleterooms(data[i]["roomsId"]);

​              //}

​            }

​             break;

​        };

​      });

​    });
```

运行截图：

管理员查看信息部分截图：

 

图4-4-1

管理员添加数据截图：

 

图4-4-2

 

图4-4-3

管理员删除数据截图：

 

图4-4-4

 

图4-4-5

管理员修改数据截图：

 

图4-4-6

 

图4-4-7

 

## 3.5 功能设计思路及关键运行截图

功能：管理员对用户数据进行操作

设计思路： 利用jdbc获取数据库中全部关于用户的数据，转换位json格式传递到前端。前端由layui的table组件自动获取json数据并以数据表格的形式展示出来。前端在完成对数据的操作后，利用ajax向后台发送请求，后台收到请求后开始操作数据库。

关键代码：

以用户信息的增删，导出为例：

Sql代码：

```xml
<select id="Sel" resultType="com.example.demo.domain.User">

​    select * from user where id = #{id}

  </select>

 

  <select id="Sel1" resultType="com.example.demo.domain.User">

​    select * from user where userName = #{username}

  </select>

 

  <delete id="delete">

​    delete from user where id = #{id}

  </delete>

 

  <insert id="insert" parameterType="java.util.List">

​    insert into user(userName,passWord,realName,phone,email) 

​    values (#{userName},#{passWord},#{realName},#{phone},#{email})

  </insert>

 

  <update id="update">

​    update user set userName=#{userName},passWord=#{passWord},realName=#{realName},phone=#{phone},email=#{email} where id=#{id}

</update>
```

Controller类：

```java
@RequestMapping("getAllUser")

  public Test<User> getAllUser() {

 

​    Test<User> test = new Test<>();

​    test.setCode(0);

​    test.setCount(1000);

​    test.setMsg("");

​    test.setData(jdbcService.getAllUser());

​    return test;

  }

@RequestMapping("/user")

  public void insertUser(@RequestBody User user)

  {

​    userService.insert(user);

  }

@RequestMapping("/user")

  public void deleteUser(int id){

​    userService.delete(id);

  }

@RequestMapping("/user")

  public void update(@RequestBody User user)

  {

​    userService.update(user);;

  }
```

Ajax代码：

```javascript
$.ajax({

​            url: '/update/user',

​            type: "post",

​            data: JSON.stringify({

​              "id": data.id,

​              "userName": data.userName,

​              "passWord": data.passWord,

​              "realName": data.realName,

​              "phone": data.phone,

​              "email": data.email

​            }),

​            contentType: "application/json",

​            success: function (data) {

​              var temp = JSON.stringify(data.data);

​              layer.alert("更新数据成功");

​            },

$.ajax({

​        url: '/insert/user',

​        type: "post",

​        data: JSON.stringify({

​          "id": 0,

​          "userName": $("#userName").val(),

​          "passWord": $("#passWord").val(),

​          "realName": $("#realName").val(),

​          "phone": $("#phone").val(),

​          "email": $("#email").val()

​        }),

​        contentType: "application/json",

​        success: function (data) {

​          var temp = JSON.stringify(data.data);

​          layer.alert("插入数据成功");

​          refreshTable();

​        },

​        error: function () {

​          layer.alert("插入数据失败,用户名重复");

​         }

​      });

$.ajax({

​        url: '/delete/user',

​        type: "post",

​        data: { id: idTemp },

​        success: function (data) {

​          var temp = JSON.stringify(data.data);

​        },

​        error: function () {

​          layer.alert("删除数据失败");

​        }

​      });

Layui代码：

table.render({

​          elem: '#test'

​          , url: "/testBoot/getAllUser"

​          , toolbar: '#toolbarDemo'

​          , defaultToolbar: ['filter', 'exports', 'print']

​          , title: '用户数据表'

​          , cols: [[ //表头

​            { field: 'id', title: 'ID', width: 110, sort: true, fixed: 'left', totalRowText: '合计：' }

​            , { field: 'userName', title: '用户名', width: 260}

​            , { field: 'passWord', title: '密码', width: 260, sort: true, edit: 'text', totalRow: true }

​            , { field: 'realName', title: '真名', width: 260, sort: true, edit: 'text' }

​            , { field: 'phone', title: '联系电话', width: 260, sort: true, edit: 'text', totalRow: '{{ parseInt(d.TOTAL_NUMS) }} 分' }

​            , { field: 'email', title: '电子邮件',edit: 'text' }

​            , { fixed: 'right', title: '操作', toolbar: '#barDemo', width: 100 }

​          ]]

​        });
```

运行截图：

管理员对用户信息进行增删，导出部分截图：

 

图4-5-1

 

 

图4-5-2

 

 

图4-5-3

 

图4-5-4

 

图4-5-5

## 3.6功能设计思路及关键运行截图

功能：用户对个人信息的查看

设计思路： 前端接收用户的操作指令，通过ajax发送请求到后端。后端通过实体类接收json类型数据。经过Controller的处理后开始对数据库进行操作。从数据库得到的数据会从这一途径反方向传回

关键代码：

以用户个人信息的查看为例：

Ajax代码：

```javascript
$.ajax({

​        url: "/testBoot/getuserbyid",

​        type: "get",

​        success: function (data) {

​          var temp = data.data[0];

​          createTable(temp);

​        },

​        error: function () {

​          alert("获取用户信息失败，请刷新界面或重新登录");

​        }

​      });
```

Controller类：

```java
@RequestMapping("/test1/aa")

  public Test<CityMessage> test1() {

​    Test<CityMessage> test = new Test<>();

​    test.setCode(0);

​    test.setCount(1000);

​    test.setMsg("");

​    CityMessage city = new CityMessage();

​    city.setId("123");

​    city.setCity("asda");

​    city.setSex("man");

​    city.setUsername("null");

​    List<CityMessage> list = new ArrayList<>();

 

​    int num = 1000;

 

​    for (int i = 0; i < num; i++) {

​      list.add(city);

​    }

 

​    test.setData(list);

 

​    return test;

}
```

Service类：

```java
public List<User> getAllUser() {

​    String sql = "select * from user";

​    List<User> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));

 

​    return list;

  }
```

Xml代码：

```xml
<select id="Sel" resultType="com.example.demo.domain.User">

​    select * from user where id = #{id}

  </select>

 

  <select id="Sel1" resultType="com.example.demo.domain.User">

​    select * from user where userName = #{username}

  </select>

 

  <delete id="delete">

​    delete from user where id = #{id}

  </delete>

 

  <insert id="insert" parameterType="java.util.List">

​    insert into user(userName,passWord,realName,phone,email) 

​    values (#{userName},#{passWord},#{realName},#{phone},#{email})

  </insert>

 

  <update id="update">

​    update user set userName=#{userName},passWord=#{passWord},realName=#{realName},phone=#{phone},email=#{email} where id=#{id}

  </update> 
```

运行截图：

用户个人信息进行查看部分截图：

 

图4-6-1

 

 

图4-6-2
