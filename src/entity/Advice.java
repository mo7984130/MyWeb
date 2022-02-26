package entity;

/**
 * @author mo7984130
 * @Classname Advice
 * @Description TODO
 * @Date 2022/1/1 10:52 下午
 */
public class Advice {
    private static String database = "advice";
    private static int id;
    private static String name;
    private static String advice;
    private static String time;

    public static String getDatabase() {
        return database;
    }

    public static void setDatabase(String database) {
        Advice.database = database;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Advice.id = id;
    }
    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        Advice.name = name;
    }

    public static String getAdvice() {
        return advice;
    }

    public static void setAdvice(String advice) {
        Advice.advice = advice;
    }

    public static String getTime() {
        return time;
    }

    public static void setTime(String time) {
        Advice.time = time;
    }


}
