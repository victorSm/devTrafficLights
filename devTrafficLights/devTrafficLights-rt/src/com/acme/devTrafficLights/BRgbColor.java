package com.acme.devTrafficLights;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.StringTokenizer;
import javax.baja.nre.annotations.NiagaraSingleton;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.nre.annotations.NoSlotomatic;
import javax.baja.sys.BObject;
import javax.baja.sys.BSimple;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;

/**
 * Created by B. Puhak on November 16, 2018.
 * modified by R. Derek Otieno
 */

@NiagaraSingleton
@NiagaraType
@NoSlotomatic
public final class BRgbColor
        extends BSimple
{
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.acme.devTrafficLights.BRgbColor(66548216)1.0$ @*/
/* Generated Wed Dec 05 14:36:56 EST 2018 by Slot-o-Matic (c) Tridium, Inc. 2012 */

  public static final BRgbColor INSTANCE = new BRgbColor();

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

    private int r;
    private int g;
    private int b;

    public BRgbColor() {
        this(0,0,0);
    }

//    public static final BRgbColor DEFAULT =  null;
    public static final BRgbColor DEFAULT =  BRgbColor.make(255,254,253);
    private BRgbColor(int r, int g, int b)
    {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public static BRgbColor make(int r, int g, int b)
    {
        return new BRgbColor(r, g, b);
    }

    @Override
    public boolean equals(Object o)
    {
        if (o instanceof BRgbColor)
        {
            BRgbColor c = (BRgbColor)o;
            return r == c.r && g == c.g && b == c.b;
        }
        return false;
    }

//    @Override
    public void encode(DataOutput dataOutput) throws IOException
    {
        dataOutput.writeInt(r);
        dataOutput.writeInt(g);
        dataOutput.writeInt(b);
    }

//    @Override
    public BObject decode(DataInput dataInput) throws IOException
    {
        try
        {
            return BRgbColor.make(dataInput.readInt(), dataInput.readInt(), dataInput.readInt());
        }
        catch (IOException io)
        {
            System.out.println("Read error. Please, investigate " + io.toString());
            throw new IOException("Invalid input: " + io.toString());
        }
//        return null;
    }

//    @Override
    public String encodeToString() throws IOException
    {
        return r + ","+  g + "," + b;
    }

//    @Override
    public BObject decodeFromString(String s) throws IOException
    {
        try
        {
            StringTokenizer tok = new StringTokenizer(s, ",");
            if (tok.hasMoreTokens()) {
                int r = Integer.parseInt(tok.nextToken());
                if (tok.hasMoreTokens())
                {
                    int g = Integer.parseInt(tok.nextToken());
                    if (tok.hasMoreTokens())
                    {
                        int b = Integer.parseInt(tok.nextToken());
                    }
                    else
                    {
                        int b = 0;
                    }
                }
                else
                {
                    int g = 0;
                    int b = 0;
                }
            }
            else
            {
                int r = 0;
                int g = 0;
                int b = 0;
            }
        }
        catch(NullPointerException npe)
        {
            System.out.println("Your RGB string is null: " + npe.toString());
        }
        catch (Exception e)
        {
            System.out.println("Error with your RGB string: " + e.toString());
        }

        return BRgbColor.make(r, g, b);
    }

    @Override
    public Type getType() { return TYPE; }
    public static final Type TYPE = Sys.loadType(BRgbColor.class);
}