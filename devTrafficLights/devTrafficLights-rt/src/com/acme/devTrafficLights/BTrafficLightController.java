package com.acme.devTrafficLights;

import javax.baja.nre.annotations.NiagaraAction;
import javax.baja.nre.annotations.NiagaraProperty;
import javax.baja.nre.annotations.NiagaraTopic;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.status.BStatusEnum;
import javax.baja.sys.*;

@NiagaraType

@NiagaraProperty(
        name = "minimumYellowTime",
        type = "baja:RelTime",
        defaultValue = "BRelTime.makeSeconds(5)"
)

@NiagaraProperty(
        name = "color",
        type = "BTrafficLightColor",
        flags = Flags.READONLY | Flags.SUMMARY,
        defaultValue = "BTrafficLightColor.red"
)

@NiagaraProperty(
        name = "lastChange",
        type = "baja:AbsTime",
        flags = Flags.READONLY | Flags.HIDDEN,
        defaultValue = "BAbsTime.NULL"
)

@NiagaraAction(
        name="transition",
        parameterType="BTrafficLightColor",
        defaultValue = "BTrafficLightColor.red"
)

@NiagaraTopic(
        name="lightChanged",
        eventType="BTrafficLightColor"
)

/**
 * The out slot is a BStatusEnum reflecting the
 * current color to allow easy linking to Niagara logic
 */
@NiagaraProperty(
        name = "out",
        type = "baja:StatusEnum",
        flags = Flags.OPERATOR | Flags.READONLY | Flags.TRANSIENT | Flags.SUMMARY,
        defaultValue = "new BStatusEnum()"
)
public class BTrafficLightController extends BComponent {
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.acme.devTrafficLights.BTrafficLightController(3520013714)1.0$ @*/
/* Generated Wed May 01 12:21:59 EDT 2019 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Property "minimumYellowTime"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code minimumYellowTime} property.
   * @see #getMinimumYellowTime
   * @see #setMinimumYellowTime
   */
  public static final Property minimumYellowTime = newProperty(0, BRelTime.makeSeconds(5), null);
  
  /**
   * Get the {@code minimumYellowTime} property.
   * @see #minimumYellowTime
   */
  public BRelTime getMinimumYellowTime() { return (BRelTime)get(minimumYellowTime); }
  
  /**
   * Set the {@code minimumYellowTime} property.
   * @see #minimumYellowTime
   */
  public void setMinimumYellowTime(BRelTime v) { set(minimumYellowTime, v, null); }

////////////////////////////////////////////////////////////////
// Property "color"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code color} property.
   * @see #getColor
   * @see #setColor
   */
  public static final Property color = newProperty(Flags.READONLY | Flags.SUMMARY, BTrafficLightColor.red, null);
  
  /**
   * Get the {@code color} property.
   * @see #color
   */
  public BTrafficLightColor getColor() { return (BTrafficLightColor)get(color); }
  
  /**
   * Set the {@code color} property.
   * @see #color
   */
  public void setColor(BTrafficLightColor v) { set(color, v, null); }

////////////////////////////////////////////////////////////////
// Property "lastChange"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code lastChange} property.
   * @see #getLastChange
   * @see #setLastChange
   */
  public static final Property lastChange = newProperty(Flags.READONLY | Flags.HIDDEN, BAbsTime.NULL, null);
  
  /**
   * Get the {@code lastChange} property.
   * @see #lastChange
   */
  public BAbsTime getLastChange() { return (BAbsTime)get(lastChange); }
  
  /**
   * Set the {@code lastChange} property.
   * @see #lastChange
   */
  public void setLastChange(BAbsTime v) { set(lastChange, v, null); }

////////////////////////////////////////////////////////////////
// Property "out"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code out} property.
   * The out slot is a BStatusEnum reflecting the
   * current color to allow easy linking to Niagara logic
   * @see #getOut
   * @see #setOut
   */
  public static final Property out = newProperty(Flags.OPERATOR | Flags.READONLY | Flags.TRANSIENT| Flags.SUMMARY, new BStatusEnum(), null);
  
