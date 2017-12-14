package com.lzw.ys7.sdk;

import java.util.List;

/**
 * @author: lzw
 * @date: 14/12/2017 11:12 AM
 * @desc:
 */

public class DeviceListResponse {
    private String msg;
    private String code;
    private List<DataValue> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<DataValue> getData() {
        return data;
    }

    public void setData(List<DataValue> data) {
        this.data = data;
    }

    public class DataValue {
        private String deviceSerial;
        private String deviceName;
        private String deviceType;
        private String status;
        private String defence;
        private String deviceVersion;

        public String getDeviceSerial() {
            return deviceSerial;
        }

        public void setDeviceSerial(String deviceSerial) {
            this.deviceSerial = deviceSerial;
        }

        public String getDeviceName() {
            return deviceName;
        }

        public void setDeviceName(String deviceName) {
            this.deviceName = deviceName;
        }

        public String getDeviceType() {
            return deviceType;
        }

        public void setDeviceType(String deviceType) {
            this.deviceType = deviceType;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getDefence() {
            return defence;
        }

        public void setDefence(String defence) {
            this.defence = defence;
        }

        public String getDeviceVersion() {
            return deviceVersion;
        }

        public void setDeviceVersion(String deviceVersion) {
            this.deviceVersion = deviceVersion;
        }
    }
}
