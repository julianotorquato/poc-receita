package br.com.southsystem.pocreceita.service.util;

public class StringUtil {

    public static String getFirstValueWithSplitBy(String value, String regex, int index){
        if (value == null) return null;
        if (value.contains(regex)){
            return value.split(regex)[index];
        }
        return value;
    }

    public static String getPositionArray(String[] args, int index){
        if (args == null || args.length == 0)return null;
        return args.length <= index ? args[index] : null;
    }

}
