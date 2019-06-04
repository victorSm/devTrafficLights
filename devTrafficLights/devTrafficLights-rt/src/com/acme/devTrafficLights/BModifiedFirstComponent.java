package com.acme.devTrafficLights;

import javax.baja.sys.*;
import javax.baja.timezone.BTimeZone;

/**
 * Created by R. Derek Otieno on December 25, 2017.
 * wb "ip:David-PC.attlocal.net|foxs:|station:|slot:/LabExcersices"
 */
public class BModifiedFirstComponent extends BComponent {

    public static final Property timeExecuted =
            newProperty(
                    Flags.READONLY | Flags.SUMMARY,
                    BAbsTime.DEFAULT,
                    BFacets.make(
                            BFacets.SHOW_SECONDS, BBoolean.TRUE,
                            BFacets.TIME_ZONE, BTimeZone.UTC
                    ));

    public BAbsTime getTimeExecuted(){
        return (BAbsTime) get(timeExecuted);
    }

    public void setTimeExecuted(BAbsTime v){
        set(timeExecuted, v, null);
    }

    public static final Property alarmType =
           newProperty(0, "",
                  BFacets.make(BFacets.FIELD_EDITOR, BString.make("alarm:AlarmClassFE")));

    public String getAlarmType(){
        return getString(alarmType);
    }

    public void setAlarmType(String v){
        setString(alarmType, v, null);
    }

    public static final Action execute = newAction(Flags.SUMMARY, null);

    public static final Topic statusTopic = newTopic(0, null);


    public void execute() { invoke(execute,null,null); }

    public void doExecute(){
        System.out.println("Alarm Type: " + getAlarmType());
        BAbsTime myTime = BAbsTime.now();
        fireStatusTopic(BDouble.make(myTime.getMillis()));
        setTimeExecuted(myTime);
    }

    public void fireStatusTopic(BDouble value){
        fire(statusTopic, value, null);

    }

    /*@Override
    public void started() throws Exception{
        Sys.getStation().linkTo(this, statusTopic, BStation.save);
    }*/

    public Type getType(){
        return TYPE;
    }

    public static final Type TYPE = Sys.loadType(BModifiedFirstComponent.class);

}