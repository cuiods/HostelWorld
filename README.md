# HostelWorld

基于SpringBoot+Hibernate+Spring+Swagger+Spring Security+Spring Data JPA实现的 HostelWorld 系统后端<br/>

[系统前端](https://github.com/cuiods/HostelWorld-Front)

## Introduction
HostelWorld系统后端采用全Restful风格API，并使用Swagger UI制定了详细的API文档，运行项目后访问[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)就可以查看项目的所有API。
系统主要分为以下十个模块：

 * /account：用户银行账户管理
 * /auth：用户身份认证和登陆注销
 * /check：酒店登记入住和退房管理
 * /hotel：酒店创建、管理和查看相关信息
 * /manager：经理审批和结算操作
 * /member：会员创建和管理
 * /reserve： 会员酒店预约和取消预约
 * /room： 酒店房间创建和管理
 * /stat： 系统数据统计
 * /upload： 阿里云对象存储获取服务端签名

## Features
#### 1、使用Swagger制定API文档
![](http://ww1.sinaimg.cn/large/005N9RKSgy1fdmd9uasqvj31fq0obtbz)
![](http://ww1.sinaimg.cn/large/005N9RKSly1fdmdb9cwzpj31dt0phgnm)
#### 2、全面使用函数式编程
项目中所有的业务逻辑代码全部使用[Java8函数式编程](http://blog.csdn.net/cuiods/article/category/6603088)。<br/>
下面是数据统计方法中的一部分示例：
```java
List<CheckRecordEntity> todayChecks = weekChecks.stream()
        .filter(checkRecordEntity -> checkRecordEntity.getCreatedAt().after(yesterday))
        .collect(Collectors.toList());
//set checks
statisticVo.setCheck(todayChecks.size());
long sumToday = todayChecks.stream()
        .mapToLong(checkRecordEntity -> checkRecordEntity.getRoomEntity().getPrice().intValue()).sum();
long sumWeek = weekChecks.stream()
        .mapToLong(checkRecordEntity -> checkRecordEntity.getRoomEntity().getPrice().intValue()).sum();
//set sum
statisticVo.setMoney(sumToday);
statisticVo.setWeekMoney(sumWeek);
weekChecks.stream()
        .collect(groupingBy(check -> (check.getCreatedAt().getTime()-aWeekAgo.getTime())/86400000))
        .forEach((aLong, checkRecordEntities) -> statisticVo.setChecks(aLong, checkRecordEntities.size()));
```
#### 3、 JSR-303 @Valid后端验证
采用前后端结合的方式进行输入验证，后端主要使用JSR-303 @Valid注解验证。
```java
@Data
public class CheckJson {
    @NotNull
    private int roomId;
    private int memberId;
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}",message = "Unsupported date format.")
    private String start;
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}",message = "Unsupported date format.")
    private String end;
    @Size(min = 1, max = 6, message = "tenant number should between 1 and 6")
    private List<Integer> tenants;
}

```
#### 4、错误码和异常处理
项目建立了完整的错误码体系。以下是几个示例:

| 错误码        | 字段    |  描述  |
| --------   | -----:   | :----: |
| 420        | MEMBER_NOT_FOUND      |   Cannot find member.  |
| 430        | ACCOUNT_CONFLICT      |   Not member account.  |
| 440        | SCORE_NOT_ENOUGH      |   Not enough score for the operation.  |

使用@ControllerAdvice定义全局异常处理控制器（保证返回的是包含错误信息的Json文件）
```java
@ControllerAdvice(basePackages = "edu.nju.web.controller")
@ResponseBody
public class GlobalExceptionHandler {
    @ExceptionHandler(value = HostelException.class)
    public ResultVo<String> hostelExceptionHandler(HostelException exception) throws Exception {
        return new ResultVo<>(exception.getCode(),exception.getMessage(),exception.getLocalizedMessage());
    }
    @ExceptionHandler(value = AccessDeniedException.class)
    public ResultVo<String> accessDeniedExceptionHandler(AccessDeniedException exception)
            throws Exception {
        return new ResultVo<>(ErrorCode.AUTHORITY_FORBIDDEN,MessageConstant.AUTHORITY_FORBIDDEN,exception.getReason()
                +":"+exception.getMessage());
    }
    ......
}
```
#### 5、Spring Security权限验证
系统用户根据角色分配有6种不同的权限，每次Http请求都会分析用户权限从而判断该操作是否可以进行。Http请求采用基本的Http Baisc Auth验证。
系统的权限包括：

| 编号        | 权限    |  描述  |
| --------   | -----:   | :----: |
| 1        | USER_BASE      |   Basic authority of hostel user.  |
| 2        | MEMBER_ACTIVE      |  Active member authority.  |
| 3        | HOTEL_ACTIVE      |   Active hotel authority.  |
| 4        | MEMBER_PAUSE      |   Suspended member authority.  |
| 5        | MANAGER      |   Manager authority.  |
| 6        | HOTEL_PAUSE      |   Suspended hotel authority.  |

权限配置见[SecurityConfig.java](https://github.com/cuiods/HostelWorld/blob/master/src/main/java/edu/nju/web/security/SecurityConfig.java)

#### 6、Slf4j + logback日志系统
对控制器Controller方法定义切面，记录每一次请求的详细信息。
```
2017-03-14 11:51:52.750  INFO 4740 --- [http-nio-8080-exec-3] edu.nju.web.log.WebLogAspect             : URL : http://localhost:8080/api/v1/manager/hotel/new
2017-03-14 11:51:52.750  INFO 4740 --- [http-nio-8080-exec-3] edu.nju.web.log.WebLogAspect             : HTTP_METHOD : GET
2017-03-14 11:51:52.750  INFO 4740 --- [http-nio-8080-exec-3] edu.nju.web.log.WebLogAspect             : IP : 0:0:0:0:0:0:0:1
2017-03-14 11:51:52.750  INFO 4740 --- [http-nio-8080-exec-3] edu.nju.web.log.WebLogAspect             : CLASS_METHOD : edu.nju.web.controller.ManagerController.approveNewHotels
2017-03-14 11:51:52.751  INFO 4740 --- [http-nio-8080-exec-3] edu.nju.web.log.WebLogAspect             : ARGS : []
2017-03-14 11:51:52.775  INFO 4740 --- [http-nio-8080-exec-3] edu.nju.web.log.WebLogAspect             : RESPONSE : [HotelVo(id=22, phone=00000000000, avatar=http://hostel-world.oss-cn-shanghai.aliyuncs.com/images/avatar.png, createdAt=2017-03-10 12:44:14.0......
```
#### 7、lombok插件
使用lombok插件自动生成get、set、构造器等方法。
```java
@Data
@NoArgsConstructor
public class AccountVo {
    private int id;
    private Integer balance;

    public AccountVo(AccountEntity accountEntity) {
        BeanUtils.copyProperties(accountEntity,this,"userEntity");
    }
}
```
#### 8、数据库设计
#####  （1）继承
`@Inheritance(strategy = InheritanceType.JOINED)`使用JOIN继承策略，子类表只保存相对于父类额外的属性。
父类User表定义：
```java
@Entity
@Table(name = "user")
@Where(clause="deleted_at is null")
@Inheritance(strategy = InheritanceType.JOINED)
public class UserEntity {
```
子类Member表定义：
```java
@Entity
@Table(name = "member")
@PrimaryKeyJoinColumn(name = "id")
public class MemberEntity extends UserEntity{
```

##### （2）表关联与懒加载
默认获取关联表时是懒加载，调用get方法后会自动从数据库里查询出来。
```java
@OneToMany
@JoinColumn(name = "user_id")
public List<AccountEntity> getAccountEntities() {
    return accountEntities;
}
```
使用`(fetch = FetchType.EAGER)`强制直接从数据库里查到相关属性，不需要调用get方法

##### （3）分页查询
```java
Sort sort = new Sort(sortDirection, sortColumn);
Pageable pageable = new PageRequest(page,pageSize,sort);
return hotelPageRepository.findAll(pageable);
```
##### （4）软删除
删除时设置deleted_at为删除时间，取出数据时要求deleted_at为null。
```java
@Where(clause="deleted_at is null")
```
#### 9、阿里云OSS后端签名
采用服务端签名的方法提高安全性。

## Get Started
 1. 新建Mysql数据库hostel，导入项目根目录的hostel.sql文件
 2. 编译项目，运行HostelWorldApplication中的main方法
 3. 访问[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)查看后端API
 4. 前端项目运行需要查看[系统前端](https://github.com/cuiods/HostelWorld-Front)
 5. 如果需要部署，使用mvn package -Dmaven.test.skip=true打包，然后直接运行java -jar [name].jar即可。

## License
[MIT](https://tldrlegal.com/license/mit-license)
