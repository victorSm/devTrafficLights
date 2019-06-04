package com.acme.devTrafficLights.ux;

import javax.baja.naming.BOrd;
import javax.baja.nre.annotations.AgentOn;
import javax.baja.nre.annotations.NiagaraSingleton;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.BSingleton;
import javax.baja.sys.*;
import javax.baja.web.BIFormFactorMax;
import javax.baja.web.js.BIJavaScript;
import javax.baja.web.js.JsInfo;

/**
 * Created by R. Derek Otieno on February 18, 2018
 */
@NiagaraSingleton
@NiagaraType (
        agent = @AgentOn(
                types = {"devTrafficLights:TrafficLightController"}
        )
)
public class BTrafficLightWidget extends BSingleton implements BIJavaScript, BIFormFactorMax {
    /*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
    /*@ $com.acme.devtrafficlights.ux.BTrafficLightWidget(902037903)1.0$ @*/
    /* Generated Thu Dec 08 16:36:38 EST 2016 by Slot-o-Matic (c) Tridium, Inc. 2012 */

    public static final BTrafficLightWidget INSTANCE = new BTrafficLightWidget();

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////

    @Override
    public Type getType() { return TYPE; }
    public static final Type TYPE = Sys.loadType(BTrafficLightWidget.class);

    /*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

    @Override
    public JsInfo getJsInfo(Context cx) { return jsInfo; }

    private static final JsInfo jsInfo =
            JsInfo.make(BOrd.make(
                    "module://devTrafficLights/rc/TrafficLightWidget.js"));
}