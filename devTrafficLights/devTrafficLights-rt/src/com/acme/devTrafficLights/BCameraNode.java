package com.acme.devTrafficLights;

import javax.baja.nre.annotations.NiagaraProperty;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.*;

/**
 * Created by R. Derek Otieno on 2/17/2017.
 */
@NiagaraType
@NiagaraProperty
        (
                name="cameraNodeIP_Address",
                type="String",
                flags= Flags.SUMMARY | Flags.READONLY,
                defaultValue = "172.10.10.56"
        )
@NiagaraProperty
        (
                name="timeOfCapture",
                type="BAbsTime",
                flags= Flags.SUMMARY | Flags.READONLY,
                defaultValue = "BAbsTime.now()"
        )
@NiagaraProperty
        (
                name="nameOfPicture",
                type="String",
                flags= Flags.SUMMARY | Flags.READONLY,
                defaultValue = "InterIdAndTime"
        )

public class BCameraNode extends BStruct {
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.acme.devTrafficLights.BCameraNode(915574631)1.0$ @*/
/* Generated Mon Nov 12 20:42:34 EST 2018 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Property "cameraNodeIP_Address"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code cameraNodeIP_Address} property.
   * @see #getCameraNodeIP_Address
   * @see #setCameraNodeIP_Address
   */
  public static final Property cameraNodeIP_Address = newProperty(Flags.SUMMARY | Flags.READONLY, "172.10.10.56", null);
  
  /**
   * Get the {@code cameraNodeIP_Address} property.
   * @see #cameraNodeIP_Address
   */
  public String getCameraNodeIP_Address() { return getString(cameraNodeIP_Address); }
  
  /**
   * Set the {@code cameraNodeIP_Address} property.
   * @see #cameraNodeIP_Address
   */
  public void setCameraNodeIP_Address(String v) { setString(cameraNodeIP_Address, v, null); }

////////////////////////////////////////////////////////////////
// Property "timeOfCapture"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code timeOfCapture} property.
   * @see #getTimeOfCapture
   * @see #setTimeOfCapture
   */
  public static final Property timeOfCapture = newProperty(Flags.SUMMARY | Flags.READONLY, BAbsTime.now(), null);
  
  /**
   * Get the {@code timeOfCapture} property.
   * @see #timeOfCapture
   */
  public BAbsTime getTimeOfCapture() { return (BAbsTime)get(timeOfCapture); }
  
  /**
   * Set the {@code timeOfCapture} property.
   * @see #timeOfCapture
   */
  public void setTimeOfCapture(BAbsTime v) { set(timeOfCapture, v, null); }

////////////////////////////////////////////////////////////////
// Property "nameOfPicture"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code nameOfPicture} property.
   * @see #getNameOfPicture
   * @see #setNameOfPicture
   */
  public static final Property nameOfPicture = newProperty(Flags.SUMMARY | Flags.READONLY, "InterIdAndTime", null);
  
  /**
   * Get the {@code nameOfPicture} property.
   * @see #nameOfPicture
   */
  public String getNameOfPicture() { return getString(nameOfPicture); }
  
  /**
   * Set the {@code nameOfPicture} property.
   * @see #nameOfPicture
   */
  public void setNameOfPicture(String v) { setString(nameOfPicture, v, null); }

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BCameraNode.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

    public static final BCameraNode DEFAULT = new BCameraNode();

    public BCameraNode()
    {
        this("000.00.00.00", BAbsTime.now(),"picture Name");
    }

    public BCameraNode(String ipAddress, BAbsTime whenPicTaken, String pictureName)
    {
        setCameraNodeIP_Address(ipAddress);
        setTimeOfCapture(whenPicTaken);
        setNameOfPicture(pictureName);
    }

}