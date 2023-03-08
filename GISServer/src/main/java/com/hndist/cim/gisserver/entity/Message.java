package com.hndist.cim.gisserver.entity;

/**
 * @author FWY
 * @version V1.0.0
 * @ClassName: Message
 * @Description: TODO
 * @date 2022/5/30 2:38 下午
 * @Copyright: http://www.hndist.com All rights reserved.
 * 注意：本内容仅限于河南数慧信息技术有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public class Message {

    /**
     * @author FWY
     * @Description: 状态true:成功，false失败
     * @date 2022/5/30 2:42 下午
     * @Param
     * @return
     */
    private boolean state = false;

    /**
     * @author FWY
     * @Description: 消息（返回的所有内容）
     * @date 2022/5/30 2:42 下午
     * @Param 
     * @return 
     */
    private String message;

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
