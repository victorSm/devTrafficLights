package com.acme.devTrafficLights;

import javax.baja.nre.annotations.NiagaraEnum;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.nre.annotations.Range;
import javax.baja.sys.BFrozenEnum;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;

/**
 * Created by Derek Otieno on December 23, 2018
 */

@NiagaraEnum
        (
                range = {
                        @Range(ordinal=100, value="red"),
                        @Range(ordinal=200, value="orange"),
                        @Range(ordinal=300, value="green"),
                        @Range(ordinal=400, value="blue"),
                        @Range(ordinal=500, value="indigo"),
                        @Range(ordinal=600, value="yellow"),
                        @Range(ordinal=700, value="violet"),
                },
                defaultValue = "violet"
        )

@NiagaraType

public final class BStdColors extends BFrozenEnum {
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.acme.devTrafficLights.BStdColors(2900533102)1.0$ @*/
/* Generated Sun Dec 23 13:12:53 EST 2018 by Slot-o-Matic (c) Tridium, Inc. 2012 */
  
  /** Ordinal value for red. */
  public static final int RED = 100;
  /** Ordinal value for orange. */
  public static final int ORANGE = 200;
  /** Ordinal value for green. */
  public static final int GREEN = 300;
  /** Ordinal value for blue. */
  public static final int BLUE = 400;
  /** Ordinal value for indigo. */
  public static final int INDIGO = 500;
  /** Ordinal value for yellow. */
  public static final int YELLOW = 600;
  /** Ordinal value for violet. */
  public static final int VIOLET = 700;
  
  /** BStdColors constant for red. */
  public static final BStdColors red = new BStdColors(RED);
  /** BStdColors constant for orange. */
  public static final BStdColors orange = new BStdColors(ORANGE);
  /** BStdColors constant for green. */
  public static final BStdColors green = new BStdColors(GREEN);
  /** BStdColors constant for blue. */
  public static final BStdColors blue = new BStdColors(BLUE);
  /** BStdColors constant for indigo. */
  public static final BStdColors indigo = new BStdColors(INDIGO);
  /** BStdColors constant for yellow. */
  public static final BStdColors yellow = new BStdColors(YELLOW);
  /** BStdColors constant for violet. */
  public static final BStdColors violet = new BStdColors(VIOLET);
  
  /** Factory method with ordinal. */
  public static BStdColors make(int ordinal)
  {
    return (BStdColors)red.getRange().get(ordinal, false);
  }
  
  /** Factory method with tag. */
  public static BStdColors make(String tag)
  {
    return (BStdColors)red.getRange().get(tag);
  }
  
  /** Private constructor. */
  private BStdColors(int ordinal)
  {
    super(ordinal);
  }
  
  public static final BStdColors DEFAULT = violet;

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BStdColors.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/
}
