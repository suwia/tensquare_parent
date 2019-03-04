package entity;

/**
 * @Author sirc_hzr
 * @Description  TODO 状态码实体类
 * @Date 14:01 2019/3/4
 * @Param
 * @return
 **/
public class StatusCode {
    public static final int OK = 20000;//成功
    public static final int ERROR = 20001;//失败
    public static final int LOGINERROR = 20002;//用户名或密码错误
    public static final int ACCESSERROR = 20003;//权限不足
    public static final int REMOTEERROR = 20004;//远程调用失败
    public static final int REPERROR = 20005;//重复操作
}