package com.acme.devTrafficLights;

import javax.baja.nre.annotations.*;
import javax.baja.sys.*;

/**
 * Created by R. Derek Otieno on February 16, 2018
 */

@NiagaraType

@NiagaraProperty
        (
                name="timeExecuted",
                type="BAbsTime",
                flags= Flags.READONLY | Flags.SUMMARY,
                defaultValue="BAbsTime.now()")
@NiagaraProperty
        (
                name="alarmType",
                type="String",
                flags=Flags.SUMMARY | Flags.READONLY,
                defaultValue = "Heating Coil: Default value from BSecondComponent"
        )

@NiagaraAction
        (
                name="execute",
                flags=Flags.SUMMARY
        )
public class BSecondComponent extends BComponent {
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.acme.devTrafficLights.BSecondComponent(1229200063)1.0$ @*/
/* Generated Tue Apr 30 09:40:56 EDT 2019 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Property "timeExecuted"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code timeExecuted} property.
   * @see #getTimeExecuted
   * @see #setTimeExecuted
   */
  public static final Property timeExecuted = newProperty(Flags.READONLY | Flags.SUMMARY, BAbsTime.now(), null);
  
  /**
   * Get the {@code timeExecuted} property.
   * @see #timeExecuted
   */
  public BAbsTime getTimeExecuted() { return (BAbsTime)get(timeExecuted); }
  
  /**
   * Set the {@code timeExecuted} property.
   * @see #timeExecuted
   */
  public void setTimeExecuted(BAbsTime v) { set(timeExecuted, v, null); }

////////////////////////////////////////////////////////////////
// Property "alarmType"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code alarmType} property.
   * @see #getAlarmType
   * @see #setAlarmType
   */
  public static final Property alarmType = newProperty(Flags.SUMMARY | Flags.READONLY, "Heating Coil: Default value from BSecondComponent", null);
  
  /**
   * Get the {@code alarmType} property.
   * @see #alarmType
   */
  public String getAlarmType() { return getString(alarmType); }
  
  /**
   * Set the {@code alarmType} property.
   * @see #alarmType
   */
  public void setAlarmType(String v) { setString(alarmType, v, null); }

////////////////////////////////////////////////////////////////
// Action "execute"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code execute} action.
   * @see #execute()
   */
  public static final Action execute = newAction(Flags.SUMMARY, null);
  
  /**
   * Invoke the {@code execute} action.
   * @see #execute
   */
  public void execute() { invoke(execute, null, null); }

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BSecondComponent.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/


    public void doExecute(){
        System.out.println("Alarm Type: " + getAlarmType());
        setTimeExecuted(BAbsTime.now());
        System.out.println("Updated time Executed  NOW: " + getTimeExecuted());
    }
}