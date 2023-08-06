package step_definition.testCase;

import step_definition.api.Login;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.JSONObject;
import org.junit.Assert;

public class scenario_login {
    Login login = new Login();
    JSONObject result;

    @When("获取license")
    public void getLicense(){
        result= login.getLiense();
        int code = result.getInt("code");
        Assert.assertEquals(200,code);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Then("登录成功，成功获取记录信息")
    public void getRecord(){
         result = login.getRecord();
        String msg = result.getString("message");
        Assert.assertEquals("请求成功",msg);

    }

}
