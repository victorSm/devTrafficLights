package com.acme.devTrafficLights;

import javax.baja.sys.*;

/**
 * Created by R. Derek Otieno on December 25, 2017.
 */
public class BFirstComponent extends BComponent {

    public static final Property timeExecuted = newProperty(Flags.SUMMARY, BAbsTime.DEFAULT, null);

    public BAbsTime getTimeExecuted(){
        return (BAbsTime) get(timeExecuted);
    }

    public void setTimeExecuted(BAbsTime v){
        set(timeExecuted, v, null);
    }

    public static final Property alarmType = newProperty(Flags.SUMMARY, "Heating Coil", null);

    public String getAlarmType(){
        return getString(alarmType);
    }

    public void setAlarmType(String v){
        setString(alarmType, v, null);
    }

    public static final Action execute = newAction(Flags.SUMMARY, null);


    public void execute() { invoke(execute,null,null); }

    public void doExecute(){
        System.out.println("Alarm Type: " + getAlarmType() + " at " + BAbsTime.now());
        setTimeExecuted(BAbsTime.now());
        System.out.println("written by Victor Smolinski");
    }

    public Type getType(){
        return TYPE;
    }

    public static final Type TYPE = Sys.loadType(BFirstComponent.class);

}