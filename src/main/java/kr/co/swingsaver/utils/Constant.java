package kr.co.swingsaver.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;

public enum Constant {
    INCONSTANT;

    public static final String PERMISSION_AUTHENTICATED = "AUTH";
    public static final String ROOT_PREFIX = "";
    public static final String UPLOAD_PREFIX = "/upload";
    public static final String ADMIN_PREFIX = "/api/v1/admin";
    private static Map<String, MediaType> mediaMap;

    static {
        mediaMap = new HashMap<String, MediaType>();
        mediaMap.put("JPG", MediaType.IMAGE_JPEG);
        mediaMap.put("JPEG", MediaType.IMAGE_JPEG);
        mediaMap.put("GIF", MediaType.IMAGE_GIF);
        mediaMap.put("PNG", MediaType.IMAGE_PNG);
    }//

    public static MediaType getMediaType(String type) {
        return mediaMap.get(type.toUpperCase());
    }//
}
