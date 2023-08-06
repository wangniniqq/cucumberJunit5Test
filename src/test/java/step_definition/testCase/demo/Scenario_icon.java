package step_definition.testCase.demo;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.http.entity.ContentType;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import step_definition.utils.RequestUtils;

import java.util.List;
import java.util.Map;


public class Scenario_icon {

    //Background测试
//    @Given("{string}账号已登录")
//    public void loginA(String name) {
//        System.out.println("login------------"+name);
//    }

//    @BeforeStep
//    public void beforeTest(){
//        System.out.println("=======*****==icon====start===");
//    }
    JSONObject result;

    @When("上传{string}图标")
    public void uploadImage(String filename) {
        String filePath = "src\\test\\resources\\file\\1.png";
        String urlPath = "/gateway/basex/fileService/upload";
        JSONObject param = new JSONObject();

        param.put("bucketName","图例文件");
        result = RequestUtils.uploadFile(urlPath, filePath, ContentType.IMAGE_PNG,param);
        System.out.println(result);
    }

    @Then("图标上传成功")
    public void assertResult() {
        Assert.assertNotNull("data",result.getString("data"));
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }


    @Given("输入名称和孪生编码，保存：")
//    public void testDataTable(Map<String,String> table) {
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        for (String key :table.keySet()){
//            System.out.println(key + "::" + table.get(key).toString());
//        }
//    }

    //@DataTableType
    public void testDataTable(DataTable table) {
        System.out.println(table.row(0).get(0));
    }

    //outline测试
    @Given("选择{string}")
    public void getGroup(String name) {
        System.out.println(name);

    }
    @When("输入{string}和{string}，保存")
    public void getInfo(String name,String code) {
        System.out.println(name + ":::" + code);
    }

    @Then("孪生体{string}创建成功")
    public void AssertResult(String name) {
        System.out.println(name);
    }

    //    @Given("苹果的数量是{int}个")
//    public void 苹果的数量是个(int arg0) {
//    }


//    @AfterStep
//    public void afterTest(){
//        System.out.println("=======*****==icon====after===");
//    }


//    @After
//    public void teardown(){
//        System.out.println("=========icon====end===");
//    }


}
