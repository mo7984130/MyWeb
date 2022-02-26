package entity;

/**
 * @author mo7984130
 * @Classname Xiangji
 * @Description TODO
 * @Date 2022/2/11 11:47 下午
 */
public class Xiangji {

    private static final String database = "xiangji";
    private int id;
    private String realPath;
    private String webPath;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRealPath() {
        return realPath;
    }

    public void setRealPath(String realPath) {
        this.realPath = realPath;
    }

    public String getWebPath() {
        return webPath;
    }

    public void setWebPath(String webPath) {
        this.webPath = webPath;
    }
}
