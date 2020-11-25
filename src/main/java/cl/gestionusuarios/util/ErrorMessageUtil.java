package cl.gestionusuarios.util;

import org.json.JSONObject;

import java.util.List;

/**
 * Ricardo Carrasco S
 * 24-11-2020
 **/
public class ErrorMessageUtil {

    public static String errorMessage(String message) {
        JSONObject objectFile = new JSONObject();
        objectFile.put("mensaje", message);
        return objectFile.toString();
    }

    public static String errorMessage(List<String> messages) {
        JSONObject objectFile = new JSONObject();
        for (String m : messages) {
            objectFile.append("mensaje", m);
        }
        return objectFile.toString();
    }
}