  /**
   * Get the {@code out} property.
   * The out slot is a BStatusEnum reflecting the
   * current color to allow easy linking to Niagara logic
   * @see #out
   */
  public BStatusEnum getOut() { return (BStatusEnum)get(out); }
  
  /**
   * Set the {@code out} property.
   * The out slot is a BStatusEnum reflecting the
   * current color to allow easy linking to Niagara logic
   * @see #out
   */
  public void setOut(BStatusEnum v) { set(out, v, null); }

////////////////////////////////////////////////////////////////
// Action "transition"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code transition} action.
   * @see #transition(BTrafficLightColor parameter)
   */
  public static final Action transition = newAction(0, BTrafficLightColor.red, null);
  
  /**
   * Invoke the {@code transition} action.
   * @see #transition
   */
  public void transition(BTrafficLightColor parameter) { invoke(transition, parameter, null); }

////////////////////////////////////////////////////////////////
// Topic "lightChanged"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code lightChanged} topic.
   * @see #fireLightChanged
   */
  public static final Topic lightChanged = newTopic(0, null);
  
  /**
   * Fire an event for the {@code lightChanged} topic.
   * @see #lightChanged
   */
  public void fireLightChanged(BTrafficLightColor event) { fire(lightChanged, event, null); }

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BTrafficLightController.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/
public void doTransition(BTrafficLightColor color)
{
  // Get the original color
  BTrafficLightColor origColor = getColor();

  try
  {

    if (color.equals(BTrafficLightColor.red))
    {
      // If we are transitioning to red from green
      if (getColor().equals(BTrafficLightColor.green))
      {
        // When transitioning to red from green, go through
        // yellow
        setColor(BTrafficLightColor.yellow);

        // Transition automatically to red once the yellow
        // time has passed
        if (ticket != null) ticket.cancel();

        ticket =
                Clock.schedule(this, getMinimumYellowTime(),
                        BTrafficLightController.transition,
                        BTrafficLightColor.red);
      }
      // If we are transitioning to red from yellow
      else if (getColor().equals(BTrafficLightColor.yellow))
      {
        // Time left in the yellow state (measured in
        // milliseconds)
        BRelTime lastChange = getLastChange().delta(BAbsTime.now());
        long yellowTransitionTimeLeft =
                getMinimumYellowTime().getMillis()-
                        lastChange.abs().getMillis();

        if (yellowTransitionTimeLeft <= 0)
        {
          // Only transition if the correct delay has passed
          setColor(BTrafficLightColor.red);
        }
        else
        {
          // Otherwise schedule an automatic transition to
          // red once the yellow time has passed
          if (ticket != null) ticket.cancel();


          ticket =
                  Clock.schedule(this,
                          BRelTime.make(yellowTransitionTimeLeft),
                          BTrafficLightController.transition,
                          BTrafficLightColor.red);
        }
      }
    }
    else
    {
      // If transitioning to yellow or green, transition
      // immediately
      setColor(color);

      // Cancel ticket if needed (when not going from green
      // to red)
      if (ticket != null)
        ticket.cancel();
    }

  }
  finally
  {
    // If the color has changed from the original, then fire
    // the lightChanged topic and update the 'lastChanged'
    // slot
    if(!origColor.equals(getColor()))
    {
      // fire our change event
      fireLightChanged(getColor());
      setLastChange(BAbsTime.now());
    }
  }

}

  @Override
  public void started() throws Exception
  {
    // On startup, set the output to the current
    // traffic light color (the status will always be ok)
    getOut().setValue(getColor());
  }

  @Override
  public void changed(Property property, Context context)
  {
    // If the color property changes, reflect the current
    // color in the out slot
    if (property.equals(color))
      getOut().setValue(getColor());
  }

  ////////////////////////////////////////////////////////////////
  //  Attributes
  ////////////////////////////////////////////////////////////////

  private Clock.Ticket ticket = null;
}
