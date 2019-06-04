package com.acme.devTrafficLights.ux;

import javax.baja.naming.BOrd;
import javax.baja.nre.annotations.AgentOn;
import javax.baja.nre.annotations.NiagaraSingleton;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.BSingleton;
import javax.baja.sys.Context;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;
import javax.baja.web.BIFormFactorMini;
import javax.baja.web.js.BIJavaScript;
import javax.baja.web.js.JsInfo;

/**
 * Created by Derek Otieno on 9/1/2016.
 */

@NiagaraSingleton
@NiagaraType(
        agent = @AgentOn(
                types = {"devTrafficLights:TrafficLightColor"}
        )
)

public class BTrafficLightColorFE extends BSingleton implements BIJavaScript, BIFormFactorMini {
    /*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
    /*@ $com.acme.devTrafficLights.ux.BTrafficLightColorFE(2713675747)1.0$ @*/
    /* Generated Thu Feb 01 20:14:52 CET 2018 by Slot-o-Matic (c) Tridium, Inc. 2012 */

    public static final BTrafficLightColorFE INSTANCE = new BTrafficLightColorFE();

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////

    @Override
    public Type getType() { return TYPE; }
    public static final Type TYPE = Sys.loadType(BTrafficLightColorFE.class);

    /*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

    private BTrafficLightColorFE() {}

    @Override
    public JsInfo getJsInfo(Context cx) { return jsInfo; }

    private static final JsInfo jsInfo =
            JsInfo.make(BOrd.make("module://devTrafficLights/rc/TrafficLightColorFE.js"));
}