package br.com.southsystem.pocreceita.service.util;

public class FileSystemUtil {

    private static final String CSV_DEFAULT = "account.csv";
    private static final String USER_DIR = "user.dir";

    private static String storageOut;
    private static String storageIn;
    private static String storageLog;

    public static void setStorageOut(String storageOut) {
        FileSystemUtil.storageOut = storageOut;
    }

    public static void setStorageIn(String storageIn) {
        FileSystemUtil.storageIn = storageIn;
    }

    public static void setStorageLog(String storageLog) {
        FileSystemUtil.storageLog = storageLog;
    }

    public static String getResultCSV() {
        return getStorageOut() +"/"+CSV_DEFAULT;
    }

    public static String getCsvDefault() {
        return getStorageIn() +"/"+CSV_DEFAULT;
    }

    private static String getUserDir(){
        return System.getProperty(USER_DIR);
    }
    public static String getStorageOut() {
        return getUserDir() + storageOut;
    }

    public static String getStorageIn() {
        return getUserDir() + storageIn;
    }

    public static String getStorageLog() {
        return getUserDir() + storageLog;
    }

}
