
@Smoke @icon
Feature: 图标管理

  Background:
    Given "wnn"账号已登录

#  @isolated
  Scenario: 创建孪生体分类
    When 上传"1.png"图标
    Then 图标上传成功
#    And "code"值为200
#    And "data" 为2.341223434353
#    And 苹果的颜色为red
#    * 苹果的颜色为blue
#    * 苹果的颜色为yellow

  @datatable
  Scenario: 表单测试
    Given 输入名称和孪生编码，保存：
#      | name | code |
      | WNN | 1 |
      | LGQ | 2 |

    @test
  Scenario Outline: outline测试
    Given 选择'<孪生体分组>'
    When 输入'<孪生体名称>'和'<孪生体编码>'，保存
    Then 孪生体'<孪生体名称>'创建成功
    Examples:
      | 孪生体分组 | 孪生体名称 | 孪生体编码 |
      | 安防 | wnn | 1|
      | 消防 | lg | 12 |


#    Scenario: int 测试
#      Given  苹果的数量是3个



