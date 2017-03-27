package com.itbird.retrofit.entity;


import java.util.ArrayList;

/**
 * 测试数据类
 * Created by itbird on 17/3/5.
 */
public class PatientList {

    private int Count;
    private String GroupName;
    private ArrayList<PatientDetailInfo> mPatientDetailInfos;

    public String getGroupName() {
        return GroupName;
    }

    public void setGroupName(String groupName) {
        GroupName = groupName;
    }

    public int getCount() {
        return Count;
    }

    public void setCount(int count) {
        Count = count;
    }

    public ArrayList<PatientDetailInfo> getPatientDetailInfos() {
        if (mPatientDetailInfos == null) {
            mPatientDetailInfos = new ArrayList<PatientDetailInfo>();
        }
        return mPatientDetailInfos;
    }

    public void setPatientDetailInfoss(ArrayList<PatientDetailInfo> mPatientDetailInfo) {
        this.mPatientDetailInfos = mPatientDetailInfo;
    }

    public class PatientDetailInfo {
        protected long Id;
        protected String RealName;
        String Avatar;
        String PopeleTag;

        public long getId() {
            return Id;
        }

        public void setId(long id) {
            Id = id;
        }

        public String getRealName() {
            return RealName;
        }

        public void setRealName(String realName) {
            RealName = realName;
        }

        public String getAvatar() {
            return Avatar;
        }

        public void setAvatar(String avatar) {
            Avatar = avatar;
        }

        public String getPopeleTag() {
            return PopeleTag;
        }

        public void setPopeleTag(String popeleTag) {
            PopeleTag = popeleTag;
        }
    }
}