package step_definition.utils;

import org.json.JSONArray;
import org.json.JSONObject;

public class JSONObjectUtils {

    public static Object queryValueFromArray(JSONArray arr,String key,String expVal,String expKey){
        Object value = null;
        for (Object obj:arr){
            JSONObject elem =  (JSONObject) obj;
            if (elem.getString(key).equals(expVal)){
                value =  elem.getString(expKey);
            }
        }
        return value;
    }
}
