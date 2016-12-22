package lab.io.rush.dto;

import lab.io.rush.pojo.SnapRecord;

/**
 * 抢购消息DTO
 * Created by cpt72 on 2016/12/12.
 */
public class SnapMessageDto {
    /**
     * 抢购状态
     */
    private boolean status;
    /**
     * 抢购信息
     */
    private String message;
    private String code;
    private SnapRecord snapRecord;

    /**
     * @param resultEnum 抢购结果枚举
     */
    public SnapMessageDto(SnapResultEnum resultEnum) {
        this.message = resultEnum.value();
        this.code = resultEnum.toString();
        if (resultEnum == SnapResultEnum.SUCCESS)
            this.status = true;
        else
            this.status = false;
    }

    /**
     * @param resultEnum 抢购结果枚举
     */
    public SnapMessageDto(SnapResultEnum resultEnum, SnapRecord snapRecord) {
        this(resultEnum);
        this.snapRecord = snapRecord;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public SnapRecord getSnapRecord() {
        return snapRecord;
    }

    public void setSnapRecord(SnapRecord snapRecord) {
        this.snapRecord = snapRecord;
    }
}
