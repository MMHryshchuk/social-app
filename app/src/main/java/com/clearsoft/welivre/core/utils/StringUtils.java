package com.clearsoft.welivre.core.utils;

/**
 * Created by vladimir on 07.06.16.
 */
public final class StringUtils {
    public static boolean isNullEmpty(String text){
        return text == null || text.isEmpty();
    }

    public static String generateShortName(String firstName, String lastName){
        StringBuilder builder = new StringBuilder();
        if (!isNullEmpty(firstName)){
            builder.append(firstName.substring(0,1));
        }

        if (!isNullEmpty(firstName)){
            builder.append(lastName.substring(0,1));
        }

        return builder.toString();
    }

    public static String join(Long... item){
        if (item.length > 0) {
            StringBuilder nameBuilder = new StringBuilder();
            for (Long n : item) {
                nameBuilder.append(n).append(",");
            }
            nameBuilder.deleteCharAt(nameBuilder.length() - 1);
            return nameBuilder.toString();
        } else {
            return "";
        }
    }

    public static String join(String... item){
        if (item.length > 0) {
            StringBuilder nameBuilder = new StringBuilder();
            for (String n : item) {
                nameBuilder.append(n).append(",");
            }
            nameBuilder.deleteCharAt(nameBuilder.length() - 1);
            return nameBuilder.toString();
        } else {
            return "";
        }
    }
}
