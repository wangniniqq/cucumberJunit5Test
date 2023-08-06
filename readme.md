# Cucumber结合junit5测试
## Feature语法
    Feature：说明模块名称
    Background：定义单个Scenario通用的步骤，类似测试用例的通用"预置条件"，比如登录
    Scenario：单条数据测试
        1. 参数变量需要用" "来标识
        2. DataTable：用于批量上传多条数据
    Scenario Outline：多条数据测试
        1.定义时需要用'< >'来表示，用于一条case使用多个不同数据对象测试，比如登录时，输入正确数据、异常数据
## RunCucumberTest运行类
    @ConfigurationParameter：用于junit参数配置，如cucumber 自带 html报告
    @ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "junit:target/generated-report/cucumber-reports.html")
    @CucumberOptions：用于cucumber参数配置，如tags
        方法1：@ConfigurationParameter(key = FILTER_TAGS_PROPERTY_NAME , value = "@test")
        方法2：@CucumberOptions(tags = "@test")
        以上都可以在key上按ctrl进入源码，查看支持的参数配置
### Step definition定义
    1. 参数用{参数类型}来识别，去掉Feature定义中的双引号
    2. DataTable table：注意需要导io.cucumber.datatable.DataTable 的包，格式可以直接使用，也可以使用以下类型
        DataTable table
        List<List<String>> table
        List<Map<String, String>> table
        Map<String, String> table
        Map<String, List<String>> table
        Map<String, Map<String, String>> table
    3. Scenario outline：step defination 需要统一使用{string}来匹配，不能识别其他数据类型，注意此时不带单引号
## hook
    1. 注意导包为io.cucumber.java.*；不是junit
    2. beforeall、afterall：针对整体，可用于数据初始化和数据清理
    3. before\after:用在运行类中，针对每个scenario
    4. beforestep、afterstep：针对每个step
## 配置项
    1. cucumber.properties：针对cucumber的配置项
    2. junit-platform.properties：针对junit的配置项
       上述配置，详见 git code
## 并行测试策略配置
    1. 方法一：在junit中配置开关和并行粒度,参见git code
    2. 方法二：在cucumber中配置并行力度，参见如下:
    
    在 Cucumber 中，cucumber.execution.parallel.config.strategy 是一个用于配置并行执行策略的属性。
    该属性可用于设置 Cucumber 的并行执行方式，以提高测试的执行效率和速度。具体的策略可以根据你的需求进行配置，以下是一些常见的配置策略：
    simple: 简单的并行执行策略，将特性文件的执行分配给不同的线程。
    Copy Code-Dcucumber.execution.parallel.config.strategy=simple
    
    scenario: 按照场景（scenario）的级别执行并行测试。
    Copy Code-Dcucumber.execution.parallel.config.strategy=scenario
    
    feature: 按照特性（feature）的级别执行并行测试。
    Copy Code-Dcucumber.execution.parallel.config.strategy=feature
    
    examples: 按照 Scenario Outline 中的不同示例进行并行测试。
    Copy Code-Dcucumber.execution.parallel.config.strategy=examples
    
    这是一些常见的并行执行策略示例，你可以根据自己的需求选择适合你的策略。
    
-------------------------------------------------------------------------------------
    下面是一个配置示例：
    如果你正在使用 Maven 进行构建，你可以在 pom.xml 文件中添加以下配置：
    xmlCopy Code<properties>
    <cucumber.execution.parallel.config.strategy>scenario</cucumber.execution.parallel.config.strategy>
    </properties>
    
    这将设置并行执行策略为 scenario，即按照场景级别进行并行执行。
    另一种方式是通过命令行参数设置。例如，使用 mvn 命令进行测试运行时，可以使用以下配置：
    bashCopy Codemvn test -Dcucumber.execution.parallel.config.strategy=scenario
    
    这样就可以在命令行中将并行执行策略设置为 scenario。
    report：集成Cluecumber插件
    添加maven依赖：参见https://cucumber.io/docs/cucumber/reporting/?lang=java中Cluecumber插件
    运行cucumber 运行器，生成report.json文件
    命令行运行，mvn cluecumber:reporting，生成html报告
## report：集成Cluecumber插件
    1. 添加maven依赖：参见https://cucumber.io/docs/cucumber/reporting/?lang=java中Cluecumber插件
    2. 运行cucumber 运行器，生成report.json文件
    3. 命令行运行，mvn cluecumber:reporting，生成html报告
## CI集成
    参见官网CI部分
## 待研究
    1. 自定义参数类型
## 参考链接
    官网：https://cucumber.io/docs/installation/
    junit api：https://junit.org/junit5/docs/current/api/index.html
    junit配置项参考：https://github.com/cucumber/cucumber-jvm/blob/main/cucumber-junit-platform-engine/README.md