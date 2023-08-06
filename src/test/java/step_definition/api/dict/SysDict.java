package step_definition.api.dict;

import org.json.JSONArray;
import org.json.JSONObject;
import step_definition.utils.JSONObjectUtils;
import step_definition.utils.RequestUtils;

public class SysDict {
    public String getDataTypeByName(String name){
        String path = "/gateway/basex/sysDictType/dropDown?code=TWIN_CLASS_DATA_TYPE";
        JSONObject response = RequestUtils.getWithHeader(path);
        JSONArray arr =  response.getJSONArray("data");
        Object val = JSONObjectUtils.queryValueFromArray(arr, "value", name, "code");

        return val.toString();
    }

    public String getLayerByName(String name){
        String path = "/gateway/basex/sysDictType/dropDown?code=TWIN_CLASS_LEVEL";
        JSONObject response = RequestUtils.getWithHeader(path);
        JSONArray arr =  response.getJSONArray("data");
        Object val = JSONObjectUtils.queryValueFromArray(arr, "value", name, "code");

        return val.toString();
    }
}
