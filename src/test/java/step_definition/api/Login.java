package step_definition.api;

import org.json.JSONObject;
import step_definition.utils.RequestUtils;

public class Login {
    public JSONObject getLiense(){
        String path = "/gateway/licensex/validateLicense";
        JSONObject result = RequestUtils.get(path);

        return result;
    }

    public JSONObject getRecord(){
        String path = "/gateway/basex/sysOpLog/record";

        JSONObject param = new JSONObject();
        String account = "superAdmin";
        String appCode = "BaseX";
        String browser = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36";
        String message = "当前访问[欢迎页]页面";
        String opTime = "2023-08-02 15:12:57";
        int operationType = 14;
        String screenInfo = "{\\\"width\\\":1920,\\\"height\\\":1080,\\\"availHeight\\\":1040,\\\"availWidth\\\":1920}";
        String host = "http://basex.uino.com/welcome";

        param.put("account",account);
        param.put("appCode",appCode);
        param.put("browser",browser);
        param.put("message",message);
        param.put("opTime",opTime);
        param.put("operationType",operationType);
        param.put("screenInfo",screenInfo);
        param.put("url",host);

        JSONObject response = RequestUtils.post(path, param);
        return response;

    }

}
