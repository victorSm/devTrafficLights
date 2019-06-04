package com.acme.devTrafficLights;
import javax.baja.data.BIDataValue;
import javax.baja.nre.annotations.AgentOn;
import javax.baja.nre.annotations.NiagaraAction;
import javax.baja.nre.annotations.NiagaraProperty;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.BComponent;
import javax.baja.sys.BIMixIn;
import javax.baja.sys.*;

/**
 * Created by UserId on 6/9/2016.
 */

@NiagaraType( agent=@AgentOn(types={"baja:User"}) )
@NiagaraProperty(
        name = "isImplemented",
        type = "boolean",
        flags = Flags.OPERATOR | Flags.READONLY | Flags.TRANSIENT | Flags.SUMMARY,
        defaultValue = "false"
)
@NiagaraProperty(
        name = "timesIncremented",
        type = "int",
        flags = Flags.READONLY ,
        defaultValue = "0"
)
@NiagaraAction(
        name="increment"
)
public class BTrafficMixin
        extends BComponent implements BIMixIn
{
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.acme.devTrafficLights.BTrafficMixin(2513147282)1.0$ @*/
/* Generated Tue May 07 10:05:07 EDT 2019 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Property "isImplemented"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code isImplemented} property.
   * @see #getIsImplemented
   * @see #setIsImplemented
   */
  public static final Property isImplemented = newProperty(Flags.OPERATOR | Flags.READONLY | Flags.TRANSIENT| Flags.SUMMARY, false, null);
  
  /**
   * Get the {@code isImplemented} property.
   * @see #isImplemented
   */
  public boolean getIsImplemented() { return getBoolean(isImplemented); }
  
  /**
   * Set the {@code isImplemented} property.
   * @see #isImplemented
   */
  public void setIsImplemented(boolean v) { setBoolean(isImplemented, v, null); }

////////////////////////////////////////////////////////////////
// Property "timesIncremented"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code timesIncremented} property.
   * @see #getTimesIncremented
   * @see #setTimesIncremented
   */
  public static final Property timesIncremented = newProperty(Flags.READONLY, 0, null);
  
  /**
   * Get the {@code timesIncremented} property.
   * @see #timesIncremented
   */
  public int getTimesIncremented() { return getInt(timesIncremented); }
  
  /**
   * Set the {@code timesIncremented} property.
   * @see #timesIncremented
   */
  public void setTimesIncremented(int v) { setInt(timesIncremented, v, null); }

////////////////////////////////////////////////////////////////
// Action "increment"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code increment} action.
   * @see #increment()
   */
  public static final Action increment = newAction(0, null);
  
  /**
   * Invoke the {@code increment} action.
   * @see #increment
   */
  public void increment() { invoke(increment, null, null); }

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BTrafficMixin.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

    @Override
    public String getDisplayNameInParent(Context cx)
    {
        return "Traffic MixIn";
    }

    public String toString(Context context)
    {
        return "isImplemented: " + getIsImplemented() +
                "\n timesIncremented: " + getTimesIncremented();
    }

    public void doIncrement()
    {
        setTimesIncremented( getTimesIncremented() + 1);
    }

}
