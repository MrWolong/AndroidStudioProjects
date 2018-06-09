package com.example.thomas.mygames.interfaces;


import com.example.thomas.mygames.bean.RobotAnswer;

public interface TuringRobotModel {
    /**
     * 图灵机器人
     */
    public void getRobotAnswer(String question, BeanCallBack<RobotAnswer> callback);
}
